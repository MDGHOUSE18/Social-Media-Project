package com.ghouse.service;

import com.ghouse.model.User;
import com.ghouse.request.LoginRequest;
import com.ghouse.response.AuthResponse;

public interface AuthService {
	
	AuthResponse userRegistration(User user) throws Exception;
	
	AuthResponse userLogin(LoginRequest loginRequest) throws Exception;
	
	public void userLogout();
	
}
