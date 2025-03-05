package com.example.springBackend_Hibernate.mapper;

import com.example.springBackend_Hibernate.dto.ProductDTO;
import com.example.springBackend_Hibernate.entity.Product;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class ProductMapper {
    public ProductDTO toDTO(Product product)
    {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setManufacturer(product.getManufacturer());
        productDTO.setPrice(product.getPrice());
        productDTO.setType(product.getType());
        productDTO.setProcessor(product.getProcessor());
        productDTO.setMotherboard(product.getMotherboard());
        productDTO.setGraphicCard(product.getGraphicCard());

        return productDTO;
    }

    public Product toEntity(ProductDTO productDTO)
    {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setManufacturer(productDTO.getManufacturer());
        product.setPrice(productDTO.getPrice());
        product.setType(productDTO.getType());
        product.setProcessor(productDTO.getProcessor());
        product.setMotherboard(productDTO.getMotherboard());
        product.setGraphicCard(productDTO.getGraphicCard());

        return product;
    }

    public Product toEntityWithId(ProductDTO productDTO)
    {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setManufacturer(productDTO.getManufacturer());
        product.setPrice(productDTO.getPrice());
        product.setType(productDTO.getType());
        product.setProcessor(productDTO.getProcessor());
        product.setMotherboard(productDTO.getMotherboard());
        product.setGraphicCard(productDTO.getGraphicCard());

        return product;
    }

    public List<ProductDTO> fromProductToProductDTOList(List<Product> products)
    {
        List<ProductDTO> productDTOS = new LinkedList<>();
        for(Product product : products)
        {
            productDTOS.add(toDTO(product));
        }
        return productDTOS;
    }

    public List<Product> fromProductDTOToProductListWithID(List<ProductDTO> productDTOS)
    {
        List<Product> products = new LinkedList<>();
        for(ProductDTO productDTO : productDTOS)
        {
            products.add(toEntityWithId(productDTO));
        }
        return products;
    }
}