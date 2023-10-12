package org.comit.pluralisticsecurity.repository;

import java.util.Optional;

import org.comit.pluralisticsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByEmail(String email);
	
	
}

