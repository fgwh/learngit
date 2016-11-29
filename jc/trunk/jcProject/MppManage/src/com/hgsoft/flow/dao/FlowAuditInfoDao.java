package com.hgsoft.flow.dao;

import com.hgsoft.flow.entity.FlowAuditInfo;
import com.hgsoft.security.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FlowAuditInfoDao extends BaseDao<FlowAuditInfo> {

	/***
	 * 找出上一条审批记录（未完成的审批）
	 * @param pid
	 * @return
	 */
	public FlowAuditInfo findLatestFlowAuditInfoByPid(String pid) {
		String hql = "from FlowAuditInfo c where c.bizPid='"+pid+"' and c.finish=0 order by c.reportTime desc";
		List list = queryByHQL(hql);
		if (list!=null && !list.isEmpty()){
			return (FlowAuditInfo)list.get(0);
		}
		return null;
	}

	/**
	 * 找出同一条审批流程的所有审批记录
	 * @param bizPid
	 * @return
	 */
	public List queryListByPid(String bizPid) {
		String hql = "from FlowAuditInfo c where c.bizPid='"+bizPid+"' order by c.reportTime desc";
		return queryByHQL(hql, null);
	}
	
}
