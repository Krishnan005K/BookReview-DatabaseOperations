package com.krishnan.bookreview.repository;

import com.krishnan.bookreview.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    
}
