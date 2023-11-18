package org.comit.pluralisticsecurity;

import java.util.ArrayList;

import org.comit.pluralisticsecurity.entity.Role;
import org.comit.pluralisticsecurity.entity.RoleEnum;
import org.comit.pluralisticsecurity.entity.User;
import org.comit.pluralisticsecurity.entity.UserRole;
import org.comit.pluralisticsecurity.repository.RoleRepository;
import org.comit.pluralisticsecurity.repository.UserRepository;
import org.comit.pluralisticsecurity.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class DemoRunner implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Override
	public void run(String... args) throws Exception {

		UserRole adminRole = userRoleRepository.findAdminRole(Integer.valueOf(RoleEnum.ADMIN.ordinal()));

		if (null == adminRole) {
			User user = new User();
			UserRole userRoles = new UserRole();
			Role roles = new Role(Integer.valueOf(RoleEnum.ADMIN.ordinal()));
			// Role roles = new Role(RoleEnum.ADMIN.toString());

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

}
