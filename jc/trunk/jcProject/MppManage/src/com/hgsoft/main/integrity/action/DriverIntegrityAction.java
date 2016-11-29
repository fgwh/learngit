package com.hgsoft.main.integrity.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hgsoft.main.integrity.service.DriverIntegrityService;
import com.hgsoft.security.action.BaseAction;
@Controller
@Scope("prototype")
public class DriverIntegrityAction extends BaseAction{
@Resource
private DriverIntegrityService driverIntegrityService;
public String list(){
	 list = driverIntegrityService.findAll();
	return "list";
}
}
