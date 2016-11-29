package com.hgsoft.flow.action;

import com.hgsoft.affix.action.AffixFileAction;
import com.hgsoft.flow.constant.FlowConstant;
import com.hgsoft.flow.entity.FlowAuditInfo;
import com.hgsoft.flow.service.FlowAuditService;
import com.hgsoft.main.entity.DicItem;
import com.hgsoft.main.service.DictionaryService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
@Scope("prototype")
public class FlowAuditAction extends AffixFileAction{
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private FlowAuditService flowAuditService;
	private FlowAuditInfo flowAuditInfo = new FlowAuditInfo();
	private String bids;//选择的业务数据集合
	private String stepType;//发送下一步、退回上一步、审批结束
	private String relistUrl;//返回审批前的具体的业务列表
	/***
	 * 审批入口
	 * @return
	 */
	public String audit(){
		DicItem dicItem = dictionaryService.getItem("flowAuditBizType", flowAuditInfo.getBizType());
		if (dicItem==null){
			//抛出businessException
			return null;
		}
		flowAuditInfo.setSendUserId(getOperator().getId());
		flowAuditInfo.setSendUserName(getOperator().getName());
		switch (stepType) {
			case FlowConstant.FLOW_STEP_NEXT:{
				//点击发送下一步时，
				flowAuditService.sendNext(flowAuditInfo, dicItem.getName(), bids);
				break;
			}
			case FlowConstant.FLOW_STEP_UP: {
				break;
			}
			case FlowConstant.FLOW_STEP_FINISH: {
				flowAuditInfo.setReceiveUserId(getOperator().getId());
				flowAuditInfo.setReceiveUserName(getOperator().getName());
				flowAuditInfo.setReportTime(new java.util.Date());
				flowAuditService.finishFlow(flowAuditInfo, dicItem.getName(), bids);
				break;
			}
		}
		message="提交成功";
		return "relist";
	}
	
	public String orgTreeWithPeople(){
		//更改成取上级人员
		if (getOperator().getOrg().getOrgLevel()==1){
			list = flowAuditService.getSelectPeopleListWithOrg(getOperator().getOrg());
		} else {
			list = flowAuditService.getSelectPeopleListWithOrg(getOperator().getOrg().getParent());
		}
		return "tree";
	}
	
	public String  sameOrgTreeWithPeople() {
		list = flowAuditService.getSelectPeopleListWithOrg(getOperator().getOrg());
		return "tree";
	}
	
	/**
	 * 取下级人员
	 * @return
	 */
	public String subOrgTreeWithPeople(){
		list = flowAuditService.getSubSelectPeopleListWithOrg(getOperator().getOrg());
		return "tree";
	}
	
	public String list(){
		list = flowAuditService.queryListByPid(flowAuditInfo.getBizPid());
		return "list";
	}
	
	
	/***
	 * 跳转填写意见
	 * @return
	 */
	public String toReport(){
		return "toReport";
	}

	

	public String getBids() {
		return bids;
	}

	public void setBids(String bids) {
		this.bids = bids;
	}

	public String getStepType() {
		return stepType;
	}

	public void setStepType(String stepType) {
		this.stepType = stepType;
	}

	public FlowAuditInfo getFlowAuditInfo() {
		return flowAuditInfo;
	}

	public void setFlowAuditInfo(FlowAuditInfo flowAuditInfo) {
		this.flowAuditInfo = flowAuditInfo;
	}

	public String getRelistUrl() {
		return relistUrl;
	}

	public void setRelistUrl(String relistUrl) {
		this.relistUrl = relistUrl;
	}
	
}
