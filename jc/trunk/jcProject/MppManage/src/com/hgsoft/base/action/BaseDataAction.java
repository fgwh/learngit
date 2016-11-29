package com.hgsoft.base.action;


import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.hgsoft.base.entity.BaseDataEntity;
import com.hgsoft.base.service.BaseDataService;
import com.hgsoft.flow.constant.FlowConstant;
import com.hgsoft.security.action.BaseAction;
import com.hgsoft.security.service.BaseService;

/**
 * 基础数据操作抽象类
 * @author linl
 *
 */
public abstract class BaseDataAction<Entity extends BaseDataEntity, Service extends BaseDataService<Entity>> 
extends BaseAction<Entity>
implements ServletResponseAware, ServletContextAware
{
	
	/** 对应的服务类 */
	protected Service service;
	
	/** 实体对象,用于查询和增/改 */
	protected Entity entity;


	/** 编辑界面,根据编辑后的实体对象 更新 数据库实体对象 *//*
	protected abstract void updateEntity(Entity entity);*/
	
	/** 检查记录是否已存在 */
	public abstract Boolean checkExist();
	
	/**
	 * tab页
	 * @return
	 */
	public String frame(){
		return "frame";
	}
	
	/**
	 * 信息列表
	 * @return
	 */
	@Override
	public String list(){
		if(null == entity) {
			entity = service.newEntity();
		}		
		this.list = service.query(getPager(), entity);
		return LIST;
	}
	
	/**
	 * 我的待办
	 * @return
	 */
	public String workinglist(){
		list = service.queryWorkingTaskByUserId(pager, entity, getOperator());
		return "workingList";
	}
	
	/**
	 * 我的已办
	 * @return
	 */
	public String workedlist(){
		list = service.queryWorkedTaskByUserId(pager, entity, getOperator());
		return "workedList";
	}

	/** 编辑 */
	public String edit() {
		entity = service.find(entity.getId());
		return EDIT;
	}

	/** 检查记录是否已存在 */
	public String isExist(){
		Boolean isExist = checkExist();
		jsonResult = new JsonResult();
		jsonResult.setData(isExist);
		return "isExist";
	}
	
	/** 增 */
	@Override
	public String save(){
		entity.setLatestMark(true);
		entity.setFlowStatus(FlowConstant.FLOW_STATUS_NOREPORT);		
		service.saveOrUpdate(entity);
		entity.setPid(entity.getId());
		service.saveOrUpdate(entity);
		this.message = "保存成功";
		reset();
		
		if("Mobile".equals(deviceType)) {
			map.put("result", "save");
			return "mobile";
		}
		
		return "frame";
	}
	
	/** 改 */
	@Override
	public String update(){
		Entity _entity = service.find(entity.getId());
		if(_entity==null){
			message = "记录不存在";
			return EDIT;
		}
		//updateEntity(_entity);
		entity.setFlowStatus(_entity.getFlowStatus());
		entity.setLatestMark(_entity.getLatestMark());
		entity.setPid(_entity.getPid());
		if (entity.getFlowStatus()!=null && FlowConstant.FLOW_STATUS_REPORTED==entity.getFlowStatus()){
			service.updateAudit(entity);
		} else {
			service.update(entity);
		}
    	reset();
    	return "frame";
	}
	
	/** 删 */
	@Override
    public String delete() {
    	entity = service.find(entity.getId());
    	service.delete(entity);
    	reset();
    	return list();
    }
    
	 /** 批量删 */
	@Override
    public String batchDelete() {
    	if(ids != null){
        	service.batchDelete(ids.split(","));
    	}
    	reset();
        return list();
    }
	
	
	public Service getService() {
		return service;
	}
	
}
