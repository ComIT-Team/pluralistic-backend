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
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "image_url")
    private String imageUrl; // New field for the image URL

    @OneToOne(mappedBy = "product")
    private OrderDetails orderDetails;
}
