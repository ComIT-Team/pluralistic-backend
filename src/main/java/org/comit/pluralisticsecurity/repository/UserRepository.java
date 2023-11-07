package org.comit.pluralisticsecurity.repository;

import org.comit.pluralisticsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	@Query(value = "SELECT * FROM USERS WHERE EMAIL = ?1", nativeQuery = true)
	User findByEmail(String email);

	@Query(value = "SELECT * FROM USERS WHERE EMAIL = ?1", nativeQuery = true)
	User findCurrentUserID(String currentPrincipalName);

}
