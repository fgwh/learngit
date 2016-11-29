package com.hgsoft.main.entity;


/**
 * 封装请求参数
 * @author 郭志强
 * @time 2016/9/12 15:39
 */
public class Conditions {
		
    private String startQueryDate; // 开始时间
    private String endQueryDate; // 开始时间
    private String days;  // 天数
	private String plateNum="";//车牌省份
	private String carNo=""; //车牌号
	private String laneExNo=""; //出口流水号
	private String laneEnNo="";//入口流水号
	private String exCPCSnNo="";//复合卡表面卡号

    public String getStartQueryDate() {
        return startQueryDate;
    }

    public void setStartQueryDate(String startQueryDate) {
        this.startQueryDate = startQueryDate;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

	public String getEndQueryDate() {
		return endQueryDate;
	}

	public void setEndQueryDate(String endQueryDate) {
		this.endQueryDate = endQueryDate;
	}

	public String getPlateNum() {
		return plateNum;
	}

	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getLaneExNo() {
		return laneExNo;
	}

	public void setLaneExNo(String laneExNo) {
		this.laneExNo = laneExNo;
	}

	public String getLaneEnNo() {
		return laneEnNo;
	}

	public void setLaneEnNo(String laneEnNo) {
		this.laneEnNo = laneEnNo;
	}

	public String getExCPCSnNo() {
		return exCPCSnNo;
	}

	public void setExCPCSnNo(String exCPCSnNo) {
		this.exCPCSnNo = exCPCSnNo;
	}


}