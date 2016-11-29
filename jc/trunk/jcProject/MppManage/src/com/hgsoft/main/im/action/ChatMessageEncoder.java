package com.hgsoft.main.im.action;
 

import com.hgsoft.main.im.entity.ChatMessage;
import com.hgsoft.main.im.service.ChatUtility;
import com.hgsoft.util.DateUtil;
import com.hgsoft.util.MobileMD5;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.util.Date;

public class ChatMessageEncoder implements Encoder.Text<ChatMessage> {
	public void init(final EndpointConfig config) {
	}
 
	public void destroy() {
	}
 
	public String encode(final ChatMessage chatMessage) throws EncodeException {
		String msg =Json.createObjectBuilder()
				.add("sendId", chatMessage.getSendId())			
				.add("sender", chatMessage.getSender())
				.add("recipientId", chatMessage.getRecipientId())
				.add("recipientor", chatMessage.getRecipientor())
				.add("messageType", chatMessage.getMessageType())
				.add("singleOrGroup", chatMessage.getSingleOrGroup())
				.add("messageContent", chatMessage.getMessageContent())
				.add("time", DateUtil.fromatDate(new Date(),"yyyy-mm-dd HH:mm:ss E").toString())
				.add("messageId", MobileMD5.encodeByMD5(ChatUtility.SECKEY + chatMessage.getSendId().toString() + chatMessage.getTime()))
				.build().toString();
		return msg;
	}
}


