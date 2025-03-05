package com.example.springBackend_Hibernate.dto;

import com.example.springBackend_Hibernate.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private Double totalPrice;
    private LocalDate orderDate;
    private String status;
    private String paymentMethod;
    private Client client;
    private List<ProductDTO> products;
}