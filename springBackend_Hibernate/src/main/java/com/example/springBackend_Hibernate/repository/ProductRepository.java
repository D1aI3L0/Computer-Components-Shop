package com.example.springBackend_Hibernate.repository;

import com.example.springBackend_Hibernate.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
