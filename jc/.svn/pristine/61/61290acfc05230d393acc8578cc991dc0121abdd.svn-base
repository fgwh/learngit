package com.hgsoft.main.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hgsoft.component.ProcessMode;
import com.hgsoft.component.mapper.CustomFileNameMapper;
import com.hgsoft.component.mapper.FileToClassMapper;
import com.hgsoft.component.model.DataHolder;
import com.hgsoft.component.parser.FileParser;
import com.hgsoft.component.parser.ObjectParser;
import com.hgsoft.component.transmission.FtpDownloader;
import com.hgsoft.component.transmission.FtpUploader;
import com.hgsoft.job.Common;
//import com.hgsoft.main.entity.FileTransDownLoadLog;
import com.hgsoft.parse.ConsumptionEntry;
import com.hgsoft.parse.ConsumptionParseToRecvService;

/**
 * com.hgsoft.component
 * 
 * @Project: QuartzTask
 * @Date: 2013/21/02
 * @Author: zhouzhaofeng
 * @Desc: 清分结算辅助类
 */
@Service("settlementService")
public class SettlementService {
	private static Log logger = LogFactory.getLog(SettlementService.class);
	private String server;
	private int port;
	private String user;
	private String password;
	private String ftpUploadDir; // 本地上传到ftp的目录
	private String ftpWorkingDir; // 需要下载文件的ftp目录
	private String localDownloadDir; // 从ftp下载到本地的目录
	private String localWorkingDir; // 本地从实体类生成的文件目录
	private String templatePath; // 解析流水文件的模板
	
	
	@Autowired
	private com.hgsoft.main.dao.JobDao myDao;

	public SettlementService() {
	}
	
	public String getLocalWorkingDir(int fileType) {
		loadConfig(fileType);
		return localWorkingDir;
	}
	
