package com.example.shop.myproject.like;

import com.example.shop.myproject.like.domain.Like;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LikeDto {
    private Long id;
    private LocalDateTime createdAt;

    public LikeDto(Like like) {
        this.id = like.getId();
        this.createdAt = like.getCreatedAt();
    }
}
