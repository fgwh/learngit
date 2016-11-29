package com.hgsoft.cxf.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hibernate.SessionFactory;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.engine.SessionFactoryImplementor;
import org.springframework.stereotype.Service;
import com.hgsoft.cxf.entity.Message;
import com.hgsoft.cxf.server.impl.CallMessageImpl;
import com.hgsoft.main.squadMana.service.SquadService;
import com.hgsoft.security.service.AdminService;
import com.hgsoft.security.service.BaseService;
import com.hgsoft.security.service.ModuleService;
import com.hgsoft.security.service.RoleService;
import com.hgsoft.util.DateUtil;
import com.hgsoft.util.StringUtil;

/**
 * @version 1.0
 * @date 2015-07-07
 * @author wubiao
 *
 */
@Service
@SuppressWarnings("unchecked")
public class CallMessageOperateService extends BaseService {
	private static Logger logger = Logger.getLogger(CallMessageOperateService.class);
	
	private static SessionFactory sessionFactory;
	private static NewGoodsInspectionService newGoodsInspectionService;
	private static SquadService squadService;
	private final static String COLUMN_SEPARATOR = "####";
	
	@Resource
	public void setSquadService(SquadService squadService) {
		this.squadService = squadService;
	}
	
	@Resource
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
	 
	@Resource
	public void setNewGoodsInspectionService(
			NewGoodsInspectionService newGoodsInspectionService) {
		CallMessageOperateService.newGoodsInspectionService = newGoodsInspectionService;
	}

	
	public static AdminService adminService;
	@Resource
	public  void setAdminService(AdminService adminService) {
		CallMessageOperateService.adminService = adminService;
	}

	public static RoleService roleService;
	@Resource
	public  void setRoleService(RoleService roleService) {
		CallMessageOperateService.roleService = roleService;
	}
	
	public static ModuleService moduleService;
	@Resource
	public  void setModuleService(ModuleService moduleService) {
		CallMessageOperateService.moduleService = moduleService;
	}
	
	private static  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	public interface DealMessage{
		public void setMessage(Message msg);//消息设置
        public boolean parseMessage();//消息解析
        public Message settlementOperate();//执行操作
    }
	
	/**
	 * 
	 * 更新用户、角色、功能权限信息
	 */
	public static class DealUser implements DealMessage {
		private Message msg;
		private String data;
		
		private Map<String,String> insertMap = new HashMap<String,String> ();
		
