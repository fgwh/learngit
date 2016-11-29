package com.hgsoft.security.entity;

import javax.persistence.*;

/**
 * AdminRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_admin_role")
public class AdminRole implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AdminRoleId id;

	// Constructors

	/** default constructor */
	public AdminRole() {
	}

	/** full constructor */
	public AdminRole(AdminRoleId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "admin", column = @Column(name = "admin", nullable = false)),
			@AttributeOverride(name = "role", column = @Column(name = "role", nullable = false)) })
	public AdminRoleId getId() {
		return this.id;
	}

	public void setId(AdminRoleId id) {
		this.id = id;
	}

}
