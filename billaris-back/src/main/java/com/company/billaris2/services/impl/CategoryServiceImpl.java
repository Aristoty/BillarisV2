package com.company.billaris2.services.impl;

import com.company.billaris2.DTO.CategoryDTO;
import com.company.billaris2.entities.Category;
import com.company.billaris2.repositories.CategoryRepository;
import com.company.billaris2.services.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;



    @Override
    public List<CategoryDTO> getAllCategories() {
        return null;
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        return null;
    }

    @Override
    public Category createCategory(CategoryDTO dto) {
        Category category = Category.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, CategoryDTO dto) {
        return null;
    }

    @Override
    public void deleteCategory(Long id) {

    }
}
