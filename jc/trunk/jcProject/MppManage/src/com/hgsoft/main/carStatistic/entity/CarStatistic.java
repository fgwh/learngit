package com.hgsoft.main.carStatistic.entity;

import java.util.Date;

import com.hgsoft.security.entity.BaseEntity;


public class CarStatistic implements BaseEntity{
	private static final long serialVersionUID = 1L;
	private String id;
	private String centralStationId;
	private String selectId;  //选择的中心站编号
	private String stationNo; //收费站编号
	private String laneEnSerialNo; //入口流水号
	private String exVehiclePlate;   //车牌
	private String exCPCSnNo;  //复合卡表面卡号
	private String exCPUCardSnNo; //CPU卡号
	private Date startTime;     //开始时间
	private Date endTime;       //结束时间
	private String laneExSerialNo;   //出口流水号
	private String dealStatusArr;    //多选的处理情况
	private int flag = 0;   //标识是不是第一次加载
	private String dealStatusStr; //异常情况数据显示
	private Date exTime; //出口时间
	private String enRoadNo; //入口路段编号
	private String exRoadNo;  //出口路段
	
	private String exSerialNo;    //出口流水号获取图片
	private String enSerialNo;	  //入口流水号获取图片
	private String exRoadID;    //出口路段编号
	private String enNetRoadID;   //入口入网编号
	private String exNetRoadID;   //出口入网编号
	private String enStationID;   //入口站编号
	private String exStationID;   //出口站编号
	private String enLaneID;      //入口站编号
	private String exLaneID;    //出口站编号
	private String squadDate;   //工班日期
	private String exLaneType = "";  //出口车道类型
	private String enLaneType = "";  //入口车道类型
	private String exSerialNoTwo;
	private Date exTimeTwo;
	
	
	
	
	
	
	
	/****************************  set/get方法  ********************************/
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCentralStationId() {
		return centralStationId;
	}

	public void setCentralStationId(String centralStationId) {
		this.centralStationId = centralStationId;
	}

	public String getSelectId() {
		return selectId;
	}

	public void setSelectId(String selectId) {
		this.selectId = selectId;
	}

	public String getStationNo() {
		return stationNo;
	}

	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}

	public String getLaneEnSerialNo() {
		return laneEnSerialNo;
	}

	public void setLaneEnSerialNo(String laneEnSerialNo) {
		this.laneEnSerialNo = laneEnSerialNo;
	}

	public String getExVehiclePlate() {
		return exVehiclePlate;
	}

	public void setExVehiclePlate(String exVehiclePlate) {
		this.exVehiclePlate = exVehiclePlate;
	}

	public String getExCPCSnNo() {
		return exCPCSnNo;
	}

	public void setExCPCSnNo(String exCPCSnNo) {
		this.exCPCSnNo = exCPCSnNo;
	}

	public String getExCPUCardSnNo() {
		return exCPUCardSnNo;
	}

	public void setExCPUCardSnNo(String exCPUCardSnNo) {
		this.exCPUCardSnNo = exCPUCardSnNo;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getLaneExSerialNo() {
		return laneExSerialNo;
	}

	public void setLaneExSerialNo(String laneExSerialNo) {
		this.laneExSerialNo = laneExSerialNo;
	}

	public String getDealStatusArr() {
		return dealStatusArr;
	}

	public void setDealStatusArr(String dealStatusArr) {
		this.dealStatusArr = dealStatusArr;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getDealStatusStr() {
		return dealStatusStr;
	}

	public void setDealStatusStr(String dealStatusStr) {
		this.dealStatusStr = dealStatusStr;
	}

	public String getExSerialNo() {
		return exSerialNo;
	}

	public void setExSerialNo(String exSerialNo) {
		this.exSerialNo = exSerialNo;
	}

	public String getEnSerialNo() {
		return enSerialNo;
	}

	public void setEnSerialNo(String enSerialNo) {
		this.enSerialNo = enSerialNo;
	}

	public Date getExTime() {
		return exTime;
	}

	public void setExTime(Date exTime) {
		this.exTime = exTime;
	}

	public String getEnRoadNo() {
		return enRoadNo;
	}

	public void setEnRoadNo(String enRoadNo) {
		this.enRoadNo = enRoadNo;
	}

	public String getExRoadNo() {
		return exRoadNo;
	}

	public void setExRoadNo(String exRoadNo) {
		this.exRoadNo = exRoadNo;
	}

	public String getExRoadID() {
		return exRoadID;
	}

	public void setExRoadID(String exRoadID) {
		this.exRoadID = exRoadID;
	}

	public String getEnNetRoadID() {
		return enNetRoadID;
	}

	public void setEnNetRoadID(String enNetRoadID) {
		this.enNetRoadID = enNetRoadID;
	}

	public String getExNetRoadID() {
		return exNetRoadID;
	}

	public void setExNetRoadID(String exNetRoadID) {
		this.exNetRoadID = exNetRoadID;
	}

	public String getEnStationID() {
		return enStationID;
	}

	public void setEnStationID(String enStationID) {
		this.enStationID = enStationID;
	}

	public String getExStationID() {
		return exStationID;
	}

	public void setExStationID(String exStationID) {
		this.exStationID = exStationID;
	}

	public String getEnLaneID() {
		return enLaneID;
	}

	public void setEnLaneID(String enLaneID) {
		this.enLaneID = enLaneID;
	}

	public String getExLaneID() {
		return exLaneID;
	}

	public void setExLaneID(String exLaneID) {
		this.exLaneID = exLaneID;
	}

	public String getSquadDate() {
		return squadDate;
	}

	public void setSquadDate(String squadDate) {
		this.squadDate = squadDate;
	}

	public String getExLaneType() {
		return exLaneType;
	}

	public void setExLaneType(String exLaneType) {
		this.exLaneType = exLaneType;
	}

	public String getEnLaneType() {
		return enLaneType;
	}

	public void setEnLaneType(String enLaneType) {
		this.enLaneType = enLaneType;
	}

	public String getExSerialNoTwo() {
		return exSerialNoTwo;
	}

	public void setExSerialNoTwo(String exSerialNoTwo) {
		this.exSerialNoTwo = exSerialNoTwo;
	}

	public Date getExTimeTwo() {
		return exTimeTwo;
	}

	public void setExTimeTwo(Date exTimeTwo) {
		this.exTimeTwo = exTimeTwo;
	}
	
}