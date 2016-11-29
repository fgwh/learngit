package com.hgsoft.main.im.action;

import com.hgsoft.main.im.entity.ChatMessage;
import com.hgsoft.main.im.service.ChatUtility;
import com.hgsoft.main.im.service.MessageOperateService;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.hgsoft.main.im.service.MessageOperateService.*;

@ServerEndpoint(value = "/chat/{room}", encoders = ChatMessageEncoder.class, decoders = ChatMessageDecoder.class)
public class ChatEndpoint {
	private static WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
	private MessageOperateService messageOperateService;
	public static Map sessions = new HashMap();
	public final static String SINGLE = "single";//消息单发或群发
	public final static String GROUP = "group";
	public final static Map<String,DealWithMessage> messageType = new HashMap<String,DealWithMessage>(){{//消息类型
		put("onAndOffline", new dealOnAndOffLine());//上线
		put("word",new dealWord());//文字
		put("photo",new dealPhoto());//图片
		put("audio",new dealAudio());//移动端音频
		put("audios",new dealAudios());//WEB端实时语音
		put("video",new dealVideo());//视频
		put("getUserList",new dealGetUserList());//获得用户列表
		put("getChatRecord",new dealChatRecord());//获得聊天记录
		put("updateIsRead",new dealUpdateIsRead());//把未读消息标记为已读
		
		put("group", new dealGroup());   //群操作
		put("getGroupList", new getGroupList());   //获得群组的操作
		put("getNewGroup", new getNewGroup());
		put("getGroupMember", new getGroupMember()); //显示群组成员操作
		put("getGroupChatRecord", new getGroupChatRecord());
		put("setGroupMsgStatus", new setGroupMsgStatus());
		put("userExitGroup", new userExitGroup());
	}};


	@OnOpen
	public void open(final Session session, @PathParam("room") final String room) {
		session.getUserProperties().put("room", room);
		if(!sessions.containsKey(room)){//之前不在线
			messageOperateService.dealOnAndOffLine("online",room);//发布上线消息
		}
		sessions.put(room, session);

	}
	@OnMessage
	public void onMessage(final Session session, final ChatMessage chatMessage) {
		if(messageOperateService == null) {
			messageOperateService = (MessageOperateService) applicationContext.getBean("messageOperateService");
		}
		if(ChatUtility.checkMessage(chatMessage)){//检查合法性
			DealWithMessage dealWithMessage = (DealWithMessage) messageType.get(chatMessage.getMessageType());
			dealWithMessage.setMsg(chatMessage,session);//设置消息
			if(dealWithMessage.parsingMessage()){//处理消息
				dealWithMessage.saveMessage();//保存消息
			}
		}else{
			System.out.println("消息格式有误,将忽略");
		}
	}
	@OnClose
	public void close(final Session session,CloseReason reason){
		DelayOffLine delayOffLine = new DelayOffLine(session.getUserProperties().get("room").toString(),session);
		delayOffLine.start();
	}

	class DelayOffLine extends Thread{//延迟5秒下线，防止客户端刷新造成上下线信息广播风暴
		private String id;//每个线程都拥有100张票
		private Session session;
		DelayOffLine(String id, Session session){
			this.id=id;
			this.session = session;
		}
		public void run(){
			try {
				Thread.sleep(3000);
				//防止被顶下的同ID用户删除当前Session
				if(!session.isOpen() && session == sessions.get(id)){
					ChatEndpoint.sessions.remove(id);
				}
				//如果又在线了，则不再发布
				if(!ChatEndpoint.sessions.containsKey(id)){
					messageOperateService.dealOnAndOffLine("offline",id);//发布下线消息
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}