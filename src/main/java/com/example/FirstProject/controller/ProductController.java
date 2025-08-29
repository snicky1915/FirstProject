package com.example.FirstProject.controller;

import com.example.FirstProject.entity.Product;
import com.example.FirstProject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public String createProduct(@RequestBody Product product){
        return productService.createEntity(product);
    }

    @PostMapping("/update")
    public String updateProduct(@RequestBody Product request){
        return productService.updateEntity(request);
    }

    @PostMapping("/delete")
    public String deleteProduct(@RequestBody Product request){
        return productService.deleteProduct(request);
    }
}
