package com.hgsoft.main.im.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hgsoft.main.im.entity.Group;
import com.hgsoft.security.dao.BaseDao;
import com.hgsoft.util.Pager;


@Repository
public class GroupDao extends BaseDao<Group>{

	public List queryGroup(String sql,Pager pager){
        return findBySql(sql, pager);
    }
	
	
	public void deleteGroup(String groupId){
		String sql = "delete from TB_group where groupId = "+groupId;
		getSession().createSQLQuery(sql).executeUpdate();
	}
}
