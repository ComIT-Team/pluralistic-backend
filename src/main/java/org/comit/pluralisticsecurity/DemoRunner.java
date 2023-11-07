package org.comit.pluralisticsecurity;

import org.comit.pluralisticsecurity.entity.Role;
import org.comit.pluralisticsecurity.entity.RoleEnum;
import org.comit.pluralisticsecurity.entity.User;
import org.comit.pluralisticsecurity.entity.UserRole;
import org.comit.pluralisticsecurity.repository.UserRepository;
import org.comit.pluralisticsecurity.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DemoRunner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public void run(String... args) {
        UserRole userRole = new UserRole();
        UserRole adminAccount = userRoleRepository.findByRole(Integer.valueOf(RoleEnum.ADMIN.ordinal()));
        // UserRole adminAccount = userRoleRepository.findByRole(RoleEnum.ADMIN.toString());

        if (null == adminAccount) {

            User user = new User();
            Role role = new Role(RoleEnum.ADMIN.toString());
            // Role role = new Role(RoleEnum.ADMIN.ordinal());

            user.setEmail("admin@gmail.com");
            user.setFirstname("admin");
            user.setLastname("admin");
            user.setUsername("admin");
            user.setActive(true);

            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRole.setRole(role);
            userRole.setUser(user);

            user.setUserRoles(new ArrayList<>());
            user.getUserRoles().add(userRole);

            userRepository.save(user);
        }
    }
}
