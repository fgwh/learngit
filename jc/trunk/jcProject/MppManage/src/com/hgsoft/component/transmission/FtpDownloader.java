package com.hgsoft.component.transmission;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

import com.hgsoft.component.DownloadStatus;
import com.hgsoft.component.Downloader;
import com.hgsoft.component.ProcessMode;

/**
 * com.hgsoft.util
 * 
 * @Project: QuartzTask
 * @Date: 2013/16/26
 * @Author: zhouzhaofeng
 * @Desc:
 */
public class FtpDownloader extends RemoteClient implements Downloader {
	private static final int MAX_RECONNECT_CNT = 10; // 最大重连次数
	private static final int INTERVAL_TIMEOUT = 5000; // 重连间隔时间
	private static Logger logger = Logger.getLogger(FtpDownloader.class);
	private ProcessMode mode = ProcessMode.NORMAL; // 是否支持正则下载文件
	private FTPClient ftp;
	private String[] remoteFiles;
	private File[] locals; // 下载之后的本地文件
	List<File> localList = null;

	public FtpDownloader(String server, int port, String userName,
			String userPassword) {
		this.server = server;
		this.port = port;
		this.userName = userName;
		this.userPassword = userPassword;
	}

	public static void main(String[] args) {
		FtpDownloader ftpDownloader = new FtpDownloader("127.0.0.1", 21,
				"root", "123456");
		ftpDownloader.connect();
		ftpDownloader.setMode(ProcessMode.REGEX); // 正则匹配
		// ftpDownloader.download("^\\d{3}_20131025_TB_\\w*[.]txt$",
		// "E:/front/");
		ftpDownloader.download("^CardClient\\d*[.]rar$", "E:/front/");
		// ftpDownloader.download("CardClient3.rar","E:/front/");
	}

	/**
	 * 提取完整文件名，不包括路径
	 * 
	 * @param remoteFile
	 * @return
	 */
	private String getDownloadFileName(String remoteFile) {
		// 文件路径分隔符UNIX风格"/"
		int idx = remoteFile.lastIndexOf("/");
		if (idx != -1) {
			return remoteFile.substring(idx);
		} else {
			return remoteFile;
		}
	}

	public void setRemoteFiles(String[] remoteFiles) {
		logger.debug("设置下载的远程文件列表，共" + remoteFiles.length + "个文件");
		this.remoteFiles = remoteFiles;
	}

	/**
	 * 连接
	 */
	public void connect() {
		logger.info("正在进行FTP连接.......");
		ftp = new FTPClient(); // 持有FTP client实例
		ftp.addProtocolCommandListener(new PrintCommandListener(
				new PrintWriter(System.out))); // ftp操作命令写在控制台
		try {
			int reply; // 返回码，标志是否成功
			ftp.connect(server, port); // 连接
			ftp.login(userName, userPassword); // 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				logger.error("连接FTP的用户名或者密码错误......");
				this.disconnect();
			}
			ftp.changeWorkingDirectory(INIT_DIRECTORY); // 切换到ftp工作目录
			ftp.setControlEncoding("GBK");
			logger.info("===================登录成功====================");
			logger.info("Address:" + server + ":" + port);
			logger.info("Username:" + userName);
			logger.info("WorkingDirectory:" + INIT_DIRECTORY);
			logger.info("ControlEncoding:" + ftp.getControlEncoding());
			logger.info("===============================================");
		} catch (ConnectException connectException) {
			logger.debug("准备重连");
			try {
				logger.debug("重连等待时间：" + INTERVAL_TIMEOUT + "ms");
				Thread.sleep(INTERVAL_TIMEOUT);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			connect();
		} catch (IOException e) {
			logger.error("FTP连接发生I/O错误");
		}
	}

