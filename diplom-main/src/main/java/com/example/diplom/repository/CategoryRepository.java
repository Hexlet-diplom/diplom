package com.example.diplom.repository;

import com.example.diplom.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Repository interface for Category entity.
 * Provides basic CRUD operations and custom queries.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Finds a category by its exact name.
     *
     * @param name the name of the category
     * @return an Optional containing the category if found, otherwise empty
     */
    Optional<Category> findByName(String name);

    /**
     * Deletes a category by its name.
     *
     * @param name the name of the category to delete
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM Category c WHERE c.name = :name")
    void deleteByName(String name);
}
