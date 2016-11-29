package com.hgsoft.security.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hgsoft.security.dao.AdminLoginDao;
import com.hgsoft.security.entity.AdminLogin;

@Service
public class AdminLoginService extends BaseService<AdminLogin> {
	
	@Resource
	public void setDao(AdminLoginDao dao){
		super.setDao(dao);
	}

	public AdminLoginDao getAdminLoginDao() {
		return (AdminLoginDao) this.getDao();
	}

}
