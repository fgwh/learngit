package com.hgsoft.main.im.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.websocket.Session;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.hgsoft.main.im.action.ChatEndpoint;
import com.hgsoft.main.im.entity.ChatMessage;
import com.hgsoft.main.im.entity.Group;
import com.hgsoft.main.im.entity.GroupMember;
import com.hgsoft.main.im.entity.GroupMsg;
import com.hgsoft.main.im.entity.GroupMsgStatus;
import com.hgsoft.main.im.entity.TbUseMsgEntity;
import com.hgsoft.security.service.BaseService;
import com.hgsoft.util.DateUtil;
import com.hgsoft.util.Pager;

/**
 * Created by linl on 2015/03/03/0003.
 */
@Service
public class MessageOperateService extends BaseService {
	private static ChatUserService chatUserService;
	private final static String ROW_GROUP = "####";
	private final static String ROW_PART = "&&&&";
    private static GroupService groupService;
    private static GroupMemberService groupMemberService;
    private static GroupMsgService groupMsgService;
    private static GroupMsgStatusService groupMsgStatusService;
    

    @Resource
    public void setChatUserService( ChatUserService chatUserService){
        this.chatUserService = chatUserService;
    }
    
    @Resource
    public void setGroupService(GroupService groupService){
        this.groupService = groupService;
    }
    
    @Resource
    public void setGroupMemberService(GroupMemberService groupMemberService){
        this.groupMemberService = groupMemberService;
    }
    
    @Resource
    public void setGroupMsgService(GroupMsgService groupMsgService){
        this.groupMsgService = groupMsgService;
    }
    
