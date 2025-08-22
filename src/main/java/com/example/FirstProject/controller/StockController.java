package com.example.FirstProject.controller;

import com.example.FirstProject.entity.Stock;
import com.example.FirstProject.service.StockService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

    public final StockService stockService;
    public StockController(StockService stockService){
        this.stockService = stockService;
    }

    @PostMapping("/auth/create")
    public Stock create(@RequestBody Stock requestStock){
        return stockService.create(requestStock);
    }

    @PostMapping("/auth/delete")
    public Stock delete(@RequestBody Stock requestStock){
        return stockService.create(requestStock);
    }
}