		public Message settlementOperate() {
			
			Message message = new Message();//返回消息
			
			String delARole = "delete from sys_admin_role";
			String delRModule = "delete from sys_role_module";
			String delAdmin = "delete from sys_admin";
			String delRole = "delete from sys_role";
			String delModule = "delete from sys_module";
			StringBuffer insertAdmin = new StringBuffer();
			insertAdmin.append("insert into sys_admin(id,username,name,password,sex,staffNo,email,")
				.append("phone,valid,plane,createTime,lastTime,lastIp,indexMainUrl,indexSecondUrl,subsystem,")
				.append("areaNo,roadNo,roadBranchNo,stationNo,squareNo,userId,cardNo,userLevel) ")	  
				.append("values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			
			StringBuffer insertRole = new StringBuffer();
			insertRole.append("insert into sys_role(id,name,remark,subSystem) ")//10
					  .append("values(?,?,?,?)");
			StringBuffer insertAdminRole = new StringBuffer();
			insertAdminRole.append("insert into sys_admin_role(admin,role) ")
					     .append("values(?,?)");
			StringBuffer insertModule = new StringBuffer();
			insertModule.append("insert into sys_module(id,parent,name,url,functions,priority,display,level,subsystem,remark,image) ")//5
					    .append("values(?,?,?,?,?,?,?,?,?,?,?)");
			StringBuffer insertRoleModule = new StringBuffer();
			insertRoleModule.append("insert into sys_role_module(role,module) ")//5
					    .append("values(?,?)");
			
			insertMap.put("sys_admin", insertAdmin.toString());
			insertMap.put("sys_role", insertRole.toString());
			insertMap.put("sys_admin_role", insertAdminRole.toString());
			insertMap.put("sys_module", insertModule.toString());
			insertMap.put("sys_role_module", insertRoleModule.toString());
			
			ConnectionProvider cp =((SessionFactoryImplementor)sessionFactory).getConnectionProvider();
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			Statement stmt = null;
			SimpleDateFormat sdfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");						
			try {				
				conn = cp.getConnection();
				conn.setAutoCommit(false);
				stmt = conn.createStatement();
				/*1.删除原有数据*/
				adminService.updateBySql(delARole);
				moduleService.updateBySql(delRModule);
				adminService.updateBySql(delAdmin);
				roleService.updateBySql(delRole);
				moduleService.updateBySql(delModule);
//				
//				stmt.executeUpdate(delARole);
//				stmt.executeUpdate(delRModule);
//				stmt.executeUpdate(delAdmin);
//				stmt.executeUpdate(delRole);
//				stmt.executeUpdate(delModule);
//				conn.commit();
				
				/*
				 * 更新数据
				 */
				 Document document = DocumentHelper.parseText(data);
				 Element root = document.getRootElement();
				 List<Element> tables = root.elements();
				//存储表设置对象
				 Map<String,TbService> tbMap = new HashMap<String, TbService>();
				 tbMap.put("sys_admin", new TbAdmin());
				 tbMap.put("sys_role", new TbRole());
				 tbMap.put("sys_admin_role", new TbAdminRole());
				 tbMap.put("sys_module", new TbModule());
				 tbMap.put("sys_role_module", new TbRoleModule());
				 
				 for(Element table:tables){
					 String tb = table.attributeValue("name");
					 String insertStr = insertMap.get(tb);
					 if(tb.equals("sys_admin") || tb.equals("sys_role") || tb.equals("sys_module")){
						 stmt.executeUpdate("SET IDENTITY_Insert "+tb+" ON");
					 }
					 pstmt = conn.prepareStatement(insertStr);
					 List<Element> rows = table.elements();
					 
					 for(int r=0;r<rows.size();r++){
						 List<Element> eles = rows.get(r).elements();
						 int size = eles.size();
						 String[] column = new String [size];
						 
						 for(int k=0;k<size;k++){
							 column[k]=eles.get(k).getTextTrim();
						 }
						 //表插入设值
						 TbService tbService = tbMap.get(tb.trim());
						 tbService.setColumn(pstmt, column);
						 
					
					//每个表数据都要批量执行一次
					pstmt.addBatch();
					//当数据多时分批执行
					if(r%100==0){
						pstmt.executeBatch();
						conn.commit();
						pstmt.clearBatch();
					}
					
				 }
				 
					pstmt.executeBatch();//int[] counts = 
					if(tb.equals("sys_admin") || tb.equals("sys_role") || tb.equals("sys_module")){
						stmt.executeUpdate("SET IDENTITY_Insert "+tb+" OFF");
					}
					conn.commit();
				 }
					
				message.getSendMap().put(CallMessageImpl.OPERATE_RESULT_KEY, "succ");
				
				logger.info("更新用户、角色、权限信息成功。");
				
			} catch (Exception e) {
			    try{ 
			    	conn.rollback();
			    }catch(Exception e1){
			    	logger.error("下发异常",e1);
			    } 
			    
			    message.getSendMap().put(CallMessageImpl.OPERATE_RESULT_KEY, "fail");
			    
				logger.error("更新用户、角色、权限信息失败。");
			} finally {
				closeConnection(conn,stmt,pstmt);
				cp.close();
			}
			
			return message;
		}

		public boolean parseMessage() {
			data = (String)msg.getSendMap().get(CallMessageImpl.OPERATE_DATA_KEY);
			return true;
		}

		public void setMessage(Message msg) {
			this.msg = msg;
		}
		
	}
	
	/*
	 * sys_admin
	 */
	
	public static class TbAdmin implements TbService{

