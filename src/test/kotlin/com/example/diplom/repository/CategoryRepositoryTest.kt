package com.example.diplom.repository

import com.example.diplom.model.Category
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings
import org.assertj.core.api.Assertions.assertThat
import org.flywaydb.core.Flyway
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SuppressFBWarnings(
    value = ["EI_EXPOSE_REP2"],
    justification = "Result is a List-compatible collection and safe to convert",
)
@SpringBootTest
@ActiveProfiles("test")
class CategoryRepositoryTest {
    @Autowired
    lateinit var categoryRepository: CategoryRepository

    @Autowired
    lateinit var flyway: Flyway

    @BeforeEach
    fun setUp() {
        flyway.clean()
        flyway.migrate()
    }

    @Test
    fun `should save and find category by name`() {
        val category = Category(null, "Category1", "Description1", ArrayList())
        categoryRepository.save(category)

        val found = categoryRepository.findByName("Category1")
        assertThat(found).isPresent
        assertThat(found.get().description).isEqualTo("Description1")
    }

    @Test
    fun `should delete category by name`() {
        val category = Category(null, "Category2", "Description2", ArrayList())
        categoryRepository.save(category)

        categoryRepository.deleteByName("Category2")

        val result = categoryRepository.findByName("Category2")
        assertThat(result).isEmpty()
    }
}
