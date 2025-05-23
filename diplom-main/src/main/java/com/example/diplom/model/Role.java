package com.example.diplom.model;

import com.example.diplom.model.enums.RoleName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Entity representing a user role in the system.
 */
@Data
@EqualsAndHashCode(of = "name")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Unique identifier for the user role.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the user role. Must be unique and not null.
     */
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(unique = true, nullable = false, length = 50)
    private RoleName name;
}
