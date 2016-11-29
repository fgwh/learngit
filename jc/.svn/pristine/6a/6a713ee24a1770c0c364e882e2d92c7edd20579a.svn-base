package com.hgsoft.main.laneenlist.service;


import com.hgsoft.job.GetLaneEnListDataJob;
import com.hgsoft.job.JobLog;
import com.hgsoft.main.entity.QrtzLog;
import com.hgsoft.main.laneenlist.dao.LaneEnListDao;
import com.hgsoft.main.laneenlist.entity.LaneEnList;
import com.hgsoft.security.service.BaseService;
import com.hgsoft.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 入口流水service
 *
 * @author 郭志强
 * @time 2016/9/9 13:26
 */
@Service
public class LaneEnListService extends BaseService<LaneEnList> {
    private static Logger logger = Logger.getLogger(GetLaneEnListDataJob.class);

    // 注入dao
    @Autowired
    private LaneEnListDao laneEnListDao;

    public synchronized void saveLaneEnList(QrtzLog qrtzLog, String... date) {

        System.out.println(date[0]);
        System.out.println(date[1]);

        qrtzLog.setCreatetime(new Date());
        // 从中间库中取出数据
        List<LaneEnList> list = getLaneEnListFromOuterDatabase(date);
        System.out.println("共获取到："+list.size()+"入口数据");
        Connection connection = null;
        Connection connection1 = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        try {
            long startTime = System.currentTimeMillis();
            connection = LocalDBUtils.getConnection();
            connection1 = DBUtil.getConnection();

            connection.setAutoCommit(false);
            connection1.setAutoCommit(false);
            int count = 0;
            // 判断list,并遍历list
            if (list != null && list.size() > 0) {
                for (LaneEnList laneEnList : list) {
                    Timestamp enTime = laneEnList.getEnTime();
                    // 截取入口时间年份
                    String y = DateUtil.format(enTime, "yyyy");
                    StringBuilder sb = new StringBuilder();
                    sb.append("INSERT INTO tb_LaneEnList_");
                    // 这里需要改成year
                    sb.append(y);
                    sb.append("(EnRecordNo,LaneEnSerialNo,CardNetRoadID,ICInCardID,EnNetRoadID,EnRoadID,EnStationID,EnLaneID,EnLaneType,EnTime,EnOperatorID,EnOpCardNo,EnOpCardID,EnShiftID,EnCPCSnNo,EnCPCInID,EnVehicleClass,EnVehicleStatus,EnVehiclePlate,EnVehIdentifyPlate,EnVehicleFlag,EnICCIssuer,EnCPUCardSnNo,EnCPUCardID,EnCPUCardType,EnETCTermCode,EnOBUNum,EnETCTradNo,EnETCTermTradNo,EnETCTac,SquadDate,ImageSerialNo,CardBoxNo,CardTrunkNo,AppVer,DealStatus,DeviceStatus,RecordType,VehCount,TicketType,ListName,BillNo,VoidSerialNo,VerifyCode,CPCCurrentVol,Spare1,Spare2,Spare3,Spare4,UploadTime)");
                    sb.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    // 打印出sql语句
//                    System.out.println(sb.toString());
                    List<Object> params = new ArrayList<Object>();
                    // 封装参数
                    LaneEnAndExUtils.encapsulationLanEnParams(laneEnList, params);
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
                    preparedStatement1 = connection1.prepareStatement("DELETE FROM tb_LaneEnList_Mid WHERE LaneEnSerialNo=?");
                    preparedStatement1.setString(1, laneEnList.getLaneEnSerialNo());
                    preparedStatement1.addBatch();
                    preparedStatement1.executeBatch();
                    preparedStatement1.clearBatch();
                    count++;
                }
            }
            if (preparedStatement != null) {
                preparedStatement.executeBatch();
            }
            if (preparedStatement1 != null) {
                preparedStatement1.executeBatch();
            }

            if (connection != null) {
                connection.commit();
            }

            if (connection1 != null) {
                connection1.commit();
            }
            logger.info("["+date[0]+"]"+"-"+"["+date[1]+"]"+count + "条入口流水数据保存到本地数据库成功。");
            qrtzLog.setDescription("["+date[0]+"]"+"-"+"["+date[1]+"]"+count + "条入口流水数据保存到本地数据库成功。");
            qrtzLog.setJobStatus(JobLog.JOB_STATUS_SUCC);
            long endTime = System.currentTimeMillis();

            System.out.println("执行了:" + (endTime - startTime)/1000 + "s");
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
            e.printStackTrace();
            logger.info("入口流水数据保存到本地数据库失败。");
            qrtzLog.setDescription("[" + date[0] + "]" + "-" + "[" + date[1] + "]" + "入口的流水数据保存到本地数据库失败。");
            qrtzLog.setJobStatus(JobLog.JOB_STATUS_FAIL);


        } finally {
            JDBCFrame.release(connection, preparedStatement, null);
            JDBCFrame.release(connection1, preparedStatement1, null);
        }

    }

