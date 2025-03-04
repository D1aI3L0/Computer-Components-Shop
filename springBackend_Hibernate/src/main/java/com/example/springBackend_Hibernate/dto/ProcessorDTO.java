package com.example.springBackend_Hibernate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessorDTO {

    private Long id;
    private Integer threadsCount;
    private Double clockFrequency;
    private Double maxFrequency;
    private Integer cpuCount;
}
