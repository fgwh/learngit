
package com.hgsoft.main.statisticsAnalysis.service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hgsoft.main.statisticsAnalysis.dao.LiushuiStatisticsDAO;
import com.hgsoft.main.statisticsAnalysis.entity.Condition;
import com.hgsoft.main.statisticsAnalysis.entity.TrafficFlow;
import com.hgsoft.security.dao.BaseDao;
import com.hgsoft.security.entity.Admin;
import com.hgsoft.security.service.BaseService;
import com.hgsoft.util.CacheUtil;
import com.hgsoft.util.StringUtil;


/** 
  * @author  dje 
  * @date    创建时间：2016年9月13日 下午3:26:41   
  */
@Service
public class TrafficFlowStatisticalService extends BaseService<TrafficFlow>{
	
	@Resource
	public void setDao(LiushuiStatisticsDAO dao) {
		// TODO Auto-generated method stub
		super.setDao(dao);
	}
	
	public LiushuiStatisticsDAO getLiushuiStatisticsDAO(){
		return (LiushuiStatisticsDAO)this.getDao();
	}
	
	/**
	 * 获取交通流量统计数据
	 * @param condition
	 * @return
	 * @throws Exception 
	 */
	public TrafficFlow getTrafficFlowListStatistics(Admin operator,Condition condition) throws Exception {
		TrafficFlow trafficFlow = new TrafficFlow();
		// 如果不选择路段、站编号，则查询该登录人下的所有操作数据
		String treeCode;
		if (StringUtil.isTrimEmpty(condition.getCenterStationId())
				&& StringUtil.isTrimEmpty(condition.getStationId())) {
			treeCode = "";
		} else {
			if (StringUtil.isTrimEmpty(condition.getStationId())) {
				treeCode = CacheUtil.getAllOrgTreeCodeMap().get(condition.getCenterStationId());
			} else {
				treeCode = CacheUtil.getAllOrgTreeCodeMap().get(condition.getStationId());
			}
		}
		if (null != condition.getEnExFlag() && "1".equals(condition.getEnExFlag())) {
			trafficFlow = getLiushuiStatisticsDAO().getTrafficFlowByExStatistics(condition,treeCode);
		} else if (null != condition.getEnExFlag() && "2".equals(condition.getEnExFlag())) {
			trafficFlow = getLiushuiStatisticsDAO().getTrafficFlowByEnStatistics(condition,treeCode);
		} else {
			trafficFlow = (TrafficFlow) copy(getLiushuiStatisticsDAO().getTrafficFlowByExStatistics(condition,treeCode),
					getLiushuiStatisticsDAO().getTrafficFlowByEnStatistics(condition,treeCode));
		}
		return trafficFlow;
	}
	
