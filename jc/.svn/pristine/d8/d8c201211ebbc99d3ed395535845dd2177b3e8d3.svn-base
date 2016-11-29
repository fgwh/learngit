package com.hgsoft.main.im.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hgsoft.main.im.entity.GroupMember;
import com.hgsoft.security.dao.BaseDao;
import com.hgsoft.util.Pager;

@Repository
public class GroupMemberDao extends BaseDao<GroupMember>{
	public List queryGroupMember(String sql,Pager pager){
        return findBySql(sql, pager);
    }
	
	public List<Object[]> querySqlGroupMember(String sql, Pager pager){
		return (List<Object[]>) findBySql(sql, pager);
	}
	
	
	public void deleteGroupMember(String userId, String groupId){
		String sql = "delete from TB_groupMember where userId = "+userId+" and groupId = "+groupId;
		getSession().createSQLQuery(sql).executeUpdate();
	}
}
