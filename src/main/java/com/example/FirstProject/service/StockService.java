package com.example.FirstProject.service;

import com.example.FirstProject.common.CrudService;
import com.example.FirstProject.entity.Stock;
import com.example.FirstProject.entity.StockHistory;
import com.example.FirstProject.repository.StockHistoryRepository;
import com.example.FirstProject.repository.StockRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StockService extends CrudService<Stock, StockHistory> {

    private final StockRepository stockRepository;
    private final StockHistoryRepository stockHistoryRepository;

    public StockService(StockRepository stockRepository, StockHistoryRepository stockHistoryRepository){
        super(stockRepository,stockHistoryRepository,StockHistory.class,Stock::getStockId);
        this.stockRepository= stockRepository;
        this.stockHistoryRepository = stockHistoryRepository;
    }

    public Stock create(Stock requestStock){
        return stockRepository.save(requestStock);
    }
    public void deleteStockById(Stock requestStock){

            Stock delteStock = stockRepository.findByStockId(requestStock.getProductId());
            stockRepository.delete(delteStock);
    }

    public Page<Stock> getStocks(Pageable pageable) {
        return stockRepository.findAll(pageable);
    }

    public Optional<Stock> getStockByProductId(Long productId) {
        return stockRepository.findByProductId(productId);
    }

    @Transactional
    public Stock addStock(Long productId, Integer qty, String action) {
        validateQty(qty);
        Optional<Stock> existing = stockRepository.findByProductId(productId);
        Stock stock = existing.orElseGet(() -> Stock.builder().productId(productId).qty(0).build());
        stock.setQty(stock.getQty() + qty);
        Stock saved = stockRepository.save(stock);
        saveStockHistory(saved, action);
        return saved;
    }

    @Transactional
    public Stock deductStock(Long productId, Integer qty, String action) {
        validateQty(qty);
        Stock stock = stockRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tồn kho cho productId: " + productId));

        if (stock.getQty() == null || stock.getQty() < qty) {
            throw new RuntimeException("Tồn kho không đủ để xuất cho productId: " + productId);
        }

        stock.setQty(stock.getQty() - qty);
        Stock saved = stockRepository.save(stock);
        saveStockHistory(saved, action);
        return saved;
    }

    private void validateQty(Integer qty) {
        if (qty == null || qty <= 0) {
            throw new RuntimeException("Số lượng phải lớn hơn 0");
        }
    }

    private void saveStockHistory(Stock stock, String action) {
        StockHistory history = StockHistory.builder()
                .stockId(stock.getStockId())
                .productId(stock.getProductId())
                .qty(stock.getQty())
                .build();
        history.setAction(action);
        stockHistoryRepository.save(history);
    }

}
