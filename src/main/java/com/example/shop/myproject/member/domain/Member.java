package com.example.shop.myproject.member.domain;

import com.example.shop.myproject.common.domain.BaseEntity;
import com.example.shop.myproject.like.domain.Like;
import jakarta.persistence.*;
import jodd.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@Builder
@Table(name = "member")
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

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<Like> likes = new ArrayList<>();

    protected Member() {
    }

    public Member(String email, String password, Profile profile) {
        this.email = email;
        this.password = password;
        this.profile = profile;
    }

    private void setEmail(String email) {
        if (StringUtil.isNotBlank(email)) {
            this.email = email;
        }
    }

    public void updateProfile(Profile profile) {
        this.profile = profile;
    }
}