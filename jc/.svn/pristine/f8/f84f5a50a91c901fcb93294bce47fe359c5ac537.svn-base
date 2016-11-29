package com.hgsoft.main.report.action;

import java.util.List;

import javax.annotation.Resource;

import com.hgsoft.main.jcManange.service.AbnormalCarDetailService;
import com.hgsoft.report.AbstractReportAction;

public class AbnorCarDetailReportAction extends AbstractReportAction{

	
	private String exportplateNum;
	private String auditStatusExport;
	 @Resource
	 AbnormalCarDetailService abnormalcardetailservice=new AbnormalCarDetailService();
	 
	 
	 
	 public void doExportExcel() throws Exception{
		 List list=abnormalcardetailservice.getAbnorLiuShui(exportplateNum, auditStatusExport);
		 exportXLS(list);
	 }


/**
 * 
 * ************************* get set*****************************
 */
	public String getExportplateNum() {
		return exportplateNum;
	}
	public void setExportplateNum(String exportplateNum) {
		this.exportplateNum = exportplateNum;
	}
	public String getAuditStatusExport() {
		return auditStatusExport;
	}
	public void setAuditStatusExport(String auditStatusExport) {
		this.auditStatusExport = auditStatusExport;
	}



}
