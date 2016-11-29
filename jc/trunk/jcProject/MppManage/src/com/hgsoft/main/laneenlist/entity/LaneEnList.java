package com.hgsoft.main.laneenlist.entity;// default package

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * 入口流水表
 *
 * @author 郭志强
 * @time 2016/9/9 12:50
 */
public class LaneEnList implements Serializable {
    // Fields    


    private Short enRecordNo;  // 入口记录号
    private String laneEnSerialNo; // 入口流水号
    private Short cardNetRoadId; // 复合卡卡网络编号
    private Long icinCardId; // IC卡内部编号
    private Short enNetRoadId; // 入口路网编号
    private Short enRoadId; // 入口路段编号
    private Integer enStationId; // 入口站编号
    private Short enLaneId; // 入口车道编号
    private Short enLaneType; // 车道类型
    private Timestamp enTime; // 入口日期及时间
    private Long enOperatorId; // 入口收费员工号
    private String enOpCardNo; // 入口收费员身份卡表面号
    private Long enOpCardId; // 入口收费员身份卡ID号
    private Short enShiftId; // 入口收费员班次
    private String enCpcsnNo; // 入口复合卡表面号
    private Long enCpcinId; // 入口复合卡内部ID号
    private Short enVehicleClass; //入口车型
    private Short enVehicleStatus; // 入口车种
    private String enVehiclePlate; // 入口车牌
    private String enVehIdentifyPlate; // 入口识别车牌
    private Short enVehicleFlag; // 入口客货标识
    private Short enIccissuer; // 入口非现金卡片路网编号
    private String enCpucardSnNo; // 入口CPU卡表面号
    private Long enCpucardId; // 入口CPU卡内部ID号
    private Short enCpucardType;// 入口CPU卡类型
    private String enEtctermCode; // 入口端机编码
    private String enObunum; // 入口OBU的应用序列号
    private String enEtctradNo; // 入口卡交易序号
    private Integer enEtctermTradNo; // 入口终端机交易序号
    private String enEtctac; // 入口TAC码
    private Timestamp squadDate; // 入口工班日期
    private Integer imageSerialNo; // 入口图片编号
    private Integer cardBoxNo; // 入口卡盒号
    private Integer cardTrunkNo; // 入口卡箱号
    private String appVer; // 入口车道程序版本
    private Long dealStatus; // 交易状态
    private Integer deviceStatus; // 设备状态
    private Short recordType; // 记录类型
    private Short vehCount; // 车辆数
    private Short ticketType; // 通行券类型
    private Short listName; // 流水文件名
    private Integer billNo; // 纸券编码
    private String voidSerialNo; // 被冲减流水号
    private Integer verifyCode; // 校验码
    private Short cpccurrentVol; // RFID复合通行卡当前电量
    private Integer spare1; // 备用1
    private Integer spare2; // 备用2
    private String spare3; // 备用3
    private String spare4; // 备用4
    private Timestamp uploadTime; // 上传时间


    /**
     * default constructor
     */
    public LaneEnList() {
    }


    // Property accessors
    public Short getEnRecordNo() {
        return this.enRecordNo;
    }

    public void setEnRecordNo(Short enRecordNo) {
        this.enRecordNo = enRecordNo;
    }

    public String getLaneEnSerialNo() {
        return this.laneEnSerialNo;
    }

    public void setLaneEnSerialNo(String laneEnSerialNo) {
        this.laneEnSerialNo = laneEnSerialNo;
    }

    public Short getCardNetRoadId() {
        return this.cardNetRoadId;
    }

    public void setCardNetRoadId(Short cardNetRoadId) {
        this.cardNetRoadId = cardNetRoadId;
    }

    public Long getIcinCardId() {
        return this.icinCardId;
    }

    public void setIcinCardId(Long icinCardId) {
        this.icinCardId = icinCardId;
    }

    public Short getEnNetRoadId() {
        return this.enNetRoadId;
    }

    public void setEnNetRoadId(Short enNetRoadId) {
        this.enNetRoadId = enNetRoadId;
    }