	/**
	 * 流量统计柱状图字符串
	 * @param condition
	 * @param flow
	 * @return
	 */
	public String barDataStr(Condition condition, TrafficFlow flow) {
		StringBuffer date = new StringBuffer("");// “年-月-日；客车数，货车数”
		if (condition.getTableInfo() == 3) {
			date.append(condition.getYear());
		} else if (condition.getTableInfo() == 2) {
			date.append(condition.getYear() + "-" + condition.getMonth());
		} else if (condition.getTableInfo() == 1) {
			date.append(condition.getYear() + "-" + condition.getMonth() + "-" + condition.getDay());
		}
		date.append(";").append(flow.getCarsTotalNum()).append(",").append(flow.getTruckTotalNum());
		return date.toString();
	}
	
	
	public String graphDataStrWithYear(Condition condition, Admin operator) {
		// 如果不选择路段、站编号，则查询该登录人下的所有操作数据
		String treeCode;
		if (StringUtil.isTrimEmpty(condition.getCenterStationId())
				&& StringUtil.isTrimEmpty(condition.getStationId())) {
			treeCode = "";
		} else {
			if (StringUtil.isTrimEmpty(condition.getStationId())) {
				treeCode = CacheUtil.getAllOrgTreeCodeMap().get(condition.getCenterStationId());
			} else {
				treeCode = CacheUtil.getAllOrgTreeCodeMap().get(condition.getStationId());
			}
		}
		List<Object[]> enList = null;
		List<Object[]> exList = null;
		//只查入口数据
		if("2".equals(condition.getEnExFlag())){
			enList = getLiushuiStatisticsDAO().getTrafficFlowByEnStatisticsWithYear(condition, treeCode);
		}
		//只查出口数据
		if ("1".equals(condition.getEnExFlag())) {
			exList = getLiushuiStatisticsDAO().getTrafficFlowByExStatisticsWithYear(condition, treeCode);
		}
		//全部出入口数据
		if ("0".equals(condition.getEnExFlag())) {
			enList = getLiushuiStatisticsDAO().getTrafficFlowByEnStatisticsWithYear(condition, treeCode);
			exList = getLiushuiStatisticsDAO().getTrafficFlowByExStatisticsWithYear(condition, treeCode);
		}
		int[] carsTotalByMon = new int[12];
		int[] truckTotalByMon = new int[12];
		
		if (null != enList) {
			for (Object[] en : enList) {
				carsTotalByMon[(int)en[0]-1] = carsTotalByMon[(int)en[0]-1]
						+ (int) en[1];
				truckTotalByMon[(int)en[0]-1] = truckTotalByMon[(int)en[0]-1] + (int) en[2];
			}
		}
		
		if (null != exList) {
			for (Object[] ex : exList) {
				carsTotalByMon[(int)ex[0]-1] = carsTotalByMon[(int)ex[0]-1]
						+ (int) ex[1];
				truckTotalByMon[(int)ex[0]-1] = truckTotalByMon[(int)ex[0]-1] + (int) ex[2];
			}
		}
		
		StringBuffer sb = new StringBuffer();
		StringBuffer item = new StringBuffer();
		StringBuffer carsData = new StringBuffer();// 客车分月数量
		StringBuffer truckData = new StringBuffer();// 货车分月数量
		
		for (int i = 0; i < 12; i++) {
			item.append(i + 1 + ",");
			carsData.append(carsTotalByMon[i] + "&");
			truckData.append(truckTotalByMon[i] + "&");
		}
		
		if(carsData.length()>0&&truckData.length()>0){
			sb.append(item.toString().substring(0, item.length() - 1)).append(";")
			.append(carsData.toString().substring(0, carsData.length() - 1)).append(",")
			.append(truckData.toString().substring(0, truckData.length() - 1));
		}
		
		if (sb.length() > 0) {
			return sb.toString();
		} else {
			return "";
		}
	}
	
