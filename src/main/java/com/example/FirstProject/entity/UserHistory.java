package com.example.FirstProject.entity;

import com.example.FirstProject.common.BaseHistoryEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_his")
public class UserHistory extends BaseHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "his_id") // Khóa chính riêng cho bảng history
    private Long hisId;

    // Tham chiếu user_id từ bảng chính (không bắt buộc FK, nhưng thường nên có)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "full_name", length = 100)
    private String fullName;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "user_role", length = 50)
    private String role; // ADMIN / USER / MANAGER


}
