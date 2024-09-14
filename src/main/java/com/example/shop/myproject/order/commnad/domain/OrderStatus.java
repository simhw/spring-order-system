package com.example.shop.myproject.order.commnad.domain;

public enum OrderStatus {
    PAYMENT_WAITING("결제대기"),
    PREPARING("준비중"),
    SHIPPED("발송"),
    DELIVERING("배송중"),
    DELIVERY_COMPLETED("배송완료"),
    CANCELED("주문취소");

    private String description;

    OrderStatus(String description) {
    }
}
