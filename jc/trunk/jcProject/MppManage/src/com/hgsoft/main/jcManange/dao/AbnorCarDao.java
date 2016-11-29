package com.hgsoft.main.jcManange.dao;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hgsoft.main.dao.BaseDao;
import com.hgsoft.main.jcManange.entity.AbnormalCarDetail;
import com.hgsoft.main.jcManange.entity.Conditions;
import com.hgsoft.util.Pager;
import com.hgsoft.util.StringUtil;
@Repository
public class AbnorCarDao extends BaseDao< Object[]>{
	
	
	
	
	public List<Object[]> getAbnorCar(Conditions  conditions,Pager pager){
		 Calendar a=Calendar.getInstance();
		 int year=a.get(Calendar.YEAR);
		String eds=conditions.getExcepDisplayType();
		StringBuffer sql=new StringBuffer("SELECT * FROM(SELECT  (SELECT COUNT(*) FROM tb_LaneExList_"+year+" where  AbnormalCar.Exvehicleplate=tb_LaneExList_"+year+".Exvehicleplate)as PassNumber, * FROM   AbnormalCar)t where 1=1");
		//倒卡
		if(!StringUtil.isTrimEmpty(conditions.getSwitchCard())){
			 
			  if(!StringUtil.isTrimEmpty(conditions.getSwitchCardNum())){
				  sql.append("  and t.SwitchCard>='"+conditions.getSwitchCardNum()+"'  ");
			  }
			  if(!StringUtil.isTrimEmpty(conditions.getSwitchCardNum2())){
				  sql.append("  AND t.RecSwitchCard>='"+conditions.getSwitchCardNum2()+"' ");
			  }           
			  if(!StringUtil.isTrimEmpty(conditions.getSwitchCardPercent())){
				  sql.append(" and (t.PassNumber=0 or (t.SwitchCard*100/t.PassNumber)>='"+conditions.getSwitchCardPercent()+"') ");
			  }    
			
		}
		//有收费无标识
		if(!StringUtil.isTrimEmpty(conditions.getChargesNotIndetify())){
			
			 if(!StringUtil.isTrimEmpty(conditions.getChargesNotIndetifyNum())){
				 sql.append(" and t.ChargesNotIndetify>='"+conditions.getChargesNotIndetifyNum()+"'");
			 }
			 if(!StringUtil.isTrimEmpty(conditions.getChargesNotIndetifyNum2())){
				 sql.append(" and t.RecChargesNotIndetify>='"+conditions.getChargesNotIndetifyNum2()+"'");
			 }
			 if(!StringUtil.isTrimEmpty(conditions.getChargesNotIndetifyPercent())){
				 sql.append(" and (t.PassNumber=0 or (t.ChargesNotIndetify*100/t.PassNumber)>='"+conditions.getChargesNotIndetifyPercent()+"')");
			 }
		}
		//标识点异常
		if(!StringUtil.isTrimEmpty(conditions.getIndentifyPointExcep())){
			
			 if(!StringUtil.isTrimEmpty(conditions.getIndentifyPointExcepNum())){
				 sql.append(" and t.IndentifyPointExcep>='"+conditions.getIndentifyPointExcepNum()+"'");
			 }
			 if(!StringUtil.isTrimEmpty(conditions.getIndentifyPointExcepNum2())){
				 sql.append(" and t.RecIndentifyPointExcep>='"+conditions.getIndentifyPointExcepNum2()+"'");
			 }
			 if(!StringUtil.isTrimEmpty(conditions.getIndentifyPointExcepPercent())){
				 sql.append(" and (t.PassNumber=0 or (t.IndentifyPointExcep*100/t.PassNumber)>='"+conditions.getIndentifyPointExcepPercent()+"')");
			 }
		}
	
		//ETC车牌不符
		if(!StringUtil.isTrimEmpty(conditions.getEtcPlateNotMeet())&&("1".equals(eds))){
			
			 if(!StringUtil.isTrimEmpty(conditions.getEtcPlateNotMeetNum())){
				 sql.append(" and t.ETCPlateNotMeet>='"+conditions.getEtcPlateNotMeetNum()+"'");
			 }
			 if(!StringUtil.isTrimEmpty(conditions.getEtcPlateNotMeetNum2())){
				 sql.append(" and t.RecETCPlateNotMeet>='"+conditions.getEtcPlateNotMeetNum2()+"'");
			 }
			 if(!StringUtil.isTrimEmpty(conditions.getEtcPlateNotMeetPercent())){
				 sql.append(" and (t.PassNumber=0 or (t.ETCPlateNotMeet*100/t.PassNumber)>='"+conditions.getEtcPlateNotMeetPercent()+"')");
			 }
		}
		
		//ETC车形不符
		if(!StringUtil.isTrimEmpty(conditions.getEtcModelsNotMeet())&&("1".equals(eds))){
			
			 if(!StringUtil.isTrimEmpty(conditions.getEtcModelsNotMeetNum())){
				 sql.append(" and t.ETCModelsNotMeet>='"+conditions.getEtcModelsNotMeetNum()+"'");
			 }
			 if(!StringUtil.isTrimEmpty(conditions.getEtcModelsNotMeetNum2())){
				 sql.append(" and t.RecETCModelsNotMeet>='"+conditions.getEtcModelsNotMeetNum2()+"'");
			 }
			 if(!StringUtil.isTrimEmpty(conditions.getEtcModelsNotMeetPercent())){
				 sql.append(" and (t.PassNumber=0 or (t.ETCModelsNotMeet*100/t.PassNumber)>='"+conditions.getEtcModelsNotMeetPercent()+"')");
			 }
		}
		
		//轴组异常
		if(!StringUtil.isTrimEmpty(conditions.getAxisGroupExcep())&&("2".equals(eds))){
			
			 if(!StringUtil.isTrimEmpty(conditions.getAxisGroupExcepNum())){
				 sql.append(" and t.AxisGroupExcep>='"+conditions.getAxisGroupExcepNum()+"'");
			 }
			 if(!StringUtil.isTrimEmpty(conditions.getAxisGroupExcepNum2())){
				 sql.append(" and t.RecAxisGroupExcep>='"+conditions.getAxisGroupExcepNum2()+"'");
			 }
			 if(!StringUtil.isTrimEmpty(conditions.getAxisGroupExcepPercent())){
				 sql.append(" and (t.PassNumber=0 or (t.AxisGroupExcep*100/t.PassNumber)>='"+conditions.getAxisGroupExcepPercent()+"')");
			 }
		}
		
		//历史性逃费
		if(!StringUtil.isTrimEmpty(conditions.getHistoryEscape())){
			if("1".equals(conditions.getHistoryEscapeNum())){
				sql.append(" and t.HistoryEscape='1'");
			}else if("2".equals(conditions.getHistoryEscapeNum())){
				sql.append(" and t.HistoryEscape='2'");
			}
			
		}
		
		//异常展示类型
		if(!StringUtil.isTrimEmpty(conditions.getExcepDisplayType())){
		if("1".equals(conditions.getExcepDisplayType())){
			sql.append(" and ExVehicleFlag='1'");
		}else if("2".equals(conditions.getExcepDisplayType())){
			sql.append(" and ExVehicleFlag='2'");
		}
		}
		
		//稽查状态
		if(!StringUtil.isTrimEmpty(conditions.getAuditStatus())){
			if("1".equals(conditions.getAuditStatus())){
				sql.append(" and AuditStatus='1'");
			}else if("2".equals(conditions.getAuditStatus())){
				 sql.append(" and AuditStatus='2'");
			}
			
		}
		//车牌
		if(!StringUtil.isTrimEmpty(conditions.getPlateNum())&&!StringUtil.isTrimEmpty(conditions.getCarNo())){
		
			sql.append(" and ExVehiclePlate  like'"+conditions.getPlateNum()+conditions.getCarNo().toUpperCase()+"%'");
		}
		
		   sql.append(" order by Exvehicleplate");
		 
	   List<Object[]> list=(List<Object[]>)findBySqlByPager(sql.toString(), pager);
		return list;
	
	}
	public List<Object[]> getAbnorCarTest(Conditions  conditions,Pager pager){
		 Calendar a=Calendar.getInstance();
		 int year=a.get(Calendar.YEAR);
		StringBuffer sql=new StringBuffer("SELECT * FROM(SELECT  (SELECT COUNT(*) FROM tb_LaneEnList_"+year+")as PassNumber, * FROM   AbnormalCar)t where 1=1");
		List<Object[]> list=(List<Object[]>)findBySql(sql.toString(), pager);
			return list;
	}
	
	
}