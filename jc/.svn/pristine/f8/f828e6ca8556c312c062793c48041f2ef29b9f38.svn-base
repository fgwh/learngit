package com.hgsoft.main.service;

import com.hgsoft.main.dao.LoginLogDao;
import com.hgsoft.security.entity.Admin;
import com.hgsoft.security.entity.AdminLogin;
import com.hgsoft.security.service.BaseService;
import com.hgsoft.util.Pager;
import com.hgsoft.util.StringUtil;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/7/1.
 */

@Service
@SuppressWarnings({ "rawtypes","unchecked" })
public class LoginLogService extends BaseService<AdminLogin> {

    @Resource
    public void setDao(LoginLogDao dao) {
        super.setDao(dao);
    }

    public List<AdminLogin> queryLoginLogList(Pager pager,Admin operator,Object... obj) {
        StringBuffer sql = new StringBuffer(" from TB_Admin_Login l where 1 = 1");
        List<String> value = new ArrayList<String>();
		List<String> pattern = new ArrayList<String>();
        if (obj != null) {
            if (obj[0] != null && !obj[0].toString().trim().equals("")) {
                sql.append(" and l.userName like '%").append(obj[0].toString().trim()).append("%'");
            }
            if (obj[1] != null && !obj[1].toString().trim().equals("")) {
                sql.append(" and convert(varchar, l.loginTime, 120) = '").append(obj[1].toString().trim()).append("'");
            }
            if (obj[2] != null && !obj[2].toString().trim().equals("")) {
            	//convert(date, squadDate) <= CONVERT(date, '" + ((String) obj[4]).trim() + "')
                sql.append(" and convert(DATETIME, l.loginTime) >= CONVERT(DATETIME, '").append(obj[2].toString().trim()).append("')");
            }
            if (obj[3] != null && !obj[3].toString().trim().equals("")) {
                sql.append(" and convert(DATETIME, l.loginTime) <= CONVERT(DATETIME, '").append(obj[3].toString().trim()).append("')");
            }
            String seachId; 
			if(!StringUtil.isTrimEmpty((String) obj[5])){
				if (!StringUtil.isTrimEmpty((String) obj[4])) {
					seachId = obj[4].toString();
				}else if(operator.getOrg().getOrgType().equals("4")){
					seachId = operator.getOrg().getId();
				}else{
					seachId = obj[5].toString();
				}
			}else{
				seachId = operator.getOrg().getId(); 
			}
			
            pattern.add(" and l.orgId in "); 
			value.add(this.getDao().addSearchCondition(seachId));
			
		
			sql.append(queryBuilder(value, pattern));
        }
        int totalSize = this.getDao().executeCountQuery(sql.toString());
        pager.setTotalSize(totalSize);
        String execSql = "select l.userName, convert(varchar, l.loginTime, 120) loginTime, l.ip, (case l.status when 1 then '成功' when 0 then '失败' end) status" + sql.toString() + " order by l.loginTime desc";
        
        return (List<AdminLogin>)this.getDao().findBySql(execSql, pager);
    }
}
