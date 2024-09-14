package com.example.shop.myproject.order.commnad.dto;

import com.example.shop.myproject.common.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderForm {
    private Long ordererId;
    private List<OrderProduct> orderProducts = new ArrayList<>();
    private Long couponId;
    private Address address;

    public OrderForm() {
    }

    public OrderForm(Long ordererId, List<OrderProduct> orderProducts) {
        this.ordererId = ordererId;
        this.orderProducts = orderProducts;
    }
}
