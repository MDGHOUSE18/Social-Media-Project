package com.ghouse.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ghouse.exceptions.ReelsException;
import com.ghouse.model.Reels;
import com.ghouse.model.User;
import com.ghouse.repository.ReelsRepository;


@Service
public class ReelsServiceImp implements ReelsService{

	
	@Autowired
	ReelsRepository reelsRepository;
	
	@Autowired
	UserService userService;
	
	@Override
	public Reels createReel(Reels reel, User user){
		Reels createReel  = new Reels();
		createReel.setTitle(reel.getTitle());
		createReel.setUser(user);
		createReel.setCreatedAt(LocalDateTime.now());
		createReel.setVideo(reel.getVideo());
		return reelsRepository.save(createReel);
	}

	@Override
	public List<Reels> findAllReels() {
		
		return reelsRepository.findAll();
	}

	@Override
	public List<Reels> findUserReels(Integer userId) throws ReelsException {
		userService.findUserById(userId);
		return reelsRepository.findByUserId(userId);
	}

}
