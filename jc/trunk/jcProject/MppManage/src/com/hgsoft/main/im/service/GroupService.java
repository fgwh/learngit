package com.hgsoft.main.im.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Service;

import com.hgsoft.main.dao.BaseDao;
import com.hgsoft.main.im.dao.GroupDao;
import com.hgsoft.main.im.entity.Group;
import com.hgsoft.security.service.BaseService;
import com.hgsoft.util.Pager;


@Service
@SuppressWarnings({ "rawtypes","unchecked" })
public class GroupService extends BaseService<Group>{
	@Resource
	GroupDao groupDao;
	
	
	
	public GroupDao getGroupDao() {
		return groupDao;
	}

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	/**
	 * 保存群组信息
	 * @param group
	 */
	public void saveGroup(Group group){
		groupDao.save(group);
	}
	
	/**
	 * 查询用户所在群组的信息
	 * @param id
	 * @param pager
	 * @return
	 */
	public String queryGroup(String id, Pager pager){
		List listStr = new ArrayList();   //封装转化后的结果集
		
		String  sql = "select d1.groupId,d1.createId, d1.groupName, d1.createTime, COUNT(d1.groupMsgId) msgCount from " +
				" (select c1.groupId, c1.createId, c1.groupName, c1.createTime, c2.groupMsgId from " +
				" (select b2.*, b1.groupMsgId, b1.groupMsg, b1.createTime msgCreateTime, b1.senderId, b1.senderName, b1.contentType from TB_groupMsg as b1 right join " +
				" (select a1.* from TB_group as a1 join (select groupId from TB_groupMember where userId = " + id + ") as a2 on a1.groupId = a2.groupId) as b2 on b1.groupId = b2.groupId) as c1 left join " +
				" TB_groupMsgStatus as c2 on c1.groupMsgId = c2.groupMsgId and c2.isRead = 0 and c2.receiveid = " + id + ") as d1 group by d1.groupId, d1.createId, d1.groupName, d1.createTime order by d1.createTime desc";
		
		List list = groupDao.queryGroup(sql, pager);
		
		for(int i=0;i<list.size();i++){//结果集转换为List
			listStr.add((Object[]) list.get(i));
		}
		
		return JSONArray.fromObject(listStr).toString();
	}
	
	/**
	 * 查询新建的群组
	 * @param id
	 * @return
	 */
	public String queryNewGroup(String id, Pager pager){
		List listStr = new ArrayList();
		String sql = "select *, 0 msgCount from TB_group where createTime = (select MAX(createTime) from TB_group where createId="+id+") and createId = "+id;
		
		List list = groupDao.queryGroup(sql, pager);
		for(int i=0;i<list.size();i++){
			listStr.add((Object[]) list.get(i));
		}
		
		return JSONArray.fromObject(listStr).toString();
	}
	
	public List queryGroupAndLocation(String groupId, String id, Pager pager){
		String sql = "select -1 as num, b2.userId, b2.name, isnull(b2.longitude,0) as longitude, isnull(b2.latitude,0) as latitude, b2.memberId, b2.groupId from sys_admin as b1 join " +
				" (select * from (select * from TB_groupMember where groupId = "+groupId+" and userId = "+id+") as a1 left join TB_StaffLocation as a2 on a1.userId = a2.operatorNo and a2.uploadTime = (select MAX(a3.uploadTime) from TB_StaffLocation as a3 where a3.operatorNo = a1.userId)) as b2 on b1.id = b2.userId" +
				" union select b2.userId as num, b2.userId, b2.name, isnull(b2.longitude,0) as longitude, isnull(b2.latitude,0) as latitude, b2.memberId, b2.groupId from sys_admin as b1 join " +
				" (select * from (select * from TB_groupMember where groupId = "+groupId+") as a1 left join TB_StaffLocation as a2 on a1.userId = a2.operatorNo and a2.uploadTime = (select MAX(a3.uploadTime) from TB_StaffLocation as a3 where a3.operatorNo = a1.userId)) as b2 on b1.id = b2.userId";
System.out.println("queryMemberSql:   "+ sql);
		
		return groupDao.queryGroup(sql, pager);
	}
	
	public void deleteGroup(String groupId){
		groupDao.deleteGroup(groupId);
	}
}
