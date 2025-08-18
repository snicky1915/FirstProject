package com.example.FirstProject.entity;

import com.example.FirstProject.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    @Column(name = "user_id")
    private Long userId;  // 🔑 Khóa chính

    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;  // 👤 Tên đăng nhập

    @Column(name = "password", length = 255, nullable = false)
    private String password;  // 🔑 Mật khẩu (đã mã hóa)

    @Column(name = "full_name", length = 100)
    private String fullName;  // 📝 Họ và tên

    @Column(name = "email", length = 100)
    private String email;     // 📧 Email

    @Column(name = "role", length = 50)
    private String role;      // 🎭 Vai trò (ADMIN / USER / MANAGER)
}
