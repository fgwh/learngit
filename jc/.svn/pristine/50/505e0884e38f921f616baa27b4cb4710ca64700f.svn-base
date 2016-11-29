package com.hgsoft.main.im.entity;


import java.util.Date;

// default package



/**
 * GroupUser entity. @author MyEclipse Persistence Tools
 */

public class GroupUser  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Group group;
     private Integer userid;
     private String username;
     

     private Date createTime;

    // Constructors

    /** default constructor */
    public GroupUser() {
    }

	/** minimal constructor */
    public GroupUser(Integer userid,String username,Date createTime) {
        this.userid = userid;
        this.setUsername(username);
        this.createTime = createTime;
    }
    
    /** full constructor */
    public GroupUser(Group group, Integer userid, String username, Date createTime) {
        this.group = group;
        this.userid = userid;
        this.setUsername(username);
       
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Group getGroup() {
        return this.group;
    }
    
    public void setGroup(Group group) {
        this.group = group;
    }

    public Integer getUserid() {
        return this.userid;
    }
    
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}









}