package com.example.movie_app.service;

import com.example.movie_app.entity.User;
import com.example.movie_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    public void updateUserProfile(Integer userId, String name) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(name);
        userRepository.save(user);
    }

    public void updateUserPassword(Integer userId, String oldPassword, String newPassword) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        // Kiểm tra mật khẩu cũ
        if (!user.getPassword().equals(oldPassword)) {
            throw new RuntimeException("Mật khẩu cũ không chính xác");
        }

        String encodedNewPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedNewPassword);
        userRepository.save(user);
    }

    public boolean checkOldPassword(Integer userId, String oldPassword) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        System.out.println("Mật khẩu cũ: " + oldPassword);
        System.out.println("Mật khẩu đã mã hóa: " + user.getPassword());

        // So sánh mật khẩu cũ đã mã hóa với mật khẩu đã nhập
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }
}
