package com.hgsoft.main.im.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hgsoft.main.im.entity.GroupMsgStatus;
import com.hgsoft.security.dao.BaseDao;
import com.hgsoft.util.Pager;

@Repository
public class GroupMsgStatusDao extends BaseDao<GroupMsgStatus>{
	public List queryGroupMsgStatus(String sql,Pager pager){
        return findBySql(sql, pager);
    }
	
	public void deleteGroupMsgStatus(String groupId){
		String sql = "delete from TB_groupMsgStatus where groupMsgId in (select groupMsgId from TB_groupMsg where groupId =" +groupId + ")";
		getSession().createSQLQuery(sql).executeUpdate();
	}
}
