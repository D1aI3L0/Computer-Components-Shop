package com.example.springBackend_Hibernate.repository;

import com.example.springBackend_Hibernate.entity.GraphicCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraphicCardRepository extends JpaRepository<GraphicCard, Long> {
}
