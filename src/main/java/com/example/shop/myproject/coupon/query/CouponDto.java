package com.example.shop.myproject.coupon.query;

import com.example.shop.myproject.coupon.domain.Coupon;
import com.example.shop.myproject.coupon.domain.CouponIssue;
import com.example.shop.myproject.coupon.domain.DiscountType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CouponDto {
    private Long id;
    private String title;
    private String description;
    private DiscountType discountType;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private boolean isUsed;

    protected CouponDto() {
    }

    public CouponDto(CouponIssue issue) {
        Coupon coupon = issue.getCoupon();
        this.id = coupon.getId();
        this.title = coupon.getTitle();
        this.description = coupon.getDescription();
        this.discountType = coupon.getDiscountType();
        this.startAt = coupon.getStartAt();
        this.endAt = coupon.getEndAt();
        this.isUsed = issue.isUsed();
    }
}
