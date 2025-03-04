package com.example.springBackend_Hibernate.dto;

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
    private ProcessorDTO processor;
    private GraphicCardDTO graphicCard;
    private MotherboardDTO motherboard;
}