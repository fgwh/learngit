package com.hgsoft.security.entity;

import javax.persistence.*;

/**
 * RoleModule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_role_module")
public class RoleModule implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RoleModuleId id;

	// Constructors

	/** default constructor */
	public RoleModule() {
	}

	/** full constructor */
	public RoleModule(RoleModuleId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "role", column = @Column(name = "role", nullable = false)),
			@AttributeOverride(name = "module", column = @Column(name = "module", nullable = false)) })
	public RoleModuleId getId() {
		return this.id;
	}

	public void setId(RoleModuleId id) {
		this.id = id;
	}

}
