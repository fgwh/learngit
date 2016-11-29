package com.hgsoft.main.squadMana.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;



/**
 * AdminSquadId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class AdminSquadId  implements java.io.Serializable {


    // Fields    
	private static final long serialVersionUID = 1L;
     private Integer adminId;
     private Integer workNo;


    // Constructors

    /** default constructor */
    public AdminSquadId() {
    }

    
    /** full constructor */
    public AdminSquadId(Integer adminId, Integer workNo) {
        this.adminId = adminId;
        this.workNo = workNo;
    }

   
    // Property accessors
    @Column(name = "adminId", nullable = false)
    public Integer getAdminId() {
        return this.adminId;
    }
    
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    @Column(name = "workNo", nullable = false)
    public Integer getWorkNo() {
        return this.workNo;
    }
    
    public void setWorkNo(Integer workNo) {
        this.workNo = workNo;
    }


	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AdminSquadId))
			return false;
		AdminSquadId castOther = (AdminSquadId) other;

		return ((this.getAdminId() == castOther.getAdminId()) || (this.getAdminId() != null
				&& castOther.getAdminId() != null && this.getAdminId().equals(castOther.getAdminId())))
				&& ((this.getWorkNo() == castOther.getWorkNo()) || (this.getWorkNo() != null
						&& castOther.getWorkNo() != null && this.getWorkNo().equals(castOther.getWorkNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getAdminId() == null ? 0 : this.getAdminId().hashCode());
		result = 37 * result + (getWorkNo() == null ? 0 : this.getWorkNo().hashCode());
		return result;
	}

}