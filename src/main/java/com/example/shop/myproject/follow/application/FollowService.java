package com.example.shop.myproject.follow.application;

import com.example.shop.myproject.follow.domain.FollowRepository;
import com.example.shop.myproject.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FollowService {

    private final MemberRepository memberRepository;
    private final FollowRepository followRepository;
    /**

    public List<Member> followers(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(RuntimeException::new);

        return member.getFollowers()
                .stream().map(follow -> follow.getFollower()).collect(Collectors.toList());
    }

    public List<Member> followees(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(RuntimeException::new);

        return member.getFollowees()
                .stream().map(follow -> follow.getFollowee()).collect(Collectors.toList());
    }

    @Transactional
    public String follow(Long followerId, Long followeeId) throws RuntimeException {
        Member follower = memberRepository.findById(followerId).orElseThrow(RuntimeException::new);
        Member followee = memberRepository.findById(followeeId).orElseThrow(RuntimeException::new);

        Follow follow = new Follow(follower, followee);
        followRepository.save(follow);
        return "ok";
    }
    */
}
