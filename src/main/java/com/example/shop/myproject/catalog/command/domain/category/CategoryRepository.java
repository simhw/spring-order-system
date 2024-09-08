package com.example.shop.myproject.catalog.command.domain.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByIdIn(List<Long> categoryIds);

    @Query("select Category from Category c where c.parent.id = :id")
    List<Category> findChildrenById(Long id);
}
