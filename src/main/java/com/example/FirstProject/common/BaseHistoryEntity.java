package com.example.FirstProject.common;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseHistoryEntity extends BaseEntity {
    // Loại hành động: INSERT / UPDATE / DELETE
    private String action;

    // Người thay đổi
    private String changedBy;

    // Thời điểm thay đổi
    private LocalDateTime changedAt = LocalDateTime.now();
}

