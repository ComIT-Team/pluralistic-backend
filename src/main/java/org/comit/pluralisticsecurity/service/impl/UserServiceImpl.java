package org.comit.pluralisticsecurity.service.impl;

import org.comit.pluralisticsecurity.dto.SellerRequest;
import org.comit.pluralisticsecurity.entity.Role;
import org.comit.pluralisticsecurity.entity.RoleEnum;
import org.comit.pluralisticsecurity.entity.Seller;
import org.comit.pluralisticsecurity.entity.User;
import org.comit.pluralisticsecurity.entity.UserRole;
import org.comit.pluralisticsecurity.repository.RoleRepository;
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
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SellerRepository sellerRepository;

	@Autowired
	private  UserRoleRepository userRoleRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	/*@Override
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username){

				return userRepository.findByEmail(username)
						.orElseThrow(() -> new UsernameNotFoundException("User not Found"));
			}
		};
	}*/
	

	
	public User findCurrentUserID(String currentPrincipalName) {

		return this.userRepository.findCurrentUserID(currentPrincipalName);
	}

	
	public void saveSellerDetails(SellerRequest sellerRequest, User user) {
		
		User currentUser_Seller = new User(user.getIdUser());
		Seller seller = new Seller(currentUser_Seller,sellerRequest.getSellerName(),sellerRequest.getSellerName());
		//seller.setUser(currentUser_Seller);
		//seller.setSellername(sellerRequest.getSellerName());
		//seller.setInteracID(sellerRequest.getInteracId());
		sellerRepository.save(seller);
		
	}

	
	public void changeRole(User user) {
		
		
		//Role role = new Role(Integer.valueOf(RoleEnum.SELLER.ordinal()));
		Role roleSeller = new Role(RoleEnum.SELLER.name());
		//roleSeller.setNameRole(RoleEnum.SELLER.name().toUpperCase());
		UserRole userRole = userRoleRepository.findIdRole(user.getIdUser());
		roleRepository.updateRole(roleSeller.getNameRole(),userRole.getRole().getIdRole());
		// userRoleRepository.changeRole(role.getIdRole(),user.getIdUser());
		
	}

	
}
