package org.comit.pluralisticsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.comit.pluralisticsecurity.dto.JwtAuthenticationResponse;
import org.comit.pluralisticsecurity.dto.SignInRequest;
import org.comit.pluralisticsecurity.dto.SignUpRequest;
import org.comit.pluralisticsecurity.entity.User;
import org.comit.pluralisticsecurity.service.AuthenticationService;
import org.comit.pluralisticsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://127.0.0.1:5173") // Replace with your frontend URL

@RequiredArgsConstructor
public class AuthenticationController {

	@Autowired
	AuthenticationService authenticationService;

	@Autowired
	UserService userService;

	@PostMapping("/signup")
	@CrossOrigin(origins = "http://127.0.0.1:5173") // Replace with your frontend URL
	public ResponseEntity<Optional<User>> signup(@RequestBody SignUpRequest signUpRequest) throws Exception{

		return ResponseEntity.ok(authenticationService.signup(signUpRequest));

	}

	@PostMapping("/signin")
	@CrossOrigin(origins = "http://127.0.0.1:5173") // Replace with your frontend URL

	public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest signinRequest) {

		return ResponseEntity.ok(authenticationService.signin(signinRequest));
	}

	@PostMapping("/signin/seller")
	@CrossOrigin(origins = "http://127.0.0.1:5173") // Replace with your frontend URL
	public ResponseEntity<JwtAuthenticationResponse> sellerSignin(@RequestBody SignInRequest signinRequest) {

		return ResponseEntity.ok(authenticationService.sellerSignin(signinRequest));
	}

	@PostMapping("/signin/admin")
	@CrossOrigin(origins = "http://127.0.0.1:5173") // Replace with your frontend URL
	public ResponseEntity<JwtAuthenticationResponse> adminSignin(@RequestBody SignInRequest signinRequest) {

		return ResponseEntity.ok(authenticationService.adminSignin(signinRequest));
	}

	/*@PostMapping("/refresh")
	public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
		return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
	}*/

}
