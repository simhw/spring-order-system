package com.example.shop.myproject.catalog.query;

import com.example.shop.myproject.catalog.command.domain.category.Category;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class SimpleCategory {
    private Long id;
    private String name;
    private List<SimpleCategory> children;

    public SimpleCategory(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.children = category.getChildren().stream()
                .map(SimpleCategory::new)
                .collect(Collectors.toList());
    }
}
