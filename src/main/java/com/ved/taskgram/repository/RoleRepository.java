package com.ved.taskgram.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ved.taskgram.entity.Role;
public interface RoleRepository extends JpaRepository<Role,Long> {
}