	public void loadConfig(int fileType) {
		logger.info("加载ftp配置文件.....");
		templatePath = Thread.currentThread().getContextClassLoader().getResource("ofile_template.xml").getFile().substring(1);
		InputStream in = null;
		// in = getClass().getResourceAsStream("ftp.properties");
		try {
			in = new BufferedInputStream(new FileInputStream(Thread
					.currentThread().getContextClassLoader()
					.getResource("ftp.properties").getFile()));
		} catch (FileNotFoundException e) {
			logger.error("ftp.properties不存在!");
			throw new RuntimeException("ftp.properties不存在!");
		}
		Properties prop = new Properties();
		try {
			prop.load(in); // Load
			server = prop.getProperty("ftp.server");
			port = Integer.valueOf(prop.getProperty("ftp.port"));
			user = prop.getProperty("ftp.user");
			password = prop.getProperty("ftp.password");
			
			if (Common.FILETYPE_PARKCONSUMPTION == fileType) {
				ftpWorkingDir = prop.getProperty("ftp.ParkConsumptionFileWorkingDir");
				ftpUploadDir = prop.getProperty("ftp.ParkConsumptionFileUploadDir");
				localDownloadDir = prop.getProperty("local.ParkConsumptionFileDownloadDir");
				localWorkingDir = prop.getProperty("local.ParkConsumptionFileWorkingDir");
			} else {
				ftpWorkingDir = prop.getProperty("ftp.BusinessFileWorkingDir");
				ftpUploadDir = prop.getProperty("ftp.BusinessFileUploadDir");
				localDownloadDir = prop.getProperty("local.BusinessFileDownloadDir");
				localWorkingDir = prop.getProperty("local.BusinessFileWorkingDir");
			}
			
			
			logger.debug("加载ftp配置文件成功！");
		} catch (IOException e) {
			logger.error("加载FTP配置文件I/O出错");
		}finally
		{
			if(in != null)
			{
				try {
					in.close();
					in = null;
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * 将数据源上传数据到ftp
	 * 
	 * @return
	 */
	public void uploadDataSourceToFtp(DataHolder[] holders,int fileType,String suffix) {
		loadConfig(fileType);
		
		try {
			File file = new File(localWorkingDir);
			
			// 如果文件夹不存在则创建
			if (!file.exists() && !file.isDirectory()) {
				boolean flag = file.mkdirs();
				if(flag)
				{
					logger.debug("创建本地目录：" + localWorkingDir);
				}
			}
		} catch (Exception e) {
		}
		
		FileParser parser = new FileParser(holders, new CustomFileNameMapper(
				localWorkingDir),suffix);
		File[] files = parser.parse(fileType);
		FtpUploader ftpUploader = new FtpUploader(server, port, user, password);
		ftpUploader.connect();
		ftpUploader.setFiles(files);
		try {
			ftpUploader.upload(ftpUploadDir);
		} catch (IOException e) {
			logger.error("上传到FTP过程中发生I/O错误");
		}
	}

	/**
	 * 从FTP下载文件
	 * 
	 * @param remoteFiles
	 *            需要下载的FTP文件名(不包含ftp的路径名，路径直接从properties文件获取)
	 */
	public File[] downloadFile(String[] remoteFiles,int fileType) {
		loadConfig(fileType);
		FtpDownloader ftpDownloader = new FtpDownloader(server, port, user,
				password);
		ftpDownloader.connect(); // 连接ftp
		if (Common.FILETYPE_PARKCONSUMPTION == fileType) {
			ftpDownloader.setMode(ProcessMode.REGEX); // 正则匹配
		} 
		
		// 根据properties拼路径
		for (int i = 0; i < remoteFiles.length; i++) {
			remoteFiles[i] = ftpWorkingDir + "/" + remoteFiles[i];
			logger.debug("拼接ftp远程文件名：" + remoteFiles[i]);
		}
	
		ftpDownloader.setRemoteFiles(remoteFiles);
		File[] files = ftpDownloader.download(localDownloadDir); // 先从FTP下载文件
		
		logger.info("下载文件到本地地址：" + localDownloadDir + "，准备转换Object");
		return files;
	}
	
	/*public void downloadConParkFileToTable(File file,String batchNo,int fileType,String suffix) throws Exception {
		
		logger.info("============文件入库-开始=============");
		
		ConsumptionParseToRecvService consumptionParseService = new ConsumptionParseToRecvService(templatePath);
		LinkedList<ConsumptionEntry> consumptionEntrys = null;
		int state = 0;
		String fileName = null;
		boolean isWrong = false;
		
		FileTransDownLoadLog fileTransDownLoadLog = new FileTransDownLoadLog();

		int index = 0;
		String absolutePath = file.getAbsolutePath();
		fileName = file.getName();
		try {
			consumptionEntrys = consumptionParseService.parse(absolutePath, null);
			
			for(ConsumptionEntry consumptionEntry : consumptionEntrys) {
				
				if (index == consumptionEntrys.size()-1 && !(consumptionEntry.getReturnMsg().equals("成功"))) {
											
				} else {
					++index;
					saveOrUpdate(consumptionEntry.getResult());
				}
				
			}
			
			logger.info(fileName + "入库完毕，" + "共更新"
					+ index + "条记录");
		} catch (Exception e) {
			isWrong = true;
			state = 1;
			logger.error(fileName + "入库失败，在第" + index + "行出错，共更新0条记录");
			e.printStackTrace();
		}
		
		fileTransDownLoadLog.setFileType(fileType);   ///1:流水文件
		fileTransDownLoadLog.setBatchNo(batchNo);
		fileTransDownLoadLog.setRecordNum(index);
		fileTransDownLoadLog.setHandleRecordNum(0);
		fileTransDownLoadLog.setHandleType(2);     //1:人工  2:自动
		fileTransDownLoadLog.setHandleFlag(1);
		fileTransDownLoadLog.setState(state);
		fileTransDownLoadLog.setCreateTime(new Date());
		fileTransDownLoadLog.setSystemCode("10001");
		fileTransDownLoadLog.setRemark(fileName);
		
		try {
			saveOrUpdate(fileTransDownLoadLog);
		} catch (Exception e) {
			logger.error(file + "下载日志记录保存失败，");
			e.printStackTrace();
		}
		
		if (isWrong) {
			throw new Exception(fileName+"下载失败，在第" + index + "行出错.");
		}
		FileUtils.copyFile(file, new File(absolutePath+".done"));
		
		if (file.exists()) {
			file.delete();
		}
		
		logger.info("============文件入库-结束=============");
	}*/

	
	/**
	 * 从FTP下载文件并转换到中间表
	 * 
	 * @param remoteFiles
	 *            需要下载的FTP文件名(不包含ftp的路径名，路径直接从properties文件获取)
	 * @return
	 */
	/*public void downloadFileToTable(String[] remoteFiles,String batchNo,int fileType,String suffix) {
		
		logger.info("============文件入库-开始=============");
		File[] files = downloadFile(remoteFiles,fileType);
		logger.info("下载文件到本地地址：" + localDownloadDir + "，准备转换Object");

		if (Common.FILETYPE_PARKCONSUMPTION == fileType) {
		//	downloadConParkFileToTable(files,batchNo,fileType,suffix);
		} else {
			ObjectParser objectParser = new ObjectParser(files,
					new FileToClassMapper(),suffix);
			DataHolder[] holders = objectParser.parse(fileType);
			// 校验业务数据
			//holders = downloadCheck(holders,batchNo,organization);
			if (holders != null && holders.length > 0) {
				DataHolder dataHolder = null;
				//每个文件入库
				for (int i = 0; i < holders.length; i++) {

					dataHolder = holders[i];
					try {
						for (Object o : dataHolder.getRecords()) {
							// myDao.saveOrUpdate(o);
							saveOrUpdate(o);
						}
						logger.info(dataHolder.getClassName() + "入库完毕，" + "共更新"
								+ dataHolder.getRecords().size() + "条记录");
						
					} catch (Exception e) {
						
						logger.info(dataHolder.getClassName() + "入库失败，" + "共更新0条记录");
						e.printStackTrace();
					}
					
	//				Integer fileType = 2;  //业务文件
					String remark = null;
					if (dataHolder.getClassName().equals("com.hgsoft.main.entity.UserRecv")) { // 客户信息
						fileType = FileTransDownLoadLog.FileType.TB_USER;
						remark = "TB_User.txt入库";
					} else if (dataHolder.getClassName().equals("com.hgsoft.main.entity.AccountRecv")) { // 客户信息
						fileType = FileTransDownLoadLog.FileType.TB_ACCOUNT;
						remark = "TB_Account.txt入库";
					} else if (dataHolder.getClassName().equals("com.hgsoft.main.entity.CardRecv")) { // 客户信息
						fileType = FileTransDownLoadLog.FileType.TB_CARD;
						remark = "TB_Card.txt入库";
					} else if (dataHolder.getClassName().equals("com.hgsoft.main.entity.ObuRecv")) { // 客户信息
						fileType = FileTransDownLoadLog.FileType.TB_OBU;
						remark = "TB_OBU.txt入库";
					} else if (dataHolder.getClassName().equals("com.hgsoft.main.entity.TradeListRecv")) { // 客户信息
						fileType = FileTransDownLoadLog.FileType.TB_TRADELIST;
						remark = "TB_TradeList.txt入库";
					} else if (dataHolder.getClassName().equals("com.hgsoft.main.entity.CardBlackListRecv")) { // 客户信息
						fileType = FileTransDownLoadLog.FileType.TB_CARDBLACKLIST;
						remark = "TB_CardBlackList.txt入库";
					}
					
					FileTransDownLoadLog fileTransDownLoadLog = new FileTransDownLoadLog();
					fileTransDownLoadLog.setFileType(fileType);
					fileTransDownLoadLog.setBatchNo(batchNo);
					fileTransDownLoadLog.setRecordNum(dataHolder.getRecords().size());
					fileTransDownLoadLog.setHandleRecordNum(0);
					fileTransDownLoadLog.setHandleType(2);
					fileTransDownLoadLog.setHandleFlag(1);
					fileTransDownLoadLog.setState(dataHolder.getResultCode());
					fileTransDownLoadLog.setCreateTime(new Date());
					fileTransDownLoadLog.setSystemCode("10001");
					fileTransDownLoadLog.setRemark(remark);
					
					try {
						saveOrUpdate(fileTransDownLoadLog);
					} catch (Exception e) {
						logger.error(dataHolder.getClassName() + "下载日志记录保存失败，");
						e.printStackTrace();
					}

				}
			} else {
				logger.error("中间表写数据失败，无法从文件转换到表数据");
			}
		}
		
		
		logger.info("============文件入库-结束=============");
	}*/

	private final void saveOrUpdate(Object o) throws Exception {
		myDao.saveOrUpdate(o);
	}
	
	/**
	 * 校验下载文件结果是否正确 写TB_File_TransDownLoad_Log 业务统计
	 */
	private DataHolder[] downloadCheck(DataHolder[] holders,String batchNo,int organization) {
		logger.info("============业务数据校验开始============");
		LinkedList<DataHolder> result = new LinkedList<DataHolder>(); // 通过验证的数据


		return null;
	}
	
	/**
	 * 将数据源上传数据到ftp
	 * 
	 * @return
	 */
	public boolean uploadParkBlackDataToFtp(DataHolder[] holders,int fileType,String suffix) {
		loadConfig(fileType);
		
		try {
			File file = new File(localWorkingDir);
			
			// 如果文件夹不存在则创建
			if (!file.exists() && !file.isDirectory()) {
				boolean flag = file.mkdirs();
				if(flag)
				{
					logger.debug("创建本地目录：" + localWorkingDir);
				}
			}
		} catch (Exception e) {
		}
		
		FileParser parser = new FileParser(holders, new CustomFileNameMapper(
				localWorkingDir),suffix);
		File[] files = parser.parseParkBlackData(fileType);
		FtpUploader ftpUploader = new FtpUploader(server, port, user, password);
		ftpUploader.connect();
		ftpUploader.setFiles(files);
		try {
			return ftpUploader.upload(ftpUploadDir);
		} catch (IOException e) {
			logger.error("上传到FTP过程中发生I/O错误");
			return false;
		}
	}
	
}
