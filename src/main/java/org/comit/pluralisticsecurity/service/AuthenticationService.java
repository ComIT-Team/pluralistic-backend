package org.comit.pluralisticsecurity.service;

import java.util.Optional;

import org.comit.pluralisticsecurity.dto.JwtAuthenticationResponse;
import org.comit.pluralisticsecurity.dto.RefreshTokenRequest;
//import org.comit.pluralisticsecurity.dto.SellerDetails;
import org.comit.pluralisticsecurity.dto.SignInRequest;
import org.comit.pluralisticsecurity.dto.SignUpRequest;
import org.comit.pluralisticsecurity.entity.User;

public interface AuthenticationService {

	Optional<User> signup(SignUpRequest signUpRequest);

	JwtAuthenticationResponse signin(SignInRequest signInRequest);

	JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

	// public void saveSellerDetails(SellerDetails sellerDetails,String username);
	JwtAuthenticationResponse sellerSignin(SignInRequest signinRequest);

}
