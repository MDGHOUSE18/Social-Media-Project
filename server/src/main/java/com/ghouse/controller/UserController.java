package com.ghouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ghouse.model.User;
import com.ghouse.service.UserServiceImp;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserServiceImp userServiceImp;
	
	
	@PostMapping
	public User registerUser(@RequestBody User user) {
		return userServiceImp.registerUser(user);
	}
	
	@GetMapping("/getallusers")
	public List<User> getAllUsers() {
		
		return userServiceImp.getAllUsers();
	}
	
	@GetMapping("/id/{id}")
	public User getUserById(@PathVariable Integer id) throws Exception {
		return userServiceImp.findUserById(id);
	}
	
	@GetMapping("/email/{email}")
	public User getUserByEmail(@PathVariable String email) throws Exception {
		return userServiceImp.findUserByEmail(email);
	}
	
	@PutMapping("/follow/{reqUserId}/{followUserId}")
	public User followUserHandler(@PathVariable Integer reqUserId,@PathVariable Integer followUserId) throws Exception {
		return userServiceImp.followUser(reqUserId, followUserId);
	}
	
	@PutMapping("/{id}")
	public User updateUser(@PathVariable Integer id,@RequestBody User user) throws Exception {
		return userServiceImp.updateUserById(id,user);
	}
	
	@GetMapping("/search")
	public List<User> searchUser(@RequestParam String query){
		System.out.println(query);
		return userServiceImp.serchUser(query);
		
	}
	
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Integer id) throws Exception {
		System.out.println("Received ID: " + id);
		return userServiceImp.deleteUser(id);
	}
	
}
