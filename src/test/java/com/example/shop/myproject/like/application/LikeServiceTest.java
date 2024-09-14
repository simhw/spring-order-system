package com.example.shop.myproject.like.application;

import com.example.shop.myproject.catalog.command.domain.product.Product;
import com.example.shop.myproject.catalog.command.domain.product.ProductRepository;
import com.example.shop.myproject.like.domain.Like;
import com.example.shop.myproject.member.domain.Member;
import com.example.shop.myproject.member.domain.MemberRepository;

import org.junit.jupiter.api.BeforeEach;
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

    Product product;
    Member member;

    @BeforeEach
    void init() {
        member = Member.builder()
                .id(1L)
                .build();

        product = Product.builder()
                .id(1L)
                .build();
    }

    @Test
    void isLike() {

    }

    @Test
    void 좋아요() {
        // given
        when(memberRepository.findById(member.getId())).thenReturn(Optional.of(member));
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(likeRepository.findByMemberAndProduct(member, product)).thenReturn(Optional.empty());
        when(likeRepository.save(any(Like.class))).thenReturn(new Like(member, product));

        // when
        Like result = likeService.like(member.getId(), product.getId());

        // then
        assertNotNull(result);
        assertEquals(member, result.getMember());
        assertEquals(product, result.getProduct());

        verify(likeRepository).save(any(Like.class));
    }

    @Test
    void 이미_좋아요() {
        // given
        Like like = new Like(member, product);

        when(memberRepository.findById(member.getId())).thenReturn(Optional.of(member));
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(likeRepository.findByMemberAndProduct(member, product)).thenReturn(Optional.of(like));

        // when, then
        assertThrows(AlreadyLikedException.class,
                () -> likeService.like(member.getId(), product.getId()));
    }

    @Test
    void 좋아요_취소() {
        // given
        Like like = new Like(member, product);

        when(memberRepository.findById(member.getId())).thenReturn(Optional.of(member));
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(likeRepository.findByMemberAndProduct(member, product)).thenReturn(Optional.of(like));

        // when
        likeService.unlike(member.getId(), product.getId());
        verify(likeRepository).delete(any(Like.class));

    }

}