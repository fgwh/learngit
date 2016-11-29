package com.hgsoft.main.carStatistic.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hgsoft.main.carStatistic.entity.CarStatistic;
import com.hgsoft.main.carStatistic.entity.LaneExListImg;
import com.hgsoft.main.carStatistic.service.CarStatisticService;
import com.hgsoft.security.action.BaseAction;
import com.hgsoft.security.util.OrgUtils;
import com.hgsoft.util.DateUtil;

@Controller
@Scope("prototype")
public class CarStatisticAction  extends BaseAction<CarStatistic> {
	@Resource
	private CarStatisticService carStatisticService;
	/* 获取实体, 用于JSP读取实体对象属性 */
    public CarStatistic getCarStatistic() {
        return this.entity;
    }
    /* 设置实体, 用于STRUTS设置实体对象属性 */
    public void setCarStatistic(CarStatistic carStatistic) {
        this.entity = carStatistic;
    }
    
    public String list(){
    	if(entity == null){
			entity = new CarStatistic();
			String dateTemp = DateUtil.format(new Date(), DateUtil.PATTERN_STRING_DATE); 
			entity.setStartTime(DateUtil.parse(dateTemp+" 00:00:00", DateUtil.PATTERN_STRING_TIME));
			entity.setEndTime(DateUtil.parse(dateTemp+" 23:59:59", DateUtil.PATTERN_STRING_TIME));
			entity.setFlag(1);
		}
    	
    	list = carStatisticService.queryAllCarStatisticMsg(entity, pager, operator);
    	return "list";
    }
	
	public String getStationSelectName(){
		map.put("stationList", OrgUtils.getSubOrgByParentOrgStationIdZJ(entity.getSelectId(), this.operator));
		return "success";
	}
	
	/**
	 * 查询出所有的特殊事件类型
	 * @return
	 */
	public String getDealStatus(){
		map.put("dealStatusList", carStatisticService.queryAllDealStatusMsg());
		return "success";
	}
	
	/**
	 * 入口图片，出口图片信息的获取
	 * @return
	 */
	public String pictureMsg(){
		List<LaneExListImg> imageList = carStatisticService.pictureMsgData(entity);
		map.put("imageList", imageList);

		return "success";
	}
	
	/**
	 * 异常稽查出入口图片获取
	 * @return
	 */
	public String getEntrancesAndExits(){
		List<LaneExListImg> imageList = carStatisticService.abnorCarPicture(entity);
		map.put("imageList", imageList);
		
		return "success";
	}
	
	/**
	 * 异常稽查两个出口图片对比
	 * @return
	 */
	public String getAllExitPicture(){
		List<LaneExListImg> imageList = carStatisticService.queryAllExitPicture(entity);
		map.put("imageList", imageList);
		return "success";
	}
	
	/*车道类型*/
	public Map<String,String> getLaneTypeMap() {	
		return getDicItemsMap("laneType");
	}
	
	/*车型*/
	public Map<String,String> getVehClassMap() {	
		return getDicItemsMap("vehClass");
	}
	
	/*车型*/
	public Map<String,String> getVehTypeMap() {	
		return getDicItemsMap("vehType");
	}
	
}
