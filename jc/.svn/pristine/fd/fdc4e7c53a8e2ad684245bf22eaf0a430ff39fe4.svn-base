package com.hgsoft.security.entity;

import java.util.Date;


public class Org implements BaseEntity {
    private static final long serialVersionUID = -4857756469643231598L;
    
    public static final String ORGTYPE_OWNER = "owner";
    public static final String ORGTYPE_CONSTRUCTPARTY = "constructParty";
    public static final String ORGTYPE_SUPERVISION = "supervision";
    
    private String id; //编号
    private Integer orgLevel;//层
    private String orgName;//机构名称
    private String orgCode;//机构编号
    private String orgType;//机构类型
    private Org parent; 
    private String  parentId; //父机构ID
    private String serverName;//服务器名称
    private String serverIP;//服务器IP
    private String domain;//域名
    private String dbName;//数据库名称
    private String dbUserName;//数据库用户名
    private String dbPassword;//数据库密码
    private Date startTime;//启用时间
    private String treeCode;//树编号
    private Double Xcode;//经度坐标
    private Double Ycode;//纬度坐标
    private Integer orgStatus;//状态
    private String remark;//备注
    private Integer priority;//排序
    private Integer isDelete;//是否删除 0未删除 -1表示删除
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Org that = (Org) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

//    @Override
//    public String toString() {
//        return "Org{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", parentId=" + parent.getId() +
//                ", available=" + available +
//                '}';
//    }
    

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getParentId() {
		if (parent ==null){
			return "-1";
		} else {
			return parent.id;
		}
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public Integer getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(Integer orgLevel) {
		this.orgLevel = orgLevel;
	}

	public Org getParent() {
		return parent;
	}

	public void setParent(Org parent) {
		this.parent = parent;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getServerIP() {
		return serverIP;
	}

	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbUserName() {
		return dbUserName;
	}

	public void setDbUserName(String dbUserName) {
		this.dbUserName = dbUserName;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getTreeCode() {
		return treeCode;
	}

	public void setTreeCode(String treeCode) {
		this.treeCode = treeCode;
	}

	public Double getXcode() {
		return Xcode;
	}

	public void setXcode(Double xcode) {
		Xcode = xcode;
	}

	public Double getYcode() {
		return Ycode;
	}

	public void setYcode(Double ycode) {
		Ycode = ycode;
	}

	public Integer getOrgStatus() {
		return orgStatus;
	}

	public void setOrgStatus(Integer orgStatus) {
		this.orgStatus = orgStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
}
