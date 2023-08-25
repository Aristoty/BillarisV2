package com.company.billaris2.services.interfaces;


import com.company.billaris2.DTO.CategoryDTO;
import com.company.billaris2.entities.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryService {

    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryById(Long id);
    Category createCategory(CategoryDTO dto);
    Category updateCategory(Long id, CategoryDTO dto);
    void deleteCategory(Long id);


}
