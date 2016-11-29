package com.hgsoft.main.integrity.entity;

import java.util.Date;

public class DriverIntegrity implements java.io.Serializable{
	private String id;
	private String driverName;//驾驶员姓名
	private boolean driverSex;//性别
	private String driverNationality;//国籍
	private String driverAddress;//地址
	private Date driverBirthday;//生日
	private String driverNo;//身份证号
	private Integer fakeDriverNum;//假冒驾驶证次数
	private Integer normalNum;//正常次数
	private Integer noNormalNum;//非正常次数
	private float driverScore;//诚信分数
	private Integer fakeGreenNum;//假冒绿通次数
	private Date reportTime;//更新日期
	private String grade;//等级
	
	public DriverIntegrity(){}
	public DriverIntegrity(String id, String driverName, boolean driverSex, String driverNationality,
			String driverAddress, Date driverBirthday, String driverNo, Integer fakeDriverNum, Integer normalNum,
			Integer noNormalNum, Integer fakeGreenNum,float driverScore,  Date reportTime) {
		this.id = id;
		this.driverName = driverName;
		this.driverSex = driverSex;
		this.driverNationality = driverNationality;
		this.driverAddress = driverAddress;
		this.driverBirthday = driverBirthday;
		this.driverNo = driverNo;
		this.fakeDriverNum = fakeDriverNum;
		this.normalNum = normalNum;
		this.noNormalNum = noNormalNum;
		this.driverScore = driverScore;
		this.fakeGreenNum = fakeGreenNum;
		this.reportTime = reportTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public boolean isDriverSex() {
		return driverSex;
	}
	public void setDriverSex(boolean driverSex) {
		this.driverSex = driverSex;
	}
	public String getDriverNationality() {
		return driverNationality;
	}
	public void setDriverNationality(String driverNationality) {
		this.driverNationality = driverNationality;
	}
	public String getDriverAddress() {
		return driverAddress;
	}
	public void setDriverAddress(String driverAddress) {
		this.driverAddress = driverAddress;
	}
	public Date getDriverBirthday() {
		return driverBirthday;
	}
	public void setDriverBirthday(Date driverBirthday) {
		this.driverBirthday = driverBirthday;
	}
	public String getDriverNo() {
		return driverNo;
	}
	public void setDriverNo(String driverNo) {
		this.driverNo = driverNo;
	}
	public Integer getFakeDriverNum() {
		return fakeDriverNum;
	}
	public void setFakeDriverNum(Integer fakeDriverNum) {
		this.fakeDriverNum = fakeDriverNum;
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
	public float getDriverScore() {
		return driverScore;
	}
	public void setDriverScore(float driverScore) {
		this.driverScore = driverScore;
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
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	

}
