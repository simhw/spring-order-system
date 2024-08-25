package com.example.shop.myproject.catalog.command.domain.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByDepth(int i);

    @Query("SELECT c FROM Category c WHERE c.parent.id = :id")
    List<Category> findChildrenById(@Param("id") Long id);

    List<Category> findByIdIn(List<Long> categoryIds);
}
