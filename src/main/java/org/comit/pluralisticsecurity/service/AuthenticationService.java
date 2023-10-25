package org.comit.pluralisticsecurity.service;

import org.comit.pluralisticsecurity.dto.JwtAuthenticationResponse;
import org.comit.pluralisticsecurity.dto.RefreshTokenRequest;
//import org.comit.pluralisticsecurity.dto.SellerDetails;
import org.comit.pluralisticsecurity.dto.SignInRequest;
import org.comit.pluralisticsecurity.dto.SignUpRequest;
import org.comit.pluralisticsecurity.entity.User;

public interface AuthenticationService {
	
	 User signup(SignUpRequest signUpRequest);
	 JwtAuthenticationResponse signin(SignInRequest signInRequest);
	 JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
	//public void saveSellerDetails(SellerDetails sellerDetails,String username);
	
}
