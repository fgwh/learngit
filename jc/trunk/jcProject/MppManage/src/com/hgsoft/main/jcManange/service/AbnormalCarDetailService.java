package com.hgsoft.main.jcManange.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hgsoft.cxf.common.PropertiesUtil;
import com.hgsoft.main.jcManange.dao.AbnormalCarDetailDao;
import com.hgsoft.main.jcManange.entity.AbnormalCarDetail;
import com.hgsoft.security.service.BaseService;

@Service
@SuppressWarnings({ "rawtypes" })
public class AbnormalCarDetailService extends BaseService<AbnormalCarDetail> {
	private static final String roadCode = PropertiesUtil.getProperty("ROAD_CODE");
	@Resource
	AbnormalCarDetailDao abnormalcardetaildao;

	public List<AbnormalCarDetail> getAbnorLiuShui(String plateNum, String num) {
		return abnormalcardetaildao.getAbnorLiuShui(plateNum, num);
	}

	public void updateAbnorCat(String ids){

	   String[] idStr=ids.split(",");
   	   String id[]=new String[idStr.length];   //ID数组
   	   for (int i = 0; i < idStr.length; i++) {
          	id[i]=idStr[i].substring(0, idStr[i].indexOf("_"));
  	  	}
		if(id.length>=0){
		for (int i = 0; i < id.length; i++) {
			abnormalcardetaildao.updateAbnor(id[i]);
		}
		}
		return ;
	}
	
	public List<Object> getAbnormalCarDetail(String id){
		return abnormalcardetaildao.getAbnormalCarDetail(id);
	}

	/**
	 * 
	 * 
	 * 标识点异常一
	 */
	public List<Object[]> identifyPointOne(String year, String beginDate, String endDate) {
		return abnormalcardetaildao.identifyPointOne(year, beginDate, endDate,roadCode);
	}

	/**
	 * 
	 * 
	 * 标识点异常二
	 */
	public List<Object[]> identifyPointTwo(String year, String beginDate, String endDate) {
		return abnormalcardetaildao.identifyPointTwo(year, beginDate, endDate);
	}
	/**
	 * 
	 * 
	 * 倒卡异常
	 */
	public List<Object[]> getPourCarException(String year, String beginDate, String endDate) {

		return abnormalcardetaildao.getPourCarException(year, beginDate, endDate);

	}

	/**
	 * 车型与轴组类型异常分析 暂时只分析 车型为5 轴组为2 的情况
	 * 
	 * @param year
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List<Object[]> getAxisNumException(String year, String beginDate, String endDate) {
		return abnormalcardetaildao.getAxisNumException(year, beginDate, endDate);
	}

	/**
	 * 保存信息到可疑车辆信息表
	/**
	 * 车型与轴组类型异常分析 暂时只分析 车型为5 轴组为2 的情况
	 * 
	 * @param year
	 * @param beginDate
	 * @param endDate
	 * @return
	 */

	/**
	 * 保存信息到可疑车辆信息表
	 * 
	 * @param list
	 * @param ExceptionType
	 */
	public void saveAbnorCarList(List<Object[]> list, String ExceptionType) {
		abnormalcardetaildao.saveAbnorCarList(list, ExceptionType);
	}

	public int deleteByReply() {
		return abnormalcardetaildao.deleteByReply();
	}

	/**
	 * 
	 * 
	 * ETC车型异常
	 */
	public List<Object[]> getETCVehTypeException(String year, String beginDate, String endDate) {
		return abnormalcardetaildao.getETCVehTypeException(year, beginDate, endDate);
	}

	/**
	 * 
	 * 
	 * ETC车牌异常
	 */
	public List<Object[]> getETCVehPlateException(String year, String beginDate, String endDate) {
		return abnormalcardetaildao.getETCVehPlateException(year, beginDate, endDate);
	}


}
