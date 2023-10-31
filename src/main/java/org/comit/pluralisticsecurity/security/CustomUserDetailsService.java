package org.comit.pluralisticsecurity.security;

import org.comit.pluralisticsecurity.entity.User;
import org.comit.pluralisticsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("UserDetailsService")
public class CustomUserDetailsService implements UserDetailsService  {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user =this.userRepository.findByEmail(username); 
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found: " + username);
		}
		
		return new CustomUserDetails(user);
	}

}
