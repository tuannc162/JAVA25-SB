package com.example.movie_app.service;

import com.example.movie_app.entity.User;
import com.example.movie_app.model.enums.UserRole;
import com.example.movie_app.model.request.LoginRequest;
import com.example.movie_app.model.request.RegisterRequest;
import com.example.movie_app.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final HttpSession session;

    public void login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }
        // Có thể lưu trong cookie, redis, database, ....
        session.setAttribute("currentUser", user);
    }

    public void register(RegisterRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        if (userOptional.isPresent()) {
            throw new RuntimeException("Email này đã tồn tại");
        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("Mật khẩu không khớp");
        }

        String endCodePassword = passwordEncoder.encode(request.getConfirmPassword());

        User newUser = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(endCodePassword)
                .role(UserRole.USER)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        userRepository.save(newUser);
    }
}
