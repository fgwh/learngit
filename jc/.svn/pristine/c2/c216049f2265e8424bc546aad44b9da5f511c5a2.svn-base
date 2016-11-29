package com.hgsoft.main.jcManange.entity;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hgsoft.security.entity.Admin;
import com.hgsoft.util.CacheUtil;
import com.hgsoft.util.StringUtil;
import com.ibm.icu.text.SimpleDateFormat;

public class Conditions {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	// 拦截开始时间
	private String startQueryDate = "";
	// 拦截结束时间
	private String endQueryDate = "";
	// 车型
	private String vehClass = "";
	// 车牌省份
	private String plateNum = "";
	// 车牌号码
	private String carNo = "";
	// 车牌
	private String vehPlate = "";
	// 客货车标识
	private String vehFlag = "";
	// 车牌颜色
	private String vehPlateColor = "";
	// 拦截选项
	private String interceptOption = "";
	// 拦截类型-大类
	private String vehBigType = "";
	// 逃费类型-中类
	private String vehMidType = "";
	// 逃费类型-细类
	private String vehSmallType = "";
	// 员工编号
	private String staffNo = "";
	// 数据源 0 路段 1全省
	private String dataSource;
	// id
	private String id;

	private Date startDate;

	private Date endDate;

	private String opinion;

