package com.example.FirstProject.state;

import lombok.Getter;

@Getter
public enum ProductStatus {
    ACTIVE,    // Có sẵn để bán/nhập/xuất
    HOLD,      // Tạm giữ / Chờ xử lý
    SHIPPED,   // Đã xuất
    INACTIVE   // Ngừng kinh doanh
}
