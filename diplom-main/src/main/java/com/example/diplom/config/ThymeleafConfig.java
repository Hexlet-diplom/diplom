package com.example.diplom.config;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * Configuration class for Thymeleaf template engine.
 *
 * <p>If you override {@link #templateEngine()},
 * make sure to configure the template resolver and
 * Spring Security dialect properly.</p>
 */
@Configuration
@NoArgsConstructor
public class ThymeleafConfig {

    /**
     * Creates and configures the {@link SpringTemplateEngine} bean.
     * Enables Spring EL compiler, sets the template resolver,
     * and adds Spring Security dialect.
     *
     * @return configured {@link SpringTemplateEngine} instance
     */
    @Bean
    public SpringTemplateEngine templateEngine() {
        final SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setEnableSpringELCompiler(true);
        engine.setTemplateResolver(templateResolver());
        engine.addDialect(new SpringSecurityDialect());
        return engine;
    }

    /**
     * Creates and configures the {@link SpringResourceTemplateResolver} bean.
     * Sets the prefix, suffix, template mode and character encoding.
     *
     * @return configured {@link SpringResourceTemplateResolver} instance
     */
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        final SpringResourceTemplateResolver resolver =
                new SpringResourceTemplateResolver();
        resolver.setPrefix("classpath:/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }
}
