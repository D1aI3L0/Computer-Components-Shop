package com.example.springBackend_Hibernate.mapper;

import com.example.springBackend_Hibernate.dto.ProductDTO;
import com.example.springBackend_Hibernate.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MotherboardMapper.class, ProcessorMapper.class, GraphicCardMapper.class})
public interface ProductMapper {
    ProductDTO toDTO(Product product);
    @Mapping(target = "orders", ignore = true)
    Product toEntity(ProductDTO productDTO);
}