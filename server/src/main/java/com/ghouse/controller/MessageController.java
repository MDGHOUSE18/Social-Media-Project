package com.ghouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ghouse.model.Message;
import com.ghouse.model.User;
import com.ghouse.service.MessageService;
import com.ghouse.service.UserService;

@RestController
@RequestMapping("/message")
public class MessageController {
    
    @Autowired
    private MessageService messageService;
    
    @Autowired
    private UserService userService;
    
    // Create a message in a chat
    @PostMapping("/chat/{chatId}")
    public ResponseEntity<Message> createMessage(@RequestBody Message req,
                                                 @RequestHeader("Authorization") String token, 
                                                 @PathVariable Integer chatId) throws Exception {
        User user = userService.getUserFromToken(token);
        Message createdMessage = messageService.createMessage(user, chatId, req);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMessage);
    }
    
    // Get all messages for a chat
    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<Message>> findChatsMessages(@PathVariable Integer chatId) throws Exception {
        List<Message> messages = messageService.findChatMessages(chatId);
        return ResponseEntity.ok(messages);
    }
}
