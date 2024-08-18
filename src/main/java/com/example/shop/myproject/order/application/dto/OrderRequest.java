package com.example.shop.myproject.order.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderRequest {
    private Long ordererId;
    private List<OrderProduct> orderProducts = new ArrayList<>();
}
