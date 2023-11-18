package org.comit.pluralisticsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.comit.pluralisticsecurity.dto.SellerRequest;
import org.comit.pluralisticsecurity.entity.User;
import org.comit.pluralisticsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "http://127.0.0.1:5173") // Replace with your frontend URL
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