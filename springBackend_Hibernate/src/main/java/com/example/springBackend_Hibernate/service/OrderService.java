package com.example.springBackend_Hibernate.service;

import com.example.springBackend_Hibernate.MEntityNotFoundException;
import com.example.springBackend_Hibernate.dto.OrderDTO;
import com.example.springBackend_Hibernate.entity.Order;
import com.example.springBackend_Hibernate.mapper.OrderMapper;
import com.example.springBackend_Hibernate.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    private final OrderMapper orderMapper = new OrderMapper();

    public List<OrderDTO> getAllOrders() throws InterruptedException {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::toDTO)
                .orElse(null);
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toDTO(savedOrder);
    }

    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) throws MEntityNotFoundException {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new MEntityNotFoundException("Order not found with id:" + id));

        existingOrder.setTotalPrice(orderDTO.getTotalPrice());
        existingOrder.setOrderDate(orderDTO.getOrderDate());
        existingOrder.setStatus(orderDTO.getStatus());
        existingOrder.setPaymentMethod(orderDTO.getPaymentMethod());
        existingOrder.setClient(orderDTO.getClient());

        Order updatedOrder = orderRepository.save(existingOrder);
        return orderMapper.toDTO(updatedOrder);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
