package com.example.shop.myproject.coupon.application;

import com.example.shop.myproject.coupon.domain.Coupon;
import com.example.shop.myproject.coupon.domain.DiscountType;
import org.springframework.stereotype.Component;

@Component
public class FixedAmountDiscountPolicy implements DiscountPolicy {
    @Override
    public int calculateDiscount(Coupon coupon, int price) {
        // 쿠폰 유효성 검사
        coupon.verifyValidCoupon(price);

        // 할인된 금액 계산
        int discountAmount = price - coupon.getDiscount();

        if (discountAmount > coupon.getMaxDiscountAmount()) {
            discountAmount = coupon.getMaxDiscountAmount();
        }

        return discountAmount;
    }

    @Override
    public boolean isSupport(DiscountType discountType) {
        return discountType == DiscountType.FIXED_AMOUNT;
    }
}
