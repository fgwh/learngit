package com.hgsoft.security.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AdminRoleId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class AdminRoleId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String admin;
	private String role;

	// Constructors

	/** default constructor */
	public AdminRoleId() {
	}

	/** full constructor */
	public AdminRoleId(String admin, String role) {
		this.admin = admin;
		this.role = role;
	}

	// Property accessors

	@Column(name = "admin", nullable = false)
	public String getAdmin() {
		return this.admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	@Column(name = "role", nullable = false)
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((admin == null) ? 0 : admin.hashCode());
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
		AdminRoleId other = (AdminRoleId) obj;
		if (admin == null) {
			if (other.admin != null)
				return false;
		} else if (!admin.equals(other.admin))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}
}
