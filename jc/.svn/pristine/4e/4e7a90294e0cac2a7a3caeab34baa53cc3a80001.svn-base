package com.hgsoft.main.im.action;
import com.hgsoft.main.im.entity.ChatMessage;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.StringReader;
 
public class ChatMessageDecoder implements Decoder.Text<ChatMessage> {
	private JsonObject obj;

	@Override
	public void init(final EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public ChatMessage decode(final String textMessage) throws DecodeException {
		ChatMessage chatMessage = new ChatMessage();
		obj = Json.createReader(new StringReader(textMessage))
				.readObject();
		chatMessage.setSendId(obj.getString("sendId"));
		chatMessage.setSender(obj.getString("sender"));
		chatMessage.setRecipientId(obj.getString("recipientId"));
		chatMessage.setRecipientor(obj.getString("recipientor"));
		chatMessage.setMessageType(obj.getString("messageType"));
		chatMessage.setSingleOrGroup(obj.getString("singleOrGroup"));
		chatMessage.setMessageContent(obj.getString("messageContent"));
		chatMessage.setMessageId(obj.getString("messageId"));
		chatMessage.setTime(obj.getString("time"));
		return chatMessage;
	}

	@Override
	public boolean willDecode(final String s) {
		return true;
	}
}
