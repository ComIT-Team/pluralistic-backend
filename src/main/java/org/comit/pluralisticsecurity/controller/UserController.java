package org.comit.pluralisticsecurity.controller;

import org.comit.pluralisticsecurity.dto.SellerRequest;
import org.comit.pluralisticsecurity.entity.User;
import org.comit.pluralisticsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")

@RequiredArgsConstructor

public class UserController {
	
	@Autowired
	private UserService userService;
	
	

	@GetMapping
	public ResponseEntity<String> sayHello() {
		User user = findCurrentUserDetails();
		return ResponseEntity.ok("HI " + user.getFirstname()+ ". Welcome to Pluralistic!");

	}
	
	@PostMapping("/signupforseller")
	public ResponseEntity<String> saveSellerDetails(@RequestBody SellerRequest sellerDetails) {

		User user = findCurrentUserDetails(); //calling method to find current userdetails.
		userService.saveSellerDetails(sellerDetails,user);
		userService.changeRole(user);
		return ResponseEntity.ok("Congratulations!");
	}

	// method to find current userdetails

	public User findCurrentUserDetails() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		System.out.println(currentPrincipalName);

		User user = this.userService.findCurrentUserID(currentPrincipalName);
		
		return user;

	}
}