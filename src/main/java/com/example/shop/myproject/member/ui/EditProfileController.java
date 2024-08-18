package com.example.shop.myproject.member.ui;

import com.example.shop.myproject.member.command.application.EditMemberProfileService;
import com.example.shop.myproject.member.command.application.dto.EditProfileRequest;
import com.example.shop.myproject.member.command.application.dto.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class EditProfileController {

    private final EditMemberProfileService editMemberProfileService;

    @PostMapping("/{memberId}/edit")
    public String editProfile(EditProfileRequest form, BindingResult bindingResult,
                              @SessionAttribute(name = "LOGIN_USER", required = false) UserDetailsImpl user) throws IOException {
        if (bindingResult.hasErrors()) {
            return "members/editProfileForm";
        }
        // form.setId(user.getId());
        editMemberProfileService.editProfile(form);
        return "redirect:/";
    }
}
