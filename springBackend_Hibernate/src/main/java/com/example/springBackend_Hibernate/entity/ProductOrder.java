package com.example.springBackend_Hibernate.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Product_Order", schema = "shop")
public class ProductOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
