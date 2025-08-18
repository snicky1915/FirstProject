package com.example.FirstProject.state;

import lombok.Getter;

@Getter
public enum GiStatus {
    RECEIVED,          // Khi phiếu được tạo, đã tiếp nhận yêu cầu
    WAITING,   // Đã duyệt, đang chờ xuất kho
    ISSUED;          // Đã xuất kho xong
}