	public String graphDataStrWithMonth(Condition condition, Admin operator) {

		// 如果不选择路段、站编号，则查询该登录人下的所有操作数据
		String treeCode;
		if (StringUtil.isTrimEmpty(condition.getCenterStationId())
				&& StringUtil.isTrimEmpty(condition.getStationId())) {
			treeCode = "";
		} else {
			if (StringUtil.isTrimEmpty(condition.getStationId())) {
				treeCode = CacheUtil.getAllOrgTreeCodeMap().get(condition.getCenterStationId());
			} else {
				treeCode = CacheUtil.getAllOrgTreeCodeMap().get(condition.getStationId());
			}
		}
		List<Object[]> enList = null;
		List<Object[]> exList = null;
		// 只查入口数据
		if ("2".equals(condition.getEnExFlag())) {
			enList = getLiushuiStatisticsDAO().getTrafficFlowByEnStatisticsWithMonth(condition, treeCode);
		}
		// 只查出口数据
		if ("1".equals(condition.getEnExFlag())) {
			exList = getLiushuiStatisticsDAO().getTrafficFlowByExStatisticsWithMonth(condition, treeCode);
		}
		// 全部出入口数据
		if ("0".equals(condition.getEnExFlag())) {
			enList = getLiushuiStatisticsDAO().getTrafficFlowByEnStatisticsWithMonth(condition, treeCode);
			exList = getLiushuiStatisticsDAO().getTrafficFlowByExStatisticsWithMonth(condition, treeCode);
		}
		
		int maxDay = getMaxDayByYearMonth(condition.getYear(), condition.getMonth());
		
		int[] carsTotalByDay = new int[maxDay];
		int[] truckTotalByDay = new int[maxDay];
		
		if (null != enList) {
			for (Object[] en : enList) {
				carsTotalByDay[(int)en[0]-1] = carsTotalByDay[(int)en[0]-1]
						+ (int) en[1];
				truckTotalByDay[(int)en[0]-1] = truckTotalByDay[(int)en[0]-1] + (int) en[2];
			}
		}
		
		if (null != exList) {
			for (Object[] ex : exList) {
				carsTotalByDay[(int)ex[0]-1] = carsTotalByDay[(int)ex[0]-1]
						+ (int) ex[1];
				truckTotalByDay[(int)ex[0]-1] = truckTotalByDay[(int)ex[0]-1] + (int) ex[2];
			}
		}
		
		// 年度SZ收费站绿通车走势图;未通过数(辆),通过数(辆);1月,2月,3月,4月,5月,6月,7月,8月,9月,10月,11月,12月;15246&9357&2&13&16&89,15411&9404&1&3&17&55
		StringBuffer sb = new StringBuffer();
		StringBuffer item = new StringBuffer();// 日期
		StringBuffer carsData = new StringBuffer();// 客车分月数量
		StringBuffer truckData = new StringBuffer();// 货车分月数量
		
		for (int i = 0; i < maxDay; i++) {
			item.append(condition.getYear()+"-"+condition.getMonth()+"-"+(i + 1) + ",");
			carsData.append(carsTotalByDay[i] + "&");
			truckData.append(truckTotalByDay[i] + "&");
		}

		if(carsData.length()>0&&truckData.length()>0){
			sb.append(item.toString().substring(0, item.length() - 1)).append(";")
			.append(carsData.toString().substring(0, carsData.length() - 1)).append(",")
			.append(truckData.toString().substring(0, truckData.length() - 1));
		}
		
		if (sb.length() > 0) {
			return sb.toString();
		} else {
			return "";
		}
	}
	
	
	/**
	 * 把第二个对象的属性复制到第一个对象上
	 * @param o1
	 * @param o2
	 * @return
	 * @throws Exception
	 */
	static Object copy(Object o1, Object o2) throws Exception {
        Field[] field = o1.getClass().getDeclaredFields();
        Method[] mm = o1.getClass().getDeclaredMethods();
        for(int j=0 ; j<field.length ; j++){
            String name = field[j].getName();    
            String type = field[j].getGenericType().toString();    
            if(type.equals("class java.lang.String")){   
                Method m = o1.getClass().getMethod("get"+toUpperCaseFirstOne(name));
                String value = (String) m.invoke(o1);
                String value2 = (String) m.invoke(o2);
                if(value == null && value2!=null){
                    m = o1.getClass().getMethod("set"+toUpperCaseFirstOne(name), String.class);
                    m.invoke(o1, value2);
                }
            } else if("int".equals(type)) {
                Method m = o1.getClass().getMethod("get"+toUpperCaseFirstOne(name));
                int value = (int) m.invoke(o1);    
                int value2 = (int) m.invoke(o2);   
                if(value2 != 0){
                    m = o1.getClass().getMethod("set"+toUpperCaseFirstOne(name), int.class);
                    m.invoke(o1, value+value2);
                }
            }
        }
        return o1;
    }
	
	/** 
     * 首字母转大写 
     * @param s 
     * @return 
     */  
    public static String toUpperCaseFirstOne(String s)  
    {  
        if(Character.isUpperCase(s.charAt(0)))  
            return s;  
        else  
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();  
    }  
    
  //根据年月，获取当前月最大天数
  	public int getMaxDayByYearMonth(int year,int month) {
  		int maxDay = 0;
  		int day = 1;
  		/**
  		 * 与其他语言环境敏感类一样，Calendar 提供了一个类方法 getInstance，
  		 * 以获得此类型的一个通用的对象。Calendar 的 getInstance 方法返回一
  		 * 个 Calendar 对象，其日历字段已由当前日期和时间初始化： 
  		 */
          Calendar calendar = Calendar.getInstance();
          /**
           * 实例化日历各个字段,这里的day为实例化使用
           */
          calendar.set(year,month - 1,day);
          /**
           * Calendar.Date:表示一个月中的某天
           * calendar.getActualMaximum(int field):返回指定日历字段可能拥有的最大值
           */
          maxDay = calendar.getActualMaximum(Calendar.DATE);
  		return maxDay;
  	}
}

