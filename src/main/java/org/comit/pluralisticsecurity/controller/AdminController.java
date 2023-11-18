package org.comit.pluralisticsecurity.controller;

import java.util.List;

import org.comit.pluralisticsecurity.entity.Product;
import org.comit.pluralisticsecurity.entity.User;
import org.comit.pluralisticsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/admin")

@RequiredArgsConstructor
public class AdminController {
	
	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<String> sayHello(){
		return ResponseEntity.ok("Hi Admin");
	}
	
	@GetMapping("/listallusers")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@GetMapping("/listallsellers")
	public ResponseEntity<List<User>> getAllSellers(){
		List<User> sellers = userService.getAllSellers();
        return new ResponseEntity<>(sellers, HttpStatus.OK);
	}
	
	@GetMapping("/deleteuser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer id) {

		this.userService.deleteUser(id);
		return ResponseEntity.ok("Deleted user with id " + id);
	}

	@GetMapping("/deleteseller/{id}")
	public ResponseEntity<String> deleteSeller(@PathVariable Integer id) {
		this.userService.deleteSeller(id);
		this.userService.deleteUser(id);
		
		return ResponseEntity.ok("Deleted seller with id " + id);
	}

}
