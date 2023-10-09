package org.comit.pluralisticsecurity.service.impl;


import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.comit.pluralisticsecurity.service.JWTService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTServiceimpl implements JWTService {
	
	
	//generate token 
	public String generateToken(UserDetails userDetails) {
		return Jwts.builder()
				   .setSubject(userDetails.getUsername())
				   .setIssuedAt(new Date(System.currentTimeMillis()))
				   .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) //token valid for 1 day
				   .signWith(getSignKey(), SignatureAlgorithm.HS256)
				   .compact();
			
	}
	
	public String generateRefreshToken(Map<String,Object> extraClaims, UserDetails userDetails) {
		return Jwts.builder().setClaims(extraClaims)
				   .setSubject(userDetails.getUsername())
				   .setIssuedAt(new Date(System.currentTimeMillis()))
				   .setExpiration(new Date(System.currentTimeMillis() + 604800000))  //JWT valid for 7 days
				   .signWith(getSignKey(), SignatureAlgorithm.HS256)
				   .compact();
			
	}
	
	//method to extract username
	//this method will return email in the particular token
	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	//extract username from the token
	//extract the claims from the token
	
	public <T> T extractClaim(String token, Function<Claims, T> claimResolvers) {
		final Claims claims = extractAllClaims(token);
		return claimResolvers.apply(claims);
	}

	public Key getSignKey() {
		byte[] key = Decoders.BASE64.decode("413F4428472B4B6250655368566D5970337336763979244226452948404D6351");
		return Keys.hmacShaKeyFor(key);
	}

	//this method will return all the claims from the token
	public Claims extractAllClaims(String token) {

		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {
		
		final String username = extractUserName(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	//get expiration date and checks with current date
	public boolean isTokenExpired(String token) {

		return extractClaim(token, Claims::getExpiration).before(new Date());
	}

}
