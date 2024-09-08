package com.example.shop.myproject.catalog.query.category;

import com.example.shop.myproject.catalog.command.domain.category.Category;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDto {
    private Long id;
    private String name;
    List<CategoryDto> children = new ArrayList<>();

    public CategoryDto() {
    }

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        setChildren(category.getChildren());
    }

    public CategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private void setChildren(List<Category> children) {
        children.forEach(category -> {
            CategoryDto child = new CategoryDto(category.getId(), category.getName());
            this.children.add(child);
        });
    }
}