		@Override
		public void setColumn(PreparedStatement pstmt, String[] column) throws NumberFormatException, SQLException, ParseException {
			pstmt.setInt(1, new Integer(column[0]));
			if(!column[1].equals("null")){
				pstmt.setString(2, column[1]);
			}else{
				pstmt.setNull(2,Types.VARCHAR);
			}
			if(!column[2].equals("null")){
				pstmt.setString(3, column[2]);
			}else{
				pstmt.setNull(3,Types.VARCHAR);
			}
			if(!column[2].equals("null")){
				pstmt.setString(3, column[2]);
			}else{
				pstmt.setNull(3,Types.VARCHAR);
			}
			if(!column[3].equals("null")){
				pstmt.setString(4, column[3]);
			}else{
				pstmt.setNull(4,Types.VARCHAR);
			}
			if(!column[4].equals("null")){
				pstmt.setString(5, column[4]);
			}else{
				pstmt.setNull(4,Types.VARCHAR);
			}
			if(!column[5].equals("null")){
				pstmt.setString(6,column[5]);//staffNo
			}else{
				pstmt.setNull(6,Types.VARCHAR);
			}
			if(!column[6].equals("null")){
				pstmt.setString(7,column[6]);//email
			}else{
				pstmt.setNull(7,Types.VARCHAR);
			}
			if(!column[7].equals("null")){
				pstmt.setString(8,column[7]); //phone
			}else{
				pstmt.setNull(8,Types.VARCHAR);
			}
			if(!column[8].equals("null")){
				pstmt.setString(9, column[8]); //valid
			}else{
				pstmt.setNull(9,Types.VARCHAR);
			}
			if(!column[9].equals("null")){
				pstmt.setString(10, column[9]); //plane
			}else{
				pstmt.setNull(10,Types.VARCHAR);
			}
			if(!StringUtil.isTrimEmpty(column[10]) && !column[10].equals("null")){
				java.util.Date createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(column[10]);
				pstmt.setTimestamp(11, new java.sql.Timestamp(createTime.getTime()));
			}else{
				pstmt.setTimestamp(11, null);
			}
			if(!StringUtil.isTrimEmpty(column[11]) && !column[11].equals("null")){
				java.util.Date lastTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(column[11]);
				pstmt.setTimestamp(12, new java.sql.Timestamp((lastTime.getTime())));
			}else{
				pstmt.setTimestamp(12, null);
			}
			if(!column[12].equals("null")){
				pstmt.setString(13, column[12]); //lastIP
			}else{
				pstmt.setNull(13,Types.VARCHAR);
			}
			if(!column[13].equals("null")){
				pstmt.setString(14, column[13]);
			}else{
				pstmt.setNull(14,Types.VARCHAR);
			}
			if(!column[14].equals("null")){
				pstmt.setString(15,column[14]);
			}else{
				pstmt.setNull(15,Types.VARCHAR);
			}
			if(!column[15].equals("null")){
				pstmt.setString(16,column[15]);  //subsystem
			}else{
				pstmt.setNull(16,Types.VARCHAR);
			}
			if(!column[16].equals("null")){
				pstmt.setInt(17, new Integer(column[16]));
			}else{
				pstmt.setNull(17,Types.INTEGER);
			}
			if(!column[17].equals("null")){
				pstmt.setInt(18, new Integer(column[17]));
			}else{
				pstmt.setNull(18,Types.INTEGER);
			}
			if(!column[18].equals("null")){
				pstmt.setInt(19, new Integer(column[18]));
			}else{
				pstmt.setNull(19,Types.INTEGER);
			}
			if(!column[19].equals("null")){
				pstmt.setInt(20, new Integer(column[19]));
			}else{
				pstmt.setNull(20,Types.INTEGER);
			}
			if(!column[20].equals("null")){
				pstmt.setInt(21, new Integer(column[20]));
			}else{
				pstmt.setNull(21,Types.INTEGER);
			}
			if(!column[21].equals("null")){
				pstmt.setString(22, column[21]);
			}else{
				pstmt.setNull(22,Types.VARCHAR);
			}
			if(!column[22].equals("null")){
				pstmt.setString(23, column[22]);
			}else{
				pstmt.setNull(23,Types.VARCHAR);
			}
			if(!column[23].equals("null")){
				pstmt.setShort(24,new Short(column[23]));
			}else{
				pstmt.setNull(24,Types.INTEGER);
			}
		}
	}

	/*
	 * sys_role
	 */
	public static class TbRole  implements TbService{

		@Override
		public void setColumn(PreparedStatement pstmt, String[] column)
				throws NumberFormatException, SQLException, ParseException {
			pstmt.setInt(1,new Integer(column[0]));
			if(!column[1].equals("null")){
				pstmt.setString(2,column[1]);
			}else{
				pstmt.setNull(2,Types.VARCHAR);
			}
			if(!column[2].equals("null")){
				pstmt.setString(3,column[2]);
			}else{
				pstmt.setNull(3,Types.VARCHAR);
			}
			if(!column[3].equals("null")){
				pstmt.setString(4,column[3]);
			}else{
				pstmt.setNull(4,Types.VARCHAR);
			}
			
			
		}
		
	}
	
	/*
	 * sys_admin_role
	 */
	public static class TbAdminRole implements TbService{

		@Override
		public void setColumn(PreparedStatement pstmt, String[] column)
				throws NumberFormatException, SQLException, ParseException {
			pstmt.setInt(1,new Integer(column[0]));
			pstmt.setInt(2,new Integer(column[1]));
		}
		
	}
	
	/*
	 * sys_module
	 */
	public static class TbModule implements TbService{

