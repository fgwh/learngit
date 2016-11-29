package com.hgsoft.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;


public class FtpUtil {

	private static Log logger = LogFactory.getLog(FtpUtil.class);
			
	private String ip;
	private String username;
	private String password;
	private int port = -1;
	
	FTPClient ftpClient = null;      
	
	public FtpUtil(String ip,String username,String password,int port){
		this.ip = ip;
		this.username = username;
		this.password = password;
		this.port = port;
	}
	
	/**
	 * 连接FTP服务器
	 */
	public boolean connectServer(){
		ftpClient = new FTPClient();
		   try {      
			   ftpClient.connect(ip, port);   
			   ftpClient.login(username, password);   
	           logger.info("ftp连接成功");
	            return true;      
	        }catch (IOException e){      
	            e.printStackTrace();      
	            return false;      
	        }      
	}
	
	/**
	 * 断开与ftp服务器连接    
	 */
    public boolean closeServer(){      
    	if(ftpClient.isConnected()){   
    		try {   
	    		ftpClient.logout();   
	    		ftpClient.disconnect();   
	    		logger.info("ftp连接已关闭");
	    		return true;
    		} catch (IOException e) {   
	    		e.printStackTrace();   
	    		return false;
    		}   
    	}  
    	return true;
    } 
    
    public FTPFile[]  getFileList(String path){     
    	FTPFile[] files = null;
		try {
			ftpClient.enterLocalPassiveMode();
			ftpClient.changeWorkingDirectory(path);
			files = ftpClient.listFiles();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return files;      
    }    
    
    public void downLoadFile(OutputStream os,String fileName){
    	 try {
			ftpClient.retrieveFile(fileName, os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void main(String[] args){      
        FtpUtil ftp = new FtpUtil("10.173.235.180","Centerftp","Centerftp",6009);
        ftp.connectServer();    
        FTPFile[] list = ftp.getFileList("/ParkConsumption/UploadFiles");
        ftp.closeServer(); 
        if(list != null && list.length > 0){
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	for(FTPFile file : list){
        		System.out.println(file.getName()+"\t"+sdf.format(file.getTimestamp().getTime()));
        	}
        }
    }
}
