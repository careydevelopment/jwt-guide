package com.careydevelopment.jwtguide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.careydevelopment.jwtguide.model.JwtRequest;
import com.careydevelopment.jwtguide.model.JwtResponse;
import com.careydevelopment.jwtguide.model.User;
import com.careydevelopment.jwtguide.util.JwtTokenUtil;


@RestController
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	
	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		final User user = authenticate(authenticationRequest);
		final String token = JwtTokenUtil.generateToken(user);		
		
		return ResponseEntity.ok(new JwtResponse(token, user));
	}

	
	private User authenticate(JwtRequest request) throws Exception {
		User user = null;
		
		try {
			Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			user = (User)auth.getPrincipal();
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		
		return user;
	}
}