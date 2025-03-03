package com.example.springBackend_Hibernate.service;

import com.example.springBackend_Hibernate.entity.Order;
import com.example.springBackend_Hibernate.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Получить все заказы
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Получить заказ по ID
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    // Создать новый заказ
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    // Обновить заказ
    public Order updateOrder(Long id, Order orderDetails) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setTotalPrice(orderDetails.getTotalPrice());
        order.setOrderDate(orderDetails.getOrderDate());
        order.setStatus(orderDetails.getStatus());
        order.setPaymentMethod(orderDetails.getPaymentMethod());
        order.setClient(orderDetails.getClient());
        return orderRepository.save(order);
    }

    // Удалить заказ
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
