package com.example.FirstProject.repository;

import com.example.FirstProject.common.BaseRepository;
import com.example.FirstProject.entity.Stock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository  extends BaseRepository<Stock,Long> {

    Stock findByStockId(Long id);

    Optional<Stock> findByProductId(Long productId);
}

