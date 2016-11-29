package com.hgsoft.util;

/**
 * @author huang_cx
 * @date 2013-05-21
 * @version 1.0
 * @Description 管理关于定时任务中SQL
 */
public abstract class JobSQL {
	/**
	 * @version 1.0
	 * @Description 数据汇总Sql
	 */
	public final static class DataCollect
	{
		/**
		 * select x.*,
		       ISNULL( x.UnsuccessCnt-x.ComFailCnt,0) BssFailCnt,
		       ISNULL( case when  x.CountSpeed=0 then 0 else  round((x.TotalSpeed/x.CountSpeed ),4) end ,0) as AVGSpeed   from (
			select 
				trad.RoadNo, 
				trad.StationNo, 
				trad.LaneNo,
				lane.LaneType, 
				trad.SquadDate, 
				datepart(Hour,isnull(trad.EnterTime,trad.LeaveTime)) as  Hour, 
				COUNT(1) AS TotalVehCnt, 
				SUM(CASE WHEN OBUNo IS NOT NULL AND OBUNO <> '' THEN 1 ELSE 0 END) AS OBUCnt,
				SUM(CASE WHEN OBUNo IS NULL OR  OBUNO = '' THEN 1 ELSE 0 END) AS OBUNotFound,
				SUM(CASE WHEN TRADERESULT >= 1 THEN 1 ELSE 0 END) AS SuccessCnt, 
				---通信异常
				SUM(CASE WHEN TRADERESULT < 1 AND OBUNo IS NOT NULL AND OBUNO <> '' THEN 1 ELSE 0 END) AS UnsuccessCnt,
				--tradebreak
				SUM(CASE WHEN TRADERESULT < 1 AND  LASTALARMTYPE = 'ALM028' AND OBUNo IS NOT NULL
									AND OBUNO <> '' THEN 1 ELSE 0 END)   AS TRADEBREAK, --交易未完
				--tradefaile
				SUM(CASE WHEN TRADERESULT < 1 AND  LASTALARMTYPE = 'ALM020' AND OBUNo IS NOT NULL 
										AND OBUNO <> '' THEN 1 ELSE 0 END) AS TRADEFAILE, --交易失败
				SUM(CASE WHEN TRADERESULT < 1 AND  LASTALARMTYPE = 'ALM027' AND OBUNo IS NOT NULL
										AND OBUNO <> '' THEN 1 ELSE 0 END) AS OVERTIME, --读超时		
				SUM(CASE WHEN  TRADERESULT < 1 AND ( LastAlarmType In( 'ALM020','ALM027','ALM028','') or LastAlarmType  IS null ) 
								AND OBUNo IS NOT NULL AND OBUNO <> '' THEN 1 ELSE 0 END) AS ComFailCnt, 
				---业务拒绝	
				SUM(CASE WHEN TRADERESULT < 1 AND OBUNo IS NOT NULL  AND OBUNO <> ''
								 AND LASTALARMTYPE = 'ALM001' THEN 1 ELSE 0 END) AS NotFoundOBU,   ---无标签	
				SUM(CASE WHEN TRADERESULT < 1 AND OBUNo IS NOT NULL AND OBUNO <> ''
								 AND LASTALARMTYPE = 'ALM014' THEN 1 ELSE 0 END) AS NOENTRY,      ---无入口
				SUM(CASE WHEN TRADERESULT < 1 AND  OBUNo IS NOT NULL AND OBUNO <> '' 
								AND LASTALARMTYPE = 'ALM021' THEN 1 ELSE 0 END)  AS OBUDEMOUNT,   ---非法拆卸
				SUM(CASE WHEN TRADERESULT < 1 AND OBUNo IS NOT NULL AND OBUNO <> '' 
								AND  LASTALARMTYPE = 'ALM023' THEN 1 ELSE 0 END) AS UTURN,        --- 回头车
				SUM(CASE WHEN TRADERESULT < 1 AND OBUNo IS NOT NULL  AND OBUNO <> ''
								AND  LASTALARMTYPE = 'ALM024'  THEN 1 ELSE 0 END) AS CARDNOTFOUND, ---卡未插好
				SUM(CASE WHEN TRADERESULT < 1 AND OBUNo IS NOT NULL AND OBUNO <> ''
								AND LASTALARMTYPE = 'ALM026' THEN 1 ELSE 0 END) AS VEHPAID,        ---重放行
				SUM(CASE WHEN TRADERESULT < 1 AND OBUNo IS NOT NULL  AND OBUNO <> ''
								AND LASTALARMTYPE = 'ALM003' THEN 1 ELSE 0 END) AS NOMONEY,         ---余额不足		
				--BSSFAILCNT		                
				SUM(CASE WHEN TRADERESULT >= 1 THEN  Money  ELSE 0 END) AS VehMoneyCnt, 
		
				-- 远近区交易统计
				SUM(CASE WHEN TRADERESULT >= 1 AND TradeRSU = '1' THEN 1 ELSE 0 END) AS FARRSUCNT,
				SUM(CASE WHEN TRADERESULT >= 1 AND TradeRSU = '2' THEN 1 ELSE 0 END) AS NEARRSUCNT,
				SUM(CASE WHEN TRADERESULT >= 1 AND TradeRSU = '0' THEN 1 ELSE 0 END) AS CLOSERSUCNT,
		
				--各地感交易统计
				SUM(CASE WHEN TRADERESULT >= 1 AND TradeLoop = '0' THEN 1 ELSE 0 END) AS Loop0TradeCNT,
				SUM(CASE WHEN TRADERESULT >= 1 AND TradeLoop = '1' THEN 1 ELSE 0 END) AS Loop1TradeCNT,
				SUM(CASE WHEN TRADERESULT >= 1 AND TradeLoop = '2' THEN 1 ELSE 0 END) AS Loop2TradeCNT,
				SUM(CASE WHEN TRADERESULT >= 1 AND TradeLoop = '3' THEN 1 ELSE 0 END) AS Loop3TradeCNT,
				SUM(CASE WHEN TRADERESULT >= 1 AND TradeLoop = '4' THEN 1 ELSE 0 END) AS Loop4TradeCNT,
				SUM(CASE WHEN TRADERESULT >= 1 AND TradeLoop = '5' THEN 1 ELSE 0 END) AS Loop5TradeCNT,
				SUM(CASE WHEN TRADERESULT >= 1 AND TradeLoop = '6' THEN 1 ELSE 0 END) AS Loop6TradeCNT,
				SUM(CASE WHEN TRADERESULT >= 1 AND TradeLoop = '7' THEN 1 ELSE 0 END) AS Loop7TradeCNT,
				SUM(CASE WHEN TRADERESULT >= 1 AND TradeLoop = '8' THEN 1 ELSE 0 END) AS Loop8TradeCNT,
		
				--统计报警数量
				SUM(CASE WHEN   LastAlarmType <>'' THEN 1 ELSE 0 END) AS TotalAlarmCnt,
				( select COUNT (1) as LaneAlamCnt from   dbo.tb_LaneEventWatch lew
				 where   trad.RoadNo=lew.RoadNo   and   trad.StationNo=lew.StationNo
					 and   trad.LaneNo=lew.LaneNo   and   trad.Squaddate =lew.Squaddate 
					 and   datepart(Hour,ISNULL(trad.EnterTime,trad.LeaveTime )) = datepart(Hour,lew.EventTime) 
				group  by RoadNo ,StationNo , LaneNo , Squaddate, datepart(Hour,lew.EventTime)  ) as LaneAlamCnt,
				--车道故障数量
				( select count(1)  as  LaneFailCnt   from  dbo.tb_LaneFailureWatch  lfw
				where  0<>PATINDEX('%2',FAILURECODE) and  trad.RoadNo=lfw.RoadNo   
		     and   trad.StationNo=lfw.StationNo  and   trad.LaneNo=lfw.LaneNo   
		     and   trad.Squaddate =lfw.Squaddate 
				and   datepart(Hour,ISNULL(trad.EnterTime,trad.LeaveTime )) = datepart(Hour,lfw.FailureTime) 
				group  by RoadNo ,StationNo , LaneNo ,Squaddate ,  datepart(Hour,lfw.FailureTime) )   TotalFailCnt ,
				--当前车道故障数量
				( select  sum(case when FailureStatus =1 then 1 else 0 end )  as  CurLaneFailCnt   from  dbo.tb_LaneFailureWatch lfw
				where  0<>PATINDEX('%2',FAILURECODE) and  trad.RoadNo=lfw.RoadNo   and   trad.StationNo=lfw.StationNo
				and   trad.LaneNo=lfw.LaneNo   and   trad.Squaddate =lfw.Squaddate 
				and   datepart(Hour,ISNULL(trad.EnterTime,trad.LeaveTime )) = datepart(Hour,lfw.FailureTime) 
				group  by RoadNo ,StationNo , LaneNo ,Squaddate ,  datepart(Hour,lfw.FailureTime) )   CurLaneFailCnt  ,
		
		
				--过车耗时主要统计成功交易车辆
				SUM(CASE WHEN TRADERESULT >= 1 THEN  cast(B2_Cost as bigint) ELSE 0 END) AS SucB2CostCnt,   
				SUM(CASE WHEN TRADERESULT >= 1 THEN  cast(B3_Cost as bigint) ELSE 0 END) AS SucB3CostCnt, 
				SUM(CASE WHEN TRADERESULT >= 1 THEN  cast(B4_Cost as bigint) ELSE 0 END) AS SucB4CostCnt, 
				SUM(CASE WHEN TRADERESULT >= 1 THEN  cast(B5_Cost as bigint) ELSE 0 END) AS SucB5CostCnt, 
				SUM(CASE WHEN TRADERESULT >= 1 THEN  
				cast( B3_Cost +B4_Cost +B5_Cost as  bigint)  ELSE 0 END) AS SucTradeCostCnt,
		
				--过车通信次数主要统计成功交易车辆
				SUM(CASE WHEN TRADERESULT >= 1 THEN  cast(B2_Cnt as bigint)  ELSE 0 END) AS SucB2CntCnt,  
				SUM(CASE WHEN TRADERESULT >= 1 THEN  cast(B3_Cnt as bigint)  ELSE 0 END) AS SucB3CntCnt,  
				SUM(CASE WHEN TRADERESULT >= 1 THEN  cast(B4_Cnt as bigint)  ELSE 0 END) AS SucB4CntCnt,  
				SUM(CASE WHEN TRADERESULT >= 1 THEN  cast(B5_Cnt as bigint)  ELSE 0 END) AS SucB5CntCnt,   
				SUM(CASE WHEN TRADERESULT >= 1 THEN 
				cast(B3_Cnt +B4_Cnt +B5_Cnt  as bigint )  ELSE 0 END) AS SucTradeCntCnt,
		
				--高速过车主要统计成功交易车辆
				SUM(CASE WHEN TRADERESULT >= 1 and  B3_Cnt +B4_Cnt +B5_Cnt<280  then 1  ELSE 0 END) AS HighQualityCnt,
				SUM(CASE WHEN TRADERESULT >= 1 and  Speed/100.00>15 THEN  1  ELSE 0 END) AS HighSpeedCnt,  
				SUM(CASE WHEN TRADERESULT >= 1 and  B3_Cnt<=1  THEN  1  ELSE 0 END) AS OnceTradeCnt,
				SUM(CASE WHEN TRADERESULT >= 1 and  Speed>0  and Speed/100.00<60 
				THEN  Speed  ELSE 0 END) AS TotalSpeed,  
				SUM(CASE WHEN TRADERESULT >= 1 and  Speed>0  and Speed/100.00<60 
				THEN   1     ELSE 0 END) AS CountSpeed,
						
		
			--AVGSPEED
			--backup1
		    null as backup1,
			--backup2
			  null as backup2,
			--backup3
		    null as backup3,
			--backup4
			  null as backup4
		
			from  dbo.tb_TradingInfo_ForSum trad,dbo.tb_LaneInfo lane     
			where trad.RoadNo=lane.RoadNo   and   trad.StationNo=lane.StationNo  and   trad.LaneNo=lane.LaneNo 
			group  by  trad.RoadNo,trad.StationNo,trad.LaneNo,lane.LaneType,trad.SquadDate,datepart(Hour,ISNULL(trad.EnterTime,trad.LeaveTime ))
		) x

		 */
		public final static String select = "";
		/**
		 * 
		 *insert into tb_TradingInfo_HourSum
			(
				RoadNo,StationNo,LaneNo,LaneType,
				SquadDate,Hour,TotalVehCnt,OBUCnt,
				OBUNotFound,SuccessCnt,UnsuccessCnt,
				TradeBreak,TradeFaile,OverTime,ComFailCnt,
				NotFoundOBU,NoEntry,OBUDemount,UTurn,
				CardNotFound,VehPaid,NoMoney,VehMoneyCnt,
				FarRSUCnt,NearRSUCnt,CLOSERSUCNT,Loop0TradeCnt,
				Loop1TradeCnt,Loop2TradeCnt,Loop3TradeCnt,
				Loop4TradeCnt,Loop5TradeCnt,Loop6TradeCnt,
				Loop7TradeCnt,Loop8TradeCnt,TotalAlarmCnt,
				LaneAlarmCnt,TotalFailCnt,CurLaneFailCnt,
				SucB2CostCnt,SucB3CostCnt,SucB4CostCnt,
				SucB5CostCnt,SucTradeCostCnt,SucB2CntCnt,
				SucB3CntCnt,SucB4CntCnt,SucB5CntCnt,
				SucTradeCntCnt,HighQualityCnt,HighSpeedCnt,
				OnceTradeCnt,TotalSpeed,CountSpeed,
				Backup1,Backup2,Backup3,Backup4,
				BssFailCnt,AVGSpeed
			) 
			select x.*,
			       ISNULL( x.UnsuccessCnt-x.ComFailCnt,0) BssFailCnt,--业务拒绝
			       ISNULL( case when  x.CountSpeed=0 then 0 else  round((x.TotalSpeed/x.CountSpeed ),4) end ,0) as AVGSpeed   
			from (
				select 
					trad.RoadNo ,-- 路段 
					trad.StationNo, --收费站
					trad.LaneNo,--车道
					lane.LaneType, --车道类型信息
					trad.SquadDate, --工班日期
					datepart(Hour,isnull(trad.EnterTime,trad.LeaveTime)) as  Hour, 
					COUNT(1) AS TotalVehCnt,--车道流量
					ISNULL(SUM(CASE WHEN OBUNo IS NOT NULL AND OBUNO <> '' THEN 1 ELSE 0 END),0) AS OBUCnt,--发现标签数
					ISNULL(SUM(CASE WHEN OBUNo IS NULL OR  OBUNO = '' THEN 1 ELSE 0 END),0) AS OBUNotFound,--未发现标签数
					ISNULL(SUM(CASE WHEN TRADERESULT >= 1 THEN 1 ELSE 0 END),0) AS SuccessCnt, --交易成功数
					ISNULL(SUM(CASE WHEN TRADERESULT < 1 AND OBUNo IS NOT NULL AND OBUNO <> '' THEN 1 ELSE 0 END),0) AS UnsuccessCnt,--交易失败数
				   --通信异常
					ISNULL(SUM(CASE WHEN TRADERESULT < 1 AND  LASTALARMTYPE = 'ALM028' AND OBUNo IS NOT NULL
										AND OBUNO <> '' THEN 1 ELSE 0 END),0)   AS TRADEBREAK, --交易未完
					ISNULL(SUM(CASE WHEN TRADERESULT < 1 AND  LASTALARMTYPE = 'ALM020' AND OBUNo IS NOT NULL 
											AND OBUNO <> '' THEN 1 ELSE 0 END),0) AS TRADEFAILE, --交易失败
					ISNULL(SUM(CASE WHEN TRADERESULT < 1 AND  LASTALARMTYPE = 'ALM027' AND OBUNo IS NOT NULL
											AND OBUNO <> '' THEN 1 ELSE 0 END),0) AS OVERTIME, --读超时		
					ISNULL(SUM(CASE WHEN  TRADERESULT < 1 AND ( LastAlarmType In( 'ALM020','ALM027','ALM028','') or LastAlarmType  IS null )   
									AND OBUNo IS NOT NULL AND OBUNO <> '' THEN 1 ELSE 0 END),0) AS ComFailCnt, ---通信异常
					ISNULL(SUM(CASE WHEN TRADERESULT < 1 AND OBUNo IS NOT NULL  AND OBUNO <> ''
									 AND LASTALARMTYPE = 'ALM001' THEN 1 ELSE 0 END),0) AS NotFoundOBU,   ---无标签	
					ISNULL(SUM(CASE WHEN TRADERESULT < 1 AND OBUNo IS NOT NULL AND OBUNO <> ''
									 AND LASTALARMTYPE = 'ALM014' THEN 1 ELSE 0 END),0) AS NOENTRY,      ---无入口
					ISNULL(SUM(CASE WHEN TRADERESULT < 1 AND  OBUNo IS NOT NULL AND OBUNO <> '' 
									AND LASTALARMTYPE = 'ALM021' THEN 1 ELSE 0 END),0)  AS OBUDEMOUNT,   ---非法拆卸
					ISNULL(SUM(CASE WHEN TRADERESULT < 1 AND OBUNo IS NOT NULL AND OBUNO <> '' 
									AND  LASTALARMTYPE = 'ALM023' THEN 1 ELSE 0 END),0) AS UTURN,        --- 回头车
					ISNULL(SUM(CASE WHEN TRADERESULT < 1 AND OBUNo IS NOT NULL  AND OBUNO <> ''
									AND  LASTALARMTYPE = 'ALM024'  THEN 1 ELSE 0 END),0) AS CARDNOTFOUND, ---卡未插好
					ISNULL(SUM(CASE WHEN TRADERESULT < 1 AND OBUNo IS NOT NULL AND OBUNO <> ''
									AND LASTALARMTYPE = 'ALM026' THEN 1 ELSE 0 END),0) AS VEHPAID,        ---重放行
					ISNULL(SUM(CASE WHEN TRADERESULT < 1 AND OBUNo IS NOT NULL  AND OBUNO <> ''
									AND LASTALARMTYPE = 'ALM003' THEN 1 ELSE 0 END),0) AS NOMONEY,         ---余额不足		
					--BSSFAILCNT		                
					ISNULL(SUM(CASE WHEN TRADERESULT >= 1 THEN  Money  ELSE 0 END),0) AS VehMoneyCnt, 
			
					-- 远近区交易统计
					ISNULL(SUM(CASE WHEN TRADERESULT >= 1 AND TradeRSU = '1' THEN 1 ELSE 0 END),0) AS FARRSUCNT,--远区天线交易
					ISNULL(SUM(CASE WHEN TRADERESULT >= 1 AND TradeRSU = '2' THEN 1 ELSE 0 END),0) AS NEARRSUCNT,--近区天线交易
					ISNULL(SUM(CASE WHEN TRADERESULT >= 1 AND TradeRSU = '0' THEN 1 ELSE 0 END),0) AS CLOSERSUCNT,--天线关闭交易
			
					--各地感交易统计
					ISNULL(SUM(CASE WHEN TRADERESULT >= 1 AND TradeLoop = '0' THEN 1 ELSE 0 END),0) AS Loop0TradeCNT,--未知地感交易
					ISNULL(SUM(CASE WHEN TRADERESULT >= 1 AND TradeLoop = '1' THEN 1 ELSE 0 END),0) AS Loop1TradeCNT,--地感1交易
					ISNULL(SUM(CASE WHEN TRADERESULT >= 1 AND TradeLoop = '2' THEN 1 ELSE 0 END),0) AS Loop2TradeCNT,--地感2交易
					ISNULL(SUM(CASE WHEN TRADERESULT >= 1 AND TradeLoop = '3' THEN 1 ELSE 0 END),0) AS Loop3TradeCNT,--地感3交易
					ISNULL(SUM(CASE WHEN TRADERESULT >= 1 AND TradeLoop = '4' THEN 1 ELSE 0 END),0) AS Loop4TradeCNT,--地感4交易
					ISNULL(SUM(CASE WHEN TRADERESULT >= 1 AND TradeLoop = '5' THEN 1 ELSE 0 END),0) AS Loop5TradeCNT,--地感5交易
					ISNULL(SUM(CASE WHEN TRADERESULT >= 1 AND TradeLoop = '6' THEN 1 ELSE 0 END),0) AS Loop6TradeCNT,--地感6交易
					ISNULL(SUM(CASE WHEN TRADERESULT >= 1 AND TradeLoop = '7' THEN 1 ELSE 0 END),0) AS Loop7TradeCNT,--地感7交易
					ISNULL(SUM(CASE WHEN TRADERESULT >= 1 AND TradeLoop = '8' THEN 1 ELSE 0 END),0) AS Loop8TradeCNT,--地感8交易
			
					--统计报警数量
					SUM(CASE WHEN   OBUNo IS NOT NULL AND OBUNO <> ''  and  LastAlarmType <>'' THEN 1 ELSE 0 END) AS TotalAlarmCnt,
					ISNULL(( select COUNT (1) as LaneAlamCnt from   dbo.tb_LaneEventWatch lew
					 where   trad.RoadNo=lew.RoadNo   and   trad.StationNo=lew.StationNo
						 and   trad.LaneNo=lew.LaneNo   and   trad.Squaddate =lew.Squaddate 
						 and   datepart(Hour,ISNULL(trad.EnterTime,trad.LeaveTime )) = datepart(Hour,lew.EventTime) 
					group  by RoadNo ,StationNo , LaneNo , Squaddate, datepart(Hour,lew.EventTime)),0) as LaneAlamCnt,
					--车道故障数量
					ISNULL(( select count(1)  as  LaneFailCnt   from  dbo.tb_LaneFailureWatch  lfw
					where  0<>PATINDEX('%2',FAILURECODE) and  trad.RoadNo=lfw.RoadNo   
			     and   trad.StationNo=lfw.StationNo  and   trad.LaneNo=lfw.LaneNo   
			     and   trad.Squaddate =lfw.Squaddate 
					and   datepart(Hour,ISNULL(trad.EnterTime,trad.LeaveTime )) = datepart(Hour,lfw.FailureTime) 
					group  by RoadNo ,StationNo , LaneNo ,Squaddate ,  datepart(Hour,lfw.FailureTime)),0)   TotalFailCnt ,
					--当前车道故障数量
					ISNULL(( select  sum(case when FailureStatus =1 then 1 else 0 end )  as  CurLaneFailCnt   from  dbo.tb_LaneFailureWatch lfw
					where  0<>PATINDEX('%2',FAILURECODE) and  trad.RoadNo=lfw.RoadNo   and   trad.StationNo=lfw.StationNo
					and   trad.LaneNo=lfw.LaneNo   and   trad.Squaddate =lfw.Squaddate 
					and   datepart(Hour,ISNULL(trad.EnterTime,trad.LeaveTime )) = datepart(Hour,lfw.FailureTime) 
					group  by RoadNo ,StationNo , LaneNo ,Squaddate ,  datepart(Hour,lfw.FailureTime)),0)   CurLaneFailCnt  ,
			
			
					--过车耗时主要统计成功交易车辆
					ISNULL(SUM(CASE WHEN TRADERESULT >= 1 THEN  cast(B2_Cost as bigint) ELSE 0 END),0) AS SucB2CostCnt, --Ｂ2帧耗时 
					ISNULL(SUM(CASE WHEN TRADERESULT >= 1 THEN  cast(B3_Cost as bigint) ELSE 0 END),0) AS SucB3CostCnt, --Ｂ3帧耗时
					ISNULL(SUM(CASE WHEN TRADERESULT >= 1 THEN  cast(B4_Cost as bigint) ELSE 0 END),0) AS SucB4CostCnt, --Ｂ4帧耗时
					ISNULL(SUM(CASE WHEN TRADERESULT >= 1 THEN  cast(B5_Cost as bigint) ELSE 0 END),0) AS SucB5CostCnt, --Ｂ5帧耗时
					ISNULL(SUM(CASE WHEN TRADERESULT >= 1 THEN  
					cast( B3_Cost +B4_Cost +B5_Cost as  bigint)  ELSE 0 END),0) AS SucTradeCostCnt,--交易耗时
			
					--过车通信次数主要统计成功交易车辆
					ISNULL(SUM(CASE WHEN TRADERESULT >= 1 THEN  cast(B2_Cnt as bigint)  ELSE 0 END),0) AS SucB2CntCnt,  --Ｂ2帧次数
					ISNULL(SUM(CASE WHEN TRADERESULT >= 1 THEN  cast(B3_Cnt as bigint)  ELSE 0 END),0) AS SucB3CntCnt,  --Ｂ3帧次数
					ISNULL(SUM(CASE WHEN TRADERESULT >= 1 THEN  cast(B4_Cnt as bigint)  ELSE 0 END),0) AS SucB4CntCnt,  --Ｂ4帧次数
					ISNULL(SUM(CASE WHEN TRADERESULT >= 1 THEN  cast(B5_Cnt as bigint)  ELSE 0 END),0) AS SucB5CntCnt,  --Ｂ5帧次数
					ISNULL(SUM(CASE WHEN TRADERESULT >= 1 THEN 
					cast(B3_Cnt +B4_Cnt +B5_Cnt  as bigint )  ELSE 0 END),0) AS SucTradeCntCnt,--成功交易通信次数
			
					--高速过车主要统计成功交易车辆
					ISNULL(SUM(CASE WHEN TRADERESULT >= 1 and  B3_Cnt +B4_Cnt +B5_Cnt<280  then 1  ELSE 0 END),0) AS HighQualityCnt,--高质量交易数
					ISNULL(SUM(CASE WHEN TRADERESULT >= 1 and  Speed/100.00>15 THEN  1  ELSE 0 END),0) AS HighSpeedCnt,  --高速交易数
					ISNULL(SUM(CASE WHEN TRADERESULT >= 1 and  B3_Cnt<=1  THEN  1  ELSE 0 END),0) AS OnceTradeCnt,--一次交易成功数
					ISNULL(SUM(CASE WHEN TRADERESULT >= 1 and  Speed>0  and Speed/100.00<60 
					THEN  Speed  ELSE 0 END),0) AS TotalSpeed,  --总体速度
					ISNULL(SUM(CASE WHEN TRADERESULT >= 1 and  Speed>0  and Speed/100.00<60 
					THEN   1     ELSE 0 END),0) AS CountSpeed,--有效统计速度
							
			
				--AVGSPEED
				--backup1
			    null as backup1,
				--backup2
				  null as backup2,
				--backup3
			    null as backup3,
				--backup4
				  null as backup4
			
				from  dbo.tb_TradingInfo_ForSum trad,dbo.tb_LaneInfo lane     
				where trad.SumFlag = 1 and trad.RoadNo=lane.RoadNo   
			   and   trad.StationNo=lane.StationNo  and   trad.LaneNo=lane.LaneNo 
				group  by  trad.RoadNo,trad.StationNo,trad.LaneNo,lane.LaneType,trad.SquadDate,datepart(Hour,ISNULL(trad.EnterTime,trad.LeaveTime ))
			) x

		*/

