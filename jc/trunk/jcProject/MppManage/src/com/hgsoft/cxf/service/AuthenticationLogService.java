package com.hgsoft.cxf.service;

import com.hgsoft.cxf.dao.AuthenticationLogDao;
import com.hgsoft.main.entity.AuthenticationLog;
import com.hgsoft.security.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Bruce.Zhan on 2014/8/6.
 * 1、保存认证日志信息
 */

@Service
public class AuthenticationLogService extends BaseService<AuthenticationLog> {

    // 打印日志
    private final static Logger log = Logger.getLogger(AuthenticationLogService.class);

    @Resource
    public void setDao(AuthenticationLogDao dao) {
        super.setDao(dao);
    }

    /**
     * Created by Bruce.Zhan on 2014/8/6.
     * 保存认证日志信息
     *
     * @param pojo 认证日志
     * @return boolean true保存成功｜false保存失败
     */
    public boolean saveAuthenticationLog(AuthenticationLog pojo) {

        if (log.isInfoEnabled()) {
            log.info("正在记录认证日志信息。");
        }

        try {

            // 正常情况下正确保存数据，直接返回true
            this.getDao().save(pojo);
            return true;

        } catch (Exception ex) {

            // 当出现异常情况记录异常信息回写日志文件，直接返回false
            log.error(ex);
            return false;
        }
    }
}
