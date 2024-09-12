package com.example.shop.myproject.coupon.domain;

import com.example.shop.myproject.common.domain.BaseEntity;
import com.example.shop.myproject.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "coupon_issue")
public class CouponIssue extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "coupon_issue_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private boolean isUsed;

    protected CouponIssue() {
    }

    public CouponIssue(Coupon coupon, Member member) {
        coupon.verifyValidDateTime(LocalDateTime.now());
        this.coupon = coupon;
        this.member = member;
        this.isUsed = false;
    }
}
