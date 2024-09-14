package com.example.shop.myproject.order.commnad.application;

import com.example.shop.myproject.catalog.command.domain.product.Product;
import com.example.shop.myproject.common.domain.Address;
import com.example.shop.myproject.coupon.application.DiscountPolicyFinder;
import com.example.shop.myproject.coupon.application.FixedRateDiscountPolicy;
import com.example.shop.myproject.coupon.domain.CouponIssued;
import com.example.shop.myproject.coupon.domain.Coupon;
import com.example.shop.myproject.coupon.domain.CouponRepository;
import com.example.shop.myproject.coupon.domain.DiscountType;
import com.example.shop.myproject.delivery.domain.Delivery;
import com.example.shop.myproject.member.domain.Member;
import com.example.shop.myproject.order.commnad.domain.Order;
import com.example.shop.myproject.order.commnad.domain.OrderLine;
import com.example.shop.myproject.order.commnad.domain.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
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

    Member orderer;
    Order order;

    @BeforeEach
    void init() {
        orderer = Member.builder()
                .id(1L)
                .build();

        // order
        Product product = Product.builder()
                .id(1L)
                .stock(1000)
                .name("product")
                .price(112500)
                .build();

        Delivery delivery = Delivery.builder()
                .address(new Address("city", "street", "zipcode"))
                .build();

        order = new Order(
                orderer,
                List.of(new OrderLine(product, product.getPrice(), 2)),
                delivery,
                OrderStatus.PREPARING
        );
    }
    @Test
    void 정액_할인() {
        // given
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

        Long couponIssuedId = 1L;
        CouponIssued couponIssued = CouponIssued.builder()
                .id(couponIssuedId)
                .coupon(coupon)
                .member(orderer)
                .isUsed(false)
                .build();

        when(couponRepository.findById(couponId)).thenReturn(Optional.of(coupon));
        when(discountPolicyFinder.find(DiscountType.FIXED_RATE)).thenReturn(Optional.of(new FixedRateDiscountPolicy()));

        // when
        calculateDiscountService.calculateDiscount(order, couponIssued);

        // then
        System.out.println("total amount = " + order.getTotalAmount());
        assertEquals(order.getFinalAmount(), order.getTotalAmount() * 0.9);
    }

    @Test
    void 최대_할인_금액을_초과한_정액_할인() {
        // given
        /**
        int price = 350000;
        Long couponId = 1L;

        Order order = new Order();

        Coupon coupon = Coupon.builder()
                .id(couponId)
                .discount(10)
                .discountType(DiscountType.FIXED_RATE)
                .startAt(LocalDateTime.of(2024, 9, 1, 0, 0, 0))
                .endAt(LocalDateTime.of(2024, 9, 30, 23, 59, 59))
                .minPaymentAmount(0)
                .maxDiscountAmount(30000)
                .build();

        CouponIssued couponIssued = new CouponIssued(coupon, null);

        when(couponRepository.findById(couponId)).thenReturn(Optional.of(coupon));
        when(discountPolicyFinder.find(DiscountType.FIXED_RATE)).thenReturn(Optional.of(new FixedRateDiscountPolicy()));

        // when
        int calculateDiscount = calculateDiscountService.calculateDiscount(order, couponIssued);

        // then
        assertEquals(calculateDiscount, price - 30000);
         */
    }

    @Test
    void  기간이_만료된_쿠폰을_적용한_정액_할인() {
        // given
        /**
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

         */
    }
}