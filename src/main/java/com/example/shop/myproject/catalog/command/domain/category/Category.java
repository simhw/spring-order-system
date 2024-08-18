package com.example.shop.myproject.catalog.command.domain.category;

import com.example.shop.myproject.catalog.command.domain.product.Product;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Getter
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    private int depth;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", fetch = LAZY)
    private List<Category> children = new ArrayList<>();

    @OneToMany(mappedBy = "category", cascade = ALL)
    private List<Product> products = new ArrayList<>();

    protected Category() {
    }

    public Category(String name, Category parent, int depth) {
        this.name = name;
        this.parent = parent;
        this.depth = depth;
    }
}
