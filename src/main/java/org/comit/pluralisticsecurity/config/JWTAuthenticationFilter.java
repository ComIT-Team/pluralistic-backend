package org.comit.pluralisticsecurity.config;

import java.io.IOException;

import org.comit.pluralisticsecurity.security.CustomUserDetails;
import org.comit.pluralisticsecurity.security.CustomUserDetailsService;
import org.comit.pluralisticsecurity.service.JWTService;
import org.comit.pluralisticsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JWTService jwtService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	private HandlerExceptionResolver exceptionResolver;

	@Autowired
	public JWTAuthenticationFilter(HandlerExceptionResolver exceptionResolver) {
		this.exceptionResolver = exceptionResolver;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String userEmail;
		try {
			if (StringUtils.isEmpty(authHeader)
					|| !org.apache.commons.lang3.StringUtils.startsWith(authHeader, "Bearer ")) {
				filterChain.doFilter(request, response);
				return;
			}

			jwt = authHeader.substring(7);
			userEmail = jwtService.extractUserName(jwt);

			if (StringUtils.isNotEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = customUserDetailsService.loadUserByUsername(userEmail);
				//UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);

				if (jwtService.isTokenValid(jwt, userDetails)) {
					SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

					UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,
							null, userDetails.getAuthorities());

					token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					securityContext.setAuthentication(token);

					SecurityContextHolder.setContext(securityContext);
					// SecurityContextHolder.getContext().setAuthentication(token);
				}
			}

			filterChain.doFilter(request, response);
		} catch (ExpiredJwtException | SignatureException ex) {
			exceptionResolver.resolveException(request, response, null, ex);
		}

	}

}