	private String status;

	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}

	public String getStartQueryDate() {
		return startQueryDate;
	}

	public void setStartQueryDate(String startQueryDate) {
		this.startQueryDate = startQueryDate;
	}

	public String getEndQueryDate() {
		return endQueryDate;
	}

	public void setEndQueryDate(String endQueryDate) {
		this.endQueryDate = endQueryDate;
	}

	public String getVehClass() {
		return vehClass;
	}

	public void setVehClass(String vehClass) {
		this.vehClass = vehClass;
	}

	public String getPlateNum() {
		return plateNum;
	}

	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}

	public String getVehFlag() {
		return vehFlag;
	}

	public void setVehFlag(String vehFlag) {
		this.vehFlag = vehFlag;
	}

	public String getVehPlateColor() {
		return vehPlateColor;
	}

	public String getVehPlate() {
		return vehPlate;
	}

	public void setVehPlate(String vehPlate) {
		this.vehPlate = vehPlate;
	}

	public void setVehPlateColor(String vehPlateColor) {
		this.vehPlateColor = vehPlateColor;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getInterceptOption() {
		return interceptOption;
	}

	public void setInterceptOption(String interceptOption) {
		this.interceptOption = interceptOption;
	}

	public String getVehBigType() {
		return vehBigType;
	}

	public void setVehBigType(String vehBigType) {
		this.vehBigType = vehBigType;
	}

	public String getVehMidType() {
		return vehMidType;
	}

	public void setVehMidType(String vehMidType) {
		this.vehMidType = vehMidType;
	}

	public String getVehSmallType() {
		return vehSmallType;
	}

	public void setVehSmallType(String vehSmallType) {
		this.vehSmallType = vehSmallType;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}




		//根据条件数组返回条件值数组
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public Object[] toArray(String[] selectName){
		
			Object selectValue[] = new Object[selectName.length];
			Map map= new HashMap();
			int num=0;								
			Field[ ] fields = this.getClass().getDeclaredFields( ); 
			for ( Field field : fields )  
	        {  
	            // 如果不为空，设置可见性，然后返回  
	            field.setAccessible( true );  	                      	
	            	 try  
	                 {     
	            		 map.put(field.getName( ),field.get( this ));
	            		 
	                 }  
	                 catch ( Exception e )  
	                 {  
	                     e.printStackTrace();  
	                 } 	                
	        
	        }  
			
			for(String name : selectName){
					selectValue[num]=map.get(name);	
					num++;
			}
			return selectValue;
		}
		

		  //异常稽查查询条件
	       
        private String auditStatus;//稽查状态
        private String excepDisplayType;//异常展示类型
        
        private String switchCard="";//倒卡
        private String switchCardNum;//倒卡
        private String switchCardNum2;//近期倒卡
        private String  switchCardPercent;
        
        private String chargesNotIndetify="";//有收费无标识
        private String chargesNotIndetifyNum;//有收费无标识
        private String chargesNotIndetifyNum2;//近期有收费无标识
        private String chargesNotIndetifyPercent;//有收费无标识
        
        private String indentifyPointExcep="";//标识点异常
        private String indentifyPointExcepNum;//标识点异常
        private String indentifyPointExcepNum2;//近期标识点异常
        private String indentifyPointExcepPercent;//标识点异常
        
        
        private String etcPlateNotMeet="";//ETC车牌不符
        private String etcPlateNotMeetNum;//ETC车牌不符
        private String etcPlateNotMeetNum2;//近期ETC车牌不符
        private String etcPlateNotMeetPercent;//ETC车牌不符
        
        private String etcModelsNotMeet="";//ETC车型不符
        private String etcModelsNotMeetNum;//ETC车型不符
        private String etcModelsNotMeetNum2;//近期ETC车型不符
        private String etcModelsNotMeetPercent;//ETC车型不符
        
        private String axisGroupExcep="";//轴组异常
        private String axisGroupExcepNum="";//轴组异常
        private String axisGroupExcepNum2="";//近期轴组异常
        private String axisGroupExcepPercent;
        
        private String historyEscape="";//历史性逃费
        private String  historyEscapeNum;
        private String batChplateID;
        
        

      
		public String getBatChplateID() {
			return batChplateID;
		}
		public void setBatChplateID(String batChplateID) {
			this.batChplateID = batChplateID;
		}
		public String getSwitchCard() {
			return switchCard;
		}
		public void setSwitchCard(String switchCard) {
			this.switchCard = switchCard;
		}

		public String getChargesNotIndetify() {
			return chargesNotIndetify;
		}

		public void setChargesNotIndetify(String chargesNotIndetify) {
			this.chargesNotIndetify = chargesNotIndetify;
		}

		public String getIndentifyPointExcep() {
			return indentifyPointExcep;
		}

		public void setIndentifyPointExcep(String indentifyPointExcep) {
			this.indentifyPointExcep = indentifyPointExcep;
		}

		public String getEtcPlateNotMeet() {
			return etcPlateNotMeet;
		}

		public void setEtcPlateNotMeet(String etcPlateNotMeet) {
			this.etcPlateNotMeet = etcPlateNotMeet;
		}

		public String getEtcModelsNotMeet() {
			return etcModelsNotMeet;
		}

		public void setEtcModelsNotMeet(String etcModelsNotMeet) {
			this.etcModelsNotMeet = etcModelsNotMeet;
		}

		public String getAxisGroupExcep() {
			return axisGroupExcep;
		}

		public void setAxisGroupExcep(String axisGroupExcep) {
			this.axisGroupExcep = axisGroupExcep;
		}

		public String getHistoryEscape() {
			return historyEscape;
		}

		public void setHistoryEscape(String historyEscape) {
			this.historyEscape = historyEscape;
		}

	












	








		public String getAuditStatus() {
			return auditStatus;
		}

		public void setAuditStatus(String auditStatus) {
			this.auditStatus = auditStatus;
		}

		public String getExcepDisplayType() {
			return excepDisplayType;
		}

		public void setExcepDisplayType(String excepDisplayType) {
			this.excepDisplayType = excepDisplayType;
		}

		public String getSwitchCardNum() {
			return switchCardNum;
		}

		public void setSwitchCardNum(String switchCardNum) {
			this.switchCardNum = switchCardNum;
		}

		public String getSwitchCardNum2() {
			return switchCardNum2;
		}

		public void setSwitchCardNum2(String switchCardNum2) {
			this.switchCardNum2 = switchCardNum2;
		}

		public String getSwitchCardPercent() {
			return switchCardPercent;
		}

		public void setSwitchCardPercent(String switchCardPercent) {
			this.switchCardPercent = switchCardPercent;
		}

		public String getChargesNotIndetifyNum() {
			return chargesNotIndetifyNum;
		}

		public void setChargesNotIndetifyNum(String chargesNotIndetifyNum) {
			this.chargesNotIndetifyNum = chargesNotIndetifyNum;
		}

		public String getChargesNotIndetifyNum2() {
			return chargesNotIndetifyNum2;
		}

		public void setChargesNotIndetifyNum2(String chargesNotIndetifyNum2) {
			this.chargesNotIndetifyNum2 = chargesNotIndetifyNum2;
		}

		public String getChargesNotIndetifyPercent() {
			return chargesNotIndetifyPercent;
		}

		public void setChargesNotIndetifyPercent(String chargesNotIndetifyPercent) {
			this.chargesNotIndetifyPercent = chargesNotIndetifyPercent;
		}

		public String getIndentifyPointExcepNum() {
			return indentifyPointExcepNum;
		}

		public void setIndentifyPointExcepNum(String indentifyPointExcepNum) {
			this.indentifyPointExcepNum = indentifyPointExcepNum;
		}

		public String getIndentifyPointExcepNum2() {
			return indentifyPointExcepNum2;
		}

		public void setIndentifyPointExcepNum2(String indentifyPointExcepNum2) {
			this.indentifyPointExcepNum2 = indentifyPointExcepNum2;
		}

		public String getIndentifyPointExcepPercent() {
			return indentifyPointExcepPercent;
		}

		public void setIndentifyPointExcepPercent(String indentifyPointExcepPercent) {
			this.indentifyPointExcepPercent = indentifyPointExcepPercent;
		}

		public String getEtcPlateNotMeetNum() {
			return etcPlateNotMeetNum;
		}

		public void setEtcPlateNotMeetNum(String etcPlateNotMeetNum) {
			this.etcPlateNotMeetNum = etcPlateNotMeetNum;
		}

		public String getEtcPlateNotMeetNum2() {
			return etcPlateNotMeetNum2;
		}

		public void setEtcPlateNotMeetNum2(String etcPlateNotMeetNum2) {
			this.etcPlateNotMeetNum2 = etcPlateNotMeetNum2;
		}

		public String getEtcPlateNotMeetPercent() {
			return etcPlateNotMeetPercent;
		}

		public void setEtcPlateNotMeetPercent(String etcPlateNotMeetPercent) {
			this.etcPlateNotMeetPercent = etcPlateNotMeetPercent;
		}

		public String getEtcModelsNotMeetNum() {
			return etcModelsNotMeetNum;
		}

		public void setEtcModelsNotMeetNum(String etcModelsNotMeetNum) {
			this.etcModelsNotMeetNum = etcModelsNotMeetNum;
		}

		public String getEtcModelsNotMeetNum2() {
			return etcModelsNotMeetNum2;
		}

		public void setEtcModelsNotMeetNum2(String etcModelsNotMeetNum2) {
			this.etcModelsNotMeetNum2 = etcModelsNotMeetNum2;
		}

		public String getEtcModelsNotMeetPercent() {
			return etcModelsNotMeetPercent;
		}

		public void setEtcModelsNotMeetPercent(String etcModelsNotMeetPercent) {
			this.etcModelsNotMeetPercent = etcModelsNotMeetPercent;
		}

		public String getAxisGroupExcepNum() {
			return axisGroupExcepNum;
		}

		public void setAxisGroupExcepNum(String axisGroupExcepNum) {
			this.axisGroupExcepNum = axisGroupExcepNum;
		}

		public String getAxisGroupExcepNum2() {
			return axisGroupExcepNum2;
		}

		public void setAxisGroupExcepNum2(String axisGroupExcepNum2) {
			this.axisGroupExcepNum2 = axisGroupExcepNum2;
		}



		public String getAxisGroupExcepPercent() {
			return axisGroupExcepPercent;
		}

		public void setAxisGroupExcepPercent(String axisGroupExcepPercent) {
			this.axisGroupExcepPercent = axisGroupExcepPercent;
		}

		public String getHistoryEscapeNum() {
			return historyEscapeNum;
		}

		public void setHistoryEscapeNum(String historyEscapeNum) {
			this.historyEscapeNum = historyEscapeNum;
		}
		
}
