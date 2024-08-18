package com.example.shop.myproject.member.domain;

import com.example.shop.myproject.common.domain.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Embeddable
public class Profile {
    private String nickname;

    private String introduce;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "profile_image")
    private String image;

    protected Profile() {
    }

    public Profile(String nickname) {
        this.nickname = nickname;
        this.gender = Gender.OTHER;
    }
}
