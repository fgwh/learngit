package com.hgsoft.main.travelpath;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hgsoft.cxf.common.PropertiesUtil;
import com.hgsoft.main.carStatistic.entity.CarStatistic;
import com.hgsoft.main.carStatistic.entity.LaneExListImg;
import com.hgsoft.main.carStatistic.service.CarStatisticService;
import com.hgsoft.main.entity.Conditions;
import com.hgsoft.main.laneexlist.entity.LaneExList2;
import com.hgsoft.main.laneexlist.service.LaneExListService;
import com.hgsoft.security.action.BaseAction;
import com.hgsoft.security.entity.Admin;
import com.hgsoft.util.DateUtil;
import com.hgsoft.util.Pager;

@Controller
@Scope("prototype")
public class TravelpathAction extends BaseAction<LaneExList2> {
	private Conditions conditions = new Conditions(); // 查询条件
	@Resource
	private CarStatisticService carStatisticService;
	private Map map = new HashMap();
	@Resource
	private LaneExListService laneExListService;
	/* 获取实体, 用于JSP读取实体对象属性 */
    public LaneExList2  getLaneExList2() {
        return this.entity;
    }
    /* 设置实体, 用于STRUTS设置实体对象属性 */
    public void setLaneExList2(LaneExList2 laneExList2) {
        this.entity = laneExList2;
    }
    
    private CarStatistic carStatistic;
   
	public CarStatistic getCarStatistic() {
		return carStatistic;
	}
	public void setCarStatistic(CarStatistic carStatistic) {
		this.carStatistic = carStatistic;
	}
	// 查询
	public String list() {
	    	if(entity == null){
				entity = new LaneExList2();
				String dateTemp = DateUtil.format(new Date(), DateUtil.PATTERN_STRING_DATE); 
				entity.setStartTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				entity.setEndTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			}
	    
	    	list = laneExListService.queryAllLaneExList(entity, pager, operator);
	    	return "list";
		
	}
	
	// 获取txt流 
	public InputStream getTXTList() {
		return laneExListService.getTxt(entity, pager, operator);
	}
	
	// 获取入口流水
	public String exList() {
		map= laneExListService.exList(entity, pager, operator);
		return "success";
	}
	// 通过realPath获取station中文名，不分页
	public String queryName() {
		map=laneExListService.queryName(entity, pager, operator);
		return "success";
	}
	// 获取导出txt数量
	public String explortTXTNum() {
		map=laneExListService.explortTXTNum(entity, pager, operator);
		return "success";
	}

	// 导出txt
	public String explortTXT() {
		return "download";
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

	/*车道类型*/
	public Map<String,String> getLaneTypeMap() {	
		return getDicItemsMap("laneType");
	}
}
