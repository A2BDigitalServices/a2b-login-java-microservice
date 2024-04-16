package com.todo.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.backend.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

}
