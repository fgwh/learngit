package com.hgsoft.main.service;

import java.util.List;

import javax.annotation.Resource;

import com.hgsoft.main.dao.StationDao;
import com.hgsoft.main.entity.Station;
import com.hgsoft.security.service.BaseService;

public class StationService extends BaseService<Station> {
	@Resource
    public void setDao(StationDao dao) {
        super.setDao(dao);
    }
	

	public StationDao getStationDao() {
		return (StationDao) this.getDao();
	}
	
	/**
	 * 保存流水表中的收费站数据
	 * @param stationList
	 */
	public void saveAllStationData(List<Station> stationList){
		if(null!=stationList && stationList.size()>0){
			for(Station station:stationList){
				this.getStationDao().saveOrUpdate(station);
			}
			
		}
	}
	
}
