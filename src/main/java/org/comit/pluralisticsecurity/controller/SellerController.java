package org.comit.pluralisticsecurity.controller;

import org.comit.pluralisticsecurity.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/seller")

public class SellerController {

	@GetMapping
	public ResponseEntity<String> sayHello(){
		System.out.println("Hi Seller");
		return ResponseEntity.ok("Hi Seller");
	}
}
