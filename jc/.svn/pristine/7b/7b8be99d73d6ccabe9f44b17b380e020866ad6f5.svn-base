package com.hgsoft.main.msgPublishManage.service;

import java.util.Date;
import java.util.UUID;
import javax.annotation.Resource;

import com.hgsoft.cxf.common.PropertiesUtil;
import org.springframework.stereotype.Service;
import com.hgsoft.main.msgPublishManage.dao.MsgPublishDao;
import com.hgsoft.main.msgPublishManage.entity.MsgPublish;
import com.hgsoft.security.entity.Admin;
import com.hgsoft.security.service.BaseService;

/**
 * 信息发布服务类
 * 
 * @version 1.0
 * @date 2016-09-10
 * @author liyuyun
 *
 */
@Service
@SuppressWarnings({ "unchecked" })
public class MsgPublishService extends BaseService<MsgPublish> {
	private static final String videoType = PropertiesUtil.getProperty("videoType");


	@Resource
	public void setDao(MsgPublishDao dao) {
		super.setDao(dao);
	}

	public MsgPublishDao getMsgPublishDao() {
		return (MsgPublishDao) this.getDao();
	}
	
	public void add(MsgPublish msg,Admin operator){
		msg.setId(UUID.randomUUID().toString().replace("-", ""));
		msg.setPublishMan(operator.getName());
		msg.setPublishTime(new Date());
		this.save(msg);
	}



	public String getVideoTypeName(){
          String returnName="";
		 switch (videoType) {
             case "1" : returnName="yushi"; break;

         }
        return  returnName;

	}

}
