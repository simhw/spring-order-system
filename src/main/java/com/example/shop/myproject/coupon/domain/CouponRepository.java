package com.example.shop.myproject.coupon.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    @Query("select c from Coupon c JOIN c.histories ch where c.startAt <= :now and c.endAt >= :now")
    List<Coupon> findValidCoupons(LocalDateTime now);

    @Query("SELECT ch FROM Coupon c JOIN c.histories ch WHERE c.id = :couponId")
    List<CouponHistory> findHistoriesByCouponId(Long couponId);

    @Query("SELECT ch FROM Coupon c JOIN c.histories ch WHERE ch.member.id = :memberId")
    List<CouponHistory> findCouponHistoryByMemberId(Long memberId);
}
