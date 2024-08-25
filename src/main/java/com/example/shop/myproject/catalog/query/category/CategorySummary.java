package com.example.shop.myproject.catalog.query.category;

import com.example.shop.myproject.catalog.command.domain.category.Category;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CategorySummary {
    private Long id;
    private String name;
    private List<CategorySummary> children;

    public CategorySummary(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.children = category.getChildren().stream()
                .map(CategorySummary::new)
                .collect(Collectors.toList());
    }
}
