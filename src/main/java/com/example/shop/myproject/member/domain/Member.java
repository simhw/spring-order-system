package com.example.shop.myproject.member.domain;

import com.example.shop.myproject.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Embedded
    private Profile profile;

    protected Member() {
    }

    public Member(String email) {
        this.email = email;
    }

    public Member(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Member(String email, String password, Profile profile) {
        this.email = email;
        this.password = password;
        this.profile = profile;
    }

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }

    public void updateProfile(Profile profile) {
        this.profile = profile;
    }
}