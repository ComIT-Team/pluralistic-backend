package org.comit.pluralisticsecurity.repository;

import org.comit.pluralisticsecurity.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
    // You can add custom query methods here if needed.
}