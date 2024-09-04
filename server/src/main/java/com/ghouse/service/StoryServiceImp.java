package com.ghouse.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ghouse.model.Story;
import com.ghouse.model.User;
import com.ghouse.repository.StoryRepository;

@Service
public class StoryServiceImp implements StoryService{

	@Autowired
	private StoryRepository storyRepository;
	
	@Autowired
	private UserService userService;

	@Override
	public Story createStory(Story story, User user) {
		Story newStory= new Story();
		
		newStory.setCaption(story.getCaption());
		newStory.setImage(story.getImage());
		newStory.setTimeStamp(LocalDateTime.now());
		newStory.setUser(user);
		
		return storyRepository.save(newStory);
	}

	@Override
	public List<Story> findStoryById(Integer userId) throws Exception {
		userService.findUserById(userId);
		return storyRepository.findByUserId(userId);
	}
	
	
	
}
