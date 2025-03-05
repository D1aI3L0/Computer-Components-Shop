package com.example.springBackend_Hibernate.mapper;

import com.example.springBackend_Hibernate.dto.OrderDTO;
import com.example.springBackend_Hibernate.entity.Order;

public class OrderMapper {

    ProductMapper productMapper = new ProductMapper();

    public OrderDTO toDTO(Order order)
    {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setPaymentMethod(order.getPaymentMethod());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setClient(order.getClient());
        orderDTO.setProducts(productMapper.fromProductToProductDTOList(order.getProducts()));
        return orderDTO;
    }

    public Order toEntity(OrderDTO orderDTO)
    {
        Order order = new Order();
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setOrderDate(orderDTO.getOrderDate());
        order.setPaymentMethod(orderDTO.getPaymentMethod());
        order.setStatus(orderDTO.getStatus());
        order.setClient(orderDTO.getClient());
        order.setProducts(productMapper.fromProductDTOToProductListWithID(orderDTO.getProducts()));
        return order;
    }
}
