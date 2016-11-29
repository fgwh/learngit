
package com.hgsoft.main.report.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.hgsoft.report.AbstractReportAction;
import com.hgsoft.security.entity.Admin;
import com.hgsoft.security.entity.OperLog;
import com.hgsoft.security.operLog.service.OperLogService;
import com.hgsoft.util.Pager;
import com.opensymphony.xwork2.ActionContext;

/** 
  * @author  dje 
  * @date    创建时间：2016年9月17日 下午3:09:58   
  */

public class OperaLogReportAction extends AbstractReportAction{
	@Resource
	private OperLogService operLogService = new OperLogService();
	
	private OperLog operLog =new OperLog();
	
	private List<OperLog> list = new ArrayList<>();
	private String message;//返回JSP详细信息
	private Pager pager = new Pager(); 
	public String frame(){
		return "frame";
	}
	
	public void doExportExcel() throws Exception{
		operLog.setName(operLog.getUploadName());
		operLog.setStartQueryDate(operLog.getBeginDate()+" 00:00:00");
		operLog.setEndQueryDate(operLog.getEndDate()+" 23:59:59");
		list = operLogService.getLoadDownListBySql(null, operLog, (Admin) ActionContext.getContext().getSession().get("operator"));
		operLog.setName("");
		operLog.setStartQueryDate("");
		operLog.setEndQueryDate("");
		super.parameters.put("operTypeNameMap", getOperTypeNameMap());
		exportXLS(list); 
	}
	
	public OperLog getOperLog() {
		return operLog;
	}

	public void setOperLog(OperLog operLog) {
		this.operLog = operLog;
	}
	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	String formName ="";
	public String getFormName(){
		return formName;
	}

	public void setFormName(String formName){
		this.formName = formName;
	}
	public String getMessage() {
		return message;
	}
	protected void outMessage(String message) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(message.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}
	
	public static Map<String, String> getOperTypeNameMap(){
		Map<String, String> map = new HashMap<>();
		map.put("1", "添加");
		map.put("2", "更新");
		map.put("3", "删除");
		map.put("4", "查询");
		map.put("5", "移动端操作");
		return map;
	}
}
