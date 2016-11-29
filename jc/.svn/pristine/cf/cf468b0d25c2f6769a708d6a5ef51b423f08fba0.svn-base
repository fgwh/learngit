package com.hgsoft.security.dao;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import com.hgsoft.security.entity.AdminLogin;
import com.hgsoft.util.DateUtil;
import com.hgsoft.util.StringUtil;

@Repository 
public class AdminLoginDao extends BaseDao<AdminLogin> {
	@Override
    public String getConditions(AdminLogin adminLogin){
		if (adminLogin == null) {
			return "";
		}
		StringBuilder hql = new StringBuilder("");
		 
		if (StringUtils.isNotBlank(adminLogin.getUserName())) {
			hql.append(" and userName like '%").append(adminLogin.getUserName()).append("%'");
        }
         
		Calendar calendar = Calendar.getInstance();
		// 默认的时间范围为当天
		if (StringUtil.isTrimEmpty(adminLogin.getStartQueryDate()) || StringUtil.isTrimEmpty(adminLogin.getStartQueryDate())) {
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
		    
			adminLogin.setStartQueryDate((String)DateUtil.fromatDate(calendar.getTime(), "yyyy-MM-dd HH:mm:ss"));
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			adminLogin.setEndQueryDate((String)DateUtil.fromatDate(calendar.getTime(), "yyyy-MM-dd HH:mm:ss"));
		}
        if (StringUtils.isNotBlank(adminLogin.getStartQueryDate())) { 
        	hql.append(" and convert(DATETIME, loginTime) >= CONVERT(DATETIME, '").append(adminLogin.getStartQueryDate()).append("')");
        }
        if (StringUtils.isNotBlank(adminLogin.getEndQueryDate())) {
        	hql.append(" and convert(DATETIME, loginTime) <= CONVERT(DATETIME, '").append(adminLogin.getEndQueryDate()).append("')");
        }
        String seachId; 
		if(!StringUtil.isTrimEmpty(adminLogin.getRoadNo())){
			if (!StringUtil.isTrimEmpty(adminLogin.getStationNo())) {
				seachId = adminLogin.getStationNo();
			}else if(adminLogin.getOperator().getOrg().getOrgType().equals("4")){
				seachId = adminLogin.getOperator().getOrg().getId();
			}else{
				seachId = adminLogin.getRoadNo();
			}
		}else{
			seachId = adminLogin.getOperator().getOrg().getId(); 
		}
        hql.append("and orgId in").append(this.addSearchCondition(seachId));
        
		return hql.toString();
	}
	@Override
	public String setOrders(AdminLogin entity) {
		 
		return "order by c.loginTime desc";
	}

}
