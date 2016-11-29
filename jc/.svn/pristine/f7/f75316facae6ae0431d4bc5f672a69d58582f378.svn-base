package com.hgsoft.affix.service;

import com.ibm.icu.util.Calendar;
import com.hgsoft.affix.dao.AffixFileDao;
import com.hgsoft.affix.entity.AffixFile;
import com.hgsoft.affix.util.ImgCompress;
import com.hgsoft.security.service.BaseService;
import com.hgsoft.util.StrTool;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Date;

@Service
public class AffixFileService extends BaseService<AffixFile> {
	@Resource
    public void setDao(AffixFileDao dao) {
        super.setDao(dao);
    }
	@Resource
	private AffixFileDao affixFileDao;
	@Override
	public void saveOrUpdate(AffixFile AffixFile){
		if (StrTool.isBlankStr(AffixFile.getId())){
			save(AffixFile);
		} else {
			update(AffixFile);
		}
	}

	/***
	 * 根据业务id，获取对应的附件列表
	 * @param bizId
	 * @return
	 */
	public Collection getFilesByBizId(String bizId) {
		return affixFileDao.getFilesByBizId(bizId);
	}

	public AffixFile getFile(String fileId) {
		return affixFileDao.find(fileId);
	}

	public void deleteFileByBizId(String bizId) {
		affixFileDao.deleteFileByRefId(bizId);
	}

	public void deleteFile(String fileId) {
		affixFileDao.deleteById(fileId);
	}

	public void saveUploadFile(AffixFile affixFile, File file, String webPath) {
		String fileInputFileName = affixFile.getFileName();
		Calendar curr = Calendar.getInstance();
		affixFile.setUploadTime(new Date());		
		
		//获取当前时间
		//String thisTime = com.hgsoft.util.DateUtil.format(new java.util.Date(), com.hgsoft.util.DateUtil.PATTERN_STRING_TIME_2);
		String extName = "";//扩展名  
	    String newFileName= "";//新文件名
	    
		if(fileInputFileName.lastIndexOf(".")>=0){  
		   extName = fileInputFileName.substring(fileInputFileName.lastIndexOf("."));  
		}  
		newFileName = file.getName().substring(0, file.getName().indexOf("."))+extName;
		affixFile.setPath(AffixFile.SYSTEM_UPLOAD_DIR + File.separator+ curr.get(Calendar.YEAR) +File.separator+ curr.get(Calendar.MONTH) + File.separator+ newFileName);
		getDao().save(affixFile);
		//获取上传的类型
		//创建目录
		File fileTemp = new File(webPath+affixFile.getPath());
		if (!fileTemp.getParentFile().exists()){
			fileTemp.getParentFile().mkdirs();
		}
		file.renameTo(fileTemp);
	}
	
	/***
	 * 压缩图片，并以bizId+"_s"作为新的bizId，方便查询获取
	 * 调用此方法时，先判断是否为图片jpg/gif/bmp/png 只处理这几种类型，其他的直接什么都不做
	 * @param affixFile
	 * @param webPath
	 */
	public void saveUploadFileForCompressPic(AffixFile affixFile, String webPath){
		//获取当前文件实体
		String fileName = affixFile.getFileName().toLowerCase();
		if (!fileName.endsWith("jpg")
				&& !fileName.endsWith("gif")
				&& !fileName.endsWith("bmp")
				&& !fileName.endsWith("png")){
			return;
		}
		
		AffixFile affixFile_s = new AffixFile();
		try {
			BeanUtils.copyProperties(affixFile_s, affixFile);
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		}
	
		affixFile_s.setId(null);
		affixFile_s.setBizId(affixFile_s.getBizId()+"_s");
		
		//实体文件为同目录下文件名加_s
		String newPath = affixFile_s.getPath();
		newPath = newPath.substring(0, newPath.length()-4)+"_s."+newPath.substring(newPath.length()-3, newPath.length());
		affixFile_s.setPath(newPath);
		//保存
		save(affixFile_s);
		
		try {
			ImgCompress imgCompress = new ImgCompress(webPath+affixFile.getPath());
			imgCompress.resizeFix(500, 500, webPath+newPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
