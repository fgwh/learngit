package com.hgsoft.security.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.hgsoft.cxf.common.PropertiesUtil;
import com.hgsoft.security.entity.Admin;
import com.hgsoft.security.entity.Operator;
import com.hgsoft.security.entity.Org;
import com.hgsoft.security.hibex.AliasToBeanAutoCastResultTransformer;
import com.hgsoft.security.service.RoleService;
import com.hgsoft.util.StrTool;
import com.opensymphony.xwork2.ActionContext;


@Repository 
public class AdminDao extends BaseDao<Admin> {
	
	private static final String operatorTable = PropertiesUtil
			.getProperty("operatorTable");
	private static final String operatorTypeTable = PropertiesUtil
			.getProperty("operatorTypeTable");
	
	private List temp;
	
	
	
	@Override
    public String getConditions(Admin admin){
		if (admin == null) {
			return "";
		}
		StringBuilder hql = new StringBuilder("");
		
		
		if (StringUtils.isNotBlank(admin.getValid())) {
			hql.append(" and valid =").append(admin.getValid());
		}
		if (StringUtils.isNotBlank(admin.getName())) {
			hql.append(" and name like '%").append(admin.getName()).append("%'");
		}
		if (StringUtils.isNotBlank(admin.getUsername())) {
			hql.append(" and username like '%").append(admin.getUsername()).append("%'");
		}
		if (StringUtils.isNotBlank(admin.getStaffNo())) {
			hql.append(" and staffNo like '%").append(admin.getStaffNo()).append("%'");
		}
		String seachId;
		Org org = admin.getOrg();
		Admin operator;
		if (org!=null && !StrTool.isBlankStr(org.getId())){ 
			 seachId = org.getId();
		}else{
			operator = (Admin) ActionContext.getContext().getSession().get("operator");	
			seachId = operator.getOrg().getId();
		}
		
		hql.append(" and orgId in "+this.addSearchCondition(seachId));
		/*if (StringUtils.isNotBlank(dicItem.getValue())) {
			hql.append(" and value like '%").append(dicItem.getValue()).append("%'");
		}
		if (null != dicItem.getInnerOrder()&& !dicItem.getInnerOrder().toString().trim().equals("")){
			hql.append(" and innerOrder =").append(dicItem.getInnerOrder());
		}*/
		
		return hql.toString();
	}
	@Override
	public String setOrders(Admin entity) {
		 
		return "";
	}
	
   
    public Admin getAdminByName(String name){
		Query query = getSession().createQuery("from Admin where username ='"+name+"'");
		return (Admin) query.uniqueResult();		
	}
	
	/**
     * 根据收费站id与角色获得相应人员的员工编号与姓名
     * @param orgId
     * @param roleType
     * @return
     */
    public List<Admin> getUsersByStationAndRole(String orgId,String roleType) {
    
    	String sql = "select a.id,a.staffNo,a.name"
    			+ " from sys_admin a,sys_admin_role ar,sys_role r where a.id = ar.admin and ar.role = r.id"
    			+ " and r.name in (select name from sys_role where type = '" + roleType + "')" 
				+ " and a.valid='1' and a.orgId = '" + orgId + "'";
		
    	List<Object[]> list = getSession().createSQLQuery(sql).list();
    	List<Admin> adminList = new ArrayList<Admin>();
    	Admin admin;
    	for(Object[] objAry: list) {
    		admin = new Admin();
    		admin.setId((String)objAry[0]);
    		admin.setStaffNo((String)objAry[1]);
    		admin.setName((String)objAry[2]);
    		adminList.add(admin);
    	}
    	
    	return adminList.isEmpty() ? null : adminList;
    }
    
    
	/**
	 * 获得所有的检查员的员工编号与姓名
	 * 
	 * @return 
	 */
    public List getAllInspectorList(String orgId,Admin operator) {
    	String sql;
    	if("".equals(orgId) || null == orgId){
    		List<Object> roleNamelist = getUsersRoleNameListByOperator(operator);
    		if(roleNamelist.size()==1){
    			sql = "select a.staffNo," +
	    				"a.name" +
	    				" from sys_admin a,sys_admin_role ar,sys_role r ,sys_org og where og.id=a.orgId and  a.id = ar.admin and ar.role = r.id and  a.id ='"+operator.getId()+"'";
    		}else{
	    		sql = "select a.staffNo," +
	    				"a.name" +
	    				" from sys_admin a,sys_admin_role ar,sys_role r ,sys_org og where og.id=a.orgId and  a.id = ar.admin and ar.role = r.id and  og.id in ";
	    		sql += this.addSearchCondition(operator.getOrg().getId());
    		}
    		
    	}else{
		  sql = "select a.staffNo," +
				"a.name" +
				" from sys_admin a,sys_admin_role ar,sys_role r ,sys_org og where og.id=a.orgId and  a.id = ar.admin and ar.role = r.id and  og.id='"+orgId+"'";
    	}
    	 
		temp = findBySql(sql, null);
		if (temp.isEmpty()) {
		    return null;
		} else {
		    return temp;
		}
    }
    
