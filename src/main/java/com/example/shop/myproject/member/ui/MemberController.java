package com.example.shop.myproject.member.ui;

import com.example.shop.myproject.member.command.application.dto.UserDetailsImpl;
import com.example.shop.myproject.member.domain.MemberRepository;
import com.example.shop.myproject.member.query.MemberDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberDetailService memberDetailService;

    @GetMapping("/{memberId}")
    public String mypage(Model model, UserDetailsImpl user) {
        return "member/mypage";
    }

    @GetMapping("/{memberId}/edit/profile")
    public String editProfileRequest(Model model, UserDetailsImpl user) {
        return "member/edit/profile";
    }
}