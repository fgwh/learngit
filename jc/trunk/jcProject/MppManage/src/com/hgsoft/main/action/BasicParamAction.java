package com.hgsoft.main.action;

import java.util.List;

import com.hgsoft.main.entity.BasicParam;
import com.hgsoft.main.service.BasicParamService;
import com.hgsoft.security.action.BaseAction;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class BasicParamAction extends BaseAction<BasicParam>{

	@Resource
	private BasicParamService basicParamService;
	/* 获取实体, 用于JSP读取实体对象属性 */
    public BasicParam getBasicParam() {
        return this.entity;
    }
    /* 设置实体, 用于STRUTS设置实体对象属性 */
    public void setBasicParam(BasicParam basicParam) {
        this.entity = basicParam;
    }
    //注入Service
    @Resource
    public void setBaseBridgeService(BasicParamService service) {
        this.setService(service);
    }
 
	private String nameExsit;
	 
	public String paramNameExsit(){
		List<?> list = basicParamService.queryBasicParamListByName(entity.getParamName());
		if(list.size()>0){
			setNameExsit("yes");
		}else{
			setNameExsit("no");
		}
		return "paramNameExsit";
	}
	 
	public void setNameExsit(String nameExsit) {
		this.nameExsit = nameExsit;
	}

	public String getNameExsit() {
		return nameExsit;
	}	
}
