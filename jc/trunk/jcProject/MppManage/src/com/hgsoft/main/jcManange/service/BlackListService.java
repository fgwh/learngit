package com.hgsoft.main.jcManange.service;




import com.hgsoft.excel.ExcelModelUtil;
import com.hgsoft.excel.service.ExcelServcie;
import com.hgsoft.main.jcManange.dao.BlackListDao;
import com.hgsoft.main.jcManange.entity.Conditions;
import com.hgsoft.main.jcManange.entity.RoBlackList;
import com.hgsoft.main.jcManange.entity.RoGrayList;
import com.hgsoft.security.service.BaseService;
import com.hgsoft.util.Pager;
import com.hgsoft.util.StringUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Service
@SuppressWarnings({ "rawtypes" })
public class BlackListService extends BaseService<RoBlackList>{

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Resource
	public EvidenceService evidenceService;
	
	
	@Resource
	private ExcelServcie excelService;
	
	@Resource
	public void setDao(BlackListDao blackListDao) {
		super.setDao(blackListDao);
	}

	public BlackListDao getBlackListDao() {
		return (BlackListDao) this.getDao();
	}

	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> queryAllMessageList(Pager pager,Conditions conditions,String[] selectName,String sqlName,Class clazz) {		
		List<Object[]> list=new ArrayList();
		String vehFlag = conditions.getVehFlag();
		String startQueryDate = conditions.getStartQueryDate();
		String endQueryDate = conditions.getEndQueryDate();
		String interceptOption = conditions.getInterceptOption();
		if(StringUtil.isTrimEmpty(vehFlag)&&StringUtil.isTrimEmpty(startQueryDate)
			&&StringUtil.isTrimEmpty(endQueryDate)&&StringUtil.isTrimEmpty(interceptOption)){
			return list;
		}
		list= getDao().findByNamedQuery(pager, sqlName, selectName,conditions.toArray(selectName),clazz,"new");
		return list;
	}
	
	
	
	public boolean saveRoBlackList(RoBlackList entity){	
		
		
		
		if(StringUtil.isTrimEmpty(entity.getVehPlate())){
			entity.setVehPlate((entity.getPlateNum()+entity.getCarNo()).toUpperCase());
		}else{
			entity.setVehPlate(entity.getVehPlate().toUpperCase());
		}
		
		Date date = new Date();
		
		
        if(entity.getId()==null||"".equals(entity.getId())){
			
			entity.setId(UUID.randomUUID().toString().trim().replaceAll("-", ""));
						
		}
		
		if(entity.getFile()!=null){
			
			entity.setUploadTime(date);		
			
			String fileName=evidenceService.rename(entity.getFileFileName(), date);
			
			entity.setFileName(fileName);
			
			evidenceService.saveFile(fileName, entity.getFile(), 0, entity.getId(),date);
			
		}														
		entity.setJfStatus(0);
		
		entity.setSource(0);
		
		entity.setCreatime(date);
		

							
		getBlackListDao().saveOrUpdate(entity);
		

		
		return  true;
	}

     //车牌验证
	 public boolean plateValidate(Conditions condition){
		 List rs=getBlackListDao().plateValidate(condition);
		 if(rs.size()==0){
			 return true;
		 }else{
			 if(rs.get(0).toString().equals(condition.getId())){
				 return true;
			 }			 
	     }
		 return false;	
	 }
	 //费用追缴
	 public int recovered(RoBlackList entity){	
            int i=0;
			if(entity.getFile()!=null){
				Date uploadTime = new Date();
										
				String fileName=evidenceService.rename(entity.getFileFileName(), uploadTime);
								
				evidenceService.saveFile(fileName, entity.getFile(), 1, entity.getId(),uploadTime);	
				
				i= getBlackListDao().recovered(entity.getId());
				
			}	
		 return i;
		}
	 
	 
	//上传证据链 0代表证据链 1代表追缴证据
	 
	public int  upload(RoBlackList entity){
		int i=0;
		if(entity.getFile()!=null){
			Date uploadTime = new Date();
							
			String fileName=evidenceService.rename(entity.getFileFileName(), uploadTime);
						
			evidenceService.saveFile(fileName, entity.getFile(), 0, entity.getId(),uploadTime);
			
			i=getBlackListDao().upload(entity.getId(),fileName,sdf.format(uploadTime));			
		}	
		   return i;
	}
	
	
	public String importExcel(RoBlackList entity){
	    String message = "";
	    message = isNullOrIsExcel(entity==null?"":entity.getFileFileName());
        if ("".equals(message)) {
            List list = null;
            try {
                list = ExcelModelUtil.importExcel(entity.getFile().getPath(), "com.hgsoft.main.jcManange.entity.RoBlackList");
            } catch (Exception e) {
                e.printStackTrace();
            }
            String result = "";
            
            try {
                result = excelService.saveBean(list, "blackMD");
            } catch (Exception e) {
                message= "导入失败，请按要求填写格式";
            }
            if (StringUtils.isNotBlank(result)) {
                message=result.toString();
            } else if(list.size()!=0){
                message="导入成功！";
            }else{
            	message="导入失败！";
            }
        }		    	
    	return message;
    }	

 
    private String isNullOrIsExcel(String fileName) {
        String message="";
        if (StringUtils.isBlank(fileName)) {
            message= "导入失败，文件不能为空！";
        } else {
            String fileSuffixName = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
            if (!"xls".equals(fileSuffixName) && !"xlsx".equals(fileSuffixName)) {
                message="导入失败，不是excel文件";
            }

        }
        return message;
    }
}
