package com.example.shop.myproject.coupon.query;

import com.example.shop.myproject.coupon.domain.Coupon;
import com.example.shop.myproject.coupon.domain.CouponIssue;
import com.example.shop.myproject.coupon.domain.CouponIssueRepository;
import com.example.shop.myproject.coupon.domain.CouponRepository;
import com.example.shop.myproject.member.domain.Member;
import com.example.shop.myproject.member.domain.MemberRepository;
import com.example.shop.myproject.member.exception.NoMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponQueryService {

    private final CouponRepository couponRepository;
    private final CouponIssueRepository couponIssueRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<Coupon> getValidCoupons() {
        LocalDateTime now = LocalDateTime.now();
        return couponRepository.findByStartAtLessThanEqualAndEndAtGreaterThanEqual(now, now);
    }

    @Transactional(readOnly = true)
    public List<CouponDto> getIssuedCouponsByMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NoMemberException::new);

        LocalDateTime now = LocalDateTime.now();
        List<CouponIssue> couponIssues = couponIssueRepository.findValidCouponIssueByMember(member, now);

        return couponIssues.stream()
                .map(CouponDto::new)
                .toList();
    }
}
