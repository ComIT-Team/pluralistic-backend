package org.comit.pluralisticsecurity.service.impl;

import org.comit.pluralisticsecurity.repository.UserRepository;
import org.comit.pluralisticsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService{
	@Autowired
	  UserRepository userRepository; 
	
	@Override
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) {

				return userRepository.findByEmail(username)
						.orElseThrow(() -> new UsernameNotFoundException("User not Found"));
			}
		};
	}

}
