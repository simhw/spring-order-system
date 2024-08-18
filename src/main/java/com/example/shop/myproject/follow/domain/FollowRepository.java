package com.example.shop.myproject.follow.domain;

import com.example.shop.myproject.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findAllByFollower(Member follower);

    List<Follow> findAllByFollowee(Member followee);

    Long countByFollower(Member member);

    Long countByFollowee(Member member);

}
