package com.hgsoft.security.operLog.dao;

import org.springframework.stereotype.Repository;
import com.hgsoft.security.dao.BaseDao;
import com.hgsoft.security.entity.OperLog;

@Repository
public class OperLogDao extends BaseDao<OperLog> {
	
	@Override
    public String getConditions(OperLog operLog){
		if (operLog == null) {
			return "";
		}
		StringBuilder hql = new StringBuilder("");
		/*if (StringUtils.isNotBlank(dicItem.getType())) {
			hql.append(" and type like '%").append(dicItem.getType()).append("%'");
		}
		if (StringUtils.isNotBlank(dicItem.getName())) {
			hql.append(" and name like '%").append(dicItem.getName()).append("%'");
		}
		if (StringUtils.isNotBlank(dicItem.getValue())) {
			hql.append(" and value like '%").append(dicItem.getValue()).append("%'");
		}
		if (null != dicItem.getInnerOrder()&& !dicItem.getInnerOrder().toString().trim().equals("")){
			hql.append(" and innerOrder =").append(dicItem.getInnerOrder());
		}*/
		
		return hql.toString();
	}
	@Override
	public String setOrders(OperLog entity) {
		 
		return "order by c.type asc,c.innerOrder asc";
	}
}
