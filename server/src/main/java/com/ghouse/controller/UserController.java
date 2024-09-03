package com.ghouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ghouse.model.User;
import com.ghouse.service.UserService;



@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	UserService userService;
	
	
	
	@GetMapping("/getallusers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
	
	@GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        try {
            User user = userService.findUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
	
	@GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        try {
            User user = userService.findUserByEmail(email);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
	
	@PutMapping("/follow/{followUserId}")
    public ResponseEntity<User> followUserHandler(@RequestHeader("Authorization") String token,
                                                   @PathVariable Integer followUserId) {
        try {
            User reqUser = userService.getUserFromToken(token);
            User followedUser = userService.followUser(reqUser.getId(), followUserId);
            return new ResponseEntity<>(followedUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
	
	@PutMapping("/")
    public ResponseEntity<User> updateUser(@RequestHeader("Authorization") String token,
                                            @RequestBody User user) {
        try {
            User reqUser = userService.getUserFromToken(token);
            User updatedUser = userService.updateUserById(reqUser.getId(), user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
	
	@GetMapping("/search")
    public ResponseEntity<List<User>> searchUser(@RequestParam String query) {
        List<User> users = userService.serchUser(query);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
	
	@DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        try {
            String response = userService.deleteUser(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }
	@GetMapping("/profile")
    public ResponseEntity<User> findUserByToken(@RequestHeader("Authorization") String token) {
        try {
            User user = userService.getUserFromToken(token);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }
	
	
}
