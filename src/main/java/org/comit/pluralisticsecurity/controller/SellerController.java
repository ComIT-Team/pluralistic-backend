package org.comit.pluralisticsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/seller")

public class SellerController {

	@GetMapping
	public ResponseEntity<String> sayHello(){
		System.out.println("Hi Seller");
		return ResponseEntity.ok("Hi Seller");
	}
}
