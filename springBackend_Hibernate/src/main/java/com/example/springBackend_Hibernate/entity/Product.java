package com.example.springBackend_Hibernate.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Product", schema = "shop")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}