		@Override
		public void setColumn(PreparedStatement pstmt, String[] column)
				throws NumberFormatException, SQLException, ParseException {
			pstmt.setInt(1,new Integer(column[0]));
			if(!column[1].equals("null")){
				pstmt.setInt(2,new Integer(column[1]));
			}else{
				pstmt.setNull(2, Types.INTEGER);
			}
			if(!column[2].equals("null")){
				pstmt.setString(3, column[2]);
			}else{
				pstmt.setNull(3, Types.VARCHAR);
			}
			if(!column[3].equals("null")){
				pstmt.setString(4, column[3]);
			}else{
				pstmt.setNull(4, Types.VARCHAR);
			}
			if(!column[4].equals("null")){
				pstmt.setString(5, column[4]);
			}else{
				pstmt.setNull(5, Types.VARCHAR);
			}
			if(!column[5].equals("null")){
				pstmt.setInt(6,new Integer(column[5]));
			}else{
				pstmt.setNull(6, Types.INTEGER);
			}
			if(!column[6].equals("null")){
				pstmt.setInt(7,new Integer(column[6]));
			}else{
				pstmt.setNull(7, Types.INTEGER);
			}
			if(!column[7].equals("null")){
				pstmt.setInt(8,new Integer(column[7]));
			}else{
				pstmt.setNull(8, Types.INTEGER);
			}
			if(!column[8].equals("null")){
				pstmt.setString(9, column[8]);
			}else{
				pstmt.setNull(9, Types.VARCHAR);
			}
			if(!column[9].equals("null")){
				pstmt.setString(10, column[9]);
			}else{
				pstmt.setNull(10, Types.VARCHAR);
			}
			if(!column[10].equals("null")){
				pstmt.setString(11, column[10]);
			}else{
				pstmt.setNull(11, Types.VARCHAR);
			}
		}
		
	}

	/*
	 * sys_role_module
	 */
	public static class TbRoleModule implements TbService{

		@Override
		public void setColumn(PreparedStatement pstmt, String[] column)
				throws NumberFormatException, SQLException, ParseException {
			pstmt.setInt(1,new Integer(column[0]));
			pstmt.setInt(2,new Integer(column[1]));
		}
		
	}
	
	/*
	 * tb_area
	 */
	public static class TbArea implements TbService{

		@Override
		public void setColumn(PreparedStatement pstmt, String[] column)
				throws NumberFormatException, SQLException, ParseException {
			pstmt.setInt(1, new Integer(column[0]));
			if(!column[1].equals("null")){
				pstmt.setString(2, column[1]);
			}else{
				pstmt.setNull(2, Types.VARCHAR);
			}
			if(!column[2].equals("null")){
				pstmt.setString(3, column[2]);
			}else{
				pstmt.setNull(3, Types.VARCHAR);
			}
			if(!column[3].equals("null")){
				pstmt.setString(4, column[3]);
			}else{
				pstmt.setNull(4, Types.VARCHAR);
			}
			if(!column[4].equals("null")){
				pstmt.setString(5, column[4]);
			}else{
				pstmt.setNull(5, Types.VARCHAR);
			}
			if(!column[5].equals("null")){
				pstmt.setString(6,column[5]);
			}else{
				pstmt.setNull(6, Types.VARCHAR);
			}
			if(!column[6].equals("null")){
				pstmt.setString(7,column[6]);
			}else{
				pstmt.setNull(7, Types.VARCHAR);
			}
			if(!column[7].equals("null")){
				pstmt.setString(8,column[7]);
			}else{
				pstmt.setNull(8, Types.VARCHAR);
			}
		}
		
	}

	/*
	 * tb_road
	 */
	public static class TbRoad implements TbService{

		@Override
		public void setColumn(PreparedStatement pstmt, String[] column)
				throws NumberFormatException, SQLException, ParseException {
			pstmt.setInt(1,new Integer(column[0]));
			if(!column[1].equals("null")){
				pstmt.setInt(2,new Integer(column[1]));
			}else{
				pstmt.setNull(2, Types.INTEGER);
			}
			if(!column[2].equals("null")){
				pstmt.setString(3,column[2]);
			}else{
				pstmt.setNull(3, Types.VARCHAR);
			}
			if(!column[3].equals("null")){
				pstmt.setString(4,column[3]);
			}else{
				pstmt.setNull(4, Types.VARCHAR);
			}
			if(!column[4].equals("null")){
				pstmt.setString(5,column[4]);
			}else{
				pstmt.setNull(5, Types.VARCHAR);
			}
			if(!column[5].equals("null")){
				pstmt.setString(6,column[5]);
			}else{
				pstmt.setNull(6, Types.VARCHAR);
			}
			if(!column[6].equals("null")){
				pstmt.setString(7,column[6]);
			}else{
				pstmt.setNull(7, Types.VARCHAR);
			}
			if(!column[7].equals("null")){
				pstmt.setString(8,column[7]);
			}else{
				pstmt.setNull(8, Types.VARCHAR);
			}
			if(!column[8].equals("null")){
				pstmt.setString(9,column[8]);
			}else{
				pstmt.setNull(9, Types.VARCHAR);
			}
			if(!column[9].equals("null")){
				pstmt.setString(10,column[9]);
			}else{
				pstmt.setNull(10, Types.VARCHAR);
			}
			
		}
		
	}
	
