package com.example.springBackend_Hibernate.mapper;

import com.example.springBackend_Hibernate.dto.GraphicCardDTO;
import com.example.springBackend_Hibernate.entity.GraphicCard;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GraphicCardMapper {
    GraphicCardDTO toDTO(GraphicCard graphicCard);
    GraphicCard toEntity(GraphicCardDTO graphicCardDTO);
}
