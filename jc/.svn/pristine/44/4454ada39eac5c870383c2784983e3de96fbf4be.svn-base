package com.hgsoft.base.util;

import com.hgsoft.base.entity.UploadFile;
import com.hgsoft.base.entity.UploadInfo;
import com.hgsoft.base.interfaces.ImportExcelFileInterface;
import com.hgsoft.base.service.UploadFileService;
import com.hgsoft.main.entity.DicItem;
import com.hgsoft.main.service.DictionaryService;
import com.hgsoft.util.DateUtil;
import com.hgsoft.util.SpringInit;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ImportExcelUtils {
	private static Queue<UploadFile> queue = new LinkedList<UploadFile>();
	private static boolean runStatus = false;//当还在进行导入工作时为true，否则为false
	private static Map<String, UploadInfo> importInfoMap = new HashMap<String, UploadInfo>();//用来记录导入过程中的信息
	
	/**
	 * 从queue中poll一个文件对象出来进行导入处理
	 * @author linnt
	 * @param realUrl 
	 * @date 2015年1月28日
	 */
	public static void run(String realUrl){
		//System.out.println("导入服务类的运行状态为："+runStatus);
		if (runStatus){
			return;
		}
		UploadFile uploadFile = queue.poll();
		if (uploadFile!=null ){
			Date beginTime = new Date();
			InputStream inputStream = null;
			try {
				//处理过程
				File file = new File(realUrl+uploadFile.getFileUrl());
				if (!file.exists()){
					return;
				}
				runStatus = true;
				inputStream = new FileInputStream(file);
				
				System.out.println(DateUtil.format(beginTime, DateUtil.PATTERN_STRING_TIME));
				Workbook workbook = WorkbookFactory.create(inputStream);
				int sheetNum = workbook.getNumberOfSheets();			
				
				importInfoMap.put(uploadFile.getId(), new UploadInfo());
				for (int i = 0; i < sheetNum; i++) {
					Sheet sheet = workbook.getSheetAt(i);
					importInfoMap.get(uploadFile.getId()).allCount+=sheet.getLastRowNum()-1;
				}
				
				UploadFileService uploadFileService = (UploadFileService)SpringInit
						.getApplicationContext().getBean(UploadFileService.class);
				
				DicItem dicItem = getDicService().getItem(UploadFile.UPLOADFILE_DICITEM_TYPE, uploadFile.getBusinessType());
				if (dicItem==null){
					uploadFile.setImportInfo("没找到该业务类型的字典项，无法启动导入的相关环节");
					uploadFile.setHasImport(true);
					uploadFileService.update(uploadFile);
					return;
				}
				
				ImportExcelFileInterface importInterface = (ImportExcelFileInterface) SpringInit.getApplicationContext().getBean(dicItem.getInnerType());
				for (int i = 0; i < sheetNum; i++) {
					Sheet sheet = workbook.getSheetAt(i);
					importInterface.setSheet(sheet);
					importInterface.setUploadFile(uploadFile);
					importInterface.importExcel();
				}
				
				String importInfo = "成功导入【"+ImportExcelUtils.getImportInfoMap().get(uploadFile.getId()).succCount+"】";
				if (ImportExcelUtils.getImportInfoMap().get(uploadFile.getId()).failCount>0){
					importInfo += "导入过程中有【"+ImportExcelUtils.getImportInfoMap().get(uploadFile.getId()).failCount+"】条记录不符合要求";
				}
				uploadFile.setImportInfo(importInfo);
				uploadFile.setHasImport(true);
				uploadFileService.update(uploadFile);
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(inputStream != null){
					try {
						inputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				runStatus = false;
				importInfoMap.remove(uploadFile.getId());
				System.out.println("耗时："+(new Date().getTime()-beginTime.getTime()));
			}
		}
	}
	
	/**
	 * 把一个上传记录推送到处理队列中
	 * @author linnt
	 * @date 2015年1月31日
	 * @param uploadFile
	 */
	public static void pushNewUploadFileIntoQueue(UploadFile uploadFile){
		//新增处理中的map
		ImportExcelUtils.getImportInfoMap().put(uploadFile.getId(), new UploadInfo());
		//把上传的文件推进处理队列中
		ImportExcelUtils.getQueue().offer(uploadFile);
	}
	
	public static Queue<UploadFile> getQueue() {
		return queue;
	}

	public static void setQueue(Queue<UploadFile> queue) {
		ImportExcelUtils.queue = queue;
	}

	public static Map<String, UploadInfo> getImportInfoMap() {
		return importInfoMap;
	}

	public static void setImportInfoMap(Map<String, UploadInfo> importInfoMap) {
		ImportExcelUtils.importInfoMap = importInfoMap;
	}
	
	private static DictionaryService dicService;
	
	public static DictionaryService getDicService() {
		if (dicService == null){
			dicService = SpringInit.getApplicationContext().getBean(DictionaryService.class);
		}
		return dicService;
	}
}
