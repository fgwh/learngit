package com.hgsoft.security.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import com.hgsoft.main.service.BasicParamService;
import com.hgsoft.main.squadMana.entity.Squad;
import com.hgsoft.main.squadMana.service.SquadService;
import com.hgsoft.security.dao.AdminDao;
import com.hgsoft.security.entity.Admin;
import com.hgsoft.security.entity.Module;
import com.hgsoft.security.entity.Operator;
import com.hgsoft.security.entity.Org;
import com.hgsoft.security.entity.Role;
import com.hgsoft.util.CacheUtil;
import com.hgsoft.util.DateUtil;
import com.hgsoft.util.Order;
import com.hgsoft.util.Pager;
import com.hgsoft.util.Property;
import com.hgsoft.util.StrTool;

@Service
@SuppressWarnings({ "rawtypes","unchecked" })
public class AdminService extends BaseService<Admin> {
	Order[] orders = new Order[] { Order.asc("staffNo") };
	
	/**用户登录状态，在线：1*/
	public static final String USER_STATE_ONLINE = "1";
	/**用户登录状态，离线：0*/
	public static final String USER_STATE_OFFLINE = "0";
	
	@Resource
	private OrgService orgService;
	
	
	@Resource
	private SquadService squadService;
	
	@Resource
	private BasicParamService basicParamService;
	
	
	public void updateAdmin4Disabled(String id)
	{
		Admin temp = this.find(id);
		if(temp != null)
			temp.setValid("0");
	}
	
	public void updateAdmin4Enable(String id)
	{
		Admin temp = this.find(id);
		if(temp != null)
			temp.setValid("1");
	}
/*<-----------------王苏------------------------>	*/
	public List queryByHQL(){
		String hql = "from Admin";
		return getDao().queryByHQL(hql, null);
	}
	
	public Admin check(Admin admin) {
		String username = admin.getUsername().trim();
		String password = admin.getPassword().trim();
		List<Admin> list = getDao().findAll(Order.asc("id"), Property.eq("username", username), Property.eq("password", password));
		if (list.size() > 0)
			return (Admin) list.get(0);
		else
			return null;
	}

	public List<Admin> findAllMajor() {
		return getDao().findAll(Order.asc("name"), Property.isNotEmpty("majors"));
	}

	public String getFunctions(Admin admin) {
		String functions = "";
		Set roles = admin.getRoles();
		if (!roles.isEmpty()) {
			Iterator it = roles.iterator();

			while (it.hasNext()) {
				Role role = (Role) it.next();
				Set modules = role.getModules();
				if (!modules.isEmpty()) {
					Iterator mit = modules.iterator();
					while (mit.hasNext()) {
						Module module = (Module) mit.next();
						functions = functions + ";" + module.getUrl() + ";" + module.getFunctions();
					}
				}

			}
		}
		return functions;
	}

	/**
	 * 总审核人数
	 * 
	 * @return
	 */
	public Integer checkCount() {
		List list = this.getDao().findAll(Order.asc("id"), new Property[] { Property.isNotEmpty("majors") });
		if (list.size() > 0)
			return list.size();
		else
			return 0;
	}

	/**
	 * 根据审核id获取审核人员
	 * 
	 * @param checkId
	 * @return
	 */
	public Admin findAdminByCheckId(Integer checkId) {
		List<Admin> list = this.getDao().findAll(Order.asc("id"), Property.eq("id", checkId));
		if (list.size() > 0) {
			return list.get(0);
		} else
			return null;
	}

	@Resource
	public void setDao(AdminDao dao) {
		super.setDao(dao);
	}

