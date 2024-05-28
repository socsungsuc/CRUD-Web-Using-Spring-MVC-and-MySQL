package com.springmvc.demo.controllers;

import com.springmvc.demo.models.Category;
import com.springmvc.demo.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Console;
import java.util.List;

@Controller
@RequestMapping(path = "categories")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
    //return name of "jsp file"
    //http:localhost:8080/categories
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        List<Category> categories = categoryRepository.findAll();

        model.addAttribute("categories", categories);

        return "category.html";
    }
}
