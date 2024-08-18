package com.example.shop.myproject.follow.query;

import lombok.Data;

@Data
public class FollowSummary {
    Long followerCount;
    Long followeeCount;
    boolean isFollowing;
}
