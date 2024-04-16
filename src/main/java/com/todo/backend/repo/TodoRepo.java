package com.todo.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.backend.entities.Todo;

@Repository
public interface TodoRepo extends JpaRepository<Todo, Long>{
	
}
