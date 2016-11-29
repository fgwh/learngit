package com.hgsoft.security.dao;

import java.util.List;

import com.hgsoft.main.entity.DicItem;
import com.hgsoft.security.entity.Org;
import com.hgsoft.security.service.OrgService;
import com.hgsoft.security.service.RoleService;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

@Repository
public class OrgDao extends BaseDao<Org> {
	@Override
    public String getConditions(Org org){
		if (org == null) {
			return "";
		}
		 
		return "";
	}
	@Override
	public String setOrders(Org entity) {
		 
		return "";
	}
	/***
	 *  根据treeCode，寻找它同级节点的最大treecode，没有则返回“0”
	 * @param treeCode
	 * @return
	 */
	public String getMaxTreeCodeByParentTreeCode(String treeCode){
		int length = treeCode.length()+4;
		String sql = "select max(treeCode) treeCode from sys_org where treeCode like '"+treeCode+"%' and  LEN(treeCode) = "+ length;
		List list = queryBySQL(sql, null);
		Object obj = list.get(0);
		
		if (obj==null){
			return "0000";
		}else {
			return (String)obj;
		}
	}
	
	/***
	 *  根据treeCode，寻找它下级节点的最大treecode，没有则返回“0”
	 * @param treeCode
	 * @return
	 */
	public String getMaxNextTreeCodeByParentTreeCode(String treeCode){
		int length = treeCode.length()+4;
		String sql = "select max(treeCode) treeCode from sys_org where treeCode like '"+treeCode+"%' and  LEN(treeCode) = "+ length;
		List list = queryBySQL(sql, null);
		Object obj = list.get(0);
	
		if (obj==null){
			return treeCode+"0000";
		}/*else if(treeCode.length() < obj.toString().length()){
			return treeCode;
		}*/
		else {
			return (String)obj;
		}
	}
	
	/**
	 * 根据收费站id查询下级所有的车道
	 * 
	 * @param orgId
	 * @return
	 */
	public List<Org> getAllLaneBystationId(String orgId) {
		String hql = "from Org where orgStatus = 0 and isDelete = 0 and treeCode like (select treeCode from Org where id = '" + orgId + "')+'________' ";
		
		List temp =  findByHql(hql, null);
		if (temp.isEmpty()) {
		    return null;
		} else {
		    return temp;
		}
	}
	
	/**
	 * 获取站编号和站名称
	 * 用户ID
	 * @param id
	 * @return
	 */
	public List<Org> getStationName(String id){
		String hql = "  from Org where orgStatus = 0 and isDelete=0 and id='"+id+"' and orgType = "+ OrgService.ORG_TYPE_STATION;
		List temp =  findByHql(hql, null);
		if (temp.isEmpty()) {
		    return null;
		} else {
		    return temp;
		}
	}
	
	/**
	 * 获取路段号和路段名称
	 * 用户ID
	 * @param id
	 * @return
	 */
	public List<Org> getRoadName(String id){
		String hql = " from Org where orgStatus = 0 and isDelete=0 and  id='"+id+"' and orgType = "+ OrgService.ORG_TYPE_ROAD;
		List temp =  findByHql(hql, null);
		if (temp.isEmpty()) {
		    return null;
		} else {
		    return temp;
		}
	}
	
	/**
	 * 获取用户上级机构信息
	 * treeCode
	 * @param id
	 * @return
	 */
	public List<Org> getUpOrgMsg(String treeCode){
		String hql = " from Org where orgStatus = 0 and isDelete=0 and treeCode = '"+treeCode.substring(0, treeCode.length()-4)+"'";
		List temp =  findByHql(hql, null);
		if (temp.isEmpty()) {
		    return null;
		} else {
		    return temp;
		}
	}
	
	
	/**
	 * 根据收费站id查询下级所有的广场
	 * 
	 * @param orgId
	 * @return
	 */
	public List<Org> getAllSquareBystationId(String orgId) {
		String hql = "from Org where orgStatus = 0 and isDelete = 0 and treeCode like (select treeCode from Org where id = '" + orgId + "')+'____' ";
		
		List temp =  findByHql(hql, null);
		if (temp.isEmpty()) {
		    return null;
		} else {
		    return temp;
		}
	}
	
	/**
	 * 根据父节点查询子节点
	 * @param orgId
	 * @param orgType
	 * @return
	 */
	public List<Org> getAllChildrenStation(String orgId, String orgType) {
		String hql = "from Org where parentId = '"+orgId+"' and isDelete = 0 and orgType='"+orgType+"'";
		
		List temp =  findByHql(hql, null);
		if (temp.isEmpty()) {
		    return null;
		} else {
		    return temp;
		}
	}
}
