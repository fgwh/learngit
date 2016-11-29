package com.hgsoft.base.entity;

import com.hgsoft.util.StrTool;

/**
 * 导入错误的记录信息
 * @author linniantai
 *
 */
public class UploadFileError implements java.io.Serializable {
	private String id;
	private UploadFile uploadFile;
	private String colsInfo;
	private String errorInfo;
	private String errorInfoStr;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public UploadFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(UploadFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getColsInfo() {
		return colsInfo;
	}
	public void setColsInfo(String colsInfo) {
		this.colsInfo = colsInfo;
	}
	public String getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	
	public String getErrorInfoStr() {
		if (StrTool.isBlankStr(colsInfo)){
			return "";
		} else {
			String [] cols = colsInfo.split("#");
			StringBuffer sb = new StringBuffer("");
			for (int i = 0; i < cols.length; i++) {
				sb.append(cols[i]+" ");
			}
			return sb.toString();
		}
	}
	public void setErrorInfoStr(String errorInfoStr) {
		this.errorInfoStr = errorInfoStr;
	}
}
