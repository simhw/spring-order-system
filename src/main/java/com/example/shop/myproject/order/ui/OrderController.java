package com.example.shop.myproject.order.ui;

import com.example.shop.myproject.auth.UserDetailsImpl;
import com.example.shop.myproject.order.commnad.application.PlaceOrderService;
import com.example.shop.myproject.order.commnad.dto.OrderForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final PlaceOrderService placeOrderService;

    @PostMapping("")
    public Long placeOrder(@AuthenticationPrincipal UserDetailsImpl user, OrderForm orderForm) {
        orderForm.setOrdererId(user.getId());
        return placeOrderService.placeOrder(orderForm);
    }
}
