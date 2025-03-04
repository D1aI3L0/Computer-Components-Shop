package com.example.springBackend_Hibernate.mapper;

import com.example.springBackend_Hibernate.dto.MotherboardDTO;
import com.example.springBackend_Hibernate.entity.Motherboard;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MotherboardMapper {
    MotherboardDTO toDTO(Motherboard motherboard);
    Motherboard toEntity(MotherboardDTO motherboardDTO);
}
