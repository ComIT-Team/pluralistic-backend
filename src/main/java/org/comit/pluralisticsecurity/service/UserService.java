package org.comit.pluralisticsecurity.service;

import java.util.List;

import org.comit.pluralisticsecurity.dto.SellerRequest;
import org.comit.pluralisticsecurity.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
	
	//UserDetailsService userDetailsService();
	User findCurrentUserID(String currentPrincipalName);

	void saveSellerDetails(SellerRequest sellerDetails, User user);

	void changeRole(User user);
	
	void deleteUser(Integer idUser);

	void deleteSeller(Integer idUser);

	 List<User> getAllUsers();
	 List<User> getAllSellers();

}
