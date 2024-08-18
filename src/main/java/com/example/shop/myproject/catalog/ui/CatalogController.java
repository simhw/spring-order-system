package com.example.shop.myproject.catalog.ui;

import com.example.shop.myproject.catalog.command.domain.category.CategoryRepository;
import com.example.shop.myproject.catalog.query.SimpleCategory;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class CatalogController {

    private final CategoryRepository categoryRepository;

    @GetMapping("/api/categories")
    public List<SimpleCategory> getAll() {
        return categoryRepository.findByDepth(1)
                .stream().map(SimpleCategory::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/group/{group}")
    public List<SimpleCategory> getAll(@PathVariable int group) {
        return categoryRepository.findByDepth(group)
                .stream().map(SimpleCategory::new)
                .collect(Collectors.toList());
    }
}
