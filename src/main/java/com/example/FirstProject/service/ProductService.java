package com.example.FirstProject.service;

import com.example.FirstProject.common.CrudService;
import com.example.FirstProject.entity.Product;
import com.example.FirstProject.entity.ProductHistory;
import com.example.FirstProject.repository.ProductHistoryRepository;
import com.example.FirstProject.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends CrudService<Product, ProductHistory> {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository,
                          ProductHistoryRepository productHistoryRepository) {
        super(
                productRepository,               // mainRepository
                productHistoryRepository,        // historyRepository
                ProductHistory.class,            // historyClass
                Product::getProductId            // ✅ idGetter trả về Long
        );
        this.productRepository = productRepository;
    }
}
