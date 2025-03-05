package com.example.springBackend_Hibernate.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Product", schema = "shop")
public class Product {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Double price;
    private String name;
    private String manufacturer;
    private String type;

    @OneToOne
    @JoinColumn(name = "processor_id")
    private Processor processor;

    @OneToOne
    @JoinColumn(name = "motherboard_id")
    private Motherboard motherboard;

    @OneToOne
    @JoinColumn(name = "graphic_card_id")
    private GraphicCard graphicCard;

    @JsonBackReference
    @ManyToMany(mappedBy = "products")
    List<Order> orders;
}