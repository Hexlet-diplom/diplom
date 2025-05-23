package com.example.diplom.service

import com.example.diplom.exception.CategoryAlreadyExistsException
import com.example.diplom.exception.CategoryNotFoundException
import org.flywaydb.core.Flyway
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class CategoryServiceTest {
    @Autowired
    private lateinit var categoryService: CategoryService

    @Autowired
    private lateinit var flyway: Flyway

    @BeforeEach
    fun setUp() {
        flyway.clean()
        flyway.migrate()
    }

    @Test
    fun `createCategory should save and return new category`() {
        val result = categoryService.createCategory("CategoryA", "Description A")

        assertEquals("CategoryA", result.name)
        assertEquals("Description A", result.description)
    }

    @Test
    fun `createCategory should throw if category exists`() {
        categoryService.createCategory("CategoryA", "Initial")

        assertThrows(CategoryAlreadyExistsException::class.java) {
            categoryService.createCategory("CategoryA", "Duplicate")
        }
    }

    @Test
    fun `getCategoryByName should return category`() {
        categoryService.createCategory("CategoryA", "Some description")
        val result = categoryService.getCategoryByName("CategoryA")

        assertEquals("CategoryA", result.name)
    }

    @Test
    fun `getCategoryByName should throw if not found`() {
        assertThrows(CategoryNotFoundException::class.java) {
            categoryService.getCategoryByName("NonExisting")
        }
    }

    @Test
    fun `getAllCategories should return all categories`() {
        categoryService.createCategory("Category1", "Description 1")
        categoryService.createCategory("Category2", "Description 2")

        val result = categoryService.getAllCategories()
        assertEquals(2, result.size)
    }

    @Test
    fun `getCategoryById should return category`() {
        val created = categoryService.createCategory("SampleCategory", "Test")
        val found = categoryService.getCategoryById(created.id)

        assertEquals(created.id, found.id)
    }

    @Test
    fun `getCategoryById should throw if not found`() {
        assertThrows(CategoryNotFoundException::class.java) {
            categoryService.getCategoryById(999L)
        }
    }

    @Test
    fun `updateCategory should update name and description`() {
        val created = categoryService.createCategory("InitialName", "InitialDesc")
        val updated = categoryService.updateCategory(created.id, "UpdatedName", "UpdatedDesc")

        assertEquals("UpdatedName", updated.name)
        assertEquals("UpdatedDesc", updated.description)
    }

    @Test
    fun `updateCategory should throw if new name belongs to another category`() {
        val cat1 = categoryService.createCategory("FirstCategory", "desc1")
        categoryService.createCategory("ConflictingCategory", "desc2")

        assertThrows(CategoryAlreadyExistsException::class.java) {
            categoryService.updateCategory(cat1.id, "ConflictingCategory", "New desc")
        }
    }

    @Test
    fun `deleteCategoryByName should delete if exists`() {
        categoryService.createCategory("ToBeDeleted", "desc")
        categoryService.deleteCategoryByName("ToBeDeleted")

        assertThrows(CategoryNotFoundException::class.java) {
            categoryService.getCategoryByName("ToBeDeleted")
        }
    }

    @Test
    fun `deleteCategoryById should delete if exists`() {
        val created = categoryService.createCategory("ToDeleteById", "desc")
        categoryService.deleteCategoryById(created.id)

        assertThrows(CategoryNotFoundException::class.java) {
            categoryService.getCategoryById(created.id)
        }
    }
}
