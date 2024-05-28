package com.springmvc.demo.controllers;

import com.springmvc.demo.models.Category;
import com.springmvc.demo.models.Product;
import com.springmvc.demo.repositories.CategoryRepository;
import com.springmvc.demo.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(path = "products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    //http:localhost:8080/products/getProductsByCategoryID/C0103
    @RequestMapping(value = "/getProductsByCategoryID/{categoryID}", method = RequestMethod.GET)
    public String getProductsByCategoryID(Model model, @PathVariable String categoryID){
        List<Product> productList = productRepository.findByCategoryID(categoryID);

        model.addAttribute("productList", productList);
        return "product.html";
    }

    @RequestMapping(value = "/upDateProduct/{productID}", method = RequestMethod.GET)
    public String changeCategory(Model model, @PathVariable String productID){
        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("product", productRepository.findById(productID).get());

        return "upDateProduct.html";
    }

    @RequestMapping(value = "/updateProduct/{productID}", method = RequestMethod.POST)
    public String updateProduct(Model model,
                                @Valid @ModelAttribute Product product,
                                BindingResult bindingResult,
                                @PathVariable String productID
    ){
        List<Category> categoryList = categoryRepository.findAll();
        if (bindingResult.hasErrors()) {
            model.addAttribute("categoryList", categoryList);
            return "upDateProduct.html";
        }
        try {
            if (productRepository.findById(product.getProductID()).isPresent()){
                Product foundProduct = productRepository.findById(productID).get();
                if (!product.getProductName().trim().isEmpty()){
                    foundProduct.setProductName(product.getProductName());
                }
                if (!product.getCategoryID().trim().isEmpty()){
                    foundProduct.setCategoryID(product.getCategoryID());
                }
                if (!product.getDescription().trim().isEmpty()){
                    foundProduct.setDescription(product.getDescription());
                }
                if (product.getPrice() > 0){
                    foundProduct.setPrice(product.getPrice());
                }
                productRepository.save(foundProduct);
            }
        } catch (Exception e) {
            return "upDateProduct.html";
        }

        return "redirect:/products/getProductsByCategoryID/" + product.getCategoryID();
    }

    @RequestMapping(value = "/insertProduct", method = RequestMethod.GET)
    public String insertProduct(Model model){
        model.addAttribute("product", new Product());
        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("categoryList", categoryList);
        return "insertProduct.html";
    }

    @RequestMapping(value = "/insertProduct", method = RequestMethod.POST)
    //method "overloading"
    public String insertProduct(Model model, @Valid @ModelAttribute Product product, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            List<Category> categoryList = categoryRepository.findAll();
            model.addAttribute("categoryList", categoryList);
            return "insertProduct.html";
        }
        try {
            product.setProductID(UUID.randomUUID().toString());
            productRepository.save(product);
            return "redirect:/products/getProductsByCategoryID/" + product.getCategoryID();
        } catch (Exception e){
            model.addAttribute("error", e.toString());
            List<Category> categoryList = categoryRepository.findAll();
            model.addAttribute("categoryList", categoryList);
            return "insertProduct.html";
        }
    }

    @RequestMapping(value = "/deleteProduct/{productID}", method = RequestMethod.POST)
    public String deleteProduct(Model model, @PathVariable String productID){
        productRepository.deleteById(productID);
        return "redirect:/categories";
    }
}
