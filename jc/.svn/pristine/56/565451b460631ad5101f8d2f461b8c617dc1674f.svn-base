package com.hgsoft.main.service;

import java.util.List;

import javax.annotation.Resource;

import com.hgsoft.main.dao.RoadDao;
import com.hgsoft.main.dao.StationDao;
import com.hgsoft.main.entity.Road;
import com.hgsoft.security.service.BaseService;

public class RoadService extends BaseService<Road> {
	@Resource
    public void setDao(RoadDao dao) {
        super.setDao(dao);
    }
	
	public RoadDao getRoadDao() {
		return (RoadDao) this.getDao();
	}
	
	
	/**
	 * 保存流水表中的路段数据
	 * @param stationList
	 */
	public void saveAllRoadData(List<Road> roadList){
		if(null!=roadList && roadList.size()>0){
			for(Road road:roadList){
				this.getRoadDao().saveOrUpdate(road);
			}
			
		}
	}
}
