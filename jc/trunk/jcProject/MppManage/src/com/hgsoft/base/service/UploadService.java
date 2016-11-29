package com.hgsoft.base.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sun.misc.BASE64Decoder;

import com.hgsoft.base.dao.UploadDao;
import com.hgsoft.base.entity.Upload;
import com.hgsoft.cxf.common.PropertiesUtil;
import com.hgsoft.security.service.BaseService;
import com.hgsoft.util.DateUtil;
import com.hgsoft.util.FileUtil;
import com.hgsoft.util.MD5Coder;
import com.hgsoft.util.Property;
@Service
@Transactional
public class UploadService  extends BaseService<Upload>{
	private Logger logger = Logger.getLogger(UploadService.class);
	private Upload upload;
	
	public final static String path = PropertiesUtil.getProperty("baseDir") + "/" + PropertiesUtil.getProperty("uploadImgPath");

	@Resource
    public void setSDao(UploadDao dao) {
        this.setDao(dao);
    }
	
	/**
	 * 创建所需要的文件夹及文件
	 * @param path 路径
	 * @param uploadNewFileName 新文件名
	 * @param uploadFileType 业务类型
	 * @return
	 */
	public String creatMkdirResultStr(String path,String uploadNewFileName,String uploadFileType){
		
		String fileUrl = "";
		try {

			String thisTime = DateUtil.format(new Date(), com.hgsoft.util.DateUtil.PATTERN_STRING_DATE);
			String pathType = path + "/" + uploadFileType;
			String pathDate = pathType + "/" + thisTime;
			fileUrl = pathDate + "/" + uploadNewFileName;
			// 判断文件夹是否存在
			if (!new File(path).exists()) {
				new File(path).mkdir();
			}
			// 判断文件夹是否存在
			if (!new File(pathType).exists()) {
				new File(pathType).mkdir();
			}
			// 判断文件夹是否存在
			if (!new File(pathDate).exists()) {
				new File(pathDate).mkdir();
			}

			// 不存在则创建，存在则覆盖
			File f = new File(fileUrl);
			if (!f.exists()) {
				f.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileUrl;
		
	}
	/**
	 * 创建所需要的文件夹及文件
	 * @param path 路径
	 * @param uploadNewFileName 新文件名
	 * @param businessType 业务类型
	 * @return
	 */
	public File creatMkdir(String path, String uploadNewFileName, String businessType) {
		File f=null;
		String fileUrl = "";
		try {

			String thisTime = com.hgsoft.util.DateUtil.format(new Date(), com.hgsoft.util.DateUtil.PATTERN_STRING_DATE);
			String pathType = path + "/" + businessType;
			String pathDate = pathType + "/" + thisTime;
			fileUrl = pathDate + "/" + uploadNewFileName;
			// 判断文件夹是否存在
			if (!new File(path).exists()) {
				new File(path).mkdir();
			}
			// 判断文件夹是否存在
			if (!new File(pathType).exists()) {
				new File(pathType).mkdir();
			}
			// 判断文件夹是否存在
			if (!new File(pathDate).exists()) {
				new File(pathDate).mkdir();
			}

			// 不存在则创建，存在则覆盖
			f = new File(fileUrl);
			if (!f.exists()) {
				f.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f;

	}
	/**
	 * 自己定义的文件夹存储
	 * @param path 文件头路径
	 * @param uploadFile 文件
	 * @param uploadFileName 文件名
	 * @param pid 父id
	 * @param uploaderName 操作人姓名
	 * @param businessType 业务类型，用于创建独立文件夹
	 * @param uploadFileType 上传文件类型，用于分别不同类型文件 文件现限制的文件为（可扩充为任意文件类型）
			1.文档类型，格式：pdf,txt,doc,docx,xls,xlsx,ppt,pptx，对应tb_upload 中 uploadFileType='1'
			2.图片类型，格式：jpg,jpeg,png,gif,bmp，对应tb_upload 中 uploadFileType='2'
			3.视频类型，格式：3gp,avi,mp4,rmvb,mpg,mkv,asf,wmv，对应tb_upload 中 uploadFileType='=3'
			4.音频类型，格式：mp3,wav,aif,midi,wma,cda，对应tb_upload 中 uploadFileType='=4'
	 * @return
	 */
	public Boolean fixRouteFile(String path,File uploadFile,String uploadFileName,String pid,String uploaderName,String businessType,String uploadFileType){
		boolean b = up(path,uploadFile,uploadFileName,pid,uploaderName,businessType,uploadFileType);
		return b;
	}
	/**
	 * 给定的文件夹存储
	 * @param uploadFile 文件
	 * @param uploadFileName 文件名
	 * @param pid 父id
	 * @param uploaderName 操作人
	 * @param businessType 业务类型，用于创建独立文件夹
	 * @param uploadFileType 文件类型，用于分别不同类型文件 文件现限制的文件为（可扩充为任意文件类型）
			1.文档类型，格式：pdf,txt,doc,docx,xls,xlsx,ppt,pptx，对应tb_upload 中 uploadFileType='1'
			2.图片类型，格式：jpg,jpeg,png,gif,bmp，对应tb_upload 中 uploadFileType='2'
			3.视频类型，格式：3gp,avi,mp4,rmvb,mpg,mkv,asf,wmv，对应tb_upload 中 uploadFileType='=3'
			4.音频类型，格式：mp3,wav,aif,midi,wma,cda，对应tb_upload 中 uploadFileType='=4'
	 * @return
	 */
	public Boolean fixRouteFile(File uploadFile,String uploadFileName,String pid,String uploaderName,String businessType,String uploadFileType){
	
		boolean b = up(path,uploadFile,uploadFileName,pid,uploaderName,businessType,uploadFileType);
		return b;
	}
	
	/**
	 * 单个文件上传，并保存信息
	 * @param uploadFile  上传的文件
	 * @param uploadFileName 上传的文件名
	 * @param pid 父id
	 * @param operator  操作人 
	 * @param businessType  业务类型 ，用于创建独立文件夹
	 * @param uploadFileType 文件类型，用于区分不同类型文件 文件现限制的文件为（可扩充为任意文件类型）
			1.文档类型，格式：pdf,txt,doc,docx,xls,xlsx,ppt,pptx，对应tb_upload 中 uploadFileType='1'
			2.图片类型，格式：jpg,jpeg,png,gif,bmp，对应tb_upload 中 uploadFileType='2'
			3.视频类型，格式：3gp,avi,mp4,rmvb,mpg,mkv,asf,wmv，对应tb_upload 中 uploadFileType='=3'
			4.音频类型，格式：mp3,wav,aif,midi,wma,cda，对应tb_upload 中 uploadFileType='=4'
	 * @return  true  文件上传成功  false  文件上传失败
	 */
	public Boolean up(String path,File uploadFile,String uploadFileName,String pid,String uploaderName,String businessType,String uploadFileType) {
		String fileExtension = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
		String uploadNewFileName = UUID.randomUUID().toString()+"."+fileExtension;
		File f = creatMkdir(path, uploadNewFileName, businessType);
		FileOutputStream out=null;
		FileInputStream in=null;
		try {
			 in = new FileInputStream(uploadFile);
			 out = new FileOutputStream(f);
			int len=0;
			byte[] b =new byte[1024];
			while((len=in.read(b))!=-1){
				out.write(b,0,len);
			}
			out.flush();
			in.close();
			out.close();
		} catch (FileNotFoundException e) {
			logger.error("文件上传失败",e);
			return false;
		} catch (IOException e) {
			logger.error("文件上传失败",e);
			//File f = new File(fileUrl);
			if(f.exists()){
				f.delete();
			}
			return false;
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		 //保存文件信息到文件信息表中
		Boolean b = saveFile(f.getAbsolutePath(),uploadNewFileName,uploadFile.length(),pid,uploaderName,uploadFileType,"");
		return b;
	}
	/**
	 * 保存文件信息到数据库
	 * @param fileUrl 文件路径
	 * @param uploadNewFileName UUID转换后的文件名
	 * @param length   文件大小
	 * @param pid		父id
	 * @param uploaderName 上传姓名
	 * @param uploadFileType 文件类型，用于区分不同类型文件 文件现限制的文件为（可扩充为任意文件类型）
			1.文档类型，格式：pdf,txt,doc,docx,xls,xlsx,ppt,pptx，对应tb_upload 中 uploadFileType='1'
			2.图片类型，格式：jpg,jpeg,png,gif,bmp，对应tb_upload 中 uploadFileType='2'
			3.视频类型，格式：3gp,avi,mp4,rmvb,mpg,mkv,asf,wmv，对应tb_upload 中 uploadFileType='=3'
			4.音频类型，格式：mp3,wav,aif,midi,wma,cda，对应tb_upload 中 uploadFileType='=4'
	 * @param md5 文件是否上传验证
	 * @return
	 */
	public Boolean saveFile(String fileUrl,String uploadNewFileName,long length,String pid,String uploaderName,String uploadFileType,String md5){
		
		upload = new Upload();
		
		upload.setFileUrl(fileUrl);
		upload.setUploadFileName(uploadNewFileName);
		upload.setUploadFileSize(length);
		upload.setPid(pid);
		upload.setUploader(uploaderName);
		upload.setUploadFileType(uploadFileType);
		upload.setMd5(md5);
		upload.setUploadTime(new Date());
		save(upload);
		
		return true;
	}
	/**
	 * 文件集合同时上传,自己给定路径
	 * @param  path 头路径
	 * @param uploads  文件集合
	 * @param uploadFileNames  文件名集合
	 * @param businessType 业务类型,用于创建独立文件夹
	 * @param uploadFileType 文件类型，用于区分不同类型文件 文件现限制的文件为（可扩充为任意文件类型）
			1.文档类型，格式：pdf,txt,doc,docx,xls,xlsx,ppt,pptx，对应tb_upload 中 uploadFileType='1'
			2.图片类型，格式：jpg,jpeg,png,gif,bmp，对应tb_upload 中 uploadFileType='2'
			3.视频类型，格式：3gp,avi,mp4,rmvb,mpg,mkv,asf,wmv，对应tb_upload 中 uploadFileType='=3'
			4.音频类型，格式：mp3,wav,aif,midi,wma,cda，对应tb_upload 中 uploadFileType='=4'
	 * @return  false  文件上传失败    true  文件上传成功
	 */
	
	public Boolean uploadList(String path,List<File> uploads,List<String> uploadFileNames,String pid,String uploaderName,String businessType,String uploadFileType){
		
		boolean result; 
		for(int i=0;i<uploads.size();i++){
			
			result = up(path,uploads.get(i),uploadFileNames.get(i),pid,uploaderName,businessType,uploadFileType);
			//当其中的一个文件上传失败时，删除其中已经上传的文件
			if(!result){
				   
					
					return false;
				}
		
		}
		return true;
	}
	/**
	 * 文件集合同时上传,自己给定路径
	 * @param  path 头路径
	 * @param uploads  文件集合
	 * @param uploadFileNames  文件名集合
	 * @param businessType 业务类型,用于创建独立文件夹
	 * @param uploadFileType 文件类型，用于区分不同类型文件 文件现限制的文件为（可扩充为任意文件类型）
			1.文档类型，格式：pdf,txt,doc,docx,xls,xlsx,ppt,pptx，对应tb_upload 中 uploadFileType='1'
			2.图片类型，格式：jpg,jpeg,png,gif,bmp，对应tb_upload 中 uploadFileType='2'
			3.视频类型，格式：3gp,avi,mp4,rmvb,mpg,mkv,asf,wmv，对应tb_upload 中 uploadFileType='=3'
			4.音频类型，格式：mp3,wav,aif,midi,wma,cda，对应tb_upload 中 uploadFileType='=4'
	 * @return  false  文件上传失败    true  文件上传成功
	 */
	
	public Boolean uploadList(List<File> uploads,List<String> uploadFileNames,String pid,String uploaderName,String businessType,String uploadFileType){
		
		boolean result; 
		for(int i=0;i<uploads.size();i++){
			
			result = up(path,uploads.get(i),uploadFileNames.get(i),pid,uploaderName,businessType,uploadFileType);
			//当其中的一个文件上传失败时，删除其中已经上传的文件
			if(!result){
				   
					
					return false;
				}
		
		}
		return true;
	}
	/**
	 * 接受移动端传回的文件字节组
	 * @param path 头路径
	 * @param b   字节组
	 * @param pid  父id
	 * @param name  上传姓名
	 * @param fileExtension 文件后缀
	 * @param businessType 业务类型,用于创建独立文件夹
	 * @param uploadFileType 文件类型，用于区分不同类型   文件现限制的文件为（可扩充为任意文件类型）
			1.文档类型，格式：pdf,txt,doc,docx,xls,xlsx,ppt,pptx，对应tb_upload 中 uploadFileType='1'
			2.图片类型，格式：jpg,jpeg,png,gif,bmp，对应tb_upload 中 uploadFileType='2'
			3.视频类型，格式：3gp,avi,mp4,rmvb,mpg,mkv,asf,wmv，对应tb_upload 中 uploadFileType='=3'
			4.音频类型，格式：mp3,wav,aif,midi,wma,cda，对应tb_upload 中 uploadFileType='=4'
	 * @return
	 */
	public Boolean mobilUp(String path,byte[] b,String pid,String name,String fileExtension,String businessType,String uploadFileType){
		
		String uploadNewFileName = UUID.randomUUID().toString()+"."+fileExtension;
		File f = creatMkdir(path, uploadNewFileName, businessType);
		FileOutputStream out=null;
		try {
			out = new FileOutputStream(f);
			out.write(b,0,b.length);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			logger.error("文件上传失败",e);
			return false;
		} catch (IOException e) {
			logger.error("文件上传失败",e);
			
			if(f.exists()){
				f.delete();
			}
			return false;
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		//现场记录 -- 同时添加验收管理附件
		if(businessType.equals("monitor")){
			//AcceptService acceptService = ApplicationContextUtils.getApplicationContext().getBean(AcceptService.class);
			/*Accept accept = getAcceptByMid(pid);
    		if(accept!=null){
    			//保存文件信息
    			saveFile(f.getAbsolutePath(),uploadNewFileName,b.length,accept.getId(),name,uploadFileType,"");
    		}*/
		}
		
		 //保存文件信息到文件信息表中
		boolean bool = saveFile(f.getAbsolutePath(),uploadNewFileName,b.length,pid,name,uploadFileType,"");
		return bool;

	}
	/**
	 * 调用移动端单个文件上传方法,指定头路径
	 * @param path
	 * @param b
	 * @param pid
	 * @param name
	 *  @param fileExtension 文件后缀
	 * @param businessType 业务类型,用于创建独立文件夹
	 * @param uploadFileType 文件类型，用于区分不同类型文件 文件现限制的文件为（可扩充为任意文件类型）
			1.文档类型，格式：pdf,txt,doc,docx,xls,xlsx,ppt,pptx，对应tb_upload 中 uploadFileType='1'
			2.图片类型，格式：jpg,jpeg,png,gif,bmp，对应tb_upload 中 uploadFileType='2'
			3.视频类型，格式：3gp,avi,mp4,rmvb,mpg,mkv,asf,wmv，对应tb_upload 中 uploadFileType='=3'
			4.音频类型，格式：mp3,wav,aif,midi,wma,cda，对应tb_upload 中 uploadFileType='=4'
	 * @return
	 */
	public Boolean mobilUpload(String path,byte[] b,String pid,String name,String fileExtension,String businessType,String uploadFileType){
		boolean bol = mobilUp(path, b, pid, name, fileExtension,businessType,uploadFileType);
		return bol;
		
	}
	
	/**
	 * 调用移动端单个文件上传方法，给定头路径
	 * @param path
	 * @param b
	 * @param pid
	 * @param name
	   @param fileExtension 文件后缀
	 * @param businessType 业务类型,用于创建独立文件夹
	 * @param uploadFileType 文件类型，用于区分不同类型文件 文件现限制的文件为（可扩充为任意文件类型）
			1.文档类型，格式：pdf,txt,doc,docx,xls,xlsx,ppt,pptx，对应tb_upload 中 uploadFileType='1'
			2.图片类型，格式：jpg,jpeg,png,gif,bmp，对应tb_upload 中 uploadFileType='2'
			3.视频类型，格式：3gp,avi,mp4,rmvb,mpg,mkv,asf,wmv，对应tb_upload 中 uploadFileType='=3'
			4.音频类型，格式：mp3,wav,aif,midi,wma,cda，对应tb_upload 中 uploadFileType='=4'
	 * @return
	 */
	public Boolean mobilUpload(byte[] b,String pid,String name,String fileExtension,String businessType,String uploadFileType){
		
		boolean bol = mobilUp(path, b, pid, name, fileExtension,businessType,uploadFileType);
		return bol;
		
	}
	/**
	 * 移动端多个不同类型文件上传
	 * @param path
	 * @param byteList
	 * @param pid
	 * @param name
	 * @param fileExtension 文件后缀集合
	 * @param businessType 业务类型,用于创建独立文件夹
	 * @param uploadFileType 文件类型，用于区分不同类型文件 文件现限制的文件为（可扩充为任意文件类型）
			1.文档类型，格式：pdf,txt,doc,docx,xls,xlsx,ppt,pptx，对应tb_upload 中 uploadFileType='1'
			2.图片类型，格式：jpg,jpeg,png,gif,bmp，对应tb_upload 中 uploadFileType='2'
			3.视频类型，格式：3gp,avi,mp4,rmvb,mpg,mkv,asf,wmv，对应tb_upload 中 uploadFileType='=3'
			4.音频类型，格式：mp3,wav,aif,midi,wma,cda，对应tb_upload 中 uploadFileType='=4'
	 * @return
	 */
	
	public Boolean mobilUploadList(String path,List<byte[]> byteList,String pid,String name,List<String> fileExtension,String businessType,String uploadFileType){
		boolean b;
		for(int i=0;i<byteList.size();i++){
			b = mobilUp(path, byteList.get(i), pid, name, fileExtension.get(i),businessType,uploadFileType);
			if(!b){
				return false;
			}
		}
		return true;
	}
	/**
	 * 移动端多个单一类型文件上传
	 * @param path
	 * @param byteList 文件集合
	 * @param pid 父id
	 * @param name 姓名
	  * @param businessType 业务类型,用于创建独立文件夹
	 * @param uploadFileType 文件类型，用于区分不同类型文件 文件现限制的文件为（可扩充为任意文件类型）
			1.文档类型，格式：pdf,txt,doc,docx,xls,xlsx,ppt,pptx，对应tb_upload 中 uploadFileType='1'
			2.图片类型，格式：jpg,jpeg,png,gif,bmp，对应tb_upload 中 uploadFileType='2'
			3.视频类型，格式：3gp,avi,mp4,rmvb,mpg,mkv,asf,wmv，对应tb_upload 中 uploadFileType='=3'
			4.音频类型，格式：mp3,wav,aif,midi,wma,cda，对应tb_upload 中 uploadFileType='=4'
	 * @return
	 */
	public Boolean mobilUploadList(String path,List<byte[]> byteList,String pid,String name,String fileExtension,String businessType,String uploadFileType){
		boolean b;
		for(int i=0;i<byteList.size();i++){
			b = mobilUp(path, byteList.get(i), pid, name, fileExtension,businessType,uploadFileType);
			if(!b){
				return false;
			}
		}
		return true;
	}
	/**
	 * 移动端多个不同类型文件上传，给定头文件夹
	 * @param path
	 * @param byteList 文件集合
	 * @param pid 父id
	 * @param name 姓名
	 * @param fileExtension 后缀集合
	  * @param businessType 业务类型,用于创建独立文件夹
	 * @param uploadFileType 文件类型，用于区分不同类型文件 文件现限制的文件为（可扩充为任意文件类型）
			1.文档类型，格式：pdf,txt,doc,docx,xls,xlsx,ppt,pptx，对应tb_upload 中 uploadFileType='1'
			2.图片类型，格式：jpg,jpeg,png,gif,bmp，对应tb_upload 中 uploadFileType='2'
			3.视频类型，格式：3gp,avi,mp4,rmvb,mpg,mkv,asf,wmv，对应tb_upload 中 uploadFileType='=3'
			4.音频类型，格式：mp3,wav,aif,midi,wma,cda，对应tb_upload 中 uploadFileType='=4'
	 * @return
	 */
	
	public Boolean mobilUploadList(List<byte[]> byteList,String pid,String name,List<String> fileExtension,String businessType,String uploadFileType){
		
		boolean b;
		for(int i=0;i<byteList.size();i++){
			b = mobilUp(path, byteList.get(i), pid, name, fileExtension.get(i),businessType,uploadFileType);
			if(!b){
				return false;
			}
		}
		return true;
	}
	/**
	 * 移动端多个单一类型文件上传,给定头文件夹
	 * @param path
	 * @param byteList
	 * @param pid
	 * @param name
	 * * @param fileExtension 后缀名
	 * @param businessType 业务类型,用于创建独立文件夹
	 * @param uploadFileType 文件类型，用于区分不同类型文件 文件现限制的文件为（可扩充为任意文件类型）
			1.文档类型，格式：pdf,txt,doc,docx,xls,xlsx,ppt,pptx，对应tb_upload 中 uploadFileType='1'
			2.图片类型，格式：jpg,jpeg,png,gif,bmp，对应tb_upload 中 uploadFileType='2'
			3.视频类型，格式：3gp,avi,mp4,rmvb,mpg,mkv,asf,wmv，对应tb_upload 中 uploadFileType='=3'
			4.音频类型，格式：mp3,wav,aif,midi,wma,cda，对应tb_upload 中 uploadFileType='=4'
	 * @return
	 */
	public Boolean mobilUploadList(List<byte[]> byteList,String pid,String name,String fileExtension,String businessType,String uploadFileType){
		
		boolean b;
		for(int i=0;i<byteList.size();i++){
			b = mobilUp(path, byteList.get(i), pid, name,fileExtension, businessType,uploadFileType);
			if(!b){
				return false;
			}
		}
		return true;
	}

	private InputStream inputStream;
	/**
	 * 返回输出流
	 * @param url 文件路径
	 * @return
	 * @throws FileNotFoundException
	 */
	public InputStream getInputStream(String url) throws FileNotFoundException{
		inputStream = new FileInputStream(url);
		if(inputStream == null){
			new ByteArrayInputStream(
					"Sorry,File not found !".getBytes());
		}
		return inputStream;
	}
	
	/**
	 * 根据父id查找文件
	 * @param pager
	 * @param pid
	 * @return
	 */
	public List<Upload> listFile(String pid){
		
		 Property property = Property.eq("pid", pid);
		 List<Upload> uploadList = getDao().findAll(property);
		 return uploadList;
		
	}
	/**
	 * 根据父id返回文件路径集合
	 * @param pid
	 * @return
	 */
	public List<String> listFileUrl(String pid){
		 Property property = Property.eq("pid", pid);
		 List<Upload> uploadList = getDao().findAll(property);
		 List<String> fileUrlList = new ArrayList<String>();
		 for(Upload upload:uploadList){
			 fileUrlList.add(upload.getFileUrl());
		 }
		 return fileUrlList;
	}
	

	 /**
	  * 根据父id删除所有文件信息以及上传的文件
	  * @param pid 父id
	  * @return
	  */
	 public Boolean delFile(String pid){
		 List<Upload> uploads = listFile(pid);
		 for(int i=0;i<uploads.size();i++){
			 File f = new File(uploads.get(i).getFileUrl());
			 if(f.exists()){
				 f.delete();
			 }		
			 getDao().delete(uploads.get(i));
		 }
		 return true;
	 }
	 /**
	  * 根据父id集合删除所有文件信息以及上传的文件
	  */
	 public Boolean delFiles(List<String> pid){
		 for(int i=0;i<pid.size();i++){
			 if(!delFile(pid.get(i))){
				 return false;
			 }
		 }
		 
		 return true;
	 }
	 
	 /**
	  * 根据md5值查询文件信息
	  * 
	  * @param pid
	  * @param md5
	  * @return
	  */
	 public Upload getUploadByPidAndMd5(String pid,String md5) {
		 List<Upload> uploads = getDao().findAll(Property.eq("pid",pid),Property.eq("md5", md5));
		 if(null == uploads || uploads.size() < 1) {
			 return null;
		 }else {
			 return uploads.get(0);
		 }
	 }
	 
	 /**
	  * 根据pid查询所有的文件信息
	  * 
	  * @param pid
	  * @return
	  */
	 public List<Upload> getUploadListByPid(String pid) {
		 
		 return getDao().findAll(Property.eq("pid",pid));		 
	 }
	 
	 /**
	  * 根据pid查询所有的文件信息
	  * 
	  * @param pid
	  * @return
	  */
	 public Upload getUploadByPid(String pid) {
		 List<Upload> list = getDao().findAll(Property.eq("pid",pid));
		 if(list.size()>0){
			 return list.get(0);
		 }
		 return null;		 
	 }
	 
	/**
	 * 对字节数组字符串进行Base64解码并生成图片
	 * 
	 * @param imgStr
	 * @param photoFile
	 * @throws IOException
	 */
	public static void generateImage(String imgStr, File photoFile) throws IOException {
		if (imgStr == null)
			return ; // 图像数据为空
		BASE64Decoder decoder = new BASE64Decoder();		
		byte[] bytes = decoder.decodeBuffer(imgStr);// Base64解码
		for (int i = 0; i < bytes.length; i++) {
			if (bytes[i] < 0) {// 调整异常数据
				bytes[i] += 256;
			}
		}
		// 生成jpeg图片
		OutputStream out = new FileOutputStream(photoFile);
		out.write(bytes);
		out.flush();
		out.close();
	}
	
	/**
	 * 保存移动端上传的视频
	 * @param videoFile
	 * @param videoFileName
	 * @param uploadBizType
	 * @param pid
	 * @param videoCommitter
	 * @return
	 */
	public void saveVideo(File videoFile,String videoFileName,String uploadBizType,String pid,String videoCommitter) throws Exception {
		String uploadNewFileName = UUID.randomUUID().toString() + ".3gp";
		File targetFile = creatMkdir(path, uploadNewFileName, uploadBizType);
		FileUtil.fileCopy(videoFile,targetFile);
		String md5 = MD5Coder.getHashNio(targetFile,"MD5");
		//现场记录 -- 同步添加验收管理
		if(uploadBizType.equals("monitor")){
			/*Accept accept = getAcceptByMid(pid);
    		if(accept!=null){
    			//保存文件信息
    			saveFile(targetFile.getAbsolutePath(), targetFile.getName(), targetFile.length(), accept.getId(), videoCommitter, "3",md5);
    		}*/
		}
		saveFile(targetFile.getAbsolutePath(), targetFile.getName(), targetFile.length(), pid, videoCommitter, "3",md5);		
	}
	
	//不能引入
	/*@SuppressWarnings("unchecked")
	public Accept getAcceptByMid(String mid){
		List<Accept> list = getDao().queryByHQL(" from Accept a where a.mid = ?", mid);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}*/
}
