package com.example.shop.myproject.catalog.command.domain.product;

import com.example.shop.myproject.common.domain.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "product_image")
public class Image extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String url;

    private String filename;

    private String path;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
