package com.charitymanagement.api.charitymanagementback.auth.repository;

import com.charitymanagement.api.charitymanagementback.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}