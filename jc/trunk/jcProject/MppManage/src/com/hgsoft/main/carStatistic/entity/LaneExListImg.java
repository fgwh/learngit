package com.hgsoft.main.carStatistic.entity;

import java.util.Date;

import com.hgsoft.security.entity.BaseEntity;

public class LaneExListImg implements BaseEntity {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private Integer exListImgeType;
	private String exListImageName;
	private Long exListImageSize;
	private String exListFileType;
	private Date exListImageDate;
	private String pid;
	private String squadDate;
	
	private String etcId;

	
	public LaneExListImg() {
		super();
	}
	public LaneExListImg(String id, Integer exListImgeType, String exListImageName, Long exListImageSize,
			String exListFileType, Date exListImageDate, String pid, String squadDate) {
		super();
		this.id = id;
		this.exListImgeType = exListImgeType;
		this.exListImageName = exListImageName;
		this.exListImageSize = exListImageSize;
		this.exListFileType = exListFileType;
		this.exListImageDate = exListImageDate;
		this.pid = pid;
		this.squadDate = squadDate; 
	}
	
	/********************* set/get 方法 ****************************/
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getExListImgeType() {
		return exListImgeType;
	}
	public void setExListImgeType(Integer exListImgeType) {
		this.exListImgeType = exListImgeType;
	}
	public String getExListImageName() {
		return exListImageName;
	}
	public void setExListImageName(String exListImageName) {
		this.exListImageName = exListImageName;
	}
	public Long getExListImageSize() {
		return exListImageSize;
	}
	public void setExListImageSize(Long exListImageSize) {
		this.exListImageSize = exListImageSize;
	}
	public String getExListFileType() {
		return exListFileType;
	}
	public void setExListFileType(String exListFileType) {
		this.exListFileType = exListFileType;
	}
	public Date getExListImageDate() {
		return exListImageDate;
	}
	public void setExListImageDate(Date exListImageDate) {
		this.exListImageDate = exListImageDate;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getSquadDate() {
		return squadDate;
	}
	public void setSquadDate(String squadDate) {
		this.squadDate = squadDate;
	}
	public String getEtcId() {
		return etcId;
	}
	public void setEtcId(String etcId) {
		this.etcId = etcId;
	}
	
	
}