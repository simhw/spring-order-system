package com.example.shop.myproject.coupon.ui;

import com.example.shop.myproject.auth.UserDetailsImpl;
import com.example.shop.myproject.common.ApiResponse;
import com.example.shop.myproject.coupon.application.IssueCouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coupons")
public class CouponRestController {

    private final IssueCouponService issueCouponService;

    @PostMapping("/download")
    public ApiResponse<Long> save(@AuthenticationPrincipal UserDetailsImpl user, @RequestParam Long couponId) {
        Long data = issueCouponService.download(user.getId(), couponId);
        return new ApiResponse<>(HttpStatus.CREATED, data);
    }
}
