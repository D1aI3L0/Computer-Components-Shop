package com.example.springBackend_Hibernate.repository;

import com.example.springBackend_Hibernate.entity.Motherboard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotherboardRepository extends JpaRepository<Motherboard, Long> {
}
