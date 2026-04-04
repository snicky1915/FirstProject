package com.example.FirstProject.service;

import com.example.FirstProject.common.CrudService;
import com.example.FirstProject.entity.Product;
import com.example.FirstProject.entity.ProductHistory;
import com.example.FirstProject.repository.ProductHistoryRepository;
import com.example.FirstProject.repository.ProductRepository;
import com.example.FirstProject.scheduler.ExecutableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductService extends CrudService<Product, ProductHistory> implements ExecutableService {

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

    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public String execute() {
        long totalProducts = productRepository.count();
        String message = "ProductService executed. Total products: " + totalProducts;
        log.info(message);
        return message;
    }
}
