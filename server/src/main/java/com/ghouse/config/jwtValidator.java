package com.ghouse.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class jwtValidator extends OncePerRequestFilter {

	private static final String BEARER_PREFIX = "Bearer ";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt=request.getHeader(JwtConstant.JWT_HEADER);
		System.out.println(jwt);
		if(jwt != null && jwt.startsWith(BEARER_PREFIX)) {
			jwt = jwt.substring(BEARER_PREFIX.length()).trim();
			try {
				String email=JwtProvider.getEmailFromToken(jwt);
				List<GrantedAuthority> authorities = new ArrayList<>();
				Authentication authentication= new UsernamePasswordAuthenticationToken(email,null, authorities);
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
			} catch (Exception e) {
				throw new BadCredentialsException("Invalid token...",e);
			}
			
		}else if (jwt != null && jwt.contains(" ")) {
            // Handle case where JWT contains whitespace
            throw new BadCredentialsException("JWT token contains whitespace, which is invalid.");
        }
		filterChain.doFilter(request, response);
	}

	

}
