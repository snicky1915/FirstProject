package com.example.FirstProject.entity;

import com.example.FirstProject.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "gi")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Gi extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gi_id")
    private Long giId;  // 🔑 Khóa chính

    @Column(name = "product_id", nullable = false)
    private Long productId;  // 🔗 FK tới product

    @Column(name = "request_qty", nullable = false)
    private Integer requestQty; // 📤 Số lượng yêu cầu

    @Column(name = "issued_qty", nullable = false)
    private Integer issuedQty;  // 📤 Số lượng thực xuất

    @Column(name = "order_id", length = 50)
    private String orderId;     // 📝 Mã đơn hàng hệ thống khác

    @Column(name = "order_user_id", length = 50)
    private String orderUserId; // 👤 ID người yêu cầu

    @Column(name = "order_user_name", length = 100)
    private String orderUserName; // 👤 Tên người yêu cầu

    @Column(name = "status", length = 20)
    private String status; // 🚦 Trạng thái (REQUESTED, APPROVED, ISSUED...)

}
