package com.todo.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.todo.backend.entities.FormData;


@Repository
public interface FormDataRepo extends JpaRepository<FormData, Long>{

}