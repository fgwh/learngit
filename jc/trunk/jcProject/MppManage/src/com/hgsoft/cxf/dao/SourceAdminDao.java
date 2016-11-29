package com.hgsoft.cxf.dao;

import com.hgsoft.main.entity.SourceAdmin;
import com.hgsoft.security.dao.BaseDao;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2014/8/6.
 */

@Repository
public class SourceAdminDao extends BaseDao<SourceAdmin> {

    // 打印日志
    private final static Logger log = Logger.getLogger(SourceAdminDao.class);

    @Override
    public Long executeCount(String hql, Object... values) {
        return super.executeCount(hql, values);
    }
}
