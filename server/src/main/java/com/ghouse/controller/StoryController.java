package com.ghouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ghouse.model.Story;
import com.ghouse.model.User;
import com.ghouse.service.StoryService;
import com.ghouse.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("/api/story")
public class StoryController {

	@Autowired
	UserService userService;
	
	@Autowired
	StoryService storyService;
	
	@PostMapping("/")
	public ResponseEntity<Story> createStory(@RequestBody Story story,
			@RequestHeader("Authorization") String token) throws Exception {
		User user=userService.getUserFromToken(token);
		
		Story craetedStory = storyService.createStory(story, user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(craetedStory);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Story>> createStory(@PathVariable Integer userId) throws Exception {
		
		List<Story> stories = storyService.findStoryById(userId);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(stories);
	}
	
}
