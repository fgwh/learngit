package com.hgsoft.main.im.entity;

/**
 * GroupMsgStatus entity. @author MyEclipse Persistence Tools
 */

public class GroupMsgStatus implements java.io.Serializable {

	// Fields

	private Integer statusId;
	private GroupMsg groupMsg;
	private Integer receiveid;
	private String receiveName;
	private Integer isRead;

	// Constructors

	/** default constructor */
	public GroupMsgStatus() {
	}

	/** full constructor */
	public GroupMsgStatus(GroupMsg groupMsg, Integer receiveid,
			String receiveName, Integer isRead) {
		this.groupMsg = groupMsg;
		this.receiveid = receiveid;
		this.receiveName = receiveName;
		this.isRead = isRead;
	}

	// Property accessors

	

	public GroupMsg getGroupMsg() {
		return this.groupMsg;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public void setGroupMsg(GroupMsg groupMsg) {
		this.groupMsg = groupMsg;
	}

	public Integer getReceiveid() {
		return this.receiveid;
	}

	public void setReceiveid(Integer receiveid) {
		this.receiveid = receiveid;
	}

	public String getReceiveName() {
		return this.receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public Integer getIsRead() {
		return this.isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

}