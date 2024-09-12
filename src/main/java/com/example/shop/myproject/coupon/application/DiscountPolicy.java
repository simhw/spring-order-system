package com.example.shop.myproject.coupon.application;

import com.example.shop.myproject.coupon.domain.Coupon;
import com.example.shop.myproject.coupon.domain.DiscountType;


public interface DiscountPolicy {
    public int calculateDiscount(Coupon coupon, int price);

    public boolean isSupport(DiscountType discountType);
}
