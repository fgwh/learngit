package com.hgsoft.security.action;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.hgsoft.security.entity.Role;
import com.hgsoft.security.service.ModuleService;

/**
 * @author liujiefeng
 * @date May 19, 2010
 * @Description 网站后台
 */

@Controller
@Scope("prototype")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SystemAction extends BaseAction {

	@Resource
	ModuleService moduleService;

	public String index() {
		if(operator == null){
			return LOGIN;
		}
		HashSet set = new HashSet();
		Set roles = operator.getRoles();
		if (roles != null && !roles.isEmpty()) {
			Iterator it = roles.iterator();
			while (it.hasNext()) {
				Role role = (Role) it.next();
				set.addAll(role.getModules());
			}
			if (set != null && !set.isEmpty()) {
				list = moduleService.getMenus(set);
			}
		}

		return "index";
	}

	public String top() {
		return "top";
	}
	
	public String right() {
		return "right";
	}

	public String left() {
		HashSet set = new HashSet();
		Set roles = operator.getRoles();
		if (roles != null && !roles.isEmpty()) {
			Iterator it = roles.iterator();
			while (it.hasNext()) {
				Role role = (Role) it.next();
				set.addAll(role.getModules());
			}
			if (set != null && !set.isEmpty()) {
				list = moduleService.getMenus(set);
			}
		}
		return "left";
	}

	public String leftJson() {
		HashSet set = new HashSet();
		Set roles = operator.getRoles();
		if (roles != null && !roles.isEmpty()) {
			Iterator it = roles.iterator();
			while (it.hasNext()) {
				Role role = (Role) it.next();
				set.addAll(role.getModules());
			}
			if (set != null && !set.isEmpty()) {
				list = moduleService.getMenus(set);
			}
		}
		return "leftJson";
	}
	
	public String middle() {
		return "middle";
	}
	
	
	/****************************************************** set/get方法 ******************************************/
	
	public ModuleService getModuleService() {
		return moduleService;
	}
	
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}
}
