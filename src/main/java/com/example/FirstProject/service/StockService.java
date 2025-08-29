package com.example.FirstProject.service;

import com.example.FirstProject.common.CrudService;
import com.example.FirstProject.entity.Stock;
import com.example.FirstProject.entity.StockHistory;
import com.example.FirstProject.repository.StockHistoryRepository;
import com.example.FirstProject.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService extends CrudService<Stock, StockHistory> {

    private final StockRepository stockRepository;
    public StockService(StockRepository stockRepository, StockHistoryRepository stockHistoryRepository){
        super(stockRepository,stockHistoryRepository,StockHistory.class,Stock::getStockId);
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
