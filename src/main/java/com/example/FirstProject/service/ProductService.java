package com.example.FirstProject.service;

import com.example.FirstProject.common.CrudService;
import com.example.FirstProject.entity.Product;
import com.example.FirstProject.entity.ProductHistory;
import com.example.FirstProject.repository.ProductHistoryRepository;
import com.example.FirstProject.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public String updateProduct(Product request) {
        Long id = request.getProductId();
        if (id == null) {
            throw new IllegalArgumentException("productId bị null");
        }
        return super.updateEntity(request);
    }

    @Transactional
    public String deleteProduct(Product request){
        Long id = request.getProductId();
        if(id == null){
            throw  new IllegalArgumentException("productId bị null");
        }
        return super.deleteById(id);
    }
}
