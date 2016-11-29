package com.hgsoft.main.im.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Group entity. @author MyEclipse Persistence Tools
 */

public class Group implements java.io.Serializable {

	// Fields

	private Integer groupId;
	private String groupName;
	private Date createTime;
	private Integer createId;
	private Set groupMembers = new HashSet(0);
	private Set groupMsgs = new HashSet(0);

	// Constructors

	/** default constructor */
	public Group() {
	}

	/** full constructor */
	public Group(String groupName, Date createTime, Integer createId,
			Set groupMembers, Set groupMsgs) {
		this.groupName = groupName;
		this.createTime = createTime;
		this.createId = createId;
		this.groupMembers = groupMembers;
		this.groupMsgs = groupMsgs;
	}

	// Property accessors

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateId() {
		return this.createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

	public Set getGroupMembers() {
		return this.groupMembers;
	}

	public void setGroupMembers(Set groupMembers) {
		this.groupMembers = groupMembers;
	}

	public Set getGroupMsgs() {
		return this.groupMsgs;
	}

	public void setGroupMsgs(Set groupMsgs) {
		this.groupMsgs = groupMsgs;
	}

}