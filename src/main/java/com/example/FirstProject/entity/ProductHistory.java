package com.example.FirstProject.entity;

import com.example.FirstProject.common.BaseHistoryEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product_history")
public class ProductHistory extends BaseHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    @Column(name = "history_id")
    private Long historyId;

    // Không đặt @ManyToOne vì bảng history thường không FK cứng tới bảng chính
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_code", length = 50)
    private String productCode;

    @Column(name = "product_name", length = 255)
    private String productName;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "status", length = 20)
    private String status;


}
