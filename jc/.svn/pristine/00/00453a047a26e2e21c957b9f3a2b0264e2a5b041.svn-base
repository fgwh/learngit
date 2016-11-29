package com.hgsoft.main.carStatistic.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hgsoft.main.carStatistic.dao.LaneExListImgDao;
import com.hgsoft.main.carStatistic.entity.CarStatistic;
import com.hgsoft.main.carStatistic.entity.LaneExListImg;
import com.hgsoft.security.service.BaseService;

@Service
@Transactional
public class LaneExListImgService extends BaseService<LaneExListImg> {
	
	@Resource
	public void setDao(LaneExListImgDao laneExListImgDao) {
		super.setDao(laneExListImgDao);
	}
	
	public LaneExListImgDao getLaneExListImgDao() {
		return (LaneExListImgDao) this.getDao();
	}
	
	//获取出口图片
	public List<LaneExListImg> queryLaneExListImgByExSeria(String exSerialNo){
		return this.getLaneExListImgDao().findImageBySeria(exSerialNo);
	}
	
	//获取入口图片
	public List<LaneExListImg> queryLaneExListImgByEnSeria(String enSerialNo){
		return this.getLaneExListImgDao().findImageBySeria(enSerialNo);
	}
	
	//保存路段中心数据库的etc图片数据
	public int saveAllLaneExListImg(List<LaneExListImg> imageList){
		return this.getLaneExListImgDao().saveAllEtcImage(imageList);
	}
}
