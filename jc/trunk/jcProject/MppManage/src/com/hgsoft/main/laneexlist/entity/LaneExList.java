package com.hgsoft.main.laneexlist.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;


/**
 * 出口流水表pojo
 * @author 郭志强
 * @time 2016/9/8 21:58
 */
public class LaneExList implements Serializable {

	//private static final long serialVersionUID = 8002761695528237158L;
	// Fields


	private Short exRecordNo; // ExRecordNo
	private String laneExSerialNo;   // 出口流水号(主键)
	private String laneEnSerialNo;  // 入口流水号
	private Short cardNetRoadID;
	private Long icinCardId; // IC卡内部编号
	private Short enNetRoadId; // 入口路网编号
	private Short enRoadId; // 入口路段编码
	
	private Integer enStationId; // 入口站编号
	private Short enLaneId; // 入口车道编号
	private Short enLaneType; // 入口车道类型
	private Timestamp enTime; // 入口日期及时间
	private Long enOperatorID;
	private Short enShiftID;
	
	private Short enVehicleClass; // 入口车型
	private Short enVehicleStatus; // 入口车种
	private String enVehiclePlate; // 入口车牌号码
	private Short enVehicleFlag;
	
	private String EnETCTermCode;
	
	private Short exNetRoadId; // 出口路网编号
	private Short exRoadId; // 出口路段编码
	private Integer exStationId; // 出口站编号
	private Short exLaneId; // 出口车道编号
	private Short exLaneType; // 出口车道类型
	private Timestamp exTime; // 出口日期及时间
	
	private Long exOperatorID;
	private String exOpCardNo;
	private Long exOpCardID;
	
	private Timestamp squadDate; // 出口工班日期
	
	private Short exShiftID;
	
	private String exCpcsnNo; // 出口复合卡表面号
	
	private Long exCPCInID;
	private Short exVehicleClass; // 出口车型
	private Short exVehicleStatus; // 出口车种
	private Integer imageSerialNo; // 出口图片编号
	
	private Integer cardBoxNo;
	private Integer cardTrunkNo;
	private String appVer;
	
	private String exVehiclePlate; // 出口车牌
	private String exVehIdentifyPlate; // 出口识别车牌
	private Short tollType; // 费用类型
	
	private Integer cashMoney; // 收费金额
	
	private String invoiceID;
	private Integer ETCMoney;
	private Integer freeMoney;
	private Integer officeMoney;
	private Integer unpayMoney;
	
	private Integer vehicleClassMoney; // 车型费率金额
	
	private Integer downVehicleClassMoney;
	
	private String officeCardSnNo;
	
	private Long ownerCode1;
	private Long ownerCode2;
	private Long ownerCode3;
	private Long ownerCode4;
	
	private Long dealStatus;
	private Integer deviceStatus; // 设备状态
	private Short recordType; // 记录类型
	
	private Short exVehicleFlag;
	private Short exICCIssuer;
	
	private String exCpucardSnNo; // 出口CPU卡表面编号
	private Long exCpucardId; // 出口CPU卡内部ID号
	private Short exCpucardType; // 出口CPU卡类型
	
	private String exCpuCardPlate;
	private String exCpuCardPlateColor;
	private Short exCpuCardUserType;
	
	private Short weightFlag; // 计重标识
	
	private Short ticketType;
	private Short payCardType;
	
	private Short vehCount; // 车辆数
	private Short axisNum; // 轴数
	private Short axisGroupNum; // 轴组数
	private Long totalWeight; // 总轴重
	private Long totalWeightLimit; // 总轴限
	
	private String axisType; // 轴型
	private String axisWeightDetail; // 各轴轴重
	private Long overLoadWeight; // 超限重量
	
	private String previousAxisType;
	private Long previousTotalWeight;
	private Short truckLimitVerNo;
	private Short truckLimitPriceVerNo;
	
	private Short preVehType; // 降档前车型
	
	private int preVehMoney;
	private Long payCardBalanceBefore;
	private Long payCardBalanceAfter;
	private String OBUNum;
	private String OBUID;
	private String OBEState;
	private String OBUPlate;
	private String OBUPlateColor;
	private Integer exETCTradeNo;
	private String exETCTac;
	private Integer exETCTermTradNo;
	
	private String exEtctermCode; // 出口终端机编码
	
