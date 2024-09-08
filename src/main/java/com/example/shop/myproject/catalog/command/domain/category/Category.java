package com.example.shop.myproject.catalog.command.domain.category;

import com.example.shop.myproject.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Entity
@Table(name = "category")
public class Category extends BaseEntity {
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


    protected Category() {
    }

    public Category(String name, int depth, Category parent) {
        this.name = name;
        this.depth = depth;
        this.parent = parent;
    }
}
