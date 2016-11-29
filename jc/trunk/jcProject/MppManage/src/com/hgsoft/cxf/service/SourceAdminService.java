package com.hgsoft.cxf.service;

import com.hgsoft.cxf.dao.SourceAdminDao;
import com.hgsoft.main.entity.SourceAdmin;
import com.hgsoft.security.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Bruce.Zhan on 2014/8/6.
 * 1、根据参数查询所需要的数据，有就返回true，反之false
 */

@Service
public class SourceAdminService extends BaseService<SourceAdmin> {

    // 打印日志
    private final static Logger log = Logger.getLogger(SourceAdminService.class);

    @Resource
    private SourceAdminDao sourceAdminDao;

    /**
     * Created by Bruce.Zhan on 2014/8/6
     * 根据参数查询所需要的数据，有就返回true，反之false
     *
     * @param obj 参考包括（sourceSystemId｜userId｜userName｜password）
     * @return boolean 返回真假值
     */
    public boolean isNotEmpty(Object... obj) {

        if (log.isInfoEnabled()) {
            log.info("正在获取接口调用，所需验证。");
        }

        // 参数为空或者参数个数不够不进操作，直接返回false
        if (obj != null && obj.length == 4) {
            String hql = " from SourceAdmin t where t.status = 1 and t.sourceSystemId = ? and t.userId = ? and t.userName = ? and t.password = ?";
            Long count = this.sourceAdminDao.executeCount(hql, obj);
            return count == 1 ? true : false;
        }
        return false;
    }
}
