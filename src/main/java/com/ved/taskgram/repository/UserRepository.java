package com.ved.taskgram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ved.taskgram.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}


