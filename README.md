# 📦 Inventory Management System

## 🚀 1. Giới thiệu
Hệ thống **Inventory Management** hỗ trợ quản lý sản phẩm, tồn kho, nhập kho (GR) và xuất kho (GI).  
Mục tiêu là đảm bảo việc quản lý hàng hóa minh bạch, tránh thất thoát và có báo cáo chi tiết cho từng giao dịch.  

---

## 🎯 2. Tính năng chính
- Quản lý vòng đời sản phẩm trong kho  
- Quản lý tồn kho theo thời gian thực  
- Theo dõi lịch sử nhập kho (GR) và xuất kho (GI)  
- Cho phép tích hợp với hệ thống bên ngoài qua **request GI**  
- Báo cáo tồn kho hiện tại và lịch sử giao dịch  

---

## 📌 3. Phạm vi dự án
**Có trong phạm vi**  
- CRUD sản phẩm, tồn kho  
- Nhập kho (tăng tồn)  
- Xuất kho (giảm tồn, có kiểm tra tồn)  
- Báo cáo cơ bản  

**Ngoài phạm vi**  
- Quản lý nhiều kho (multi-warehouse)  
- Quy trình phê duyệt nhiều bước  
- Tích hợp ERP phức tạp  

---

## 🛠 4. Công nghệ sử dụng
- Spring Boot (Java) – Backend REST API  
- JPA/Hibernate – ORM mapping  
- MySQL / PostgreSQL – Database  
- JUnit + Mockito – Unit test  