    /**
     * @param date
     */
    private List<LaneEnList> getLaneEnListFromOuterDatabase(String... date) {
        // 用jdbcFrame取数据
        JDBCFrame jdbcFrame = new JDBCFrame(DBUtil.getConnection());
        // 编写sql语句
        String sql = "SELECT EnRecordNo as enRecordNo\n" +
                "      ,LaneEnSerialNo as laneEnSerialNo\n" +
                "      ,CardNetRoadID as cardNetRoadId\n" +
                "      ,ICInCardID as icinCardId\n" +
                "      ,EnNetRoadID as enNetRoadId\n" +
                "      ,EnRoadID as enRoadId\n" +
                "      ,EnStationID as enStationId\n" +
                "      ,EnLaneID as enLaneId\n" +
                "      ,EnLaneType as enLaneType\n" +
                "      ,EnTime as enTime\n" +
                "      ,EnOperatorID as enOperatorId\n" +
                "      ,EnOpCardNo as enOpCardNo\n" +
                "      ,EnOpCardID as enOpCardId\n" +
                "      ,EnShiftID as enShiftId\n" +
                "      ,EnCPCSnNo as enCpcsnNo\n" +
                "      ,EnCPCInID as enCpcinId\n" +
                "      ,EnVehicleClass as enVehicleClass\n" +
                "      ,EnVehicleStatus as enVehicleStatus\n" +
                "      ,EnVehiclePlate as enVehiclePlate\n" +
                "      ,EnVehIdentifyPlate as enVehIdentifyPlate\n" +
                "      ,EnVehicleFlag as enVehicleFlag\n" +
                "      ,EnICCIssuer as enIccissuer\n" +
                "      ,EnCPUCardSnNo as enCpucardSnNo\n" +
                "      ,EnCPUCardID as enCpucardId\n" +
                "      ,EnCPUCardType as enCpucardType\n" +
                "      ,EnETCTermCode as enEtctermCode\n" +
                "      ,EnOBUNum as enObunum\n" +
                "      ,EnETCTradNo as enEtctradNo\n" +
                "      ,EnETCTermTradNo as enEtctermTradNo\n" +
                "      ,EnETCTac as enEtctac\n" +
                "      ,SquadDate as squadDate\n" +
                "      ,ImageSerialNo as imageSerialNo\n" +
                "      ,CardBoxNo as cardBoxNo\n" +
                "      ,CardTrunkNo as cardTrunkNo\n" +
                "      ,AppVer as appVer\n" +
                "      ,DealStatus as dealStatus\n" +
                "      ,DeviceStatus as deviceStatus\n" +
                "      ,RecordType as recordType\n" +
                "      ,VehCount as vehCount\n" +
                "      ,TicketType as ticketType\n" +
                "      ,ListName as listName\n" +
                "      ,BillNo as billNo\n" +
                "      ,VoidSerialNo as voidSerialNo\n" +
                "      ,VerifyCode as verifyCode\n" +
                "      ,CPCCurrentVol as cpccurrentVol\n" +
                "      ,Spare1 as spare1\n" +
                "      ,Spare2 as spare2\n" +
                "      ,Spare3 as spare3\n" +
                "      ,Spare4 as spare4\n" +
                "      ,UploadTime as uploadTime\n" +
                "  FROM tb_LaneEnList_Mid ";
        List<Object> params = null;
        // 根据天数查询
        sql += " WHERE CONVERT(VARCHAR(100), UploadTime, 23) >= ? AND CONVERT(VARCHAR(100), UploadTime, 120) <= ?";
        params = new ArrayList<Object>();
        params.add(date[0]);
        params.add(date[1]);
        List<LaneEnList> laneEnLists = null;
        try {
            laneEnLists = jdbcFrame.findMoreRefResult(sql, params, LaneEnList.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcFrame.releaseConn();
        }

        return laneEnLists;
    }

}
