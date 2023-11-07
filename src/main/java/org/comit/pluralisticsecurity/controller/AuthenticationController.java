package org.comit.pluralisticsecurity.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.comit.pluralisticsecurity.dto.JwtAuthenticationResponse;
import org.comit.pluralisticsecurity.dto.RefreshTokenRequest;
//import org.comit.pluralisticsecurity.dto.SellerDetails;
import org.comit.pluralisticsecurity.dto.SignInRequest;
import org.comit.pluralisticsecurity.dto.SignUpRequest;
import org.comit.pluralisticsecurity.entity.Role;
import org.comit.pluralisticsecurity.entity.RoleEnum;
import org.comit.pluralisticsecurity.entity.User;
import org.comit.pluralisticsecurity.entity.UserRole;
import org.comit.pluralisticsecurity.service.AuthenticationService;
import org.comit.pluralisticsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")

@RequiredArgsConstructor
public class AuthenticationController {

	@Autowired
	AuthenticationService authenticationService;

	@Autowired
	UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<Optional<User>> signup(@RequestBody SignUpRequest signUpRequest) {

		return ResponseEntity.ok(authenticationService.signup(signUpRequest));

	}

	@PostMapping("/signin")
	public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest signinRequest) {

		return ResponseEntity.ok(authenticationService.signin(signinRequest));
	}

	@PostMapping("/signin/seller")
	public ResponseEntity<JwtAuthenticationResponse> sellerSignin(@RequestBody SignInRequest signinRequest) {

		return ResponseEntity.ok(authenticationService.sellerSignin(signinRequest));
	}

	@PostMapping("/signin/admin")
	public ResponseEntity<JwtAuthenticationResponse> adminSignin(@RequestBody SignInRequest signinRequest) {

		return ResponseEntity.ok(authenticationService.adminSignin(signinRequest));
	}

	@PostMapping("/refresh")
	public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
		return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
	}

}
