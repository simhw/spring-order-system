package com.example.shop.myproject.member.command.application;

import com.example.shop.myproject.common.ValidationError;
import com.example.shop.myproject.common.ValidationErrorException;
import com.example.shop.myproject.member.command.application.dto.SignupForm;
import com.example.shop.myproject.member.domain.Member;
import com.example.shop.myproject.member.domain.MemberRepository;
import com.example.shop.myproject.member.domain.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignupService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignupForm form) {
        validate(form);

        Member member = new Member(
                form.getEmail(),
                passwordEncoder.encode(form.getPassword()),
                new Profile(form.getNickname())
        );
        memberRepository.save(member);
    }

    private void validate(SignupForm form) {
        ValidationErrorException validationErrorException = new ValidationErrorException();

        if (!form.getPassword().equals(form.getPassword2())) {
            validationErrorException.getErrors().add(new ValidationError("password2", "비밀번호가 일치하지 않습니다."));
        }

        Optional<Member> member = memberRepository.findByEmail(form.getEmail());

        if (member.isPresent()) {
            validationErrorException.getErrors().add(new ValidationError("email", "이미 가입된 이메일입니다."));
        }

        if (!validationErrorException.getErrors().isEmpty()) {
            throw validationErrorException;
        }
    }
}
