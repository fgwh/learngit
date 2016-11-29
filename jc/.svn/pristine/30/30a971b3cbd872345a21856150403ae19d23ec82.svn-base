package com.hgsoft.main.dao;

import com.hgsoft.security.dao.*;
import com.hgsoft.security.entity.AdminLogin;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2014/7/1.
 */

@Repository
public class LoginLogDao extends com.hgsoft.security.dao.BaseDao<AdminLogin> {

    private static Logger log = Logger.getLogger(LoginLogDao.class);

    @Override
    public int executeCountQuery(String sql) {
        if (log.isInfoEnabled()) {
            log.info(sql);
        }
        if (sql.indexOf("AdminLogin") >= 0) {
            return super.executeCountQuery(sql);
        }
        String sqlCount = "select count(*) " + sql;
        return Integer.parseInt(getSession().createSQLQuery(sqlCount).uniqueResult().toString());
    }
}
