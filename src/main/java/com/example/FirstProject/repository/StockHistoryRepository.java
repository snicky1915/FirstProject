package com.example.FirstProject.repository;

import com.example.FirstProject.common.BaseHistoryRepository;
import com.example.FirstProject.entity.StockHistory;
import org.springframework.stereotype.Repository;

@Repository
public interface StockHistoryRepository extends BaseHistoryRepository<StockHistory,Long> {
}
