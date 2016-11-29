package com.hgsoft.main.im.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Service;

import com.hgsoft.main.im.dao.GroupMsgDao;
import com.hgsoft.main.im.entity.GroupMsg;
import com.hgsoft.security.service.BaseService;
import com.hgsoft.util.Pager;

@Service
@SuppressWarnings({ "rawtypes","unchecked" })
public class GroupMsgService extends BaseService<GroupMsg>{
	@Resource
	GroupMsgDao groupMsgDao;
	
	
	public GroupMsgDao getGroupMsgDao() {
		return groupMsgDao;
	}

	public void setGroupMsgDao(GroupMsgDao groupMsgDao) {
		this.groupMsgDao = groupMsgDao;
	}

	/**
	 * 保存信息
	 * @param groupMsg
	 */
	public void saveGroupMsg(GroupMsg groupMsg){
		groupMsgDao.save(groupMsg);
	}
	
	/**
	 * 查询群组下面的历史消息 
	 * @param id
	 * @param pager
	 * @return
	 */
	public String queryGroupMsg(String id, Pager pager){
		List listStr = new ArrayList();
		String sqlCount = "select count(*) from TB_groupMsg where groupId=" + id;
		List temp = groupMsgDao.queryGroupMsg(sqlCount, null);
		pager.setTotalSize(Integer.parseInt(temp.get(0).toString()));
		
		String sql = "select groupMsg, CONVERT(varchar, createTime, 120)+DATENAME(WEEKDAY, createTime) as createTime, senderId, senderName, contentType, groupId from TB_groupMsg where groupId="+id+" order by createTime desc";
		List list = groupMsgDao.queryGroupMsg(sql, pager);
		
		for(int i=0;i<list.size();i++){//结果集转换为List
			listStr.add((Object[]) list.get(i));
		}
		
		return JSONArray.fromObject(listStr).toString();
	}
	
	public void deleteGroupMsg(String groupId){
		groupMsgDao.deleteGroupMsg(groupId);
	}
}
