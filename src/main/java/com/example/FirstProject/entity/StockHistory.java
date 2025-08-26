package com.example.FirstProject.entity;

import com.example.FirstProject.common.BaseHistoryEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
@Table(name = "stock_history")
public class StockHistory extends BaseHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long historyId;

    @Column(name = "stock_id")
    private Long stockId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "qty")
    private Integer qty;
}
