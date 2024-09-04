package com.ghouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ghouse.model.Chat;
import com.ghouse.model.User;
import com.ghouse.request.CreateChatRequest;
import com.ghouse.service.ChatService;
import com.ghouse.service.UserService;

@RestController
@RequestMapping("/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    // Create or get existing chat between two users
    @PostMapping("/")
    public ResponseEntity<Chat> createChat(@RequestHeader("Authorization") String token,
                                           @RequestBody CreateChatRequest req) throws Exception {
        User reqUser = userService.getUserFromToken(token);
        User user2 = userService.findUserById(req.getUserId());
        Chat chat = chatService.createChat(reqUser, user2);
        return ResponseEntity.status(HttpStatus.CREATED).body(chat);
    }

    // Get chat by ID
    @GetMapping("/")
    public ResponseEntity<Chat> findChatById(@RequestHeader("Authorization") String token) throws Exception {
        User user=userService.getUserFromToken(token);
    	Chat chat = chatService.findChatById(user.getId());
        return ResponseEntity.ok(chat);
    }

    // Get all chats for a specific user
    @GetMapping
    public ResponseEntity<List<Chat>> findUsersChats(@RequestHeader("Authorization") String token) throws Exception {
    	User user=userService.getUserFromToken(token);
    	List<Chat> chats = chatService.findUsersChat(user.getId());
        return ResponseEntity.ok(chats);
    }
}
