package com.example.shop.myproject.member.ui;

import com.example.shop.myproject.common.ValidationError;
import com.example.shop.myproject.common.ValidationErrorException;
import com.example.shop.myproject.member.command.application.JoinMemberService;
import com.example.shop.myproject.member.command.application.dto.JoinForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/members")
public class JoinMemberController {

    private final JoinMemberService memberJoinMemberService;

    @PostMapping("/new")
    public String create(@Validated @ModelAttribute(name = "form") JoinForm form, BindingResult bindingResult) {
        try {
            memberJoinMemberService.join(form);
            return "redirect:/members/new/success";

        } catch (ValidationErrorException validationErrorException) {
            for (ValidationError error : validationErrorException.getErrors()) {
                bindingResult.addError(new FieldError(error.getName(), error.getName(), error.getCode()));
            }
            return "member/join";
        }
    }
}
