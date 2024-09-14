package com.example.shop.myproject.order.commnad.domain;

import com.example.shop.myproject.common.domain.BaseTimeEntity;
import com.example.shop.myproject.coupon.domain.CouponIssued;
import com.example.shop.myproject.delivery.domain.Delivery;
import com.example.shop.myproject.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "orders")
@Getter
@Builder
@AllArgsConstructor()
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member orderer;

    @Comment("총 금액")
    private int totalAmount;

    @Comment("총 할인 금액")
    private int discountAmount;

    @Comment("최종 결제 금액, 할인 적용")
    private int finalAmount;

    @Builder.Default
    @OneToMany(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "order_id")
    private List<OrderLine> orderLines = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Comment("주문 상태")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToOne
    @JoinColumn(name = "coupon_issued_id")
    private CouponIssued couponIssued;

    public Order(Member orderer, List<OrderLine> orderLines, Delivery delivery, OrderStatus status) {
        setOrderer(orderer);
        setDelivery(delivery);
        this.status = status;
        setOrderLines(orderLines);
    }

    private void setOrderer(Member orderer) {
        if (orderer == null) throw new IllegalArgumentException("no orderer");
        this.orderer = orderer;
    }

    private void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    private void setOrderLines(List<OrderLine> orderLines) {
        if (orderLines.isEmpty()) throw new RuntimeException("empty order line");
        this.orderLines = orderLines;
        calculateTotalAmounts();
    }

    private void calculateTotalAmounts() {
        this.totalAmount = orderLines.stream()
                .mapToInt(OrderLine::getAmount)
                .sum();
    }

    public void applyDiscount(CouponIssued couponIssued, int discountAmount) {
        if (couponIssued == null) throw new IllegalArgumentException("no coupon issued");
        this.couponIssued = couponIssued;
        this.discountAmount = discountAmount;
        // 할인 적용 후 최종 금액 계산
        this.finalAmount = this.totalAmount - discountAmount;
    }

    public void cancel() {
        this.status = OrderStatus.CANCELED;
    }
}
