package com.hgsoft.main.jcManange.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hgsoft.cxf.common.PropertiesUtil;
import com.opensymphony.xwork2.ActionSupport;


@Controller
@Scope("prototype")
@SuppressWarnings("unchecked")
public class DownloadAction extends ActionSupport implements
		ServletContextAware {
	private static final long serialVersionUID = 1L;
	private static String FILE_NAME = PropertiesUtil.getProperty("baseDir") + "/" + PropertiesUtil.getProperty("FILE_SAVE_DIR");
	private ServletContext context;

	private String filename;

	private String mimeType;
	private String fileDate;

	private InputStream inStream ;

	@Override
	public String execute() throws Exception {
		File file=new File(FILE_NAME +"//"+fileDate+"//"+ filename);
		if(!file.exists()){
			return "error";
		}
		mimeType = context.getMimeType(filename);
		return "success";
	}

	public InputStream getInStream() throws FileNotFoundException {
		inStream = new FileInputStream(FILE_NAME +"//"+fileDate+"//"+ filename);		
		return inStream;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setFilename(String filename) {
		try {
			this.filename = new String(filename.getBytes("ISO8859-1"), "GBK");
		} catch (UnsupportedEncodingException e) {
		}
	}

	public String getFilename() {
		try {
			return new String(filename.getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			return this.filename;
		}
	}
	
	@Override
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public String getFileDate() {
		return fileDate;
	}

	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}
	
	

}
