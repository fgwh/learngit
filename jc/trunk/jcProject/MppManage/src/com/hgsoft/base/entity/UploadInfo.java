package com.hgsoft.base.entity;
/***
 * 导入进行中的记录器
 * @author linnt
 * @date 2015年1月28日
 */
public class UploadInfo {
	/***
	 * 一共多少条记录
	 */
	public int allCount=0;
	/***
	 * 当前已处理多少条记录
	 */
	public int currCount=0;
	/**
	 * 插入失败多少条
	 */
	public int failCount=0;
	/**
	 * 成功插入多少条
	 */
	public int succCount=0;
	public int getAllCount() {
		return allCount;
	}
	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}
	public int getCurrCount() {
		return currCount;
	}
	public void setCurrCount(int currCount) {
		this.currCount = currCount;
	}
	public int getFailCount() {
		return failCount;
	}
	public void setFailCount(int failCount) {
		this.failCount = failCount;
	}
	public int getSuccCount() {
		return succCount;
	}
	public void setSuccCount(int succCount) {
		this.succCount = succCount;
	}
	
}
