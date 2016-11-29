package com.hgsoft.main.entity;
// default package



/**
 * SourceAdmin entity. @author MyEclipse Persistence Tools
 */

public class SourceAdmin  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer sourceSystemId;
     private Integer userId;
     private String userName;
     private String password;
     private Integer status;
     private String remark;


    // Constructors

    /** default constructor */
    public SourceAdmin() {
    }

    
    /** full constructor */
    public SourceAdmin(Integer sourceSystemId, Integer userId, String userName, String password, Integer status, String remark) {
        this.sourceSystemId = sourceSystemId;
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.status = status;
        this.remark = remark;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSourceSystemId() {
        return this.sourceSystemId;
    }
    
    public void setSourceSystemId(Integer sourceSystemId) {
        this.sourceSystemId = sourceSystemId;
    }

    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
   








}