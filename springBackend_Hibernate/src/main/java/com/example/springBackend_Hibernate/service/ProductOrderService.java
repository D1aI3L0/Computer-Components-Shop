package com.example.springBackend_Hibernate.service;


import com.example.springBackend_Hibernate.entity.ProductOrder;
import com.example.springBackend_Hibernate.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductOrderService {

    @Autowired
    private ProductOrderRepository productOrderRepository;

    public List<ProductOrder> getAllProductOrders() {
        return productOrderRepository.findAll();
    }

    public Optional<ProductOrder> getProductOrderById(Long id) {
        return productOrderRepository.findById(id);
    }

    public ProductOrder createProductOrder(ProductOrder productOrder) {
        return productOrderRepository.save(productOrder);
    }

    public ProductOrder updateProductOrder(Long id, ProductOrder productOrderDetails) {
        ProductOrder productOrder = productOrderRepository.findById(id).orElseThrow(() -> new RuntimeException("ProductOrder not found"));
        productOrder.setProduct(productOrderDetails.getProduct());
        productOrder.setOrder(productOrderDetails.getOrder());
        return productOrderRepository.save(productOrder);
    }

    public void deleteProductOrder(Long id) {
        productOrderRepository.deleteById(id);
    }
}
