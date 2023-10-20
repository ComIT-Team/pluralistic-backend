package org.comit.pluralisticsecurity.repository;

import java.util.Optional;

import org.comit.pluralisticsecurity.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
	//UserRole userRole = new UserRole();
			@Query(value = "SELECT * FROM USER_ROLES WHERE ROLE_ID = ?1",nativeQuery = true)
			 Optional<UserRole> findByRole(int role);
			//User findByRole(User user);
}