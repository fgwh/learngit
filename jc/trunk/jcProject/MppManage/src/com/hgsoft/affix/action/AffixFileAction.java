package com.hgsoft.affix.action;

import com.hgsoft.affix.entity.AffixFile;
import com.hgsoft.affix.service.AffixFileService;
import com.hgsoft.security.action.BaseAction;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Collection;

@Controller
@Scope("prototype")
public class AffixFileAction extends BaseAction {
	@Resource
	private AffixFileService affixFileService;
	private AffixFile affixFile;
	private File file;
	private String fileId;
	private String bizId;
	private String uploaddir;
	private String fileName;

	public String list(){
		return "list";
	}
	
	public Collection getFilesByBizId() {
		return affixFileService.getFilesByBizId(getBizId());
	}
	
	/***
	 * 指定业务数据id
	 * @return
	 */
	public String getBizId(){
		return bizId;
	}
	
	public Collection getSmallFilesByBizId(){
		return affixFileService.getFilesByBizId(getBizId()+"_s");
	}
	
	/**
	 * 指定上传的子路径，方便查找
	 **/
	public String getUploaddir(){
		return uploaddir;
	}
	
	
	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	/**
     * 如果是flash上传，则进行附件对象与业务的关联；如果是浏览器上传，则创建附件对象。
     */
	public String upload() {
		String savePath = ServletActionContext.getRequest().getRealPath("");
	      HttpServletResponse response = ServletActionContext.getResponse();
	      response.setCharacterEncoding("utf-8");
	      affixFile = new AffixFile();
	      affixFile.setBizId(bizId);
	      affixFile.setFileName(fileName);
	      affixFile.setUploadUser(getOperator().getName());
	      affixFile.setFileSize(file.length());
	      //获取扩展名  
	      affixFileService.saveUploadFile(affixFile, file, savePath);
	      //如果是图片，压缩另保存一份
	      affixFileService.saveUploadFileForCompressPic(affixFile, savePath);
	      
	      try {
			response.getWriter().print("OK:上传成功");
			} catch (IOException e) {
				e.printStackTrace();
			}  
	      return null; //这里不需要页面转向，所以返回空就可以了   
//		affixFileService.uploadFiles(files, filesFileName, fileTypes,
//				refId, getWebRoot(), getLoginUserId(), projId, module);
	}
	
	// 删除附件。
    public String deleteAffixByAjax() {
        affixFileService.deleteFile(fileId);
        return null;
    }
    
    // 下载附件。
    public String download() {
        affixFile = affixFileService.getFile(fileId);
        return SUCCESS;
    }
    
    /**
     * 返回下载流,用于文件下载时。
     * @return
     */
    public InputStream getInputStream(){
        InputStream is = null;
        if(affixFile != null){
            String path = ServletActionContext.getRequest().getRealPath("")+"/"+affixFile.getPath();
            File f = new File(path);
            if(f.exists()){
                try {
                    is = new FileInputStream(f);
                } catch (FileNotFoundException e) {
                	e.printStackTrace();
                }
            }
        }
        return is;
    }
	
	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public AffixFile getAffixFile() {
		return affixFile;
	}

	public void setAffixFile(AffixFile affixFile) {
		this.affixFile = affixFile;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	
	public void setUploaddir(String uploaddir) {
		this.uploaddir = uploaddir;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
