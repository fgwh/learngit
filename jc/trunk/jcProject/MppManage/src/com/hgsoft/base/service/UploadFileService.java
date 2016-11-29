package com.hgsoft.base.service;

import com.ibm.icu.util.Calendar;
import com.hgsoft.base.dao.UploadFileDao;
import com.hgsoft.base.entity.UploadFile;
import com.hgsoft.base.util.ImportExcelUtils;
import com.hgsoft.security.service.AdminService;
import com.hgsoft.security.service.BaseService;
import com.hgsoft.util.Order;
import com.hgsoft.util.Pager;
import com.hgsoft.util.Property;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.List;

@Service
public class UploadFileService extends BaseService<UploadFile> {
	@Resource
    public void setDao(UploadFileDao dao) {
        super.setDao(dao);
    }
	
	/***
	 * 根据上传文件的业务类型，查找出对应的文件上传记录
	 * @author linnt
	 * @date 2014年11月1日
	 * @param pager
	 * @param businessType
	 * @return
	 */
	public List<UploadFile> queryFileUploadListByBusinessType(Pager pager, String businessType){
		 Order order = Order.desc("uploadTime");
		 Property property = Property.eq("businessType", businessType);
		return getDao().findByPager(pager, order, property);
	}
	
	public List<UploadFile> queryFileUploadListByBusinessType(String businessType){
		 Order order = Order.desc("uploadTime");
		 Property property = Property.eq("businessType", businessType);
		return getDao().findAll(order, property);
	}
	
	/**
	 * 保存上传的文件到工程目录下的uploads，并保存对应的UploadFile对象
	 * @author linnt
	 * @date 2014年11月3日
	 * @param uploadFile
	 * @param fileInput
	 * @param webPath
	 */
	public void saveUploadFile(UploadFile uploadFile, File fileInput,
			String webPath) {
		String fileInputFileName = uploadFile.getFileName();
		Calendar curr = Calendar.getInstance();
		uploadFile.setUploadTime(new Date());		
		
		//获取当前时间
		String thisTime = com.hgsoft.util.DateUtil.format(new Date(), com.hgsoft.util.DateUtil.PATTERN_STRING_TIME_2);
		String extName = "";//扩展名  
	    String newFileName= "";//新文件名
	    
		if(fileInputFileName.lastIndexOf(".")>=0){  
		   extName = fileInputFileName.substring(fileInputFileName.lastIndexOf("."));  
		}  
		newFileName = fileInputFileName.substring(0,fileInputFileName.lastIndexOf("."))+"_"+thisTime+extName;
		uploadFile.setFileUrl(UploadFile.FILEBASEDIR + "/"+ curr.get(Calendar.YEAR) +"/"+ newFileName);

		//获取上传的类型
		//创建目录
		File fileTemp = new File(webPath+uploadFile.getFileUrl());
		if (!fileTemp.getParentFile().exists()){
			fileTemp.getParentFile().mkdirs();
		}
		fileInput.renameTo(fileTemp);
		getDao().save(uploadFile);
		
		ImportExcelUtils.pushNewUploadFileIntoQueue(uploadFile);
		//ImportExcelUtils.run(webPath);
	}
	
	public void importExcel(UploadFile uploadFile){
//		if (StrTool.isBlankStr(uploadFile.getFileUrl()) && StrTool.isBlankStr(uploadFile.getId())){
//			return;
//		}
//		if (StrTool.isBlankStr(uploadFile.getFileUrl())){
//			uploadFile = getDao().find(uploadFile.getId());
//		}
//		String fileUrl = ServletActionContext.getServletContext().getRealPath("");
//		File file = new File(fileUrl+uploadFile.getFileUrl());
//		if (!file.exists()){
//			return;
//		}
//		
//		
//		InputStream inputStream = null;
//		try {
//			inputStream = new FileInputStream(file);
//			System.out.println(new Date());
//			Workbook workbook = WorkbookFactory.create(inputStream);
//			int sheetNum = workbook.getNumberOfSheets();			
//			ExecutorService pool = Executors.newFixedThreadPool(sheetNum);
//			List<Runnable> list = new ArrayList<Runnable>(sheetNum);
//			
//			if (UploadFile.BUSINESS_TYPE_LUMIANPOSUN.equals(uploadFile.getBusinessType())){
//				for (int i = 0; i < sheetNum; i++) {
//					Sheet sheet = workbook.getSheetAt(i);
//					//list.add(new LumianposunExcelImportService(sheet, lumianposunService, uploadFileErrorService, uploadFile));
//					pool.execute(list.get(i));				
//				}
//			}
//			
//			pool.shutdown();			
//			while(true){
//				if (pool.isTerminated()){
////					String importInfo = "成功导入【"+LumianposunExcelImportService.insertCount+"】";
////					if (LumianposunExcelImportService.failCount>0){
////						importInfo += "导入过程中有【"+LumianposunExcelImportService.failCount+"】条记录不符合要求";
////					}
////					uploadFile.setImportInfo(importInfo);
////					//uploadFile.setHasImport(true);
////					getDao().update(uploadFile);
////					LumianposunExcelImportService.insertCount = 0;
////					LumianposunExcelImportService.failCount = 0;
//					break;
//				}
//			}
//			System.out.println(new Date());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			if(inputStream != null){
//				try {
//					inputStream.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
	}

	@Resource
	private AdminService adminService;
	
	@Resource
	private UploadFileErrorService uploadFileErrorService;

	/**
	 * 删除上传信息并删除其产生的错误信息 
	 * @author linnt
	 * @date 2015年1月31日
	 * @param uploadFile
	 */
	public void deleteWithUploadError(UploadFile uploadFile) {
		uploadFileErrorService.deleteByUploadFileId(uploadFile.getId());
		delete(uploadFile);
	}
}
