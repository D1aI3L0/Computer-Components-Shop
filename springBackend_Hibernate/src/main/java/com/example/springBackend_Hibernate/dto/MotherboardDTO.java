package com.example.springBackend_Hibernate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MotherboardDTO {
    private Long id;
    private Integer memorySlots;
    private String chipset;
    private String formFactor;
}
