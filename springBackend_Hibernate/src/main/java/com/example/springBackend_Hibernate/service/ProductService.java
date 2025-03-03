package com.example.springBackend_Hibernate.service;

import com.example.springBackend_Hibernate.entity.Product;
import com.example.springBackend_Hibernate.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setPrice(productDetails.getPrice());
        product.setName(productDetails.getName());
        product.setManufacturer(productDetails.getManufacturer());
        product.setType(productDetails.getType());
        product.setProcessor(productDetails.getProcessor());
        product.setMotherboard(productDetails.getMotherboard());
        product.setGraphicCard(productDetails.getGraphicCard());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
