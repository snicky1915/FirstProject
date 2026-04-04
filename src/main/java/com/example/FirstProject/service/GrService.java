package com.example.FirstProject.service;

import com.example.FirstProject.common.CrudService;
import com.example.FirstProject.state.CrudState;
import com.example.FirstProject.entity.Gr;
import com.example.FirstProject.entity.GrHistory;
import com.example.FirstProject.repository.ProductRepository;
import com.example.FirstProject.repository.GrHistoryRepository;
import com.example.FirstProject.repository.GrRepository;
import com.example.FirstProject.scheduler.ExecutableService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Service
@Slf4j
public class GrService extends CrudService<Gr, GrHistory> implements ExecutableService {

    private final GrRepository grRepository;
    private final ProductRepository productRepository;
    private final StockService stockService;

    public GrService(GrRepository grRepository,
                     GrHistoryRepository grHistoryRepository,
                     ProductRepository productRepository,
                     StockService stockService){
        super(grRepository,grHistoryRepository, GrHistory.class,Gr::getGrId);
        this.grRepository=grRepository;
        this.productRepository = productRepository;
        this.stockService = stockService;
    }

    public Page<Gr> getGrs(Pageable pageable) {
        return grRepository.findAll(pageable);
    }

    @Transactional
    public String receiveGoods(Gr request) {
        try {
            if (request.getProductId() == null) {
                return "FAIL: productId không được null";
            }
            if (!productRepository.existsById(request.getProductId())) {
                return "FAIL: Không tìm thấy Product ID " + request.getProductId();
            }
            if (request.getReceivedQty() == null || request.getReceivedQty() <= 0) {
                return "FAIL: receivedQty phải lớn hơn 0";
            }
            if (request.getRequestQty() == null) {
                request.setRequestQty(request.getReceivedQty());
            }
            if (request.getOrderDate() == null) {
                request.setOrderDate(LocalDate.now());
            }

            Gr saved = grRepository.save(request);
            saveHistory(saved, "RECEIVE");
            stockService.addStock(saved.getProductId(), saved.getReceivedQty(), "RECEIVE");
            return CrudState.SUCCESS.name();
        } catch (Exception e) {
            return "FAIL: " + e.getMessage();
        }
    }

    @Override
    public String execute() {
        long totalGr = grRepository.count();
        String message = "GrService executed. Total GR records: " + totalGr;
        log.info(message);
        return message;
    }
}
