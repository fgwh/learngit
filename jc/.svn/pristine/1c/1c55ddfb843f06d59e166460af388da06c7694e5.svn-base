package com.hgsoft.main.integrity.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hgsoft.main.integrity.dao.DriverIntegrityDao;
import com.hgsoft.main.integrity.entity.DriverIntegrity;
import com.hgsoft.security.service.BaseService;
import com.hgsoft.util.Property;
@Service
@Transactional
public class DriverIntegrityService extends BaseService<DriverIntegrity>{
	@Resource
    public void setDao(DriverIntegrityDao dao) {
        super.setDao(dao);
    }
	public DriverIntegrityDao getDriverIntegrityDao(){
		return (DriverIntegrityDao) this.getDao();
	}
	//根据姓名，身份证号查询相关的诚信数据
	public List<DriverIntegrity> getDriverIntegrityList(String name,String driverNo){
		List<DriverIntegrity> driverIntegrityList = getDriverIntegrityDao().findAll(Property.eq("driverName", name),Property.eq("driverNo", driverNo));
		
		
		return driverIntegrityList;
	}
	/**
	 * 更新驾驶员的诚信数据
	 * @param d 驾驶人类
	 * @param fakeDriverNum 假冒驾驶证
	 * @param fakeGreenNum  假冒绿通
	 * @param noNormalNum  不正常 
	 * @param normalNum 正常
	 * @param score 分数
	 * @param b 是否假冒
	 */
	public void updateDriverIntegrity(DriverIntegrity d,int fakeDriverNum,int fakeGreenNum,int noNormalNum,int normalNum,float score,boolean b){
		d.setFakeDriverNum(d.getFakeDriverNum()+fakeDriverNum);
		d.setFakeGreenNum(d.getFakeGreenNum()+fakeGreenNum);
		d.setNoNormalNum(d.getNoNormalNum()+noNormalNum);
		d.setNormalNum(d.getNormalNum()+normalNum);
		if(b){
			d.setDriverScore(0.0f);
		}else{
			d.setDriverScore(d.getDriverScore()+score);
		}
		
	}
	/**
	 * 根据姓名和身份证号查询驾驶员诚信信息
	 * @param driverName
	 * @param driverNo
	 * @return
	 */
	public DriverIntegrity getDriverIntegrity(String driverNo){
		return getDriverIntegrityDao().getDriverIntegrity(driverNo);
	}

}
