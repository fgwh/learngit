package com.hgsoft.flow.service;

import com.hgsoft.flow.constant.FlowConstant;
import com.hgsoft.flow.dao.FlowAuditInfoDao;
import com.hgsoft.flow.entity.FlowAuditInfo;
import com.hgsoft.security.entity.Admin;
import com.hgsoft.security.entity.Org;
import com.hgsoft.security.service.AdminService;
import com.hgsoft.security.service.BaseService;
import com.hgsoft.security.util.OrgUtils;
import com.hgsoft.util.SpringInit;
import com.hgsoft.util.StrTool;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Service
public class FlowAuditService extends BaseService<FlowAuditInfo> {
	@Resource
	private FlowAuditInfoDao flowAuditInfoDao;
	
	@Resource
    public void setDao(FlowAuditInfoDao dao) {
        super.setDao(dao);
    }
	
	@Override
	public void saveOrUpdate(FlowAuditInfo flowAuditInfo){
		if (StrTool.isBlankStr(flowAuditInfo.getId())){
			save(flowAuditInfo);
		} else {
			update(flowAuditInfo);
		}
	}
	
	/***
	 * 发送下一步（退回也是发送下一步，直线流只往下走）
	 * @param flowAuditInfo
	 * @param className
	 * @param bizIds
	 */
	public void sendNext(FlowAuditInfo flowAuditInfo,String className, String bizIds) {
		if (StrTool.isBlankStr(bizIds)){
			return;
		}
		flowAuditInfo.setReportTime(new java.util.Date());
		
		FlowInterface flowInterService = (FlowInterface)SpringInit.getApplicationContext().getBean(className);
		String [] bizIdss = bizIds.split(";");
		for (int i = 0; i < bizIdss.length; i++) {
			FlowAuditInfo saveFlow = new FlowAuditInfo();
			try {
				BeanUtils.copyProperties(saveFlow, flowAuditInfo);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			String pid = flowInterService.findBizPidByBizId(bizIdss[i]);
			//更新之前的一条审批记录表
			FlowAuditInfo flowAuditInfoTemp = flowAuditInfoDao.findLatestFlowAuditInfoByPid(pid);
			if (flowAuditInfoTemp!=null){
				flowAuditInfoTemp.setFinish(true);
				update(flowAuditInfoTemp);
				//流程的操作类型要跟上一次审批的操作一致
				saveFlow.setOperateType(flowAuditInfoTemp.getOperateType());
			}

			//更新业务表
			flowInterService.sendNextStep(bizIdss[i],FlowConstant.FLOW_STEP_NEXT,saveFlow.getOperateType());
			
			saveFlow.setBizId(bizIdss[i]);
			saveFlow.setBizPid(pid);
			save(saveFlow);
		}
	}
	
	public void finishFlow(FlowAuditInfo flowAuditInfo,String className, String bizIds){
		if (StrTool.isBlankStr(bizIds)){
			return;
		}
		flowAuditInfo.setReportTime(new java.util.Date());
		FlowInterface flowInterService = (FlowInterface)SpringInit.getApplicationContext().getBean(className);
		String [] bizIdss = bizIds.split(";");
		for (int i = 0; i < bizIdss.length; i++) {
			FlowAuditInfo saveFlow = new FlowAuditInfo();
			try {
				BeanUtils.copyProperties(saveFlow, flowAuditInfo);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			String pid = flowInterService.findBizPidByBizId(bizIdss[i]);
			//更新之前的一条审批记录表
			FlowAuditInfo flowAuditInfoTemp = flowAuditInfoDao.findLatestFlowAuditInfoByPid(pid);
			if (flowAuditInfoTemp!=null){
				flowAuditInfoTemp.setFinish(true);
				update(flowAuditInfoTemp);
				//流程的操作类型要跟上一次审批的操作一致
				saveFlow.setOperateType(flowAuditInfoTemp.getOperateType());
			}
			
			

			//更新业务表
			flowInterService.sendNextStep(bizIdss[i],FlowConstant.FLOW_STEP_FINISH,saveFlow.getOperateType());
			
			
			saveFlow.setBizId(bizIdss[i]);
			saveFlow.setBizPid(pid);
			saveFlow.setFinish(true);
			saveFlow.setIdea("结束审批");
			save(saveFlow);
		}
	}

	public List getSelectPeopleListWithOrg() {
		List retList = new ArrayList();
		List orgList = OrgUtils.getOrgList();
		for (Iterator iterator = orgList.iterator(); iterator.hasNext();) {
			Org org = (Org) iterator.next();
			Map map = new HashMap();
			map.put("id", org.getId());
			map.put("pid", org.getParentId());
			map.put("name", org.getOrgName());
			map.put("orgFlag", true);
			retList.add(map);
			
			List userList = adminService.queryByOrgId(org);
			for (Iterator iterator2 = userList.iterator(); iterator2.hasNext();) {
				Admin user = (Admin) iterator2.next();
				Map mapTemp = new HashMap();
				mapTemp.put("id", user.getId());
				mapTemp.put("pid", org.getId());
				mapTemp.put("name", user.getName());
				mapTemp.put("orgFlag", false);
				retList.add(mapTemp);
			}
		}
		
		
		return retList;
	}
	/**
	 * 通过ORG取ORG所属选择对话框人员
	 * @param org
	 * @return
	 */
	public List getSelectPeopleListWithOrg(Org org) {
		if(org == null)return new ArrayList();
		List retList = new ArrayList();
		List orgList = OrgUtils.getOrgList();
		for(int i=0;i<orgList.size();i++){
			Org o = (Org)orgList.get(i);
			if(o != null && !StrTool.isBlankStr(o.getId()) && o.getId().equals(org.getId())){
				Map map = new HashMap();
				map.put("id", org.getId());
				map.put("pid", org.getParentId());
				map.put("name", org.getOrgName());
				map.put("orgFlag", true);
				retList.add(map);
				
				List userList = adminService.queryByOrgId(org);
				for (Iterator iterator2 = userList.iterator(); iterator2.hasNext();) {
					Admin user = (Admin) iterator2.next();
					Map mapTemp = new HashMap();
					mapTemp.put("id", user.getId());
					mapTemp.put("pid", org.getId());
					mapTemp.put("name", user.getName());
					mapTemp.put("orgFlag", false);
					retList.add(mapTemp);
				}
				break;
			}
		}
		return retList;
	}
	/**
	 * 通过ORG对象取所有下级的机构所属人员
	 * @param org
	 * @return
	 */
	public List getSubSelectPeopleListWithOrg(Org org){
		if(org == null)return new ArrayList();
		List<Org> subOrg = OrgUtils.getSubOrgByParentOrgId(org.getId());
		if(subOrg == null || subOrg.isEmpty())return new ArrayList();
		List relist = new ArrayList();
		for(int i =0 ;i<subOrg.size();i++){
			relist.addAll(this.getSelectPeopleListWithOrg(subOrg.get(i)));
		}
		return relist;
	}
	
	@Resource
	private AdminService adminService;

	/***
	 * 根据bizPid获取所有的审批记录
	 * @param bizPid
	 * @return
	 */
	public List queryListByPid(String bizPid) {
		return flowAuditInfoDao.queryListByPid(bizPid);
	}
}