    @Resource
    public void setGroupMsgStatusService(GroupMsgStatusService groupMsgStatusService){
        this.groupMsgStatusService = groupMsgStatusService;
    }
    /**
     *根据接受者(Id/Id串)发送消息
     */
    public static void sendMsg(ChatMessage msg) {
        try {
            for (String id : msg.getRecipientId().split(",")) {
                if (ChatEndpoint.sessions.get(id) != null) {
                    ((Session) ChatEndpoint.sessions.get(id)).getBasicRemote().sendObject(msg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("返回消息错误");
        }
    }

    public static void dealOnAndOffLine(String type,String id){
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSender("System");
        chatMessage.setSendId(id);
        chatMessage.setMessageContent(type);
        chatMessage.setTime(DateUtil.fromatDate(new Date(), "yyyy-mm-dd HH:mm:ss E").toString());
        chatMessage.setRecipientId(StringUtils.join(ChatEndpoint.sessions.keySet(), ","));
        chatMessage.setRecipientor("All");
        chatMessage.setMessageType("onAndOffline");
        chatMessage.setSingleOrGroup(ChatEndpoint.GROUP);
        sendMsg(chatMessage);
    }

    /*********内部接口与内部类************/
    public interface DealWithMessage{
        public  void saveMessage();
        public boolean parsingMessage();
        public void setMsg(ChatMessage msg,Session session);
    }

    /**
     * 处理上线
     */
    public static class dealOnAndOffLine implements DealWithMessage{
        public ChatMessage msg;

        public void saveMessage(){

        }

        @Override
        public boolean parsingMessage() {
            return false;
        }
        public void setMsg(ChatMessage msg,Session session){
            this.msg = msg;
        }
    }

    /**
     * 处理文字消息
     */
    public static class dealWord implements DealWithMessage{
        private ChatMessage msg;
        private Session session;
        TbUseMsgEntity tbUseMsgEntity;
        public void saveMessage(){
            chatUserService.saveMsg(tbUseMsgEntity);
        }
        public boolean parsingMessage(){
        	if(msg.getSingleOrGroup().equals("single")){
	            tbUseMsgEntity = new TbUseMsgEntity();
	            tbUseMsgEntity.setMsgid(msg.getMessageId());
	            tbUseMsgEntity.setSenderId(Integer.parseInt(msg.getSendId()));
	            tbUseMsgEntity.setSender(msg.getSender());
	            tbUseMsgEntity.setReceiverId(Integer.parseInt(msg.getRecipientId()));
	            tbUseMsgEntity.setReceiver(msg.getRecipientor());
	            tbUseMsgEntity.setMsg(msg.getMessageContent());
	            tbUseMsgEntity.setCreateTime(new Date());
	            if(msg.getMessageType().equals("word")){
	            	tbUseMsgEntity.setContentType(new Integer("1"));
	            } 
	            
	            tbUseMsgEntity.setIsread(0);
	            sendMsg(msg);
	            return true;
        	} else {	
        		Group group = new Group();
        		group.setGroupId(new Integer(msg.getRecipientId().trim()));
 
        		GroupMsg groupMsg = new GroupMsg();
        		Date date = new Date();
        		groupMsg.setGroupMsgId(msg.getMessageId());
        		groupMsg.setGroup(group);
        		groupMsg.setSenderId(new Integer(msg.getSendId().trim()));
        		groupMsg.setSenderName(msg.getSender());
        		groupMsg.setGroupMsg(msg.getMessageContent());
        		groupMsg.setCreateTime(date);
        		groupMsg.setContentType(new Integer("1"));
        		groupMsgService.saveGroupMsg(groupMsg);
        		
        		
        		GroupMsgStatus groupMsgStatus = new GroupMsgStatus();
        		groupMsgStatus.setGroupMsg(groupMsg);
        		groupMsgStatus.setReceiveid(new Integer(msg.getSendId().trim()));
        		groupMsgStatus.setReceiveName(msg.getSender());
        		groupMsgStatus.setIsRead(new Integer("1"));
        		groupMsgStatusService.saveGroupMsgStatus(groupMsgStatus);
        		
        		String[] recArr = msg.getRecipientor().trim().split(ROW_GROUP);
        		for(int i=0;i<recArr.length;i++){
        			String[] recs = recArr[i].split(ROW_PART); 
        			groupMsgStatus = new GroupMsgStatus();
        			groupMsgStatus.setGroupMsg(groupMsg);
            		groupMsgStatus.setReceiveid(new Integer(recs[0].trim()));
            		groupMsgStatus.setReceiveName(recs[1]);
            		groupMsgStatus.setIsRead(new Integer("0"));
            		
            		groupMsgStatusService.saveGroupMsgStatus(groupMsgStatus);
            		
            		msg.setRecipientId(recs[0].trim());
            		msg.setRecipientor((group.getGroupId()+ROW_PART+DateUtil.fromatDate(date, "yyyy-MM-dd HH:mm:ss E").toString()).trim());
            		
            		sendMsg(msg);
        		}
        		
        		return false;
        	}
        }
        public void setMsg(ChatMessage msg,Session session){
            this.msg = msg;
            this.session = session;
        }
    }

    /**
     * 处理图像消息
     */
    public static class dealPhoto implements DealWithMessage{
    	 private ChatMessage msg;
         private Session session;
         TbUseMsgEntity tbUseMsgEntity;
         public void saveMessage(){
             chatUserService.saveMsg(tbUseMsgEntity);
         }
         public boolean parsingMessage(){
        	 if(msg.getSingleOrGroup().equals("single")){
	             tbUseMsgEntity = new TbUseMsgEntity();
	             tbUseMsgEntity.setMsgid(msg.getMessageId());
	             tbUseMsgEntity.setSenderId(Integer.parseInt(msg.getSendId()));
	             tbUseMsgEntity.setSender(msg.getSender());
	             tbUseMsgEntity.setReceiverId(Integer.parseInt(msg.getRecipientId()));
	             tbUseMsgEntity.setReceiver(msg.getRecipientor());
	             tbUseMsgEntity.setMsg(msg.getMessageContent());
	             tbUseMsgEntity.setCreateTime(new Date());
	             
	             if(msg.getMessageType().equals("photo")){
	             	tbUseMsgEntity.setContentType(new Integer("2"));
	             }
	             
	             
	             tbUseMsgEntity.setIsread(0);
	             sendMsg(msg);
	             return true;
        	 } else{
        		Group group = new Group();
          		group.setGroupId(new Integer(msg.getRecipientId().trim()));
   
          		GroupMsg groupMsg = new GroupMsg();
          		Date date = new Date();
          		groupMsg.setGroupMsgId(msg.getMessageId());
          		groupMsg.setGroup(group);
          		groupMsg.setSenderId(new Integer(msg.getSendId().trim()));
          		groupMsg.setSenderName(msg.getSender());
          		groupMsg.setGroupMsg(msg.getMessageContent());
          		groupMsg.setCreateTime(date);
          		groupMsg.setContentType(new Integer("2"));
          		groupMsgService.saveGroupMsg(groupMsg);
          		
          		
          		GroupMsgStatus groupMsgStatus = new GroupMsgStatus();
          		groupMsgStatus.setGroupMsg(groupMsg);
          		groupMsgStatus.setReceiveid(new Integer(msg.getSendId().trim()));
          		groupMsgStatus.setReceiveName(msg.getSender());
          		groupMsgStatus.setIsRead(new Integer("1"));
          		groupMsgStatusService.saveGroupMsgStatus(groupMsgStatus);
          		
          		
          		String[] recArr = msg.getRecipientor().trim().split(ROW_GROUP);
          		for(int i=0;i<recArr.length;i++){
          			String[] recs = recArr[i].split(ROW_PART); 
          			groupMsgStatus = new GroupMsgStatus();
          			groupMsgStatus.setGroupMsg(groupMsg);
              		groupMsgStatus.setReceiveid(new Integer(recs[0].trim()));
              		groupMsgStatus.setReceiveName(recs[1]);
              		groupMsgStatus.setIsRead(new Integer("0"));
              		
              		groupMsgStatusService.saveGroupMsgStatus(groupMsgStatus);
              		
              		msg.setRecipientId(recs[0].trim());
              		msg.setRecipientor((group.getGroupId()+ROW_PART+DateUtil.fromatDate(date, "yyyy-MM-dd HH:mm:ss E").toString()).trim());
              		sendMsg(msg);
          		}
          		
          		return false; 
        	 }
         }
         public void setMsg(ChatMessage msg,Session session){
             this.msg = msg;
             this.session = session;
         }
    }

    /**
     * 处理移动端片段音频消息
     */
    public static class dealAudio implements DealWithMessage{
    	 private ChatMessage msg;
         private Session session;
         TbUseMsgEntity tbUseMsgEntity;
         public void saveMessage(){
             chatUserService.saveMsg(tbUseMsgEntity);
         }
         public boolean parsingMessage(){
        	 if(msg.getSingleOrGroup().trim().equals("single")){
	             tbUseMsgEntity = new TbUseMsgEntity();
	             tbUseMsgEntity.setMsgid(msg.getMessageId());
	             tbUseMsgEntity.setSenderId(Integer.parseInt(msg.getSendId()));
	             tbUseMsgEntity.setSender(msg.getSender());
	             tbUseMsgEntity.setReceiverId(Integer.parseInt(msg.getRecipientId()));
	             tbUseMsgEntity.setReceiver(msg.getRecipientor());
	             tbUseMsgEntity.setMsg(msg.getMessageContent());
	             tbUseMsgEntity.setCreateTime(new Date());
	             if(msg.getMessageType().equals("audio")){
	              	tbUseMsgEntity.setContentType(new Integer("3"));// 判断消息类型
	              }
	             tbUseMsgEntity.setIsread(0);
	             sendMsg(msg);
	             return true;
        	 } else{
        		 Date myDate = new Date();
        		 Group group = new Group();
            	 group.setGroupId(new Integer(msg.getRecipientId().trim()));
     
            	 GroupMsg groupMsg = new GroupMsg();
            		
            	 groupMsg.setGroupMsgId(msg.getMessageId());
            	 groupMsg.setGroup(group);
            	 groupMsg.setSenderId(new Integer(msg.getSendId().trim()));
            	 groupMsg.setSenderName(msg.getSender());
            	 groupMsg.setGroupMsg(msg.getMessageContent());
            	 groupMsg.setCreateTime(myDate);
            	 groupMsg.setContentType(new Integer("3"));
            	 groupMsgService.saveGroupMsg(groupMsg);
            		
            		
            	 GroupMsgStatus groupMsgStatus = new GroupMsgStatus();
            	 groupMsgStatus.setGroupMsg(groupMsg);
            	 groupMsgStatus.setReceiveid(new Integer(msg.getSendId().trim()));
            	 groupMsgStatus.setReceiveName(msg.getSender());
            	 groupMsgStatus.setIsRead(new Integer("1"));
            	 groupMsgStatusService.saveGroupMsgStatus(groupMsgStatus);	
            		
            	 String[] recArr = msg.getRecipientor().trim().split(ROW_GROUP);
            	 for(int i=0;i<recArr.length;i++){
            		String[] recs = recArr[i].split(ROW_PART); 
            		groupMsgStatus = new GroupMsgStatus();
            		groupMsgStatus.setGroupMsg(groupMsg);
                	groupMsgStatus.setReceiveid(new Integer(recs[0].trim()));
                	groupMsgStatus.setReceiveName(recs[1]);
                	groupMsgStatus.setIsRead(new Integer("0"));
                		
                	groupMsgStatusService.saveGroupMsgStatus(groupMsgStatus);
                		
                	msg.setRecipientId(recs[0].trim());
                	msg.setRecipientor((group.getGroupId()+ROW_PART+DateUtil.fromatDate(myDate, "yyyy-MM-dd HH:mm:ss E").toString()).trim());
                	sendMsg(msg);
            	}
         		
         		 return false;
        	 }
         }
         public void setMsg(ChatMessage msg,Session session){
             this.msg = msg;
             this.session = session;
         }
    }
    /**
     * 处理WEB音频消息
     */
    public static class dealAudios implements DealWithMessage{
    	 private ChatMessage msg;
         private Session session;
         TbUseMsgEntity tbUseMsgEntity;
         public void saveMessage(){
             chatUserService.saveMsg(tbUseMsgEntity);
         }
         public boolean parsingMessage(){
	             tbUseMsgEntity = new TbUseMsgEntity();
	             tbUseMsgEntity.setMsgid(msg.getMessageId());
	             tbUseMsgEntity.setSenderId(Integer.parseInt(msg.getSendId()));
	             tbUseMsgEntity.setSender(msg.getSender());
	             tbUseMsgEntity.setReceiverId(Integer.parseInt(msg.getRecipientId()));
	             tbUseMsgEntity.setReceiver(msg.getRecipientor());
	             tbUseMsgEntity.setMsg(msg.getMessageContent());
	             tbUseMsgEntity.setCreateTime(new Date());
	             if(msg.getMessageType().equals("audios")){
	              	tbUseMsgEntity.setContentType(new Integer("5"));// 判断消息类型
	              }
	             tbUseMsgEntity.setIsread(1);
	             sendMsg(msg);
	             return true;
         }
         public void setMsg(ChatMessage msg,Session session){
             this.msg = msg;
             this.session = session;
         }
    }

    /**
     * 处理视频消息
     */
    public static class dealVideo implements DealWithMessage{
    	 private ChatMessage msg;
         private Session session;
         TbUseMsgEntity tbUseMsgEntity;
         public void saveMessage(){
             chatUserService.saveMsg(tbUseMsgEntity);
         }
         public boolean parsingMessage(){
             tbUseMsgEntity = new TbUseMsgEntity();
             tbUseMsgEntity.setMsgid(msg.getMessageId());
             tbUseMsgEntity.setSenderId(Integer.parseInt(msg.getSendId()));
             tbUseMsgEntity.setSender(msg.getSender());
             tbUseMsgEntity.setReceiverId(Integer.parseInt(msg.getRecipientId()));
             tbUseMsgEntity.setReceiver(msg.getRecipientor());
             tbUseMsgEntity.setMsg(msg.getMessageContent());
             tbUseMsgEntity.setCreateTime(new Date());
             if(msg.getMessageType().equals("video")){
              	tbUseMsgEntity.setContentType(new Integer("4"));// 判断消息类型
              }
             tbUseMsgEntity.setIsread(1);
             sendMsg(msg);
             return true;
         }
         public void setMsg(ChatMessage msg,Session session){
             this.msg = msg;
             this.session = session;
         }
    }
    

    /**
     * 获取好友列表
     */
    public static class dealGetUserList implements DealWithMessage{
        private ChatMessage msg;
        private Session session;
        public void saveMessage(){}
        public  boolean  parsingMessage(){
            double position[] ;//当前用户经纬度
            List onLineIdList;//在线用户列表
            List<Object> dbUsers = chatUserService.queryUserAndLocation(msg.getSendId(),null);//获得所有用户
            position = new double[]{(Double)((Object[]) dbUsers.get(0))[3],(Double) ((Object[]) dbUsers.get(0))[4]};//获得经纬度
            onLineIdList = chatUserService.getOnlineID(session);//获得所有在线ID列表
            msg.setMessageContent(chatUserService.getUserInfo(dbUsers,onLineIdList,position));// 当前用户信息字符串
            sendMsg( msg);
            return false;//无需保存消息
        }
        public void setMsg(ChatMessage msg,Session session){
            this.msg = msg;
            this.session =session;
        }
    }

    /**
     *获得用户聊天记录
     */
    public static class dealChatRecord implements DealWithMessage{
        private ChatMessage msg;
        private Session session;
        public void saveMessage(){}
        public  boolean  parsingMessage(){
            Pager pager = new Pager();
            List<String> msgContent = Arrays.asList( msg.getMessageContent().split(","));
            pager.setCurrentPage(msgContent.get(1));
            msg.setMessageContent(chatUserService.getChatRecord(msg.getSendId(), msgContent.get(0), pager)+"&&&&"+pager.getTotalPage());
            
            
            sendMsg(msg);
            return false;
        }
        public void setMsg(ChatMessage msg,Session session){
            this.msg = msg;
            this.session =session;
        }
    }

    /**
     * 更新未读消息状态到已读
     */
    public static class dealUpdateIsRead implements DealWithMessage{
        private ChatMessage msg;
        private Session session;
        public void saveMessage(){}
        public  boolean  parsingMessage(){
            chatUserService.UpdateIsRead(msg.getRecipientId(),msg.getSendId());
            return false;
        }
        public void setMsg(ChatMessage msg,Session session){
            this.msg = msg;
            this.session =session;
        }

    }
    /**
     * 处理群组消息
     */
    public static class dealGroup implements DealWithMessage{
    	private ChatMessage msg;
        private Session session;

		public boolean parsingMessage() {
			String groupName = msg.getRecipientor();
			String createId = msg.getSendId();
			String createName = msg.getSender();
			//将群组写入数据库
			Group group = new Group();
			group.setCreateId(new Integer(createId.trim()));
			group.setCreateTime(new Date());
			group.setGroupName(groupName);
			groupService.saveGroup(group);
			
			GroupMember groupMember = new GroupMember();
			groupMember.setUserId(new Integer(createId.trim()));
			groupMember.setName(createName.trim());
			groupMember.setGroup(group);
			groupMemberService.saveGroupMember(groupMember);
			
			String messageContent = msg.getMessageContent();
			String[] strArr = messageContent.split(ROW_GROUP);
			
			for(int i=0;i<strArr.length;i++){
				groupMember = new GroupMember();
				String[] strs = strArr[i].split(ROW_PART);
				groupMember.setUserId(new Integer(strs[0].trim()));
				groupMember.setName(strs[1].trim());
				groupMember.setGroup(group);
				
				groupMemberService.saveGroupMember(groupMember);
			}
			
			return false;
		}

		public void saveMessage() {
			
		}

		public void setMsg(ChatMessage msg, Session session) {
			this.msg = msg;
            this.session =session;
		}
    }
    
    /**
     * 获得群组的操作
     */
    public static class getGroupList implements DealWithMessage{
    	private ChatMessage msg;
        private Session session;
		public boolean parsingMessage() {
			
			String groupStr = groupService.queryGroup(msg.getSendId(), null); //获得所有的群组信息
			
			msg.setMessageContent(groupStr);
			sendMsg(msg);
			
			
            return false;//无需保存消息
		}

		public void saveMessage() {}

		public void setMsg(ChatMessage msg, Session session) {
			this.msg = msg;
			this.session = session;
		}
    	
    }
    
    
    public static class getNewGroup implements DealWithMessage{
    	private ChatMessage msg;
        private Session session;
		public boolean parsingMessage() {
			//获得了创建的群组的消息
			List<Object[]> groupList = groupMemberService.queryGroupMember(msg.getSendId(), null);
			for(int i=0;i<groupList.size();i++){
				String userId = (groupList.get(i)[1].toString().trim());
				String messageContent = groupService.queryNewGroup(msg.getSendId(), null);
				msg.setMessageContent(messageContent);
				msg.setRecipientId(userId);
				msg.setRecipientor(groupList.get(i)[1].toString().trim());
				sendMsg(msg);
			}
			
            return false;//无需保存消息
		}

		public void saveMessage() {}

		public void setMsg(ChatMessage msg, Session session) {
			this.msg = msg;
			this.session = session;
		}
    	
    }
    
    
    public static class getGroupMember implements DealWithMessage{
    	private ChatMessage msg;
        private Session session;
		public boolean parsingMessage() {
			double position[] ;//当前用户经纬度
            List onLineIdList;//在线用户列表
            List<Object> dbUsers = groupService.queryGroupAndLocation(msg.getRecipientId(), msg.getSendId() ,null);//获得所有用户
            
            position = new double[]{(Double)((Object[]) dbUsers.get(0))[3],(Double) ((Object[]) dbUsers.get(0))[4]};//获得经纬度
            onLineIdList = chatUserService.getOnlineID(session);//获得所有在线ID列表
            msg.setMessageContent(chatUserService.getUserInfo(dbUsers,onLineIdList,position));// 当前用户信息字符串
           
            
            msg.setRecipientId(msg.getSendId());
            sendMsg(msg);
            
            return false;//无需保存消息
		}
		
		public void saveMessage() {}

		public void setMsg(ChatMessage msg, Session session) {
			this.msg = msg;
			this.session = session;
		}
    	
    }
    
    public static class getGroupChatRecord implements DealWithMessage{
    	private ChatMessage msg;
        private Session session;
		public boolean parsingMessage() {
			//所有未读取的信息设置为已读
			String groupId  = msg.getMessageContent().trim();
			String currentPage = msg.getRecipientor().trim();
			String sendId = msg.getSendId();
			//修改消息由未读到已读，当初始状态的时候才操作这个方法
			if(currentPage.equals("1")){
				groupMsgStatusService.updateGroupIsRead(sendId, groupId);
			}

			Pager pager = new Pager();
			pager.setCurrentPage(currentPage);
			String content = groupMsgService.queryGroupMsg(groupId, pager);
			msg.setMessageContent(content);
			//修改  发送者id
			msg.setRecipientId(msg.getSendId());
			msg.setRecipientor((pager.getTotalPage()+"").trim());
			sendMsg(msg);
			
			return false;
		}

		public void saveMessage() {}

		public void setMsg(ChatMessage msg, Session session) {
			this.msg = msg;
			this.session = session;
		}
    	
    }
    
    
    public static class setGroupMsgStatus implements DealWithMessage{
    	private ChatMessage msg;
        private Session session;
		public boolean parsingMessage() {
			//直接显示的消息设置为已读
			//System.out.println("setGroupMsgStatus:   "+msg.getSender()+ "    "+msg.getSendId()+"   "+msg.getRecipientId()+"   "+msg.getRecipientor()+"  "+msg.getMessageContent());
			
			groupMsgStatusService.updateNewGroupIsRead(msg.getMessageContent(), msg.getSendId());

			return false;
		}

		public void saveMessage() {}

		public void setMsg(ChatMessage msg, Session session) {
			this.msg = msg;
			this.session = session;
		}
    	
    }
    
    
    public static class userExitGroup implements DealWithMessage{
    	private ChatMessage msg;
        private Session session;
		public boolean parsingMessage() {
			//退出群组
			String exitUserId = msg.getRecipientId();
			String exitGroupId = msg.getRecipientor();
			
			List<Object[]> memberLists = groupMemberService.queryGroupMembers(exitGroupId, null);
			
			if(memberLists.size() == 1){
				//如果只有一个成员的情况下删除需要删除所有有关的信息
				groupMsgStatusService.deleteGroupMsgStatus(exitGroupId);
				groupMsgService.deleteGroupMsg(exitGroupId);
				groupMemberService.deleteGroupMember(exitUserId, exitGroupId);
				groupService.deleteGroup(exitGroupId);
			} else{
				//删除
				groupMemberService.deleteGroupMember(exitUserId, exitGroupId);
			}
			
			for(int i=0;i<memberLists.size();i++){
				String userId = memberLists.get(i)[1].toString().trim();
				msg.setRecipientId(userId.trim());
				msg.setRecipientor(exitGroupId);
				msg.setSingleOrGroup(exitUserId);
				sendMsg(msg);
			}
				
			return false;
		}

		public void saveMessage() {}

		public void setMsg(ChatMessage msg, Session session) {
			this.msg = msg;
			this.session = session;
		}
    	
    }

}
