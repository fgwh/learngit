package com.hgsoft.cxf.service;

import com.hgsoft.cxf.dao.CallWebServiceLogDao;
import com.hgsoft.main.entity.CallWebServiceLog;
import com.hgsoft.security.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Bruce.Zhan on 2014/8/7.
 */

@Service
public class CallWebServiceLogService extends BaseService<CallWebServiceLog> {

    // 打印日志
    private final static Logger log = Logger.getLogger(CallWebServiceLogService.class);

    @Resource
    public void setDao(CallWebServiceLogDao dao) {
        super.setDao(dao);
    }

}