	/**
	 * 把remoteFiles的文件下载到localPath下
	 * 
	 * @param localPath
	 *            下载到本地的目录
	 * @return
	 */
	public File[] download(String localPath) {
		try {
			File file = new File(localPath);
			
			// 如果文件夹不存在则创建
			if (!file.exists() && !file.isDirectory()) {
				boolean flag = file.mkdirs();
				if(flag)
				{
					logger.debug("创建本地目录：" + localPath);
				}
				
			}
		} catch (Exception e) {
		}
		
		logger.debug("下载文件到本地目录：" + localPath);
		localList = new ArrayList<File>();
		
		if (remoteFiles != null && remoteFiles.length > 0) {
			logger.debug("需要下载的远程文件个数：" + remoteFiles.length);
			locals = new File[remoteFiles.length]; // 本地文件
			
			int size = 0;
			for (int i = 0; i < remoteFiles.length; i++) {
				String remoteFile = remoteFiles[i];
				DownloadStatus status = download(remoteFile, localPath); // 下载远程文件到本地目录
				logger.info(remoteFile + "下载结果：" + status.name());
				// 过滤下载失败的
				if (status == DownloadStatus.Download_From_Break_Success
						|| status == DownloadStatus.Download_New_Success) {
					locals[size++] = new File(localPath + "/"
							+ getDownloadFileName(remoteFile)); // 返回下载回来的文件，需要拼接
				}
			}
			return localList.toArray(new File[localList.size()]);
	//		return Arrays.copyOf(locals, size);
		} else {
			logger.warn("找不到需要下载的文件列表参数");
		}
		return null;
	}
	
	

