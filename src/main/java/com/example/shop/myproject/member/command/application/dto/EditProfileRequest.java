package com.example.shop.myproject.member.command.application.dto;

import com.example.shop.myproject.member.domain.Member;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@Data
public class EditProfileRequest {
    @NotEmpty
    Long id;

    @NotEmpty
    String email;

    @NotEmpty
    @Length(min = 2, max = 50)
    private String nickname;

    private String profileImage;

    private MultipartFile profileImageFile;

    @Length(max = 50)
    private String introduce;

    @NotEmpty
    private String gender;

    public EditProfileRequest(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.nickname = member.getProfile().getNickname();
        this.profileImage = member.getProfile().getImage();
        this.introduce = member.getProfile().getIntroduce();
        this.gender = String.valueOf(member.getProfile().getGender());
    }
}
