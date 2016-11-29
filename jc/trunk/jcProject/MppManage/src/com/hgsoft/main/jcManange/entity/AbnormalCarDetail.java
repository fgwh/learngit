package com.hgsoft.main.jcManange.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class AbnormalCarDetail implements Serializable{
	
	private static final long serialVersionUID = 8002761695528237158L;
	// Fields

	private String id;
	private short enStationId; // 入口站编号
	private Timestamp enTime; // 入口日期及时间
	private String enVehicleClass; // 入口车型
	private short enVehicleStatus; // 入口车种
	private String enVehiclePlate; // 入口车牌号码
	private short exStationId; // 出口站编号
	private Timestamp exTime; // 出口日期及时间
	private String exVehicleClass; // 出口车型
	private short exVehicleStatus; // 出口车种
	private String exVehiclePlate; // 出口车牌
	private String exVehIdentifyPlate; // 出口识别车牌
	private long dealStatus;
	private short axisGroupNum; // 轴组数
	private long totalWeight; // 总轴重
	private long overLoadWeight; // 超限重量
	private String realPath; // 收费路径信息
	private int miles; // 
	private String obuPlate; //obu车牌
	private float overLoadPersent;
	private short exceptionType; // 异常类型
	private short exvehicleFlag;
	private int cashMoney;
	private short enNetRoadID;
	private short exNetRoadID;
	private String laneEnSerialNo;  // 入口流水号
	private String laneExSerialNo;   //出口流水号
	private short enLaneId; // 入口车道编号
	private short exLaneId; // 出口车道编号
	private short enRoadId; // 入口路段编号
	private short exRoadId; // 出口路段编号
	private Timestamp squadDate; // 入口日期及时间
	private int exOperatorID;
	private byte auditStatus ;
	private float OverLoadPercent;

	
	public float getOverLoadPercent() {
		return OverLoadPercent;
	}
	public void setOverLoadPercent(float overLoadPercent) {
		OverLoadPercent = overLoadPercent;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public short getEnStationId() {
		return enStationId;
	}
	public void setEnStationId(short enStationId) {
		this.enStationId = enStationId;
	}
	public Timestamp getEnTime() {
		return enTime;
	}
	public void setEnTime(Timestamp enTime) {
		this.enTime = enTime;
	}

	public short getEnVehicleStatus() {
		return enVehicleStatus;
	}
	public void setEnVehicleStatus(short enVehicleStatus) {
		this.enVehicleStatus = enVehicleStatus;
	}
	public String getEnVehiclePlate() {
		return enVehiclePlate;
	}
	public void setEnVehiclePlate(String enVehiclePlate) {
		this.enVehiclePlate = enVehiclePlate;
	}
	public short getExStationId() {
		return exStationId;
	}
	public void setExStationId(short exStationId) {
		this.exStationId = exStationId;
	}
	public Timestamp getExTime() {
		return exTime;
	}
	public void setExTime(Timestamp exTime) {
		this.exTime = exTime;
	}

	public short getExVehicleStatus() {
		return exVehicleStatus;
	}
	public void setExVehicleStatus(short exVehicleStatus) {
		this.exVehicleStatus = exVehicleStatus;
	}
	public String getExVehiclePlate() {
		return exVehiclePlate;
	}
	public void setExVehiclePlate(String exVehiclePlate) {
		this.exVehiclePlate = exVehiclePlate;
	}
	public String getExVehIdentifyPlate() {
		return exVehIdentifyPlate;
	}
	public void setExVehIdentifyPlate(String exVehIdentifyPlate) {
		this.exVehIdentifyPlate = exVehIdentifyPlate;
	}
	public long getDealStatus() {
		return dealStatus;
	}
	public void setDealStatus(long dealStatus) {
		this.dealStatus = dealStatus;
	}
	public short getAxisGroupNum() {
		return axisGroupNum;
	}
	public void setAxisGroupNum(short axisGroupNum) {
		this.axisGroupNum = axisGroupNum;
	}
	public long getTotalWeight() {
		return totalWeight;
	}
	public void setTotalWeight(long totalWeight) {
		this.totalWeight = totalWeight;
	}
	public long getOverLoadWeight() {
		return overLoadWeight;
	}
	public void setOverLoadWeight(long overLoadWeight) {
		this.overLoadWeight = overLoadWeight;
	}
	public String getRealPath() {
		return realPath;
	}
	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}
	public int getMiles() {
		return miles;
	}
	public void setMiles(int miles) {
		this.miles = miles;
	}
	public String getObuPlate() {
		return obuPlate;
	}
	public void setObuPlate(String obuPlate) {
		this.obuPlate = obuPlate;
	}
	public float getOverLoadPersent() {
		return overLoadPersent;
	}
	public void setOverLoadPersent(float overLoadPersent) {
		this.overLoadPersent = overLoadPersent;
	}
	public short getExceptionType() {
		return exceptionType;
	}
	public void setExceptionType(short exceptionType) {
		this.exceptionType = exceptionType;
	}
	public short getExvehicleFlag() {
		return exvehicleFlag;
	}
	public void setExvehicleFlag(short exvehicleFlag) {
		this.exvehicleFlag = exvehicleFlag;
	}
	public int getCashMoney() {
		return cashMoney;
	}
	public void setCashMoney(int cashMoney) {
		this.cashMoney = cashMoney;
	}
	public short getEnNetRoadID() {
		return enNetRoadID;
	}
	public void setEnNetRoadID(short enNetRoadID) {
		this.enNetRoadID = enNetRoadID;
	}
	public short getExNetRoadID() {
		return exNetRoadID;
	}
	public void setExNetRoadID(short exNetRoadID) {
		this.exNetRoadID = exNetRoadID;
	}
	public String getLaneEnSerialNo() {
		return laneEnSerialNo;
	}
	public void setLaneEnSerialNo(String laneEnSerialNo) {
		this.laneEnSerialNo = laneEnSerialNo;
	}
	public String getLaneExSerialNo() {
		return laneExSerialNo;
	}
	public void setLaneExSerialNo(String laneExSerialNo) {
		this.laneExSerialNo = laneExSerialNo;
	}
	public short getEnLaneId() {
		return enLaneId;
	}
	public void setEnLaneId(short enLaneId) {
		this.enLaneId = enLaneId;
	}
	public short getExLaneId() {
		return exLaneId;
	}
	public void setExLaneId(short exLaneId) {
		this.exLaneId = exLaneId;
	}
	public short getEnRoadId() {
		return enRoadId;
	}
	public void setEnRoadId(short enRoadId) {
		this.enRoadId = enRoadId;
	}
	public short getExRoadId() {
		return exRoadId;
	}
	public void setExRoadId(short exRoadId) {
		this.exRoadId = exRoadId;
	}
	public Timestamp getSquadDate() {
		return squadDate;
	}
	public void setSquadDate(Timestamp squadDate) {
		this.squadDate = squadDate;
	}
	public int getExOperatorID() {
		return exOperatorID;
	}
	public void setExOperatorID(int exOperatorID) {
		this.exOperatorID = exOperatorID;
	}
	public byte getAuditStatus() {  
		return auditStatus;
	}
	public void setAuditStatus(byte auditStatus) {
		this.auditStatus = auditStatus;

	}
	public String getEnVehicleClass() {
		return enVehicleClass;
	}
	public void setEnVehicleClass(String enVehicleClass) {
		this.enVehicleClass = enVehicleClass;
	}
	public String getExVehicleClass() {
		return exVehicleClass;
	}
	public void setExVehicleClass(String exVehicleClass) {
		this.exVehicleClass = exVehicleClass;
	}


}
