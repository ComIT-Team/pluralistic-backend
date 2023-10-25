package org.comit.pluralisticsecurity.service.impl;

import java.util.Optional;

import org.comit.pluralisticsecurity.dto.SellerRequest;
import org.comit.pluralisticsecurity.entity.Role;
import org.comit.pluralisticsecurity.entity.RoleEnum;
import org.comit.pluralisticsecurity.entity.Seller;
import org.comit.pluralisticsecurity.entity.User;
import org.comit.pluralisticsecurity.repository.SellerRepository;
import org.comit.pluralisticsecurity.repository.UserRepository;
import org.comit.pluralisticsecurity.repository.UserRoleRepository;
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
	private   UserRepository userRepository; 
	
	@Autowired
	private  SellerRepository sellerRepository;
	
	@Autowired
	private  UserRoleRepository userRoleRepository;
	
	
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

	@Override
	public User findCurrentUserID(String currentPrincipalName) {

		return this.userRepository.findCurrentUserID(currentPrincipalName);
	}

	@Override
	public void saveSellerDetails(SellerRequest sellerRequest, User user) {
		
		User currentUser_Seller = new User(user.getIdUser());
		Seller seller = new Seller();
		seller.setUser(currentUser_Seller);
		seller.setSellername(sellerRequest.getSellerName());
		seller.setInteracID(sellerRequest.getInteracId());
		this.sellerRepository.save(seller);
		
	}

	@Override
	public void changeRole(User user) {
		
		
		Role role = new Role(RoleEnum.SELLER.ordinal());
		
		 userRoleRepository.changeRole(role.getIdRole(),user.getIdUser());
		
	}

	
}