    public Short getEnRoadId() {
        return this.enRoadId;
    }

    public void setEnRoadId(Short enRoadId) {
        this.enRoadId = enRoadId;
    }

    public Integer getEnStationId() {
        return this.enStationId;
    }

    public void setEnStationId(Integer enStationId) {
        this.enStationId = enStationId;
    }

    public Short getEnLaneId() {
        return this.enLaneId;
    }

    public void setEnLaneId(Short enLaneId) {
        this.enLaneId = enLaneId;
    }

    public Short getEnLaneType() {
        return this.enLaneType;
    }

    public void setEnLaneType(Short enLaneType) {
        this.enLaneType = enLaneType;
    }

    public Timestamp getEnTime() {
        return this.enTime;
    }

    public void setEnTime(Timestamp enTime) {
        this.enTime = enTime;
    }

    public Long getEnOperatorId() {
        return this.enOperatorId;
    }

    public void setEnOperatorId(Long enOperatorId) {
        this.enOperatorId = enOperatorId;
    }

    public String getEnOpCardNo() {
        return this.enOpCardNo;
    }

    public void setEnOpCardNo(String enOpCardNo) {
        this.enOpCardNo = enOpCardNo;
    }

    public Long getEnOpCardId() {
        return this.enOpCardId;
    }

    public void setEnOpCardId(Long enOpCardId) {
        this.enOpCardId = enOpCardId;
    }

    public Short getEnShiftId() {
        return this.enShiftId;
    }

    public void setEnShiftId(Short enShiftId) {
        this.enShiftId = enShiftId;
    }

    public String getEnCpcsnNo() {
        return this.enCpcsnNo;
    }

    public void setEnCpcsnNo(String enCpcsnNo) {
        this.enCpcsnNo = enCpcsnNo;
    }

    public Long getEnCpcinId() {
        return this.enCpcinId;
    }

    public void setEnCpcinId(Long enCpcinId) {
        this.enCpcinId = enCpcinId;
    }

    public Short getEnVehicleClass() {
        return this.enVehicleClass;
    }

    public void setEnVehicleClass(Short enVehicleClass) {
        this.enVehicleClass = enVehicleClass;
    }

    public Short getEnVehicleStatus() {
        return this.enVehicleStatus;
    }

    public void setEnVehicleStatus(Short enVehicleStatus) {
        this.enVehicleStatus = enVehicleStatus;
    }

    public String getEnVehiclePlate() {
        return this.enVehiclePlate;
    }

    public void setEnVehiclePlate(String enVehiclePlate) {
        this.enVehiclePlate = enVehiclePlate;
    }

    public String getEnVehIdentifyPlate() {
        return this.enVehIdentifyPlate;
    }

    public void setEnVehIdentifyPlate(String enVehIdentifyPlate) {
        this.enVehIdentifyPlate = enVehIdentifyPlate;
    }

    public Short getEnVehicleFlag() {
        return this.enVehicleFlag;
    }

    public void setEnVehicleFlag(Short enVehicleFlag) {
        this.enVehicleFlag = enVehicleFlag;
    }

    public Short getEnIccissuer() {
        return this.enIccissuer;
    }

    public void setEnIccissuer(Short enIccissuer) {
        this.enIccissuer = enIccissuer;
    }

    public String getEnCpucardSnNo() {
        return this.enCpucardSnNo;
    }

    public void setEnCpucardSnNo(String enCpucardSnNo) {
        this.enCpucardSnNo = enCpucardSnNo;
    }

    public Long getEnCpucardId() {
        return this.enCpucardId;
    }

    public void setEnCpucardId(Long enCpucardId) {
        this.enCpucardId = enCpucardId;
    }

    public Short getEnCpucardType() {
        return this.enCpucardType;
    }

    public void setEnCpucardType(Short enCpucardType) {
        this.enCpucardType = enCpucardType;
    }

    public String getEnEtctermCode() {
        return this.enEtctermCode;
    }

