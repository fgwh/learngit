package com.hgsoft.main.entity;

import java.util.Date;

/**
 * QrtzLog entity. @author MyEclipse Persistence Tools
 */
public class QrtzLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private String jobName;
	private String jobGroup;
	private String description;
	private String jobClassName;
	private Date createtime;
	private Short jobStatus;//0：失败，1：成功

	// Constructors

	/** default constructor */
	public QrtzLog() {
	}

	/** full constructor */
	public QrtzLog(String jobName, String jobGroup, String description,
			String jobClassName, Date createtime, Short jobStatus) {
		this.jobName = jobName;
		this.jobGroup = jobGroup;
		this.description = description;
		this.jobClassName = jobClassName;
		this.createtime = createtime;
		this.jobStatus = jobStatus;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJobName() {
		return this.jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return this.jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJobClassName() {
		return this.jobClassName;
	}

	public void setJobClassName(String jobClassName) {
		this.jobClassName = jobClassName;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Short getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(Short jobStatus) {
		this.jobStatus = jobStatus;
	}

}
