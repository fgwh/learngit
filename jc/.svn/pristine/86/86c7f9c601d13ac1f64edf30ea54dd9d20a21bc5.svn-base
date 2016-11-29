package com.hgsoft.main.msgPublishManage.action;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.hgsoft.main.msgPublishManage.entity.MsgPublish;
import com.hgsoft.main.msgPublishManage.service.MsgPublishService;
import com.hgsoft.security.action.BaseAction;
import com.hgsoft.security.operLog.annotation.Description;

/**
 * 信息发布服务类
 * 
 * @version 1.0
 * @date 2016-09-10
 * @author liyuyun
 * @Description 消息发布管理
 */
@Controller
@Scope("prototype")
public class MsgPublishAction extends BaseAction<MsgPublish> {

	public MsgPublish getMsgPublish() {
		return this.entity;
	}

	public void setMsgPublish(MsgPublish msgPublish) {
		this.entity = msgPublish;
	}

	// 注入Service
	@Resource
	public void setBaseService(MsgPublishService service) {
		this.setService(service);
	}
	
	@Resource
	MsgPublishService service;
	@Description(details="消息新增")
	public String add(){
		service.add(entity,operator);
		reset();
		return RELIST;
	}
	/**
	 * 登录首页显示
	 * @return
	 */
	@Description(details="消息首页查询")
	public String homeList(){
		if(null == entity) {
			entity = service.newEntityInstance();
		}
		this.list = service.query(getPager(), entity);	
		return "homeList";
	}
	@Description(details="消息首页删除")
	public String homeDelete(){
		try {
	    	entity = service.find(entity.getId());
	    	service.delete(entity);
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	reset();
    	return homeList();
	}
}
