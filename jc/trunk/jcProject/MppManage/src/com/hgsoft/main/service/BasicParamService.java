package com.hgsoft.main.service;

import com.hgsoft.main.dao.BasicParamDao;
import com.hgsoft.main.entity.BasicParam;
import com.hgsoft.security.service.BaseService;
import com.hgsoft.util.Pager;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/7/1.
 */

@Service
@SuppressWarnings({"unchecked" })
public class BasicParamService extends BaseService<BasicParam> {
	
	private static final String LOCATION_UPLOAD_PARAM = "locaUpload";
	
    @Resource
    public void setBaseBridgeDao(BasicParamDao dao) {
        this.setDao(dao);
    }

    public List<Object> queryBasicParamToKeyAndValue(Object... obj) {
        String sql = "select b.paramVal, b.paramName from TB_Basic_Param b where b.paramFlag = ? and b.paramCode = ?";
        /*if (obj != null && obj.length == 2) {
            if (obj[0] != null && !obj[0].toString().trim().equals("")) {
                sql.append(" and b.paramFlag = ").append(Integer.parseInt(obj[0].toString().trim()));
            }
            if (obj[1] != null && !obj[1].toString().trim().equals("")) {
                sql.append(" and b.paramCode = '").append(obj[1].toString().trim()).append("'");
            }
        }*/
        return this.getDao().queryBySQL(sql, obj);
    }
    
    public List<Object[]> queryBasicParamList(Pager pager, Object... obj){
    	StringBuffer sql = new StringBuffer(" from TB_Basic_Param b where 1 = 1");
		if(obj != null && obj.length == 2){
			if(obj[0] != null && !obj[0].toString().trim().equals("")){
				sql.append(" and b.paramName like '%").append(obj[0].toString().trim()).append("%'");
			}
			if(obj[1] != null && !obj[1].toString().trim().equals("")){
				sql.append(" and b.status = ").append(Integer.parseInt(obj[1].toString().trim()));
			}			
		}
		int totalSize = this.getDao().executeCountQuery(sql.toString());
		pager.setTotalSize(totalSize);
		
    	String execSql="select b.paramName,\n"+
    	               "b.paramVal,\n"+
    			       "(case b.status when 0 then '启用' when -1 then '停用' end)status,\n"+
    	               "b.paramCode,\n"+
    			       "b.id"
    			       +sql.toString()+"order by b.paramName";
    	
    	
		List<Object[]> list=(List<Object[]>)this.getDao().findBySql(execSql, pager);
		return list;
    }  
    
    
    /*****
     * 获得移动端位置上传间隔
     * @return String 上传间隔参数
     */
    public String  getGeoInfo(){
    	String execSql="select b.paramVal from TB_Basic_Param b where 1=1 and paramCode = '"+ LOCATION_UPLOAD_PARAM + "'";
    	
    	List list = this.getDao().findBySql(execSql, null);
    	if(!list.isEmpty()) {
    		return (String) list.get(0);
    	}
    	
    	return "5";
    }

    public List<BasicParam> getParamsByFlagAndType(int flag, int valType) {
		String hql = "from BasicParam b  where b.paramFlag="+flag+ " and b.paramValType="+valType+" and status=0";
		return (List<BasicParam>) this.getDao().findByHql(hql, null);
	}

    public List<?> queryBasicParamListByName(String paramName) {
		String hql = "from BasicParam b  where b.paramName='"+paramName+"'";
		return getDao().findByHql(hql, null);
	}
    
}
