package org.example.oicc.service;

import org.example.oicc.dto.*;
import org.example.oicc.model.User;
import org.example.oicc.repository.UserRepository;
import org.example.oicc.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // 用户注册
    public void register(RegistrationRequest req) {
        if (userRepository.existsByUsername(req.getUsername())) {
            throw new IllegalArgumentException("username already exists");
        }
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("email already exists");
        }
        User u = new User();
        u.setUsername(req.getUsername());
        u.setEmail(req.getEmail());
        u.setPassword(passwordEncoder.encode(req.getPassword()));
        u.setNickname(req.getNickname());
        userRepository.save(u);
    }

    // 用户登录
    public String login(LoginRequest req) {
        User user = userRepository.findByUsername(req.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("invalid credentials"));
        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("invalid credentials");
        }
        return jwtUtil.generateToken(user.getUsername());
    }

    // 获取用户信息
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }

    // 更新用户资料
    public User updateProfile(String username, UpdateProfileRequest req) {
        User u = userRepository.findByUsername(username).orElseThrow();
        if (req.getNickname() != null) u.setNickname(req.getNickname());
        if (req.getAvatarUrl() != null) u.setAvatarUrl(req.getAvatarUrl());
        if (req.getBio() != null) u.setBio(req.getBio());
        return userRepository.save(u);
    }
}
