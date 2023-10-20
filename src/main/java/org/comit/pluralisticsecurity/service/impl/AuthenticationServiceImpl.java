package org.comit.pluralisticsecurity.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.comit.pluralisticsecurity.dto.JwtAuthenticationResponse;
import org.comit.pluralisticsecurity.dto.RefreshTokenRequest;
import org.comit.pluralisticsecurity.dto.SignInRequest;
import org.comit.pluralisticsecurity.dto.SignUpRequest;
import org.comit.pluralisticsecurity.entity.Role;
import org.comit.pluralisticsecurity.entity.RoleEnum;
import org.comit.pluralisticsecurity.entity.User;
import org.comit.pluralisticsecurity.entity.UserRole;
import org.comit.pluralisticsecurity.repository.UserRepository;
import org.comit.pluralisticsecurity.service.AuthenticationService;
import org.comit.pluralisticsecurity.service.JWTService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{
	//@Autowired	
 private final UserRepository userReository;
	//@Autowired
  private final PasswordEncoder passwordEncoder;
	//@Autowired
  private final AuthenticationManager authenticationManager;
	
	
	
	
	//@Autowired
	private final JWTService jwtService;
	
	public User signup(SignUpRequest signUpRequest) {
		
		User user = new User();
		Role role = new Role(RoleEnum.USER.ordinal());
		UserRole userRole = new UserRole();
		
		user.setEmail(signUpRequest.getEmail());
		user.setFirstName(signUpRequest.getFirstName());
		user.setLastName(signUpRequest.getLastName());
		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		//user.setRole(Role.USER);
		userRole.setRole(role);
		userRole.setUser(user);
		
		user.setUserRoles(new ArrayList<>());
		user.getUserRoles().add(userRole);
		
		return userReository.save(user);
		
		
}
	public JwtAuthenticationResponse signin(SignInRequest signInRequest) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
				(signInRequest.getEmail(), signInRequest.getPassword()));
		var user = userReository.findByEmail(signInRequest.getEmail())
								.orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
		
		var jwt = jwtService.generateToken(user);
		var refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);
		
		JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
		
		jwtAuthenticationResponse.setToken(jwt);
		jwtAuthenticationResponse.setRefreshToken(refreshToken);
		return jwtAuthenticationResponse;
		
	}
	
	public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
		
		String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
		User user = userReository.findByEmail(userEmail).orElseThrow();
		if(jwtService.isTokenValid(refreshTokenRequest.getToken(),user)) {
			
			var jwt = jwtService.generateToken(user);
			JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
			
			jwtAuthenticationResponse.setToken(jwt);
			jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
			return jwtAuthenticationResponse;
			
		}
		return null;
	}
}
