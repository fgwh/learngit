package com.hgsoft.test.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hgsoft.test.dao.IUserDao;
import com.hgsoft.test.pojo.User;
import com.hgsoft.test.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService{
	
	@Resource
	private IUserDao userDao;
	
	
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return this.userDao.selectByPrimaryKey(userId);
	}
	
}
