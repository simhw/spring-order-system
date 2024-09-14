package com.example.shop.myproject.order.commnad.application;

import com.example.shop.myproject.catalog.command.domain.product.Product;
import com.example.shop.myproject.catalog.command.domain.product.ProductRepository;

import com.example.shop.myproject.coupon.domain.*;
import com.example.shop.myproject.member.domain.Member;
import com.example.shop.myproject.member.domain.MemberRepository;
import com.example.shop.myproject.order.commnad.domain.OrderRepository;
import com.example.shop.myproject.order.commnad.dto.OrderForm;
import com.example.shop.myproject.order.commnad.dto.OrderProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CreateOrderServiceIntegrationTest {

    @Autowired
    private CreateOrderService createOrderService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    CouponRepository couponRepository;

    @Autowired
    private CouponIssuedRepository couponIssuedRepository;

    @Autowired
    private OrderRepository orderRepository;

    Member orderer;
    Product product;
    Coupon coupon;
    CouponIssued couponIssued;

    @BeforeEach
    void init() {
        orderer = Member.builder()
                .id(1L)
                .build();
        memberRepository.save(orderer);

        product = Product.builder()
                .id(1L)
                .name("product1")
                .price(125000)
                .stock(100)
                .build();
        productRepository.save(product);

        coupon = Coupon.builder()
                .id(1L)
                .discount(10)
                .discountType(DiscountType.FIXED_RATE)
                .startAt(LocalDateTime.of(2024, 9, 1, 0, 0, 0))
                .endAt(LocalDateTime.of(2024, 9, 30, 23, 59, 59))
                .minPaymentAmount(0)
                .maxDiscountAmount(30000)
                .build();
        couponRepository.save(coupon);

        couponIssued = CouponIssued.builder()
                .id(1L)
                .member(orderer)
                .coupon(coupon)
                .build();
        couponIssuedRepository.save(couponIssued);

    }

    @Test
    void 주문() {
        // given
        OrderForm orderForm = new OrderForm();
        orderForm.setOrdererId(orderer.getId());
        orderForm.setOrderProducts(List.of(new OrderProduct(product.getId(), 2)));
        orderForm.setCouponId(couponIssued.getId());

        // when
        Long orderId = createOrderService.createOrder(orderForm);

        // then
        assertNotNull(orderId);
        assertTrue(orderRepository.findById(orderId).isPresent());
    }
}