package com.example.springBackend_Hibernate.mapper;

import com.example.springBackend_Hibernate.dto.ProcessorDTO;
import com.example.springBackend_Hibernate.entity.Processor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProcessorMapper {
    ProcessorDTO toDTO(Processor processor);
    Processor toEntity(ProcessorDTO processorDTO);
}
