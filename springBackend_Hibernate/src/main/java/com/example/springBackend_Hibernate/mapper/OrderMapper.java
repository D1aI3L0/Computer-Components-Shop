package com.example.springBackend_Hibernate.mapper;

import com.example.springBackend_Hibernate.dto.OrderDTO;
import com.example.springBackend_Hibernate.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface OrderMapper {
    OrderDTO toDTO(Order order);
    Order toEntity(OrderDTO orderDTO);
}