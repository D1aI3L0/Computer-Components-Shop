package com.example.springBackend_Hibernate.service;

import com.example.springBackend_Hibernate.MEntityNotFoundException;
import com.example.springBackend_Hibernate.dto.ProductDTO;
import com.example.springBackend_Hibernate.entity.Order;
import com.example.springBackend_Hibernate.entity.Product;
import com.example.springBackend_Hibernate.mapper.*;
import com.example.springBackend_Hibernate.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProcessorMapper processorMapper;
    @Autowired
    private GraphicCardMapper graphicCardMapper;
    @Autowired
    private MotherboardMapper motherboardMapper;

    @Autowired
    private ProductMapper productMapper;

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
        product.setProcessor(processorMapper.toEntity(productDetails.getProcessor()));
        product.setMotherboard(motherboardMapper.toEntity(productDetails.getMotherboard()));
        product.setGraphicCard(graphicCardMapper.toEntity(productDetails.getGraphicCard()));

        return productMapper.toDTO(productRepository.save(product));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
