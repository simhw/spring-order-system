package com.example.shop.myproject.follow.ui;

import com.example.shop.myproject.follow.application.FollowService;
import com.example.shop.myproject.member.command.application.dto.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class FollowController {

    private final FollowService followService;

    @PostMapping("/api/follow/{memberId}")
    public void follow(@PathVariable Long memberId, @SessionAttribute(name = "LOGIN_USER", required = false) UserDetailsImpl user) {
        //followService.follow(user.getId(), memberId);
    }

    @PostMapping("/api/unfollow/{memberId}")
    public void unfollow(@PathVariable Long memberId, @SessionAttribute(name = "LOGIN_USER", required = false) UserDetailsImpl user) {
        //followService.follow(user.getId(), memberId);
    }

}

