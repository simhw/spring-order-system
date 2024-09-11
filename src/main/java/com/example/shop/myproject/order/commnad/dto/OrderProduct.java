package com.example.shop.myproject.order.commnad.dto;

import lombok.Data;

@Data
public class OrderProduct {
    private Long productId;
    private int quantity;

    public OrderProduct(long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
