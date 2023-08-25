package com.company.billaris2.services.impl;

import com.company.billaris2.DTO.CategoryDTO;
import com.company.billaris2.DTO.ProductDTO;
import com.company.billaris2.entities.Category;
import com.company.billaris2.entities.Product;
import com.company.billaris2.mappers.ProductMapper;
import com.company.billaris2.repositories.CategoryRepository;
import com.company.billaris2.repositories.ProductRepository;
import com.company.billaris2.services.interfaces.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper ;

    private final CategoryRepository categoryRepository;

//    @Override
//    public List<Product> getAllProducts(){
//        return productRepository.findAll();
//    }

    @Override
    public List<ProductDTO> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return Collections.singletonList(productMapper.toDTO((Product) products));
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product;
        product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No Product found with this ID!!"));
        return productMapper.toDTO(product);
    }

  @Autowired
    public Category createCategory(CategoryDTO dto) {
        Category category = Category.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
        return categoryRepository.save(category);
    }

    @Override
    public Product createProduct(ProductDTO dto) {
        Category category = categoryRepository.findByName(dto.getCategory().getName());
        if (category == null){
            category = Category.builder()
                    .name(dto.getCategory().getName())
                    .description(dto.getCategory().getDescription())
                    .build();
            categoryRepository.save(category);
        }

        Product product = Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .unitPrice(dto.getUnitPrice())
                .status(dto.getStatus())
                .category(category)
                .build();

        if (productRepository.existsByName(product.getName())){
            throw new EntityNotFoundException("Product with name " + product.getName() + " already exists.");
        }

        return productRepository.save(product);
    }


    @Override
    public Product updateProduct(Long id, ProductDTO dto) {

        Product product = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);


        if(productRepository.existsByNameAndIdNot(dto.getName(), id)){
            throw new EntityNotFoundException("Product with name"+ dto.getName() + "already exists.");
        }

        Category category = categoryRepository.findByName(dto.getCategory().getName());
        if (category == null){
            category = Category.builder()
                    .name(dto.getCategory().getName())
                    .description(dto.getCategory().getDescription())
                    .build();
            categoryRepository.save(category);
        }

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setUnitPrice(dto.getUnitPrice());
        product.setStatus(dto.getStatus());
        product.setCategory(category);

        return productRepository.save(product);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

}
