package org.comit.pluralisticsecurity.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {

	String token;
	String refreshToken;

	public String getToken() {
		return token;
	}

	
	
	public void setToken(String token) {
		this.token = token;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}
