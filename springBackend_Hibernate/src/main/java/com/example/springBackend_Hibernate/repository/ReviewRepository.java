package com.example.springBackend_Hibernate.repository;

import com.example.springBackend_Hibernate.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
