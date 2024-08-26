package com.ghouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ghouse.model.User;
import com.ghouse.service.JWTService;
import com.ghouse.service.UserService;
import com.ghouse.service.UserServiceImp;



@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	UserService userService;
	
	
	
	@GetMapping("/getallusers")
	public List<User> getAllUsers() {
		
		return userService.getAllUsers();
	}
	
	@GetMapping("/id/{id}")
	public User getUserById(@PathVariable Integer id) throws Exception {
		return userService.findUserById(id);
	}
	
	@GetMapping("/email/{email}")
	public User getUserByEmail(@PathVariable String email) throws Exception {
		return userService.findUserByEmail(email);
	}
	
	@PutMapping("/follow/{followUserId}")
	public User followUserHandler(@RequestHeader("Authorization") String token,@PathVariable Integer followUserId) throws Exception {
		User reqUser = userService.getUserFromToken(token);
		
		return userService.followUser(reqUser.getId(), followUserId);
	}
	
	@PutMapping("/")
	public User updateUser(@RequestHeader("Authorization") String token,@RequestBody User user) throws Exception {
		User reqUser = userService.getUserFromToken(token);
		return userService.updateUserById(reqUser.getId(),user);
	}
	
	@GetMapping("/search")
	public List<User> searchUser(@RequestParam String query){
		System.out.println(query);
		return userService.serchUser(query);
		
	}
	
	@DeleteMapping("/users/{id}")
	public String deleteUser(@PathVariable Integer id) throws Exception {
		System.out.println("Received ID: " + id);
		return userService.deleteUser(id);
	}
	@GetMapping("/profile")
	public User findUserBYtoken(@RequestHeader("Authorization") String token) throws Exception{		
		return userService.getUserFromToken(token);
		
	}
	
	
}
