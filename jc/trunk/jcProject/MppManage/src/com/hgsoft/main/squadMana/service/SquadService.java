package com.hgsoft.main.squadMana.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hgsoft.main.squadMana.dao.SquadDao;
import com.hgsoft.main.squadMana.entity.Squad;
import com.hgsoft.security.entity.Admin;
import com.hgsoft.security.service.BaseService;
import com.hgsoft.util.Order;
import com.hgsoft.util.Pager;
import com.hgsoft.util.StringUtil;

/**
 * 工班管理服务类
 * 
 * @version 1.0
 * @date 2014-10-16
 * @author wubiao
 *
 */
@Service
@SuppressWarnings({"unchecked" })
public class SquadService extends BaseService<Squad> {
	
	Order[] orders = new Order[] { Order.asc("workType") };

	@Resource
    public void setDao(SquadDao dao) {
        super.setDao(dao);
    }
	
	public SquadDao getSquadDao() {
		return (SquadDao) this.getDao();
	}
	
	public List<Squad> findAll(){
		return getDao().findAll(orders);
	}
	
	public List<Squad> querySquad(){
		String hql = "from Squad where squadStatus=1 order by workType";
		return (List<Squad>)this.getSquadDao().findByHql(hql, null);
	}
	
	 public List<Object[]> querySquadList(Pager pager, Object... obj) {
		StringBuffer sql = new StringBuffer(
				" from tb_Squad s where 1 = 1");
		if (obj != null && !StringUtil.isTrimEmpty((String)obj[0])) {
			/*if (obj[0] != null && !obj[0].toString().trim().equals("")) {
				sql.append(" and s.workName like '%").append(
						obj[0].toString().trim()).append("%'");
			}*/
			List<String> value = new ArrayList<String>();
			value.add("'%" + (String)obj[0] + "%'");
			
			List<String> pattern = new ArrayList<String>();
			pattern.add(" and s.workName like ");
			
			sql.append(queryBuilder(value,pattern));
			
		}
		int totalSize = this.getDao().executeCountQuery(sql.toString());
		pager.setTotalSize(totalSize);

		String execSql = "select s.workNo,\n"+
                         "s.workName,\n"+
                         "CONVERT(varchar(5), s.startTime, 114) startTime,\n"+
                         "CONVERT(varchar(5), s.endTime, 114) endTime,\n"+
                         "CONVERT(varchar(10), s.startDate, 120) startDate,\n"+
	                     "(case s.timeDiff when 0 then '不偏移' when -1 then '往后一天'  when 1 then '往前一天' end)timeDiff,\n"+
                         "s.remark,\n"+
                         "s.squadStatus "
	                     +sql.toString() + " order by s.startTime";

		List<Object[]> list = (List<Object[]>) this.getDao().findBySql(execSql,
				pager);
		return list;
	 }
	 
	 public List<Squad> query(Pager pager,Squad squad,Admin operator) {
			
			
			String hql = "from Squad where 1=1";
			String count = "select count(workNo) ";
			
			count = count+hql;
			List<Long> counts = (List<Long>)getDao().findByHql(count, null);
			Long totalSize = (counts == null || counts.isEmpty()) ? 0l : counts.get(0);
			pager.setTotalSize(totalSize);
			
			hql += " order by workName asc";
			
			return (List<Squad>)this.getSquadDao().findByHql(hql, pager);
	}
	 
	/**
	 * 删除工班信息
	 * 
	 * @param squad
	 */
	public void deleteSquad(Squad squad) {
		
		//1.删除工班分配表里的信息
		this.getSquadDao().deleteAdminSquadByWorkNo(squad.getWorkNo());
		//2.删除工班信息
		this.getSquadDao().delete(squad);
	}
	
	/**
	 * 删除所有工班信息
	 */
	public void deleteAllSquad() {
		getSquadDao().updateBySql("delete from tb_squad");
	}
	
	/**
	 * 获得用户的工班信息
	 * 
	 * @param adminId
	 * @return
	 */
	public List getUserSquads(String adminId) {
		
		return getSquadDao().getUserSquads(adminId);
	}
	
	/**
	 * 根据工班名称查询工班信息
	 * 
	 * @param workName
	 * @return
	 */
	public Squad getSquadByName(String workName) {
		
		return getSquadDao().getSquadByName(workName);
	}
	
	public Squad getSquadByWorkNo(int workNo) {
		
		return getSquadDao().getSquadByWorkNo(workNo);
	}
	
	/**
	 * 停用工班
	 * @param id
	 */
	public void updateSquadDisabled(String id) {
		Squad temp = this.find(id);
		if(temp != null)
			temp.setSquadStatus(0);
	}
	
	/**
	 * 启用工班
	 * @param id
	 */
	public void updateSquadEnable(String id) {
		Squad temp = this.find(id);
		if(temp != null)
			temp.setSquadStatus(1);
	}
}
