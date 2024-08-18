package com.example.shop.myproject.member.ui;

import com.example.shop.myproject.member.command.application.dto.JoinForm;
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
@RequestMapping("/member")
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberDetailService memberDetailService;

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("form", new JoinForm());
        return "member/join";
    }

    @GetMapping("/new/success")
    public String createSuccess(Model model) {
        return "member/join-success";
    }

    @GetMapping("/{memberId}")
    public String mypage(Model model, UserDetailsImpl user) {
        // com.example.shop.myproject.member.query.MemberDetail memberDetail = memberDetailService.getMemberDetail(user.getId());
        // model.addAttribute("data", memberDetail);
        return "member/mypage";
    }

    @GetMapping("/{memberId}/edit")
    public String editProfileRequest(Model model, UserDetailsImpl user) {
//        Member member = memberRepository.findById(user.getId())
//                .orElseThrow(NoMemberException::new);
//
//        model.addAttribute("form", new EditProfileRequest(member));
        return "member/edit-profile";
    }
}