package com.example.FirstProject.controller;
import com.example.FirstProject.dto.request.LoginRequest;
import com.example.FirstProject.dto.request.RegisterRequest;
import com.example.FirstProject.entity.User;
import com.example.FirstProject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        User user = userService.register(request);
        return ResponseEntity.ok("Đăng ký thành công: " + user.getUsername());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = userService.login(request);
        if (user != null) {
            return ResponseEntity.ok("Đăng nhập thành công, xin chào " + user.getFullName());
        }
        return ResponseEntity.status(401).body("Sai username hoặc password");
    }
}
