package com.hgsoft.main.im.service;


import com.hgsoft.security.service.BaseService;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings({ "rawtypes","unchecked" })
public class UseMsgService extends BaseService{
/****
	Order[] orders = new Order[] { Order.asc("id") };


	public List<UseMsg> findAll(){
		return getDao().findAll(orders);
	}
	//查询单人聊天的消息
	public List<Object[]> query(Pager pager,ChatUser chatUser) {
		Integer cid = chatUser.getCurrentid();
		Integer gid = chatUser.getGoalid();
		String hql = " from UseMsg as u inner join u.chatUser as c where 1=1 ";
		if(null != cid && !"".equals(cid))
		{
			hql += " and c.currentid="+cid;
		}
		if(null != gid && !"".equals(gid))
		{
			hql += " and c.goalid="+gid;
		}
		if(null != cid && !"".equals(cid))
		{
			hql += " or ( c.currentid="+gid;
		}
		if(null != gid && !"".equals(gid))
		{
			hql += " and c.goalid="+cid+")";
		}
		int totalSize = this.getDao().executeCountQuery(hql.toString());
		pager.setTotalSize(totalSize);
		hql = "select u "+hql;
		hql += "order by u.createTime desc";
		System.out.println(hql);
		return (List<Object[]>)this.getDao().findByHql(hql, pager);
	}
	//查询未读消息数量
	public List<Object[]> queryUnreadMessageNums(ChatUser chatUser) {
		Integer gid = chatUser.getGoalid();
		//SELECT c.goalid,COUNT(*)  FROM TB_useMsg1 as um left join TB_chatUser as c on um.uid = c.uid where um.isread =0 and c.currentid = 5 group by c.goalid;
		String sql = "SELECT c.currentid,COUNT(*)  FROM TB_useMsg1 as um left join TB_chatUser as c on um.uid = c.uid where 1=1 and um.isread =0 "; //" from UseMsg as u inner join u.chatUser as c where 1=1 and u.isread=0";
		if(null != gid && !"".equals(gid))
		{
			sql += " and c.goalid="+gid;
		}
		sql += "  group by c.currentid";
		System.out.println(sql);
		return (List<Object[]>)this.getDao().findBySql(sql, null);
	}
	//查询未读消息
	public List<Object[]> queryUnreadMessage(Pager pager,ChatUser chatUser){
		Integer cid = chatUser.getCurrentid();
		Integer gid = chatUser.getGoalid();
		//SELECT um.msgid,c.currentid ,c.goalid,um.isread FROM TB_useMsg1 as um left join TB_chatUser as c on um.uid = c.uid where 1=1 and um.isread =0 and c.currentid = 5 and c.goalid=32;

		//String sql = " from TB_useMsg1  um left join TB_chatUser  c on um.uid = c.uid where 1=1 and um.isread =0 ";

		StringBuffer sql = new StringBuffer(" from TB_useMsg1  um left join TB_chatUser  c on um.uid = c.uid where 1=1 and (um.isread =0 ");
		if(null != cid && !"".equals(cid))
		{
			sql.append(" and c.currentid=").append(cid);
		}
		if(null != gid && !"".equals(gid))
		{
			sql.append(" and c.goalid=").append(gid);
		}
		if(null != cid && !"".equals(cid))
		{
			sql.append(") or ( um.isread=0 and c.currentid=").append(gid);
		}
		if(null != gid && !"".equals(gid))
		{
			sql.append(" and c.goalid=").append(cid).append(")") ;
		}
		if(null != pager) {
			int totalSize = this.getDao().executeCountSqlQuery(sql.toString());
			pager.setTotalSize(totalSize);
		}
		String executeSql = "SELECT um.msgid,um.sender,um.msg,um.createTime,c.currentid  "+sql.toString();
		executeSql += "order by um.createTime desc";
		System.out.println(executeSql);
		return (List<Object[]>)this.getDao().findBySql(executeSql, pager);
	}
	***/
}

