package com.hgsoft.main.dao;

import com.hgsoft.main.entity.BasicParam;
import com.hgsoft.security.dao.BaseDao;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2014/7/1.
 */

@Repository
public class BasicParamDao extends BaseDao<BasicParam> {
	
	private static Logger log = Logger.getLogger(BasicParamDao.class);
	
	@Override
    public int executeCountQuery(String sql) {
        if (log.isInfoEnabled()) {
            log.info(sql);
        }
        
        if (sql.indexOf("TB_Basic_Param") >= 0) {
            String sqlCount = "select count(*) " + sql;
            return Integer.parseInt(getSession().createSQLQuery(sqlCount).uniqueResult().toString());  
        	          
        }
        return super.executeCountQuery(sql);  
    }
	@Override
    public String getConditions(BasicParam basicParam){
		if (basicParam == null) {
			return "";
		}
		StringBuilder hql = new StringBuilder("");
		if (StringUtils.isNotBlank(basicParam.getParamName())) {
			hql.append(" and paramName like '%").append(basicParam.getParamName()).append("%'");
		}
		if (basicParam.getStatus() != null && !basicParam.getStatus().toString().trim().equals("")) {
			hql.append(" and status = ").append(basicParam.getStatus());
		} 
		return hql.toString();
	}
	@Override
	public String setOrders(BasicParam entity) {
		//"order by b.paramName"
		
		 
		return "order by c.paramVal";
	}
}