	/*
	 * tb_station
	 */
	public static class TbStation implements TbService{

		@Override
		public void setColumn(PreparedStatement pstmt, String[] column)
				throws NumberFormatException, SQLException, ParseException {
			pstmt.setInt(1,new Integer(column[0]));
			if(!column[1].equals("null")){
				pstmt.setInt(2,new Integer(column[1]));
			}else{
				pstmt.setNull(2, Types.INTEGER);
			}
			if(!column[2].equals("null")){
				pstmt.setString(3,column[2]);
			}else{
				pstmt.setNull(3, Types.VARCHAR);
			}
			if(!column[3].equals("null")){
				pstmt.setString(4,column[3]);
			}else{
				pstmt.setNull(4, Types.VARCHAR);
			}
			if(!column[4].equals("null")){
				pstmt.setString(5,column[4]);
			}else{
				pstmt.setNull(5, Types.VARCHAR);
			}
			if(!column[5].equals("null")){
				pstmt.setString(6,column[5]);
			}else{
				pstmt.setNull(6, Types.VARCHAR);
			}
			if(!column[6].equals("null")){
				pstmt.setString(7,column[6]);
			}else{
				pstmt.setNull(7, Types.VARCHAR);
			}
			if(!column[7].equals("null")){
				pstmt.setString(8,column[7]);
			}else{
				pstmt.setNull(8, Types.VARCHAR);
			}
			if(!column[8].equals("null")){
				pstmt.setString(9,column[8]);
			}else{
				pstmt.setNull(9, Types.VARCHAR);
			}
			if(!column[9].equals("null")){
				pstmt.setString(10,column[9]);
			}else{
				pstmt.setNull(10, Types.VARCHAR);
			}
			if(!column[10].equals("null")){
				java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(column[10]);
				pstmt.setTimestamp(11, new java.sql.Timestamp((date.getTime())));
			}else{
				//pstmt.setNull(11, Types.TIME);
				pstmt.setTimestamp(11, null);
			}
			if(!column[11].equals("null")){
				pstmt.setInt(12,new Integer(column[11]));
			}else{
				pstmt.setNull(12, Types.INTEGER);
			}
			if(!column[12].equals("null")){
				pstmt.setInt(13,new Integer(column[12]));
			}else{
				pstmt.setNull(13, Types.INTEGER);
			}
			if(!column[13].equals("null")){
				pstmt.setInt(14,new Integer(column[13]));
			}else{
				pstmt.setNull(14, Types.INTEGER);
			}
			if(!column[14].equals("null")){
				pstmt.setDouble(15,new Double(column[14]));
			}else{
				pstmt.setNull(15, Types.DOUBLE);
			}
			if(!column[15].equals("null")){
				pstmt.setDouble(16,new Double(column[15]));
			}else{
				pstmt.setNull(16, Types.DOUBLE);
			}
			if(!column[16].equals("null")){
				pstmt.setInt(17,new Integer(column[16]));
			}else{
				pstmt.setNull(17, Types.INTEGER);
			}
			
		}
		
	}
	
	/*
	 * tb_square
	 */
	public static class TbSquare implements TbService{

		@Override
		public void setColumn(PreparedStatement pstmt, String[] column)
				throws NumberFormatException, SQLException, ParseException {
			pstmt.setInt(1,new Integer(column[0]));
			if(!column[1].equals("null")){
				pstmt.setInt(2,new Integer(column[1]));
			}else{
				pstmt.setNull(2, Types.INTEGER);
			}
			if(!column[2].equals("null")){
				pstmt.setInt(3, new Integer(column[2]));
			}else{
				pstmt.setNull(3, Types.INTEGER);
			}
			if(!column[3].equals("null")){
				pstmt.setInt(4, new Integer(column[3]));
			}else{
				pstmt.setNull(4, Types.INTEGER);
			}
			if(!column[4].equals("null")){
				pstmt.setString(5, column[4]);
			}else{
				pstmt.setNull(5, Types.VARCHAR);
			}
			
		}
		
	}
	/**
	 * 
	 * 更新区域、路段、收费站、广场信息
	 */
	public static class DealOrga implements DealMessage {
		private Message msg;
		private String data;
		
		private Map<String,String> insertMap = new HashMap<String,String> ();
		
