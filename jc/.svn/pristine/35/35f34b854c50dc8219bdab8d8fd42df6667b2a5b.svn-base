package com.hgsoft.main.jcManange.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hgsoft.cxf.common.PropertiesUtil;
import com.hgsoft.main.jcManange.dao.BlackListDao;
import com.hgsoft.main.jcManange.dao.EvidenceDao;
import com.hgsoft.main.jcManange.entity.Evidence;
import com.hgsoft.security.service.BaseService;
import com.ibm.icu.text.SimpleDateFormat;
@Service
@SuppressWarnings({ "rawtypes" })
public class EvidenceService extends BaseService<Evidence>{
private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public static final String FILE_SAVE_DIR = PropertiesUtil.getProperty("baseDir") + "/" + PropertiesUtil.getProperty("FILE_SAVE_DIR");//  保存文件的路径
	
	
	
	
	
	@Resource
	public void setDao(EvidenceDao evidenceDao) {
		super.setDao(evidenceDao);
	}

	public EvidenceDao getEvidenceDao() {
		return (EvidenceDao) this.getDao();
	}
	
	/**
	 * 上传文件
	 * 
	 * @param s
	 * @param t
	 */
	public  boolean upload(String fileName,File fileUpload,int type,String pid){
		Date uploadTime= new Date();
		
		saveFile(fileName,fileUpload,type,pid,uploadTime);
		
		return true;
	}
	
	
	/**
	 * 上传文件
	 * 
	 * @param s
	 * @param t
	 */
	public void saveFile(String fileName,File fileUpload,int type,String pid,Date uploadTime){

		File target = null;
	
		String uploadDirName = sdf.format(uploadTime);
		target = new File(FILE_SAVE_DIR +"//"+uploadDirName+"//"+ fileName);
		if(!target.getParentFile().exists()){
			target.getParentFile().mkdirs();
		}
	
			FileInputStream in = null;
			FileOutputStream out = null;
			try {
				in = new FileInputStream(fileUpload);
				out = new FileOutputStream(target, true);// 以追加的方式

				byte[] buff = new byte[1024];
				int length;
				while ((length = in.read(buff)) != -1) {
					out.write(buff, 0, length);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (out != null) {
						out.close();
					}

					if (in != null) {
						in.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		
		try{
			Evidence evidence= new Evidence();
			evidence.setId(UUID.randomUUID().toString());
			evidence.setPid(pid);
			evidence.setType(type);
			evidence.setFileURL(fileName);
			getEvidenceDao().save(evidence);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	
	public String rename(String fileName,Date uploadTime) {  
	      String body="";  
	      String ext="";  
	      int pot=fileName.lastIndexOf(".");  
	      if(pot!=-1){  
	          body= uploadTime.getTime() +"";  
	          ext=fileName.substring(pot);  
	      }else{  
	          body=uploadTime.getTime()+"";  
	          ext="";  
	      }  
	      String newName=body+ext;    
	      return newName;  
	  
	    }  
}
