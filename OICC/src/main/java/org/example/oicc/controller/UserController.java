package org.example.oicc.controller;

import org.example.oicc.dto.UpdateProfileRequest;
import org.example.oicc.dto.UserProfileResponse;
import org.example.oicc.model.User;
import org.example.oicc.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // 构造函数注入（推荐写法）
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 获取当前登录用户信息
     * 需要请求头中携带 Bearer Token
     */
    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> me(Principal principal) {
        User user = userService.getByUsername(principal.getName());
        // ✅ 返回专用 DTO，避免匿名对象问题
        UserProfileResponse response = new UserProfileResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getNickname(),
                user.getAvatarUrl(),
                user.getBio()
        );
        return ResponseEntity.ok(response);
    }

    /**
     * 更新当前登录用户资料（昵称、头像、简介）
     */
    @PutMapping("/me")
    public ResponseEntity<String> updateMe(Principal principal, @RequestBody UpdateProfileRequest request) {
        userService.updateProfile(principal.getName(), request);
        return ResponseEntity.ok("User profile updated successfully");
    }
}
