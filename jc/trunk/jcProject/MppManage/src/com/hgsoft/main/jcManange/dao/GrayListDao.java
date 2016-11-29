package com.hgsoft.main.jcManange.dao;

import com.hgsoft.main.jcManange.entity.Conditions;
import com.hgsoft.main.jcManange.entity.RoBlackList;
import com.hgsoft.main.jcManange.entity.RoGrayList;
import com.hgsoft.security.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class GrayListDao extends BaseDao<RoGrayList>{
	
	public void audit(String id,String opinion,String status){
		String sql=" update tb_roGrayList set opinion ='"+opinion+ "',status="+status+" where id='"+id+"'";		
		getSession().createSQLQuery(sql).executeUpdate();
	}
	
	
	public void saveBlackList(RoBlackList roBlackList){
		getSession().save(roBlackList);
	}
	
	public void upload(String id,String fileName,String uploadTime){
		String sql="update tb_roGrayList set fileName ='"+fileName+"',uploadTime = convert(datetime,'"+uploadTime+"') where id= '"+id+"'";		
		getSession().createSQLQuery(sql).executeUpdate();
	}

	public List plateValidate(Conditions condition){
		String sql="SELECT id FROM tb_roGrayList where VehPlate='"+condition.getVehPlate()+"' and VehPlateColor='"+condition.getVehPlateColor()+"'";
	    return	 getSession().createSQLQuery(sql).list();	    
	}
	
	/*public void deleteByAuto(){
		String sql="delete  from tb_roGrayList  where DATEDIFF(MONTH, tb_roGrayList.UpdateTime,getdate()) >=6 ";
		getSession().createSQLQuery(sql).executeUpdate();
	}*/
	
	/**
	 * 通过名字查询用户组织机构
	 * 
	 */

		public String getOrgCode(String name){
			String sql="SELECT orgCode FROM sys_org where id in (SELECT orgId FROM sys_admin where username='"+name+"')";
			return getSession().createSQLQuery(sql).getQueryString();
			
		}
		
		
}