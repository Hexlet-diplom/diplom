package com.example.diplom.config;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Configuration class for JPA-related settings.
 * Used to customize or extend default JPA behavior.
 */
@Configuration
@EnableJpaAuditing
@NoArgsConstructor
public class JpaConfig { }
