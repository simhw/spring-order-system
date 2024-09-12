package com.example.shop.myproject.coupon.application;

import com.example.shop.myproject.coupon.domain.DiscountType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class DiscountPolicyFinder {

    private final List<DiscountPolicy> policies;

    public DiscountPolicyFinder(final List<DiscountPolicy> policies) {
        this.policies = policies;
    }

    public Optional<DiscountPolicy> find(final DiscountType discountType) {
        log.info("Discount policy found for type {}", discountType);
        return policies.stream()
                .filter(dp -> dp.isSupport(discountType))
                .findAny();
    }
}
