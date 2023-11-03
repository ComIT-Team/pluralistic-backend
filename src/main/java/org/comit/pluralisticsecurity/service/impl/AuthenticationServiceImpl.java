package org.comit.pluralisticsecurity.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import org.comit.pluralisticsecurity.dto.JwtAuthenticationResponse;
import org.comit.pluralisticsecurity.dto.RefreshTokenRequest;
import org.comit.pluralisticsecurity.dto.SignInRequest;
import org.comit.pluralisticsecurity.dto.SignUpRequest;
import org.comit.pluralisticsecurity.entity.Role;
import org.comit.pluralisticsecurity.entity.RoleEnum;
import org.comit.pluralisticsecurity.entity.User;
import org.comit.pluralisticsecurity.entity.UserRole;
import org.comit.pluralisticsecurity.repository.RoleRepository;
import org.comit.pluralisticsecurity.repository.UserRepository;
import org.comit.pluralisticsecurity.repository.UserRoleRepository;
import org.comit.pluralisticsecurity.service.AuthenticationService;
import org.comit.pluralisticsecurity.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;

	// @Autowired
	private UserDetails userDetails;

	@Autowired
	private JWTService jwtService;

	public Optional<User> signup(SignUpRequest signUpRequest) {

		User user = new User();
		Role role = new Role(RoleEnum.USER.toString());

		UserRole userRole = new UserRole();

		user.setEmail(signUpRequest.getEmail());
		user.setFirstname(signUpRequest.getFirstName());
		user.setLastname(signUpRequest.getLastName());
		user.setUsername(signUpRequest.getUsername());
		user.setActive(true);
		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

		userRole.setRole(role);
		userRole.setUser(user);

		user.setUserRoles(new ArrayList<>());
		user.getUserRoles().add(userRole);
		userRepository.save(user);
		return userRepository.findById(user.getIdUser());

	}

	public JwtAuthenticationResponse signin(SignInRequest signInRequest) {

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
		var currentuser = userRepository.findByEmail(signInRequest.getEmail());

		UserRole userRole = userRoleRepository.findIdRole(currentuser.getIdUser());

		Role role = roleRepository.findNameRole(userRole.getRole().getIdRole());

		if (role.getNameRole().equals(RoleEnum.USER.name())) {

			var jwt = jwtService.generateToken(currentuser);
			var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), currentuser);

			JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

			jwtAuthenticationResponse.setToken(jwt);
			jwtAuthenticationResponse.setRefreshToken(refreshToken);
			return jwtAuthenticationResponse;
		} else {
			throw new AccessDeniedException("Access denied due to insufficient authorisation");
		}
	}

	public JwtAuthenticationResponse sellerSignin(SignInRequest signInRequest) {

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
		var currentuser = userRepository.findByEmail(signInRequest.getEmail());

		UserRole userRole = userRoleRepository.findIdRole(currentuser.getIdUser());

		Role role = roleRepository.findNameRole(userRole.getRole().getIdRole());

		if (role.getNameRole().equals(RoleEnum.SELLER.name())) {
			var jwt = jwtService.generateToken(currentuser);
			var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), currentuser);

			JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

			jwtAuthenticationResponse.setToken(jwt);
			jwtAuthenticationResponse.setRefreshToken(refreshToken);
			return jwtAuthenticationResponse;
		} else {
			throw new AccessDeniedException("Access denied due to insufficient authorisation");
		}

	}

	public JwtAuthenticationResponse adminSignin(SignInRequest signInRequest) {

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
		var currentuser = userRepository.findByEmail(signInRequest.getEmail());

		UserRole userRole = userRoleRepository.findIdRole(currentuser.getIdUser());

		Role role = roleRepository.findNameRole(userRole.getRole().getIdRole());

		if (role.getNameRole().equals(RoleEnum.ADMIN.name())) {

			var jwt = jwtService.generateToken(currentuser);
			var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), currentuser);

			JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

			jwtAuthenticationResponse.setToken(jwt);
			jwtAuthenticationResponse.setRefreshToken(refreshToken);
			return jwtAuthenticationResponse;
		} else {
			throw new AccessDeniedException("Access denied due to insufficient authorisation");
		}
	}

	public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {

		String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
		User user = userRepository.findByEmail(userEmail);
		if (jwtService.isTokenValid(refreshTokenRequest.getToken(), userDetails)) {

			var jwt = jwtService.generateToken(user);
			JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

			jwtAuthenticationResponse.setToken(jwt);
			jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
			return jwtAuthenticationResponse;

		}
		return null;
	}

}
