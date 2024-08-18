package com.example.shop.myproject.member.command.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class JoinForm {
    @NotBlank(message = "필수 입력 항목입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "영문, 숫자를 포함한 8자 이상의 비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "확인을 위해 비밀번호를 한 번 더 입력해주세요.")
    private String confirmPassword;

    @Length(min = 2, max = 20)
    private String nickname;
}
