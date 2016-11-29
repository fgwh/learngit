
package com.hgsoft.main.report.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.hgsoft.main.jcManange.entity.Conditions;
import com.hgsoft.main.jcManange.entity.ProGrayList;
import com.hgsoft.main.jcManange.entity.RoBlackList;
import com.hgsoft.main.jcManange.entity.RoGrayList;
import com.hgsoft.main.jcManange.service.BlackListService;
import com.hgsoft.main.jcManange.service.GrayListService;
import com.hgsoft.report.AbstractReportAction;
import com.hgsoft.util.Pager;

/** 
  * @author  dje 
  * @date    创建时间：2016年9月19日 上午10:20:37   
  */

public class ProGrayListReportAction extends AbstractReportAction {

	@Resource
	private GrayListService grayListService = new GrayListService();
	
	private Conditions conditions = new Conditions();
	
	private List list = new ArrayList<>();
	
	private Pager pager = new Pager();
	
	public String frame(){
		return "frame";
	}
	
	public void doExportExcel() throws Exception{
		
        String[] selectName = new String[]{"plateNum","carNo","vehClass","vehFlag","vehPlateColor"};
		
		list=grayListService.queryAllMessageList(null, conditions, selectName, "proGrayList", ProGrayList.class);
		
		conditions.setDataSource("1");
		
		super.parameters.put("dataSource", "1".equals(conditions.getDataSource())?"全省":"路段");
		
		exportXLS(list); 
	}
	
	public Conditions getConditions() {
		return conditions;
	}

	public void setConditions(Conditions conditions) {
		this.conditions = conditions;
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
}
