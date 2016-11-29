package com.hgsoft.base.service;

import com.hgsoft.base.dao.BaseDataDao;
import com.hgsoft.base.entity.BaseDataEntity;
import com.hgsoft.base.entity.UploadFile;
import com.hgsoft.base.entity.UploadFileError;
import com.hgsoft.base.interfaces.ImportExcelFileInterface;
import com.hgsoft.base.util.ImportExcelUtils;
import com.hgsoft.excel.POIExcel;
import com.hgsoft.flow.constant.FlowConstant;
import com.hgsoft.flow.service.FlowInterface;
import com.hgsoft.main.service.DictionaryService;
import com.hgsoft.security.entity.Admin;
import com.hgsoft.security.service.BaseService;
import com.hgsoft.util.BeaUtilsEx;
import com.hgsoft.util.BusinessException;
import com.hgsoft.util.Pager;
import com.hgsoft.util.StrTool;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public abstract class BaseDataService<E extends BaseDataEntity> 
extends BaseService<E> implements FlowInterface,ImportExcelFileInterface {
	@Resource
	protected DictionaryService dictionaryService;

	public List<E> query(Pager pager, E entity) {
		return getBaseDataDao().query(pager, entity);
	}
	
	public List<E> queryWorkingTaskByUserId(Pager pager, E bridge, Admin operator) {
		return getBaseDataDao().queryWorkingTaskByUserId(pager, bridge, operator);
	}

	public List<E> queryWorkedTaskByUserId(Pager pager, E bridge, Admin operator) {
		return getBaseDataDao().queryWorkedTaskByUserId(pager,bridge, operator);
	}

	public void updateAudit(E entity) {
		// 新增一条记录
		E newEntity = getBaseDataDao().newEntity();
		try {
			BeaUtilsEx.copyProperties(newEntity, entity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		// 更新之前已审批结束的
		entity.setLatestMark(false);
		update(entity);

		newEntity.setId(null);
		newEntity.setFlowStatus(0);
		newEntity.setLatestMark(true);
		save(newEntity);
	}

	
	@Override
	public void sendNextStep(String id, String nextStepType, String operateType) {
		E dbEntity = find(id);
		int maxFlow = getBaseDataDao().getMaxFlowStatusByPid(dbEntity.getPid());//获取当前flowStatus最大值，在后面+1
		if (StrTool.isBlankStr(dbEntity.getPid())){//修改之前的那条记录的flowStatus和latestMark,第一次提交时直接修改pid为当前id
			dbEntity.setPid(dbEntity.getId());
		}
		dbEntity.setLatestMark(false);
		update(dbEntity);
		//在原有数据基础上复制一条新的数据,latestMark设置为1，并且flowStatus+1,再插入数据库
		E nextEntity = getBaseDataDao().newEntity();
		try {
			BeaUtilsEx.copyProperties(nextEntity, dbEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		nextEntity.setId(null);
		nextEntity.setLatestMark(true);
		if (FlowConstant.FLOW_STEP_NEXT.equals(nextStepType) || FlowConstant.FLOW_STEP_UP.equals(nextStepType) ){
			if (FlowConstant.OPERATE_TYPE_REPORT_PASS.equals(operateType)){
				nextEntity.setFlowStatus(maxFlow+1);//如果是上报请求通过，且是发送下一步，则flowStatus自动加一
			} else {
				if (nextEntity.getFlowStatus()==FlowConstant.FLOW_STATUS_REPORTED){//如果是刚从审批结束的状态，来通过审批请求删除，则第一条新建的记录流程状态设置为-1
					nextEntity.setFlowStatus(-1);
				} else {
					nextEntity.setFlowStatus(nextEntity.getFlowStatus()-1);
				}
			}
		} else {
			if (FlowConstant.OPERATE_TYPE_REPORT_PASS.equals(operateType)){//审批结束
				nextEntity.setFlowStatus(999);//如果是上报请求通过，且是审批结束，则flowStatus自动设置成999
			} else {
				nextEntity.setFlowStatus(-999);
				nextEntity.setLatestMark(false);//设置为不可见
			}
		}
		save(nextEntity);
		//是否需要复制附件？好像记录之间没有附件，这个为以后做个口
//		return newBaseBridge.getPid();
	}
	
	@Override
	public String findBizPidByBizId(String id) {
		E temp = find(id);
		if (StrTool.isBlankStr(temp.getPid())){
			temp.setPid(id);
		}
		return temp.getPid();
	}
	
	public Boolean isExist(E entity) {
		return getBaseDataDao().isExist(entity);
	}

	private BaseDataDao<E> getBaseDataDao(){
		return (BaseDataDao<E>)this.getDao();
	}
	
	public E newEntity(){
		return getBaseDataDao().newEntity();
	}
	
	//导入---------------------------------------------------------------------------------------------------------
	
	/**
	 * 导入和导出数据时, 从第几行开始是数据行
	 * @return
	 */
	protected int getDataRowNumber(){
		return 3;
	}

	protected int getReportYearColumnNumber(){
		return 1;
	}

	protected int getReportDateColumnNumber(){
		return 3;
	}

	protected int getReportorColumnNumber(){
		return 5;
	}
	
	public void importExcel(){
		allDistinctRecord.clear();
		List<E> insertList = new ArrayList<E>();
		int rowCount = sheet.getPhysicalNumberOfRows();
		//第一行去掉
		//判断第二行的值，是否满足【上报年度:		】、【上报日期:		】【填报人:	】
		String reportYear="";
		Date reportDate=null;
		String operator = "";
		
		Row rowTwo = sheet.getRow(1);
		try{
			reportYear = getCellValue(rowTwo.getCell(getReportYearColumnNumber()));
			reportDate = getDateCellValue(rowTwo.getCell(getReportDateColumnNumber()));
			operator = (String)getCellValue(rowTwo.getCell(getReportorColumnNumber()));
		}catch(Exception e){
			UploadFileError ufe = new UploadFileError();
			ufe.setErrorInfo("该文档开头没有在指定位置录入上报年份、上报日期、上报人员");
			ufe.setUploadFile(uploadFile);
			uploadFileErrorService.save(ufe);
			return;
		}
		if (StrTool.isBlankStr(reportYear) || StrTool.isBlankStr(operator) || reportDate==null){
			UploadFileError ufe = new UploadFileError();
			ufe.setErrorInfo("该文档开头没有在指定位置录入上报年份、上报日期、上报人员");
			ufe.setUploadFile(uploadFile);
			uploadFileErrorService.save(ufe);
			return;
		}
		//String = 
		//这些项没有，则直接不处理整份文档
		for (int j = getDataRowNumber(); j < rowCount; j++) {
			Row row = sheet.getRow(j);
			//计时器，现在处理到第几条
			ImportExcelUtils.getImportInfoMap().get(uploadFile.getId()).currCount++;
			if (row != null) {
				try{
					E entity = trancCellToObj(row,reportYear,reportDate,operator);
					insertList.add(entity);
					if (insertList.size()>10000){
						saveBatchByList(insertList);
						System.out.println(j);
						insertList.clear();
					}
					
					//计时器，成功插入几条
					ImportExcelUtils.getImportInfoMap().get(uploadFile.getId()).succCount++;
				}catch (BusinessException e){
					//e.printStackTrace();
					UploadFileError ufe = new UploadFileError();
					String msg = e.getMessage();
					if (e.getMessage()!=null && e.getMessage().length()>200){
						msg = e.getMessage().substring(0, 200);
					}
					ufe.setErrorInfo("第"+row.getRowNum()+"记录："+msg);
					ufe.setUploadFile(uploadFile);
					uploadFileErrorService.save(ufe);
					//计时器，插入失败几条s
					ImportExcelUtils.getImportInfoMap().get(uploadFile.getId()).failCount++;
				}catch (Exception e){
					e.printStackTrace();
					//其他情况不处理了
					//估计也就剩重复数据的错误了
				}
			}
		}
		
		if (!insertList.isEmpty()){
			saveBatchByList(insertList);
		}
		
	}
	protected void saveBatchByList(List<E> insertList) {
		for (E e : insertList) {
			save(e);
		}
	}
	protected Sheet sheet;
	protected UploadFile uploadFile;
	
	public abstract E trancCellToObj(Row row, String reportYear, Date reportDate, String operator) throws BusinessException;

	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	public void setUploadFile(UploadFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	
	@Resource
	protected UploadFileErrorService uploadFileErrorService;
	
	public Double getDoubleCellValue(Cell cell){
		return POIExcel.getDoubleCellValue(cell);
//		if (cell==null){
//			return null;
//		}
//		NumberFormat formatter = new DecimalFormat("#.000");
//		if (Cell.CELL_TYPE_BLANK==cell.getCellType()){
//			return null;
//		} else {
//			return Double.parseDouble(formatter.format(cell.getNumericCellValue())); 
//		}
	}
	
	public Date getDateCellValue(Cell cell){
		return POIExcel.getDateCellValue(cell);
//		if (cell==null){
//			return null;
//		}
//		if (Cell.CELL_TYPE_BLANK==cell.getCellType()){
//			return null;
//		}
//		if (cell.getCellType()==Cell.CELL_TYPE_STRING){
//			return DateUtil.parse(cell.getRichStringCellValue().getString(), DateUtil.PATTERN_STRING_DATE);
//		}
//		if (HSSFDateUtil.isCellDateFormatted(cell)) {
//        	return cell.getDateCellValue();  
//        } else {   
//        	throw new BusinessException();
//        } 
	}
	
	public String getCellValue(Cell cell) {
		return POIExcel.getCellValue(cell);
//		aif (cell==null){
//			return "";
//		}
//        String ret = null;  
//        try{
//	        switch (cell.getCellType()) {  
//	        case Cell.CELL_TYPE_BLANK:  
//	            ret = "";  
//	            break;  
//	        case Cell.CELL_TYPE_BOOLEAN:
//	            return String.valueOf(cell.getBooleanCellValue());
//	        case Cell.CELL_TYPE_ERROR:  
//	            ret = null;  
//	            break;  
//	        case Cell.CELL_TYPE_FORMULA:  
//	            Workbook wb = cell.getSheet().getWorkbook();  
//	            CreationHelper crateHelper = wb.getCreationHelper();  
//	            FormulaEvaluator evaluator = crateHelper.createFormulaEvaluator();  
//	            ret = (String)getCellValue(evaluator.evaluateInCell(cell));  
//	            break;  
//	        case Cell.CELL_TYPE_NUMERIC:  
//	            if (HSSFDateUtil.isCellDateFormatted(cell)) {
//	            	return DateUtil.format(cell.getDateCellValue(),DateUtil.PATTERN_STRING_DATE);  
//	            } else {   
//	            	return NumberTool.format(cell.getNumericCellValue(),0);  
//	            }    
//	           // return NumberTool.format(cell.getNumericCellValue(),0);
//	        case Cell.CELL_TYPE_STRING:  
//	            return cell.getRichStringCellValue().getString();  
//	        default:  
//	            ret = null;  
//	        }  
//        }catch(Exception e){
//        	e.printStackTrace();
//        }
//        return ret; //有必要自行trim  
    }
	protected Map<String, Object> allDistinctRecord = new HashMap<String, Object>();
	/***
	 * 取重复数据
	 * @return
	 */
	public Map<String, Object> getAllDistinctRecord() {
		if (allDistinctRecord==null || allDistinctRecord.isEmpty()){
			List list = getBaseDataDao().queryAllDistinctRecord();
			if (list!=null){
				for (Iterator iterator = list.iterator(); iterator.hasNext();) {
					Object[] object = (Object[]) iterator.next();
					allDistinctRecord.put(object[0]+""+object[1]+""+object[2]+ "", true);
				}
			}
		}
		return allDistinctRecord;
	}
}