		public Message settlementOperate() {
			
			Message message = new Message();//返回消息
			
			String delSquard = "delete from tb_square";
			String delStation = "delete from tb_station";
			String delRoad = "delete from tb_road";
			String deleteArea = "delete from tb_area";
			StringBuffer insertArea = new StringBuffer();
			insertArea.append("insert into tb_area(areaNo,areaName,areaServerName,areaServerIP,areaDomain,areaDBName,areaDBUserName,areaDBPassword) ")//8
					  .append("values(?,?,?,?,?,?,?,?)");
			StringBuffer insertRoad = new StringBuffer();
			insertRoad.append("insert into tb_road(roadNo,areaNo,roadName,roadAllName,roadServerName,roadServerIP,roadDomain,roadDBName,roadDBUserName,roadDBPassword) ")//10
					  .append("values(?,?,?,?,?,?,?,?,?,?)");
			StringBuffer insertStation = new StringBuffer();
			insertStation.append("insert into tb_station(stationNo,roadNo,stationName,stationServerName,stationServerIP,stationDomain,stationDBName,stationDBUserName,stationDBPassword")//9
			             .append(",version,startTime,stationType,stationIndex,status,Xcode,Ycode,branchId) ")//8
					     .append("values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			StringBuffer insertSquare = new StringBuffer();
			insertSquare.append("insert into tb_square(squareNo,areaNo,roadNo,stationNo,squareName) ")//5
					    .append("values(?,?,?,?,?)");
			insertMap.put("tb_area", insertArea.toString());
			insertMap.put("tb_road", insertRoad.toString());
			insertMap.put("tb_station", insertStation.toString());
			insertMap.put("tb_square", insertSquare.toString());
			
			ConnectionProvider cp =((SessionFactoryImplementor)sessionFactory).getConnectionProvider();
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			Statement stmt = null;
			SimpleDateFormat sdfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");						
			try {				
				conn = cp.getConnection();
				conn.setAutoCommit(false);
				
				/*1.删除原有数据*/
				stmt = conn.createStatement();
//				squareService.updateBySql(delSquard);
//				stationService.updateBySql(delStation);
//				roadService.updateBySql(delRoad);
//				areaService.updateBySql(deleteArea);
//				stmt.executeUpdate(delSquard);
//				stmt.executeUpdate(delStation);
//				stmt.executeUpdate(delRoad);
//				stmt.executeUpdate(deleteArea);
//				conn.commit();
				
				/*
				 * 更新数据
				 */
				//存储表设置对象
				 Map<String,TbService> tbMap = new HashMap<String, TbService>();
				 tbMap.put("tb_area", new TbArea());
				 tbMap.put("tb_road", new TbRoad());
				 tbMap.put("tb_station", new TbStation());
				 tbMap.put("tb_square", new TbSquare());
				 
				 Document document = DocumentHelper.parseText(data);
				 Element root = document.getRootElement();
				 List<Element> tables = root.elements();
				 for(Element table:tables){
					 String tb = table.attributeValue("name");
				 String insertStr = insertMap.get(tb);
//				 stmt.executeUpdate("SET IDENTITY_Insert " + tb + " ON");
				 pstmt = conn.prepareStatement(insertStr);
				 List<Element> rows = table.elements();
				 for(int r=0;r<rows.size();r++){
					 List<Element> eles = rows.get(r).elements();
					 int size = eles.size();
					 String[] column = new String [size];
					 
					 for(int k=0;k<size;k++){
						 column[k]=eles.get(k).getTextTrim();
					 }
					 //表插入设值
					 TbService tbService = tbMap.get(tb.trim());
					 tbService.setColumn(pstmt, column);
				
					//每个表数据都要批量执行一次
					pstmt.addBatch();
					//当数据多时分批执行
					if(r%100==0){
						pstmt.executeBatch();
						conn.commit();
						pstmt.clearBatch();
					}
						
				 }
				 
				pstmt.executeBatch();//int[] counts = 
//				stmt.executeUpdate("SET IDENTITY_Insert " + tb + " OFF");
					conn.commit();
				 }
					
					
				
				message.getSendMap().put(CallMessageImpl.OPERATE_RESULT_KEY, "succ");
				
				logger.info("更新区域、路段、收费站、收费广场信息成功。");
				
			} catch (Exception e) {
			    try{ 
			    	conn.rollback();
			    }catch(Exception e1){
			    	logger.error("下发异常",e1);
			    } 
			    
			    message.getSendMap().put(CallMessageImpl.OPERATE_RESULT_KEY, "fail");
			    
				logger.error("更新区域、路段、收费站、收费广场信息失败。");
			} finally {
				closeConnection(conn,stmt,pstmt);
				cp.close();
			}
			
			return message;
		}

		public boolean parseMessage() {
			data = (String)msg.getSendMap().get(CallMessageImpl.OPERATE_DATA_KEY);
			return true;
		}

		public void setMessage(Message msg) {
			this.msg = msg;
		}
		
	}
	
	
	/**
	 * 
	 * 更新工班信息
	 */
	public static class DealSquad implements DealMessage {
		private Message msg;
		private String data;
		
