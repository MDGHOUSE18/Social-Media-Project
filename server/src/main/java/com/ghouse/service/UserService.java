package com.ghouse.service;

import java.util.List;

import com.ghouse.exceptions.UserException;
import com.ghouse.model.User;



public interface UserService {
	
//	public void initializeUsers();
	
	public User registerUser(User user);
	
	public List<User> getAllUsers();
	
	public User findUserById(Integer id) throws UserException;
	
	public User findUserByEmail(String email) throws UserException;
	
	public User followUser(Integer reqUserId,Integer followUserId) throws UserException;
	
	public List<User> serchUser(String query);
	
	public User updateUserById(Integer id,User user) throws UserException;
	
	public String deleteUser(Integer id) throws UserException;
	
	public User getUserFromToken(String token) throws UserException;

	
	
	
	
}
