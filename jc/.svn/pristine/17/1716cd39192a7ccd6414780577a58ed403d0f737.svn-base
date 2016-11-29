package com.hgsoft.main.msgPublishManage.entity;

import java.util.Date;
import com.hgsoft.security.entity.BaseEntity;

/**
 * Squad entity. @author MyEclipse Persistence Tools
 */

public class MsgPublish implements BaseEntity {

	// Fields
	private String id;
	private Date publishTime;
	private String theme;
	private String content;
	private String publishMan;
	//查询条件
	private String beginDate;
	private String endDate;
	
	public MsgPublish() {

	}

	public MsgPublish(String id, Date publishTime, String theme, String content, String publishMan) {

		this.id = id;
		this.publishTime = publishTime;
		this.theme = theme;
		this.content = content;
		this.publishMan = publishMan;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPublishMan() {
		return publishMan;
	}

	public void setPublishMan(String publishMan) {
		this.publishMan = publishMan;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "MsgPublish [id=" + id + ", publishTime=" + publishTime + ", theme=" + theme + ", content=" + content
				+ ", publishMan=" + publishMan + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((publishMan == null) ? 0 : publishMan.hashCode());
		result = prime * result + ((publishTime == null) ? 0 : publishTime.hashCode());
		result = prime * result + ((theme == null) ? 0 : theme.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MsgPublish other = (MsgPublish) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (publishMan == null) {
			if (other.publishMan != null)
				return false;
		} else if (!publishMan.equals(other.publishMan))
			return false;
		if (publishTime == null) {
			if (other.publishTime != null)
				return false;
		} else if (!publishTime.equals(other.publishTime))
			return false;
		if (theme == null) {
			if (other.theme != null)
				return false;
		} else if (!theme.equals(other.theme))
			return false;
		return true;
	}
}