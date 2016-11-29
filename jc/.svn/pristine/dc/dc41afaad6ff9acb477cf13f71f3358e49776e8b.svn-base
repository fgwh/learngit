package com.hgsoft.main.integrity.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hgsoft.main.integrity.service.VehIntegrityService;
import com.hgsoft.security.action.BaseAction;
@Controller
@Scope("prototype")
public class VehIntegrityAction extends BaseAction{
	
	private static Logger logger = Logger.getLogger(VehIntegrityAction.class);
	@Resource
	private VehIntegrityService vehIntegrityService;
	
	public String list(){
		 list = vehIntegrityService.findAll();
		return "list";
	}
	/***************--获取字典信息--**************/
	
	
}
