package com.hgsoft.security.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hgsoft.main.service.DictionaryService;
import com.hgsoft.security.entity.Module;
import com.hgsoft.security.service.ModuleService;

/**
 * @author liujiefeng
 * @date May 19, 2010
 * @Description 系统功能模块管理
 */

@Controller
@Scope("prototype")
@SuppressWarnings( { "rawtypes", "unchecked" })
public class ModuleAction extends BaseAction<Module> {

	public Module getModule() {
		return entity;
	}

	public void setModule(Module module) {
		this.entity = module;
	}
	
	//注入Service
    @Resource
    public void setBaseBridgeService(ModuleService service) {
        this.setService(service);
    }
	
	@Resource
	private ModuleService moduleService;

	public String save() {
		entity.setFunctions(entity.getFunctions().replaceAll("\\s+",""));//替换掉所有的符号
        if (entity.getParent() != null && "".equals(entity.getParent().getId())) {
        	entity.setParent(null);
		} else {
			entity.setParent(moduleService.find(entity.getParent().getId()));
		}

		if (entity.getParent() == null) {
			entity.setLevel(1);
		} else {
			entity.setLevel(entity.getParent().getLevel() + 1);
		}

		moduleService.save(entity);
        //authController.reCreateFilterChains();
		message = "保存成功！";
		return edit();
	}

	public String update() {
        entity.setFunctions(entity.getFunctions().replaceAll("\\s+",""));//替换掉所有的符号
		if (entity.getParent() != null && "".equals(entity.getParent().getId())) {//!"".equals(entity.getParent().getId())
			entity.setParent(null);
		} else {
			if (entity.getId().equals(entity.getParent().getId()))
				return edit();
			entity.setParent(moduleService.find(entity.getParent().getId()));
		}

		Integer level = entity.getLevel();
		if (entity.getParent() == null) {
			entity.setLevel(1);
		} else {
			entity.setLevel(entity.getParent().getLevel() + 1);
		}
		moduleService.update(entity);
		if (!level.equals(entity.getLevel())) {
			// 更新module子节点level
			list = moduleService.findByLevel(level);
			updateLevel(list, entity.getId(), entity.getLevel());
		}
        //authController.reCreateFilterChains();
		message = "修改成功！";
		return edit();
	}

	private void updateLevel(List list, String parent, int level) {
		for (int i = 0; i < list.size(); i++) {
			Module module = (Module) list.get(i);
			if (entity.getParent() != null
					&& entity.getParent().getId().equals(parent)) {
				entity.setLevel(level + 1);
				moduleService.update(module);
				updateLevel(list, entity.getId(), level + 1);
			}
		}
	}

    public String delete() {
        moduleService.deleteModule(entity.getId());
        //authController.reCreateFilterChains();
        message = "删除成功！";
        return edit();
    }

	public String edit() {
		list = moduleService.findAll();
		if (list != null) {
			// 排序 list，把每个结点对应的下级结点相邻，采用循环查找方法
			for (int i = 0; i < list.size(); i++) {
				String currentId = ((Module) list.get(i)).getId();
				int count = list.size() - i - 1;
				for (int j = i + 1; count > 0 && j < list.size(); j++) {
					Module m = (Module) list.get(j);
					// 若此结点不是currentId的下层结点，则放到放到list最后
					if (m.getParent() == null) {
						list.remove(j);
						list.add(m);
						j--;
					} else if (!m.getParent().getId().equals(currentId)) {
						list.remove(j);
						list.add(m);
						j--;
					}
					count--;
				}
			}
		}
		return EDIT;
	}
}
