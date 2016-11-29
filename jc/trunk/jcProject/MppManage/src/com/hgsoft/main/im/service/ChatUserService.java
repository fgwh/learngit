package com.hgsoft.main.im.service;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Service;

import com.hgsoft.main.im.action.ChatEndpoint;
import com.hgsoft.main.im.action.CnToSpell;
import com.hgsoft.main.im.dao.TbUseMsgDao;
import com.hgsoft.main.im.entity.TbUseMsgEntity;
import com.hgsoft.security.service.BaseService;
import com.hgsoft.util.Pager;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
/**
 *Created by linlin on 2015/03/03/0003.
 */
public class ChatUserService extends BaseService<TbUseMsgEntity> {
	private static final int BUFFER_SIZE = 16 * 1024;
	@Resource
	TbUseMsgDao tbUseMsgDao;

	/**
	 * 查询用户以及相应的经纬度、未读消息数目
	 */
	public List queryUserAndLocation(String id, Pager pager) {
		String sql = "select -1 as num,a1.id,a1.name,isnull(a2.longitude,0) as longitude,isnull(a2.latitude,0) as latitude ,sa.StationName,0 from sys_admin as a1  left join  TB_StaffLocation as a2  on a2.operatorNo = a1.id  and a2.uploadTime=(select MAX(a.uploadTime) from TB_StaffLocation as a where a.operatorNo = a1.id)   left join Tb_Square as sq on a1.squareNo = sq.SquareNo left join Tb_Station as sa on sa.StationNo = sq.StationNo where a1.id ="
				+ id
				+ " union "
				+ "select a1.id as num,a1.id,a1.name,isnull(a2.longitude,0) as longitude,isnull(a2.latitude,0) as latitude,sa.StationName,count(us.msgid) as msgCount from sys_admin as a1  left join  TB_StaffLocation as a2 on a2.operatorNo = a1.id and a2.uploadTime=(select MAX(a.uploadTime) from TB_StaffLocation as a where a.operatorNo = a1.id)  left join Tb_Square as sq on a1.squareNo = sq.SquareNo left join Tb_Station as sa on sa.StationNo = sq.StationNo left join TB_useMsg as us on us.senderId = a1.id and us.receiverId = "
				+ id
				+ " and us.isread=0 where not exists (select id from sys_admin where a1.id ="
				+ id
				+ ") group by a1.id,a1.name,longitude,latitude,StationName";
		return tbUseMsgDao.queryUserAndLocation(sql, pager);
	}

	/**
	 * 得到在线用户id列表
	 */
	public static List getOnlineID(javax.websocket.Session session) {
		return new ArrayList(ChatEndpoint.sessions.keySet());
	}

	/**
	 * 获得聊天记录
	 */
	public String getChatRecord(String UserID, String TargeID, Pager pager) {
		List list = new ArrayList();// 装转换后的结果集
		List Temp = tbUseMsgDao.findBySql(
				" select count(*) from (select msg from TB_useMsg where senderId = "
						+ UserID + " and receiverId =" + TargeID + " union "
						+ " select msg from TB_useMsg where senderId = "
						+ TargeID + " and receiverId =" + UserID + ") a", null);
		pager.setTotalSize(Integer.parseInt(Temp.get(0).toString()));
		List msgResult = tbUseMsgDao
				.findBySql(
						" select * from ( select msg,convert(varchar,createTime,120)+datename(weekday, createTime) as createTime,sender,senderId,receiver,receiverId, contentType from TB_useMsg where senderId = "
								+ UserID
								+ " and receiverId ="
								+ TargeID
								+ " union "
								+ " select msg,convert(varchar,createTime,120)+datename(weekday, createTime) as createTime,sender,senderId,receiver,receiverId, contentType from TB_useMsg where senderId = "
								+ TargeID
								+ " and receiverId ="
								+ UserID
								+ ")a order by a.createTime desc", pager);

		for (int i = 0; i < msgResult.size(); i++) {// 结果集转换为List
			list.add((Object[]) msgResult.get(i));
		}
		return JSONArray.fromObject(list).toString();
	}

	/**
	 * 更新未读消息状态到已读
	 */
	public void UpdateIsRead(String senderID, String receiverID) {
		tbUseMsgDao.updateBySql("update TB_useMsg set isread=1 where senderId="
				+ senderID + " and receiverId=" + receiverID);
	}

