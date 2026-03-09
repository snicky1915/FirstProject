package com.example.FirstProject.controller;

import com.example.FirstProject.common.PaginationService;
import com.example.FirstProject.entity.Product;
import com.example.FirstProject.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;
    private final PaginationService paginationService;

    public ProductController(ProductService productService, PaginationService paginationService) {
        this.productService = productService;
        this.paginationService = paginationService;
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

    @GetMapping("/products")
    public Page<Product> getProducts(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(defaultValue = "productId") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        Pageable pageable = paginationService.resolvePageable(page, size, sortBy, direction);
        return productService.getProducts(pageable);
    }
}
