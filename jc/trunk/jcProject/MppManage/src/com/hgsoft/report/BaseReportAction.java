package com.hgsoft.report;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class BaseReportAction extends AbstractReportAction {

	public String frame(){
		return "frame";
	}

	public void view() throws Exception {
		//this.parameters.put("username","stationAdmin");
		preview();
	}

	public void doExportExcel() throws Exception{
		this.parameters.put("username","stationAdmin");
		exportXLS(null);
	}

	public void doExportPDF() throws Exception{
		this.parameters.put("username","stationAdmin");
		exportPDF();
	}

	String formName ="";
	public String getFormName(){
		return formName;
	}

	public void setFormName(String formName){
		this.formName = formName;
	}
	
}