		/**插入tb_TradingInfo_ForSum表*/
		public final static String insert = 
			"insert into tb_TradingInfo_HourSum("+
				"RoadNo,StationNo,LaneNo,LaneType,"+
				"SquadDate,Hour,TotalVehCnt,OBUCnt,"+
				"OBUNotFound,SuccessCnt,UnsuccessCnt,"+
				"TradeBreak,TradeFaile,OverTime,ComFailCnt,"+
				"NotFoundOBU,NoEntry,OBUDemount,UTurn,"+
				"CardNotFound,VehPaid,NoMoney,VehMoneyCnt,"+
				"FarRSUCnt,NearRSUCnt,CLOSERSUCNT,Loop0TradeCnt,"+
				"Loop1TradeCnt,Loop2TradeCnt,Loop3TradeCnt,"+
				"Loop4TradeCnt,Loop5TradeCnt,Loop6TradeCnt,"+
				"Loop7TradeCnt,Loop8TradeCnt,TotalAlarmCnt,"+
				"LaneAlarmCnt,TotalFailCnt,CurLaneFailCnt,"+
				"SucB2CostCnt,SucB3CostCnt,SucB4CostCnt,"+
				"SucB5CostCnt,SucTradeCostCnt,SucB2CntCnt,"+
				"SucB3CntCnt,SucB4CntCnt,SucB5CntCnt,"+
				"SucTradeCntCnt,HighQualityCnt,HighSpeedCnt,"+
				"OnceTradeCnt,TotalSpeed,CountSpeed,"+
				"Backup1,Backup2,Backup3,Backup4,"+
				"BssFailCnt,AVGSpeed"+
			") "+
			"select x.*,"+
			       "ISNULL( x.UnsuccessCnt-x.ComFailCnt,0) BssFailCnt,"+
			       "ISNULL( case when  x.CountSpeed=0 then 0 else  round((x.TotalSpeed/x.CountSpeed ),4) end ,0) as AVGSpeed   "+
			"from ("+
				    "select "+
					"trad.RoadNo ,"+
					"trad.StationNo, "+
					"trad.LaneNo,"+
					"lane.LaneType, "+
					"trad.SquadDate, "+
					"datepart(Hour,isnull(trad.EnterTime,trad.LeaveTime)) as  Hour, "+
					"COUNT(1) AS TotalVehCnt,"+
					"ISNULL(SUM(CASE WHEN OBUNo IS NOT NULL AND OBUNO <> '' THEN 1 ELSE 0 END),0) AS OBUCnt,"+
					"ISNULL(SUM(CASE WHEN OBUNo IS NULL OR  OBUNO = '' THEN 1 ELSE 0 END),0) AS OBUNotFound,"+
					"ISNULL(SUM(CASE WHEN TRADERESULT >= 1 THEN 1 ELSE 0 END),0) AS SuccessCnt,"+
					"ISNULL(SUM(CASE WHEN TRADERESULT < 1 AND OBUNo IS NOT NULL AND OBUNO <> '' THEN 1 ELSE 0 END),0) AS UnsuccessCnt,"+
					"ISNULL(SUM(CASE WHEN TRADERESULT < 1 AND  LASTALARMTYPE = 'ALM028' AND OBUNo IS NOT NULL"+
										" AND OBUNO <> '' THEN 1 ELSE 0 END),0)   AS TRADEBREAK, "+
					"ISNULL(SUM(CASE WHEN TRADERESULT < 1 AND  LASTALARMTYPE = 'ALM020' AND OBUNo IS NOT NULL "+
										" AND OBUNO <> '' THEN 1 ELSE 0 END),0) AS TRADEFAILE, "+
					"ISNULL(SUM(CASE WHEN TRADERESULT < 1 AND  LASTALARMTYPE = 'ALM027' AND OBUNo IS NOT NULL"+
										" AND OBUNO <> '' THEN 1 ELSE 0 END),0) AS OVERTIME,	"+
					"ISNULL(SUM(CASE WHEN  TRADERESULT < 1 AND (LastAlarmType In( 'ALM020','ALM027','ALM028','') or LastAlarmType  IS null )   "+
										" AND OBUNo IS NOT NULL AND OBUNO <> '' THEN 1 ELSE 0 END),0) AS ComFailCnt,"+
					"ISNULL(SUM(CASE WHEN TRADERESULT < 1 AND OBUNo IS NOT NULL  AND OBUNO <> ''"+
									    " AND LASTALARMTYPE = 'ALM001' THEN 1 ELSE 0 END),0) AS NotFoundOBU,"+
					"ISNULL(SUM(CASE WHEN TRADERESULT < 1 AND OBUNo IS NOT NULL AND OBUNO <> ''"+
									    " AND LASTALARMTYPE = 'ALM014' THEN 1 ELSE 0 END),0) AS NOENTRY,"+
					"ISNULL(SUM(CASE WHEN TRADERESULT < 1 AND  OBUNo IS NOT NULL AND OBUNO <> '' "+
										" AND LASTALARMTYPE = 'ALM021' THEN 1 ELSE 0 END),0)  AS OBUDEMOUNT, "+
					"ISNULL(SUM(CASE WHEN TRADERESULT < 1 AND OBUNo IS NOT NULL AND OBUNO <> '' "+
										" AND  LASTALARMTYPE = 'ALM023' THEN 1 ELSE 0 END),0) AS UTURN,"+
					"ISNULL(SUM(CASE WHEN TRADERESULT < 1 AND OBUNo IS NOT NULL  AND OBUNO <> ''"+
										" AND  LASTALARMTYPE = 'ALM024'  THEN 1 ELSE 0 END),0) AS CARDNOTFOUND, "+
					"ISNULL(SUM(CASE WHEN TRADERESULT < 1 AND OBUNo IS NOT NULL AND OBUNO <> ''"+
										" AND LASTALARMTYPE = 'ALM026' THEN 1 ELSE 0 END),0) AS VEHPAID, "+
					"ISNULL(SUM(CASE WHEN TRADERESULT < 1 AND OBUNo IS NOT NULL  AND OBUNO <> ''"+
										" AND LASTALARMTYPE = 'ALM003' THEN 1 ELSE 0 END),0) AS NOMONEY,	"+
					"ISNULL(SUM(CASE WHEN TRADERESULT >= 1 THEN  Money  ELSE 0 END),0) AS VehMoneyCnt, "+
					"ISNULL(SUM(CASE WHEN TRADERESULT >= 1 AND TradeRSU = '1' THEN 1 ELSE 0 END),0) AS FARRSUCNT,"+
					"ISNULL(SUM(CASE WHEN TRADERESULT >= 1 AND TradeRSU = '2' THEN 1 ELSE 0 END),0) AS NEARRSUCNT,"+
					"ISNULL(SUM(CASE WHEN TRADERESULT >= 1 AND TradeRSU = '0' THEN 1 ELSE 0 END),0) AS CLOSERSUCNT,"+
					"ISNULL(SUM(CASE WHEN TRADERESULT >= 1 AND TradeLoop = '0' THEN 1 ELSE 0 END),0) AS Loop0TradeCNT,"+
					"ISNULL(SUM(CASE WHEN TRADERESULT >= 1 AND TradeLoop = '1' THEN 1 ELSE 0 END),0) AS Loop1TradeCNT,"+
					"ISNULL(SUM(CASE WHEN TRADERESULT >= 1 AND TradeLoop = '2' THEN 1 ELSE 0 END),0) AS Loop2TradeCNT,"+
					"ISNULL(SUM(CASE WHEN TRADERESULT >= 1 AND TradeLoop = '3' THEN 1 ELSE 0 END),0) AS Loop3TradeCNT,"+
					"ISNULL(SUM(CASE WHEN TRADERESULT >= 1 AND TradeLoop = '4' THEN 1 ELSE 0 END),0) AS Loop4TradeCNT,"+
					"ISNULL(SUM(CASE WHEN TRADERESULT >= 1 AND TradeLoop = '5' THEN 1 ELSE 0 END),0) AS Loop5TradeCNT,"+
					"ISNULL(SUM(CASE WHEN TRADERESULT >= 1 AND TradeLoop = '6' THEN 1 ELSE 0 END),0) AS Loop6TradeCNT,"+
					"ISNULL(SUM(CASE WHEN TRADERESULT >= 1 AND TradeLoop = '7' THEN 1 ELSE 0 END),0) AS Loop7TradeCNT,"+
					"ISNULL(SUM(CASE WHEN TRADERESULT >= 1 AND TradeLoop = '8' THEN 1 ELSE 0 END),0) AS Loop8TradeCNT,"+
					"SUM(CASE WHEN   OBUNo IS NOT NULL AND OBUNO <> ''  and  LastAlarmType <>'' THEN 1 ELSE 0 END) AS TotalAlarmCnt,"+
					"ISNULL(( select COUNT (1) as LaneAlamCnt from   dbo.tb_LaneEventWatch lew"+
					" where   trad.RoadNo=lew.RoadNo   and   trad.StationNo=lew.StationNo"+
						" and   trad.LaneNo=lew.LaneNo   and   trad.Squaddate =lew.Squaddate "+
						" and   datepart(Hour,ISNULL(trad.EnterTime,trad.LeaveTime )) = datepart(Hour,lew.EventTime) "+
						" group  by RoadNo ,StationNo , LaneNo , Squaddate, datepart(Hour,lew.EventTime)),0) as LaneAlamCnt,"+
						" ISNULL(( select count(1)  as  LaneFailCnt   from  dbo.tb_LaneFailureWatch  lfw"+
						" where  0<>PATINDEX('%2',FAILURECODE) and  trad.RoadNo=lfw.RoadNo   "+
						"  and   trad.StationNo=lfw.StationNo  and   trad.LaneNo=lfw.LaneNo   "+
						" and   trad.Squaddate =lfw.Squaddate "+
						" and   datepart(Hour,ISNULL(trad.EnterTime,trad.LeaveTime )) = datepart(Hour,lfw.FailureTime) "+
						" group  by RoadNo ,StationNo , LaneNo ,Squaddate ,  datepart(Hour,lfw.FailureTime)),0)   TotalFailCnt ,"+
						" ISNULL(( select  sum(case when FailureStatus =1 then 1 else 0 end )  as  CurLaneFailCnt   from  dbo.tb_LaneFailureWatch lfw"+
						" where  0<>PATINDEX('%2',FAILURECODE) and  trad.RoadNo=lfw.RoadNo   and   trad.StationNo=lfw.StationNo"+
						" and   trad.LaneNo=lfw.LaneNo   and   trad.Squaddate =lfw.Squaddate "+
						" and   datepart(Hour,ISNULL(trad.EnterTime,trad.LeaveTime )) = datepart(Hour,lfw.FailureTime) "+
						" group  by RoadNo ,StationNo , LaneNo ,Squaddate ,  datepart(Hour,lfw.FailureTime)),0)   CurLaneFailCnt  ,"+
						" ISNULL(SUM(CASE WHEN TRADERESULT >= 1 THEN  cast(B2_Cost as bigint) ELSE 0 END),0) AS SucB2CostCnt, "+
						" ISNULL(SUM(CASE WHEN TRADERESULT >= 1 THEN  cast(B3_Cost as bigint) ELSE 0 END),0) AS SucB3CostCnt, "+
						" ISNULL(SUM(CASE WHEN TRADERESULT >= 1 THEN  cast(B4_Cost as bigint) ELSE 0 END),0) AS SucB4CostCnt, "+
						" ISNULL(SUM(CASE WHEN TRADERESULT >= 1 THEN  cast(B5_Cost as bigint) ELSE 0 END),0) AS SucB5CostCnt, "+
						" ISNULL(SUM(CASE WHEN TRADERESULT >= 1 THEN  "+
						" cast( B3_Cost +B4_Cost +B5_Cost as  bigint)  ELSE 0 END),0) AS SucTradeCostCnt,"+
						" ISNULL(SUM(CASE WHEN TRADERESULT >= 1 THEN  cast(B2_Cnt as bigint)  ELSE 0 END),0) AS SucB2CntCnt,  "+
						" ISNULL(SUM(CASE WHEN TRADERESULT >= 1 THEN  cast(B3_Cnt as bigint)  ELSE 0 END),0) AS SucB3CntCnt, "+
						" ISNULL(SUM(CASE WHEN TRADERESULT >= 1 THEN  cast(B4_Cnt as bigint)  ELSE 0 END),0) AS SucB4CntCnt,  "+
						" ISNULL(SUM(CASE WHEN TRADERESULT >= 1 THEN  cast(B5_Cnt as bigint)  ELSE 0 END),0) AS SucB5CntCnt,  "+
					" ISNULL(SUM(CASE WHEN TRADERESULT >= 1 THEN "+
					" cast(B3_Cnt +B4_Cnt +B5_Cnt  as bigint )  ELSE 0 END),0) AS SucTradeCntCnt,"+
					" ISNULL(SUM(CASE WHEN TRADERESULT >= 1 and  B3_Cnt +B4_Cnt +B5_Cnt<280  then 1  ELSE 0 END),0) AS HighQualityCnt,"+
					" ISNULL(SUM(CASE WHEN TRADERESULT >= 1 and  Speed/100.00>15 THEN  1  ELSE 0 END),0) AS HighSpeedCnt,  "+
					" ISNULL(SUM(CASE WHEN TRADERESULT >= 1 and  B3_Cnt<=1  THEN  1  ELSE 0 END),0) AS OnceTradeCnt,"+
					" ISNULL(SUM(CASE WHEN TRADERESULT >= 1 and  Speed>0  and Speed/100.00<60 "+
					" THEN  Speed  ELSE 0 END),0) AS TotalSpeed, "+
					" ISNULL(SUM(CASE WHEN TRADERESULT >= 1 and  Speed>0  and Speed/100.00<60 "+
					" THEN   1     ELSE 0 END),0) AS CountSpeed,"+
					" null as backup1,"+
					" null as backup2,"+
					" null as backup3,"+
					" null as backup4"+
					" from  dbo.tb_TradingInfo_ForSum trad,dbo.tb_LaneInfo lane     "+
					" where trad.SumFlag = 1 and trad.RoadNo=lane.RoadNo   "+
					"  and   trad.StationNo=lane.StationNo  and   trad.LaneNo=lane.LaneNo "+
					" group  by  trad.RoadNo,trad.StationNo,trad.LaneNo,lane.LaneType,trad.SquadDate,datepart(Hour,ISNULL(trad.EnterTime,trad.LeaveTime ))"+
					" ) x";
		
