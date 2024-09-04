package com.ghouse.service;

import java.util.List;

import com.ghouse.model.Message;
import com.ghouse.model.User;

public interface MessageService {

	public Message createMessage(User user,Integer chatId, Message req) throws Exception;
	
	public List<Message> findChatMessages(Integer chatId) throws Exception;
	
}
