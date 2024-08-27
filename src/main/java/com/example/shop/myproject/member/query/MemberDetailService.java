package com.example.shop.myproject.member.query;

import com.example.shop.myproject.follow.domain.FollowRepository;
import com.example.shop.myproject.member.exception.NoMemberException;
import com.example.shop.myproject.member.domain.Member;
import com.example.shop.myproject.member.domain.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDetailService {

    private final MemberRepository memberRepository;
    private final FollowRepository followRepository;

    public MemberDetail getMemberDetail(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NoMemberException::new);
        Long followerCount = followRepository.countByFollowee(member);
        Long followeeCount = followRepository.countByFollower(member);
        return new MemberDetail(member, followeeCount, followerCount);
    }
}
