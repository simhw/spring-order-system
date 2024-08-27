package com.example.shop.myproject.member.ui;

import com.example.shop.myproject.common.ValidationError;
import com.example.shop.myproject.common.ValidationErrorException;
import com.example.shop.myproject.member.command.application.SignupService;
import com.example.shop.myproject.member.command.application.dto.SignupForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/members")
public class SignupController {

    private final SignupService signupService;

    @GetMapping("/new")
    public String signup(Model model) {
        model.addAttribute("form", new SignupForm());
        return "member/signup";
    }

    @GetMapping("/new/success")
    public String success(Model model) {
        return "member/signup-success";
    }

    @PostMapping("/signup")
    public String create(@Valid @ModelAttribute(name = "form") SignupForm form, BindingResult bindingResult) {
        try {
            signupService.signup(form);
            return "redirect:/members/new/success";

        } catch (ValidationErrorException validationErrorException) {
            for (ValidationError error : validationErrorException.getErrors()) {
                bindingResult.addError(new FieldError(error.getName(), error.getName(), error.getCode()));
            }
            return "member/signup";
        }
    }
}
