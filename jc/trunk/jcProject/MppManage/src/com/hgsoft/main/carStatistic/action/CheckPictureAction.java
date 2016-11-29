package com.hgsoft.main.carStatistic.action;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hgsoft.cxf.common.PropertiesUtil;
import com.hgsoft.security.action.BaseAction;

@Controller
@Scope("prototype")
public class CheckPictureAction extends BaseAction {

	/**
	 * 获取图片流
	 * 
	 * @return
	 */
	public CheckPictureAction() {

	}

	private String imgUrl="";
	private String fileDate = "";
	private final String savePath = PropertiesUtil.getProperty("baseDir") + "/" + PropertiesUtil.getProperty("FILE_SAVE_DIR");

	public String getDocumentImg() {
		ByteArrayOutputStream bos=null;
		InputStream input =null;
		// 判断存储图片的文件是否存在 不存在 则创建文件夹
		File file = new File(savePath);
System.out.println("savepath:      "+savePath);
		if (!file.exists() && !file.isDirectory()) {
			file.mkdir();// 不存在则创建文件夹
		}
		
		if("".equals(imgUrl)||null==imgUrl||"undefined".equals(imgUrl)){
			return "displayImgs";
		}
		String realUrl = savePath +"/"+fileDate +"/"+ imgUrl;
		// imgUrl
		try {
			bos = new ByteArrayOutputStream();
			input = new BufferedInputStream(new FileInputStream(
					realUrl));
			byte[] bt = new byte[1024];
			while (input.read(bt) > 0) {
				bos.write(bt);
			}
			this.inputStream = new ByteArrayInputStream(bos.toByteArray());
			bos.close();
			input.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return "displayImgs";
	}

	private ByteArrayInputStream inputStream;

	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}

	@JSON(serialize = false)
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	@JSON(serialize = false)
	public String getFileDate() {
		return fileDate;
	}

	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}

}
