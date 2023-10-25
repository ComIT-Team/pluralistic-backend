package org.comit.pluralisticsecurity.util;

import org.comit.pluralisticsecurity.entity.User;
import org.comit.pluralisticsecurity.service.JWTService;
import org.comit.pluralisticsecurity.service.impl.JWTServiceimpl;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

public class JwtGenerator {
    public static void main(String[] args) {
        // Create an instance of your JWTService implementation
        JWTService jwtService = new JWTServiceimpl();

        // Create a UserDetails object (you can mock this for testing)
        UserDetails userDetails = new User("test@gmail.com", "test", new ArrayList<>());

        // Generate a JWT token
        String jwtToken = jwtService.generateToken(userDetails);

        // Print the JWT token
        System.out.println("Generated JWT Token: " + jwtToken);
    }
}
