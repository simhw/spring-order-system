package com.example.shop.myproject.coupon.domain;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

@DataJpaTest
class CouponRepositoryTest {

    @Autowired
    private CouponRepository couponRepository;

    @BeforeEach
    public void init() {
        Coupon coupon = Coupon.builder()
                .id(1L)
                .discount(10)
                .discountType(DiscountType.FIXED_RATE)
                .startAt(LocalDateTime.of(2024, 9, 1, 0, 0, 0))
                .endAt(LocalDateTime.of(2024, 9, 30, 23, 59, 59))
                .minPaymentAmount(0)
                .maxDiscountAmount(30000)
                .build();

        couponRepository.save(coupon);
    }
}