package com.ghouse.service;

import java.util.List;

import com.ghouse.model.User;



public interface UserService {
	
//	public void initializeUsers();
	
	public User registerUser(User user);
	
	public List<User> getAllUsers();
	
	public User findUserById(Integer id) throws Exception;
	
	public User findUserByEmail(String email) throws Exception;
	
	public User followUser(Integer reqUserId,Integer followUserId) throws Exception;
	
	public List<User> serchUser(String query);
	
	public User updateUserById(Integer id,User user) throws Exception;
	
	public String deleteUser(Integer id) throws Exception;

	
	
	
	
}
