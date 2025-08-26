package com.example.FirstProject.repository;

import com.example.FirstProject.common.BaseHistoryRepository;
import com.example.FirstProject.entity.ProductHistory;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductHistoryRepository extends BaseHistoryRepository<ProductHistory,Long> {
}