	private Short rebateType;
	private Short rebateVerNo;
	private Short rebateRate;
	private Integer preRebateFee;
	private Integer rebateFee;
	private Short flagType;
	
	private String originalPath; // 原始标识信息
	private String realPath; // 收费路径信息
	private String roadComb; // 收费路径路段组合
	
	private String moneyComb;
	
	private String roadSstationComb; // 收费路径路段起始站编码组合
	private String roadEstationComb; // 收费路径路段结束站编码组合
	private String realPath01; // 缺失路径标识信息
	
	private Integer tollRateVer;
	private Short listName;
	private String  VoidSerialNo;
	
	private Short manualFlag; // 人工录入标识
	private Integer verifyCode; // 校验码
	
	private Short CPCCurrentVol;
	private Integer miles;
	private Integer translationMiles;
	private Short tollCardFreeListVerNo;
	
	private Integer spare1; // 备用1
	private Integer spare2; // 备用2
	private String spare3; // 备用3
	private String spare4; // 备用4
	private Timestamp uploadTime; // 上传时间
	
	
	
	
	//private Long dealStatus2;
	

	/*public Long getDealStatus2() {
		return dealStatus2;
	}

	public void setDealStatus2(Long dealStatus2) {
		this.dealStatus2 = dealStatus2;
	}*/

	public Long getExOperatorID() {
		return exOperatorID;
	}

	public void setExOperatorID(Long exOperatorID) {
		this.exOperatorID = exOperatorID;
	}



	/*public static long getSerialVersionUID() {
		return serialVersionUID;
	}*/

	// Constructors

	/** default constructor */
	public LaneExList() {
	}

	public Short getExRecordNo() {
		return exRecordNo;
	}

	public void setExRecordNo(Short exRecordNo) {
		this.exRecordNo = exRecordNo;
	}

	public String getLaneExSerialNo() {
		return laneExSerialNo;
	}

	public void setLaneExSerialNo(String laneExSerialNo) {
		this.laneExSerialNo = laneExSerialNo;
	}

	public String getLaneEnSerialNo() {
		return laneEnSerialNo;
	}

	public void setLaneEnSerialNo(String laneEnSerialNo) {
		this.laneEnSerialNo = laneEnSerialNo;
	}

	public Short getEnNetRoadId() {
		return enNetRoadId;
	}

	public void setEnNetRoadId(Short enNetRoadId) {
		this.enNetRoadId = enNetRoadId;
	}


	public Long getIcinCardId() {
		return icinCardId;
	}

	public void setIcinCardId(Long icinCardId) {
		this.icinCardId = icinCardId;
	}


	public Short getEnLaneId() {
		return enLaneId;
	}

	public void setEnLaneId(Short enLaneId) {
		this.enLaneId = enLaneId;
	}


	public Timestamp getEnTime() {
		return enTime;
	}

	public void setEnTime(Timestamp enTime) {
		this.enTime = enTime;
	}

	public Short getEnVehicleClass() {
		return enVehicleClass;
	}

	public void setEnVehicleClass(Short enVehicleClass) {
		this.enVehicleClass = enVehicleClass;
	}

	public Short getEnVehicleStatus() {
		return enVehicleStatus;
	}

	public void setEnVehicleStatus(Short enVehicleStatus) {
		this.enVehicleStatus = enVehicleStatus;
	}

	public String getEnVehiclePlate() {
		return enVehiclePlate;
	}

	public void setEnVehiclePlate(String enVehiclePlate) {
		this.enVehiclePlate = enVehiclePlate;
	}

	public Short getExNetRoadId() {
		return exNetRoadId;
	}

	public void setExNetRoadId(Short exNetRoadId) {
		this.exNetRoadId = exNetRoadId;
	}

	public Short getExRoadId() {
		return exRoadId;
	}

	public void setExRoadId(Short exRoadId) {
		this.exRoadId = exRoadId;
	}

	public Integer getExStationId() {
		return exStationId;
	}

	public void setExStationId(Integer exStationId) {
		this.exStationId = exStationId;
	}

	public Short getExLaneId() {
		return exLaneId;
	}

	public void setExLaneId(Short exLaneId) {
		this.exLaneId = exLaneId;
	}

	public Short getExLaneType() {
		return exLaneType;
	}

	public void setExLaneType(Short exLaneType) {
		this.exLaneType = exLaneType;
	}

