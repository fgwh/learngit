package com.hgsoft.job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;


public class HandleDetailMethods {
	private final static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private final static int INSERT=1;
	private final static int UPDATE=2;
	private final static int DELETE=3;
	private final static int IGNORE=4;
	
	public static int handleUserData(Connection conn,Log logger,String handleDate,String batchNo) throws Exception{
		
		String tableName = "TB_User";
		
		StringBuffer sqlInsertUserBuf = new StringBuffer();
		sqlInsertUserBuf.append("INSERT INTO TB_User")
			.append("(id,name,customerID,sex,nationality")  //5
			.append(",nativeProvince,idType,idNum,birthday,mobile")  //10
			.append(",telephone,address,domicilePlace,zipCode,email") 
			.append(",userProperties,fax,unitType,status,customPoint")//20
			.append(",creation,createTime,lastModifier,lastModifierTime,syncTime")//25
			.append(",syncBatchNo,originalUserId)") //27
			.append(" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		
		StringBuffer sqlUpdateUserBuf = new StringBuffer();
		sqlUpdateUserBuf.append("UPDATE TB_User set status=1 where id=?");
		
		
		StringBuffer sqlInsertUserExBuf = new StringBuffer();
		sqlInsertUserExBuf.append("INSERT INTO TB_User_Extend")
			.append("(userId,invoiceType,agentName,agentIdType,agentIdNum")  //5
			.append(",agentMobile,agentTelephone,agentAddress,grade)")  //9
			.append(" values(?,?,?,?,?,?,?,?,?)"); 
		
		
		
		StringBuffer sqlUpdateUserExBuf = new StringBuffer();
		sqlUpdateUserExBuf.append("UPDATE TB_User_Extend set ")
			.append("userId=?,invoiceType=?,agentName=?,agentIdType=?,agentIdNum=?")  //5
			.append(",agentMobile=?,agentTelephone=?,agentAddress=?,grade=?")  //9
			.append(" where userId=?");
		
		StringBuffer sqlInsertUserHisBuf = new StringBuffer();
		sqlInsertUserHisBuf.append("INSERT INTO TB_User_His(id,name,customerID,sex,nationality,nativeProvince,idType,idNum,birthday,mobile,telephone,address,domicilePlace,zipCode,email,userProperties,fax,unitType,status,customPoint,creation,createTime,lastModifier,lastModifierTime,invoiceType,agentName,agentIdType,agentIdNum,agentMobile,agentTelephone,agentAddress,grade,BusinessType,BusinessDate,HandleDate,HandleFlag,batchNo)")
			.append(" select id,name,customerID,sex,nationality,nativeProvince,idType,idNum,birthday,mobile,telephone,address,domicilePlace,zipCode,email,userProperties,fax,unitType,status,customPoint,creation,createTime,lastModifier,lastModifierTime,invoiceType,agentName,agentIdType,agentIdNum,agentMobile,agentTelephone,agentAddress,grade,BusinessType,BusinessDate,?,?,? from TB_User_Recv where id=?");
		
		
		PreparedStatement psInsertUser = conn.prepareStatement(sqlInsertUserBuf.toString());
		PreparedStatement psUpdateUser = conn.prepareStatement(sqlUpdateUserBuf.toString());
		PreparedStatement psDeleteUser = conn.prepareStatement("DELETE FROM TB_User where id=?");
		PreparedStatement psInsertUserEx = conn.prepareStatement(sqlInsertUserExBuf.toString());
		PreparedStatement psUpdatetUserEx = conn.prepareStatement(sqlUpdateUserExBuf.toString());
		PreparedStatement psInsertUserHis = conn.prepareStatement(sqlInsertUserHisBuf.toString());
		PreparedStatement psDeleteUserRecv = conn.prepareStatement("DELETE FROM TB_User_Recv where id=?");
		
		
		/**
		 * 查询tb_User_Recv表的记录
		 */
		StringBuffer sqlQueryUserRecvQueryBuf = new StringBuffer();
		sqlQueryUserRecvQueryBuf.append("select")
			.append(" id,name,customerID,sex,nationality")  //5
			.append(",nativeProvince,idType,idNum,birthday,mobile")  //10
			.append(",telephone,address,domicilePlace,zipCode,email") 
			.append(",userProperties,fax,unitType,status,customPoint")
			.append(",creation,createTime,lastModifier,lastModifierTime,invoiceType")
			.append(",agentName,agentIdType,agentIdNum,agentMobile,agentTelephone")
			.append(",agentAddress,grade,BusinessType,BusinessDate") //34
			.append(" FROM TB_User_Recv");
		
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(sqlQueryUserRecvQueryBuf.toString());
		
		
		int queryCount = 0;
		
		
		try{
		
		while(rs.next()){
			
			boolean handleFlag= true;
			
			int id = rs.getInt(1);
			String name = rs.getString(2);
			String customerId = rs.getString(3);
			int sex = rs.getInt(4);
			String nationality = rs.getString(5);
			
			String nativeProvince = rs.getString(6);
			int idType = rs.getInt(7);
			String idNum = rs.getString(8);
			Timestamp birthday = rs.getTimestamp(9);
			String mobile = rs.getString(10);
			
			String telephone = rs.getString(11);
			String address = rs.getString(12);
			String domicilePlace = rs.getString(13);
			String zipCode = rs.getString(14);
			String email = rs.getString(15);
			
			int userProperties = rs.getInt(16);
			String fax = rs.getString(17);
			int unitType = rs.getInt(18);
			int status = rs.getInt(19);
			int customPoint = rs.getInt(20);
			
			String creation = rs.getString(21);
			Timestamp createTime = rs.getTimestamp(22);
			String lastModifier = rs.getString(23);
			Timestamp lastModifierTime = rs.getTimestamp(24);
			int invoiceType = rs.getInt(25);
			
			String agentName = rs.getString(26);
			int agentIdType = rs.getInt(27);
			String agentIdNum = rs.getString(28);
			String agentMobile = rs.getString(29);
			String agentTelephone = rs.getString(30);
			
			String agentAddress = rs.getString(31);
			int grade = rs.getInt(32);
			int businessType = rs.getInt(33);
			Timestamp businessDate = rs.getTimestamp(34);
			
			
			psInsertUser.setInt(1, id);
			psInsertUser.setString(2, name);
			psInsertUser.setString(3, customerId);
			psInsertUser.setInt(4, sex);
			psInsertUser.setString(5, nationality);
			
			psInsertUser.setString(6, nativeProvince);
			psInsertUser.setInt(7, idType);
			psInsertUser.setString(8, idNum);
			psInsertUser.setTimestamp(9, birthday);
			psInsertUser.setString(10, mobile);
			
			psInsertUser.setString(11, telephone);
			psInsertUser.setString(12, address);
			psInsertUser.setString(13, domicilePlace);
			psInsertUser.setString(14, zipCode);
			psInsertUser.setString(15, email);
			
			psInsertUser.setInt(16, userProperties);
			psInsertUser.setString(17, fax);
			psInsertUser.setInt(18, unitType);
			psInsertUser.setInt(19, status);
			psInsertUser.setInt(20, customPoint);

			psInsertUser.setString(21, creation);
			psInsertUser.setTimestamp(22, createTime);
			psInsertUser.setString(23, lastModifier);
			psInsertUser.setTimestamp(24, lastModifierTime);
			psInsertUser.setString(25, handleDate);
			
			psInsertUser.setString(26, batchNo);
			psInsertUser.setNull(27, Types.INTEGER);
			
		
			/**
			 * 新增
			 */
			if(INSERT == businessType){
				
				/**
				 * 先删除
				 */
				psDeleteUser.setInt(1, id);
				psDeleteUser.executeUpdate();
				
				psInsertUserEx.executeUpdate();
				/**
				 * 再插入
				 */
				psInsertUser.executeUpdate();
				
				psInsertUserEx.setInt(1, id);
				psInsertUserEx.setInt(2, invoiceType);
				psInsertUserEx.setString(3, agentName);
				psInsertUserEx.setInt(4, agentIdType);
				psInsertUserEx.setString(5, agentIdNum);
				
				psInsertUserEx.setString(6, agentMobile);
				psInsertUserEx.setString(7, agentTelephone);
				psInsertUserEx.setString(8, agentAddress);
				psInsertUserEx.setInt(9, grade);
				
				psInsertUserEx.executeUpdate();
				
			}
			/**
			 * 更新
			 */
			else if(UPDATE == businessType){
				/**
				 * 先删除
				 */
				psDeleteUser.setInt(1, id);
				psDeleteUser.executeUpdate();
				
				/**
				 * 再插入
				 */
				psInsertUser.executeUpdate();
				
				psUpdatetUserEx.setInt(1, id);
				psUpdatetUserEx.setInt(2, invoiceType);
				psUpdatetUserEx.setString(3, agentName);
				psUpdatetUserEx.setInt(4, agentIdType);
				psUpdatetUserEx.setString(5, agentIdNum);
				
				psUpdatetUserEx.setString(6, agentMobile);
				psUpdatetUserEx.setString(7, agentTelephone);
				psUpdatetUserEx.setString(8, agentAddress);
				psUpdatetUserEx.setInt(9, grade);
				psUpdatetUserEx.setInt(10, id);
				
				psUpdatetUserEx.executeUpdate();
				
			}
			/**
			 * 删除
			 * 将user状态改为停用
			 */
			else if (DELETE == businessType){
				psUpdateUser.setInt(1, id);
				psUpdateUser.executeUpdate();
			} else {
			}
			/**
			 * 插入到备份表
			 */
			
			psInsertUserHis.setString(1, handleDate);
			psInsertUserHis.setInt(2, businessType);
			psInsertUserHis.setString(3, batchNo);
			psInsertUserHis.setInt(4, id);
			
			psInsertUserHis.executeUpdate();
			
			/**
			 * 清空recv表
			 */
			psDeleteUserRecv.setInt(1, id);
			psDeleteUserRecv.executeUpdate();
			
			queryCount ++;
			
		}
		}catch(SQLException se){
			logger.info("处理"+tableName+"数据过程出现SQL错误...错误原因："+se.getMessage());
			se.printStackTrace();
			throw se;
		}catch(Exception e){
			logger.info("处理"+tableName+"数据过程数据转换处理出现错误...错误原因："+e.getMessage());
			throw e;
		}finally {
			try {
				
				if (null != rs) {
					rs.close();
					rs = null;
				}
				
				if (null != psDeleteUserRecv) {
					psDeleteUserRecv.close();
					psDeleteUserRecv = null;
				}
				
				if (null != psInsertUserHis) {
					psInsertUserHis.close();
					psInsertUserHis = null;
				}
				
				if (null != psUpdatetUserEx) {
					psUpdatetUserEx.close();
					psUpdatetUserEx = null;
				}
				
				if (null != psInsertUserEx) {
					psInsertUserEx.close();
					psInsertUserEx = null;
				}
				
				if (null != psDeleteUser) {
					psDeleteUser.close();
					psDeleteUser = null;
				}
				
				if (null != psUpdateUser) {
					psUpdateUser.close();
					psUpdateUser = null;
				}
				
				if (null != psInsertUser) {
					psInsertUser.close();
					psInsertUser = null;
				}
				
				if (null != statement) {
					statement.close();
					statement = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return queryCount;
	}

	public static int handleCardData(Connection conn,Log logger,String HandleDate,String batchNo) throws Exception {
		
		String tableName = "TB_Card";
		
		
		///)
	   //  VALUES(<id, int,>,<accountId, int,>,<customPoint, int,>,<faceCardNum, varchar(20),>,<phyCardNum, varchar(10),>,<status, int,>,<version, varchar(10),>,<cardType, int,>,<lastBalanceTime, date,>,<vehPlate, varchar(12),>,<bind, int,>,<startDate, date,>,<endDate, date,>,<inBlackListTime, date,>,<obuBind, int,>,<vehiclePlateColor, int,>,<vehicleType, int,>,<lastBalance, float,>,<cardUserType, int,>,<previoustBalance, float,>,<remark, varchar(200),>,<syncTime, date,>,<syncBatchNo, varchar(15),>,<originalCardId, int,>,<creation, varchar(25),>,<createTime, date,>,<lastModifier, varchar(25),>,<lastModifierTime, date,>)
	 //卡号和卡类型不可以变
		/*StringBuffer sqlInsertCardBuf = new StringBuffer();
		sqlInsertCardBuf.append("INSERT INTO TB_Card")
			.append("(id,accountId,customPoint,faceCardNum,phyCardNum")  //5
			.append(",status,version,cardType,lastBalanceTime,vehPlate")  //10
			.append(",bind,startDate,endDate,inBlackListTime,obuBind") 
			.append(",vehiclePlateColor,vehicleType,lastBalance,cardUserType,previoustBalance")//20
			.append(",creation,createTime,lastModifier,lastModifierTime,remark")//25
			.append(",syncTime,syncBatchNo,originalCardId)") //28
			.append(" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");*/
		
	//	PreparedStatement psInsertCard = conn.prepareStatement(sqlInsertCardBuf.toString());
	//	PreparedStatement psUpdateUser = conn.prepareStatement(sqlUpdateUserBuf.toString());
	//	PreparedStatement psDeleteUser = conn.prepareStatement("DELETE FROM TB_User where id=?");
	//	PreparedStatement psInsertUserEx = conn.prepareStatement(sqlInsertUserExBuf.toString());
	//	PreparedStatement psUpdatetUserEx = conn.prepareStatement(sqlUpdateUserExBuf.toString());
	//	PreparedStatement psInsertUserHis = conn.prepareStatement(sqlInsertUserHisBuf.toString());
	//	PreparedStatement psDeleteUserRecv = conn.prepareStatement("DELETE FROM TB_User_Recv where id=?");
		/**
		 * 处理tb_Card
		 */
		/*StringBuffer sqlQueryCardRecvQueryBuf = new StringBuffer();
		sqlQueryCardRecvQueryBuf.append("select")
			.append(" id,accountId,customPoint,faceCardNum,phyCardNum")  //5
			.append(",status,version,cardType,lastBalanceTime,vehPlate")  //10
			.append(",bind,startDate,endDate,inBlackListTime,obuBind") 
			.append(",vehiclePlateColor,vehicleType,lastBalance,cardUserType,creation")
			.append(",createTime,lastModifier,lastModifierTime,remark,BusinessType")
			.append(",BusinessDate")  //26
			.append(" FROM TB_Card_Recv");

		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(sqlQueryCardRecvQueryBuf.toString());*/
		
	/*	PreparedStatement preparedStatement = conn.prepareStatement(queryCardRecvSql);
		Statement statement = conn.createStatement();
		ResultSet rs = preparedStatement.executeQuery();*/
		
		
	//	ResultSet resultSet = null;
		
	//	int queryCount = 0;
		Statement statement = conn.createStatement();
		int queryCount = 0;
		
		try{
			StringBuffer mergeCardBuf = new StringBuffer();
			mergeCardBuf.append("MERGE TB_Card as a USING TB_Card_Recv as b on (a.id=b.id and b.businessType!=4)")
				.append(" when MATCHED THEN UPDATE SET a.accountId = b.accountId,a.customPoint=b.customPoint,a.[status]=b.[status],a.[version]=b.[version],a.lastBalanceTime=b.lastBalanceTime")
				.append(",a.vehPlate=b.vehPlate,a.bind=b.bind,a.startDate=b.startDate,a.endDate=b.endDate,a.inBlackListTime=b.inBlackListTime,a.obuBind=b.obuBind")
				.append(",a.vehiclePlateColor=b.vehiclePlateColor,a.vehicleType=b.vehicleType,a.lastBalance=b.lastBalance,a.cardUserType=b.cardUserType,a.creation=b.creation")
				.append(",a.createTime=b.createTime,a.lastModifier=b.lastModifier,a.lastModifierTime=b.lastModifierTime,a.remark=b.remark")
				.append(" when NOT MATCHED THEN INSERT (id,accountId,customPoint,[status],[version],cardType,lastBalanceTime,vehPlate,bind,startDate,endDate,inBlackListTime,obuBind,vehiclePlateColor,vehicleType,lastBalance,cardUserType,creation,createTime,lastModifier,lastModifierTime,remark)")
				.append(" VALUES(b.id,b.accountId,b.customPoint,b.[status],b.[version],b.cardType,b.lastBalanceTime,b.vehPlate,b.bind,b.startDate,b.endDate,b.inBlackListTime,b.obuBind,b.vehiclePlateColor,b.vehicleType,b.lastBalance,b.cardUserType,b.creation,b.createTime,b.lastModifier,b.lastModifierTime,b.remark);");
			
			
			
			StringBuffer insToHis = new StringBuffer();
			insToHis.append("INSERT INTO TB_Card_His(id,accountId,customPoint,faceCardNum,phyCardNum,[status],[version],cardType,lastBalanceTime,vehPlate,bind,startDate,endDate,inBlackListTime,obuBind,vehiclePlateColor,vehicleType,lastBalance,cardUserType,remark,BusinessType,BusinessDate,HandleDate,HandleFlag,batchNo,creation,createTime,lastModifier,lastModifierTime)")
				.append("select id,accountId,customPoint,faceCardNum,phyCardNum,[status],[version],cardType,lastBalanceTime,vehPlate,bind,startDate,endDate,inBlackListTime,obuBind,vehiclePlateColor,vehicleType,lastBalance,cardUserType,remark,BusinessType,BusinessDate")
				.append(",'").append(HandleDate).append("',1")
				.append(",'").append(batchNo).append("',")
				.append(",creation,createTime,lastModifier,lastModifierTime from TB_Card_Recv");
			
			
			
			queryCount = statement.executeUpdate(mergeCardBuf.toString());
			
			statement.executeUpdate(insToHis.toString());
			
			statement.executeUpdate("update a set a.[status]=1 from TB_Card a inner join TB_Card_Recv b on a.id=b.id where b.businessType=3");
			statement.executeUpdate("delete from TB_Card_Recv");
	//	
	//	while(rs.next()){
			
			/*int id = rs.getInt(1);
			int accountId = rs.getInt(2);
			int customPoint = rs.getInt(3);
			String faceCardNum = rs.getString(4);
			String phyCardNum = rs.getString(5);
			
			int status = rs.getInt(6);
			String version = rs.getString(7);
			int cardType = rs.getInt(8);
			String lastBalanceTime = rs.getString(9);
			String vehPlate = rs.getString(10);
			int bind = rs.getInt(11);
			String startDate = rs.getString(12);
			String endDate = rs.getString(13);
			String inBlackListTime = rs.getString(14);
			int obuBind = rs.getInt(15);
			int vehiclePlateColor = rs.getInt(16);
			int vehicleType = rs.getInt(17);
			String lastBalance = rs.getString(18);
			int cardUserType = rs.getInt(19);
			String creation = rs.getString(20);
			String createTime = rs.getString(21);
			String lastModifier = rs.getString(22);
			String lastModifierTime = rs.getString(23);
			String remark = rs.getString(24);
			int businessType = rs.getInt(25);
			String businessDate = rs.getString(26);*/
		
			   //  VALUES(<id, int,>,<accountId, int,>,<customPoint, int,>,<faceCardNum, varchar(20),>,<phyCardNum, varchar(10),>,<status, int,>,<version, varchar(10),>,<cardType, int,>,<lastBalanceTime, date,>,<vehPlate, varchar(12),>,<bind, int,>,<startDate, date,>,<endDate, date,>,<inBlackListTime, date,>,<obuBind, int,>,<vehiclePlateColor, int,>,<vehicleType, int,>,<lastBalance, float,>,<cardUserType, int,>,<previoustBalance, float,>,<remark, varchar(200),>,<syncTime, date,>,<syncBatchNo, varchar(15),>,<originalCardId, int,>,<creation, varchar(25),>,<createTime, date,>,<lastModifier, varchar(25),>,<lastModifierTime, date,>)
			 
			
//		}
		}catch(SQLException se){
			logger.info("处理"+tableName+"数据过程出现SQL错误...错误原因："+se.getMessage());
			throw se;
			//e.printStackTrace();
		}catch(Exception e){
			logger.info("处理"+tableName+"数据过程数据转换处理出现错误...错误原因："+e.getMessage());
			throw e;
		}finally {
			try {
				
				if (null != statement) {
					statement.close();
					statement = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		return queryCount;
		
	}
	
	public static int handleAccountData(Connection conn, Log logger,
			String HandleDate, String batchNo)
			throws Exception {
		
		String tableName = "TB_Account";

		Statement statement = conn.createStatement();
		
		
		int queryCount = 0;
		
		try{
			
			StringBuffer mergeAccountBuf = new StringBuffer();
			mergeAccountBuf.append("MERGE TB_Account as a USING TB_Account_Recv as b on (a.id=b.id and b.businessType!=4)")
				.append(" when MATCHED THEN UPDATE SET a.userId = b.userId,a.customPoint=b.customPoint,a.accountCode=b.accountCode,a.accountName=b.accountName,a.lastBalance=b.lastBalance")
				.append(",a.lastBalanceTime=b.lastBalanceTime,a.duration=b.duration,a.accountDate=b.accountDate,a.[type]=b.[type],a.credit=b.credit,a.previousCredit=b.previousCredit")
				.append(",a.openBank=b.openBank,a.bankAccount=b.bankAccount,a.bankAccountNo=b.bankAccountNo,a.creation=b.creation,a.createTime=b.createTime")
				.append(",a.lastModifier=b.lastModifier,a.lastModifierTime=b.lastModifierTime,a.remark=b.remark")
				.append(" when NOT MATCHED THEN INSERT (id,userId,customPoint,accountCode,accountName,lastBalance,lastBalanceTime,duration,accountDate,[type],credit,previousCredit,openBank,bankAccount,bankAccountNo,creation,createTime,lastModifier,lastModifierTime,remark)")
				.append(" VALUES(b.id,b.userId,b.customPoint,b.accountCode,b.accountName,b.lastBalance,b.lastBalanceTime,b.duration,b.accountDate,b.[type],b.credit,b.previousCredit,b.openBank,b.bankAccount,b.bankAccountNo,b.creation,b.createTime,b.lastModifier,b.lastModifierTime,b.remark);");
			
			
			StringBuffer insToHis = new StringBuffer();
			insToHis.append("INSERT INTO TB_Account_His(id,userId,customPoint,accountCode,accountName,lastBalance,lastBalanceTime,duration,accountDate,[type],credit,previousCredit,openBank,bankAccount,bankAccountNo,creation,createTime,lastModifier,lastModifierTime,remark,BusinessDate,BusinessType,HandleDate,HandleFlag,batchNo)")
				.append("select id,userId,customPoint,accountCode,accountName,lastBalance,lastBalanceTime,duration,accountDate,[type],credit,previousCredit,openBank,bankAccount,bankAccountNo,creation,createTime,lastModifier,lastModifierTime,remark,BusinessDate,BusinessType")
				.append(",'").append(HandleDate).append("',1")
				.append(",'").append(batchNo).append("',")
				.append(" from TB_Account_Recv");
			
			
			
			queryCount = statement.executeUpdate(mergeAccountBuf.toString());
			
			statement.executeUpdate(insToHis.toString());
			
			statement.executeUpdate("delete from TB_Card_Recv");
		
		
		}catch(SQLException se){
			logger.info("处理"+tableName+"数据过程出现SQL错误...错误原因："+se.getMessage());
			throw se;
			//e.printStackTrace();
		}catch(Exception e){
			logger.info("处理"+tableName+"数据过程数据转换处理出现错误...错误原因："+e.getMessage());
			throw e;
		}finally {
			try {
				if (null != statement) {
					statement.close();
					statement = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return queryCount;
		
	}
	

	public static int handleCardBlackListData(Connection conn, Log logger,
			String HandleDate, String batchNo)
			throws SQLException {
		
		String cardBlackListRecvQuerySql = "select faceCardNum, CardType, ValidFlag from TB_CardBlackList_Recv where 1=1 ";
		
		StringBuffer sqlInsHisBuf = new StringBuffer();
		sqlInsHisBuf.append("INSERT INTO TB_CardBlackList_His(id,faceCardNum,cardType,blackType,occurTime,expireTime,validFlag,registerOrgId,registerOperatorId,spare1,spare2,remark,[version])")
			.append(" select id,faceCardNum,cardType,blackType,occurTime,expireTime,validFlag,registerOrgId,registerOperatorId,spare1,spare2,remark,[version]")
			.append(" from TB_CardBlackList_Recv");
		
		StringBuffer sqlInsCBLBuf = new StringBuffer();
		sqlInsCBLBuf.append("INSERT INTO TB_CardBlackList(id,faceCardNum,cardType,blackType,occurTime,expireTime,validFlag,registerOrgId,registerOperatorId,spare1,spare2,remark,[version])")
			.append(" select id,faceCardNum,cardType,blackType,occurTime,expireTime,validFlag,registerOrgId,registerOperatorId,spare1,spare2,remark,[version]")
			.append(" from TB_CardBlackList_Recv where faceCardNum =? and cardType=?");
		
		PreparedStatement psDelCard = conn.prepareStatement("delete TB_CardBlackList where 1=1 and faceCardNum =? and cardType=?");
//		PreparedStatement psDelCardRecv = conn.prepareStatement("delete TB_CardBlackList_Recv where 1=1 and faceCardNum =? and cardType=?");
//		PreparedStatement psInsHis = conn.prepareStatement(sqlInsHisBuf.toString());
		PreparedStatement psInsCBL = conn.prepareStatement(sqlInsCBLBuf.toString());
		
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(cardBlackListRecvQuerySql);
		
		int queryCount = 0;
		
		try{
		
		while(rs.next()){
			
			queryCount++;
			
			String faceCardNum = rs.getString(1);
			int cardType = rs.getInt(2);
			int validFlag = rs.getInt(3);
			
			//黑名单无效，消除
			if(validFlag == 0){
				
				psDelCard.setString(1, faceCardNum);
				psDelCard.setInt(2, cardType);
				
				/**
				 * 删除TB_CardBlackList表
				 */
				psDelCard.executeUpdate();
			}
			//黑名单新增
			else if(validFlag == 1){
				
				psDelCard.setString(1, faceCardNum);
				psDelCard.setInt(2, cardType);
				
				/**
				 * 删除TB_CardBlackList表
				 */
				psDelCard.executeUpdate();
				
				psInsCBL.setString(1, faceCardNum);
				psInsCBL.setInt(2, cardType);
				/**
				 * 插入到TB_CardBlackList表
				 */
				psInsCBL.executeUpdate();
				
			}
		}
		
		/**
		 * 插入到备份表
		 */
		statement.execute(sqlInsHisBuf.toString());
		/**
		 * 删除临时表
		 */
		statement.execute("delete from TB_CardBlackList_Recv");
		
		return queryCount;
		}catch(SQLException e){
			logger.info("处理TB_CardBlackList_Recv数据过程出现SQL错误...错误原因："+e.getMessage());
			throw e;
		}finally {
			try {
				
				if (null != rs) {
					rs.close();
					rs = null;
				}
				
				if (null != psDelCard) {
					psDelCard.close();
					psDelCard = null;
				}
				
				if (null != psInsCBL) {
					psInsCBL.close();
					psInsCBL = null;
				}
				
				if (null != statement) {
					statement.close();
					statement = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
	}

	public static int handleObuData(Connection conn, Log logger,
			String HandleDate, String batchNo)
			throws Exception {

		String tableName = "TB_OBU";
		Statement statement = conn.createStatement();
		
		int queryCount = 0;
		
		try{
			
			StringBuffer mergeObuBuf = new StringBuffer();
			mergeObuBuf.append("MERGE TB_OBU as a USING TB_OBU_Recv as b on (a.serialNumber=b.serialNumber and b.businessType!=4)")
				.append(" when MATCHED THEN UPDATE SET a.vehicleClass = b.vehicleClass,a.vehiclePlate=b.vehiclePlate,a.vehiclePlateColor=b.vehiclePlateColor,a.vehicleUserType=b.vehicleUserType,a.serialNumber=b.serialNumber")
				.append(",a.vehicleEnginNumber=b.vehicleEnginNumber,a.vehicleAxles=b.vehicleAxles,a.vehicleWheels=b.vehicleWheels,a.vehicleWheelBases=b.vehicleWheelBases,a.vehicleWeightLimits=b.vehicleWeightLimits,a.vehicleWidth=b.vehicleWidth")
				.append(",a.vehicleLong=b.vehicleLong,a.vehicleHeight=b.vehicleHeight,a.protectFilm=b.protectFilm,a.vehicleSpecificInformation=b.vehicleSpecificInformation,a.supplier=b.supplier")
				.append(",a.contractType=b.contractType,a.contractVersion=b.contractVersion,a.contractSerial=b.contractSerial,a.dateOfIssue=b.dateOfIssue,a.dateOfExpire=b.dateOfExpire")
				.append(",a.userId=b.userId,a.customPoint=b.customPoint,a.[status]=b.[status],a.creation=b.creation,a.createTime=b.createTime,a.lastModifier=b.lastModifier,a.lastModifierTime=b.lastModifierTime")
				.append(" when NOT MATCHED THEN INSERT (id,obuId,vehicleClass,vehiclePlate,vehiclePlateColor,vehicleUserType,serialNumber,vehicleEnginNumber,vehicleAxles,vehicleWheels,vehicleWheelBases,vehicleWeightLimits,vehicleWidth,vehicleLong,vehicleHeight,protectFilm,vehicleSpecificInformation,supplier,contractType,contractVersion,contractSerial,dateOfIssue,dateOfExpire,userId,customPoint,status,creation,createTime,lastModifier,lastModifierTime)")
				.append(" VALUES(b.id,b.obuId,b.vehicleClass,b.vehiclePlate,b.vehiclePlateColor,b.vehicleUserType,b.serialNumber,b.vehicleEnginNumber,b.vehicleAxles,b.vehicleWheels,b.vehicleWheelBases,b.vehicleWeightLimits,b.vehicleWidth,b.vehicleLong,b.vehicleHeight,b.protectFilm,b.vehicleSpecificInformation,b.supplier,b.contractType,b.contractVersion,b.contractSerial,b.dateOfIssue,b.dateOfExpire,b.userId,b.customPoint,b.status,b.creation,b.createTime,b.lastModifier,b.lastModifierTime);");
			
			
			StringBuffer insToHis = new StringBuffer();
			insToHis.append("INSERT INTO TB_OBU_His(id,obuId,vehicleClass,vehiclePlate,vehiclePlateColor,vehicleUserType,serialNumber,vehicleEnginNumber,vehicleAxles,vehicleWheels,vehicleWheelBases,vehicleWeightLimits,vehicleWidth,vehicleLong,vehicleHeight,protectFilm,vehicleSpecificInformation,supplier,contractType,contractVersion,contractSerial,dateOfIssue,dateOfExpire,userId,customPoint,[status],creation,createTime,lastModifier,lastModifierTime,BusinessType,BusinessDate,HandleDate,HandleFlag,batchNo)")
				.append("select id,obuId,vehicleClass,vehiclePlate,vehiclePlateColor,vehicleUserType,serialNumber,vehicleEnginNumber,vehicleAxles,vehicleWheels,vehicleWheelBases,vehicleWeightLimits,vehicleWidth,vehicleLong,vehicleHeight,protectFilm,vehicleSpecificInformation,supplier,contractType,contractVersion,contractSerial,dateOfIssue,dateOfExpire,userId,customPoint,[status],creation,createTime,lastModifier,lastModifierTime,BusinessType,BusinessDate")
				.append(",'").append(HandleDate).append("',1")
				.append(",'").append(batchNo).append("'")
				.append(" from TB_OBU_Recv");
			
			
			
			queryCount = statement.executeUpdate(mergeObuBuf.toString());
			
			statement.executeUpdate("delete from TB_OBU where exists(select 1 from TB_OBU_Recv b where TB_OBU.serialNumber=b.serialNumber and b.businessType=3)");
			statement.executeUpdate(insToHis.toString());
			
			statement.executeUpdate("delete from TB_Card_Recv");
		
		}catch(SQLException se){
			logger.info("处理"+tableName+"数据过程出现SQL错误...错误原因："+se.getMessage());
			throw se;
			//e.printStackTrace();
		}catch(Exception e){
			logger.info("处理"+tableName+"数据过程数据转换处理出现错误...错误原因："+e.getMessage());
			throw e;
		}finally
		{
			if(statement != null)
			{
				statement.close();
				statement = null;
			}
		}
		return queryCount;
	}


/**
	 * 处理TB_TradeList
	 */
	public static int handleTradeListData(Connection conn, Log logger,
			String HandleDate, String batchNo, int organization)
			throws SQLException {
		/**
		 * 写入到TB_TradeList_His
		 */
		String insertHisSql = "insert into TB_TradeList_His (OrgID, ListNo, [User], Account, " +
				"Card, CardFaceNo, area, customPoint, OperatorNo, OperatorName, TradeType, " +
				"TradeAmount, Banlance, TradeTime, TradeNo, TerminalID, TermTradeNo, CRN, TAC, " +
				"MAC2, Remark, BusinessDate, HandleDate, HandleFlag, batchNo) " +
				" select OrgID, ListNo, [user], Account, Card, CardFaceNo, area, customPoint, " +
				"OperatorNo, OperatorName, TradeType, TradeAmount, Banlance, tradeTime, TradeNo, " +
				"TerminalID, TermTradeNo, CRN, TAC, MAC2, Remark, " +
				"BusinessDate,'"+HandleDate+"',1,'"+batchNo+"' from TB_TradeList_Recv where 1=1 ";
		
		/**
		 * 删除临时表的sql
		 */
		String deleteRecvSql = "delete TB_TradeList_Recv where 1=1 ";
		
		String countSql = "select count(1) as count from TB_TradeList_Recv where 1=1 ";

		Statement statement = conn.createStatement();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		
		int accountId = 0;
		int cardId = 0;
		int banlance = 0;
		int tradeAmount = 0;
		Date tradeTime = null;
		/**
		 * 长沙银行
		 */
		if(organization == 6){
			insertHisSql += " and OrgID = 313 ";
			deleteRecvSql += " and OrgID = 313 ";
			countSql += " and OrgID = 313 ";
			
			/**
			 * 查询_Recv数据表中的所有数据
			 */
			String queryTradeListRecvSql = "select Account,Card,Banlance,TradeAmount,tradeTime from TB_TradeList_Recv where 1 = 1 and OrgID = 313  order by tradeTime asc ";
			/**
			 * 后台入库平账
			 */
			ResultSet rs = statement.executeQuery(queryTradeListRecvSql);
			
			while(rs.next()){
				accountId = rs.getInt(1);
				cardId = rs.getInt(2);
				banlance = rs.getInt(3);
				tradeAmount = rs.getInt(4);
				tradeTime = rs.getTimestamp(5);
				
				String tradeTimeStr = sf.format(tradeTime);
				
				Statement st = conn.createStatement();
				
				if(banlance == 0){
					st.addBatch("update TB_Account_BCS set balance = (balance+("+tradeAmount+"*0.01)),lastBalanceTime = '"+tradeTimeStr+"' where id = "+accountId);
					st.addBatch("update TB_Card_BCS set lastBalance = (lastBalance+("+tradeAmount+"*0.01)),lastBalanceTime = '"+tradeTimeStr+"' where id = "+cardId);

				}else{
					
					st.addBatch("update TB_Account_BCS set balance = "+banlance+"*0.01,lastBalanceTime = '"+tradeTimeStr+"' where id = "+accountId);
					st.addBatch("update TB_Card_BCS set lastBalance = "+banlance+"*0.01,lastBalanceTime = '"+tradeTimeStr+"' where id = "+cardId);
					
				}
				
				st.executeBatch();
				
				st.close();
			}
		}
		
		
		int queryCount = 0;
		
		ResultSet resultSet = conn.prepareStatement(countSql).executeQuery();
		while(resultSet.next()){
			queryCount = resultSet.getInt(1);
		}
		resultSet = null;
		
		try{
		/**
		 * 插入到备份表
		 */
		statement.execute(insertHisSql);
		/**
		 * 删除临时表
		 */
		statement.execute(deleteRecvSql);
		}catch(SQLException e){
			logger.info("处理TB_TradeList_Recv数据过程出现SQL错误...错误原因："+e.getMessage());
			throw e;
		}finally {
			try {
				if (null != resultSet) {
					resultSet.close();
					resultSet = null;
				}
				
				if (null != statement) {
					statement.close();
					statement = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return queryCount;
		
	}
	/**
	 * 处理TB_BusinessSum
	 */
	public static int handleBusinessSumData(Connection conn, Log logger,
			String HandleDate, String batchNo, int organization)
			throws SQLException {
		/**
		 * 写入到TB_InvoiceStock_His
		 */
		String insertHisSql = "insert into TB_BusinessSum_His (OrgID, BusinessDate, " +
				"UserBusinessCnt, AccountBusinessCnt, CardBusinessCnt, ObuBusinessCnt, " +
				"TradeListCnt, DayEndVerifyCnt, CardBlackList, InvoiceStockCnt, Remark, " +
				"HandleDate, HandleFlag, batchNo) " +
				" select OrgID, BusinessDate, UserBusinessCnt, AccountBusinessCnt, " +
				"CardBusinessCnt, ObuBusinessCnt, TradeListCnt, DayEndVerifyCnt, CardBlackList, " +
				"InvoiceStockCnt, Remark,'"+HandleDate+"',1,'"+batchNo+"' from TB_BusinessSum_Recv where 1=1 ";
		
		/**
		 * 删除临时表的sql
		 */
		String deleteRecvSql = "delete TB_BusinessSum_Recv where 1=1 ";
		
		String countSql = "select count(1) as count from TB_BusinessSum_Recv where 1=1 ";
		
		/**
		 * 建设银行
		 */
		if(organization == 10){
			insertHisSql += " and OrgID = 105 ";
			deleteRecvSql += " and OrgID = 105 ";
			countSql += " and OrgID = 105 ";
		}
		/**
		 * 长沙银行
		 */
		else if(organization == 6){
			insertHisSql += " and OrgID = 313 ";
			deleteRecvSql += " and OrgID = 313 ";
			countSql += " and OrgID = 313 ";
		}
		/**
		 * 招商银行
		 */
		else if(organization == 5){
			insertHisSql += " and OrgID = 308 ";
			deleteRecvSql += " and OrgID = 308 ";
			countSql += " and OrgID = 308 ";
		}
		/**
		 * 工商银行
		 */
		else if(organization == 4){
			insertHisSql += " and OrgID = 102 ";
			deleteRecvSql += " and OrgID = 102 ";
			countSql += " and OrgID = 102 ";
		}
		
		Statement statement = conn.createStatement();
		
		int queryCount = 0;
		
		ResultSet resultSet = conn.prepareStatement(countSql).executeQuery();
		while(resultSet.next()){
			queryCount = resultSet.getInt(1);
		}
		resultSet = null;
		
		try{
		/**
		 * 插入到备份表
		 */
		statement.execute(insertHisSql);
		/**
		 * 删除临时表
		 */
		statement.execute(deleteRecvSql);
		}catch(SQLException e){
			logger.info("处理TB_BusinessSum_Recv数据过程出现SQL错误...错误原因："+e.getMessage());
			throw e;
		}finally {
			try {
				if (null != resultSet) {
					resultSet.close();
					resultSet = null;
				}
				
				if (null != statement) {
					statement.close();
					statement = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return queryCount;
		
	}

	public static int handleParkConsumptionData(Connection conn, Log logger,
			String handleDate, String batchNo) throws Exception {
		
		
		ResultSet rs = null;
		Statement statement = null;
		PreparedStatement psInsHead = null;
		PreparedStatement psInsSubHead = null;
		PreparedStatement psInsParkDetail = null;
		PreparedStatement psInsConsRecv = null;
		PreparedStatement psInsParkCollect = null;
		
		int subHeadNum = 0;
		int headPackageNum = 0;
//		int diffValueNum = 0;
		
		int handleNum = 0;
		
		statement = conn.createStatement();
		
		rs = statement.executeQuery("select packageNum from TB_Park_Consumption_Head_Recv");
		if(rs.next()) {
			headPackageNum = rs.getInt(1);
		} else {
			logger.error("文件包头不存在");
			throw new Exception("文件包头不存在");
		}
		
		rs = statement.executeQuery("select COUNT(1) from TB_Park_Cons_SubHead_Recv");
		if(rs.next()) {
			subHeadNum = rs.getInt(1);
		}
		
		if (subHeadNum != headPackageNum) {
			logger.error("子包头数量校验不通过");
			throw new Exception("子包头数量校验不通过");
		}
		
		rs = statement.executeQuery("select (isnull(a.recordNum,0)-b.detailNum) as errNum from (select recordNum from TB_Park_Cons_SubHead_Recv where packageType='1001') a full join (select COUNT(1) as detailNum from TB_Park_Consumption_Detail_Recv) b on 1=1");
		if (rs.next() && (rs.getInt(1) != 0)) {
			logger.error("流水明细记录数与子包头记录数不一致");
			throw new Exception("流水明细记录数与子包头记录数不一致");
		}
		
		rs = statement.executeQuery("select (isnull(a.recordNum,0)-b.collectlNum) as errNum from (select recordNum from TB_Park_Cons_SubHead_Recv where packageType='1002') a full join (select COUNT(1) as collectlNum from TB_Park_Cons_Collect_Recv) b on 1=1");
		if (rs.next() && (rs.getInt(1) != 0)) {
			logger.error("汇总信息记录数与子包头记录数不一致");
			throw new Exception("汇总信息记录数与子包头记录数不一致");
		}
			
		rs = statement.executeQuery("select COUNT(1) from (select ISNULL(CAST(a.gross as decimal(20,2)),0)-ISNULL(b.payAmt,0) as diffvalue from TB_Park_Cons_Collect_Recv a full join (select squadDate,icType,sum(CAST(payAmt as decimal(20,2))) as payAmt from TB_Park_Consumption_Detail_Recv group by squadDate,icType) b on a.squadDate=b.squadDate and a.icType=b.icType) c where diffvalue<>0");
		if(rs.next() && (rs.getInt(1) != 0)) {
			logger.error("小区停车场流水明细的汇总金额与文件中的汇总金额校验不通过");
			throw new Exception("小区停车场流水明细的汇总金额与文件中的汇总金额校验不通过");
		}
		
		StringBuffer sqlInsHead = new StringBuffer();
		sqlInsHead.append("INSERT INTO TB_Park_Consumption_Head(id,fileType,[fileName],sendCode,receiveCode,transmissionDate,packageNum,HandleDate,batchNo)")
			.append(" select id,fileType,[fileName],sendCode,receiveCode,transmissionDate,packageNum,?,? from TB_Park_Consumption_Head_Recv");
		
		StringBuffer sqlInsSubHead = new StringBuffer();
		sqlInsSubHead.append("INSERT INTO TB_Park_Consumption_SubHead(id,packageType,recordNum,operateType,HandleDate,batchNo)")
			.append(" select id,packageType,recordNum,operateType,?,? from TB_Park_Cons_SubHead_Recv");
		
		StringBuffer sqlInsParkDetail = new StringBuffer();
		sqlInsParkDetail.append("INSERT INTO TB_Park_Consumption_Detail(id,listno,detailNo,icCode,icType,entry,enTime,export,exTime,expType,payAmt,tollBala,trSeq,termCode,termSeq,tac,ownerCode,stopPlaceCode,squadDate,HandleDate,batchNo)")
			.append(" select id,listno,detailNo,icCode,icType,entry,enTime,export,exTime,expType,payAmt,tollBala,trSeq,termCode,termSeq,tac,mechineCode,stopPlaceCode,squadDate,?,?")
			.append(" from TB_Park_Consumption_Detail_Recv");
		
		StringBuffer sqlInsConsRecv = new StringBuffer();
		sqlInsConsRecv.append("INSERT INTO TB_Consumption_Recv(id,listno,detailNo,icCode,icType,entry,enTime,export,exTime,expType,toll,tollBala,trSeq,termCode,termSeq,tac,ownerCode,stopPlaceCode,squadDate)")
			.append(" select id,listno,detailNo,icCode,icType,entry,?,export,?,expType,?,?,trSeq,termCode,termSeq,tac,mechineCode,stopPlaceCode,?")
			.append(" from TB_Park_Consumption_Detail_Recv where id=?");
		
		StringBuffer sqlInsParkCollect= new StringBuffer();
		sqlInsParkCollect.append("INSERT INTO TB_Park_Consumption_Collect(id,listno,startTime,endTime,icType,ownerCode,gross,squadDate,HandleDate,batchNo)")
			.append(" select id,listno,startTime,endTime,icType,mechineCode,gross,squadDate,?,? from TB_Park_Cons_Collect_Recv");
		
		
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
		
		
		
		psInsHead = conn.prepareStatement(sqlInsHead.toString());
		psInsHead.setString(1, handleDate);
		psInsHead.setString(2, batchNo);
		handleNum += psInsHead.executeUpdate();
		
		psInsSubHead = conn.prepareStatement(sqlInsSubHead.toString());
		psInsSubHead.setString(1, handleDate);
		psInsSubHead.setString(2, batchNo);
		handleNum += psInsSubHead.executeUpdate();
		
		psInsParkDetail = conn.prepareStatement(sqlInsParkDetail.toString());
		psInsParkDetail.setString(1, handleDate);
		psInsParkDetail.setString(2, batchNo);
		handleNum += psInsParkDetail.executeUpdate();
		
		psInsParkCollect = conn.prepareStatement(sqlInsParkCollect.toString());
		psInsParkCollect.setString(1, handleDate);
		psInsParkCollect.setString(2, batchNo);
		handleNum += psInsParkCollect.executeUpdate();
		
		psInsConsRecv = conn.prepareStatement(sqlInsConsRecv.toString());
		
		rs = statement.executeQuery("select id,enTime,exTime,payAmt,tollBala,squadDate from TB_Park_Consumption_Detail_Recv");
		while(rs.next()) {
			int id = rs.getInt(1);
			Timestamp enTime = new Timestamp(sdf.parse(rs.getString(2)).getTime());
			Timestamp exTime = new Timestamp(sdf.parse(rs.getString(3)).getTime());
			String payAmt = rs.getString(4);
			String tollBala = rs.getString(5);
			Timestamp squadDate = new Timestamp(sdf.parse(rs.getString(6)).getTime());
			
			psInsConsRecv.setTimestamp(1, enTime);
			psInsConsRecv.setTimestamp(2, exTime);
			psInsConsRecv.setString(3, payAmt);
			psInsConsRecv.setString(4, tollBala);
			psInsConsRecv.setTimestamp(5, squadDate);
			psInsConsRecv.setInt(6, id);
			
			psInsConsRecv.executeUpdate();
			
		}
		
		statement.addBatch("delete from TB_Park_Consumption_Head_Recv");
		statement.addBatch("delete from TB_Park_Cons_SubHead_Recv");
		statement.addBatch("delete from TB_Park_Consumption_Detail_Recv");
		statement.addBatch("delete from TB_Park_Cons_Collect_Recv");
		statement.executeBatch();
		
		if (null != rs) {
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		if (null != statement) {
			try {
				statement.close();
				statement = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		if (null != psInsConsRecv) {
			try {
				psInsConsRecv.close();
				psInsConsRecv = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (null != psInsParkCollect) {
			try {
				psInsParkCollect.close();
				psInsParkCollect = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (null != psInsParkDetail) {
			try {
				psInsParkDetail.close();
				psInsParkDetail = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (null != psInsSubHead) {
			try {
				psInsSubHead.close();
				psInsSubHead = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (null != psInsHead) {
			try {
				psInsHead.close();
				psInsHead = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return handleNum;
	}
	

}
