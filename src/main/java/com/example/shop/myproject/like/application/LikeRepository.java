package com.example.shop.myproject.like.application;

import com.example.shop.myproject.catalog.command.domain.product.Product;
import com.example.shop.myproject.like.domain.Like;
import com.example.shop.myproject.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByMember(Member member);

    Page<Like> findByMember(Member member, Pageable pageable);

    Optional<Like> findByMemberAndProduct(Member member, Product product);

    void deleteByMemberAndProduct(Member member, Product product);
}
