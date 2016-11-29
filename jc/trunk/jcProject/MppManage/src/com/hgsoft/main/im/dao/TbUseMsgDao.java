package com.hgsoft.main.im.dao;

import com.hgsoft.security.dao.BaseDao;
import com.hgsoft.main.im.entity.ChatMessage;
import com.hgsoft.main.im.entity.TbUseMsgEntity;
import com.hgsoft.util.Pager;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by linlin on 2015-3-10-010.
 */
@Repository
public class TbUseMsgDao extends BaseDao<TbUseMsgEntity>{
    public List queryUserAndLocation(String sql,Pager pager){
        return findBySql(sql, pager);
    }
}
