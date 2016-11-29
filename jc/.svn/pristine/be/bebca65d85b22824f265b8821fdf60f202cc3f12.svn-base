package com.hgsoft.main.msgPublishManage.dao;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import com.hgsoft.main.msgPublishManage.entity.MsgPublish;
import com.hgsoft.security.dao.BaseDao;
import com.hgsoft.util.DateUtil;

/**
 * 信息发布服务类
 * 
 * @version 1.0
 * @date 2016-09-10
 * @author liyuyun
 *
 */
@Repository
public class MsgPublishDao extends BaseDao<MsgPublish> {
	@Override
	public String getConditions(MsgPublish msgPublish) {
		if (msgPublish == null) {
			return "";
		}
		StringBuilder hql = new StringBuilder("");
		Calendar calendar = Calendar.getInstance();
		if(StringUtils.isBlank(msgPublish.getBeginDate()) || StringUtils.isBlank(msgPublish.getEndDate())){
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			msgPublish.setBeginDate((String)DateUtil.fromatDate(calendar.getTime(), DateUtil.PATTERN_STRING_TIME));
			
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			msgPublish.setEndDate((String)DateUtil.fromatDate(calendar.getTime(), DateUtil.PATTERN_STRING_TIME));
		}
		if (StringUtils.isNotBlank(msgPublish.getBeginDate())) {
			hql.append(" and publishTime >='").append(msgPublish.getBeginDate()).append("'");
		}

		if (StringUtils.isNotBlank(msgPublish.getEndDate())) {
			hql.append(" and publishTime <='").append(msgPublish.getEndDate()).append("'");
		}
		
		if (StringUtils.isNotBlank(msgPublish.getPublishMan())) {
			hql.append(" and publishMan =").append(msgPublish.getPublishMan());
		} 

		if (StringUtils.isNotBlank(msgPublish.getTheme())) {
			hql.append(" and theme like '%").append(msgPublish.getTheme()).append("%'");
		}

		return hql.toString();
	}

	@Override
	public String setOrders(MsgPublish msgPublish) {
		return "order by c.publishTime desc";
	}
}
