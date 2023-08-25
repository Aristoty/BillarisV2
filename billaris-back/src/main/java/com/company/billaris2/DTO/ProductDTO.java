package com.company.billaris2.DTO;

import com.company.billaris2.entities.Category;
import com.company.billaris2.models.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal unitPrice;
    private Category category;
    private ProductStatus status;

}
