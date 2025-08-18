📦 Inventory Management System
1. Giới thiệu

Hệ thống Inventory Management hỗ trợ quản lý sản phẩm, tồn kho, nhập kho (GR) và xuất kho (GI).
Mục tiêu: đảm bảo quản lý hàng hóa minh bạch, tránh thất thoát, và có báo cáo chi tiết cho từng giao dịch.

2. Mục tiêu

Quản lý vòng đời sản phẩm trong kho.

Quản lý tồn kho theo thời gian thực.

Theo dõi lịch sử nhập kho (GR) và xuất kho (GI).

Tích hợp với hệ thống bên ngoài qua request GI.

Báo cáo tồn kho hiện tại và lịch sử giao dịch.

3. Phạm vi

✅ Có trong phạm vi

CRUD sản phẩm

CRUD tồn kho

Nhập kho (tăng tồn)

Xuất kho (giảm tồn, có kiểm tra tồn kho)

Báo cáo cơ bản

❌ Ngoài phạm vi

Quản lý nhiều kho (multi-warehouse)

Quy trình phê duyệt nhiều bước

Tích hợp ERP phức tạp

4. Kiến trúc tổng quan

Spring Boot (Java) – Backend

JPA/Hibernate – ORM

MySQL / PostgreSQL – Database

JUnit + Mockito – Unit test

API RESTful cho client/web
