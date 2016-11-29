package com.hgsoft.main.squadMana.action;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.hgsoft.main.entity.BasicParam;
import com.hgsoft.main.squadMana.entity.Squad;
import com.hgsoft.main.squadMana.service.SquadService;
import com.hgsoft.security.action.BaseAction;
import com.hgsoft.security.entity.Admin;
import com.hgsoft.security.service.AdminService;

/**
 * 工班管理Action
 * 
 * @version 1.0
 * @date 2014-10-16
 * @author wubiao
 *
 */
@Controller
@Scope("prototype")
public class SquadAction extends BaseAction<Squad> {
	
	@Resource
	private SquadService squadService;
	@Resource
	private AdminService adminService; 
    private Admin admin = new Admin();
    
	/* 获取实体, 用于JSP读取实体对象属性 */
    public Squad getSquad() {
        return this.entity;
    }
    /* 设置实体, 用于STRUTS设置实体对象属性 */
    public void setSquad(Squad squad) {
        this.entity = squad;
    }
  //注入Service
    @Resource
    public void setBaseBridgeService(SquadService service) {
        this.setService(service);
    }
     
	public String save(){	
		entity.setStartDate(new Date());//设置生效日期
		entity.setSquadStatus(1);//默认启用
		squadService.save(entity);
		reset();
		return list();
	}
	
	/*
	 * 修改工班信息
	 */
	public String update(){
		
		entity.setStartDate(new Date());//设置生效日期
		squadService.update(entity);
		reset();
		return list();
	}
	
	/**
	 * 查询用户列表
	 *
	 * @return
	 * @throws Exception
	 */
	public String listAdmin() {
		
		this.list = adminService.query(pager, admin, this.operator);
		
		return "listAdmin";
	}
	
	public String allotEdit() {
		
		admin =  adminService.find(admin.getId());
		list = squadService.findAll();
		
		return "allotEdit";
	}
	
	public String allotUpdate() {
		
		Admin adminDb = adminService.find(admin.getId());
		
		adminDb.setSquads(admin.getSquads());
		
		adminService.update(adminDb);
		message = "操作成功！";
		
		return listAdmin();
	}
	
	public String isExists() {
		String workName = entity.getWorkName();
		Squad squadDb = squadService.getSquadByName(workName);
		
		if (null != squadDb && entity.getWorkNo() != squadDb.getWorkNo()) {
			message = "班次名重复";
		} else { 
			message = ""; 
		}
		return "message";
	}
	
	public String isIdExists() {
		 
		Squad squadDb = squadService.getSquadByWorkNo(entity.getWorkNo());
		
		if (null != squadDb && entity.getId() != squadDb.getId()) {
			message = "工班编号重复";
		} else { 
			message = ""; 
		}
		return "message";
	}
	
	/**
	 * 停用工班
	 *
	 * @return
	 */
	public String disable() {
		// adminService.deleteById(admin.getId());
		if (entity == null || entity.getId() == null) {
			this.message = "待停用的工班不存在！";
			return ERROR;
		}
		squadService.updateSquadDisabled(entity.getId());
		return list();
	}

	/**
	 * 启用用户
	 *
	 * @return
	 */
	public String enable() {
		// adminService.deleteById(admin.getId());
		if (entity == null || entity.getId() == null) {
			this.message = "待启用的工班不存在！";
			return ERROR;
		}
		squadService.updateSquadEnable(entity.getId());
		return list();
	}
	
	/* ==============================  get/set  ===========================*/
	
	

	 

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	/*@SuppressWarnings("unchecked")
	public void setSquads(String squads) {
		if (squads != null) {
			squads = squads.replace("，", ",").replace(" ", "");
			String[] ids = squads.split(",");
			if (ids != null && ids.length > 0) {
				for (int i = 0; i < ids.length; i++) {
					Integer j = new Integer(ids[i]);
					if (j > 0) {
						Squad squad = new Squad();
						squad.setWorkNo(j);
						admin.getSquads().add(squad);
					}
				}
			}
		}
	}*/
 
}
