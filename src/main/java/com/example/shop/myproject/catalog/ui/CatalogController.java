package com.example.shop.myproject.catalog.ui;

import com.example.shop.myproject.catalog.command.domain.category.CategoryRepository;
import com.example.shop.myproject.catalog.query.category.CategorySummary;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class CatalogController {

    private final CategoryRepository categoryRepository;

    @GetMapping("/api/all/categories")
    public List<CategorySummary> getAll() {
        return categoryRepository.findByDepth(1)
                .stream().map(CategorySummary::new)
                .collect(Collectors.toList());
    }

}