    public void setEnEtctermCode(String enEtctermCode) {
        this.enEtctermCode = enEtctermCode;
    }

    public String getEnObunum() {
        return this.enObunum;
    }

    public void setEnObunum(String enObunum) {
        this.enObunum = enObunum;
    }

    public String getEnEtctradNo() {
        return this.enEtctradNo;
    }

    public void setEnEtctradNo(String enEtctradNo) {
        this.enEtctradNo = enEtctradNo;
    }

    public Integer getEnEtctermTradNo() {
        return this.enEtctermTradNo;
    }

    public void setEnEtctermTradNo(Integer enEtctermTradNo) {
        this.enEtctermTradNo = enEtctermTradNo;
    }

    public String getEnEtctac() {
        return this.enEtctac;
    }

    public void setEnEtctac(String enEtctac) {
        this.enEtctac = enEtctac;
    }

    public Timestamp getSquadDate() {
        return this.squadDate;
    }

    public void setSquadDate(Timestamp squadDate) {
        this.squadDate = squadDate;
    }

    public Integer getImageSerialNo() {
        return this.imageSerialNo;
    }

    public void setImageSerialNo(Integer imageSerialNo) {
        this.imageSerialNo = imageSerialNo;
    }

    public Integer getCardBoxNo() {
        return this.cardBoxNo;
    }

    public void setCardBoxNo(Integer cardBoxNo) {
        this.cardBoxNo = cardBoxNo;
    }

    public Integer getCardTrunkNo() {
        return this.cardTrunkNo;
    }

    public void setCardTrunkNo(Integer cardTrunkNo) {
        this.cardTrunkNo = cardTrunkNo;
    }

    public String getAppVer() {
        return this.appVer;
    }

    public void setAppVer(String appVer) {
        this.appVer = appVer;
    }

    public Long getDealStatus() {
        return this.dealStatus;
    }

    public void setDealStatus(Long dealStatus) {
        this.dealStatus = dealStatus;
    }

    public Integer getDeviceStatus() {
        return this.deviceStatus;
    }

    public void setDeviceStatus(Integer deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public Short getRecordType() {
        return this.recordType;
    }

    public void setRecordType(Short recordType) {
        this.recordType = recordType;
    }

    public Short getVehCount() {
        return this.vehCount;
    }

    public void setVehCount(Short vehCount) {
        this.vehCount = vehCount;
    }

    public Short getTicketType() {
        return this.ticketType;
    }

    public void setTicketType(Short ticketType) {
        this.ticketType = ticketType;
    }

    public Short getListName() {
        return this.listName;
    }

    public void setListName(Short listName) {
        this.listName = listName;
    }

    public Integer getBillNo() {
        return this.billNo;
    }

    public void setBillNo(Integer billNo) {
        this.billNo = billNo;
    }

    public String getVoidSerialNo() {
        return this.voidSerialNo;
    }

    public void setVoidSerialNo(String voidSerialNo) {
        this.voidSerialNo = voidSerialNo;
    }

    public Integer getVerifyCode() {
        return this.verifyCode;
    }

    public void setVerifyCode(Integer verifyCode) {
        this.verifyCode = verifyCode;
    }

    public Short getCpccurrentVol() {
        return this.cpccurrentVol;
    }

    public void setCpccurrentVol(Short cpccurrentVol) {
        this.cpccurrentVol = cpccurrentVol;
    }

    public Integer getSpare1() {
        return this.spare1;
    }

    public void setSpare1(Integer spare1) {
        this.spare1 = spare1;
    }

    public Integer getSpare2() {
        return this.spare2;
    }

    public void setSpare2(Integer spare2) {
        this.spare2 = spare2;
    }

    public String getSpare3() {
        return this.spare3;
    }

    public void setSpare3(String spare3) {
        this.spare3 = spare3;
    }

    public String getSpare4() {
        return this.spare4;
    }

    public void setSpare4(String spare4) {
        this.spare4 = spare4;
    }

    public Timestamp getUploadTime() {
        return this.uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }


}