package com.example.shop.myproject.order.commnad.application;

import com.example.shop.myproject.catalog.command.domain.product.Product;
import com.example.shop.myproject.catalog.command.domain.product.ProductRepository;

import com.example.shop.myproject.coupon.domain.CouponIssuedRepository;
import com.example.shop.myproject.coupon.domain.CouponIssued;
import com.example.shop.myproject.coupon.exception.NoCouponException;
import com.example.shop.myproject.delivery.domain.Delivery;
import com.example.shop.myproject.delivery.domain.DeliveryStatus;
import com.example.shop.myproject.member.exception.NoMemberException;
import com.example.shop.myproject.member.domain.Member;
import com.example.shop.myproject.member.domain.MemberRepository;
import com.example.shop.myproject.order.commnad.dto.OrderForm;
import com.example.shop.myproject.order.commnad.domain.Order;
import com.example.shop.myproject.order.commnad.domain.OrderLine;
import com.example.shop.myproject.order.commnad.domain.OrderRepository;
import com.example.shop.myproject.order.commnad.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class CreateOrderService {

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final CouponIssuedRepository couponIssuedRepository;
    private final CalculateDiscountService calculateDiscountService;
    private final OrderRepository orderRepository;

    @Transactional
    public Long createOrder(OrderForm form) {
        try {
            Member member = memberRepository.findById(form.getOrdererId())
                    .orElseThrow(() -> new NoMemberException(form.getOrdererId()));

            List<OrderLine> orderLines = form.getOrderProducts().stream()
                    .map((op) -> {
                        Product product = productRepository.findById(op.getProductId())
                                .orElseThrow(() -> new NoOrderProductException(op.getProductId()));
                        product.decreaseStock(op.getQuantity());
                        return new OrderLine(product, product.getPrice(), op.getQuantity());
                    })
                    .toList();

            // 배달 정보 생성
            Delivery delivery = new Delivery(form.getAddress(), DeliveryStatus.READY);
            // 주문 생성
            Order order = new Order(member, orderLines, delivery, OrderStatus.PREPARING);
            // 쿠폰 적용 및 할인 금액 계산
            CouponIssued couponIssued = couponIssuedRepository.findById(form.getCouponId())
                    .orElseThrow(NoCouponException::new);
            calculateDiscountService.calculateDiscount(order, couponIssued);

            orderRepository.save(order);
            return order.getId();

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
