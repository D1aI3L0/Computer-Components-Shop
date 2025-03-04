package com.example.springBackend_Hibernate.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Processor", schema = "shop")
public class Processor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "threads_count")
    private Integer threadsCount;

    @Column(name = "clock_frequency")
    private Double clockFrequency;

    @Column(name = "max_frequency")
    private Double maxFrequency;

    @Column(name = "cpu_count")
    private Integer cpuCount;
}