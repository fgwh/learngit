package com.hgsoft.cxf.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;
import com.hgsoft.security.service.BaseService;

@Service
public class NewGoodsInspectionService extends BaseService{
	/** 列分割符：#### */
	private final static String COLUMN_SEPARATOR = "####";
	/** 行分割符：&&&& */
	private final static String ROW_SEPARATOR = "&&&&";
	 
	/**
	 * 将获取list类型转化成map类型
	 * @param list
	 * @return
	 */
	public HashMap<String, String> goodsListToMap(List<Object[]> list){
		HashMap<String, String> shMap = new HashMap<String, String>();
		if(list==null || list.size()==0){
		} else {
			for(int i=0;i<list.size();i++){
				Object[] objs = list.get(i);
				StringBuffer tempStr = new StringBuffer();
				for(int j=0;j<objs.length;j++){
					tempStr.append(objs[j]).append(COLUMN_SEPARATOR);
				}
				//tempStr.append(objs[objs.length-1]);
				shMap.put(tempStr.toString(), "");
			}
		}
		
		return shMap;
	}
	
	public HashMap<String, String> laneExListListToMap(List<Object[]> list){
		HashMap<String, String> shMap = new HashMap<String, String>();
		if(list==null || list.size()==0){
		} else {
			for(int i=0;i<list.size();i++){
				Object[] objs = list.get(i);
				StringBuffer tempStr = new StringBuffer();
				for(int j=0;j<objs.length;j++){
					tempStr.append(objs[j]).append(COLUMN_SEPARATOR);
				}
				shMap.put(tempStr.toString(), "");
			}
		}
		
		return shMap;
	}
	/**
	 * 时间的推算
	 * @return
	 */
	public String getDateStr(int days){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		
		c.add(Calendar.DATE, days);
		return dateFormat.format(c.getTime());
	}
}
