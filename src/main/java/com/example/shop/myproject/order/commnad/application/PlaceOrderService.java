package com.example.shop.myproject.order.commnad.application;

import com.example.shop.myproject.order.commnad.dto.OrderForm;

public interface PlaceOrderService {
    public Long placeOrder(OrderForm request);
}
