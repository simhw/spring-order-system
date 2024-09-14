package com.example.shop.myproject.coupon.domain;

import com.example.shop.myproject.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface CouponIssuedRepository extends JpaRepository<CouponIssued, Long> {
    @Query("select ci from CouponIssued ci join ci.coupon c " +
            "where ci.member = :member " +
            "and c.startAt <= :now and c.endAt >= :now")
    List<CouponIssued> findValidCouponIssuedByMember(Member member, LocalDateTime now);
}
