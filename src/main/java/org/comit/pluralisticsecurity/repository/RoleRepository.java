package org.comit.pluralisticsecurity.repository;

import org.comit.pluralisticsecurity.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	/*@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE ROLES SET NAME= ?1 WHERE ID = ?2",nativeQuery = true)
	public void updateRole(String nameRole,Integer idRole);*/
	
	@Query(value = "SELECT * FROM ROLES WHERE ID= ?1",nativeQuery = true)
	public Role findNameRole(Integer idRole); 
}
