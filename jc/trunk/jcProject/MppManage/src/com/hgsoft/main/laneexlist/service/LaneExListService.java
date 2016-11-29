package com.hgsoft.main.laneexlist.service;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.eclipse.birt.report.model.api.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hgsoft.cxf.common.PropertiesUtil;
import com.hgsoft.job.GetLaneExListDataJob;
import com.hgsoft.job.JobLog;
import com.hgsoft.main.carStatistic.entity.LaneExListImg;
import com.hgsoft.main.entity.QrtzLog;
import com.hgsoft.main.laneexlist.dao.LaneExListDao;
import com.hgsoft.main.laneexlist.entity.LaneExList;
import com.hgsoft.main.laneexlist.entity.LaneExList2;
import com.hgsoft.security.entity.Admin;
import com.hgsoft.security.service.BaseService;
import com.hgsoft.util.DBUtil;
import com.hgsoft.util.DateUtil;
import com.hgsoft.util.JDBCFrame;
import com.hgsoft.util.LaneEnAndExUtils;
import com.hgsoft.util.LocalDBUtils;
import com.hgsoft.util.Pager;


/**
 * 出口流水service
 *
 * @author 郭志强
 * @time 2016/9/9 9:37
 */
@Service
public class LaneExListService extends BaseService<LaneExList> {
	private static final String roadCode = PropertiesUtil.getProperty("ROAD_CODE");
	private static final String provinceIp = PropertiesUtil.getProperty("PROVINCE_IP");
	private static final String provincePort = PropertiesUtil.getProperty("PROVINCE_PORT");
	private static final String fileDir = PropertiesUtil.getProperty("baseDir")+"//"+PropertiesUtil.getProperty("FILE_SAVE_DIR");
    private static Logger logger = Logger.getLogger(GetLaneExListDataJob.class);
    private Map map = new HashMap();
    public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	// 注入dao
    @Autowired
    private LaneExListDao laneExListDao;


