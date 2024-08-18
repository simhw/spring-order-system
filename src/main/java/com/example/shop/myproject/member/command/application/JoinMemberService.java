package com.example.shop.myproject.member.command.application;

import com.example.shop.myproject.common.ValidationError;
import com.example.shop.myproject.common.ValidationErrorException;
import com.example.shop.myproject.member.command.application.dto.JoinForm;
import com.example.shop.myproject.member.domain.Member;
import com.example.shop.myproject.member.domain.MemberRepository;
import com.example.shop.myproject.member.domain.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JoinMemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void join(JoinForm form) {
        validate(form);

        Profile profile = new Profile(form.getNickname());
        Member member = new Member(form.getEmail(), form.getPassword(), profile);

        memberRepository.save(member);
    }

    private void validate(JoinForm form) {
        ValidationErrorException validationErrorException = new ValidationErrorException();

        if (!form.getPassword().equals(form.getConfirmPassword())) {
            validationErrorException.getErrors()
                    .add(new ValidationError("confirmPassword", "비밀번호가 일치하지 않습니다."));
        }

        memberRepository.findByEmail(form.getEmail())
                .ifPresent(member -> validationErrorException.getErrors()
                .add(new ValidationError("email", "이미 가입된 이메일입니다.")));

        if (!validationErrorException.getErrors().isEmpty()) {
            throw validationErrorException;
        }
    }
}
