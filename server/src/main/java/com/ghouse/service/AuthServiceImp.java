package com.ghouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ghouse.config.JwtProvider;
import com.ghouse.model.User;
import com.ghouse.repository.UserRepository;
import com.ghouse.request.LoginRequest;
import com.ghouse.response.AuthResponse;

@Service
public class AuthServiceImp implements AuthService{

	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomerUserDetailService customerUserDetailService;
	
	@Override
	public AuthResponse userRegistration(User user) throws Exception {
		User isExists=userRepository.findByEmail(user.getEmail());
		if(isExists!=null) {
			throw new Exception("This email already have account.Please Login...");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		userRepository.save(user);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		
		String token = JwtProvider.generateToken(authentication);
		
		AuthResponse response= new AuthResponse(token,"Register Successfull");
		System.out.println(response.getMessage());
		return response;
	}

	@Override
	public AuthResponse userLogin(LoginRequest loginRequest) throws Exception{
		Authentication authentication = authenticate(loginRequest.getEmail(),loginRequest.getPassword()); 
		String token = JwtProvider.generateToken(authentication);
		AuthResponse response= new AuthResponse(token,"Login Successfull");
		System.out.println(response.getMessage());
		return response;
	}
	
	private Authentication authenticate(String email, String password) {
		UserDetails userDetails=customerUserDetailService.loadUserByUsername(email);
		
		if(userDetails==null) {
			throw new BadCredentialsException("Invalid username");
		}
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Password not matched");
		}
		
		
		return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
	}

	@Override
	public void userLogout() {
		SecurityContextHolder.clearContext();
		
	}
	
	
	
}
