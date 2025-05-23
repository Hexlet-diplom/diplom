package com.example.diplom.model;

import com.example.diplom.model.audit.Auditable;
import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Entity representing a user in the system.
 * Implements UserDetails for Spring Security integration.
 */
@Data
@EqualsAndHashCode(exclude = {"roles"}, callSuper = false)
@ToString(exclude = {"roles", "password"})
@Entity
@Table(name = "users")
public class User extends Auditable implements UserDetails, Serializable  {

    private static final long serialVersionUID = 1L;

    /**
     * Maximum allowed length for the password.
     */
    @SuppressWarnings("PMD.LongVariable")
    private static final int MAX_PASSWORD_LENGTH = 100;

    /**
     * Unique identifier of the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Email of the user, must be unique and valid.
     */
    @Column(unique = true, nullable = false)
    @NotEmpty
    @Email
    private String email;

    /**
     * Encrypted password of the user.
     */
    @Column(nullable = false, length = MAX_PASSWORD_LENGTH)
    @NotEmpty
    private String password;

    /**
     * Roles assigned to the user.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserCourse> userCourses = new HashSet<>();

    /**
     * Timestamp when the user was created.
     */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Timestamp when the user was last updated.
     */
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    /**
     * Returns the authorities granted to the user.
     * @return collection of granted authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (roles == null) {
            return Collections.emptySet();
        }
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().name()))
                .collect(Collectors.toSet());
    }

    /**
     * Returns the username used to authenticate the user.
     * @return user email
     */
    @Override
    public String getUsername() {
        return email;
    }
}
