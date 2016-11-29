package com.hgsoft.security.dao;

import org.springframework.stereotype.Repository;
import com.hgsoft.security.entity.Module;

@Repository 
public class ModuleDao extends BaseDao<Module> {
	@Override
    public String getConditions(Module module){
		if (module == null) {
			return "";
		}
		return "";
	}
	
	@Override
	public String setOrders(Module entity) {
		return "";
	}
}
