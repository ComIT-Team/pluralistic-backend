package org.comit.pluralisticsecurity.repository;

import org.comit.pluralisticsecurity.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {

			@Query(value = "SELECT * FROM USER_ROLE WHERE ID_ROLE = ?1", nativeQuery = true)
			 UserRole findByRole(int role);
			
			
			@Modifying (clearAutomatically = true) //(flushAutomatically = true) //(clearAutomatically = true)  -> we make sure that the persistence context is cleared after our query execution.
			@Transactional
			@Query(value ="UPDATE USER_ROLE  SET ID_ROLE = ?1 WHERE ID_USER =?2" , nativeQuery = true)
			Integer changeRole(Integer id_role, Integer id_user);
			
}
