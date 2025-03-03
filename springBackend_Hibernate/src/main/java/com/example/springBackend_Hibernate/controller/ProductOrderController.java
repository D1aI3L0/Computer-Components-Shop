package com.example.springBackend_Hibernate.controller;


import com.example.springBackend_Hibernate.entity.ProductOrder;
import com.example.springBackend_Hibernate.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-orders")
public class ProductOrderController {

    @Autowired
    private ProductOrderService productOrderService;

    @GetMapping
    public List<ProductOrder> getAllProductOrders() {
        return productOrderService.getAllProductOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductOrder> getProductOrderById(@PathVariable Long id) {
        return productOrderService.getProductOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProductOrder createProductOrder(@RequestBody ProductOrder productOrder) {
        return productOrderService.createProductOrder(productOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductOrder> updateProductOrder(@PathVariable Long id, @RequestBody ProductOrder productOrderDetails) {
        return ResponseEntity.ok(productOrderService.updateProductOrder(id, productOrderDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductOrder(@PathVariable Long id) {
        productOrderService.deleteProductOrder(id);
        return ResponseEntity.noContent().build();
    }
}
