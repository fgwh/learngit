package com.hgsoft.main.action;

import java.util.List;

import javax.annotation.Resource;

import com.hgsoft.security.action.BaseAction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hgsoft.main.service.DataBaseCollectService;

@Controller
@Scope("prototype")
public class DataBaseCollectAction extends BaseAction {

	private static Log logger = LogFactory.getLog(DataBaseCollectAction.class);
	private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String url = "jdbc:sqlserver://10.173.235.180:1433;databaseName=ETCTolling";
	private static final String user = "sa";
	private static final String password = "123456";
	
	private String sqlStr;
	private List<Object[]> dataList;
	private Object[] rowNameList;
	
	@Resource
	private DataBaseCollectService dataBaseCollectService;

	public String getSqlStr() {
		return sqlStr;
	}

	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}
	
	public String dataBaseSelect(){
		return "dataBaseSelect";
	}
	
	public void validSqlStr(){
		String message = "false";
		outMessage(message);
	}
	
	public String queryBySql(){
		if(sqlStr != null && !sqlStr.trim().equals("")){
		}
		try {
			dataList = dataBaseCollectService.queryBySQL(driver, url, user, password, sqlStr,pager);
			if(dataList != null && dataList.size() > 0){
				rowNameList = dataList.get(0);
				dataList.remove(0);
			}
		} catch (Exception e) {
			message = "SQL语句异常,请与管理员联系";
			e.printStackTrace();
		}
		return "queryBySql";
	}

	public List<Object[]> getDataList() {
		return dataList;
	}

	public void setDataList(List<Object[]> dataList) {
		this.dataList = dataList;
	}

	public Object[] getRowNameList() {
		return rowNameList;
	}

	public void setRowNameList(Object[] rowNameList) {
		this.rowNameList = rowNameList;
	}
	
	
}
