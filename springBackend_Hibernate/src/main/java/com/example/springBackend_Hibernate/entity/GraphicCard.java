package com.example.springBackend_Hibernate.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Graphic_Card", schema = "shop")
public class GraphicCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "gpu_count")
    private Integer gpuCount;

    @Column(name = "gpu_frequency")
    private Double gpuFrequency;

    @Column(name = "memory_count")
    private Integer memoryCount;

    @Column(name = "memory_frequency")
    private Double memoryFrequency;
}