    /**
	 * 获得所有的检查员的员工编号与姓名
	 * 
	 * @return 
	 */
    public List getInspectorList(String id) {
    	String sql;
    	if("".equals(id) || null == id){
    		sql = "select a.staffNo," +
    				"a.name" +
    				" from sys_admin a,sys_admin_role ar,sys_role r ,sys_org og where og.id=a.orgId and  a.id = ar.admin and ar.role = r.id  ";
    	}else{
		  sql = "select a.staffNo," +
				"a.name" +
				" from sys_admin a,sys_admin_role ar,sys_role r ,sys_org og where og.id=a.orgId and  a.id = ar.admin and ar.role = r.id and og.id='"+id+"'";
    	}
    	 
		temp = findBySql(sql, null);
		if (temp.isEmpty()) {
		    return null;
		} else {
		    return temp;
		}
    }
    
    /**
     * 根据操作员获取角色名list
     * @param operator
     * @return
     */
    public List<Object> getUsersRoleNameListByOperator(Admin operator) {
    	String sql = "select r.name from sys_admin a,sys_admin_role ar,sys_role r where a.id = ar.admin and ar.role = r.id"
    			+ " and a.id = '"+operator.getId()+"'";
    	List<Object> list = getSession().createSQLQuery(sql).list();
    	return list;
    }
    
    /**
     * 根据操作员获取角色类型list
     * @param operator
     * @return
     */
    public List<Object> getUsersRoleTypeListByOperator(Admin operator) {
    	String sql = "select r.type from sys_admin a,sys_admin_role ar,sys_role r where a.id = ar.admin and ar.role = r.id"
    			+ " and a.id = '"+operator.getId()+"'";
    	List<Object> list = getSession().createSQLQuery(sql).list();
    	return list;
    }
    
    public List getStaffListByStation(String stationNo) {
    	//String sql = "select id,name from sys_admin where squareNo in (select squareNo from Tb_Square where StationNo = " + stationNo + ")";
    	String hql = "From Admin where squareNo in (select squareNo from Square where StationNo = " + stationNo + ")";
    	
    	temp = findByHql(hql, null);//findBySql(sql, null);
		if (temp.isEmpty()) {
		    return null;
		} else {
		    return temp;
		}
    }

	public String queryOrgByname(String username) {
		String hql = "From Admin where username = '" +username +"'";// TODO Auto-generated method stub
		/*String hql = "From sys_admin where username in (select orgId from sys_admin where username = " + username + ")";*/
		List<Admin> list= (List<Admin>) findByHql(hql, null);
		if(list.size()>0){
			return list.get(0).getOrg().getId();
		}
		return null;
	}

	public String queryOrgBylevel(int i) {
		String hql = "From Admin where orgid in (select id From Org where orgLevel = "+i+")";// TODO Auto-generated method stub
		List<Admin> list= (List<Admin>) findByHql(hql, null);
		if(list.size()>0){
			return list.get(0).getOrg().getId();
		}
		return null;
	}
	
	
	public void saveOperator(Operator operator){
		getSession().save(operator);
	}
	
	
	public List<Operator> getOperatorBySql(String sql){
		return getSession().createSQLQuery(sql).setResultTransformer(new AliasToBeanAutoCastResultTransformer(Operator.class)).list();
	}
}
