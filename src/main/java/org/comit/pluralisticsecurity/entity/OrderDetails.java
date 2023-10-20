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
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
