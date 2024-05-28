package com.springmvc.demo.repositories;

import com.springmvc.demo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByCategoryID(String categoryID);
}
