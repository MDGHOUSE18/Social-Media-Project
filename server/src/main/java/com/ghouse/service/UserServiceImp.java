package com.ghouse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ghouse.model.User;
import com.ghouse.repository.UserRepository;

@Service
public class UserServiceImp implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	

	
	@Override
	public User registerUser(User user) {
		userRepository.save(user);
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users=userRepository.findAll();
		return users;
	}

	@Override
	public User findUserById(Integer id) throws Exception {
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty()) {
			throw new Exception("user not exist with id "+id);
		}
		return user.get();
	}

	@Override
	public User updateUserById(Integer id, User user) throws Exception {
		Optional<User> user1 = userRepository.findById(id);
		
		if(user1.isEmpty()) {
			throw new Exception("user not exist with id "+id);
		}
		User oldUser=user1.get();
		if(oldUser.getFirstName()!=null) {
			oldUser.setFirstName(user.getFirstName());
		}
		if(oldUser.getLastName()!=null) {
			oldUser.setLastName(user.getLastName());
		}
		if(oldUser.getEmail()!=null) {
			oldUser.setEmail(user.getEmail());
		}
		if(oldUser.getPassword()!=null) {
			oldUser.setPassword(user.getPassword());
		}
		User updatedUser=userRepository.save(oldUser);
		return updatedUser;
	}

	@Override
	public String deleteUser(Integer id) throws Exception {
		Optional<User> user=userRepository.findById(id);
		if(user.isEmpty()) {
			throw new Exception("User not exists with this id "+id);
		}
		return "User deleted sucessfully with this is "+id;
	}


	@Override
	public User findUserByEmail(String email) throws Exception {
		User user=userRepository.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(Integer reqUserId, Integer followUserId) throws Exception {
		User reqUser=findUserById(reqUserId);
		User followuser=findUserById(followUserId);
		
		followuser.getFollowers().add(reqUser.getId());
		reqUser.getFollowing().add(followUserId);
		
		userRepository.save(reqUser);
		userRepository.save(followuser);
		return reqUser;
	}

	@Override
	public List<User> serchUser(String query) {
		List<User> users = userRepository.searchUsers(query);
		return users;
	}
	
}
