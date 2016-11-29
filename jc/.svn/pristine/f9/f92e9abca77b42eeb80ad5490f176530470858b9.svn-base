package com.hgsoft.main.integrity.dao;

import org.springframework.stereotype.Repository;

import com.hgsoft.main.integrity.entity.VehIntegrity;
import com.hgsoft.security.dao.BaseDao;
@Repository
public class VehIntegrityDao extends BaseDao<VehIntegrity>{
	/**
	 * 根据车牌，车牌颜色查询车辆诚信数据
	 * @param vehPlate
	 * @param vehPlateColor
	 * @return
	 */
	public VehIntegrity getVehIntegrity(String vehPlate,String vehPlateColor){
		//String sql ="select * from tb_vehIntegrity v where v.vehPlate='"+vehPlate+"' and v.vehPlateColor='"+vehPlateColor+"'";
		String hql = "from VehIntegrity v where v.vehPlate='"+vehPlate+"' and v.vehPlateColor = '"+vehPlateColor+"'";
		return (VehIntegrity) this.getSession().createQuery(hql).uniqueResult();
	}
}
