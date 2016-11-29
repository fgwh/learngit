package com.hgsoft.main.integrity.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hgsoft.main.integrity.dao.VehIntegrityDao;
import com.hgsoft.main.integrity.entity.VehIntegrity;
import com.hgsoft.security.service.BaseService;
import com.hgsoft.util.Property;
@Service
@Transactional
public class VehIntegrityService extends BaseService<VehIntegrity>{
	@Resource
    public void setDao(VehIntegrityDao dao) {
        super.setDao(dao);
    }
	public VehIntegrityDao getVehIntegrityDao(){
		return (VehIntegrityDao) this.getDao();
	}
	//通过车牌号和车牌颜色查询诚信数据
	public List<VehIntegrity> getVehIntegrityList(String vehPlate,String vehPlateColor){
		List<VehIntegrity> vehIntegrityList = getVehIntegrityDao().findAll(Property.eq("vehPlate", vehPlate),Property.eq("vehPlateColor", vehPlateColor));
		return vehIntegrityList;
	}
	/**
	 * 更新车辆诚信数据
	 * @param v
	 * @param fakeDriverNum
	 * @param fakeGreenNum
	 * @param noNormalNum
	 * @param normalNum
	 */
	public void updateVehIntegrity(VehIntegrity v,int fakeDriverNum,int fakeGreenNum,int noNormalNum,int normalNum,float score,boolean b){
		v.setFakeDrivingNum(v.getFakeDrivingNum()+fakeDriverNum);
		v.setFakeGreenNum(v.getFakeGreenNum()+fakeGreenNum);
		v.setNoNormalNum(v.getNoNormalNum()+noNormalNum);
		v.setNormalNum(v.getNormalNum()+normalNum);
		if(b){
			v.setVehScore(0);
		}else{
		v.setVehScore(v.getVehScore()+score);
		}
	    saveOrUpdate(v);
		
	}
	/**
	 * 新增车辆诚信记录
	 * @param vehPlate
	 * @param vehPlateColor
	 * @param vehType
	 */
	public void saveVehIntegrity(String vehPlate,String vehPlateColor,String vehType){
		VehIntegrity v = new VehIntegrity();
		v.setVehPlate(vehPlate);
		v.setVehPlateColor(vehPlateColor);
		v.setVehType(vehType);
		v.setFakeDrivingNum(0);
		v.setFakeGreenNum(0);
		v.setNoNormalNum(0);
		v.setNormalNum(0);
		v.setVehScore(60);
		v.setReportTime(new Date());
		save(v);
	}
	public VehIntegrity getVehIntegrity(String vehPlate,String vehPlateColor){
		return getVehIntegrityDao().getVehIntegrity(vehPlate, vehPlateColor);

	}


}
