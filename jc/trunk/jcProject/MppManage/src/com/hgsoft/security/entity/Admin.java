package com.hgsoft.security.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.apache.shiro.SecurityUtils;
import com.hgsoft.main.squadMana.entity.Squad;


/**
 * Admin entity. @author MyEclipse Persistence Tools
 */

public class Admin  implements  BaseEntity{
	
	private static final long serialVersionUID = 1L;
    // Fields    

     private String id;
     private String username;
     private String name;
     private String password;
     private String sex;
     private String staffNo;
     private String ostaffNo;//用于暂存员工编号
     private String email;
     private String phone;
     private String valid;
     private String plane;
     private Date createTime;
     private Date lastTime;
     private String lastIp;
     private String indexMainUrl;
     private String indexSecondUrl;
     private String subsystem;
     
     private String userId;
     private String cardNo;
     private Short userLevel;
     private Set operLogs = new HashSet(0);
     private Set<Role> roles = new HashSet<Role>(0);
     private Set<Squad> squads = new HashSet<Squad>(0); 
     private Set staffLocations = new HashSet(0);

     private Org org;
     private String orgId;
     
    public String getOstaffNo() {
 		return ostaffNo;
 	}

 	public void setOstaffNo(String ostaffNo) {
 		this.ostaffNo = ostaffNo;
 	}
 	
    // Constructors

    /** default constructor */
    public Admin() {
    }

	/** minimal constructor */
    public Admin(String username, String name, String password) {
        this.username = username;
        this.name = name;
        this.password = password;
    }
    
    /** full constructor */
    public Admin(String username, String name, String password, String sex, String staffNo, String email, String phone, String valid, String plane, Date createTime, Date lastTime, String lastIp, String indexMainUrl, String indexSecondUrl, String subsystem, String userId, String cardNo, Short userLevel, Set operLogs, Set roles, Set squads, Set staffAttendanceLists, Set staffLocations,String orgId) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.staffNo = staffNo;
        this.email = email;
        this.phone = phone;
        this.valid = valid;
        this.plane = plane;
        this.createTime = createTime;
        this.lastTime = lastTime;
        this.lastIp = lastIp;
        this.indexMainUrl = indexMainUrl;
        this.indexSecondUrl = indexSecondUrl;
        this.subsystem = subsystem; 
        this.userId = userId;
        this.cardNo = cardNo;
        this.userLevel = userLevel;
        this.operLogs = operLogs;
        this.roles = roles;
        this.squads = squads; 
        this.staffLocations = staffLocations;
        this.orgId = orgId;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return this.sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStaffNo() {
        return this.staffNo;
    }
    
    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getValid() {
        return this.valid;
    }
    
    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getPlane() {
        return this.plane;
    }
    
    public void setPlane(String plane) {
        this.plane = plane;
    }

    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastTime() {
        return this.lastTime;
    }
    
    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public String getLastIp() {
        return this.lastIp;
    }
    
    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public String getIndexMainUrl() {
        return this.indexMainUrl;
    }
    
    public void setIndexMainUrl(String indexMainUrl) {
        this.indexMainUrl = indexMainUrl;
    }

    public String getIndexSecondUrl() {
        return this.indexSecondUrl;
    }
    
    public void setIndexSecondUrl(String indexSecondUrl) {
        this.indexSecondUrl = indexSecondUrl;
    }

    public String getSubsystem() {
        return this.subsystem;
    }
    
    public void setSubsystem(String subsystem) {
        this.subsystem = subsystem;
    }
 
	public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCardNo() {
        return this.cardNo;
    }
    
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Set getOperLogs() {
        return this.operLogs;
    }
    
    public void setOperLogs(Set operLogs) {
        this.operLogs = operLogs;
    }
 
    public Set getStaffLocations() {
        return this.staffLocations;
    }
    
    public void setStaffLocations(Set staffLocations) {
        this.staffLocations = staffLocations;
    }

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Squad> getSquads() {
		return squads;
	}

	public void setSquads(Set<Squad> squads) {
		this.squads = squads;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Short getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Short userLevel) {
		this.userLevel = userLevel;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Admin admin = (Admin) o;

        if (!username.equals(admin.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
}