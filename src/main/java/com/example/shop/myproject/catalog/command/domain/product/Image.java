package com.example.shop.myproject.catalog.command.domain.product;

import com.example.shop.myproject.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Entity
@AllArgsConstructor
@Table(name = "product_image")
public class Image extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "image_id")
    private Long id;

    private String url;

    private String filename;

    private String path;

    @Setter
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    protected Image() {
    }
}
