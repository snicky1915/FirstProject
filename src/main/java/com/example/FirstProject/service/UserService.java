package com.example.FirstProject.service;

import com.example.FirstProject.common.CrudService;
import com.example.FirstProject.dto.request.LoginRequest;
import com.example.FirstProject.dto.request.RegisterRequest;
import com.example.FirstProject.entity.User;
import com.example.FirstProject.entity.UserHistory;
import com.example.FirstProject.repository.UserHistoryRepository;
import com.example.FirstProject.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends CrudService<User,UserHistory> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // Dùng BCrypt

    public UserService(UserRepository userRepository,UserHistoryRepository  userHistoryRepository, PasswordEncoder passwordEncoder) {
        // gọi constructor cha CrudService
        super(userRepository, userHistoryRepository, User.class, UserHistory.class);

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Register
    public User register(RegisterRequest request){
        // 1. Kiểm tra username đã tồn tại chưa
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username đã tồn tại");
        }

        // 2. Kiểm tra email đã tồn tại chưa (nếu cần)
        if (request.getEmail() != null && userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email đã tồn tại");
        }

        // 3. Kiểm tra password có hợp lệ không
        if (request.getPassword().length() < 6) {
            throw new IllegalArgumentException("Password phải có ít nhất 6 ký tự");
        }

        // 4. Tạo user mới
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // mã hóa
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setRole("USER");

        // Đặc biệt cho bảng user: createdId chính là username đăng ký
        user.setCreatedId(request.getUsername());

        return userRepository.save(user);
    }

    // Login
    public User login(LoginRequest request){
        Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                return user; // login thành công
            }
        }
        return null; // login thất bại
    }

    //tést
    public String createTestUser(User requestUser) {
        return this.create(requestUser);
    }


}
