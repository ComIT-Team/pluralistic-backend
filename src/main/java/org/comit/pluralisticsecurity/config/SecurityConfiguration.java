package org.comit.pluralisticsecurity.config;

import org.comit.pluralisticsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity

@RequiredArgsConstructor
public class SecurityConfiguration {
	// @Autowired
	//private final JWTAuthenticationFilter jwtAuthenticationFilter;
	 @Autowired
	private  UserService userService;

	@Autowired
	@Qualifier("handlerExceptionResolver")
	private HandlerExceptionResolver exceptionResolver;
	
	@Bean
	public JWTAuthenticationFilter jwtAuthenticationFilter() {
		return new JWTAuthenticationFilter(exceptionResolver);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(request -> request.requestMatchers("/api/v1/auth/**").permitAll()
						.requestMatchers("/api/v1/admin").hasAnyAuthority("ADMIN")
						.requestMatchers("/api/v1/user").hasAnyAuthority("USER")
						.requestMatchers("/api/v1/seller").hasAnyAuthority("SELLER")
						.anyRequest().authenticated())

				.sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				.authenticationProvider(authenticationProvider())
				.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();

	}

	@Bean
	public AuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userService.userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {

		return config.getAuthenticationManager();
	}

}
