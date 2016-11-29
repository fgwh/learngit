package com.hgsoft.main.squadMana.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;



/**
 * AdminSquad entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TB_AdminSquad")
public class AdminSquad  implements java.io.Serializable {


    // Fields    
	 private static final long serialVersionUID = 1L;
     private AdminSquadId id;


    // Constructors

    /** default constructor */
    public AdminSquad() {
    }

    
    /** full constructor */
    public AdminSquad(AdminSquadId id) {
        this.id = id;
    }

   
    // Property accessors
    @EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "adminId", column = @Column(name = "adminId", nullable = false)),
			@AttributeOverride(name = "workNo", column = @Column(name = "workNo", nullable = false)) })
    public AdminSquadId getId() {
        return this.id;
    }
    
    public void setId(AdminSquadId id) {
        this.id = id;
    }
   








}