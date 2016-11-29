package com.hgsoft.main.im.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hgsoft.main.im.dao.GroupMsgStatusDao;
import com.hgsoft.main.im.entity.GroupMsgStatus;
import com.hgsoft.security.service.BaseService;

@Service
@SuppressWarnings({ "rawtypes","unchecked" })
public class GroupMsgStatusService extends BaseService<GroupMsgStatus>{
	@Resource
	GroupMsgStatusDao groupMsgStatusDao;
	
	
	public void saveGroupMsgStatus(GroupMsgStatus groupMsgStatus){
		groupMsgStatusDao.save(groupMsgStatus);
	}
	
	public void updateGroupIsRead(String receiveId,String groupId){
		String sql = "update TB_groupMsgStatus set isRead = 1 where statusId in " +
				" (select statusId from (select * from TB_groupMsgStatus where isRead=0 and receiveid = "+receiveId+") as a1 join " +
				" (select * from TB_groupMsg where groupId = "+groupId+") as a2 on a1.groupMsgId = a2.groupMsgId)";
		
		groupMsgStatusDao.updateBySql(sql);
	}
	
	public void updateNewGroupIsRead(String groupMsgId, String receiveId){
		String sql = "update TB_groupMsgStatus set isRead = 1 where groupMsgId = '"+groupMsgId+"' and receiveid = "+ receiveId;
		groupMsgStatusDao.updateBySql(sql);
	}
	
	public void deleteGroupMsgStatus(String groupId){
		groupMsgStatusDao.deleteGroupMsgStatus(groupId);
	}
	/**
	 * 某个用户登录时显示未读消息的数量
	 * @param id
	 * @return
	 */
	public List<Object[]> getIsReadGroupCount(String id){
		StringBuffer sql = new StringBuffer(" from TB_groupMsgStatus where receiveid = '"+id+"'");
		String execSql = "select SUM(case isRead when 0 then 1 else 0 end) readCount\n " +sql.toString();
		List<Object[]> list = (List<Object[]>) groupMsgStatusDao.findBySql(execSql, null);
		
		return list;
	}
}
