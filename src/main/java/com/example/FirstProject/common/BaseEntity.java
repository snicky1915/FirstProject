package com.example.FirstProject.common;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    // Thông tin tạo
    private LocalDateTime createdAt = LocalDateTime.now();
    private String createdId;

    // Thông tin cập nhật cuối
    private LocalDateTime finalUpdateAt;
    private String finalUpdateId;

    // Soft delete flag
    private String delYn = "N";

    // Có đang sử dụng không
    private String useYn = "Y";
}

