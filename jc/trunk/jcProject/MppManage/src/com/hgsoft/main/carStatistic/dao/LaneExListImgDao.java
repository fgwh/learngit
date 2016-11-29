package com.hgsoft.main.carStatistic.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.hgsoft.main.carStatistic.entity.CarStatistic;
import com.hgsoft.main.carStatistic.entity.LaneExListImg;
import com.hgsoft.security.dao.BaseDao;
import com.hgsoft.util.DateUtil;

@Repository
public class LaneExListImgDao extends BaseDao<LaneExListImg> {
	
	public List<LaneExListImg> findImageBySeria(String seriaNo){
		String hql = " from LaneExListImg where pid='"+seriaNo+"' order by exListImageDate desc";
		return  (List<LaneExListImg>) this.findByHql(hql, null);
	}
	
	//etc图片保存到数据库
	public int saveAllEtcImage(List<LaneExListImg> imageList){
		StringBuilder strBuilder = new StringBuilder("");
		int count = 0;
		try{
			if(null!=imageList && imageList.size()>0){
				for(LaneExListImg image : imageList){
					strBuilder.delete(0, strBuilder.length());
					strBuilder.append("INSERT INTO tb_LaneExList_Img([id],[exListImgeType],[exListImageName],[exListImageSize],[exListFileType],[exListImageDate],[pid],[squadDate]) VALUES(")
					.append("'"+image.getId()+"',").append(image.getExListImgeType()+",").append("'"+image.getExListImageName()+"',").append(image.getExListImageSize()+",")
					.append("'"+image.getExListFileType()+"',").append("'"+DateUtil.format(image.getExListImageDate(), DateUtil.PATTERN_STRING_TIME)+"',")
					.append("'"+image.getPid()+"',").append("'"+image.getSquadDate()+"')");
					
					count += this.getSession().createSQLQuery(strBuilder.toString()).executeUpdate();
				}
			}
		} catch(Exception e){
			e.printStackTrace();
			
			return count;
		}
		
		return count;
	}
}
