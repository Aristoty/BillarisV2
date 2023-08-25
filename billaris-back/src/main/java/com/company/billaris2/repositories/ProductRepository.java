package com.company.billaris2.repositories;

import com.company.billaris2.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);


    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);
}
