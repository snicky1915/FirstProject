package com.example.FirstProject.service;

import com.example.FirstProject.entity.Stock;
import com.example.FirstProject.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    private final StockRepository stockRepository;
    public StockService(StockRepository stockRepository){
        this.stockRepository= stockRepository;
    }

    public Stock create(Stock requestStock){
        return stockRepository.save(requestStock);
    }
    public void deleteStockById(Stock requestStock){

            Stock delteStock = stockRepository.findByStockId(requestStock.getProductId());
            stockRepository.delete(delteStock);
    }

}
