package com.example.shop.myproject;


import com.example.shop.myproject.catalog.command.domain.category.Category;
import com.example.shop.myproject.catalog.command.domain.category.CategoryRepository;
import com.example.shop.myproject.catalog.command.domain.product.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class ShopApplication {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    ShopApplication(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

    @PostConstruct
    public void init() {
    }

    public void category() {
        List<Category> categories = new ArrayList<>();

        // dep1
        Category women = new Category("WOMEN", null, 1);
        Category men = new Category("MEN", null, 1);
        Category interior = new Category("INTERIOR", null, 1);

        // dep2
        Category clo1 = new Category("의류", women, 2);
        Category bag1 = new Category("가방", women, 2);
        Category clo2 = new Category("의류", men, 2);
        Category bag2 = new Category("가방", men, 2);
        Category bed = new Category("침구", interior, 2);

        // dep3
        Category top1 = new Category("상의", clo1, 3);
        Category bottom1 = new Category("하의", clo1, 3);
        Category outer1 = new Category("아우터", clo1, 3);
        Category top2 = new Category("상의", clo2, 3);
        Category bottom2 = new Category("하의", clo2, 3);
        Category outer2 = new Category("아우터", clo2, 3);

        categoryRepository.saveAll(List.of(
                women, men, interior,
                clo1, bag1, bag2, clo2, bag2, bed,
                top1, bottom1, outer1, top2, bottom2, outer2
        ));
    }

}
