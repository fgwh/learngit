package com.hgsoft.main.im.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * GroupMsg entity. @author MyEclipse Persistence Tools
 */

public class GroupMsg implements java.io.Serializable {

	// Fields

	private String groupMsgId;
	private Group group;
	private String groupMsg;
	private Date createTime;
	private Integer senderId;
	private String senderName;
	private Integer contentType;
	private Set groupMsgStatuses = new HashSet(0);

	// Constructors

	/** default constructor */
	public GroupMsg() {
	}

	/** full constructor */
	public GroupMsg(Group group, String groupMsg, Date createTime,
			Integer senderId, String senderName,
			Integer contentType, Set groupMsgStatuses) {
		this.group = group;
		this.groupMsg = groupMsg;
		this.createTime = createTime;
		this.senderId = senderId;
		this.senderName = senderName;
		this.contentType = contentType;
		this.groupMsgStatuses = groupMsgStatuses;
	}

	// Property accessors

	public String getGroupMsgId() {
		return this.groupMsgId;
	}

	public void setGroupMsgId(String groupMsgId) {
		this.groupMsgId = groupMsgId;
	}

	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String getGroupMsg() {
		return this.groupMsg;
	}

	public void setGroupMsg(String groupMsg) {
		this.groupMsg = groupMsg;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getSenderId() {
		return this.senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	public String getSenderName() {
		return this.senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public Integer getContentType() {
		return this.contentType;
	}

	public void setContentType(Integer contentType) {
		this.contentType = contentType;
	}

	public Set getGroupMsgStatuses() {
		return this.groupMsgStatuses;
	}

	public void setGroupMsgStatuses(Set groupMsgStatuses) {
		this.groupMsgStatuses = groupMsgStatuses;
	}

}