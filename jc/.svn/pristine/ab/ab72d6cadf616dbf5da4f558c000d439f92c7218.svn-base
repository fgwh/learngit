package com.hgsoft.main.integrity.dao;

import org.springframework.stereotype.Repository;

import com.hgsoft.main.integrity.entity.OperationIntegrity;
import com.hgsoft.security.dao.BaseDao;
@Repository
public class OperationIntegrityDao extends BaseDao<OperationIntegrity>{
	public OperationIntegrity getOperationIntegrity(String operationNo){
		String hql = "from OperationIntegrity v where v.operationNo = '"+operationNo+"'";
		OperationIntegrity o=null;
		try{
		o = (OperationIntegrity) this.getSession().createQuery(hql).uniqueResult();
		
	}catch(Exception e){
		e.printStackTrace();
		}
		return o;
	}
}
