package com.example.springBackend_Hibernate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GraphicCardDTO {

    private Long id;
    private Integer gpuCount;
    private Double gpuFrequency;
    private Integer memoryCount;
    private Double memoryFrequency;
}