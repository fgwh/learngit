package com.hgsoft.util;

public class BusinessException extends RuntimeException {

	public BusinessException() {
		super();
	}

	/**
	 * @param msg
	 */
	public BusinessException(String msg) {
		super(msg);
	}

	public String getResult() {
		return "error";
	}

	private String link;

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * 示例代码：
	 * 
	 *            throw new BusinessException("请登记合同后再使用本功能模块。",
	 *            processURL("contract.action"));
	 * @param msg
	 * @param link
	 */
	public BusinessException(String msg, String link) {
		super(msg);
		setLink(link);
	}
}
