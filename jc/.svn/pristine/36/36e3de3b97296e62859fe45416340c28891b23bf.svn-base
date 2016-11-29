package com.hgsoft.main.jcManange.dao;



import java.util.List;

import org.springframework.stereotype.Repository;

import com.hgsoft.main.jcManange.entity.Conditions;
import com.hgsoft.main.jcManange.entity.RoBlackList;
import com.hgsoft.security.dao.BaseDao;

@Repository
public class BlackListDao extends BaseDao<RoBlackList>{
	
	
	
	
	public List plateValidate(Conditions condition){
		String sql="SELECT id FROM tb_roBlackList where VehPlate='"+condition.getVehPlate()+"' and VehPlateColor='"+condition.getVehPlateColor()+"'";
	    return	 getSession().createSQLQuery(sql).list();	    
	}
	
	
	public int recovered(String id){
		String sql="update tb_roBlackList set jfStatus =1 where id= '"+id+"'";		
		return getSession().createSQLQuery(sql).executeUpdate();
	}
	
	public int  upload(String id,String fileName,String uploadTime){
		String sql="update tb_roBlackList set fileName ='"+fileName+"',uploadTime = convert(datetime,'"+uploadTime+"') where id= '"+id+"'";		
		return getSession().createSQLQuery(sql).executeUpdate();
	}
}
