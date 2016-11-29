package com.hgsoft.main.dao;


import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import com.hgsoft.main.entity.DicItem;
import com.hgsoft.main.squadMana.entity.Squad;
import com.hgsoft.security.dao.BaseDao;

@Repository
public class DicItemDao extends BaseDao<DicItem> {
	@Override
    public String getConditions(DicItem dicItem){
		if (dicItem == null) {
			return "";
		}
		StringBuilder hql = new StringBuilder("");
		if (StringUtils.isNotBlank(dicItem.getType())) {
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
		}
		
		return hql.toString();
	}
	@Override
	public String setOrders(DicItem entity) {
		 
		return "order by c.type asc,c.innerOrder asc";
	}

}
