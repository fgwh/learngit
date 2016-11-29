package com.hgsoft.security.action;

import com.hgsoft.main.entity.DicItem;
import com.hgsoft.security.entity.Org;
import com.hgsoft.security.service.AdminService;
import com.hgsoft.security.service.OrgService;
import com.hgsoft.security.util.OrgUtils;
import com.hgsoft.util.StrTool;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Controller
@Scope("prototype")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class OrgAction extends BaseAction<Org>{
	@Resource
	private AdminService adminService;
	@Resource
	private OrgService orgService;
	private String orgChartStr;
	private HashMap map = new HashMap();// 返回的数据
	private String orgName;
	private String orgCode;
	private String orgID;
	 
	public Org getOrg() {
		return entity;
	}

	public void setOrg(Org org) {
		this.entity = org;
	}
	
	//注入Service
    @Resource
    public void setBaseBridgeService(OrgService service) {
        this.setService(service);
    }
    
	public HashMap getMap() {
		return map;
	}
	
	public String tree(){
		return "tree";
	}
	
	public String index(){
		//setMidByName("组织机构");
		list = OrgUtils.getSubOrgByParentOrgId(operator.getOrg().getId());//getAllOrgListForOrder();				
		if (entity!=null){
			entity = orgService.find(entity.getId());
		}
		return "index";
	}
	
	/*public String edit(){
		if (entity!=null && StrTool.isNotBlank(entity.getId())){
			entity = orgService.find(entity.getId());
		}
		
		return "edit";
	}
	*/
	public String orgChart(){
		orgChartStr = orgService.getOrgLevelChartUiLiString();
		return "orgchart";
	}
	
	/**根据当前机构增加同级机构*/
	public String addSameLevel(){
		entity = orgService.addSameLevelByOrgId(entity.getId());
		map.put("org", entity);
		map.put("addSameLevel", "yes");
		return "success";
		//return index();
	}
	
	/**根据当前机构增加下级机构*/
	public String addLowerLevel(){
		entity = orgService.addLowerLevelByOrgId(entity.getId());
		map.put("org", entity);
		map.put("addLowerLevel", "yes");
		return "success";
		//return index();
	}
	
	/*删除机构*/
	public String delete(){
		//先判断该机构下是否有人员 有则无法删除
		if (entity!=null && StrTool.isNotBlank(entity.getTreeCode())){
			//删除该节点及所有子节点
			orgService.deleteAllChildNode(entity.getTreeCode());
			OrgUtils.init();
		}
		entity = orgService.find(entity.getParentId());
		map.put("delResult","yes");
		return "success";
		//message = "删除成功";
		//return index();
	}
	
	public String isExistStaff(){
		if(orgService.getOrgStaffNum(entity.getTreeCode())){
			map.put("result", "NO");
		}
		
		return "success";
	}
	//获得树的结构
	public String orgTreeForJson(){
		list = OrgUtils.getSubOrgByParentOrgId(getOperator().getOrg().getId());
		//list = orgService.getAllOrgListForOrder();
		return "orgTreeJson";
	}
	
	//获取当前用户的所有子机构
	public String orgTreeForJsonForCurrOperator(){
		list = OrgUtils.getSubOrgByParentOrgId(getOperator().getOrg().getId());
		return "orgTreeJson";
	}
	
	public String orgOwnerPartyTreeForJson(){
		//list = orgService.findConstructPartyOrg();
		list = orgService.findOwnerPartyOrgBtOrgId(operator.getOrg().getId());
		return "orgTreeJson";
	}
	
	public String orgOwnerPartyTreeForJsonHasPower(){
		//list = orgService.findConstructPartyOrg();
		
		list = orgService.findOwnerPartyOrgBtOrgIdHasPower(operator.getOrg().getId());
		return "orgTreeJson";
	}
	
	public String orgConstructPartyTreeForJson(){
		//list = orgService.findConstructPartyOrg();
		list = orgService.findConstructPartyOrgBtOrgId(operator.getOrg().getId());
		return "orgTreeJson";
	}
	
	public String orgSupervisionTreeForJson(){
		list = orgService.findSubSupervisionOrgId(operator.getOrg().getId());
//		list = orgService.findSupervisionOrg();
		return "orgTreeJson";
	}
	public String save(){
		if (entity.getParent() != null && (StrTool.isBlankStr(entity.getParent().getId()))) {
			entity.setParent(null);
		} else {
			entity.setParent(orgService.find(entity.getParent().getId()));
		}

		if (entity.getParent() == null) {
			entity.setOrgLevel(1);
		} else {
			entity.setOrgLevel(entity.getParent().getOrgLevel() + 1);
		}
		entity.setStartTime(getNow());
		entity.setIsDelete(0);
		
		orgService.saveOrUpdate(entity);
		OrgUtils.init();
		map.put("updateResult", "yes");
		map.put("org", entity);
		return "success";
		/*message = "保存成功！";
		return edit();*/
	}
	
	public String adminList(){
		if (entity.getId()!=null){
			entity = orgService.find(entity.getId());
			list = adminService.queryByOrgId(entity);
		}
		return "adminList";
	}
	
	public String adminAdd(){
		return "";
	}
	
	public String update(){
		if (entity.getParent() != null && StrTool.isBlankStr(entity.getParent().getId())) {
			entity.setParent(null);
		} else {
			if (entity.getId().equals(entity.getParent().getId()))
				return edit();
			entity.setParent(orgService.find(entity.getParent().getId()));
		}
		
		Integer level = entity.getOrgLevel();
		if (entity.getParent() == null) {
			entity.setOrgLevel(1);
		} else {
			entity.setOrgLevel(entity.getParent().getOrgLevel() + 1);
		}
		entity.setIsDelete(0);
		
		orgService.update(entity);
		if (level==entity.getOrgLevel()) {
			list = orgService.findByLevel(level);
			updateLevel(list, entity.getId(), entity.getOrgLevel());
		}
		OrgUtils.init();
		return index();
	}
	
	private void updateLevel(List list, String parent, int level) {
		for (int i = 0; i < list.size(); i++) {
			Org org = (Org) list.get(i);
			if (org.getParent() != null
					&& org.getParent().getId().equals(parent)) {
				org.setOrgLevel(level + 1);
				orgService.update(org);
				updateLevel(list, org.getId(), level + 1);
			}
		}
	}
	
	public String isOrgTypeReply(){
		int num = orgService.getOrgCodeNum(entity.getOrgCode());
		map.put("result", num);
		return "success";
	}
	
	
	public String checkOrgName(){
		orgName = orgName.trim();
		List<Object[]> list = orgService.findOrgByOrgName(orgName);
		if(list==null&&list.size()==0){
			map.put("result", "OK");
		}else{
			for(int i=0;i<list.size();i++){
				Object[] obj = list.get(i);
				if(obj[2].toString().equals(orgID)){
					if((obj[1].toString()).equals("0")){
						map.put("result", "OK");
						return "success";
					}
				}else{ 
					if((obj[1].toString()).equals("0")){
						map.put("result", "NO");
						return "success";
					}
				}
			}
			map.put("result", "OK");
		}
		return "success";
	}
	
	public String checkOrg(){
		List<Object[]> list = orgService.findOrgByOrgCode(orgCode);
		if(list==null&&list.size()==0){
			map.put("result", "OK");
		}else{
			for(int i=0;i<list.size();i++){
				Object[] obj = list.get(i);
				if(obj[2].toString().equals(orgID)){
					if((obj[1].toString()).equals("0")){
						map.put("result", "OK");
						return "success";
					}
				}else{
					if((obj[1].toString()).equals("0")){
						map.put("result", "NO");
						return "success";
					}
				}
			}
			map.put("result", "OK");
		}
		return "success";
	}

	public String getOrgChartStr() {
		return orgChartStr;
	}

	public void setOrgChartStr(String orgChartStr) {
		this.orgChartStr = orgChartStr;
	}

	 //从字典中获得前端性别下拉框内容
    public List<DicItem> getTypeList() {
        return getDicItems("orgType");
    }

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgID() {
		return orgID;
	}

	public void setOrgID(String orgID) {
		this.orgID = orgID;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
    
}
