package org.comit.pluralisticsecurity.entity;

// Product.java
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;

    @OneToOne(mappedBy = "product")
    private OrderDetails orderDetails;
}
