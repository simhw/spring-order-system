package com.example.shop.myproject.order.commnad.application;

import com.example.shop.myproject.coupon.application.DiscountPolicy;
import com.example.shop.myproject.coupon.application.DiscountPolicyFinder;
import com.example.shop.myproject.coupon.domain.CouponIssued;
import com.example.shop.myproject.coupon.exception.NoCouponException;
import com.example.shop.myproject.coupon.domain.Coupon;
import com.example.shop.myproject.coupon.domain.CouponRepository;
import com.example.shop.myproject.order.commnad.domain.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class CalculateDiscountService {

    private final CouponRepository couponRepository;
    private final DiscountPolicyFinder discountPolicyFinder;

    public void calculateDiscount(Order order, CouponIssued couponIssued) {
        Coupon coupon = couponRepository.findById(couponIssued.getCoupon().getId())
                .orElseThrow(NoCouponException::new);
        DiscountPolicy discountPolicy = discountPolicyFinder.find(coupon.getDiscountType())
                .orElseThrow(RuntimeException::new);

        int discount = discountPolicy.calculateDiscount(coupon, order.getTotalAmount());
        int discountAmount = order.getTotalAmount() - discount;
        order.applyDiscount(couponIssued, discountAmount);
    }
}
