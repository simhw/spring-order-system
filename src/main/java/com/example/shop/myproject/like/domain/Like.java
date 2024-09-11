package com.example.shop.myproject.like.domain;

import com.example.shop.myproject.catalog.command.domain.product.Product;
import com.example.shop.myproject.common.domain.BaseEntity;
import com.example.shop.myproject.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "likes")
public class Like extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "product_like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    protected Like() {
    }

    public Like(Member member, Product product) {
        this.member = member;
        this.product = product;
    }
}