	public Timestamp getExTime() {
		return exTime;
	}

	public void setExTime(Timestamp exTime) {
		this.exTime = exTime;
	}

	public Timestamp getSquadDate() {
		return squadDate;
	}

	public void setSquadDate(Timestamp squadDate) {
		this.squadDate = squadDate;
	}

	public String getExCpcsnNo() {
		return exCpcsnNo;
	}

	public void setExCpcsnNo(String exCpcsnNo) {
		this.exCpcsnNo = exCpcsnNo;
	}

	public Short getExVehicleClass() {
		return exVehicleClass;
	}

	public void setExVehicleClass(Short exVehicleClass) {
		this.exVehicleClass = exVehicleClass;
	}

	public Short getExVehicleStatus() {
		return exVehicleStatus;
	}

	public void setExVehicleStatus(Short exVehicleStatus) {
		this.exVehicleStatus = exVehicleStatus;
	}

	public Integer getImageSerialNo() {
		return imageSerialNo;
	}

	public void setImageSerialNo(Integer imageSerialNo) {
		this.imageSerialNo = imageSerialNo;
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

	public Integer getCashMoney() {
		return cashMoney;
	}

	public void setCashMoney(Integer cashMoney) {
		this.cashMoney = cashMoney;
	}

	public Integer getVehicleClassMoney() {
		return vehicleClassMoney;
	}

	public void setVehicleClassMoney(Integer vehicleClassMoney) {
		this.vehicleClassMoney = vehicleClassMoney;
	}

	public Integer getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(Integer deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public Short getRecordType() {
		return recordType;
	}

	public void setRecordType(Short recordType) {
		this.recordType = recordType;
	}

	public String getExCpucardSnNo() {
		return exCpucardSnNo;
	}

	public void setExCpucardSnNo(String exCpucardSnNo) {
		this.exCpucardSnNo = exCpucardSnNo;
	}

	public Long getExCpucardId() {
		return exCpucardId;
	}

	public void setExCpucardId(Long exCpucardId) {
		this.exCpucardId = exCpucardId;
	}

	public Short getExCpucardType() {
		return exCpucardType;
	}

	public void setExCpucardType(Short exCpucardType) {
		this.exCpucardType = exCpucardType;
	}

	public Short getWeightFlag() {
		return weightFlag;
	}

	public void setWeightFlag(Short weightFlag) {
		this.weightFlag = weightFlag;
	}

	public Short getVehCount() {
		return vehCount;
	}

	public void setVehCount(Short vehCount) {
		this.vehCount = vehCount;
	}

	public Short getAxisNum() {
		return axisNum;
	}

	public void setAxisNum(Short axisNum) {
		this.axisNum = axisNum;
	}

	public Short getAxisGroupNum() {
		return axisGroupNum;
	}

	public void setAxisGroupNum(Short axisGroupNum) {
		this.axisGroupNum = axisGroupNum;
	}

	public Long getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(Long totalWeight) {
		this.totalWeight = totalWeight;
	}

	public Long getTotalWeightLimit() {
		return totalWeightLimit;
	}

	public void setTotalWeightLimit(Long totalWeightLimit) {
		this.totalWeightLimit = totalWeightLimit;
	}

	public Short getTollType() {
		return tollType;
	}

	public void setTollType(Short tollType) {
		this.tollType = tollType;
	}

	public String getAxisType() {
		return axisType;
	}

	public void setAxisType(String axisType) {
		this.axisType = axisType;
	}

	public String getAxisWeightDetail() {
		return axisWeightDetail;
	}

	public void setAxisWeightDetail(String axisWeightDetail) {
		this.axisWeightDetail = axisWeightDetail;
	}

	public Long getOverLoadWeight() {
		return overLoadWeight;
	}

	public void setOverLoadWeight(Long overLoadWeight) {
		this.overLoadWeight = overLoadWeight;
	}

	public Short getPreVehType() {
		return preVehType;
	}

	public void setPreVehType(Short preVehType) {
		this.preVehType = preVehType;
	}

	public String getExEtctermCode() {
		return exEtctermCode;
	}

	public void setExEtctermCode(String exEtctermCode) {
		this.exEtctermCode = exEtctermCode;
	}

	public String getOriginalPath() {
		return originalPath;
	}

	public void setOriginalPath(String originalPath) {
		this.originalPath = originalPath;
	}

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	public String getRoadComb() {
		return roadComb;
	}

	public void setRoadComb(String roadComb) {
		this.roadComb = roadComb;
	}

	public String getRealPath01() {
		return realPath01;
	}

	public void setRealPath01(String realPath01) {
		this.realPath01 = realPath01;
	}

	public Short getManualFlag() {
		return manualFlag;
	}

	public void setManualFlag(Short manualFlag) {
		this.manualFlag = manualFlag;
	}

	public Integer getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(Integer verifyCode) {
		this.verifyCode = verifyCode;
	}

	public Integer getSpare1() {
		return spare1;
	}

	public void setSpare1(Integer spare1) {
		this.spare1 = spare1;
	}

	public Integer getSpare2() {
		return spare2;
	}

	public void setSpare2(Integer spare2) {
		this.spare2 = spare2;
	}

	public String getSpare3() {
		return spare3;
	}

	public void setSpare3(String spare3) {
		this.spare3 = spare3;
	}

	public String getSpare4() {
		return spare4;
	}

	public void setSpare4(String spare4) {
		this.spare4 = spare4;
	}

	public Timestamp getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Short getEnVehicleFlag() {
		return enVehicleFlag;
	}

	public void setEnVehicleFlag(Short enVehicleFlag) {
		this.enVehicleFlag = enVehicleFlag;
	}

	public Long getEnOperatorID() {
		return enOperatorID;
	}

	public void setEnOperatorID(Long enOperatorID) {
		this.enOperatorID = enOperatorID;
	}

	public String getRoadSstationComb() {
		return roadSstationComb;
	}

	public void setRoadSstationComb(String roadSstationComb) {
		this.roadSstationComb = roadSstationComb;
	}

	public String getRoadEstationComb() {
		return roadEstationComb;
	}

	public void setRoadEstationComb(String roadEstationComb) {
		this.roadEstationComb = roadEstationComb;
	}

	public Short getEnRoadId() {
		return enRoadId;
	}

	public void setEnRoadId(Short enRoadId) {
		this.enRoadId = enRoadId;
	}

	public Integer getEnStationId() {
		return enStationId;
	}

	public void setEnStationId(Integer enStationId) {
		this.enStationId = enStationId;
	}

	public Short getEnLaneType() {
		return enLaneType;
	}

	public void setEnLaneType(Short enLaneType) {
		this.enLaneType = enLaneType;
	}

	public Long getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(Long dealStatus) {
		this.dealStatus = dealStatus;
	}

	public String getOBUPlate() {
		return OBUPlate;
	}

	public void setOBUPlate(String oBUPlate) {
		OBUPlate = oBUPlate;
	}

	public Short getCardNetRoadID() {
		return cardNetRoadID;
	}

	public void setCardNetRoadID(Short cardNetRoadID) {
		this.cardNetRoadID = cardNetRoadID;
	}

	public Short getEnShiftID() {
		return enShiftID;
	}

	public void setEnShiftID(Short enShiftID) {
		this.enShiftID = enShiftID;
	}

	public String getEnETCTermCode() {
		return EnETCTermCode;
	}

	public void setEnETCTermCode(String enETCTermCode) {
		EnETCTermCode = enETCTermCode;
	}

	public String getExOpCardNo() {
		return exOpCardNo;
	}

	public void setExOpCardNo(String exOpCardNo) {
		this.exOpCardNo = exOpCardNo;
	}

	public Long getExOpCardID() {
		return exOpCardID;
	}

	public void setExOpCardID(Long exOpCardID) {
		this.exOpCardID = exOpCardID;
	}

	public Short getExShiftID() {
		return exShiftID;
	}

	public void setExShiftID(Short exShiftID) {
		this.exShiftID = exShiftID;
	}

	public Long getExCPCInID() {
		return exCPCInID;
	}

	public void setExCPCInID(Long exCPCInID) {
		this.exCPCInID = exCPCInID;
	}

	public Integer getCardBoxNo() {
		return cardBoxNo;
	}

	public void setCardBoxNo(Integer cardBoxNo) {
		this.cardBoxNo = cardBoxNo;
	}

	public Integer getCardTrunkNo() {
		return cardTrunkNo;
	}

	public void setCardTrunkNo(Integer cardTrunkNo) {
		this.cardTrunkNo = cardTrunkNo;
	}

	public String getAppVer() {
		return appVer;
	}

	public void setAppVer(String appVer) {
		this.appVer = appVer;
	}

	public String getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(String invoiceID) {
		this.invoiceID = invoiceID;
	}

	public Integer getETCMoney() {
		return ETCMoney;
	}

	public void setETCMoney(Integer eTCMoney) {
		ETCMoney = eTCMoney;
	}

	public Integer getFreeMoney() {
		return freeMoney;
	}

	public void setFreeMoney(Integer freeMoney) {
		this.freeMoney = freeMoney;
	}

	public Integer getOfficeMoney() {
		return officeMoney;
	}

	public void setOfficeMoney(Integer officeMoney) {
		this.officeMoney = officeMoney;
	}

	public Integer getUnpayMoney() {
		return unpayMoney;
	}

	public void setUnpayMoney(Integer unpayMoney) {
		this.unpayMoney = unpayMoney;
	}

	public Integer getDownVehicleClassMoney() {
		return downVehicleClassMoney;
	}

	public void setDownVehicleClassMoney(Integer downVehicleClassMoney) {
		this.downVehicleClassMoney = downVehicleClassMoney;
	}

	public String getOfficeCardSnNo() {
		return officeCardSnNo;
	}

	public void setOfficeCardSnNo(String officeCardSnNo) {
		this.officeCardSnNo = officeCardSnNo;
	}

	public Long getOwnerCode1() {
		return ownerCode1;
	}

	public void setOwnerCode1(Long ownerCode1) {
		this.ownerCode1 = ownerCode1;
	}

	public Long getOwnerCode2() {
		return ownerCode2;
	}

	public void setOwnerCode2(Long ownerCode2) {
		this.ownerCode2 = ownerCode2;
	}

	public Long getOwnerCode3() {
		return ownerCode3;
	}

	public void setOwnerCode3(Long ownerCode3) {
		this.ownerCode3 = ownerCode3;
	}

	public Long getOwnerCode4() {
		return ownerCode4;
	}

	public void setOwnerCode4(Long ownerCode4) {
		this.ownerCode4 = ownerCode4;
	}

	public Short getExVehicleFlag() {
		return exVehicleFlag;
	}

	public void setExVehicleFlag(Short exVehicleFlag) {
		this.exVehicleFlag = exVehicleFlag;
	}

	public Short getExICCIssuer() {
		return exICCIssuer;
	}

	public void setExICCIssuer(Short exICCIssuer) {
		this.exICCIssuer = exICCIssuer;
	}

	public String getExCpuCardPlate() {
		return exCpuCardPlate;
	}

	public void setExCpuCardPlate(String exCpuCardPlate) {
		this.exCpuCardPlate = exCpuCardPlate;
	}

	public String getExCpuCardPlateColor() {
		return exCpuCardPlateColor;
	}

	public void setExCpuCardPlateColor(String exCpuCardPlateColor) {
		this.exCpuCardPlateColor = exCpuCardPlateColor;
	}

	public Short getExCpuCardUserType() {
		return exCpuCardUserType;
	}

	public void setExCpuCardUserType(Short exCpuCardUserType) {
		this.exCpuCardUserType = exCpuCardUserType;
	}

	public Short getTicketType() {
		return ticketType;
	}

	public void setTicketType(Short ticketType) {
		this.ticketType = ticketType;
	}

	public Short getPayCardType() {
		return payCardType;
	}

	public void setPayCardType(Short payCardType) {
		this.payCardType = payCardType;
	}

	public String getPreviousAxisType() {
		return previousAxisType;
	}

	public void setPreviousAxisType(String previousAxisType) {
		this.previousAxisType = previousAxisType;
	}

	public Long getPreviousTotalWeight() {
		return previousTotalWeight;
	}

	public void setPreviousTotalWeight(Long previousTotalWeight) {
		this.previousTotalWeight = previousTotalWeight;
	}

	public Short getTruckLimitVerNo() {
		return truckLimitVerNo;
	}

	public void setTruckLimitVerNo(Short truckLimitVerNo) {
		this.truckLimitVerNo = truckLimitVerNo;
	}

	public Short getTruckLimitPriceVerNo() {
		return truckLimitPriceVerNo;
	}

	public void setTruckLimitPriceVerNo(Short truckLimitPriceVerNo) {
		this.truckLimitPriceVerNo = truckLimitPriceVerNo;
	}

	public int getPreVehMoney() {
		return preVehMoney;
	}

	public void setPreVehMoney(int preVehMoney) {
		this.preVehMoney = preVehMoney;
	}

	public Long getPayCardBalanceBefore() {
		return payCardBalanceBefore;
	}

	public void setPayCardBalanceBefore(Long payCardBalanceBefore) {
		this.payCardBalanceBefore = payCardBalanceBefore;
	}

	public Long getPayCardBalanceAfter() {
		return payCardBalanceAfter;
	}

	public void setPayCardBalanceAfter(Long payCardBalanceAfter) {
		this.payCardBalanceAfter = payCardBalanceAfter;
	}

	public String getOBUNum() {
		return OBUNum;
	}

	public void setOBUNum(String oBUNum) {
		OBUNum = oBUNum;
	}

	public String getOBUID() {
		return OBUID;
	}

	public void setOBUID(String oBUID) {
		OBUID = oBUID;
	}

	public String getOBEState() {
		return OBEState;
	}

	public void setOBEState(String oBEState) {
		OBEState = oBEState;
	}

	public String getOBUPlateColor() {
		return OBUPlateColor;
	}

	public void setOBUPlateColor(String oBUPlateColor) {
		OBUPlateColor = oBUPlateColor;
	}

	public Integer getExETCTradeNo() {
		return exETCTradeNo;
	}

	public void setExETCTradeNo(Integer exETCTradeNo) {
		this.exETCTradeNo = exETCTradeNo;
	}

	public String getExETCTac() {
		return exETCTac;
	}

	public void setExETCTac(String exETCTac) {
		this.exETCTac = exETCTac;
	}

	public Integer getExETCTermTradNo() {
		return exETCTermTradNo;
	}

	public void setExETCTermTradNo(Integer exETCTermTradNo) {
		this.exETCTermTradNo = exETCTermTradNo;
	}

	public Short getRebateType() {
		return rebateType;
	}

	public void setRebateType(Short rebateType) {
		this.rebateType = rebateType;
	}

	public Short getRebateVerNo() {
		return rebateVerNo;
	}

	public void setRebateVerNo(Short rebateVerNo) {
		this.rebateVerNo = rebateVerNo;
	}

	public Short getRebateRate() {
		return rebateRate;
	}

	public void setRebateRate(Short rebateRate) {
		this.rebateRate = rebateRate;
	}

	public Integer getPreRebateFee() {
		return preRebateFee;
	}

	public void setPreRebateFee(Integer preRebateFee) {
		this.preRebateFee = preRebateFee;
	}

	public Integer getRebateFee() {
		return rebateFee;
	}

	public void setRebateFee(Integer rebateFee) {
		this.rebateFee = rebateFee;
	}

	public Short getFlagType() {
		return flagType;
	}

	public void setFlagType(Short flagType) {
		this.flagType = flagType;
	}

	public String getMoneyComb() {
		return moneyComb;
	}

	public void setMoneyComb(String moneyComb) {
		this.moneyComb = moneyComb;
	}

	public Integer getTollRateVer() {
		return tollRateVer;
	}

	public void setTollRateVer(Integer tollRateVer) {
		this.tollRateVer = tollRateVer;
	}

	public Short getListName() {
		return listName;
	}

	public void setListName(Short listName) {
		this.listName = listName;
	}

	public String getVoidSerialNo() {
		return VoidSerialNo;
	}

	public void setVoidSerialNo(String voidSerialNo) {
		VoidSerialNo = voidSerialNo;
	}

	public Short getCPCCurrentVol() {
		return CPCCurrentVol;
	}

	public void setCPCCurrentVol(Short cPCCurrentVol) {
		CPCCurrentVol = cPCCurrentVol;
	}

	public Integer getMiles() {
		return miles;
	}

	public void setMiles(Integer miles) {
		this.miles = miles;
	}

	public Integer getTranslationMiles() {
		return translationMiles;
	}

	public void setTranslationMiles(Integer translationMiles) {
		this.translationMiles = translationMiles;
	}

	public Short getTollCardFreeListVerNo() {
		return tollCardFreeListVerNo;
	}

	public void setTollCardFreeListVerNo(Short tollCardFreeListVerNo) {
		this.tollCardFreeListVerNo = tollCardFreeListVerNo;
	}
	
	
	
}