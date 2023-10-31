package org.comit.pluralisticsecurity.repository;

import org.comit.pluralisticsecurity.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {

}
