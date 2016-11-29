package com.hgsoft.main.entity;

import com.hgsoft.security.entity.BaseEntity;

@SuppressWarnings("serial")
public class Good implements  BaseEntity{
	
	private String id;
	private String name;
	private String pyname;
	private String pid;
	private Integer level;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPyname() {
		return pyname;
	}
	public void setPyname(String pyname) {
		this.pyname = pyname;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	
}
