package com.example.shop.myproject.coupon.application;

import com.example.shop.myproject.coupon.domain.DiscountType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DiscountPolicyFinder {

    private final List<DiscountPolicy> policies;

    public DiscountPolicyFinder(List<DiscountPolicy> policies) {
        this.policies = policies;
    }

    public Optional<DiscountPolicy> find(final DiscountType discountType) {
        return policies.stream()
                .filter(dp -> dp.isSupport(discountType))
                .findAny();
    }
}
