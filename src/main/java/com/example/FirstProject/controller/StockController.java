package com.example.FirstProject.controller;

import com.example.FirstProject.entity.Stock;
import com.example.FirstProject.service.StockService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StockController {
    private final StockService stockService;
    public StockController(StockService stockService) { this.stockService = stockService; }

    @PostMapping("/createStock")
    public String create(@RequestBody Stock req) { return stockService.createEntity(req); }

    @PostMapping("/updateStock")
    public String update(@RequestBody Stock req) { return stockService.updateEntity(req); }

    @PostMapping("/deleteStock")
    public String delete(@RequestBody Stock req) { return stockService.deleteById(req.getStockId()); }
}

