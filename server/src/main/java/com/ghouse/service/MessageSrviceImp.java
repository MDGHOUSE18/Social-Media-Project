package com.ghouse.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ghouse.exceptions.ChatException;
import com.ghouse.exceptions.MessageException;
import com.ghouse.model.Chat;
import com.ghouse.model.Message;
import com.ghouse.model.User;
import com.ghouse.repository.ChatRepository;
import com.ghouse.repository.MessageRepository;

@Service
public class MessageSrviceImp implements MessageService{
	
	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private ChatService chatService;
	
	@Autowired
	private ChatRepository chatRepository;
	
	@Override
	public Message createMessage(User user, Integer chatId, Message req) throws MessageException {
		
		Message message = new Message();
		
		Chat chat = chatService.findChatById(chatId);
		
		message.setChat(chat);
		message.setContent(req.getContent());
		message.setImage(req.getImage());
		message.setUser(user);
		message.setTimestamp(LocalDateTime.now());
		
		Message savedMessage = messageRepository.save(message);
		
		chat.getMessages().add(savedMessage);
		chatRepository.save(chat);
		
		return savedMessage;
	}

	@Override
	public List<Message> findChatMessages(Integer chatId) throws ChatException {
		Chat chat = chatService.findChatById(chatId);
		
		return messageRepository.findByChatId(chatId);
	}

}
