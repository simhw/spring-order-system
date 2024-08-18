package com.example.shop.myproject;

import com.example.shop.myproject.member.command.application.dto.LoginRequest;
import com.example.shop.myproject.member.command.application.dto.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MainController {
    @GetMapping(path = "/")
    public String index(UserDetailsImpl user, Model model) {
        if (user != null) {
            model.addAttribute("user", user);
            return "index-login";
        } else {
            return "index";
        }
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("form", new LoginRequest());
        return "login";
    }
}