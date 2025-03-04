package com.example.springBackend_Hibernate.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Client", schema = "shop")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "email_address", unique = true)
    private String emailAddress;
}