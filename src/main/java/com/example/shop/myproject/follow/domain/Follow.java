package com.example.shop.myproject.follow.domain;

import com.example.shop.myproject.common.domain.BaseEntity;
import com.example.shop.myproject.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Follow extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "follow_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "follower_id")
    private Member follower;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "followee_id")
    private Member followee;

    @Column(name = "matapl")
    private boolean matpal;

    public Follow(Member follower, Member followee) {
        this.follower = follower;
        this.followee = followee;
    }
}