    /**
     * 根据时间从中间库中取出数据，并保存到本地库
     *
     * @param qrtzLog
     * @param date
     */
    public synchronized void saveLaneExList(QrtzLog qrtzLog, String... date) {
        System.out.println(date[0]);
        System.out.println(date[1]);
        qrtzLog.setCreatetime(new Date());
        // 从中间库中取出数据
        List<LaneExList> list = getLaneExListFromOuterDatabase(date);
        System.out.println("共获取到：" + list.size() + "出口数据");
        Connection connection = null;
        Connection connection1 = null;
        Connection connection2 = null;//拆分出口流水路径标识，存入tb_ExPathPoint表的连接
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        
        
        try {
            long startTime = System.currentTimeMillis();
            connection = LocalDBUtils.getConnection();
            connection1 = DBUtil.getConnection();
            connection2 = LocalDBUtils.getConnection();
            
            connection.setAutoCommit(false);
            connection1.setAutoCommit(false);
            connection2.setAutoCommit(false);
            int count = 0;
            int pointNum = 0;
            // 判断list,并遍历list
            if (list != null && list.size() > 0) {
                for (LaneExList laneExList : list) {
                    Timestamp exTime = laneExList.getExTime();
                    String y = DateUtil.format(exTime, "yyyy");
                    // 保存到本地库 (用jdbc)
                    // 获取数据库连接
                    connection = LocalDBUtils.getConnection();
//                    JDBCFrameForLaneExOrEnList jf =
//                            new JDBCFrameForLaneExOrEnList(connection);

                    StringBuilder sb = new StringBuilder();
                    sb.append("INSERT INTO tb_LaneExList_");
                    // 这里需要改成year
                    sb.append(y);
                    sb.append("([ExRecordNo],[LaneExSerialNo] ,[LaneEnSerialNo],[CardNetRoadID],[IcinCardId],"
                    		+ "[EnNetRoadId],[EnRoadId],[EnStationID],"
                    		+ "[EnLaneID],[EnLaneType],[EnTime],[EnOperatorID],[EnShiftID],"
                    		+ "[EnVehicleClass],[EnVehicleStatus],[EnVehiclePlate],[EnVehicleFlag],[EnETCTermCode],"
                    		+ "[ExNetRoadID],[ExRoadID],[ExStationID],[ExLaneID],[ExLaneType],"
                    		+ "[ExTime],[ExOperatorID],[ExOpCardNo],[ExOpCardID],[SquadDate],"
                    		+ "[ExShiftID],[ExCPCSnNo],[ExCPCInID],[ExVehicleClass],[ExVehicleStatus],"
                    		+ "[ImageSerialNo],[CardBoxNo],[CardTrunkNo],[AppVer],[ExVehiclePlate],"
                    		+ "[ExVehIdentifyPlate],[TollType],[CashMoney],[InvoiceID],[ETCMoney],"
                    		+ "[FreeMoney],[OfficeMoney],[UnpayMoney],[VehicleClassMoney],[DownVehicleClassMoney],"
                    		+ "[OfficeCardSnNo],[OwnerCode1],[OwnerCode2],[OwnerCode3],[OwnerCode4],"
                    		+ "[DealStatus],[DeviceStatus],[RecordType],[ExVehicleFlag],[ExICCIssuer],"
                    		+ "[ExCPUCardSnNo],[ExCPUCardID],[ExCPUCardType],[ExCpuCardPlate],[ExCpuCardPlateColor],"
                    		+ "[ExCpuCardUserType],[WeightFlag],[TicketType],[PayCardType],[VehCount],"
                    		+ "[AxisNum],[AxisGroupNum],[TotalWeight],[TotalWeightLimit],[AxisType],"
                    		+ "[AxisWeightDetail],[OverLoadWeight],[PreviousAxisType],[PreviousTotalWeight],[TruckLimitVerNo],"
                    		+ "[TruckLimitPriceVerNo],[PreVehType],[PreVehMoney],[PayCardBalanceBefore],[PayCardBalanceAfter],"
                    		+ "[OBUNum],[OBUID],[OBEState],[OBUPlate],[OBUPlateColor],"
                    		+ "[ExETCTradeNo],[ExETCTac],[ExETCTermTradNo],[ExETCTermCode],[RebateType],"
                    		+ "[RebateVerNo],[RebateRate],[PreRebateFee],[RebateFee],[FlagType],"
                    		+ "[OriginalPath],[RealPath],[RoadComb],[MoneyComb],[RoadSStationComb],"
                    		+ "[RoadEStationComb],[RealPath01],[TollRateVer],[ListName],[VoidSerialNo],"
                    		+ "[ManualFlag],[VerifyCode],[CPCCurrentVol],[Miles],[TranslationMiles],"
                    		+ "[TollCardFreeListVerNo],[Spare1],[Spare2],[Spare3],[Spare4],"
                    		+ "[UploadTime])");
                    sb.append("VALUES(?,?,?,?,?,"
                    		+ "?,?,?,"
                    		+ "?,?,?,?,?,"
                    		+ "?,?,?,?,?,"
                    		+ "?,?,?,?,?,"
                    		+ "?,?,?,?,?,"
                    		+ "?,?,?,?,?,"
                    		+ "?,?,?,?,?,"
                    		+ "?,?,?,?,?,"
                    		+ "?,?,?,?,?,"
                    		+ "?,?,?,?,?,"
                    		+ "?,?,?,?,?,"
                    		+ "?,?,?,?,?,"
                    		+ "?,?,?,?,?,"
                    		+ "?,?,?,?,?,"
                    		+ "?,?,?,?,?,"
                    		+ "?,?,?,?,?,"
                    		+ "?,?,?,?,?,"
                    		+ "?,?,?,?,?,"
                    		+ "?,?,?,?,?,"
                    		+ "?,?,?,?,?,"
                    		+ "?,?,?,?,?,"
                    		+ "?,?,?,?,?,"
                    		+ "?,?,?,?,?,"
                    		+ "?)");

//                    System.out.println(sb.toString());
                    List<Object> params = new ArrayList<Object>();
                    // 封装参数
                    LaneEnAndExUtils.encapsulationLanExParams(laneExList, params);
                    preparedStatement = connection.prepareStatement(sb.toString());
                    int index = 1;

                    if ((params != null) && !params.isEmpty()) {
                        for (int i = 0; i < params.size(); i++) {
                            preparedStatement.setObject(index++, params.get(i));
                        }
                    }
                    preparedStatement.addBatch();
                    preparedStatement.executeBatch();
                    preparedStatement.clearBatch();
                    preparedStatement1 = connection1.prepareStatement("DELETE FROM tb_LaneExList_Mid WHERE LaneExSerialNo=?");
                    preparedStatement1.setString(1, laneExList.getLaneExSerialNo());
                    preparedStatement1.addBatch();
                    preparedStatement1.executeBatch();
                    preparedStatement1.clearBatch();
                    count++;
                    
                    StringBuilder ppSave = new StringBuilder();
                    ppSave.append("INSERT INTO tb_ExPathPoint_");
                    ppSave.append(y);
                    ppSave.append(" (LaneExSerialNo,RealPathPoint,SquadDate) VALUES (?,?,?)");
                    preparedStatement2 = connection2.prepareStatement(ppSave.toString());
                    
                    //批量保存标识点数据
                    if(laneExList.getRealPath()!=null&&!laneExList.getRealPath().isEmpty()){
                    	List pathPoints = getSubStringByCharNum(laneExList.getRealPath(), 4);
                        String[] strs = (String[]) pathPoints.toArray(new String[pathPoints.size()]);
                        for(String s:strs){
                        	preparedStatement2.setString(1, laneExList.getLaneExSerialNo());
                        	preparedStatement2.setString(2, s);
                        	preparedStatement2.setTimestamp(3, laneExList.getSquadDate());
                        	preparedStatement2.addBatch();
                        	//每200条标识点数据保存一次
                        	if(++pointNum % 200 == 0) {
                        		preparedStatement2.executeBatch();
                        		preparedStatement2.clearBatch();
                            }
                        }
                        //最后不满200条的标识点数据单独保存一次
                        preparedStatement2.executeBatch();
                		preparedStatement2.clearBatch();
                    }
                    
                }
                
            }
            if (preparedStatement != null) {
                preparedStatement.executeBatch();
            }
            if (preparedStatement1 != null) {
                preparedStatement1.executeBatch();
            }
            if (preparedStatement2 != null) {
                preparedStatement2.executeBatch();
            }
            if (connection != null) {
                connection.commit();
            }

            if (connection1 != null) {
                connection1.commit();
            }
            
            if (connection2 != null) {
                connection2.commit();
            }
            logger.info( "["+date[0]+"]"+"-"+"["+date[1]+"]"+count + "条出口流水数据保存到本地数据库成功。");
            qrtzLog.setDescription("["+date[0]+"]"+"-"+"["+date[1]+"]"+count + "条出口流水数据保存到本地数据库成功。");
            qrtzLog.setJobStatus(JobLog.JOB_STATUS_SUCC);

            long endTime = System.currentTimeMillis();
            System.out.println("执行了:" + (endTime - startTime) / 1000 + "s");
        } catch (Exception e) {
            // 事务回滚
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

            if (connection1 != null) {
                try {
                    connection1.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            
            if (connection2 != null) {
                try {
                    connection2.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
            logger.info("出口流水数据保存到本地数据库失败。");
            qrtzLog.setDescription("[" + date[0] + "]" + "-" + "[" + date[1] + "]" + "出口的流水数据保存到本地数据库失败。");
            qrtzLog.setJobStatus(JobLog.JOB_STATUS_FAIL);
        } finally {
            JDBCFrame.release(connection, preparedStatement, null);
            JDBCFrame.release(connection1, preparedStatement1, null);
            JDBCFrame.release(connection2, preparedStatement2, null);
        }
    }
    
    
    /**
     * 按指定长度 分割字符串
     * @param s
     * @param i
     * @return
     */
	public List<String> getSubStringByCharNum(String s, int length) {
		List<String> strs = new ArrayList<>((s.length() / length) + 1);
		if (length >= s.length()) {
			strs.add(s);
		} else {
			while (length < s.length()) {
				strs.add(s.substring(0, length));
				s = s.substring(length, s.length());
			}
			strs.add(s);
		}
		return strs;
	}
	
    /**
     * @param date
     */
    private List<LaneExList> getLaneExListFromOuterDatabase(String... date) {
        // 用jdbcFrame取数据
        JDBCFrame jdbcFrame = new JDBCFrame(DBUtil.getConnection());
        // 编写sql语句
        String sql = "SELECT \n" +
                "  ExRecordNo as exRecordNo,\n" +
                "  LaneExSerialNo as laneExSerialNo,\n" +
                "  LaneEnSerialNo as laneEnSerialNo,\n" +
                "  cardNetRoadID as cardNetRoadID,\n"+
                "  ICInCardID as icinCardId,\n" +
                "  EnNetRoadID as enNetRoadId,\n" +
                "  EnRoadID as enRoadId,\n" +
                "  EnStationID as enStationId,\n" +
                "  EnLaneID as enLaneId,\n" +
                "  EnLaneType as enLaneType,\n" +
                "  EnTime as enTime,\n" +
                "  enOperatorID as enOperatorID,\n"+
                "  enShiftID as enShiftID,\n"+
                "  EnVehicleClass as enVehicleClass,\n" +
                "  EnVehicleStatus as enVehicleStatus,\n" +
                "  EnVehiclePlate as enVehiclePlate,\n" +
                "  enVehicleFlag as enVehicleFlag,\n"+
                "  EnETCTermCode as EnETCTermCode,\n"+
                "  ExNetRoadID as exNetRoadId,\n" +
                "  ExRoadID as exRoadId,\n" +
                "  ExStationID as exStationId,\n" +
                "  ExLaneID as exLaneId,\n" +
                "  ExLaneType as exLaneType,\n" +
                "  ExTime as exTime,\n" +
                "  exOperatorID as exOperatorID,\n"+
                "  exOpCardNo as exOpCardNo,\n"+
                "  exOpCardID as exOpCardID,\n"+
                "  SquadDate as squadDate,\n" +
                "  exShiftID as exShiftID,\n"+
                "  ExCPCSnNo as exCpcsnNo,\n" +
                "  exCPCInID as exCPCInID,\n"+
                "  ExVehicleClass as exVehicleClass,\n" +
                "  ExVehicleStatus as exVehicleStatus,\n" +
                "  ImageSerialNo as imageSerialNo,\n" +
                "  cardBoxNo as cardBoxNo,\n"+
                "  cardTrunkNo as cardTrunkNo,\n"+
                "  appVer as appVer,\n"+
                "  ExVehiclePlate as exVehiclePlate,\n" +
                "  ExVehIdentifyPlate as exVehIdentifyPlate,\n" +
                "  TollType as tollType,\n" +
                "  CashMoney as cashMoney,\n" +
                "  invoiceID as invoiceID,\n"+
                "  ETCMoney as ETCMoney,\n"+
                "  freeMoney as freeMoney,\n"+
                "  officeMoney as officeMoney,\n"+
                "  unpayMoney as unpayMoney,\n"+
                "  VehicleClassMoney as vehicleClassMoney,\n" +
                "  downVehicleClassMoney as downVehicleClassMoney,\n"+
                "  officeCardSnNo as officeCardSnNo,\n"+
                "  ownerCode1 as ownerCode1,\n"+
                "  ownerCode2 as ownerCode2,\n"+
                "  ownerCode3 as ownerCode3,\n"+
                "  ownerCode4 as ownerCode4,\n"+
                "  dealStatus as dealStatus,\n"+
                "  DeviceStatus as deviceStatus,\n" +
                "  RecordType as recordType,\n" +
                "  exVehicleFlag as exVehicleFlag,\n"+
                "  exICCIssuer as exICCIssuer,\n"+
                "  ExCPUCardSnNo as exCpucardSnNo,\n" +
                "  ExCPUCardID as exCpucardId,\n" +
                "  ExCPUCardType as exCpucardType,\n" +
                "  exCpuCardPlate as exCpuCardPlate,\n"+
                "  exCpuCardPlateColor as exCpuCardPlateColor,\n"+
                "  exCpuCardUserType as exCpuCardUserType,\n"+
                "  WeightFlag as weightFlag,\n" +
                "  ticketType as ticketType,\n"+
                "  payCardType as payCardType,\n"+
                "  VehCount as vehCount,\n" +
                "  AxisNum as axisNum,\n" +
                "  AxisGroupNum as axisGroupNum,\n" +
                "  TotalWeight as totalWeight,\n" +
                "  TotalWeightLimit as totalWeightLimit,\n" +
                
                "  AxisType as axisType,\n" +
                "  AxisWeightDetail as axisWeightDetail,\n" +
                "  OverLoadWeight as  overLoadWeight,\n" +
                "  previousAxisType as previousAxisType,\n"+
                "  previousTotalWeight as previousTotalWeight,\n"+
                "  truckLimitVerNo as truckLimitVerNo,\n"+
                "  truckLimitPriceVerNo as truckLimitPriceVerNo,\n"+
                "  PreVehType as preVehType,\n" +
                "  preVehMoney as preVehMoney,\n"+
                "  payCardBalanceBefore as payCardBalanceBefore,\n"+
                "  payCardBalanceAfter as payCardBalanceAfter,\n"+
                "  OBUNum as OBUNum,\n"+
                "  OBUID as OBUID,\n"+
                "  OBEState as OBEState,\n"+
                "  OBUPlate as OBUPlate,\n"+
                "  OBUPlateColor as OBUPlateColor,\n"+
                "  exETCTradeNo as exETCTradeNo,\n"+
                "  exETCTac as exETCTac,\n"+
                "   exETCTermTradNo as exETCTermTradNo,\n"+
                "  ExETCTermCode as exEtctermCode,\n" +
                "  rebateType as rebateType,\n"+
                "  rebateVerNo as rebateVerNo,\n"+
                "  rebateRate as rebateRate,\n"+
                "  preRebateFee  as preRebateFee,\n"+
                "  rebateFee as rebateFee,\n"+
                "  flagType as flagType,\n"+
                "  OriginalPath as originalPath,\n" +
                "  RealPath as realPath,\n" +
                "  RoadComb as roadComb,\n" +
                "  moneyComb as moneyComb ,\n"+
                "  RoadSStationComb as roadSstationComb,\n" +
                "  RoadEStationComb as roadEstationComb,\n" +
                "  RealPath01 as realPath01,\n" +
                "  tollRateVer as tollRateVer,\n"+
                "  listName as listName,\n"+
                "  VoidSerialNo as VoidSerialNo,\n"+
                "  ManualFlag as manualFlag,\n" +
                "  VerifyCode as verifyCode,\n" +
                "  CPCCurrentVol as CPCCurrentVol,\n"+
                "  miles as miles,\n"+
                "  translationMiles as translationMiles,\n"+
                "  tollCardFreeListVerNo as tollCardFreeListVerNo,\n"+
                "  Spare1 as spare1,\n" +
                "  Spare2 as spare2,\n" +
                "  Spare3 as spare3,\n" +
                "  Spare4 as spare4,\n" +
                "  UploadTime as uploadTime\n" +
                "  FROM tb_LaneExList_Mid\n" +
                "WHERE CONVERT(VARCHAR(100), UploadTime, 23) >= ? AND CONVERT(VARCHAR(100), UploadTime, 120) <= ?";
        System.out.println(sql);
        // 查询
        List<Object> params = new ArrayList<Object>();
        params.add(date[0]);
        params.add(date[1]);
        List<LaneExList> laneExLists = null;
        try {
            laneExLists = jdbcFrame.findMoreRefResult(sql, params, LaneExList.class);
        } catch (Exception e) {
            logger.warn("从中间库获取数据失败");
            return null;
        } finally {
            // 关闭连接
            jdbcFrame.releaseConn();
        }

        // 返回
        return laneExLists;
    }

    /**
     * 获取根据流水获取路径名字
     */
    public List queryNameBypath(List flaglist) {
        // TODO Auto-generated method stub
        return laneExListDao.queryNameBypath(flaglist);
    }
	 /**
     * 分页获取出口流水 
     */
	public List<Object[]> queryAllLaneExList(LaneExList2 entity, Pager pager, Admin operator) {
		return laneExListDao.findByLaneExListEntity(pager, entity, operator);
	}
	 /**
     * 获取入口流水 
     */
	public List<Object[]> queryAllLaneEnList(LaneExList2 entity, Pager pager, Admin operator){
		List<Object[]> list = new ArrayList<Object[]>();
		list=laneExListDao.findByLaneExListEntity(pager, entity, operator);
		return list;
	}
	 /**
     * 不分页获取出口流水 
     */
	public List<Object[]> queryAllLaneExList2(LaneExList2 entity, Pager pager, Admin operator) {
		return laneExListDao.findByLaneExListEntity2(pager, entity, operator);
	}
	/**
     * 根据出口获取入口流水 接口
     */
	public String getProvinceEnList(LaneExList2 entity) throws IOException{
		String validateURL = "http://"+provinceIp+":"+provincePort+"/openservice/services/http/entranceInfo?VehiclePlate="+java.net.URLEncoder.encode(entity.getExVehiclePlate().trim(), "UTF-8")+"&CPCSnNo="+entity.getExCpcsnNo().trim()+"&StartTime="+java.net.URLEncoder.encode(entity.getStartTime(), "UTF-8")+"&EndTime="+java.net.URLEncoder.encode(entity.getEndTime(), "UTF-8");
		HttpURLConnection conn = null;
		DataInputStream dis = null;
		FileOutputStream fos = null;
		try{
			URL url = new URL(validateURL);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.connect();
			
			
			// 判断是否正常响应数据
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				System.out.println("网络正常！!!!");
				
				System.out.println("responseCode:"+conn.getHeaderField("responseCode"));
				
				System.out.println(conn.getContent());
				
				
				InputStream inputStream = conn.getInputStream();
				
				System.out.println(inputStream.available());
				
				
				dis = new DataInputStream(inputStream);
				byte[] buffer = new byte[1024];
				int len = 0;
				fos = new FileOutputStream(new File("E:/7.txt"));
				while((len = inputStream.read(buffer))!=-1){
					fos.write(buffer, 0, len);
					
				}
			} else{
				System.out.println("网络错误异常！!!!");
			}
		
		
		} catch(Exception e){

			e.printStackTrace();
		} finally{
			conn.disconnect();
			if(dis!=null){
				try {
					dis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		return fos.toString();
	}
	
	public byte[] readInputStram(InputStream inputStream) throws IOException{
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while((len = inputStream.read(buffer))!=-1){
			outStream.write(buffer, 0, len);
		}
		
		byte[] data = outStream.toByteArray();
		
		outStream.close();
		inputStream.close();
		
		return data;
	}
	/**
     * 根据出口获取入口流水 
     */
	public Map exList(LaneExList2 entity, Pager pager, Admin operator) {
		String testStr="";
		List<Object[]> list = new ArrayList<Object[]>();
		int Flag ;
    	if(!roadCode.equals(entity.getEnRoadId())){ 
    		Flag=1; //1：使用省营运协会的接口获取数据(非本路段)
    		try {
				testStr=getProvinceEnList(entity);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else{
			Flag=0;
			list=queryAllLaneEnList(entity,pager,operator);
		}
    	map.put("test", testStr);
		map.put("Flag", Flag);
		map.put("list", list);
		return map;
	}
	/**
     * 根据出口获取路径中文名
	 * @return 
     */
	public Map queryName(LaneExList2 entity, Pager pager, Admin operator) {
		List<Object[]> List2 =queryAllLaneExList2(entity, pager, operator);
		long laneExNum = 0;
		String time="";
		ArrayList<Integer> flagIdList = new ArrayList<>();//标示点集合
		if (List2 != null & List2.size() > 0) {
			
			for (int i = 0; i < List2.size(); i++) {
				Object[] oi = (Object[]) List2.get(i);
				String iHex = oi [15].toString();
				time =oi [0].toString();
				for (int j = 0; j < iHex.length();) {
					String s = iHex.substring(j, j + 4);
					j += 4;
					flagIdList.add(Integer.parseInt(s, 16));
				}
			}
		}
		List list =queryNameBypath(flagIdList);
		laneExNum = list.size();
		map.put("list", list);
		map.put("time", time);
		map.put("status", laneExNum);
		return map;
	}
	/**
     * txt数量查询 
     */
	public Map explortTXTNum(LaneExList2 entity, Pager pager, Admin operator) {
		
		FileOutputStream outSTr = null;
		BufferedOutputStream Buff = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		String path ="E:/LaneExList.txt";
		String enter = "\r\n";
		List list = queryAllLaneExList2(entity, pager, operator);
		StringBuffer write;
		long laneExNum = 0;
		try {
			outSTr = new FileOutputStream(new File(path));
			Buff = new BufferedOutputStream(outSTr);
			if (list != null & list.size() > 0) {
				laneExNum = list.size();
				for (int i = 0; i < list.size(); i++) {
					Object[] oi = (Object[]) list.get(i);
					write = new StringBuffer();
					write.append("出    口    日    期|出 口 流 水 号|出口车道号|出口识别车牌|出口车牌|交易状态");
					write.append(enter);
					write.append( oi [0]+ "|" + oi [1] + "|"
							+ oi [4] + "|" + oi [6] + "|"+ oi [7] + "|"
							+ oi [16].toString().replace("&&&&", " "));
					write.append(enter);
					Buff.write(write.toString().getBytes("UTF-8"));
				}
			}
			Buff.flush();
			Buff.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				Buff.close();
				outSTr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		map.put("status", laneExNum);
		return map;
	}
	/**
     * 获取txt流 
     */
	public InputStream getTxt(LaneExList2 entity, Pager pager, Admin operator) {	
	String enter = "\r\n";
	List list = queryAllLaneExList2(entity, pager, operator);
	StringBuffer write;
	long laneExNum = 0;
	FileInputStream fileIn=null;
	File f=null;
	FileOutputStream out=null;
	try {
		f = File.createTempFile("temp", ".txt");
		out=new FileOutputStream(f);
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	try {
		if (list != null & list.size() > 0) {
			laneExNum = list.size();
			for (int i = 0; i < list.size(); i++) {
				Object[] oi = (Object[]) list.get(i);
				write = new StringBuffer();
				write.append("出    口    日    期|出 口 流 水 号|出口车道号|出口识别车牌|出口车牌|交易状态");
				write.append(enter);
				write.append( oi [0]+ "|" + oi [1] + "|"
						+ oi [4] + "|" + oi [6] + "|"+ oi [7] + "|"
						+ oi [16].toString().replace("&&&&", " "));
				write.append(enter);
				out.write(write.toString().getBytes());
				fileIn=new FileInputStream(f);
				f.delete();
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally{
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return fileIn; 
  }
}
