package com.hgsoft.main.carStatistic.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.zip.CRC32;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hgsoft.cxf.common.PropertiesUtil;
import com.hgsoft.main.carStatistic.dao.CarStatisticDao;
import com.hgsoft.main.carStatistic.entity.CarStatistic;
import com.hgsoft.main.carStatistic.entity.LaneExListImg;
import com.hgsoft.security.entity.Admin;
import com.hgsoft.security.service.BaseService;
import com.hgsoft.util.DateUtil;
import com.hgsoft.util.Pager;

import sun.misc.BASE64Decoder;

@Service
@Transactional
public class CarStatisticService extends BaseService<CarStatistic> {
	private static Long startMath = new Long(10000000);
	private static int m_iSeqNo = 0;
	private static final String roadCode = PropertiesUtil.getProperty("ROAD_CODE");
	private static final String provinceIp = PropertiesUtil.getProperty("PROVINCE_IP");
	private static final String provincePort = PropertiesUtil.getProperty("PROVINCE_PORT");
	private static final String fileDir = PropertiesUtil.getProperty("baseDir")+"//"+PropertiesUtil.getProperty("FILE_SAVE_DIR");
	private static final String localPort = PropertiesUtil.getProperty("LOCAL_PORT");
	private static final String localPortTwo = PropertiesUtil.getProperty("LOCAL_PORTTWO");
	private static final String stationServerIp = PropertiesUtil.getProperty("STATION_SERVER_IP");
	private static final String stationServerPort = PropertiesUtil.getProperty("STATION_SERVER_PORT");
	
	// socket server 线程
	private SocketThread socketThread;
	//
	private ServerSocket serverSocket;
	
	@Resource
	private LaneExListImgService laneExListImgService;

	@Resource
	public void setDao(CarStatisticDao carStatisticDao) {
		super.setDao(carStatisticDao);
	}

	public CarStatisticDao getCarStatisticDao() {
		return (CarStatisticDao) this.getDao();
	}

	/**
	 * 根据车辆信息条件查询所有的收费流水信息
	 * 
	 * @param entity
	 * @param pager
	 * @return
	 */
	public List<Object[]> queryAllCarStatisticMsg(CarStatistic entity, Pager pager, Admin operator) {
		return this.getCarStatisticDao().findByCarStatisticEntity(pager, entity, operator);
	}

	public List<Object[]> queryAllDealStatusMsg() {
		return this.getCarStatisticDao().findByDealStatusEntity();
	}
	
	//两个出口图片的对比
	public List<LaneExListImg> queryAllExitPicture(CarStatistic entity){
		List<LaneExListImg> imageList = new ArrayList<LaneExListImg>();
		// 首先获取出口图片One
		List<LaneExListImg> exImageListOne = laneExListImgService.queryLaneExListImgByExSeria(entity.getExSerialNo());
		
		LaneExListImg exImage = selectInputOrOutput(exImageListOne);
		//判定条件为：exImage为空   车道类型不是etc的车道才使用站级服务器去取图片
		if (null == exImage && !entity.getExLaneType().equals("3") && !entity.getExLaneType().equals("4") && !entity.getExLaneType().equals("6") && !entity.getExLaneType().equals("8")) {
			entity = returnQueryEntity(entity.getExSerialNo(), entity.getExTime(), entity);
			// 图片需要获取到本地，直接使用站级服务器获取
			imageList.add(connectStationServerTwo(entity, 0));
		} else {
			imageList.add(exImage);
		}
		
		
		// 首先获取出口图片Two
		List<LaneExListImg> exImageListTwo = laneExListImgService.queryLaneExListImgByExSeria(entity.getExSerialNoTwo());
		
		LaneExListImg exImageTwo = selectInputOrOutput(exImageListTwo);
		//判定条件为：exImage为空   车道类型不是etc的车道才使用站级服务器去取图片
		if (null == exImage && !entity.getExLaneType().equals("3") && !entity.getExLaneType().equals("4") && !entity.getExLaneType().equals("6") && !entity.getExLaneType().equals("8")) {
			entity = returnQueryEntity(entity.getExSerialNoTwo(), entity.getExTimeTwo(), entity);
			// 图片需要获取到本地，直接使用站级服务器获取
			imageList.add(connectStationServerTwo(entity, 1));
		} else {
			imageList.add(exImageTwo);
		}
		
		return null;
	}
	
	
	public List<LaneExListImg> abnorCarPicture(CarStatistic entity){
		return pictureMsgData(returnQueryEntity(entity.getExSerialNo(), entity.getExTime(), entity));
	}
	
	
	public CarStatistic returnQueryEntity(String exSerialNo, Date exTime, CarStatistic entity){
		List<Object[]> tempList = this.getCarStatisticDao().queryLaneByLaneExSeriaNo(exSerialNo, exTime);
		entity.setEnRoadNo(tempList.get(0)[2].toString());
		entity.setExRoadID(tempList.get(0)[3].toString());
		entity.setEnNetRoadID(tempList.get(0)[4].toString());
		entity.setExNetRoadID(tempList.get(0)[5].toString());
		entity.setEnStationID(tempList.get(0)[6].toString());
		entity.setExStationID(tempList.get(0)[7].toString());
		entity.setEnLaneID(tempList.get(0)[8].toString());
		entity.setExLaneID(tempList.get(0)[9].toString());
		
		entity.setSquadDate(DateUtil.format((Date)tempList.get(0)[10], DateUtil.PATTERN_STRING_DATE));
		entity.setEnLaneType((String) tempList.get(0).toString());
		entity.setExLaneType((String) tempList.get(0).toString());
		
		return entity;
	}
	
