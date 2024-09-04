package com.ghouse.service;

import java.util.List;

import com.ghouse.exceptions.StoryException;
import com.ghouse.model.Story;
import com.ghouse.model.User;

public interface StoryService {

	public Story createStory(Story story,User user);
	
	public List<Story> findStoryById(Integer userId) throws StoryException;
	
}
