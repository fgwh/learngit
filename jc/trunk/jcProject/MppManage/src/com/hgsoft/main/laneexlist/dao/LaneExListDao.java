package com.hgsoft.main.laneexlist.dao;

import com.hgsoft.main.carStatistic.entity.CarStatistic;
import com.hgsoft.main.laneexlist.entity.LaneExList;
import com.hgsoft.main.laneexlist.entity.LaneExList2;
import com.hgsoft.security.dao.BaseDao;
import com.hgsoft.security.entity.Admin;
import com.hgsoft.security.entity.Org;
import com.hgsoft.security.util.OrgUtils;
import com.hgsoft.util.DateUtil;
import com.hgsoft.util.Pager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

/**
 * 出口流水dao
 * 
 * @author 郭志强
 * @time 2016/9/9 9:41
 */
@Repository
public class LaneExListDao extends BaseDao<LaneExList> {

	/**
	 * 将数据保存到对应的表中
	 * 
	 * @param laneExList
	 * @param year
	 */
	public void saveLaneExList(LaneExList laneExList, String year) {
	}

	public List queryNameBypath(List flaglist) {
		// 分页、查询SQL、
		List list = new ArrayList<>();  
		List<Object[]> list2 = new ArrayList<>();
		for (int j = 0; j < flaglist.size(); j++) {
			String sql = "select top(1) StationName from tb_flag f left join tb_Station s on f.exStationId=s.StationId and f.RoadId =s.RoadId where f.flagId='"
					+ flaglist.get(j) + "' order by f.startTime desc";
			SQLQuery query = getSession().createSQLQuery(sql);
			list2 = query.list();
			list.addAll(list2);
		}
		return list;
	}

	public List<Object[]> findByLaneExListEntity(Pager pager, LaneExList2 entity, Admin operator) {
		String titleStr = "select ExTime,LaneExSerialNo,ExRoadID,ExStationID,ExLaneID,ExLaneType,ExVehiclePlate,ExVehIdentifyPlate,EnTime,LaneEnSerialNo,EnRoadID,EnStationID,EnLaneID,EnLaneType,EnVehiclePlate,realPath,dbo.f_getSpName(DealStatus) as dealStatus,EnNetRoadID,"
				+ "ExNetRoadID,SquadDate,exCpcsnNo ";
		String orderSql = " order by exTime Desc";

		int totalSize = this.executeCountSqlQuery(parseAllSql2(entity, operator));
		pager.setTotalSize(totalSize);
		return (List<Object[]>) this.findBySql((titleStr + parseAllSql2(entity, operator) + orderSql), pager);
	}
   /*  
    * 查询list 不分页
    */
	public List<Object[]> findByLaneExListEntity2(Pager pager, LaneExList2 entity, Admin operator) {
		String titleStr = "select ExTime,LaneExSerialNo,ExRoadID,ExStationID,ExLaneID,ExLaneType,ExVehiclePlate,ExVehIdentifyPlate,EnTime,LaneEnSerialNo,EnRoadID,EnStationID,EnLaneID,EnLaneType,EnVehiclePlate,realPath,dbo.f_getSpName(DealStatus) as dealStatus";

		String orderSql = " order by exTime Desc";

		int totalSize = this.executeCountSqlQuery(parseAllSql2(entity, operator));
		pager.setTotalSize(totalSize);
		return (List<Object[]>) this.findBySql((titleStr + parseAllSql2(entity, operator) + orderSql), null);
	}
	public String parseAllSql2(LaneExList2 entity, Admin operator) {

		StringBuilder addSql = new StringBuilder(" select * from ");

		addSql.append("tb_laneExList_" + entity.getStartTime().substring(0,4) + " l where 1=1 ");
		if (StringUtils.isNotBlank(entity.getLaneEnSerialNo())) {
			addSql.append(" and laneEnSerialNo='" + entity.getLaneEnSerialNo().trim() + "'");
		}

		if (StringUtils.isNotBlank(entity.getExCpcsnNo())) {
			addSql.append(" and exCPCSnNo = '" + entity.getExCpcsnNo().trim() + "'");
		}

		if (StringUtils.isNotBlank(entity.getLaneExSerialNo())) {
			addSql.append(" and laneExSerialNo = '" + entity.getLaneExSerialNo().trim() + "'");
		}
		if (StringUtils.isNotBlank(entity.getCarNo()) || StringUtils.isNotBlank(entity.getPlateNum())) {
			addSql.append(
					" and ExVehiclePlate like '%" + entity.getPlateNum().trim() + entity.getCarNo().trim() + "%'");
		}
		addSql.append(" and CONVERT(varchar(100),exTime,23) >= '" + entity.getStartTime() + "'");
		addSql.append(" and CONVERT(varchar(100),exTime,23) <= '" + entity.getEndTime() + "'");
		StringBuilder addSqlTwo = new StringBuilder(" from (select * from ( ");
		addSqlTwo.append(addSql).append(") laneTable ").append(") laneTableTwo ");
		addSqlTwo.append("  where 1=1 "); // 连表查询
		return addSqlTwo.toString();
	}
 
	
	
	
}