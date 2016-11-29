package com.hgsoft.main.recanalysis.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hgsoft.cxf.common.PropertiesUtil;
import com.hgsoft.main.recanalysis.dao.RecanalysisDao;
import com.hgsoft.main.recanalysis.entity.Conditions;
import com.hgsoft.security.entity.Admin;
import com.hgsoft.security.service.BaseService;
import com.hgsoft.util.Pager;

@Service
@Transactional
public class RecanalysisService extends BaseService{
	private static final String stationID = PropertiesUtil.getProperty("stationID");
	@Resource
	RecanalysisDao recanalysisdao;
	

	public float getRecanalysisList(Pager pager, Admin operator, Conditions conditons){
            return recanalysisdao. queryRecanalysisDao(pager,operator,conditons);
	}

/*	public List getOrgnameList(){
        return recanalysisdao.getAllcentral();
}
	
	
	public List getStationName(String imgId){
		return recanalysisdao.getStation(imgId);
		
	}*/
}
