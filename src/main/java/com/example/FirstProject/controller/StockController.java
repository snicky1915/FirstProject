package com.example.FirstProject.controller;

import com.example.FirstProject.common.PaginationService;
import com.example.FirstProject.entity.Stock;
import com.example.FirstProject.service.StockService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StockController {
    private final StockService stockService;
    private final PaginationService paginationService;

    public StockController(StockService stockService, PaginationService paginationService) {
        this.stockService = stockService;
        this.paginationService = paginationService;
    }

    @PostMapping("/createStock")
    public String create(@RequestBody Stock req) { return stockService.createEntity(req); }

    @PostMapping("/updateStock")
    public String update(@RequestBody Stock req) { return stockService.updateEntity(req); }

    @PostMapping("/deleteStock")
    public String delete(@RequestBody Stock req) { return stockService.deleteById(req.getStockId()); }

    @GetMapping("/stocks")
    public Page<Stock> getStocks(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(defaultValue = "stockId") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        Pageable pageable = paginationService.resolvePageable(page, size, sortBy, direction);
        return stockService.getStocks(pageable);
    }

    @GetMapping("/stocks/by-product/{productId}")
    public Stock getStockByProductId(@PathVariable Long productId) {
        return stockService.getStockByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tồn kho cho productId: " + productId));
    }
}
