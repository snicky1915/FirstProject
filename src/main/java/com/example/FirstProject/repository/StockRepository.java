package com.example.FirstProject.repository;

import com.example.FirstProject.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository  extends JpaRepository<Stock,Long> {

    Stock findByStockId(Long id);
}


