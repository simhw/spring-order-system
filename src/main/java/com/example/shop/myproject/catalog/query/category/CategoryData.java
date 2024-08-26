package com.example.shop.myproject.catalog.query.category;

import com.example.shop.myproject.catalog.command.domain.category.Category;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryData {
    private Long id;

    private String name;

    List<CategoryData> children = new ArrayList<>();

    public CategoryData() {
    }

    public CategoryData(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.children = category.getChildren().stream()
                .map(CategoryData::new).toList();
    }
}