	public AdminDao getAdminDao() {
		return (AdminDao) this.getDao();
	}
	
	
	public List<Admin> query(Pager pager,Admin admin,Admin operator) {
		String valid = admin.getValid();
		String name = admin.getName();
		String username = admin.getUsername();
		String staffNo = admin.getStaffNo();
		Org org = admin.getOrg();
		String hql = "from sys_admin where 1=1";
		String count = "select count(id) ";
		if(null != valid && !"".equals(valid))
		{
			hql += " and valid="+valid.trim();
		}
		if(null != name && !"".equals(name))
		{
			hql += " and name like '%"+name.trim()+"%'";
		}
		if(null != username && !"".equals(username))
		{
			hql += " and username like '%"+username.trim()+"%'";
		}
		if(null != staffNo && !"".equals(staffNo))
		{
			hql += " and staffNo like '%"+staffNo.trim()+"%'";
		}
		
		String seachId  ;
		if (org!=null && !StrTool.isBlankStr(org.getId())){ 
			 seachId = org.getId();
		}else{
			seachId = operator.getOrg().getId();
		}
		
		hql += " and orgId in "+this.getDao().addSearchCondition(seachId);
		
		List<Integer> counts = (List<Integer>)getDao().findBySql("select count(id) "+hql, null);
		 
		int a = counts.get(0);
		pager.setTotalSize(a);

		hql = "select * "+hql;
		return (List<Admin>)this.getAdminDao().findBySql(hql, pager,Admin.class);
	}
	
	/**
	 * 根据条件查询用户,不分页
	 * @return
	 */
	public List<Admin> queryAll(Admin admin) {
		
		String name = admin.getName();
		String username = admin.getUsername();
		String staffNo = admin.getStaffNo();
		
		String hql = "from Admin where 1=1";
		if(null != name && !"".equals(name))
		{
			hql += " and name like '%"+name.trim()+"%'";
		}
		if(null != username && !"".equals(username))
		{
			hql += " and username like '%"+username.trim()+"%'";
		}
		if(null != staffNo && !"".equals(staffNo))
		{
			hql += " and staffNo like '%"+staffNo.trim()+"%'";
		}
		
		hql += "order by id desc";
		
		return (List<Admin>)this.getAdminDao().findByHql(hql, null);
	}

