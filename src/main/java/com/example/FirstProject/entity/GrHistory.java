package com.example.FirstProject.entity;

import com.example.FirstProject.common.BaseHistoryEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
@Table(name = "gr_history")
public class GrHistory extends BaseHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long historyId;

    @Column(name = "gr_id")
    private Long grId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "request_qty")
    private Integer requestQty;

    @Column(name = "received_qty")
    private Integer receivedQty;

    @Column(name = "order_user", length = 50)
    private String orderUser;

    @Column(name = "order_dept", length = 50)
    private String orderDept;

    @Column(name = "order_date")
    private LocalDate orderDate; // DATE -> LocalDate
}
