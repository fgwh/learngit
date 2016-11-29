package com.hgsoft.main.im.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hgsoft.main.im.dao.GroupMemberDao;
import com.hgsoft.main.im.entity.GroupMember;
import com.hgsoft.security.service.BaseService;
import com.hgsoft.util.Pager;

@Service
@SuppressWarnings({ "rawtypes","unchecked" })
public class GroupMemberService extends BaseService<GroupMember>{
	@Resource
	GroupMemberDao groupMemberDao;
	
	
	
	public GroupMemberDao getGroupMemberDao() {
		return groupMemberDao;
	}

	public void setGroupMemberDao(GroupMemberDao groupMemberDao) {
		this.groupMemberDao = groupMemberDao;
	}

	//保存群组成员
	public void saveGroupMember(GroupMember groupMember){
		groupMemberDao.save(groupMember);
	}
	
	/**
	 * 查询群组下的成员
	 * @param id
	 * @param pager
	 * @return
	 */
	public List<Object[]> queryGroupMember(String id, Pager pager){
		String sql = "select * from TB_groupMember where groupId = (select groupId from TB_group where createTime = (select MAX(createTime) from TB_group where createId = "+id+") and createId = " + id +")";
		List<Object[]> list = groupMemberDao.querySqlGroupMember(sql, pager);
		
		return list;
	}
	
	public void deleteGroupMember(String userId, String groupId){
		
System.out.println(userId+"    "+groupId);
		groupMemberDao.deleteGroupMember(userId, groupId);
	}
	
	public List<Object[]> queryGroupMembers(String id, Pager pager){
		String sql = "select * from TB_groupMember where groupId = "+id;
		List<Object[]> list = groupMemberDao.querySqlGroupMember(sql, pager);
		
		return list;
	}
}