		public Message settlementOperate() {
			
			Message message = new Message();//返回消息
			
			String deleteSquad = "delete from tb_squad";
			
			StringBuffer insertSquad = new StringBuffer();
			insertSquad.append("insert into tb_squad(workNo,workName,startTime,endTime,startDate,timeDiff,remark,workType, squadStatus) ")//8
					    .append("values(?,?,?,?,?,?,?,?,?)");
			
			ConnectionProvider cp =((SessionFactoryImplementor)sessionFactory).getConnectionProvider();
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			Statement stmt = null;
			SimpleDateFormat sdfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");						
			try {				
				conn = cp.getConnection();
				conn.setAutoCommit(false);
				
				/*1.删除原有数据*/
				stmt = conn.createStatement();
				//stmt.executeUpdate(deleteSquad);
				//conn.commit();
				squadService.deleteAllSquad();
				
				/*2.更新表数据*/				
				stmt.executeUpdate("SET IDENTITY_Insert tb_squad ON");
				pstmt = conn.prepareStatement(insertSquad.toString());
				
	            Document doc = DocumentHelper.parseText(data);
	            List<Element> rows = doc.getRootElement().elements();
	            Element row;
	            for(int i=0; i<rows.size(); i++) {
	            	row = rows.get(i);
	            	pstmt.setInt(1, new Integer(row.element("workNo").getTextTrim()));
	            	pstmt.setString(2, row.element("workName").getTextTrim());
	            	pstmt.setTime(3, Time.valueOf(row.element("startTime").getTextTrim()));
	            	pstmt.setTime(4, Time.valueOf(row.element("endTime").getTextTrim()));
					pstmt.setDate(5, new java.sql.Date(sdfmt.parse(row.element("startDate").getTextTrim()).getTime()));
					pstmt.setInt(6,new Integer(row.element("timeDiff").getTextTrim()));
					pstmt.setString(7,row.element("remark").getTextTrim());
					pstmt.setInt(8,new Integer(row.element("workType").getTextTrim()));
					pstmt.setInt(9,new Integer(row.element("squadStatus").getTextTrim()));
					
					pstmt.addBatch();
	            }
	            
				pstmt.executeBatch();//int[] counts = 
				stmt.executeUpdate("SET IDENTITY_Insert tb_squad OFF");
				conn.commit();
				
				message.getSendMap().put(CallMessageImpl.OPERATE_RESULT_KEY, "succ");
				
				logger.info("更新工班信息成功。");
				
			} catch (Exception e) {
			    try{ conn.rollback();}catch(Exception e1){} 
			    
			    message.getSendMap().put(CallMessageImpl.OPERATE_RESULT_KEY, "fail");
			    
				logger.info("更新工班信息失败。");
			} finally {
				closeConnection(conn,stmt,pstmt);
				cp.close();
			}
			
			return message;
		}

		public boolean parseMessage() {
			data = (String)msg.getSendMap().get(CallMessageImpl.OPERATE_DATA_KEY);
			return true;
		}

		public void setMessage(Message msg) {
			this.msg = msg;
		}
		
	}
	
	/**
	 * 关闭连接
	 * 
	 * @param conn
	 * @param pstmt
	 * @param rs
	 */
	public static void closeConnection(Connection conn,Statement stmt,PreparedStatement pstmt) {
		
		if (null != pstmt) {
			try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}finally {pstmt = null;}
		}
		if (null != stmt) {
			try {stmt.close();} catch (SQLException e) {e.printStackTrace();}finally {stmt = null;}
		}
		if (null != conn) {
			try {conn.close();} catch (SQLException e) {e.printStackTrace();}finally {conn = null;}
		}
		
	}

	/**
	 * 取黑名单数据
	 *
	 */
	public static class DealBlackList implements DealMessage {

		private Message msg;

		public Message settlementOperate() {
			try{
				 
				logger.info("站级黑名单数据查询上传成功");
			}catch(Exception e){
				logger.info("站级黑名单数据查询上传失败");
			}
			return msg;
		}

		public boolean parseMessage() {
			return true;
		}

		public void setMessage(Message msg) {
			this.msg = msg;
		}
	}
	
	/**
	 * 黑名单数据保存
	 * @author Administrator
	 *
	 */
	public static class SaveBlackList implements DealMessage{
		private Message msg;

		public Message settlementOperate() {
			 
			return msg;
		}

		public boolean parseMessage() {
			return true;
		}

		public void setMessage(Message msg) {
			this.msg = msg;
		}
	}
	
	public static class GetLaneExListData implements DealMessage{
		private Message msg;
		
		@Override
		public Message settlementOperate() {
			 
			return msg;
		}

		@Override
		public void setMessage(Message msg) {
			this.msg = msg;
		}

		@Override
		public boolean parseMessage() {
			return true;
		}
	}
	
