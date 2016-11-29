package com.hgsoft.main.entity;

/**
 * LaneType entity. @author MyEclipse Persistence Tools
 */

public class LaneType implements java.io.Serializable {

	// Fields

	private Integer laneTypeNo;
	private String laneTypeName;

	// Constructors

	/** default constructor */
	public LaneType() {
	}

	/** full constructor */
	public LaneType(String laneTypeName) {
		this.laneTypeName = laneTypeName;
	}

	// Property accessors

	public Integer getLaneTypeNo() {
		return this.laneTypeNo;
	}

	public void setLaneTypeNo(Integer laneTypeNo) {
		this.laneTypeNo = laneTypeNo;
	}

	public String getLaneTypeName() {
		return this.laneTypeName;
	}

	public void setLaneTypeName(String laneTypeName) {
		this.laneTypeName = laneTypeName;
	}

}