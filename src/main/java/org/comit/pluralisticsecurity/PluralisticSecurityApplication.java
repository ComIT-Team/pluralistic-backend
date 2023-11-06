package org.comit.pluralisticsecurity;

import java.util.ArrayList;

import org.comit.pluralisticsecurity.dto.SignInRequest;
import org.comit.pluralisticsecurity.entity.Role;
import org.comit.pluralisticsecurity.entity.RoleEnum;
import org.comit.pluralisticsecurity.entity.User;
import org.comit.pluralisticsecurity.entity.UserRole;
import org.comit.pluralisticsecurity.repository.RoleRepository;
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
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	public static void main(String[] args) {
		SpringApplication.run(PluralisticSecurityApplication.class, args);
	}

	public void run(SignInRequest signInRequest, String... args) {

		
		var currentUser = userRepository.findByEmail(signInRequest.getEmail());
		UserRole userRoles = userRoleRepository.findIdRole(currentUser.getIdUser());
		Role role = roleRepository.findNameRole(userRoles.getRole().getIdRole());

		if (!role.getNameRole().equals(RoleEnum.ADMIN.name())) {

			User user = new User();
			Role roles = new Role(RoleEnum.ADMIN.toString());
		
			user.setEmail("admin@gmail.com");
			user.setFirstname("admin");
			user.setLastname("admin");
			user.setUsername("admin");
			user.setActive(true);

			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRoles.setRole(roles);
			userRoles.setUser(user);

			user.setUserRoles(new ArrayList<>());
			user.getUserRoles().add(userRoles);

			userRepository.save(user);

		}

	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

	}

}