	public boolean usernameIsExists(Admin admin) {

		if (admin == null) {
			return false;
		}

		String name = admin.getUsername();

		if (name == null || name.isEmpty()) {
			return false;
		}

		List<Admin> adminList = this.getDao().findAll(Order.desc("id"), Property.eq("username", name));

		String id = admin.getId();

		if (adminList != null && !adminList.isEmpty()) {

			if (id != null) {
				for (Admin temp : adminList) {
					if (id.equals(temp.getId().toString())) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}

	public boolean staffNoIsExists(Admin admin) {

		if (admin == null) {
			return false;
		}

		String staffNo = admin.getStaffNo();

		if (staffNo == null || staffNo.isEmpty()) {
			return false;
		}
		
		if(admin.getId() == null ){
			admin.setId("-1");
		}

		String hql = "select count(id) from Admin where valid = 1 and staffNo='"+admin.getStaffNo()+"' and id <> '" + admin.getId() + "'";
		
		List<Long> adminList = (List<Long>)this.getDao().findByHql(hql, null);

		if (adminList != null && !adminList.isEmpty() && (long)adminList.get(0)>0l) {
			return true;
		}
		return false;
	}

	public boolean isExistsByStaffNoAndName(Admin admin) {
		if (null == admin || admin.getStaffNo() == null || admin.getName() == null) {
			return false;
		}
		List list = getDao().findAll(Order.asc("id"), Property.eq("staffNo", admin.getStaffNo()), Property.eq("name", admin.getName()));
		if (list != null && list.size() > 0) {
			Admin tempAdmin = (Admin) list.get(0);
			if (admin.getName().equals(tempAdmin.getName()) && admin.getStaffNo().equals(tempAdmin.getStaffNo())) {
				return true;
			}
		}
		return false;
	}
	
	
/*	public void initInspection(){		
		Map<String, String> inspectorMap = new HashMap<String, String>();

		List<Object[]> inspectorList = getAdminDao().getInspectorList(null);// 获得检查员信息

		if (inspectorList.size() != 0) {
			StringBuffer temp = new StringBuffer();
			for (Object[] inspector : inspectorList) {
				temp.append(inspector[0]).append(",").append(inspector[1]).append(";");
			}

			for (Object[] admin : inspectorList) {
				inspectorMap.put(admin[0].toString(), admin[1].toString());
			}

			CacheUtil.setInspectorList(temp.substring(0, temp.length() - 1));

			CacheUtil.setStaffNoNameMap(inspectorMap);
		}
	}*/
	
	/**
	 * 获得所有的检查员的员工编号与姓名
	 * 
	 * @return
	 * 
	 */
    public List getAllInspectorList(String orgId,Admin operator) {
    	
    	return getAdminDao().getAllInspectorList(orgId,operator);
    }
    
    /**
     * 获取机构所有收费站检查员
     * @param orgId
     * @return
     */
    public List getInspectorList(String id){
    	return getAdminDao().getInspectorList(id);
    }
	
    /**
     * 根据收费站获得收费站的所有人员
     * 
     * @return
     */
    public List getStaffListByStation(String stationNo) {
    	
    	return getAdminDao().getStaffListByStation(stationNo);
    }

	public void updateBySql(String sql) {
		 getAdminDao().updateBySql(sql);
	}

	public void getIdByName(String name) {
		getDao().findBySql("select id from tb_admin where ", null);
		
	}
	
	public void deleteOperator(){
		String sql="delete from Operator";
		getDao().updateByHql(sql);
	}
	 
	public List<Admin> queryByOrgId(Org org) {
		String hql = "from Admin where 1=1";
		if (org!=null && org.getId()!=null){
			hql += " and org.id = '"+org.getId()+"'";
		}

		hql += "order by id desc";
		return (List<Admin>)this.getAdminDao().findByHql(hql,null);
	}
	
	/**
	 * 根据收费站id与角色获得相应人员的员工编号与姓名
	 * @param orgId
	 * @return
	 */
	public List<Admin> getUsersByStationAndRole(String orgId,String roleType) {
		
		return getAdminDao().getUsersByStationAndRole(orgId,roleType);
	}

	 /**
     * 根据操作员获取角色list
     * @param operator
     * @return
     */
    public List<Object> getUsersRoleNameListByOperator(Admin operator) {
    	return getAdminDao().getUsersRoleNameListByOperator(operator);
    }
	
    /**
     * 根据操作员获取角色list
     * @param operator
     * @return
     */
    public List<Object> getUsersRoleTypeListByOperator(Admin operator) {
    	return getAdminDao().getUsersRoleTypeListByOperator(operator);
    }
    
	public String queryOrgByname(String username) {
		// TODO Auto-generated method stub
		return getAdminDao().queryOrgByname(username);
	}

	public String queryOrgBylevel(int i) {
		// TODO Auto-generated method stub
		return getAdminDao().queryOrgBylevel(i);
	}
	
	/**
	 * 人员Map<人员编号，人员名称>
	 * @return
	 */
	public Map<String, String> getStaffNoNameMap() {
		Map<String, String> staffNoNameMap = new HashMap<String, String>();
		List<Admin> list = getAdminDao().findAll(orders);
		if (null != list) {
			for (Admin admin : list) {
				staffNoNameMap.put(admin.getStaffNo(), admin.getName());
			}
		}
		return staffNoNameMap;
	}
	
	
	public void saveOperator(Operator operator){
		getAdminDao().saveOperator(operator);
	}
	
	public List<Operator> getAddOperator(){
		String sql="select distinct * from sys_operator o where not exists(select staffNo from sys_admin a where a.staffNo=o.staffNo )";
		return getAdminDao().getOperatorBySql(sql);
	}
	
	public List<Operator> getUpdateOperator(){
		String sql="select distinct d.id,d.password,o.staffNo,o.name,o.registerTime,o.valid,o.orgId from sys_operator o inner join sys_admin d on d.staffNo=o.staffNo where not exists(select staffNo from sys_admin a "
				+ "where a.staffNo=o.staffNo  and a.valid=o.valid and a.orgId=o.orgId)";
		return getAdminDao().getOperatorBySql(sql);
	}
	
}
