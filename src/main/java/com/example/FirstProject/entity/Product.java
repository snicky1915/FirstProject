package com.example.FirstProject.entity;

import com.example.FirstProject.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    @Column(name = "product_id")
    private Long productId;  // 🔑 Khóa chính

    @Column(name = "product_code", length = 50, nullable = false, unique = true)
    private String productCode;  // 📦 Mã sản phẩm

    @Column(name = "product_name", length = 255, nullable = false)
    private String productName;  // 📦 Tên sản phẩm

    @Column(name = "qty")
    private Integer qty;  // 🔢 Số lượng

    @Column(name = "status", length = 20)
    private String status; // ✅ Trạng thái (AVAILABLE, HOLD, EXPIRED...)
}
