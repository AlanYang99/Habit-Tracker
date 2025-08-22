package com.habittracker.repository;

import com.habittracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(final String username);
    Optional<User> findByEmail(final String email);

    // Add a query to fetch the user details, plus a summary of game stats, streak, etc (In the future)
    // Table joining and etc
}
