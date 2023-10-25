package org.comit.pluralisticsecurity.controller;

import org.comit.pluralisticsecurity.dto.JwtAuthenticationResponse;
import org.comit.pluralisticsecurity.dto.RefreshTokenRequest;
//import org.comit.pluralisticsecurity.dto.SellerDetails;
import org.comit.pluralisticsecurity.dto.SignInRequest;
import org.comit.pluralisticsecurity.dto.SignUpRequest;
import org.comit.pluralisticsecurity.entity.User;
import org.comit.pluralisticsecurity.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	private  AuthenticationService authenticationService;

	@PostMapping("/signup")
	public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest) {

		return ResponseEntity.ok(authenticationService.signup(signUpRequest));

	}

	@PostMapping("/signin")
	public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest signinRequest) {

		return ResponseEntity.ok(authenticationService.signin(signinRequest));
	}

	@PostMapping("/refresh")
	public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
		return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
	}

}
