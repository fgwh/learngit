package com.hgsoft.base.service;

import com.hgsoft.base.dao.UploadFileErrorDao;
import com.hgsoft.base.entity.UploadFileError;
import com.hgsoft.security.service.BaseService;
import com.hgsoft.util.Pager;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

@Service
public class UploadFileErrorService extends BaseService<UploadFileError> {
	@Resource
    public void setDao(UploadFileErrorDao dao) {
        super.setDao(dao);
    }

	/***
	 * 根据主表id，删除信息
	 * @author linnt
	 * @date 2015年1月31日
	 * @param id
	 */
	public void deleteByUploadFileId(String uploadFileId) {
		String hql = " delete from UploadFileError c where c.uploadFile.id='"+uploadFileId+"'";
		getDao().updateByHql(hql);
	}

	public List queryUploadFileErrorListByUploadFileId(Pager pager, String uploadFileId) {
		//getDao().findAll(Property.eq("uploadFile.id", uploadFileId));
		
		String hql = " from UploadFileError where uploadFile.id='"+uploadFileId+"'";
		
		String totalStr = "select count(*) from base_uploadfile_error where fileId='"+uploadFileId+"'";
		
		List<Integer> counts = (List<Integer>)getDao().findBySql(totalStr, null);
		Long totalSize = (counts == null || counts.isEmpty()) ? 0l : counts.get(0);
		pager.setTotalSize(totalSize);
		
		return getDao().findByHql(hql, pager);
	}

	public HSSFWorkbook exportExcelByLumianposunCondition(String uploadFileId) {
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建表头
		HSSFSheet sheet = wb.createSheet("导入错误信息");
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		String sql = "select top 50000 colsInfo,errorInfo from base_uploadfile_error where fileId='"+uploadFileId+"'";
		List list = getDao().queryBySQL(sql, null);
		if (list!=null && !list.isEmpty()){
			int rowIndex = 0;
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Object[] object = (Object[]) iterator.next();
				//String colsInfo = object[0]+"";
				String errorInfo = object[1].toString();
				
//				if (colsInfo==null){
//					continue;
//				}
//				
//				String[]cols = colsInfo.split("#");
//				int len = cols.length;
				HSSFRow row = sheet.createRow(rowIndex++);
				HSSFCell cell;
//				for (int i = 0; i < len; i++) {
//					cell = row.createCell(i);
//					cell.setCellStyle(style);
//					cell.setCellValue(cols[i]);
//				}
				cell = row.createCell(0);
				cell.setCellStyle(style);
				cell.setCellValue(errorInfo);
			}
		}
		return wb;
	}
	
	
}
