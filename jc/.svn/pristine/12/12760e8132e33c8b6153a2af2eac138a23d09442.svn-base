package com.hgsoft.job.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hgsoft.cxf.common.PropertiesUtil;
import com.hgsoft.main.carStatistic.entity.LaneExListImg;
import com.hgsoft.main.carStatistic.service.LaneExListImgService;
import com.hgsoft.security.service.BaseService;
import com.hgsoft.util.DBUtil;
import com.hgsoft.util.DateUtil;
import com.hgsoft.util.JDBCFrame;

@Service
public class EtcPictureService extends BaseService {
	@Resource
	private LaneExListImgService laneExListImgService;
	//流水数据库连接
	private Connection conn = null;
	private Statement pstmt = null;
	private ResultSet rs = null;
	//保存图片的基础路径
	private static final String fileDir = PropertiesUtil.getProperty("baseDir")+"//"+PropertiesUtil.getProperty("FILE_SAVE_DIR");
	//日志打印
	private static Logger logger = Logger.getLogger(EtcPictureService.class);

	
	//成功转化的etc图片数据
	public int successParseEtcPicture(){
		int count = 0;
		try{
			List<LaneExListImg> imageList = queryRoadEtcPictureList();
			
			count = laneExListImgService.saveAllLaneExListImg(imageList);
			
			int deleteEtcCount = deleteAllEtcImg(imageList);
			
			logger.info(deleteEtcCount+"条有图片的etc数据被清空了，，，，");
		} finally{
			JDBCFrame.release(conn, pstmt, rs);
		}
		
		return count;
	}
	
	//查询etc车辆图片数据
	private List<LaneExListImg> queryRoadEtcPictureList(){
		List<LaneExListImg> imageList = null;
		try{
			conn = DBUtil.getConnection();
			String deleteSql = "delete from tb_BackRegPlate_Send where regImages is null or listNo is null or CONVERT (varchar(MAX), CONVERT (VARBINARY(MAX), RegImages)) =''";
			//pstmt = conn.prepareStatement(deleteSql);
			pstmt = conn.createStatement();
			int deleteCount = pstmt.executeUpdate(deleteSql);
			
			logger.info("清空的etc数据为："+deleteCount);
			
			String sql = "select top(1000) * from tb_BackRegPlate_Send";
			pstmt = conn.createStatement();
			rs = pstmt.executeQuery(sql);
			
			byte[] imageByte = null;
			LaneExListImg image = null;
			imageList = new ArrayList<LaneExListImg>();
			while(rs.next()){
				try{
					//image实体对象拼接
					image = new LaneExListImg();
					image.setExListImgeType(3);  //etc图片标识
					image.setExListFileType("jpg");
					image.setExListImageDate(new Date());
					image.setExListImageName(rs.getString(4)+".jpg");
					image.setPid(rs.getString(4));
					imageByte = rs.getBytes(9);
					image.setExListImageSize(new Long(imageByte.length));
					image.setSquadDate(DateUtil.format(rs.getDate(3), DateUtil.PATTERN_STRING_DATE));
					image.setId(UUID.randomUUID().toString());
					image.setEtcId(rs.getString(1));
					
					
					//图片数据转化
					streamToPhoto(imageByte, image);
					
					imageList.add(image);
				} catch(Exception e){
					e.printStackTrace();
					logger.info("queryRoadEtcPictureList函数报错了，，，，");
				}
			}
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("出现异常报错了"+e.getLocalizedMessage());
			return imageList;
		}
		
		return imageList;
	}
	
	//删除已经获取过来的数据
	private int deleteAllEtcImg(List<LaneExListImg> imageList){
		int count = 0;
		try {
			if(null!=imageList && imageList.size()>0){
				StringBuilder strBuilder = new StringBuilder("");
				pstmt = conn.createStatement();
				
				for(LaneExListImg image : imageList){
					strBuilder.delete(0, strBuilder.length());
					strBuilder.append("delete from tb_BackRegPlate_Send where RegListNo='"+image.getEtcId()+"'");
					pstmt.addBatch(strBuilder.toString());
				}
				
				int[] arr = pstmt.executeBatch();
				count = arr.length;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return count;
		}
		
		return count;
	}
	
	
	
	//图片文件保存
	private void streamToPhoto(byte[] imageBlob, LaneExListImg entity) throws Exception{
		FileOutputStream fos = null;
		try{
			File file = createDir(entity.getSquadDate(), entity.getPid());
			
			fos = new FileOutputStream(file);
			
			fos.write(imageBlob);
		} finally{
			if(fos!=null){
				fos.close();
			}
		}
	}
	
	
	//通过时间创建文件
	private File createDir(String squadDate, String laneExSeriaNo) throws IOException{
		String dirName = fileDir+"//"+squadDate;
		File dirFile = new File(dirName);
		
		if(!dirFile.exists() || dirFile.isDirectory()){
			dirFile.mkdir();
		} 

		String fileName = dirName+"//"+laneExSeriaNo.trim()+".jpg";
		File newFile = new File(fileName);
		newFile.createNewFile();
		
		return newFile;
	}
}
