package com.springmvc.demo.repositories;

import com.springmvc.demo.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {}
