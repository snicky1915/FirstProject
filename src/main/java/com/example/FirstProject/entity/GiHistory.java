package com.example.FirstProject.entity;

import com.example.FirstProject.common.BaseHistoryEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
@Table(name = "gi_history")
public class GiHistory extends BaseHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long historyId;

    @Column(name = "gi_id")
    private Long giId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "request_qty")
    private Integer requestQty;

    @Column(name = "issued_qty")
    private Integer issuedQty;

    @Column(name = "order_id", length = 50)
    private String orderId;

    @Column(name = "order_user_id", length = 50)
    private String orderUserId;

    @Column(name = "order_user_name", length = 100)
    private String orderUserName;

    @Column(name = "status", length = 20)
    private String status;
}
