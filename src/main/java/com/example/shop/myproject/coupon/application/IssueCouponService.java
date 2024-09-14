package com.example.shop.myproject.coupon.application;

import com.example.shop.myproject.coupon.domain.Coupon;
import com.example.shop.myproject.coupon.domain.CouponIssued;
import com.example.shop.myproject.coupon.domain.CouponIssuedRepository;
import com.example.shop.myproject.coupon.domain.CouponRepository;
import com.example.shop.myproject.coupon.exception.NoCouponException;
import com.example.shop.myproject.member.domain.Member;
import com.example.shop.myproject.member.domain.MemberRepository;
import com.example.shop.myproject.member.exception.NoMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class IssueCouponService {

    private final CouponRepository couponRepository;
    private final MemberRepository memberRepository;
    private final CouponIssuedRepository couponIssuedRepository;

    @Transactional
    public Long download(Long memberId, Long couponId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NoMemberException::new);

        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(NoCouponException::new);

        CouponIssued issue = new CouponIssued(coupon, member);
        CouponIssued saved = couponIssuedRepository.save(issue);
        return saved.getId();
    }
}
