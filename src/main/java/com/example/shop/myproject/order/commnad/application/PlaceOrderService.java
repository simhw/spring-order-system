package com.example.shop.myproject.order.commnad.application;

import com.example.shop.myproject.order.commnad.dto.OrderRequest;

public interface PlaceOrderService {
    public Long placeOrder(OrderRequest request);
}
