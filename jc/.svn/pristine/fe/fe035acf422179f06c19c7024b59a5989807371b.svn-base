package com.hgsoft.job.service;

import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.hgsoft.job.JobLog;
import com.hgsoft.main.entity.QrtzLog;
import com.hgsoft.main.entity.Road;
import com.hgsoft.main.entity.Station;
import com.hgsoft.main.service.RoadService;
import com.hgsoft.main.service.StationService;
import com.hgsoft.util.DBUtil;
import com.hgsoft.util.JDBCFrame;
import com.hgsoft.util.JDBCFrameForLaneExOrEnList;
import com.hgsoft.util.LocalDBUtils;


public class RoadAndStationService {
	private static Logger logger = Logger.getLogger(RoadAndStationService.class);
	
	private static WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
	//@Resource
	private static RoadService roadService;// = new XPhotoMsgService();
	private static StationService stationService;// = new XPhotoMsgService();
	
	public void saveAllRoadAndStationData(QrtzLog qrtzLog){
		Map dataMap = getRoadAndStationData();
		
		Connection connection = LocalDBUtils.getConnection();
		try{
			JDBCFrameForLaneExOrEnList jdbcFrame = new JDBCFrameForLaneExOrEnList(connection);
			
			jdbcFrame.updateByPreparedStatement("DELETE FROM tb_road", null);
			roadService = (RoadService) applicationContext.getBean("roadService");
			roadService.saveAllRoadData((List<Road>) dataMap.get("road"));
			jdbcFrame.updateByPreparedStatement("DELETE FROM tb_station", null);
			stationService = (StationService) applicationContext.getBean("stationService");
			stationService.saveAllStationData((List<Station>) dataMap.get("station"));
			
			logger.info("路段和站的数据保存数据库成功......");
            qrtzLog.setDescription("路段和站的数据保存成功");
            qrtzLog.setJobStatus(JobLog.JOB_STATUS_SUCC);
		} catch(Exception e){
			logger.info("路段和站的数据保存到本地数据库出错......");
            qrtzLog.setDescription("路段和站的数据保存失败");
            qrtzLog.setJobStatus(JobLog.JOB_STATUS_FAIL);
		} finally{
			qrtzLog.setCreatetime(new Date());
			LocalDBUtils.release(connection, null, null);
		}
	}
	
	
	private Map getRoadAndStationData(){
		// 用jdbcFrame取数据
        JDBCFrame jdbcFrame = new JDBCFrame(DBUtil.getConnection());
        String roadSql = "select AreaID as areaID,RoadID as roadID,RoadName as roadName,RoadAllName as roadAllName,NationalRoadID as nationalRoadID,RoadServerName as roadServerName,"
        		+ " RoadServerIP as roadServerIP,RoadDomain as roadDomain,RoadDBName as roadDBName,RoadDBUserName as roadDBUserName,RoadDBPassWord as roadDBPassWord from tb_Road";
        String stationSql = "select RoadID as roadID,StationID as stationID,StationName as stationName,StationServerName as stationServerName,StationServerIP as stationServerIP,StationDomain as stationDomain,StationDBName as stationDBName,StationDBUserName as stationDBUserName,StationDBPassWord as stationDBPassWord,[Version] as version,StartTime as startTime,StationType as stationType "
        		+ " from (select row_num = ROW_NUMBER() over(partition by roadId,stationID order by Version desc), * from tb_Station) as tempStation where row_num=1";
        
       
        List<Road> roadLists = null;
        List<Station> stationLists = null;
        Map allContentMap  = new HashMap();
        try {
        	roadLists = jdbcFrame.findMoreRefResult(roadSql, null, Road.class);
        	stationLists = jdbcFrame.findMoreRefResult(stationSql, null, Station.class);
        	allContentMap.put("road", roadLists);
        	allContentMap.put("station", stationLists);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcFrame.releaseConn();
        }

        return allContentMap;
	}
}
