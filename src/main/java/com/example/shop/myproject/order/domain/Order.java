package com.example.shop.myproject.order.domain;

import com.example.shop.myproject.common.domain.BaseTimeEntity;
import com.example.shop.myproject.delivery.domain.Delivery;
import com.example.shop.myproject.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member orderer;

    private int totalPrice;

    @OneToMany(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "order_id")
    private List<OrderLine> orderLines = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order(Member orderer, List<OrderLine> orderLines, OrderStatus status) {
        this.orderer = orderer;
        this.orderLines = orderLines;
        this.status = status;
        this.totalPrice = getTotalPrice();
    }

    public void setOrderProduct() {
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    public void cancel() {
    }

    public int getTotalPrice() {
        return orderLines.stream()
                .mapToInt(OrderLine::getTotalPrice)
                .sum();
    }
}
