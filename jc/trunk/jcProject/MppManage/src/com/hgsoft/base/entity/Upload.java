package com.hgsoft.base.entity;

import java.util.Date;

import com.hgsoft.security.entity.BaseEntity;

public class Upload implements BaseEntity{
	private String id;
	private String uploadFileName;//附件名
	private String uploadFileType;//附件类型
	private String pid;//父id
	private Date uploadTime;//上传时间
	private Long uploadFileSize;//附件大小
	private String uploader;//上传人
	private String fileUrl;//保存路径
	private String md5;
	public Upload(){}
	public Upload(String id,String uploadFileName,String uploadFileType,String pid,Date uploadTime,Long uploadFileSize,String uploader,String fileUrl,String md5){
		this.id=id;
		this.uploadFileName=uploadFileName;
		this.uploadFileType=uploadFileType;
		this.pid=pid;
		this.uploadTime=uploadTime;
		this.uploader=uploader;
		this.uploadFileSize=uploadFileSize;
		this.fileUrl=fileUrl;
		this.md5=md5;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getUploadFileType() {
		return uploadFileType;
	}
	public void setUploadFileType(String uploadFileType) {
		this.uploadFileType = uploadFileType;
	}
	public Long getUploadFileSize() {
		return uploadFileSize;
	}
	public void setUploadFileSize(Long uploadFileSize) {
		this.uploadFileSize = uploadFileSize;
	}
	public String getUploader() {
		return uploader;
	}
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	

}
