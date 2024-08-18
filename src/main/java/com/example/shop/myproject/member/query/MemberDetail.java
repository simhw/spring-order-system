package com.example.shop.myproject.member.query;

import com.example.shop.myproject.follow.query.FollowSummary;
import com.example.shop.myproject.member.domain.Member;
import lombok.Data;


@Data
public class MemberDetail {
    Long memberId;
    String nickname;
    String introduce;
    String image;
    Long followerCount;
    Long followeeCount;
    FollowSummary follow = new FollowSummary();

    public MemberDetail() {
    }

    public MemberDetail(Member member, Long followerCount, Long followeeCount) {
        this.memberId = member.getId();
        this.nickname = member.getProfile().getNickname();
        this.introduce = member.getProfile().getIntroduce();
        this.image = member.getProfile().getImage();
        this.followerCount = followerCount;
        this.followeeCount = followeeCount;
    }
}
