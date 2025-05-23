package com.example.diplom.repository

import com.example.diplom.model.enums.RoleName
import org.assertj.core.api.Assertions.assertThat
import org.flywaydb.core.Flyway
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class RoleRepositoryTest {
    @Autowired
    private lateinit var roleRepository: RoleRepository

    @Autowired
    lateinit var flyway: Flyway

    @BeforeEach
    fun setUp() {
        flyway.clean()
        flyway.migrate()
    }

    @Test
    fun `should find predefined role by name`() {
        val foundRole = roleRepository.findByName(RoleName.ADMIN)
        assertThat(foundRole).isPresent
        assertThat(foundRole.get().name).isEqualTo(RoleName.ADMIN)
    }

    @Test
    fun `should find all predefined roles`() {
        val roles = roleRepository.findAll()
        assertThat(roles).isNotEmpty
        assertThat(roles.map { it.name }).contains(RoleName.ADMIN, RoleName.USER) // перечисли нужные роли
    }
}
