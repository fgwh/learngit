package com.hgsoft.main.integrity.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hgsoft.main.integrity.dao.OperationIntegrityDao;
import com.hgsoft.main.integrity.entity.DriverIntegrity;
import com.hgsoft.main.integrity.entity.OperationIntegrity;
import com.hgsoft.security.service.BaseService;
import com.hgsoft.util.Property;
@Service
@Transactional
public class OperationIntegrityService extends BaseService<OperationIntegrity>{
	@Resource
	public void setDao(OperationIntegrityDao operationIntegrityDao){
		super.setDao(operationIntegrityDao);
	}
	public OperationIntegrityDao getOperationIntegrityDao(){
		
		return (OperationIntegrityDao) this.getDao();
	}
	//根据业户名称查询相关的诚信数据
	public List<OperationIntegrity> getOperationIntegrityList(String operationName){
		List<OperationIntegrity> operationIntegrityList = getOperationIntegrityDao().findAll(Property.eq("operationName", operationName));
		
		
		return operationIntegrityList;
	}
	/**
	 * 更新企业诚信数据
	 * @param o 企业类
	 * @param fakeOperationNum 假冒企业
	 * @param fakeGreenNum  假冒绿通
	 * @param noNormalNum  不正常
	 * @param normalNum  正常
	 * @param score 分数
	 * @param b  是否假冒
	 */
	public void updateOperationIntegrity(OperationIntegrity o,int fakeOperationNum,int fakeGreenNum,int noNormalNum,int normalNum,float score,boolean b){
		o.setFakeOperationNum(o.getFakeOperationNum()+fakeOperationNum);
		o.setFakeGreenNum(o.getFakeGreenNum()+fakeGreenNum);
		o.setNoNormalNum(o.getNoNormalNum()+noNormalNum);
		o.setNormalNum(o.getNormalNum()+normalNum);
		if(b){
			o.setOperationScore(o.getOperationScore()-20);
		}else{
			o.setOperationScore(o.getOperationScore()+score);
		}
	}
	public OperationIntegrity getOperationIntegrity(String operationNo){
		return getOperationIntegrityDao().getOperationIntegrity(operationNo);
	}

}
