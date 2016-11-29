package com.hgsoft.main.jcManange.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hgsoft.main.jcManange.entity.Conditions;
import com.hgsoft.main.jcManange.entity.RoGrayList;
import com.hgsoft.main.jcManange.service.AbnorCarService;
import com.hgsoft.main.jcManange.service.AbnormalCarDetailService;
import com.hgsoft.security.action.BaseAction;

@Controller
@Scope("prototype")
public class AbnorCarAction extends BaseAction{
	
	private Conditions conditions=new Conditions();
	private String plateNum;
	private String auditStatus;
	private String id;
	private RoGrayList roGrayList;
	
	@Resource
	AbnorCarService abnorcarservice;
	@Resource
	AbnormalCarDetailService abnormalcardetailservice;
	 
	public String getAbnorCar(){
		list=abnorcarservice.getAbnorCar(conditions, pager);
		return LIST;
	}
	
	
	public String getAbnorLiuShui(){
		list=abnormalcardetailservice.getAbnorLiuShui(plateNum, auditStatus);
		map.put("AbnorLiuShui", list);
		return SUCCESS;
	}

	public String updateAbnor(){
	    abnormalcardetailservice.updateAbnorCat(conditions.getBatChplateID());
	    /*map.put("msg", true);
		return SUCCESS;*/
	    return getAbnorCar();
	}

	//异常流水审核详情
	 public String getLaneExDetail(){
			map.put("list", abnormalcardetailservice.getAbnormalCarDetail(plateNum));
		    return SUCCESS;
	 }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//------------------------------get set--------------------------------//

	public String getPlateNum() {
		return plateNum;
	}
	public Conditions getConditions() {
		return conditions;
	}
	public void setConditions(Conditions conditions) {
		this.conditions = conditions;
	}
	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}

	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public RoGrayList getRoGrayList() {
		return roGrayList;
	}
	public void setRoGrayList(RoGrayList roGrayList) {
		this.roGrayList = roGrayList;
	}

	
}
