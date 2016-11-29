package com.hgsoft.main.integrity.entity;

import java.util.Date;

public class OperationIntegrity implements java.io.Serializable{
	private String id;
	private String operationName;//企业名称
	private String operationAddress;//企业地址
	private String operationRange;//经营范围
	private Integer fakeOperationNum;//假冒运营证次数
	private Integer normalNum;//正常次数
	private Integer noNormalNum;//不正常次数
	private float operationScore;//诚信分数
	private Integer fakeGreenNum;//假冒绿通次数
	private Date reportTime;//更新日期
	private String operationNo;//运营证编号
	private String grade;//等级
	
	
	public OperationIntegrity(){}
	public OperationIntegrity(String id, String operationName, String operationAddress, String operationRange,String operationNo,
			Integer fakeOperationNum, Integer normalNum, Integer noNormalNum, Integer fakeGreenNum,float operationScore,
			 Date reportTime) {
		this.id = id;
		this.operationName = operationName;
		this.operationAddress = operationAddress;
		this.operationRange = operationRange;
		this.fakeOperationNum = fakeOperationNum;
		this.normalNum = normalNum;
		this.noNormalNum = noNormalNum;
		this.operationScore = operationScore;
		this.fakeGreenNum = fakeGreenNum;
		this.reportTime = reportTime;
		this.operationNo=operationNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	public String getOperationAddress() {
		return operationAddress;
	}
	public void setOperationAddress(String operationAddress) {
		this.operationAddress = operationAddress;
	}
	public String getOperationRange() {
		return operationRange;
	}
	public void setOperationRange(String operationRange) {
		this.operationRange = operationRange;
	}
	public Integer getFakeOperationNum() {
		return fakeOperationNum;
	}
	public void setFakeOperationNum(Integer fakeOperationNum) {
		this.fakeOperationNum = fakeOperationNum;
	}
	public Integer getNormalNum() {
		return normalNum;
	}
	public void setNormalNum(Integer normalNum) {
		this.normalNum = normalNum;
	}
	public Integer getNoNormalNum() {
		return noNormalNum;
	}
	public void setNoNormalNum(Integer noNormalNum) {
		this.noNormalNum = noNormalNum;
	}
	public float getOperationScore() {
		return operationScore;
	}
	public void setOperationScore(float operationScore) {
		this.operationScore = operationScore;
	}
	public Integer getFakeGreenNum() {
		return fakeGreenNum;
	}
	public void setFakeGreenNum(Integer fakeGreenNum) {
		this.fakeGreenNum = fakeGreenNum;
	}
	public Date getReportTime() {
		return reportTime;
	}
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}
	public String getOperationNo() {
		return operationNo;
	}
	public void setOperationNo(String operationNo) {
		this.operationNo = operationNo;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	

}
