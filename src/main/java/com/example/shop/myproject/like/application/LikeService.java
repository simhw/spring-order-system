package com.example.shop.myproject.like.application;

import com.example.shop.myproject.catalog.command.application.NoProductException;
import com.example.shop.myproject.catalog.command.domain.product.Product;
import com.example.shop.myproject.catalog.command.domain.product.ProductRepository;
import com.example.shop.myproject.catalog.query.product.dto.ProductDto;
import com.example.shop.myproject.like.LikeDto;
import com.example.shop.myproject.like.domain.Like;
import com.example.shop.myproject.member.domain.Member;
import com.example.shop.myproject.member.domain.MemberRepository;
import com.example.shop.myproject.member.exception.NoMemberException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeService {

    private final LikeRepository likeRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    public LikeDto getLike(Long memberId, Long productId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NoMemberException::new);
        Product product = productRepository.findById(productId)
                .orElseThrow(NoProductException::new);
        Optional<Like> like = likeRepository.findByMemberAndProduct(member, product);

        return like.map(LikeDto::new)
                .orElse(null);
    }

    @Transactional
    public Like like(Long memberId, Long productId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NoMemberException::new);
        Product product = productRepository.findById(productId)
                .orElseThrow(NoProductException::new);
        Optional<Like> optLike = likeRepository.findByMemberAndProduct(member, product);

        if (optLike.isPresent()) {
            throw new AlreadyLikedException();
        }

        Like like = new Like(member, product);
        return likeRepository.save(like);
    }

    @Transactional
    public void unlike(Long memberId, Long productId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NoMemberException::new);
        Product product = productRepository.findById(productId)
                .orElseThrow(NoProductException::new);

        likeRepository.deleteByMemberAndProduct(member, product);
    }

    public List<ProductDto> getLikeProduct(Long memberId, Pageable pageable) {
        List<ProductDto> products = new ArrayList<>();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NoMemberException::new);
        Page<Like> likes = likeRepository.findByMember(member, pageable);

        for (Like like : likes) {
            Product product = like.getProduct();
            products.add(new ProductDto(product));
        }

        return products;
    }
}
