package org.comit.pluralisticsecurity.entity;

// OrderDetails.java
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "order_details")
@Data
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Product product;

    @ManyToOne // Corrected mapping
    private Order order; // Use the property name 'order' to match the 'mappedBy' attribute in the Order entity.
}
