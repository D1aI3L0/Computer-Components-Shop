package com.example.springBackend_Hibernate.repository;

import com.example.springBackend_Hibernate.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
}
