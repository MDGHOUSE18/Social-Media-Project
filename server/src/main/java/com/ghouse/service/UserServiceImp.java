package com.ghouse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ghouse.exceptions.UserException;
import com.ghouse.model.User;
import com.ghouse.repository.UserRepository;

@Service
public class UserServiceImp implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JWTService jwtService;
	
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
	public User findUserById(Integer id) throws UserException {
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty()) {
			throw new UserException("user not exist with id "+id);
		}
		return user.get();
	}

	@Override
	public User updateUserById(Integer id, User user) throws UserException {
		Optional<User> user1 = userRepository.findById(id);
		
		if(user1.isEmpty()) {
			throw new UserException("User not found with id: " + id);
		}
		User oldUser=user1.get();
		if(user.getFirstName()!=null) {
			oldUser.setFirstName(user.getFirstName());
		}
		if(user.getLastName()!=null) {
			oldUser.setLastName(user.getLastName());
		}
		if(user.getEmail()!=null) {
			oldUser.setEmail(user.getEmail());
		}
		if(user.getPassword()!=null) {
			oldUser.setPassword(user.getPassword());
		}
		if(user.getGender()!=null) {
			oldUser.setGender(user.getGender());
		}
		User updatedUser=userRepository.save(oldUser);
		return updatedUser;
	}

	@Override
	public String deleteUser(Integer id) throws UserException {
		Optional<User> user=userRepository.findById(id);
		if(user.isEmpty()) {
			throw new UserException("User not exists with this id "+id);
		}
		return "User deleted sucessfully with this is "+id;
	}


	@Override
	public User findUserByEmail(String email) throws UserException {
		User user=userRepository.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(Integer reqUserId, Integer followUserId) throws UserException {
		User reqUser=findUserById(reqUserId);
		User followuser=findUserById(followUserId);
		
		if(!reqUser.getFollowing().contains(followUserId)) {
			followuser.getFollowers().add(reqUser.getId());
			reqUser.getFollowing().add(followUserId);
		}else {
			followuser.getFollowers().remove(reqUser.getId());
			reqUser.getFollowing().remove(followUserId);
		}
		
		
		userRepository.save(reqUser);
		userRepository.save(followuser);
		return reqUser;
	}

	@Override
	public List<User> serchUser(String query) {
		List<User> users = userRepository.searchUsers(query);
		return users;
	}

	@Override
	public User getUserFromToken(String token) throws UserException {
		
		if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7).trim();
        }
		String email = jwtService.extractUserName(token);
		System.out.println(email);
		User user= findUserByEmail(email);
		return user;
	}
	
}