	public List<LaneExListImg> pictureMsgData(CarStatistic entity) {
		List<LaneExListImg> imageList = new ArrayList<LaneExListImg>();
		// 首先获取出口图片
		List<LaneExListImg> exImageList = laneExListImgService.queryLaneExListImgByExSeria(entity.getExSerialNo());
		
		LaneExListImg exImage = selectInputOrOutput(exImageList);
		//判定条件为：exImage为空   车道类型不是etc的车道才使用站级服务器去取图片
		if (null == exImage && !entity.getExLaneType().equals("3") && !entity.getExLaneType().equals("4") && !entity.getExLaneType().equals("6") && !entity.getExLaneType().equals("8")) {
			// 图片需要获取到本地，直接使用站级服务器获取
			imageList.add(connectStationServer(entity, 0));
		} else {
			imageList.add(exImage);
		}
		
		
		// 获取入口车辆图片
		List<LaneExListImg> enImageList = laneExListImgService.queryLaneExListImgByEnSeria(entity.getEnSerialNo());
		LaneExListImg enImage = selectInputOrOutput(enImageList);
		if (enImage == null) { 
			// 使用省营运协会的接口获取数据(非本路段)
			if(!roadCode.equals(entity.getEnRoadNo())){
				imageList.add(getProvincePictureData(entity));
			} else if(!entity.getEnLaneType().equals("3") && !entity.getEnLaneType().equals("4") && !entity.getEnLaneType().equals("6") && !entity.getEnLaneType().equals("8")){
				//直接使用站级服务器获取  非etc车道
				imageList.add(connectStationServer(entity, 1));
			} else{
				imageList.add(enImage);
			}
		} else {
			imageList.add(enImage);
		}

		return imageList;
	}
	
	
	
	
	//0为出口车辆照   1位入口车辆照
	public LaneExListImg connectStationServer(CarStatistic entity, int status) {
		//开启socket服务程序
		//CarStatisticServer startServer = new CarStatisticServer();
	
		StringBuffer msgBody = new StringBuffer();
		//LaneExListImg laneExListImg = null;
		
		String pid = "";
		LaneExListImg laneExListImg = null;
		//拼接字符串
		try {
			//启动服务
			contextInitialized(entity, status);
			if(0==status){
				/*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				msgBody.append("AreaNo=4412\t").append("ImgListNo=7817155686946ADF\t").append("LaneNo=21\t")
				.append("MsgType=101\t").append("OccurTime=").append(format.format(new Date())+"\t").append("PacketSeq=").append(getSeqStr()+"\t").append("RoadNo=120\t")
				.append("SendIP=192.168.1.64\t").append("SendPort=").append(localPort).append("\t").append("SquadDate=2016-01-01\t").append("StationNo=23");*/
				
				pid = entity.getExSerialNo();
				msgBody.append("AreaNo=").append(entity.getExNetRoadID()).append("\t").append("ImgListNo=").append(entity.getExSerialNo()).append("\t").append("LaneNo=").append(entity.getExLaneID()).append("\t")
				.append("MsgType=101\t").append("OccurTime=").append(DateUtil.format(new Date(),  DateUtil.PATTERN_STRING_TIME)+"\t")
				.append("PacketSeq=").append(getSeqStr()+"\t").append("RoadNo=").append(entity.getExRoadID()).append("\t")
				.append("SendIP=").append(InetAddress.getLocalHost().getHostAddress()).append("\t").append("SendPort=").append(localPort).append("\t")
				.append("SquadDate=").append(entity.getSquadDate()).append("\t").append("StationNo=").append(entity.getExStationID());
				
			} else{
				//查询入口工班时间
				List<Object[]> squadList = this.getCarStatisticDao().queryLaneEnSquadDate(entity.getEnSerialNo(), entity.getSquadDate());
				pid = entity.getEnSerialNo();
			System.out.println("squadList:"+squadList.get(0));
				/*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				msgBody.append("AreaNo=4412\t").append("ImgListNo=7817155686946ADF\t").append("LaneNo=21\t")
				.append("MsgType=101\t").append("OccurTime=").append(format.format(new Date())+"\t").append("PacketSeq=").append(getSeqStr()+"\t").append("RoadNo=120\t")
				.append("SendIP=192.168.1.64\t").append("SendPort=").append(localPortTwo).append("\t").append("SquadDate=2016-01-01\t").append("StationNo=23");*/
				
				if(null!=squadList && squadList.size()>0){
					msgBody.append("AreaNo=").append(entity.getEnNetRoadID()).append("\t").append("ImgListNo=").append(entity.getEnSerialNo()).append("\t").append("LaneNo=").append(entity.getEnLaneID()).append("\t")
					.append("MsgType=101\t").append("OccurTime=").append(DateUtil.format(new Date(),  DateUtil.PATTERN_STRING_TIME)+"\t")
					.append("PacketSeq=").append(getSeqStr()+"\t").append("RoadNo=").append(entity.getEnRoadNo()).append("\t")
					.append("SendIP=").append(InetAddress.getLocalHost().getHostAddress()).append("\t").append("SendPort=").append(localPortTwo).append("\t")
					.append("SquadDate=").append(squadList.get(0)).append("\t").append("StationNo=").append(entity.getEnStationID());
				} else{
					return null;
				}
			}

			String allMsgBody = getMessageHeader(msgBody)+msgBody.toString();
			
			String fourthStr = String.format("%08d", allMsgBody.length()+16);
			
			//生成标识字符串
			CRC32 crc32 = new CRC32();
			crc32.update(allMsgBody.getBytes());
			String firstBody = fourthStr+allMsgBody;
			String allMsg  = firstBody+Long.toHexString(crc32.getValue()).toUpperCase();  
			
			//头文件和尾文件字符串的生成
			char[] charHead = {0xff,0xfe,0xfd};
			String header =  String.valueOf(charHead);
			char[] charLast = {0xfd,0xfe,0xff};
			String last =  String.valueOf(charLast);
			String allStr = header+allMsg+last;
			
			//发送链接服务器请求
			sendMsg(allStr);
			
			//返回一个实体数据
			laneExListImg = returnStationLaneImage(pid, entity.getSquadDate());
		} catch (Exception e) {
			e.printStackTrace();
			
System.out.println("发送错误---------");
			
			if (null != serverSocket && !serverSocket.isClosed()) {
				System.out.println("关掉了serverSocket  carStatistic.java........");
				try {
					serverSocket.close();
					return null;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			return null;
		} finally{
			
		}
		
		return laneExListImg;
	}
	
	
	//获取两张出口图片
	public LaneExListImg connectStationServerTwo(CarStatistic entity, int flag) {
		StringBuffer msgBody = new StringBuffer();
		
		
		String pid = "";
		LaneExListImg laneExListImg = null;
		//拼接字符串
		try {
			//启动服务
			contextInitialized(entity, flag);
			
			pid = entity.getExSerialNo();
			if(0==flag){
				msgBody.append("AreaNo=").append(entity.getExNetRoadID()).append("\t").append("ImgListNo=").append(entity.getExSerialNo()).append("\t").append("LaneNo=").append(entity.getExLaneID()).append("\t")
				.append("MsgType=101\t").append("OccurTime=").append(DateUtil.format(new Date(),  DateUtil.PATTERN_STRING_TIME)+"\t")
				.append("PacketSeq=").append(getSeqStr()+"\t").append("RoadNo=").append(entity.getExRoadID()).append("\t")
				.append("SendIP=").append(InetAddress.getLocalHost().getHostAddress()).append("\t").append("SendPort=").append(localPort).append("\t")
				.append("SquadDate=").append(entity.getSquadDate()).append("\t").append("StationNo=").append(entity.getExStationID());
				
			} else{
				msgBody.append("AreaNo=").append(entity.getExNetRoadID()).append("\t").append("ImgListNo=").append(entity.getExSerialNo()).append("\t").append("LaneNo=").append(entity.getExLaneID()).append("\t")
				.append("MsgType=101\t").append("OccurTime=").append(DateUtil.format(new Date(),  DateUtil.PATTERN_STRING_TIME)+"\t")
				.append("PacketSeq=").append(getSeqStr()+"\t").append("RoadNo=").append(entity.getExRoadID()).append("\t")
				.append("SendIP=").append(InetAddress.getLocalHost().getHostAddress()).append("\t").append("SendPort=").append(localPortTwo).append("\t")
				.append("SquadDate=").append(entity.getSquadDate()).append("\t").append("StationNo=").append(entity.getExStationID());
			}

			String allMsgBody = getMessageHeader(msgBody)+msgBody.toString();
			
			String fourthStr = String.format("%08d", allMsgBody.length()+16);
			
			//生成标识字符串
			CRC32 crc32 = new CRC32();
			crc32.update(allMsgBody.getBytes());
			String firstBody = fourthStr+allMsgBody;
			String allMsg  = firstBody+Long.toHexString(crc32.getValue()).toUpperCase();  
			
			//头文件和尾文件字符串的生成
			char[] charHead = {0xff,0xfe,0xfd};
			String header =  String.valueOf(charHead);
			char[] charLast = {0xfd,0xfe,0xff};
			String last =  String.valueOf(charLast);
			String allStr = header+allMsg+last;
			
			//发送链接服务器请求
			sendMsg(allStr);
			
			//返回一个实体数据
			laneExListImg = returnStationLaneImage(pid, entity.getSquadDate());
		} catch (Exception e) {
			e.printStackTrace();
			
			if (null != serverSocket && !serverSocket.isClosed()) {
				System.out.println("关掉了serverSocket  carStatistic.java........");
				try {
					serverSocket.close();
					return null;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			return null;
		} finally{
			
		}
		
		return laneExListImg;
	}
	
	
	/**
	 * 判断是入口还是出口返回图片实体
	 * @param status
	 * @param allImageList
	 * @return
	 */
	public LaneExListImg selectInputOrOutput(List<LaneExListImg> allImageList){
		if(null==allImageList || allImageList.size()==0){
			return null;
		} else{
			return allImageList.get(0);
		}
	}
	
	/**
	 * 通过省中心接口获取图片数据
	 * @param entity
	 * @return
	 */
	public LaneExListImg getProvincePictureData(CarStatistic entity) {
		String validateURL = "http://"+provinceIp+":"+provincePort+"/openservice/services/http/entranceImage?ImageSerialNo="+entity.getEnSerialNo();
		HttpURLConnection conn = null;
		FileOutputStream fos = null;
		try {
			URL url = new URL(validateURL); // 创建URL对象
			// 返回一个URLConnection对象，它表示到URL所引用的远程对象的连接
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(1000); // 设置连接超时为0.5秒
			//conn.setReadTimeout(1000);
			conn.setRequestMethod("POST"); // 设定请求方式
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.connect(); // 建立到远程对象的实际连接
			
			//System.out.println("aaaa:"+conn.getHeaderField("responseCode"));
			
			// 判断是否正常响应数据
			if (conn.getResponseCode()==HttpURLConnection.HTTP_OK && conn.getHeaderField("responseCode").equals("0")) {
				
				InputStream inputStream = conn.getInputStream();
				byte[] data = readInputStram(inputStream);
				
				entity.setExTime(new Date());
				File file = createDir(entity.getSquadDate());
				fos = new FileOutputStream(file);
				fos.write(data);
				
				return saveLaneImage(file, entity.getEnSerialNo(), 1, entity);
			}
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("省中心图片信息获取异常........."+e.getMessage());
			
			return null;
		} finally {
			if (conn != null) {
				conn.disconnect(); // 中断连接
			}
			
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
	
	
	/***
	 * 站级服务器保存获取过来的图片
	 * @param file
	 * @param entity
	 * @return
	 */
	public LaneExListImg returnStationLaneImage(String pid, String squadDate){
		LaneExListImg image = new LaneExListImg();
		
		image.setExListImageName(pid+".jpg");
		image.setSquadDate(squadDate);
		
		return image;
	}
	
	
	
	/***
	 * 保存获取过来的图片
	 * @param file
	 * @param entity
	 * @return
	 */
	public LaneExListImg saveLaneImage(File file, String pid, int type, CarStatistic entity){
		LaneExListImg image = new LaneExListImg();
		
		image.setExListImageName(file.getName());
		image.setExListFileType("jpg");
		image.setExListImageSize(file.length());
		image.setExListImageDate(new Date());
		image.setExListImgeType(type); //入口车辆照片
		image.setId(UUID.randomUUID().toString());
		image.setPid(pid);
		image.setSquadDate(entity.getSquadDate());
		
		laneExListImgService.save(image);
		
		return image;
	}
	
	//通过时间创建文件
	public File createDir(String squadDate) throws IOException{
		String dirName = fileDir+"//"+squadDate;
		File dirFile = new File(dirName);
		
		if(!dirFile.exists() || dirFile.isDirectory()){
			dirFile.mkdir();
		} 
		
		
		String fileName = dirName+"//"+UUID.randomUUID().toString()+".jpg";
		File newFile = new File(fileName);
		newFile.createNewFile();
		
		return newFile;
	}
	
	
	// 对字节数组字符串进行Base64解码并生成图片
	public static boolean generateImage(String imgStr, String imgFilePath) throws IOException {
		if (imgStr == null)  return false;  // 图像数据为空
		BASE64Decoder decoder = new BASE64Decoder();
		// Base64解码
		byte[] bytes = decoder.decodeBuffer(imgStr);
		for (int i = 0; i < bytes.length; ++i) {
			if (bytes[i] < 0) {// 调整异常数据
				bytes[i] += 256;
			}
		}
		// 生成jpeg图片
		OutputStream out = new FileOutputStream(imgFilePath);
		out.write(bytes);
		out.flush();
		out.close();
		return true;
	}
	
	
	//将获取到的值转化成二进制文件
	public byte[] readInputStram(InputStream inputStream) throws IOException{
		ByteArrayOutputStream outStream = null;
		try{
			outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while((len = inputStream.read(buffer))!=-1){
				outStream.write(buffer, 0, len);
			}
			
			byte[] data = outStream.toByteArray();
			return data;
		} finally{
			if(outStream!=null){
				outStream.close();
			}
			
			if(inputStream!=null){
				inputStream.close();
			}
		}
	}
	
	/**
	 * 向站级服务器发送消息
	 * @param sendMsg
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * @throws NumberFormatException 
	 * @throws UnsupportedEncodingException
	 */
	public void sendMsg(String sendMsg) throws NumberFormatException, UnknownHostException, IOException {  
		Socket socket = null;
		OutputStream out = null;
		try{
			socket = new Socket(stationServerIp, Integer.parseInt(stationServerPort));
			out = socket.getOutputStream();

			out.write(sendMsg.getBytes("ISO8859-1"));  //格式转码
	        out.flush();

		} finally{
			if(socket!=null){
				try {
					socket.close();
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

	}
	
	/**
	 * 头信息序列号生成进制
	 * @return
	 */
	public String getSeqStr(){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String sSeq = String.format("SEQ%s@%03d", format.format(new Date()), m_iSeqNo++);
	    if(m_iSeqNo>999)
	        m_iSeqNo = 0;
	    return sSeq;
	}
	
	/**
	 * 向站级服务器发送请求的头信息
	 * @param buf
	 * @return
	 */
	public String getMessageHeader(StringBuffer buf){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String formatStr = format.format(new Date());
		
		String firstStr = formatStr.substring(0, 15);
		
		String secondStr = "00002";
		
		String thirdStr = startMath.toString();
		
		if(startMath >= 90000000 || startMath < 10000000 ) {
			startMath = new Long(10000000);
		}
		
		startMath ++;
		
		int byteLenth = buf.toString().getBytes().length;
		String fourthStr = String.format("%08d", byteLenth);
		
		
		return firstStr+secondStr+thirdStr+fourthStr;
	}
	
	
	/**
	 * 启动线程服务
	 * @param saveDate
	 * @param randomFileName
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public void contextInitialized(CarStatistic entity, int status) throws Exception {
		if(status == 0){
			serverSocket = new ServerSocket(Integer.parseInt(localPort));
		} else{
			serverSocket = new ServerSocket(Integer.parseInt(localPortTwo));
		}
		
		serverSocket.setSoTimeout(5000);
		// 新建线程类
		socketThread = new SocketThread(entity, status, serverSocket);
		// 启动线程
		socketThread.start();
	}
}
