package com.example.shop.myproject.order.commnad.application;

import com.example.shop.myproject.coupon.application.DiscountPolicyFinder;
import com.example.shop.myproject.coupon.application.FixedRateDiscountPolicy;
import com.example.shop.myproject.coupon.application.NotValidCouponException;
import com.example.shop.myproject.coupon.domain.Coupon;
import com.example.shop.myproject.coupon.domain.CouponRepository;
import com.example.shop.myproject.coupon.domain.DiscountType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalculateDiscountServiceTest {

    @Mock
    CouponRepository couponRepository;

    @Mock
    DiscountPolicyFinder discountPolicyFinder;

    @InjectMocks
    private CalculateDiscountService calculateDiscountService;

    @Test
    void 정액_할인() {
        // given
        int price = 125000;
        Long couponId = 1L;
        Coupon coupon = Coupon.builder()
                .id(couponId)
                .discount(10)
                .discountType(DiscountType.FIXED_RATE)
                .startAt(LocalDateTime.of(2024, 9, 1, 0, 0, 0))
                .endAt(LocalDateTime.of(2024, 9, 30, 23, 59, 59))
                .minPaymentAmount(0)
                .maxDiscountAmount(30000)
                .build();

        when(couponRepository.findById(couponId)).thenReturn(Optional.of(coupon));
        when(discountPolicyFinder.find(DiscountType.FIXED_RATE)).thenReturn(Optional.of(new FixedRateDiscountPolicy()));

        // when
        int calculateDiscount = calculateDiscountService.calculateDiscount(couponId, price);

        // then
        assertEquals(calculateDiscount, price * 0.9);
    }

    @Test
    void 최대_할인_금액을_초과한_정액_할인() {
        // given
        int price = 350000;
        Long couponId = 1L;
        Coupon coupon = Coupon.builder()
                .id(couponId)
                .discount(10)
                .discountType(DiscountType.FIXED_RATE)
                .startAt(LocalDateTime.of(2024, 9, 1, 0, 0, 0))
                .endAt(LocalDateTime.of(2024, 9, 30, 23, 59, 59))
                .minPaymentAmount(0)
                .maxDiscountAmount(30000)
                .build();

        when(couponRepository.findById(couponId)).thenReturn(Optional.of(coupon));
        when(discountPolicyFinder.find(DiscountType.FIXED_RATE)).thenReturn(Optional.of(new FixedRateDiscountPolicy()));

        // when
        int calculateDiscount = calculateDiscountService.calculateDiscount(couponId, price);

        // then
        assertEquals(calculateDiscount, price - 30000);
    }

    @Test
    void  기간이_만료된_쿠폰을_적용한_정액_할인() {
        // given
        int price = 350000;
        Long couponId = 1L;
        Coupon coupon = Coupon.builder()
                .id(couponId)
                .discount(10)
                .discountType(DiscountType.FIXED_RATE)
                .startAt(LocalDateTime.of(2024, 9, 1, 0, 0, 0))
                .endAt(LocalDateTime.of(2024, 9, 10, 23, 59, 59))
                .minPaymentAmount(0)
                .maxDiscountAmount(30000)
                .build();

        when(couponRepository.findById(couponId)).thenReturn(Optional.of(coupon));
        when(discountPolicyFinder.find(DiscountType.FIXED_RATE)).thenReturn(Optional.of(new FixedRateDiscountPolicy()));

        // when, then
        assertThrows(NotValidCouponException.class,
                () -> calculateDiscountService.calculateDiscount(couponId, price));

    }

}