package com.example.FirstProject.entity;

import com.example.FirstProject.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "gr")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoodsReceipt extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gr_id")
    private Long grId;  // 🔑 Khóa chính

    @Column(name = "product_id", nullable = false)
    private Long productId;  // 🔗 FK tới product

    @Column(name = "request_qty", nullable = false)
    private Integer requestQty;  // 📥 Số lượng yêu cầu nhập

    @Column(name = "received_qty", nullable = false)
    private Integer receivedQty; // 📥 Số lượng thực nhập

    @Column(name = "order_user", length = 50)
    private String orderUser;   // 👤 Người yêu cầu

    @Column(name = "order_dept", length = 50)
    private String orderDept;   // 🏢 Phòng ban yêu cầu

    @Column(name = "order_date")
    private LocalDate orderDate; // 📅 Ngày yêu cầu

}
