package com.hgsoft.cxf.dao;

import com.hgsoft.main.entity.CallWebServiceLog;
import com.hgsoft.security.dao.BaseDao;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * Created by Bruce.Zhan on 2014/8/7.
 */

@Repository
public class CallWebServiceLogDao extends BaseDao<CallWebServiceLog> {

    // 打印日志
    private final static Logger log = Logger.getLogger(CallWebServiceLogDao.class);
}
