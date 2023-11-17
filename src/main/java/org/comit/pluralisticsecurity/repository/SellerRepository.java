package org.comit.pluralisticsecurity.repository;

import org.comit.pluralisticsecurity.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = " delete from seller where id_user = ?1",nativeQuery = true)
	public void deleteSeller(Integer idUser);

	
}

