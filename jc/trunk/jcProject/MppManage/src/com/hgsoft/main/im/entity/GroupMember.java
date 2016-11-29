package com.hgsoft.main.im.entity;

/**
 * GroupMember entity. @author MyEclipse Persistence Tools
 */

public class GroupMember implements java.io.Serializable {

	// Fields

	private Integer memberId;
	private Group group;
	private Integer userId;
	private String name;

	// Constructors

	/** default constructor */
	public GroupMember() {
	}

	/** full constructor */
	public GroupMember(Group group, Integer userId, String name) {
		this.group = group;
		this.userId = userId;
		this.name = name;
	}

	// Property accessors

	public Integer getMemberId() {
		return this.memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}