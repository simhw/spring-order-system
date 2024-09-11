package com.example.shop.myproject.coupon.domain;

import com.example.shop.myproject.common.domain.BaseEntity;
import com.example.shop.myproject.member.domain.Member;
import jakarta.persistence.*;

@Entity
@Table(name = "coupon_history")
public class CouponHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "coupon_history_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private boolean isUsed;

    protected CouponHistory() {
    }
}
