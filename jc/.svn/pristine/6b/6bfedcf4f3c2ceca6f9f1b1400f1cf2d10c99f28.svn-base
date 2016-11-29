package com.hgsoft.main.carStatistic.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Date;
import java.util.UUID;
import java.util.zip.InflaterInputStream;

import javax.annotation.Resource;

import com.hgsoft.cxf.common.PropertiesUtil;
import com.hgsoft.main.carStatistic.entity.CarStatistic;
import com.hgsoft.util.DateUtil;
import com.hgsoft.util.LocalDBUtils;

public class SocketThread extends Thread {
	private static final String fileDir = PropertiesUtil.getProperty("baseDir")+"//"+PropertiesUtil.getProperty("FILE_SAVE_DIR");
	private ServerSocket serverSocket;
	private CarStatistic carStatistic;
	private int status;
	private String laneSeriaNo;
	private final static int BUFFER = 50000;
	private Connection conn = null;
	private Statement pstmt = null;
	private static Socket socket = null;
	
	//创建socketThread线程
	public SocketThread(CarStatistic entity, int status, ServerSocket serverSocket) {
		this.carStatistic = entity;
		this.status = status;
		this.serverSocket = serverSocket;
		
		if(status==0){
			laneSeriaNo = carStatistic.getExSerialNo();
		} else {
			laneSeriaNo = carStatistic.getEnSerialNo();
		}
	}
	
	
	public void run() {
		OutputStream out = null;
		StringBuffer strXML = new StringBuffer();
		InputStreamReader fReader = null;
		boolean flag = true;
		int fileLength = 0;
		
		while (!this.isInterrupted()) {
			try {
System.out.println("socket start accept");
				if(socket==null || socket.isClosed()){
					socket = serverSocket.accept();
				}
				
				try{
					if (null != socket && !socket.isClosed()) {
						fReader = new InputStreamReader(socket.getInputStream(), "ISO8859-1");
					
						while (true) { 
							int length = 0;
							char[] temp = new char[BUFFER];
							length = fReader.read(temp, 0, BUFFER);
					
							strXML.append(new String(temp, 0, length));
				
							if(strXML.length()>0 && flag && strXML.indexOf(laneSeriaNo+".jpg")>=0 ){
System.out.println("strXML:----"+strXML.length()+"-----"+strXML.toString().getBytes().length);
								flag = false;
								fileLength = Integer.parseInt(strXML.toString().split(laneSeriaNo+".jpg\t")[1].split("x")[0]);
							}
							
							if(strXML.toString().split("\t"+fileLength).length>=2 && strXML.toString().split("\t"+fileLength)[1].length() == fileLength){
								break;
							}
						}
						
						out = socket.getOutputStream();
						out.write("1".getBytes());
						out.flush();
						
						//解析文件
						decompress(strXML, fileLength);
						
						//设置laneExListImg实体对象
						setLaneExListImg(fileLength);
					}
				} catch(Exception e){
					e.printStackTrace();
					System.out.println("paochuException.....");
					break;
				} finally{
					closeStream(fReader, out);
					
					//this.interrupt();
					
					try {
						if (null != serverSocket && !serverSocket.isClosed()) {
							System.out.println("关掉了serverSocket  啊，什么情况");
							serverSocket.close();
							break;
						}
					} catch (IOException e) {
						System.out.println("closeSocketServer.......");
						e.printStackTrace();
					}
				}

			} catch (Exception e) {
				System.out.println("yichang.......");
				e.printStackTrace();
				
				try {
					if (null != serverSocket && !serverSocket.isClosed()) {
						System.out.println("关掉了serverSocket  啊，什么情况");
						serverSocket.close();
						break;
					}
				} catch (IOException e1) {
					System.out.println("closeSocketServer.......");
					e.printStackTrace();
				}
				
				break;
			} finally{
				closeSocket(socket);
			}
		}
	}
	
	/**
	 *	解析文件流
	 * @param strXML
	 * @param fileLength
	 */
	public void decompress(StringBuffer strXML, int fileLength) {  
		System.out.println("strXMLde长度:-------"+strXML.toString().getBytes().length);
		String[] arr = strXML.toString().split("\t"+fileLength);
		FileOutputStream os = null;
		InflaterInputStream gis = null;
		try{
			File file = createDir();
			os = new FileOutputStream(file);
			gis = new InflaterInputStream(new ByteArrayInputStream(arr[1].getBytes("ISO8859-1")));    
		    int count;    
		    byte data[] = new byte[BUFFER];
		    
		    
		    while ((count = gis.read(data, 0, BUFFER)) != -1) {  
		        os.write(data, 0, count);    
		    }

		} catch(Exception e){
			e.printStackTrace();
		} finally{
			if(gis!=null){
				try {
					gis.close();
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
			
			if(os!=null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}  
	}
	
	
	//通过时间创建文件
	public File createDir() throws IOException{
		String dirName = fileDir+"//"+carStatistic.getSquadDate();
		File dirFile = new File(dirName);
		
		if(!dirFile.exists() || dirFile.isDirectory()){
			dirFile.mkdir();
		} 
		
		
		String fileName = dirName+"//"+laneSeriaNo+".jpg";
		File newFile = new File(fileName);
		newFile.createNewFile();
		
		return newFile;
	}
	
	
	/**
	 * 关闭流
	 * @param fReader
	 * @param out
	 */
	public void closeStream(InputStreamReader fReader, OutputStream out){
		if(fReader!=null){
			try {
				fReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(out!=null){
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 关闭socket客户端
	 * @param socket
	 */
	public void closeSocket(Socket socket){
		
		if(socket!=null){
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void setLaneExListImg(int fileLength){
		try{
			conn = LocalDBUtils.getConnection();
			pstmt = conn.createStatement();
			
			StringBuilder strBuilder = new StringBuilder("");
			
			strBuilder.append("INSERT INTO tb_LaneExList_Img([id],[exListImgeType],[exListImageName],[exListImageSize],[exListFileType],[exListImageDate],[pid],[squadDate]) VALUES(")
					.append("'"+UUID.randomUUID().toString()+"',").append(status+",").append("'"+laneSeriaNo+".jpg',").append(fileLength+",")
					.append("'jpg',").append("'"+DateUtil.format(new Date(), DateUtil.PATTERN_STRING_TIME)+"',")
					.append("'"+laneSeriaNo+"',").append("'"+carStatistic.getSquadDate()+"')");
			
			
			pstmt.executeUpdate(strBuilder.toString());
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			LocalDBUtils.release(conn, pstmt, null);
		}
	}
}