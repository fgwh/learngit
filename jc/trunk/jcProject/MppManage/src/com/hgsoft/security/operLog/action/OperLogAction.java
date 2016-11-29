package com.hgsoft.security.operLog.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ContextLoader;

import com.hgsoft.cxf.common.PropertiesUtil;
import com.hgsoft.job.JobLog;
import com.hgsoft.job.MobileOperLogJob;
import com.hgsoft.main.entity.QrtzLog;
import com.hgsoft.security.action.BaseAction;
import com.hgsoft.security.entity.Admin;
import com.hgsoft.security.entity.OperLog;
import com.hgsoft.security.operLog.annotation.Description;
import com.hgsoft.security.operLog.service.OperLogService;
import com.hgsoft.security.service.AdminService;
import com.hgsoft.util.DateUtil;
import com.hgsoft.util.StringUtil;

@Controller
@Scope("prototype")
public class OperLogAction extends BaseAction<OperLog>{
	@Resource
	private OperLogService operLogService;
	public OperLog getOperLog() {
		return entity;
	}

	public void setOperLog(OperLog operLog) {
		this.entity = operLog;
	}
	@Resource
	private AdminService adminService;
	 
	private String moduleName;
	private String name;
	private String adminName;
	private String startQueryDate;
	private String endQueryDate;
	private String fileName; // 图片后缀名
	private File fileUpload;//
	private String stationNo;//
	private String roadNo;//
	//private  OperLogService operLogService=(OperLogService)ContextLoader.getCurrentWebApplicationContext().getBean("operLogService");
	//private final static String FIELPATH = PropertiesUtil.getProperty("baseDir") + "/" + PropertiesUtil.getProperty("operLogPath")+"/";
	private static Logger logger = Logger.getLogger(OperLogAction.class);
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	public static final String FILE_SAVE_LOG = PropertiesUtil.getProperty("baseDir") + "/" + PropertiesUtil.getProperty("operLogPath");// 用于判断文件夹是否存在 保存图片的路径
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
	private Map map = new HashMap();
	/**
	 * 处理视频信息
	 * 
	 * @return
	 */
	public String logDataDeal() {
		//File target = new File(FILE_SAVE_DIR +"//"+dateFileName+"//"+ fileName);
		File target = null;
		String uploadDirName = sdf.format(new Date());
		//fileName = fileName.split(".")[fileName.split(".").length-1];
		target = new File(FILE_SAVE_LOG +"//"+uploadDirName+".txt");
		try{
			if (target.exists()) {
				fileUpload(fileUpload, target);
			} else {
				this.fileChannelCopy(fileUpload, target);
			}
		} catch(Exception e){
			logger.info("保存日志文件失败.......");
			target.delete();
			map.put("success", "saveFail");
			return "mobile";
		}
		
		try {
			addOperLog();
		} catch (Exception e) {
			logger.info("保存日志文件到数据库失败.......");
			map.put("success", "fail");
			return "mobile";
		}
		
		map.put("success", "success");
		return "mobile";
	}
	
	
	public void fileUpload(File fileUpload, File target) throws Exception{
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
		} finally {
			if (out != null) {
				out.close();
			}

			if (in != null) {
				in.close();
			}

		}
	}
	
	/**
	 * 文件复制
	 * 
	 * @param s
	 * @param t
	 * @throws Exception 
	 */
	public void fileChannelCopy(File s, File t) throws Exception {
		FileInputStream fi = null;

		FileOutputStream fo = null;

		FileChannel in = null;

		FileChannel out = null;

		try {

			fi = new FileInputStream(s);

			fo = new FileOutputStream(t);

			in = fi.getChannel();// 得到对应的文件通道

			out = fo.getChannel();// 得到对应的文件通道

			in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道

		} finally {
			fi.close();

			in.close();

			fo.close();

			out.close();

		}

	}
	
	
	/*
	 * 插入移动端日志
	 */
	@SuppressWarnings("unused")
	private void addOperLog() throws IOException, ParseException {
		List<OperLog> list = parseStringToEntity();
		for(OperLog operLog:list){
			operLogService.save(operLog);
		}
		if(list==null){
			logger.info("0移动端操作日志保存到本地数据库成功。");
		} else{
			logger.info(list.size() + "个移动端操作日志保存到本地数据库成功。");
		}	
	}

	/*
	 * 拼接对象
	 */
	private List<OperLog> parseStringToEntity() throws IOException, ParseException{
		String operateType = "5";
		List<OperLog> list = new ArrayList<OperLog>();
		File file = new File(FILE_SAVE_LOG);
		if(file.isDirectory()){
			File [] files = file.listFiles();
			for(File f:files){
				String str = FileUtils.readFileToString(f);
				String [] arr = str.split("\\n");
				for(String st:arr){
					Admin admin = new Admin();
					String [] ar = st.split("\\&&&&");
					OperLog operLog = new OperLog();
					//new String(ar[0], UTF-8);
//System.out.println("转码：：：：："+new String(ar[0].getBytes("GBK"`),"UTF-8"));
					operLog.setDetails(ar[0]);
					admin.setId(ar[1]);
					operLog.setAdmin(admin);
					operLog.setOperTime(format.parse((ar[2])));
					operLog.setIp(ar[3]);
					operLog.setOperType(operateType);
					if(ar.length>4){
						/*System.out.println("ar[4]：：：：："+ar[4]);*/
						operLog.setRemark(ar[4]);
					};
					operLog.setOrgId(operator.getOrg().getId().toString());
					//operLog.setRemark(new String(ar[4].getBytes("GBK"),"UTF-8"));
					list.add(operLog);
				}
				f.delete();
			}
		}
		return list;
	}
	/*
	 * 查询操作日志
	 */
	@Description(details="查询操作日志")
	public String list(){
		if(entity == null){
			entity =  new OperLog();
		}
		Calendar calendar = Calendar.getInstance();
		// 默认的时间范围为当天
		if (StringUtil.isTrimEmpty(entity.getStartQueryDate()) && StringUtil.isTrimEmpty(entity.getEndQueryDate())) {
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
		    
			entity.setStartQueryDate((String) DateUtil.fromatDate(calendar.getTime(), "yyyy-MM-dd HH:mm:ss"));
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			entity.setEndQueryDate((String)DateUtil.fromatDate(calendar.getTime(), "yyyy-MM-dd HH:mm:ss"));
		}
		list = operLogService.getListBySql(pager, entity, operator);
		return "list";
	}
	
	public Map getMap() {
		return map;
	}

	public String getStartQueryDate() {
		return startQueryDate;
	}

	public void setStartQueryDate(String startQueryDate) {
		this.startQueryDate = startQueryDate;
	}

	public String getEndQueryDate() {
		return endQueryDate;
	}

	public void setEndQueryDate(String endQueryDate) {
		this.endQueryDate = endQueryDate;
	}
	
	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public File getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}


	public String getStationNo() {
		return stationNo;
	}


	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}


	public String getRoadNo() {
		return roadNo;
	}


	public void setRoadNo(String roadNo) {
		this.roadNo = roadNo;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public List<Admin> getAdminList(){
		return adminService.findAll();
	}


	
	
}
