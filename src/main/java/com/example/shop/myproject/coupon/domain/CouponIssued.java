package com.example.shop.myproject.coupon.domain;

import com.example.shop.myproject.common.domain.BaseEntity;
import com.example.shop.myproject.member.domain.Member;
import com.example.shop.myproject.order.commnad.domain.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
@Entity
@Table(name = "coupon_issued")
@AllArgsConstructor
public class CouponIssued extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "coupon_issued_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(mappedBy = "couponIssued")
    private Order order;

    private boolean isUsed;

    protected CouponIssued() {
    }

    public CouponIssued(Coupon coupon, Member member) {
        coupon.verifyValidDateTime(LocalDateTime.now());
        this.coupon = coupon;
        this.member = member;
        this.isUsed = false;
    }
}
