package com.example.FirstProject.entity;

import com.example.FirstProject.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stock")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long stockId;  // 🔑 Khóa chính

    @Column(name = "product_id", nullable = false)
    private Long productId;  // 🔗 FK tới product

    @Column(name = "qty")
    private Integer qty;  // 🔢 Số lượng tồn kho
}
