package com.example.FirstProject.service;

import com.example.FirstProject.common.CrudService;
import com.example.FirstProject.entity.Gi;
import com.example.FirstProject.entity.GiHistory;
import com.example.FirstProject.repository.ProductRepository;
import com.example.FirstProject.repository.GiHistoryRepository;
import com.example.FirstProject.repository.GiRepository;
import com.example.FirstProject.state.CrudState;
import com.example.FirstProject.state.GiStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GiService extends CrudService<Gi, GiHistory> {

    private final GiRepository giRepository;
    private final ProductRepository productRepository;
    private final StockService stockService;

    public GiService(GiRepository giRepository,
                     GiHistoryRepository giHistoryRepository,
                     ProductRepository productRepository,
                     StockService stockService){
        super(giRepository,giHistoryRepository,GiHistory.class,Gi::getGiId);
        this.giRepository = giRepository;
        this.productRepository = productRepository;
        this.stockService = stockService;
    }

    public Page<Gi> getGis(Pageable pageable) {
        return giRepository.findAll(pageable);
    }

    @Transactional
    public String issueGoods(Gi request) {
        try {
            if (request.getProductId() == null) {
                return "FAIL: productId không được null";
            }
            if (!productRepository.existsById(request.getProductId())) {
                return "FAIL: Không tìm thấy Product ID " + request.getProductId();
            }
            if (request.getIssuedQty() == null || request.getIssuedQty() <= 0) {
                return "FAIL: issuedQty phải lớn hơn 0";
            }
            if (request.getRequestQty() == null) {
                request.setRequestQty(request.getIssuedQty());
            }
            if (request.getStatus() == null || request.getStatus().isBlank()) {
                request.setStatus(GiStatus.ISSUED.name());
            }

            Gi saved = giRepository.save(request);
            saveHistory(saved, "ISSUE");
            stockService.deductStock(saved.getProductId(), saved.getIssuedQty(), "ISSUE");
            return CrudState.SUCCESS.name();
        } catch (Exception e) {
            return "FAIL: " + e.getMessage();
        }
    }
}
