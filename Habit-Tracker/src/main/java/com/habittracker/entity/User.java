package com.habittracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Users")
public class User extends BaseEntity {

    @Size(max = 100, message = "Username must be less than 100 characters")
    @NotNull
    @Column(name = "username", nullable = false, unique = true, length = 100)
    private String username;

    @Size(max = 500)
    @NotNull
    @Column(name = "password", nullable = false, length = 500)
    private String password;

    @Size(max = 100, message = "Name must be less than 100 characters")
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Size(max = 100, message = "Email must be less than 100 characters")
    @NotNull
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private GameStats gameStats;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AbstractHabit> habits;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserBadge> userBadges;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private CurrentStreak currentStreak;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private LongestStreak longestStreak;
}
