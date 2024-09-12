package com.example.shop.myproject.coupon.domain;

import com.example.shop.myproject.common.domain.BaseEntity;
import com.example.shop.myproject.coupon.exception.MinAmountException;
import com.example.shop.myproject.coupon.exception.NotValidCouponException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "coupon")
@AllArgsConstructor
@Builder
public class Coupon extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "coupon_id")
    private Long id;

    @Column(name = "coupon_code")
    private String code;

    private String title;

    @Comment("상세설명")
    private String description;

    @Comment("할인액, 할인률")
    private int discount;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    @Comment("사용 가능 시작일")
    private LocalDateTime startAt;

    @Comment("사용 가능 만료일")
    private LocalDateTime endAt;

    @Comment("주문 최소 금액")
    private int minPaymentAmount;

    @Comment("최대 할인 금액")
    private int maxDiscountAmount;

    @Builder.Default
    @OneToMany(mappedBy = "coupon")
    private List<CouponIssue> issues = new ArrayList<>();

    protected Coupon() {
    }

    public void verifyValidCoupon(int paymentAmount) {
        verifyMinPaymentAmount(paymentAmount);
        verifyValidDateTime(LocalDateTime.now());
    }

    public void verifyValidDateTime(LocalDateTime now) {
        // 현재 시작일 이전이거나 마지막일 이후인 경우 발급 불가능
        if (now.isBefore(startAt) || now.isAfter(endAt)) {
            throw new NotValidCouponException();
        }
    }

    public void verifyMinPaymentAmount(int paymentAmount) {
        if (paymentAmount < minPaymentAmount) {
            throw new MinAmountException();
        }
    }
}


