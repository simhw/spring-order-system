package com.example.shop.myproject.coupon.application;

import com.example.shop.myproject.coupon.domain.Coupon;
import com.example.shop.myproject.coupon.domain.DiscountType;
import org.springframework.stereotype.Component;

@Component
public class FixedRateDiscountPolicy implements DiscountPolicy {

    private static final double PERCENTAGE_DIVISOR = 100.0;

    @Override
    public int calculateDiscount(Coupon coupon, int price) {
        // 쿠폰 유효성 검사
        coupon.verifyValidCoupon(price);

        // 할인된 금액 계산
        int discountAmount = (int) (price * (coupon.getDiscount() / PERCENTAGE_DIVISOR));

        if (discountAmount > coupon.getMaxDiscountAmount()) {
            discountAmount = coupon.getMaxDiscountAmount();
        }

        return discountAmount;
    }

    @Override
    public boolean isSupport(DiscountType discountType) {
        return DiscountType.FIXED_RATE.equals(discountType);
    }
}