		/**
		 * update tb_TradingInfo_ForSum set sumflag=1
			where EXISTS
			(
			  select * from (
						select trad.id from  tb_TradingInfo_ForSum trad,tb_LaneInfo lane     
						where trad.RoadNo=lane.RoadNo   
						and   trad.StationNo=lane.StationNo  
						and   trad.LaneNo=lane.LaneNo 
				) x where  x.id = id
		  )
		 */
		
		
		
		/**修改tb_TradingInfo_ForSum表标识*/
		public final static String update = "update tb_TradingInfo_ForSum set sumflag=1"+
			"where EXISTS"+
			"("+
			"  select * from ("+
			"			select trad.id from  tb_TradingInfo_ForSum trad,tb_LaneInfo lane  "+
			"			where trad.RoadNo=lane.RoadNo   "+
			"			and   trad.StationNo=lane.StationNo  "+
			"			and   trad.LaneNo=lane.LaneNo "+
			"	) x where  x.id = id)";
		
		
		
		
		
		/**
		 * 根据sumflag=1删除表tb_TradingInfo_ForSum已汇总的数据
		 */
		public final static String delete = "delete from tb_TradingInfo_ForSum where sumflag=1";
		
		/**
		 * 汇总用户标签信息
		 * insert into tb_OBUUserInfo_HourSum(brandno,batchno,model,squaddate,lanetype,totalcount,successcount,onetimecount,tradingcount,amt)
				select   z.Backup2 as 品牌号,
						 z.BatchNo as 批次,
						 z.OBUType as 型号,
             			 z.SquadDate as 工班日期,
						 z.lanetype as 出入口,
						 z.col8 as 总次数,
						 z.col5 as 成功次数,
						 z.col6 as 一次成功数,
						 z.col7 as 可交易数,
						 Money as 金额
				 from ( 
						 SELECT op.Backup2,
								oui.BatchNo,
								oui.OBUType,
								op.SquadDate,
								tl.lanetype,
								SUM (CASE WHEN op.TradeResult > '0' THEN 1 ELSE 0 END ) AS col5,
								SUM (CASE WHEN op.TradeResult > '0'  and op.B3_Cnt = '1'  THEN 1 ELSE 0 END ) AS col6,
								SUM (CASE WHEN op.B3_Cnt >=1   THEN 1 ELSE 0 END ) AS col7,
								SUM (CASE WHEN op.OBUNo != '' THEN 1 ELSE 0 END ) AS col8,
								SUM (op.Money) as Money
						FROM  tb_TradingInfo_ForSum AS op
									LEFT JOIN tb_LaneInfo tl ON  op.roadno = tl.roadno and op.stationno = tl.stationno and op.laneno = tl.laneno 
									LEFT JOIN tb_OBUUserInfo oui ON op.obuno = oui.OBUNo
									LEFT JOIN tb_ObuInfo oi ON op.Backup2 = oi.obuType
						WHERE 1 = 1 and op.SumFlag = 1
							
						GROUP BY          
								oui.BatchNo,
          						tl.lanetype,
								op.Backup2,
								oui.OBUType,
								op.SquadDate
							
			   ) z
		 */
		
