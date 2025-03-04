package com.example.springBackend_Hibernate.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Review", schema = "shop")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "review")
    private String review;
}
