package com.example.shop.myproject.coupon.ui;

import com.example.shop.myproject.coupon.domain.Coupon;
import com.example.shop.myproject.coupon.domain.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coupons")
public class CouponRestController {

    private final CouponRepository couponRepository;

    @GetMapping("")
    public String list(Model model) {
        LocalDateTime now = LocalDateTime.now();
        // 조회 날짜로 부터 유효한 기간의 쿠폰 조회
        List<Coupon> coupons = couponRepository.findValidCoupons(now);
        model.addAttribute("coupons", couponRepository.findAll());
        return "coupon/coupons";
    }
}
