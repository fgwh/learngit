package com.hgsoft.base.entity;

import java.util.Date;

/***
 * 上传文件供导入用的实体
 * @author linnt
 * @date 2014年11月1日
 */
public class UploadFile implements java.io.Serializable {
	private static final long serialVersionUID = 5660577442750399161L;
	
	public static final String UPLOADFILE_DICITEM_TYPE="uploadFileType";
	public static final String FILEBASEDIR = "/base_upload";
	/**id*/
	private String id;
	/** 文件名 */
	private String fileName;
	/** 业务类型 */
	private String businessType;
	/** 文件大小 */
	private Long fileSize;
	/** 上传时间 */
	private Date uploadTime;
	/**  上传人*/
	private String uploader;
	/** 是否已导入 */
	private boolean hasImport;
	/** 导入过程信息 */
	private String importInfo;
	
	private String fileUrl;
	
	public UploadFile(){
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getUploader() {
		return uploader;
	}
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	
	public String getImportInfo() {
		return importInfo;
	}
	public void setImportInfo(String importInfo) {
		this.importInfo = importInfo;
	}

	public boolean isHasImport() {
		return hasImport;
	}

	public void setHasImport(boolean hasImport) {
		this.hasImport = hasImport;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	
}
