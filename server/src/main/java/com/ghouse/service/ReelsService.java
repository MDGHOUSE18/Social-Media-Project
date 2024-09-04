package com.ghouse.service;

import java.util.List;

import com.ghouse.model.Reels;
import com.ghouse.model.User;

public interface ReelsService {
	
	public Reels createReel(Reels reel,User user);
	
	public List<Reels> findAllReels();
	
	public List<Reels> findUserReels(Integer userId) throws Exception;

}
