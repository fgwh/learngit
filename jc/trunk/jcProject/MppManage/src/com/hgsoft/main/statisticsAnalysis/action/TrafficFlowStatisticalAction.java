
package com.hgsoft.main.statisticsAnalysis.action;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hgsoft.main.statisticsAnalysis.entity.Condition;
import com.hgsoft.main.statisticsAnalysis.entity.TrafficFlow;
import com.hgsoft.main.statisticsAnalysis.service.TrafficFlowStatisticalService;
import com.hgsoft.security.action.BaseAction;
import com.hgsoft.security.operLog.annotation.Description;
import com.hgsoft.util.CacheUtil;
import com.hgsoft.util.DateUtil;
import com.hgsoft.util.StringUtil;

import antlr.collections.List;

/** 
  * @author  dje 
  * @date    创建时间：2016年9月13日 下午3:12:41   
  */

@Controller
@Scope("prototype")
public class TrafficFlowStatisticalAction extends BaseAction{
	
	@Autowired
	private TrafficFlowStatisticalService trafficFlowStatisticalService;
	
	private Condition condition = new Condition();
	
	private TrafficFlow flow; //列表数据
	
	private String barData;
	
	private String graphData;
	
	private Map<String,String> stationIdNameMap = new HashMap<>(); 
	
	@Description(details="查询交通流量报表")
	public String trafficFlowStatistics() throws Exception {
		getRewriteParam();
	    flow= trafficFlowStatisticalService.getTrafficFlowListStatistics(this.operator,condition);
	    barData = trafficFlowStatisticalService.barDataStr(condition, flow);
		if (condition.getTableInfo() == 3) {
			graphData = trafficFlowStatisticalService.graphDataStrWithYear(condition, this.operator);
		}else if (condition.getTableInfo() == 2) {
			graphData = trafficFlowStatisticalService.graphDataStrWithMonth(condition, this.operator);
		}else {
			graphData ="";
		}
		return "traffic";
	}
	
	// 获取需要回写页面的列表
    private void getRewriteParam() {	
	
    	Calendar calendar = Calendar.getInstance();
		// 默认的时间范围为当前年份
    	if (0==condition.getYear()) {
			condition.setYear(calendar.get(Calendar.YEAR));
		}
    	// 默认的时间范围为当前月份
    	if(-1==condition.getMonth()){
    		condition.setMonth(calendar.get(Calendar.MONTH)+1);//日历类中0表示1月
    	}
    	// 默认的时间范围为当前月份
    	if(0==condition.getDay()){
    		condition.setDay(calendar.get(Calendar.DAY_OF_MONTH));
    	}
    }

	public Condition getCondition() {
		return condition;
	}

	public TrafficFlow getFlow() {
		return flow;
	}

	public String getBarData() {
		return barData;
	}

	public String getGraphData() {
		return graphData;
	}

	public Map<String, String> getStationIdNameMap() {
		return CacheUtil.getAllOrgMap();
	}
    
    
}

