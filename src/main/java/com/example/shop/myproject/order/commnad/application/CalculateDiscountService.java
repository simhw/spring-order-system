package com.example.shop.myproject.order.commnad.application;

import com.example.shop.myproject.coupon.application.DiscountPolicy;
import com.example.shop.myproject.coupon.application.DiscountPolicyFinder;
import com.example.shop.myproject.coupon.application.NoCouponException;
import com.example.shop.myproject.coupon.domain.Coupon;
import com.example.shop.myproject.coupon.domain.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class CalculateDiscountService {

    private final CouponRepository couponRepository;
    private final DiscountPolicyFinder discountPolicyFinder;

    public int calculateDiscount(Long couponId, int price) {
        // TODO 쿠폰 발급 여부 확인
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(NoCouponException::new);

        DiscountPolicy discountPolicy = discountPolicyFinder.find(coupon.getDiscountType())
                .orElseThrow(RuntimeException::new);

        int discountAmount = discountPolicy.calculateDiscount(coupon, price);
        return price - discountAmount;
    }
}
