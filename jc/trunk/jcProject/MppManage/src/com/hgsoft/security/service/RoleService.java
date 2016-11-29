package com.hgsoft.security.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.hgsoft.security.dao.RoleDao;
import com.hgsoft.security.entity.Module;
import com.hgsoft.security.entity.Role;
import com.hgsoft.util.Order;
import com.hgsoft.util.Property;

@Service 
public class RoleService extends BaseService<Role> {

	/** 系统角色类型：检查员 */
	public static final String ROLE_TYPE_INSPECTOR = "收费站检查员";
	
	/** 系统角色类型：站级系统管理员  tollCollector*/
	public static final String ROLE_TYPE_TOLLCOLLECTOR = "tollCollector";
	/** 系统角色类型：班长 stationMonitor */
	public static final String ROLE_TYPE_MONITOR = "stationMonitor";
	/** 系统角色类型：站长 stationMaster */
	public static final String ROLE_TYPE_MASTER = "stationMaster";
	/** 系统角色类型：检查员 stationSupervisor */
	public static final String ROLE_TYPE_SUPERVISOR = "stationSupervisor";
	/** 系统角色类型：站级运营维护管理  ticketmana*/
	public static final String ROLE_TYPE_TICKETMANA = "ticketmana";
	
	/** 系统角色类型：路段管理员  roadAdmin*/
	public static final String ROLE_TYPE_ROADADMIN = "roadAdmin";
	/** 系统角色类型：超级管理员  roadAdministrator*/
	public static final String ROLE_TYPE_ROADADMINISTRATOR = "roadAdministrator";
	/** 系统角色类型：系统管理员  admin*/
	public static final String ROLE_TYPE_ADMIN = "admin";
	/** 系统角色类型：运营维护管理  ticketmana*/
	public static final String ROLE_TYPE_OPERATIONMAINTENANCE = "operationMaintenance";
	@Resource
	public void setDao(RoleDao dao){
		super.setDao(dao);
	}
	
	public boolean nameIsExists(Role role) {
	    
	    if(role == null) {
		return false;
	    }
	    
	    String name = role.getName();
	    
	    if(name == null || name.isEmpty()) {
		return false;
	    }
	    
	    List<Role> roleList = this.getDao().findAll(Order.desc("id"), Property.eq("name", name));
	    
	    String id = role.getId();
	    
	    if(roleList != null && !roleList.isEmpty()) {
		
			if(null != id && !"".equals(id)){
			    for(Role temp : roleList) {
				    if(id.equals(temp.getId())) {
				        return false;
				    }
			    }
			}
			return true;
	    }
	    
	    return false;
	}
	
	public List queryByHQL(String hql){
		return getDao().queryByHQL(hql, null);
	}
	
	public boolean typeIsExists(Role role) {
	    
	    if(role == null) {
	    	return false;
	    }
	    
	    String type = role.getType();
	    	    
	    List<Role> roleList = this.getDao().findAll(Order.desc("id"), Property.eq("type", type));

		String id = role.getId();
	    
	    if(roleList != null && !roleList.isEmpty()) {
		
			if(id != null && !"".equals(id)) {
			    for(Role temp : roleList) {
				    if(id.equals(temp.getId())) {
				        return false;
				    }
			    }
			}
			return true;
	    }
	    
	    return false;
	}

    public void deleteRole(String id) {
        deleteById(id);
        //String hql = "delete from AdminRole where id.role=" + id;
        //getDao().updateByHql(hql);
        String sql = "delete from sys_admin_role where role = '"+id+"'";
        getDao().updateBySql(sql);
    }

    public List<Role> obtainRoles(String id) {
        String hql = "select r from Role r join r.modules m where m.id='"+id+"'";
        return (List<Role>) getDao().findByHql(hql, null);
        //return getDao().findAll(Property.eq("modules.id", id));
    }

	public void updateBySql(String sql) {
		getDao().updateBySql(sql);
	}
	
	//获取本地所有的角色
	public String getAllRoleName(){
		String sql = "select distinct name from sys_role";
		List list = (List) this.getDao().findBySql(sql,null);
		StringBuffer roleName=new StringBuffer("(''");
		Map<String, String> map = new HashMap<String, String>();
		if(list.size() > 0){
			for (Object obj : list) {
				roleName.append(",'").append(obj.toString()).append("'");
			}
		} 
		roleName.append(")");
		return roleName.toString();
	}
	
	public void saveModules(String modules,Role entity) {
		if (modules != null) {
			modules = modules.replace("，", ",").replace(" ", "");
			String[] ids = modules.split(",");
			if (ids != null && ids.length > 0) {
				for (int i = 0; i < ids.length; i++) {
					String j = ids[i];
					if (!StringUtils.isBlank(j) && !"0".equals(j)) {
						Module module = new Module();
						module.setId(j);
						entity.getModules().add(module);
					}
				}
			}
		}
	}
	
	/**
	 * 查询业务相关的角色
	 * @return
	 */
	public List<Role> queryRelatedRoles() {
		String sql = "select name,type from sys_role where type in('"
				+ RoleService.ROLE_TYPE_TOLLCOLLECTOR + "','" + RoleService.ROLE_TYPE_MONITOR + "','" + RoleService.ROLE_TYPE_MASTER + "','" + RoleService.ROLE_TYPE_SUPERVISOR + "','" + RoleService.ROLE_TYPE_TICKETMANA + "')";
	
		List<Object[]> strAryList = (List<Object[]>)getDao().queryBySQL(sql, null);
		List<Role> roles = new ArrayList<Role>();
		Role role;
		for(Object[] strAry: strAryList) {
			role = new Role();
			role.setName((String)strAry[0]);
			role.setType((String)strAry[1]);
			roles.add(role);
		}
		
		return roles.isEmpty() ? null : roles;
	}
	
	
	/**
	 * 根据角色名称获取角色
	 * @return
	 */
	public Map getAllRoleNo(){
		String sql = "select distinct name,id from sys_role";
		List<Object[]> list = (List<Object[]>) this.getDao().findBySql(sql,null);
		Map<String, String> map = new HashMap<String, String>();
		if(list.size() > 0){
			for (Object[] obj : list) {
				map.put(obj[0].toString(), obj[1].toString());
			}
		} 
		return map;
	}
}
