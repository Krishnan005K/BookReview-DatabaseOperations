package com.krishnan.bookreview.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krishnan.bookreview.model.User;


public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User>findByEmail(String email);
}
