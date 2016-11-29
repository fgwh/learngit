package com.hgsoft.base.dao;

import com.hgsoft.base.entity.BaseDataEntity;
import com.hgsoft.security.dao.BaseDao;
import com.hgsoft.security.entity.Admin;
import com.hgsoft.util.Pager;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseDataDao<Entity extends BaseDataEntity> extends BaseDao<Entity> {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// 实体类名
	protected String entityName = "";
	

	public abstract Entity newEntity();
	

	public Boolean isExist(Entity entity) {
		String hql = " from "+ entityName +" c" + " where 1=1 " + getConditions(entity);
		return executeCount(hql)>0;
	}
	
	@SuppressWarnings("unchecked")
	public List<Entity> query(Pager pager, Entity entity) {
		String hql = " from "+ entityName +" c" + " where c.latestMark=1" + super.getConditions(entity);		//c.latestMark=1
		if(pager!=null){
			pager.setTotalSize(count("select count(id) " + hql));
		}

		return (List<Entity>) findByHql(hql  + getOrderString(), pager);
	}
	
	public List<Entity> queryWorkedTaskByUserId(Pager pager, Entity entity, Admin operator) {
		String hql = " from "+ entityName +" c"
				+ " where ((c.latestMark=1 and c.flowStatus=999) or c.flowStatus=-999)"
				+ " and c.pid in (select f.bizPid from FlowAuditInfo f where f.receiveUserId='"+operator.getId()+"')"
				+ getConditions(entity);
	
		pager.setTotalSize(count("select count(id) " + hql));
		//logger.debug(hql);
		return (List<Entity>) findByHql(hql  + getOrderString(), pager);
	}
	
	public List queryAllDistinctRecord() {
		String sql = "select distinct routeId,startStake,endStake,pid from "+entityName;
		return queryByHQL(sql, null);
	}

	public List<Entity> queryWorkingTaskByUserId(Pager pager, Entity entity, Admin operator) {
		String hql = " from "+ entityName +" c "
				+ "where c.latestMark=1 and c.flowStatus != 0 and c.flowStatus != 999"
				+ " and c.flowStatus != -999 "
				+ super.getConditions(entity)
				+ " and exists (select 1 from FlowAuditInfo f where c.pid = f.bizPid "
				+ "and finish = 0 and f.receiveUserId='"+operator.getId()+"')";
		
		pager.setTotalSize(count("select count(id) " + hql));
		//logger.debug(hql);
		return (List<Entity>) findByHql(hql  + getOrderString(), pager);
	}
	
	//默认排序
	public String getOrderString() {
		return " order by c.id desc";
	}
	
	public int getMaxFlowStatusByPid(String pid) {
		String hql = "select isnull(max(c.flowStatus),0) as maxcol from "+entityName + " c " +
				" where c.latestMark = 1  and c.pid = '" + pid.trim()+"' "+
				" and (c.flowStatus != 0 and c.flowStatus != 999 and c.flowStatus != -999) ";
		//logger.debug(hql);
		Query q = this.getSession().createQuery(hql);
		Object obj = q.uniqueResult();
		//if(obj==null){return 0;}
		return (Integer)obj;
	}

}
