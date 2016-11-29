package com.hgsoft.base.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hgsoft.base.service.UploadService;

@Controller
@Scope("prototype")
public class CheckPdfAction {
	private String url;
	@Resource
	private UploadService uploadService;
	private InputStream inputStream;
	
	public String displayPdf(){
    	try {
			inputStream = uploadService.getInputStream(url);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return "displayPdf";
    }
	
	public String checkPdf(){
		return "forward";
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
}
