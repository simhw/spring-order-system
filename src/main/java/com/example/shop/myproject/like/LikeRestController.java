package com.example.shop.myproject.like;

import com.example.shop.myproject.auth.UserDetailsImpl;
import com.example.shop.myproject.catalog.query.product.dto.ProductDto;
import com.example.shop.myproject.common.ApiResponse;
import com.example.shop.myproject.like.application.LikeService;
import com.example.shop.myproject.like.domain.Like;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/like")
public class LikeRestController {

    private final LikeService likeService;

    @RequestMapping("/product/{productId}")
    public ApiResponse<LikeDto> detail(@AuthenticationPrincipal UserDetailsImpl user, @PathVariable Long productId) {
        try {
            LikeDto data = likeService.getLike(user.getId(), productId);
            return new ApiResponse<>(HttpStatus.OK, data);
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, null);
        }
    }

    @GetMapping("/products")
    public ApiResponse<List<ProductDto>> delete(@AuthenticationPrincipal UserDetailsImpl user, Pageable pageable) {
        try {
            List<ProductDto> data = likeService.getLikeProduct(user.getId(), pageable);
            return new ApiResponse<>(HttpStatus.OK, data);
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, null);
        }
    }

    @PostMapping("/product/{productId}")
    public ApiResponse<LikeDto> save(@AuthenticationPrincipal UserDetailsImpl user, @PathVariable Long productId) {
        try {
            Like like = likeService.like(user.getId(), productId);
            return new ApiResponse<>(HttpStatus.OK, new LikeDto(like));
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, null);
        }
    }

    @DeleteMapping("/product/{productId}")
    public ApiResponse<Boolean> delete(@AuthenticationPrincipal UserDetailsImpl user, @PathVariable Long productId) {
        likeService.unlike(user.getId(), productId);
        return new ApiResponse<>(HttpStatus.OK, true);
    }

}
