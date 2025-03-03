package com.example.springBackend_Hibernate.repository;

import com.example.springBackend_Hibernate.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
