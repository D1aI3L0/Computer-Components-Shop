package com.example.springBackend_Hibernate.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Motherboard", schema = "shop")
public class Motherboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "memory_slots")
    private Integer memorySlots;

    @Column(name = "chipset")
    private String chipset;

    @Column(name = "form_factor")
    private String formFactor;
}