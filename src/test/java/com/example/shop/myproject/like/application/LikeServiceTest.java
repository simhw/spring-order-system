package com.example.shop.myproject.like.application;

import com.example.shop.myproject.catalog.command.domain.product.Product;
import com.example.shop.myproject.catalog.command.domain.product.ProductRepository;
import com.example.shop.myproject.like.domain.Like;
import com.example.shop.myproject.member.domain.Member;
import com.example.shop.myproject.member.domain.MemberRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LikeServiceTest {

    @Mock
    private LikeRepository likeRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private MemberRepository memberRepository;
    @InjectMocks
    private LikeService likeService;

    @Test
    void isLike() {

    }

    @Test
    void 좋아요() {
        // given
        Long memberId = 1L;
        Long productId = 1L;

        Member member = new Member(memberId, "user1");
        Product product = new Product(productId, "product1");

        when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(likeRepository.findByMemberAndProduct(member, product)).thenReturn(Optional.empty());

        when(likeRepository.save(any(Like.class))).thenReturn(new Like(member, product));

        // when
        Like result = likeService.like(memberId, productId);

        // then
        assertNotNull(result);
        assertEquals(member, result.getMember());
        assertEquals(product, result.getProduct());

        verify(likeRepository).save(any(Like.class));
    }

    @Test
    void 이미_좋아요() {
        // given
        Long memberId = 1L;
        Long productId = 1L;

        Member member = new Member(memberId, "user1");
        Product product = new Product(productId, "product1");
        Like like = new Like(member, product);

        when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(likeRepository.findByMemberAndProduct(member, product)).thenReturn(Optional.of(like));

        // when, then
        assertThrows(AlreadyLikedException.class,
                () -> likeService.like(memberId, productId));
    }

    @Test
    void 좋아요_취소() {
        // given
        Long memberId = 1L;
        Long productId = 1L;

        Member member = new Member(memberId, "user1");
        Product product = new Product(productId, "product1");
        Like like = new Like(member, product);

        when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(likeRepository.findByMemberAndProduct(member, product)).thenReturn(Optional.of(like));

        // when
        likeService.unlike(memberId, productId);
        verify(likeRepository).delete(any(Like.class));

    }

    @Test
    void getLikeProduct() {
    }
}