	public static class DealGoodsInspection implements DealMessage{
		private Message msg;
		
		@Override
		public Message settlementOperate() { 
			logger.info("货物检测："+new Date()+"获取获取检测信息正常");
			return msg;
		}
		@Override
		public boolean parseMessage() {
			return true;
		}
		@Override
		public void setMessage(Message msg) {
			this.msg = msg;
		}
	}
	
	public static class UpdateOSTime implements DealMessage {
		private Message msg;
		
		public boolean parseMessage() {
			return true;
		}

		public void setMessage(Message msg) {
			this.msg = msg;
		}

		public Message settlementOperate() {
			String synchTimeStr = (String)msg.getSendMap().get("synchTime");//取得同步时间字符串 yyyy-MM-dd HH:mm:ss SSS
			
			Message message = new Message();//返回信息
			String operateResult = "fail";//操作结果
			
			if(changeOSTime(synchTimeStr)) {
				operateResult = "succ";
				logger.info("同步系统时间[" + synchTimeStr + "]成功");
			}else {
				logger.info("同步系统时间[" + synchTimeStr + "]失败");
			}
	        
			message.getSendMap().put(CallMessageImpl.OPERATE_RESULT_KEY, operateResult);
			return message;
		}
		
	}
	
	public static class AdjustOSTime implements DealMessage {
		private Message msg;
		
		public boolean parseMessage() {
			return true;
		}

		public void setMessage(Message msg) {
			this.msg = msg;
		}

		public Message settlementOperate() {
			
			long adjustTime = (Long)msg.getSendMap().get("adjustTime");
			Date targetTime = new Date(new Date().getTime() + adjustTime);//校准后的时间
			Calendar calender = Calendar.getInstance();
            calender.setTime(targetTime);
            long targetMillisecond = calender.get(Calendar.MILLISECOND);
            if(targetMillisecond >= 500) {//四舍五入，使误差保持在500毫秒以内
            	targetTime = new Date(targetTime.getTime() + 500);
            }
            
            String targetTimeStr = (String)DateUtil.fromatDate(targetTime,"yyyy-MM-dd HH:mm:ss");
            
            Message message = new Message();//返回信息
			String operateResult = "fail";//操作结果
			
			if(changeOSTime(targetTimeStr)) {
				operateResult = "succ";
				logger.info("校准系统时间[" + adjustTime + "毫秒]成功");
			}else {
				logger.info("校准系统时间[" + adjustTime + "毫秒]失败");
			}
	        
			message.getSendMap().put(CallMessageImpl.OPERATE_RESULT_KEY, operateResult);
			return message;
		}
		
	}
	
	/**
	 * 修改系统时间
	 * 
	 * @param time 时间字符串格式 yyyy-MM-dd HH:mm:ss SSS 或者 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static boolean changeOSTime(String time) {
		
		String[] timeAry = time.split(" ");
		String osName = System.getProperty("os.name");//操作系统名称
		int flag = 0;
		boolean succ = false;
        
		try{
			if (osName.matches("^(?i)Windows.*$")) {//windows
	        	// 设置Windows系统日期
	            Process exec = Runtime.getRuntime().exec("cmd /c date " + timeAry[0]);
	            if (exec.waitFor() == 0) {
	            	flag++;
	                System.out.println("设置系统日期成功：" + timeAry[0]);
	            } else {
	                System.out.println("设置系统日期失败：" + timeAry[0]);
	            }

	            // 设置Windows系统时间
	            exec = Runtime.getRuntime().exec("cmd /c time " + timeAry[1]);
	            if (exec.waitFor() == 0) {
	            	flag++;
	                System.out.println("设置系统时间成功：" + timeAry[1]);
	            } else {
	                System.out.println("设置系统时间失败：" + timeAry[1]);
	            }
	        }else {// Linux
		        // 设置Linux系统日期
			    Process exec = Runtime.getRuntime().exec("date -s " + timeAry[0].replace("-", ""));//date -s 20090326
		        if (exec.waitFor() == 0) {
		        	flag++;
		            System.out.println("设置系统日期成功：" + timeAry[0]);
		        } else {
		            System.out.println("设置系统日期失败：" + timeAry[0]);
		        }
		       
		        // 设置Linux系统时间
		        exec = Runtime.getRuntime().exec("date -s " + timeAry[1]);//date -s 22:35:00
		        if (exec.waitFor() == 0) {
		        	flag++;
		            System.out.println("设置系统时间成功：" + timeAry[1]);
		        } else {
		            System.out.println("设置系统时间失败：" + timeAry[1]);
		        }
	        }
			
			if(2 == flag) {
				succ = true;
			}
		}catch(Exception e) {
			//不用处理
		}
        
		return succ;
	}

}
