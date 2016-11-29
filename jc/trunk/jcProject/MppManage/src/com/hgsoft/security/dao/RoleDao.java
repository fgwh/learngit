package com.hgsoft.security.dao;

import org.springframework.stereotype.Repository;
import com.hgsoft.security.entity.Role;

@Repository
public class RoleDao extends BaseDao<Role> {
	@Override
    public String getConditions(Role role){
		if (role == null) {
			return "";
		}
		 
		return "";
	}
	@Override
	public String setOrders(Role entity) {
		 
		return " order by c.id desc ";
	}
}
