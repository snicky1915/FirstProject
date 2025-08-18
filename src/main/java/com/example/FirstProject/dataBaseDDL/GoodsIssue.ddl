CREATE TABLE gi (
    gi_id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id      BIGINT NOT NULL,
    request_qty     INT NOT NULL,                 -- Số lượng yêu cầu
    issued_qty      INT NOT NULL,                 -- Số lượng thực xuất
    order_id        VARCHAR(50),                  -- Mã đơn hàng từ hệ thống khác
    order_user_id   VARCHAR(50),
    order_user_name VARCHAR(100),
    status          VARCHAR(20),                  -- REQUESTED, APPROVED, ISSUED...

    -- Common fields
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_id      VARCHAR(50),
    final_update_at TIMESTAMP,
    final_update_id VARCHAR(50),
    del_yn          CHAR(1) DEFAULT 'N',
    use_yn          CHAR(1) DEFAULT 'Y',

    CONSTRAINT fk_gi_product FOREIGN KEY (product_id) REFERENCES product(product_id)
);