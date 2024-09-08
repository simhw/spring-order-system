package com.example.shop.myproject.catalog.command.application;

import com.example.shop.myproject.catalog.command.domain.category.Category;
import com.example.shop.myproject.catalog.command.domain.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void saveCategory(String name, Long parentId) {
        Category category;
        Optional<Category> parent = categoryRepository.findById(parentId);

        if (parent.isPresent()) {
            category = new Category(name, parent.get().getDepth() + 1, parent.get());
        } else {
            category = new Category(name, 1, null);
        }

        categoryRepository.save(category);
    }

    public void getCategory() {
        List<Category> categories = categoryRepository.findAll();
    }
}