		public final static String forSumOBU = "insert into tb_OBUUserInfo_HourSum(brandno,batchno,model,squaddate,lanetype,totalcount,successcount,onetimecount,tradingcount,amt) "+
		"select   z.Backup2,z.BatchNo,z.OBUType,z.SquadDate,"+
				  "z.lanetype,z.col8,z.col5,z.col6,z.col7,Money"+
		" from (  SELECT op.Backup2,"+
						"oui.BatchNo,"+
						"oui.OBUType,"+
						"op.SquadDate,"+
						"tl.lanetype,"+
						"SUM (CASE WHEN op.TradeResult > '0' THEN 1 ELSE 0 END ) AS col5,"+
						"SUM (CASE WHEN op.TradeResult > '0'  and op.B3_Cnt = '1'  THEN 1 ELSE 0 END ) AS col6,"+
						"SUM (CASE WHEN op.B3_Cnt >=1   THEN 1 ELSE 0 END ) AS col7,"+
						"SUM (CASE WHEN op.OBUNo != '' THEN 1 ELSE 0 END ) AS col8,"+
						"SUM (op.Money) as Money"+
							" FROM  tb_TradingInfo_ForSum AS op"+
										" LEFT JOIN tb_LaneInfo tl ON  op.roadno = tl.roadno and op.stationno = tl.stationno and op.laneno = tl.laneno "+
										" LEFT JOIN tb_OBUUserInfo oui ON op.obuno = oui.OBUNo"+
										" LEFT JOIN tb_ObuInfo oi ON op.Backup2 = oi.obuType"+
							" WHERE 1 = 1 and op.SumFlag = 1"+
							" GROUP BY oui.BatchNo,tl.lanetype,op.Backup2,oui.OBUType,op.SquadDate"+
	  " ) z";
	}
}
