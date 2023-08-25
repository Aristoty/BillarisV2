package com.company.billaris2.mappers;

import com.company.billaris2.DTO.ProductDTO;
import com.company.billaris2.entities.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {


    public ProductDTO toDTO(Product product){
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setUnitPrice(product.getUnitPrice());
        dto.setCategory(product.getCategory());
        dto.setStatus(product.getStatus());
        return dto;
    }

    public Product toEntity(ProductDTO dto){
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setCategory(dto.getCategory());
        product.setStatus(dto.getStatus());
        product.setUnitPrice(dto.getUnitPrice());
        return product;
    }

}
