package com.example.springBackend_Hibernate.dto;

import com.example.springBackend_Hibernate.entity.GraphicCard;
import com.example.springBackend_Hibernate.entity.Motherboard;
import com.example.springBackend_Hibernate.entity.Processor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private Double price;
    private String name;
    private String manufacturer;
    private String type;
    private Processor processor;
    private GraphicCard graphicCard;
    private Motherboard motherboard;
}