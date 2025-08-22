package com.example.FirstProject.controller;

import com.example.FirstProject.entity.Product;
import com.example.FirstProject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    public String deleteProduct(@PathVariable Long id){
        boolean deleted = productService.deleteProduct(id);
        if(deleted){
            return "Product with id " + id + "deleted";
        } else {
            return "Product not found";
        }
    }
}
