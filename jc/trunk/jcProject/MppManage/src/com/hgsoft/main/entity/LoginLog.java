package com.hgsoft.main.entity;

import java.util.Date;

import com.hgsoft.security.entity.BaseEntity;

/**
 * TbLoginLog entity. @author MyEclipse Persistence Tools
 */
public class LoginLog implements BaseEntity {

	// Fields

	private String id;
	private String userName;
	private Date loginTime;
	private String loginIp;
	private Integer loginResult;
	private String sessionId;

	//查询条件
	private String stationNo;
	private String roadNo; 
	private String startQueryDate;//开始时间
	private String endQueryDate;//结束时间
	
	// Constructors

	/** default constructor */
	public LoginLog() {
	}

	/** minimal constructor */
	public LoginLog(String userName, Date loginTime, String loginIp,
			Integer loginResult) {
		this.userName = userName;
		this.loginTime = loginTime;
		this.loginIp = loginIp;
		this.loginResult = loginResult;
	}

	/** full constructor */
	public LoginLog(String userName, Date loginTime, String loginIp,
			Integer loginResult, String sessionId) {
		this.userName = userName;
		this.loginTime = loginTime;
		this.loginIp = loginIp;
		this.loginResult = loginResult;
		this.sessionId = sessionId;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Integer getLoginResult() {
		return this.loginResult;
	}

	public void setLoginResult(Integer loginResult) {
		this.loginResult = loginResult;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getStationNo() {
		return stationNo;
	}

	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}

	public String getRoadNo() {
		return roadNo;
	}

	public void setRoadNo(String roadNo) {
		this.roadNo = roadNo;
	}

	public String getStartQueryDate() {
		return startQueryDate;
	}

	public void setStartQueryDate(String startQueryDate) {
		this.startQueryDate = startQueryDate;
	}

	public String getEndQueryDate() {
		return endQueryDate;
	}

	public void setEndQueryDate(String endQueryDate) {
		this.endQueryDate = endQueryDate;
	}
}
