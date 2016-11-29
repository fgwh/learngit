package com.hgsoft.main.integrity.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hgsoft.main.integrity.service.OperationIntegrityService;
import com.hgsoft.security.action.BaseAction;

@Controller
@Scope("prototype")
public class OperationIntegrityAction extends BaseAction{
@Resource
private OperationIntegrityService operationIntegrityService;
public String list(){
	 list = operationIntegrityService.findAll();
	return "list";
}
}
