package com.hgsoft.main.im.entity;


import java.util.Date;

public class ChatMessage {
	private String sendId;
	private String sender;
	private String recipientId;
	private String recipientor;
	private String messageId;
	private String messageType;
	private String singleOrGroup;
	private String messageContent;
	private String time;

	
	public String getSendId() {
		return sendId;
	}
	public String getSender() {
		return sender;
	}
	public String getRecipientId() {
		return recipientId;
	}
	public String getRecipientor() {
		return recipientor;
	}
	public String getMessageId() {
		return messageId;
	}
	public String getMessageType() {
		return messageType;
	}
	public String getSingleOrGroup() {
		return singleOrGroup;
	}
	
	public String getTime() {
		return time;
	}
	public void setSendId(String sendId) {
		this.sendId = sendId;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}
	public void setRecipientor(String recipientor) {
		this.recipientor = recipientor;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public void setSingleOrGroup(String singleOrGroup) {
		this.singleOrGroup = singleOrGroup;
	}
	
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
	
 
	// getter, setter, toString omitted..
}