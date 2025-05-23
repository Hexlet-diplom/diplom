package com.example.diplom.service;

import com.example.diplom.exception.CategoryAlreadyExistsException;
import com.example.diplom.exception.CategoryNotFoundException;
import com.example.diplom.model.Category;
import com.example.diplom.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service for managing {@link Category} entities.
 */
@Service
@RequiredArgsConstructor
public class CategoryService {

    /**
     * Repository for category CRUD operations.
     */
    private final CategoryRepository categoryRepository;

    private Category requireByName(String name) {
        return categoryRepository.findByName(name)
            .orElseThrow(() -> new CategoryNotFoundException("with name: " + name));
    }

    private Category requireById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("with id: " + id));
    }

    /**
     * Creates a new category with the given name and description.
     *
     * @param name        category name
     * @param description category description
     * @return created {@link Category}
     * @throws CategoryAlreadyExistsException if category with the same name exists
     */
    @Transactional
    public Category createCategory(final String name, final String description) {
        if (categoryRepository.findByName(name).isPresent()) {
            throw new CategoryAlreadyExistsException(name);
        }

        final Category category = new Category();
        category.setName(name);
        category.setDescription(description);

        return categoryRepository.save(category);
    }

    /**
     * Retrieves a category by its name.
     *
     * @param name category name
     * @return found {@link Category}
     * @throws CategoryNotFoundException if category not found
     */
    public Category getCategoryByName(final String name) {
        return requireByName(name);
    }

    /**
     * Retrieves all categories.
     *
     * @return list of all {@link Category} entities
     */
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Retrieves a category by its ID.
     *
     * @param id category ID
     * @return found {@link Category}
     * @throws CategoryNotFoundException if category not found
     */
    public Category getCategoryById(final Long id) {
        return requireById(id);
    }

    /**
     * Updates an existing category by ID.
     *
     * @param id          ID of the category to update
     * @param name        new name
     * @param description new description
     * @return updated {@link Category}
     * @throws CategoryNotFoundException if category not found or name conflicts
     */
    @Transactional
    public Category updateCategory(final Long id, final String name, final String description) {
        final Category category = requireById(id);

        if (!category.getName().equals(name)) {
            categoryRepository.findByName(name).ifPresent(existing -> {
                if (!existing.getId().equals(id)) {
                    throw new CategoryAlreadyExistsException(name);
                }
            });
        }

        category.setName(name);
        category.setDescription(description);
        return categoryRepository.save(category);
    }

    /**
     * Deletes the category by its name.
     *
     * @param name category name to delete
     * @throws CategoryNotFoundException if category not found
     */
    @Transactional
    public void deleteCategoryByName(final String name) {
        categoryRepository.delete(requireByName(name));
    }

    /**
     * Deletes a category by its ID.
     *
     * @param id ID of the category to delete
     * @throws CategoryNotFoundException if category not found
     */
    @Transactional
    public void deleteCategoryById(final Long id) {
        categoryRepository.delete(requireById(id));
    }
}
