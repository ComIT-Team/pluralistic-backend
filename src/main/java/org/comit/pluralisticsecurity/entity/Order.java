package org.comit.pluralisticsecurity.entity;

// Order.java
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "orders") // Using plural table name
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Use 'id' as the primary key column name
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id") // Use the referenced table name followed by _id for foreign keys
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetails;
}