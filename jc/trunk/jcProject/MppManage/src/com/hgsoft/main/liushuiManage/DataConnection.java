package com.hgsoft.main.liushuiManage;


import java.util.List;
import java.util.Map;


/**
 *  连接不同的流水数据库
 * @author liyuyun
 * date:2015-7-16
 */
public interface DataConnection {
	
	public List<Object[]> getAllData(Object... obj) throws Exception;//查询服务器前一天所有数据
	
	 
	public int queryLiuShuiDataSize(Object... obj); //查询符合流水数据的数据表的大小
	
	public List<Object> queryLiuShuiList(Object... obj); //分页搜索流水数据用于绑定
	
	public Map<String, String> getLiuShuiInfo(Object... obj) throws Exception; //查询当天流水数据 使用定时器手动绑定
}
