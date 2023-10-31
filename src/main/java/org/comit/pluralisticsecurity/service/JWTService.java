package org.comit.pluralisticsecurity.service;

import java.util.Map;

import org.comit.pluralisticsecurity.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {
	
  String extractUserName(String token);
  String generateToken(User user);
 boolean isTokenValid(String token, UserDetails userDetails);
  String generateRefreshToken(Map<String,Object> extraClaims, User user);

}
