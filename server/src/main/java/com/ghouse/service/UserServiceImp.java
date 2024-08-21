package com.ghouse.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ghouse.model.User;
import com.ghouse.repository.UserRepository;

@Service
public class UserServiceImp implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	
	
	public void initializeUsers() {
        long userCount = userRepository.count();

        if (userCount == 0) {
            User user1 = new User(null, "Mahammed", "Ghouse", "mahammed.ghouse@example.com", "Mahammed@123", "Male", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            User user2 = new User(null, "Mahammad", "Ikram", "mahammad.ikram@example.com", "Mahammad@456", "Male", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            User user3 = new User(null, "Asad", "Ali Khan", "asad.alikhan@example.com", "Asad@789", "Male", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            User user4 = new User(null, "Shaik", "Syfulla", "shaik.syfulla@example.com", "Shaik@012", "Male", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            User user5 = new User(null, "Shaik", "Shafiya", "shaik.shafiya@example.com", "Shaik@345", "Female", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            User user6 = new User(null, "Sameera", "Khanam", "sameera.khanam@example.com", "Sameera@678", "Female", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            User user7 = new User(null, "Sumiya", "Sultana", "sumiya.sultana@example.com", "Sumiya@901", "Female", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            User user8 = new User(null, "Shaik", "Munawwar", "shaik.munawwar@example.com", "Shaik@234", "Male", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            User user9 = new User(null, "Arif", "Basha", "arif.basha@example.com", "Arif@567", "Male", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            User user10 = new User(null, "Ayesha", "Banu", "ayesha.banu@example.com", "Ayesha@890", "Female", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

            userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10));
            System.out.println("Users added to the database.");
        }
	}
	
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
