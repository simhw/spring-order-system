package com.example.shop.myproject.order.ui;

import com.example.shop.myproject.member.command.application.dto.UserDetailsImpl;
import com.example.shop.myproject.order.application.PlaceOrderServiceV1;
import com.example.shop.myproject.order.application.dto.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final PlaceOrderServiceV1 placeOrderService;

    @PostMapping("")
    public Long placeOrder(@AuthenticationPrincipal UserDetailsImpl user, OrderRequest orderRequest) {
        orderRequest.setOrdererId(user.getId());
        Long orderId = placeOrderService.placeOrder(orderRequest);
        return orderId;
    }
}
