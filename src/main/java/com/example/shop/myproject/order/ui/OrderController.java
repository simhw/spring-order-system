package com.example.shop.myproject.order.ui;

import com.example.shop.myproject.auth.UserDetailsImpl;
import com.example.shop.myproject.order.commnad.application.PlaceOrderService;
import com.example.shop.myproject.order.commnad.infra.PlaceOrderServiceV1;
import com.example.shop.myproject.order.commnad.dto.OrderRequest;
import com.example.shop.myproject.order.commnad.infra.PlaceOrderServiceV3;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final PlaceOrderService placeOrderService;

    @PostMapping("")
    public Long placeOrder(@AuthenticationPrincipal UserDetailsImpl user, OrderRequest orderRequest) {
        orderRequest.setOrdererId(user.getId());
        return placeOrderService.placeOrder(orderRequest);
    }
}