	public void saveMsg(TbUseMsgEntity tbUseMsgEntity) {
		tbUseMsgDao.save(tbUseMsgEntity);
	}

	/**
	 * 获得所有用户信息及距离
	 */
	public String getUserInfo(List dbUsers, List onlineUserIdList,
			double[] position) {
		Object[] oneUser;
		List userInfo;// 单个用户信息
		List allUserInfo = new ArrayList();// 全部用户信息
		for (int i = 1; i < dbUsers.size(); i++) {
			userInfo = new ArrayList();
			oneUser = (Object[]) dbUsers.get(i);
			userInfo.add(oneUser[1].toString());// id
			userInfo.add(oneUser[2]);// 名字
			userInfo.add(CnToSpell.getFullSpell(oneUser[2].toString().trim()));// 名字缩写
			userInfo.add(oneUser[5]);// 收费站
			if (onlineUserIdList.contains(oneUser[1].toString())) {// 在线
				userInfo.add("在线");
				userInfo.add("距离"
						+ String.valueOf(ChatUtility.GetDistance(position[0],
								position[1], (Double) oneUser[3],
								(Double) oneUser[4])) + "米");
			} else {
				userInfo.add("离线");
				userInfo.add("距离未知");
			}
			userInfo.add(oneUser[6]);
			allUserInfo.add(userInfo);
		}
		Collections.sort(allUserInfo, new ComparatorUser());// 在线排序
		return JSONArray.fromObject(allUserInfo).toString();// 转json
	}

	public class ComparatorUser implements Comparator {// list排序
		@Override
		public int compare(Object arg0, Object arg1) {
			List A = (ArrayList) arg0;
			List B = (ArrayList) arg1;
			if (A.get(4) == B.get(4)) {// 在线状态相同,根据名字缩写排序
				return A.get(2).toString().compareTo(B.get(2).toString());
			}
			if ("在线".equals(A.get(4))) {// 在线的优先排序
				return -1;
			}
			return 1;
		}
	}

	/**
	 * 未读消息的数量
	 * 
	 * @param id
	 * @return
	 */
	public List<Object[]> getIsReadChatUserCount(String id) {
		StringBuffer sql = new StringBuffer(
				" from TB_useMsg where receiverId = '"+id+"'");
		String execSql = "select SUM(case isread when 0 then 1 else 0 end) readCount\n "
				+ sql.toString();
		List<Object[]> list = (List<Object[]>) tbUseMsgDao.findBySql(execSql,
				null);

		return list;
	}

	@Resource
	private GroupMsgStatusService groupMsgStatusService;

	/**
	 * 统计所有未读消息的数量
	 * 
	 * @param id
	 * @return
	 */
	public int getIsReadCount(String id) {
		List userList = this.getIsReadChatUserCount(id);
		List groupList = groupMsgStatusService.getIsReadGroupCount(id);

		Integer oneCount = 0;
		Integer twoCount = 0;
		if (userList != null && userList.size() > 0) {
			oneCount = (Integer) userList.get(0);
		}

		if (groupList != null && groupList.size() > 0) {
			twoCount = (Integer) groupList.get(0);
		}

		return oneCount.intValue() + twoCount.intValue();
	}

	// 音频转码arm--->mp3
	public static void changeToMp3(String sourcePath, String targetPath) {
		File source = new File(sourcePath);
		File target = new File(targetPath);
		AudioAttributes audio = new AudioAttributes();
		Encoder encoder = new Encoder();

		audio.setCodec("libmp3lame");
		EncodingAttributes attrs = new EncodingAttributes();
		attrs.setFormat("mp3");
		attrs.setAudioAttributes(audio);

		try {
			encoder.encode(source, target, attrs);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InputFormatException e) {
			e.printStackTrace();
		} catch (EncoderException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 文件复制
	 * 
	 * @param s
	 * @param t
	 */
	public void fileChannelCopy(File s, File t) {

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

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				fi.close();

				in.close();

				fo.close();

				out.close();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

	}

	/**
	 * 文件的读写
	 * 
	 * @param src
	 *            源文件
	 * @param dst
	 *            目标文件
	 */
	public static void copy(File src, File dst) {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(dst),
					BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
