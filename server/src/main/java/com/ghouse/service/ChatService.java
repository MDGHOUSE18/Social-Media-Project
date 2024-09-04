package com.ghouse.service;

import java.util.List;

import com.ghouse.exceptions.ChatException;
import com.ghouse.model.Chat;
import com.ghouse.model.User;

public interface ChatService {

	public Chat createChat(User reqUser,User user2);
	
	public Chat findChatById(Integer chatId) throws ChatException;
	
	public List<Chat> findUsersChat(Integer userId);
	
}
