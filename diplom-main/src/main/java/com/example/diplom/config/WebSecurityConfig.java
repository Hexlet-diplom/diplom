package com.example.diplom.config;

import com.example.diplom.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Security configuration for the web application.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * Repository for accessing user data.
     */
    private final UserRepository userRepository;

    /**
     * Logger instance for logging events in WebSecurityConfig.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(WebSecurityConfig.class);

    /**
     * Creates a security configuration with the provided user repository.
     *
     * @param repository user repository used for authentication
     */
    public WebSecurityConfig(final UserRepository repository) {
        this.userRepository = repository;
    }

    /**
     * Configures the HTTP security filter chain.
     *
     * @param http HttpSecurity object to configure
     * @return configured SecurityFilterChain
     */
    @SuppressWarnings("PMD.SignatureDeclareThrowsException")
    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                        .maximumSessions(1)
                        .expiredUrl("/login?expired")
                )
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(
                                "/register",
                                "/login",
                                "/",
                                "/course-selector",
                                "/courses",
                                "/pricing",
                                "/faq",
                                "/contacts",
                                "/about",
                                "/assets/**"
                        ).permitAll()
                        .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated()
                )
                .csrf(Customizer.withDefaults())
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(authenticationSuccessHandler())
                        .failureHandler(authenticationFailureHandler())
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                );

        return http.build();
    }

    /**
     * Handler for successful authentication events.
     *
     * @return AuthenticationSuccessHandler instance
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Login successful: {}", authentication.getName());
            }
            response.sendRedirect("/");
        };
    }

    /**
     * Handler for failed authentication events.
     *
     * @return AuthenticationFailureHandler instance
     */
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return (request, response, exception) -> {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("Login failed: {}", exception.getMessage());
            }
            response.sendRedirect("/login?error=true");
        };
    }

    /**
     * Service to load user details by email.
     *
     * @return UserDetailsService instance
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            final String normalizedEmail = email.toLowerCase(Locale.ROOT);
            final var user = userRepository.findByEmail(normalizedEmail)
                    .orElseThrow(() ->
                            new UsernameNotFoundException("User not found"));
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    user.getRoles().stream()
                            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().name()))
                            .collect(Collectors.toSet())
            );
        };
    }

    /**
     * Provides an AuthenticationManager bean.
     *
     * @param authConfig the authentication configuration
     * @return AuthenticationManager instance
     */
    @SuppressWarnings("PMD.SignatureDeclareThrowsException")
    @Bean
    public AuthenticationManager authenticationManager(final AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * Configures a DaoAuthenticationProvider with user details service
     * and password encoder.
     *
     * @return DaoAuthenticationProvider instance
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final var provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        LOGGER.info("Authentication provider initialized");
        return provider;
    }

    /**
     * Provides BCryptPasswordEncoder bean for password encoding.
     *
     * @return BCryptPasswordEncoder instance
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
