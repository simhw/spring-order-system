package com.example.shop.myproject.follow.query;

import com.example.shop.myproject.member.domain.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FollowDto {
    private Long memberId;
    private String email;
    private String nickname;
    private String profileImage;

    public FollowDto(Member member) {
        this.memberId = member.getId();
        this.email = member.getEmail();
        this.nickname = member.getProfile().getNickname();
        this.profileImage = member.getProfile().getImage();
    }
}
