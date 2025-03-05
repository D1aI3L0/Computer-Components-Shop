package com.example.springBackend_Hibernate.service;

import com.example.springBackend_Hibernate.MEntityNotFoundException;
import com.example.springBackend_Hibernate.dto.ProductDTO;
import com.example.springBackend_Hibernate.entity.Product;
import com.example.springBackend_Hibernate.mapper.*;
import com.example.springBackend_Hibernate.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private final ProductMapper productMapper = new ProductMapper();

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toDTO)
                .orElse(null);
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        return productMapper.toDTO(productRepository.save(productMapper.toEntity(productDTO)));
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDetails) throws MEntityNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(() -> new MEntityNotFoundException("Product not found with id: " + id));
        product.setPrice(productDetails.getPrice());
        product.setName(productDetails.getName());
        product.setManufacturer(productDetails.getManufacturer());
        product.setType(productDetails.getType());
        product.setProcessor(productDetails.getProcessor());
        product.setMotherboard(productDetails.getMotherboard());
        product.setGraphicCard(productDetails.getGraphicCard());

        return productMapper.toDTO(productRepository.save(product));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
