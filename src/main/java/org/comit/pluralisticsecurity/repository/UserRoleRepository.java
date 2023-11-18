package org.comit.pluralisticsecurity.repository;

import java.util.Optional;

import org.comit.pluralisticsecurity.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

	@Query(value = "SELECT * FROM USER_ROLES WHERE ROLE_ID = ?1", nativeQuery = true)
	UserRole findByRole(Integer role);

	@Query(value = "SELECT * FROM USER_ROLES WHERE USER_ID = ?1", nativeQuery = true)
	UserRole findRole(Integer idUser);

	@Modifying(clearAutomatically = true) // (flushAutomatically = true) //(clearAutomatically = true) -> we make sure
										// that the persistence context is cleared after our query execution.
	@Transactional
	@Query(value = "UPDATE USER_ROLES  SET ROLE_ID = ?1 WHERE USER_ID =?2", nativeQuery = true)
	Integer changeRole(Integer id_role, Integer id_user);

	@Query(value="SELECT * FROM USER_ROLES WHERE USER_ID = ?1", nativeQuery = true)
	UserRole findIdRole(Integer id_user);
	
	@Modifying
	@Transactional
	@Query(value ="DELETE FROM USER_ROLES WHERE USER_ID = ?1",nativeQuery = true)
	void deleteUser(Integer id_user);
	
	@Query(value = "SELECT * FROM USER_ROLES WHERE ROLE_ID = ?1",nativeQuery = true)
	UserRole findAdminRole(Integer id_role);
}
