package com.example.springBackend_Hibernate.repository;

import com.example.springBackend_Hibernate.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
