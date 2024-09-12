package com.example.shop.myproject.coupon.ui;

import com.example.shop.myproject.auth.UserDetailsImpl;
import com.example.shop.myproject.coupon.domain.Coupon;
import com.example.shop.myproject.coupon.query.CouponDto;
import com.example.shop.myproject.coupon.query.CouponQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/coupons")
public class CouponController {

    private final CouponQueryService couponQueryService;

    @GetMapping("")
    public String list(Model model) {
        List<Coupon> coupons = couponQueryService.getValidCoupons();
        model.addAttribute("coupons", coupons);
        return "coupon/coupons";
    }

    @GetMapping("/my")
    public String details(@AuthenticationPrincipal UserDetailsImpl user, Model model) {
        List<CouponDto> coupons = couponQueryService.getIssuedCouponsByMember(user.getId());
        model.addAttribute("coupons", coupons);
        return "coupon/my-coupons";
    }
}
