package com.example.springBackend_Hibernate.repository;

import com.example.springBackend_Hibernate.entity.Processor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessorRepository extends JpaRepository<Processor, Long> {
}
