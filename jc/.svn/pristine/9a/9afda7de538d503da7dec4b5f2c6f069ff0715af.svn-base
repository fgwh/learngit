package com.hgsoft.security.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * RoleModuleId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class RoleModuleId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String role;
	private String module;

	// Constructors

	/** default constructor */
	public RoleModuleId() {
	}

	/** full constructor */
	public RoleModuleId(String role, String module) {
		this.role = role;
		this.module = module;
	}

	// Property accessors

	@Column(name = "role", nullable = false)
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Column(name = "module", nullable = false)
	public String getModule() {
		return this.module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((module == null) ? 0 : module.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleModuleId other = (RoleModuleId) obj;
		if (module == null) {
			if (other.module != null)
				return false;
		} else if (!module.equals(other.module))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

}
