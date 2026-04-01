# Inventory Management System

## 1. Thông tin dự án

- Tên dự án: `FirstProject`
- Loại dự án: Backend API quản lý kho
- Mục tiêu: Quản lý sản phẩm, tồn kho, phiếu nhập kho (GR), phiếu xuất kho (GI), xác thực người dùng và tích hợp dịch vụ ngoài
- Trạng thái: Đang phát triển

## 2. Chủ dự án và thành viên

- Chủ dự án: Phan Van Cuong
- Email: `cuongsnicky@gmail.com`
- Thành viên: Le Quang Hung
- Ghi chú: Thông tin Facebook/chức danh hiện chưa được cập nhật trong mã nguồn

## 3. Mô tả ngắn

Hệ thống hỗ trợ quản lý hàng hóa trong kho theo quy trình cơ bản gồm tạo sản phẩm, nhập kho, xuất kho, theo dõi tồn kho và lịch sử biến động. Dự án được xây dựng bằng Spring Boot, dùng MySQL làm cơ sở dữ liệu chính và có thêm OpenFeign, WebSocket, Thymeleaf/XHTML để phục vụ các nhu cầu tích hợp và hiển thị nội dung mẫu.

## 4. Công nghệ sử dụng

- Java 17
- Spring Boot 3.1.4
- Spring Web
- Spring Data JPA
- Spring Data JDBC
- Spring Security
- Spring Validation
- Spring WebSocket
- Spring Cloud OpenFeign
- Thymeleaf
- MySQL Connector/J
- Lombok
- MapStruct
- Springdoc OpenAPI
- Maven

## 5. Chức năng chính

- Quản lý sản phẩm
- Quản lý tồn kho
- Nhập kho (Goods Receipt - GR)
- Xuất kho (Goods Issue - GI)
- Xác thực người dùng: đăng ký, đăng nhập
- Gọi dịch vụ ngoài qua OpenFeign
- WebSocket cho trao đổi dữ liệu thời gian thực
- Render nội dung XHTML mẫu tại endpoint `/html`

## 6. Cấu trúc chính của mã nguồn

- `src/main/java/com/example/FirstProject/controller`: REST controller và WebSocket config
- `src/main/java/com/example/FirstProject/service`: Xử lý nghiệp vụ
- `src/main/java/com/example/FirstProject/repository`: Tầng truy cập dữ liệu
- `src/main/java/com/example/FirstProject/entity`: Entity JPA
- `src/main/java/com/example/FirstProject/dto`: DTO request/response
- `src/main/resources/templates`: Template Thymeleaf/XHTML
- `src/main/resources/application.properties`: Cấu hình ứng dụng

## 7. API tiêu biểu

- `POST /auth/register`: đăng ký tài khoản
- `POST /auth/login`: đăng nhập
- `POST /api/createProduct`: tạo sản phẩm
- `POST /api/updateProduct`: cập nhật sản phẩm
- `POST /api/deleteProduct`: xóa sản phẩm
- `GET /api/products`: danh sách sản phẩm
- `POST /api/createStock`: tạo tồn kho
- `GET /api/stocks`: danh sách tồn kho
- `GET /api/stocks/by-product/{productId}`: tồn kho theo sản phẩm
- `POST /api/createGr`: tạo phiếu nhập kho
- `POST /api/gr/receive`: nhận hàng vào kho
- `GET /api/grs`: danh sách phiếu nhập
- `POST /api/createGi`: tạo phiếu xuất kho
- `POST /api/gi/issue`: xuất hàng khỏi kho
- `GET /api/gis`: danh sách phiếu xuất
- `POST /api/socket/call`: endpoint tích hợp socket
- `GET /html`: trả về nội dung XHTML mẫu

## 8. Yêu cầu môi trường

- JDK 17
- Maven Wrapper hoặc Maven cài sẵn
- MySQL đang chạy
- Database tên `demo`

## 9. Cấu hình hiện tại

File cấu hình chính nằm tại [`src/main/resources/application.properties`](/Users/cuongphan/Desktop/Java/FirstProject/src/main/resources/application.properties).

Ứng dụng hiện đang dùng:

- `spring.datasource.url=jdbc:mysql://localhost:3306/demo`
- `spring.datasource.username=root`
- `spring.jpa.hibernate.ddl-auto=update`
- Feign URL mặc định: `http://localhost:8081/api/socket/call`

Luu y: Khong nen commit mat khau that vao repository. Nen dua thong tin nhay cam sang bien moi truong hoac file local rieng.

## 10. Cách chạy dự án

Chạy compile:

```bash
./mvnw -q -DskipTests compile
```

Chạy ứng dụng:

```bash
./mvnw spring-boot:run
```

Chạy test:

```bash
./mvnw test
```

## 11. Ghi chú phát triển

- Dự án đang dùng đồng thời Spring Data JPA và Spring Data JDBC, nên cần quản lý repository rõ ràng để tránh cảnh báo cấu hình
- Muốn chạy app ổn định, cần đảm bảo MySQL local sẵn sàng và cấu hình datasource đúng
- Endpoint `/html` hiện được dùng để minh họa XHTML, tức là HTML viết theo cú pháp XML hợp lệ
