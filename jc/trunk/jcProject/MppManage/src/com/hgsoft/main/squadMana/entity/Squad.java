package com.hgsoft.main.squadMana.entity;

import java.sql.Time;
import java.util.Date;

import com.hgsoft.security.entity.BaseEntity;


/**
 * Squad entity. @author MyEclipse Persistence Tools
 */

public class Squad  implements BaseEntity {


    // Fields    
	 private String id;
     private Integer workNo;
     private String workName;
     private Integer workType;
     private Time startTime;
     private Time endTime;
     private Date startDate;
     private Integer timeDiff;
     private String remark;
     private Integer squadStatus;


    // Constructors

    /** default constructor */
    public Squad() {
    }

	/** minimal constructor */
    public Squad(String id,String workName, Integer workType, Time startTime, Time endTime, Date startDate, Integer timeDiff) {
        this.id = id;
    	this.workName = workName;
        this.workType = workType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDate = startDate;
        this.timeDiff = timeDiff;
    }
    
    /** full constructor */
    public Squad(String id,String workName, Integer workType, Time startTime, Time endTime, Date startDate, Integer timeDiff, String remark, Integer squadStatus) {
    	this.id = id;
    	this.workName = workName;
        this.workType = workType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDate = startDate;
        this.timeDiff = timeDiff;
        this.remark = remark;
        this.squadStatus = squadStatus;
    }

   
    // Property accessors
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
    public Integer getWorkNo() {
        return this.workNo;
    }
    
	public void setWorkNo(Integer workNo) {
        this.workNo = workNo;
    }

    public String getWorkName() {
        return this.workName;
    }
    
    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public Date getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getTimeDiff() {
        return this.timeDiff;
    }
    
    public void setTimeDiff(Integer timeDiff) {
        this.timeDiff = timeDiff;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

	public Integer getWorkType() {
		return workType;
	}

	public void setWorkType(Integer workType) {
		this.workType = workType;
	}

	public Integer getSquadStatus() {
		return squadStatus;
	}

	public void setSquadStatus(Integer squadStatus) {
		this.squadStatus = squadStatus;
	}
}