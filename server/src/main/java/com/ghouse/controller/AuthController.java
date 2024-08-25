package com.ghouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ghouse.config.JwtProvider;
import com.ghouse.model.User;
import com.ghouse.repository.UserRepository;
import com.ghouse.response.AuthResponse;


@RestController
//@RequestMapping()
public class AuthController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	public AuthResponse registerUser(@RequestBody User user) throws Exception {
		
		System.out.println(user.getEmail());
		
		User isExists=userRepository.findByEmail(user.getEmail());
		if(isExists!=null) {
			throw new Exception("This email already have account.Please Login...");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		userRepository.save(user);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		
		String token = JwtProvider.generateToken(authentication);
		
		AuthResponse response= new AuthResponse(token,"Register Successfull");
		System.out.println(response);
		return response;
	}
}
