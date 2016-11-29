package com.hgsoft.main.integrity.dao;

import org.springframework.stereotype.Repository;

import com.hgsoft.main.integrity.entity.DriverIntegrity;
import com.hgsoft.main.integrity.entity.VehIntegrity;
import com.hgsoft.security.dao.BaseDao;
@Repository
public class DriverIntegrityDao extends BaseDao<DriverIntegrity>{
	 /**
	  * 根据姓名和身份证号查询驾驶员诚信信息
	  * @param driverName
	  * @param driverNo
	  * @return
	  */
	public DriverIntegrity getDriverIntegrity(String driverNo){
		String hql = "from DriverIntegrity v where v.driverNo = '"+driverNo+"'";
		return (DriverIntegrity) this.getSession().createQuery(hql).uniqueResult();
	}
}
