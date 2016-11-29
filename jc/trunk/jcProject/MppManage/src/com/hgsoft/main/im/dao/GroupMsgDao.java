package com.hgsoft.main.im.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hgsoft.main.im.entity.GroupMsg;
import com.hgsoft.security.dao.BaseDao;
import com.hgsoft.util.Pager;

@Repository
public class GroupMsgDao extends BaseDao<GroupMsg>{
	public List queryGroupMsg(String sql,Pager pager){
        return findBySql(sql, pager);
    }
	
	public void deleteGroupMsg(String groupId){
		String sql = "delete from TB_groupMsg where groupId = "+groupId;
		getSession().createSQLQuery(sql).executeUpdate();
	}
}