	/**
	 * 下载文件
	 * 
	 * @param remote
	 *            远程文件名或表达式
	 * @param local
	 *            下载的本地文件或者目录
	 * @return
	 */
	@Override
	public DownloadStatus download(String remote, String local) {
		ftp.enterLocalPassiveMode();
		try {
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
		} catch (IOException e) {
			logger.error("设置以二进制传输模式失败...", e);
		}

		String ftpWorkingDir = remote.substring(0, remote.lastIndexOf("/")+1);
		String regexStr = remote.substring(remote.lastIndexOf("/")+1);
		if (ftp.isConnected()) {
			DownloadStatus result = null;
			OutputStream out = null;
			InputStream in = null;
			try {
				// 检查远程文件是否存在
				FTPFile[] files = null;
				if (mode.equals(ProcessMode.REGEX)) { // 正则匹配
					logger.info("正则匹配下载:" + remote);
					RegexNameFileFilter regexNameFileFilter = new RegexNameFileFilter(regexStr);

					files = ftp.listFiles(ftpWorkingDir, regexNameFileFilter);

				} else if (mode.equals(ProcessMode.NORMAL)) { // 普通匹配
					logger.info("普通下载:" + remote);
					files = ftp.listFiles(remote);
				}
				if (files.length < 1) {
					logger.warn("远程文件不存在");
					/**
					 * 如果争议流水文件找不到，抛出异常
					 */
//					if(remote.endsWith("TB_DisputeData.txt")){
//						logger.info("争议流水文件不存在,服务跳出本次执行");
//						throw new RuntimeException("争议流水文件不存在！");
//					}
					
					return DownloadStatus.Remote_File_Noexist;
				}

				/**
				 * 匹配的文件
				 */
				logger.debug(remote + "匹配的文件数量：" + files.length);
				
				for (int i = 0; i < files.length; i++) {
					FTPFile remoteFile = files[i]; // 远程文件
					long lRemoteSize = remoteFile.getSize(); // 远程文件大小
					long remoteModified = remoteFile.getTimestamp().getTimeInMillis();
					File localFile = new File(local + "/"
							+ remoteFile.getName()); // 本地文件
					if (localFile.exists()) { // 文件存在？
						long localSize = localFile.length(); // 存在的文件大小
						long localModified = localFile.lastModified();
						
						// 判断本地文件大小是否大于远程文件大小
						if (localSize >= lRemoteSize && localModified>=remoteModified) {
							// 本地文件大于或者等于远程文件，认为下载完成了，跳过下载
							logger.info("文件已经存在，跳过该文件:" + localFile.getName());
							result = DownloadStatus.Download_From_Break_Success;
						} else {
							if (localModified<remoteModified) {
								localFile.delete();
								localSize = 0L; 
								out = new FileOutputStream(localFile);
							} else {
								out = new FileOutputStream(localFile, true);
							}
							
							// 进行断点续传，并记录状态
							ftp.setRestartOffset(localSize); // 直接重置下载点，已经下载的不做完整性校验了
							in = ftp.retrieveFileStream(ftpWorkingDir+remoteFile.getName());
							byte[] bytes = new byte[1024];

							int c;
							while ((c = in.read(bytes)) != -1) {
								out.write(bytes, 0, c);
								localSize += c;
								/*
								 * long nowProcess = localSize / step; if
								 * (nowProcess > process) { process = nowProcess; if
								 * (process % 10 == 0) logger.debug("下载进度：" +
								 * process); }
								 */
							}
							boolean isDo = ftp.completePendingCommand();
							if (isDo) {
								logger.debug(localFile.getName() + "续传完毕!");
								result = DownloadStatus.Download_From_Break_Success;
							} else {
								logger.debug(localFile.getName() + "续传失败!");
								result = DownloadStatus.Download_From_Break_Failed;
							}
						}
						
					} else {

						out = new FileOutputStream(localFile);
						in = ftp.retrieveFileStream(ftpWorkingDir+remoteFile.getName());
						byte[] bytes = new byte[1024];

						int c;
						while ((c = in.read(bytes)) != -1) {
							out.write(bytes, 0, c);
						}
						boolean upNewStatus = ftp.completePendingCommand();
						if (upNewStatus) {
							logger.info(localFile.getName() + "传输完毕!");
							result = DownloadStatus.Download_New_Success;
						} else {
							logger.info(localFile.getName() + "传输失败!");
							result = DownloadStatus.Download_New_Failed;
						}
					}
					
					if (in != null) {
						in.close();
						in = null;
					}
					if (out != null) {
						out.close();
						out = null;
					}
					
					logger.info(remoteFile.getName() + "下载结果：" + result.name());
					// 过滤下载失败的
					if (result == DownloadStatus.Download_From_Break_Success
							|| result == DownloadStatus.Download_New_Success) {
						
						
						boolean isRename = ftp.rename(ftpWorkingDir+remoteFile.getName(), ftpWorkingDir+remoteFile.getName()+".done");
						if (!isRename) {
							logger.error("文件重命名失败，该文件不处理，需检查！");
						} else {
							localList.add(new File(local + "/"+ remoteFile.getName()));  // 返回下载回来的文件，需要拼接
						}

					}
				}
				return result;
			} catch (FTPConnectionClosedException closeException) {
				closeException.printStackTrace();
				logger.warn("FTP断开");
				disconnect();
				connect();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (in != null) {
						in.close();
					}
					if (out != null) {
						out.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			return DownloadStatus.Download_New_Failed; // Disconnect.
		}
		return DownloadStatus.Download_New_Failed;
	}

	public boolean reconnect() {
		boolean flag = true;
		if (!ftp.isConnected() || ftp == null) {
			for (int i = 0; i < MAX_RECONNECT_CNT; i++) {
				try {
					logger.info("****FTP第" + (i + 1) + "次连接****");
					Thread.sleep(INTERVAL_TIMEOUT);
					connect();
				} catch (Exception e) {
				}
				if (ftp.isConnected()) {
					flag = true;
				} else {
					flag = false;
				}
			}
		}
		return flag;
	}

	public void disconnect() {
		logger.info("进入FTP连接关闭连接方法...");
		// 判断客户端是否连接上FTP
		if (ftp.isConnected()) {
			// 如果连接上FTP，关闭FTP连接
			try {
				logger.info("关闭ftp连接......");
				ftp.disconnect();
			} catch (IOException e) {
				logger.error("关闭ftp连接出现异常......", e);
			}
		}
	}

	public void setMode(ProcessMode mode) {
		this.mode = mode;
	}
}
