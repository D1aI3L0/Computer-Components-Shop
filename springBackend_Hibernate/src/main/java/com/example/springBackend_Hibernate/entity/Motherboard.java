package com.example.springBackend_Hibernate.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Motherboard", schema = "shop")
public class Motherboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "memory_slots")
    private Integer memorySlots;

    private String chipset;

    @Column(name = "form_factor")
    private String formFactor;
}