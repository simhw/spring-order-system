package com.example.shop.myproject.coupon.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    List<Coupon> findByStartAtLessThanEqualAndEndAtGreaterThanEqual(LocalDateTime startAt, LocalDateTime endAt);
}
