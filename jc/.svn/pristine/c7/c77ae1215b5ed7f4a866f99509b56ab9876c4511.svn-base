package com.hgsoft.main.squadMana.dao;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.hgsoft.main.entity.BasicParam;
import com.hgsoft.main.squadMana.entity.Squad;
import com.hgsoft.security.dao.BaseDao;
import com.hgsoft.util.Pager;

/**
 * 工班表操作类
 * 
 * @version 1.0
 * @date 2014-10-16
 * @author wubiao
 *
 */
@Repository
public class SquadDao extends BaseDao<Squad> {
	
    private static Logger log = Logger.getLogger(SquadDao.class);
	
    private List temp;
    
	@Override
    public int executeCountQuery(String sql) {
        if (log.isInfoEnabled()) {
            log.info(sql);
        }
        
        if (sql.indexOf("tb_Squad") >= 0) {
            String sqlCount = "select count(*) " + sql;
            return Integer.parseInt(getSession().createSQLQuery(sqlCount).uniqueResult().toString());  
        	          
        }
        return super.executeCountQuery(sql);  
    }
	
	@Override
    public String getConditions(Squad squad){
		if (squad == null) {
			return "";
		}
		StringBuilder hql = new StringBuilder("");
		if (StringUtils.isNotBlank(squad.getWorkName())) {
			hql.append(" and workName like '%").append(squad.getWorkName()).append("%'");
		}
		
		return hql.toString();
	}
	@Override
	public String setOrders(Squad entity) {
		//"order by b.paramName"
		
		return "order by c.startTime";
	}
	
	public List getAllSquad(Pager pager) {
		String hql = "from Squad ";
		if (null != pager) {
		    Long totalSize = executeCount(hql);
		    pager.setTotalSize(totalSize);
		    temp = executeQuery(pager, hql);
		} else {
		    temp = executeQuery(hql);
		}
		if (temp.isEmpty()) {
		    return null;
		} else {
		    return temp;
		}
	}
	
	/**
	 * 根据工班编号删除工班分配信息
	 * 
	 * @param id
	 */
	public void deleteAdminSquadByWorkNo(int id) {
		
		String sql = "delete from TB_AdminSquad where workNo = " + id;
		
		getSession().createSQLQuery(sql).executeUpdate();
	}
	
	/**
	 * 查询用户的工班信息
	 * 
	 * @param adminId
	 * @return
	 */
	public List getUserSquads(String adminId) {
		String hql = "select * from TB_Squad where workNo in (select workNo from TB_AdminSquad where adminId = '"+adminId+"') order by WorkType";
		Query query = getSession().createSQLQuery(hql);
		List<Object[]> list = query.list();
		
		List<Squad> squads = new ArrayList<Squad>();
		Squad squad;
		String timeStr;
		for(Object[] obj: list) {
			squad = new Squad();
			squad.setWorkNo((Integer)obj[0]);
			squad.setWorkName((String)obj[1]);
			timeStr = (String)obj[2];
			squad.setStartTime(Time.valueOf(timeStr.substring(0, 8)));
			timeStr = (String)obj[3];
			squad.setEndTime(Time.valueOf(timeStr.substring(0, 8)));
			squad.setStartDate((Date)obj[4]);
			squad.setTimeDiff((Integer)obj[5]);
			squad.setRemark((String)obj[6]);
			squad.setWorkType((Integer)obj[7]);
			squads.add(squad);
		}
		
		return squads;
	}
	
	/**
	 * 根据工班名称查询工班信息
	 * 
	 * @param workName
	 * @return
	 */
	public Squad getSquadByName(String workName) {
		String hql = "from Squad where workName = '" + workName + "'";
		
		return (Squad)uniqueResult(hql);
	}
	
	
	public Squad getSquadByWorkNo(int workNo) {
		String hql = "from Squad where workNo = "+workNo;
		
		return (Squad)uniqueResult(hql);
	}
}
