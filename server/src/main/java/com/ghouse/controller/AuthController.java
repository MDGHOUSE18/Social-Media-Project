package com.ghouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.ghouse.model.User;

import com.ghouse.request.LoginRequest;
import com.ghouse.response.AuthResponse;
import com.ghouse.service.AuthService;



@RestController
//@RequestMapping()
public class AuthController {

	@Autowired
	AuthService authService;
	
	@PostMapping("/register")
	public AuthResponse registerUser(@RequestBody User user) throws Exception {
		
		return authService.userRegistration(user);
	}
	@PostMapping("/login")
	public AuthResponse userLogin(@RequestBody LoginRequest loginRequest) throws Exception {
		
		return authService.userLogin(loginRequest);
	}
	
	@PostMapping("/logout")
    public ResponseEntity<String> logout() {
        authService.userLogout();
        return ResponseEntity.ok("Logout Successful");
    }

	
}
