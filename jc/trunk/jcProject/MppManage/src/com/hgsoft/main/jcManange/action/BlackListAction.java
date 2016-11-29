package com.hgsoft.main.jcManange.action;

import com.hgsoft.main.jcManange.entity.Conditions;
import com.hgsoft.main.jcManange.entity.ProBlackList;
import com.hgsoft.main.jcManange.entity.RoBlackList;
import com.hgsoft.main.jcManange.service.BlackListService;
import com.hgsoft.security.action.BaseAction;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@Scope("prototype")
public class BlackListAction extends BaseAction<RoBlackList>{
	
	@Resource
	private BlackListService blackListService;
	
	private Conditions conditions = new Conditions() ;
	

	
	public String list(){
			
		String[] selectName = new String[]{"plateNum","carNo","vehClass","vehFlag","vehPlateColor","interceptOption","vehBigType","vehMidType","vehSmallType","startQueryDate","endQueryDate"};
		
		list=blackListService.queryAllMessageList(pager, conditions, selectName, "roBlackList", RoBlackList.class);
		
		conditions.setDataSource("0");
		
		return  "list";
	}
	
	public String proList(){
		
		String[] selectName = new String[]{"plateNum","carNo","vehClass","vehFlag","vehPlateColor","interceptOption","startQueryDate","endQueryDate"};
		
		list=blackListService.queryAllMessageList(pager, conditions, selectName, "proBlackList", ProBlackList.class);
		
		conditions.setDataSource("1");
		return  "list";
	}

	
	public String save(){
				

		if(blackListService.saveRoBlackList(entity)){
			message="保存成功！";
		}else{
			message="保存失败！";
		}
		
		return list();
		
	}
	

	
	
	public String delete(){
		
		blackListService.deleteById(conditions.getId());
		
		map.put("status", true);
		
		return "success";
		
	}
	
	public String recovered(){
		

		if(blackListService.recovered(entity)!=-1){
			message="费用追缴成功！";
		}else{
		    message="费用追缴失败！";
		}
		return  list();
	}
	
	
	public String upload(){
		
		blackListService.upload(entity);
		message="证据链上传成功！";
		return  list();
	}
	
	public String plateValidate(){		
		boolean status=blackListService.plateValidate(conditions);
		map.put("status", status);
		return "success";
	}

	public String importExcel(){
    	message=blackListService.importExcel(entity);    	
    	return list();
    }

	// ================================= 文件下载
	private String fileName;

	public void setFileName(String fileName) throws UnsupportedEncodingException {
		this.fileName = fileName;
	}
	public String getFileName() {
		try {
			return URLEncoder.encode(fileName, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException("不可楞!");
		}
	}

	public InputStream getInputStream() throws UnsupportedEncodingException {
		ServletContext servletContext = ServletActionContext.getServletContext();
		// this.fileName = new String(this.fileName.getBytes("UTF-8"),"ISO-8859-1");
		fileName = "黑名单导入模板.xls";
		InputStream inputStream = servletContext.getResourceAsStream("/WEB-INF/template/黑名单导入模板.xls");

		return inputStream;
	}

	public String execute() {
		return "success";
	}

	/**
	 * 文件下载
	 * @return
	 */
	public String downloadTemp() {
	
		return "download";
	}
	
	
	
	
	
	public Conditions getConditions() {
		return conditions;
	}

	public void setConditions(Conditions conditions) {
		this.conditions = conditions;
	}
	
	
	public void setRoBlackList(RoBlackList roBlackList) {
		this.entity = roBlackList;
	}

	public RoBlackList getRoBlackList() {
		return entity;
	}




}