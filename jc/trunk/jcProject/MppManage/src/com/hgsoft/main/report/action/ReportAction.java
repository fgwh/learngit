
package com.hgsoft.main.report.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hgsoft.main.jcManange.entity.Conditions;
import com.hgsoft.main.jcManange.entity.ProBlackList;
import com.hgsoft.main.jcManange.entity.ProGrayList;
import com.hgsoft.main.jcManange.entity.RoBlackList;
import com.hgsoft.main.jcManange.entity.RoGrayList;
import com.hgsoft.main.jcManange.service.BlackListService;
import com.hgsoft.main.jcManange.service.GrayListService;
import com.hgsoft.security.action.BaseAction;
import com.hgsoft.security.entity.Admin;
import com.hgsoft.security.entity.OperLog;
import com.hgsoft.security.operLog.service.OperLogService;
import com.opensymphony.xwork2.ActionContext;

/** 
  * @author  dje 
  * @date    创建时间：2016年9月21日 下午12:20:54   
  */
@Controller
@Scope("prototype")
public class ReportAction extends BaseAction{
	@Resource
	private OperLogService operLogService = new OperLogService();
	
	@Resource
	private BlackListService blackListService = new BlackListService();
	
	@Resource
	private GrayListService grayListService = new GrayListService();
	
    private String excelFlag;//页面uri
	
	private OperLog operLog = new OperLog();
	
	private Conditions conditions = new Conditions();
	
	private Map map = new HashMap<>();
	
	//protected Admin operator;
	
	public String excelNum(){
		int num = 0;
		switch (excelFlag) {
		case "operlog":
			operLog.setName(operLog.getUploadName());
			operLog.setStartQueryDate(operLog.getBeginDate()+" 00:00:00");
			operLog.setEndQueryDate(operLog.getEndDate()+" 23:59:59");
			num = operLogService.getLoadDownListBySql(null, operLog,
					(Admin) ActionContext.getContext().getSession().get("operator")).size();
			operLog.setName("");
			operLog.setStartQueryDate("");
			operLog.setEndQueryDate("");
			break;
		case "problack":
			String[] selectName = new String[]{"plateNum","carNo","vehClass","vehFlag","vehPlateColor","interceptOption","startQueryDate","endQueryDate"};
			
			num=blackListService.queryAllMessageList(null, conditions, selectName, "proBlackList", ProBlackList.class).size();
			
			conditions.setDataSource("1");
			break;
		case "roblack":
			String[] selectName1 = new String[]{"plateNum","carNo","vehClass","vehFlag","vehPlateColor","interceptOption","vehBigType","vehMidType","vehSmallType","startQueryDate","endQueryDate"};
			
			num=blackListService.queryAllMessageList(null, conditions, selectName1, "roBlackList", RoBlackList.class).size();
			
			conditions.setDataSource("0");
			break;
		case "progray":
			String[] selectName2 = new String[]{"plateNum","carNo","vehClass","vehFlag","vehPlateColor"};
				
			num=grayListService.queryAllMessageList(null, conditions, selectName2, "proGrayList", ProGrayList.class).size();
				
			conditions.setDataSource("1");
			break;
		case "rogray":
			Subject currentUser = SecurityUtils.getSubject();
			if (currentUser.isPermitted("jcManage:man")) {
				conditions.setStaffNo(operator.getStaffNo());
			}
			
			String[] selectName3 = new String[]{"plateNum","carNo","vehClass","vehFlag","vehPlateColor","vehBigType","vehMidType","vehSmallType","staffNo"};
			
			num=grayListService.queryAllMessageList(null, conditions, selectName3, "roGrayList", RoGrayList.class).size();
			
			conditions.setDataSource("0");
			break;
		default:
			break;
		}
		if (num == 0) {// 0 条，无法导出
			map.put("status", 0);
		} else if (num > 65535) {// 超过65535，数据量太大无法导出
			map.put("status", 1);
		}
		return "reportStatus";
	}

	/*@PostConstruct
	public void init() {
		operator = (Admin) ActionContext.getContext().getSession().get("operator");	
	}*/

	public String getExcelFlag() {
		return excelFlag;
	}

	public void setExcelFlag(String excelFlag) {
		this.excelFlag = excelFlag;
	}

	public OperLog getOperLog() {
		return operLog;
	}

	public void setOperLog(OperLog operLog) {
		this.operLog = operLog;
	}

	public Conditions getConditions() {
		return conditions;
	}

	public void setConditions(Conditions conditions) {
		this.conditions = conditions;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

}

