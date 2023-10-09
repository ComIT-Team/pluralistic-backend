package org.comit.pluralisticsecurity.service;

import org.comit.pluralisticsecurity.dto.JwtAuthenticationResponse;
import org.comit.pluralisticsecurity.dto.RefreshTokenRequest;
import org.comit.pluralisticsecurity.dto.SignInRequest;
import org.comit.pluralisticsecurity.dto.SignUpRequest;
import org.comit.pluralisticsecurity.entity.User;

public interface AuthenticationService {
	
	public User signup(SignUpRequest signUpRequest);
	public JwtAuthenticationResponse signin(SignInRequest signInRequest);
	public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
