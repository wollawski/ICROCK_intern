package org.example.oicc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "🎉 欢迎使用 OIDC 服务项目！Spring Boot 已成功运行。";
    }
}
