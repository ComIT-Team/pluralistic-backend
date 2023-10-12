package org.comit.pluralisticsecurity;

import java.util.ArrayList;
import java.util.Optional;

import org.comit.pluralisticsecurity.entity.Role;
import org.comit.pluralisticsecurity.entity.RoleEnum;
import org.comit.pluralisticsecurity.entity.User;
import org.comit.pluralisticsecurity.entity.UserRole;
import org.comit.pluralisticsecurity.repository.UserRepository;
import org.comit.pluralisticsecurity.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PluralisticSecurityApplication implements CommandLineRunner {
	
	@Autowired
	private  UserRepository userRepository ;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(PluralisticSecurityApplication.class, args);
	}
	public void run(String... args) {
		UserRole userRole = new UserRole();
		Optional<UserRole> adminAccount = userRoleRepository.findByRole(RoleEnum.ADMIN.ordinal());
		//System.out.println(adminAccount);
		//Optional<UserRole> adminAccount =  userRepository.findByRole(RoleEnum.ADMIN);
		if(null == adminAccount) {
			
			User user = new User();
			Role role = new Role(RoleEnum.ADMIN.ordinal());
			
			
			user.setEmail("admin@gmail.com");
			user.setFirstname("admin");
			user.setLastname("admin");
			//user.setRole(Roles.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRole.setRole(role);
			userRole.setUser(user);
			
			user.setUserRoles(new ArrayList<>());
			user.getUserRoles().add(userRole);
			
			
			userRepository.save(user);
			
			
		}
		
		
	}

}
