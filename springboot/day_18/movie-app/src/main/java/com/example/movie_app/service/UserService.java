package com.example.movie_app.service;

import com.example.movie_app.entity.User;
import com.example.movie_app.model.request.UpdatePasswordRequest;
import com.example.movie_app.model.request.UpdateProfileUserRequest;
import com.example.movie_app.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final HttpSession session;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    public User updateProfile(UpdateProfileUserRequest request) {
        User user = (User) session.getAttribute("currentUser");

        if (request.getName().isEmpty()) {
            throw new RuntimeException("Can not name is empty");
        }

        user.setName(request.getName());
        return userRepository.save(user);
    }

    public User updatePassword(UpdatePasswordRequest request) {
        User user = (User) session.getAttribute("currentUser");

        if (request.getOldPassword().isEmpty()) {
            throw new RuntimeException("Mật khẩu trống");
        }
        if (request.getNewPassword().isEmpty()) {
            throw new RuntimeException("Mật khẩu trống\"");
        }
        if (request.getConfirmPassword().isEmpty()) {
            throw new RuntimeException("Mật khẩu trống\"");
        }
        if (request.getOldPassword().equals(request.getNewPassword())) {
            throw new RuntimeException("Mạt khẩu cũ trùng mật khẩu mới");
        }

        if (!request.getConfirmPassword().equals(request.getNewPassword())) {
            throw new RuntimeException("Mật khẩu xác nhận không trùng với mật khẩu mới");
        }

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Mật khẩu cũ không đúng");
        }

        String encodePassword = passwordEncoder.encode(request.getConfirmPassword());

        user.setPassword(encodePassword);
        return userRepository.save(user);

    }
}
