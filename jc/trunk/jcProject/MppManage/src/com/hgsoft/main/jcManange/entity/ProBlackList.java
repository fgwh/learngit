package com.hgsoft.main.jcManange.entity;

import java.util.Date;

import com.hgsoft.security.entity.BaseEntity;

public class ProBlackList implements BaseEntity{
	
	private PK pk;
	
	private String vehPlate;
	
	private short vehPlateColor;
	
	private short vehFlag;
	
	private short vehClass;
	
	private short interceptOption;
	
	private Date startDate;
	
	private Date endDate;
	
	private String vehInfo;
	
	//外部字段   
    private String vehColorStr;
    
    private String vehClassStr;
    
    private String vehTypeStr;
    
    private String interceptStr;



	public PK getPk() {
		return pk;
	}

	public void setPk(PK pk) {
		this.pk = pk;
	}

	public String getVehPlate() {
		return vehPlate;
	}

	public void setVehPlate(String vehPlate) {
		this.vehPlate = vehPlate;
	}

	public short getVehPlateColor() {
		return vehPlateColor;
	}

	public void setVehPlateColor(short vehPlateColor) {
		this.vehPlateColor = vehPlateColor;
	}

	public short getVehFlag() {
		return vehFlag;
	}

	public void setVehFlag(short vehFlag) {
		this.vehFlag = vehFlag;
	}

	public short getVehClass() {
		return vehClass;
	}

	public void setVehClass(short vehClass) {
		this.vehClass = vehClass;
	}

	public short getInterceptOption() {
		return interceptOption;
	}

	public void setInterceptOption(short interceptOption) {
		this.interceptOption = interceptOption;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getVehInfo() {
		return vehInfo;
	}

	public void setVehInfo(String vehInfo) {
		this.vehInfo = vehInfo;
	}

	public String getVehColorStr() {
		return vehColorStr;
	}

	public void setVehColorStr(String vehColorStr) {
		this.vehColorStr = vehColorStr;
	}

	public String getVehClassStr() {
		return vehClassStr;
	}

	public void setVehClassStr(String vehClassStr) {
		this.vehClassStr = vehClassStr;
	}

	public String getVehTypeStr() {
		return vehTypeStr;
	}

	public void setVehTypeStr(String vehTypeStr) {
		this.vehTypeStr = vehTypeStr;
	}

	public String getInterceptStr() {
		return interceptStr;
	}

	public void setInterceptStr(String interceptStr) {
		this.interceptStr = interceptStr;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}
				
}