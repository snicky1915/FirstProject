package com.example.FirstProject.controller;

import com.example.FirstProject.entity.Product;
import com.example.FirstProject.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/createProduct")
    public String create(@RequestBody Product product) {
        return productService.createEntity(product);
    }

    @PostMapping("/updateProduct")
    public String update( @RequestBody Product request) {
        request.setProductId(request.getProductId());
        return productService.updateEntity(request);
    }

    @PostMapping("/deleteProduct")
    public String deleteProduct(@RequestBody Product request){
        return productService.deleteById(request.getProductId());
    }
}

