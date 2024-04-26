package com.todo.backend.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.todo.backend.entities.FormData;
import java.util.*;

@Repository
public interface FormDataRepo extends JpaRepository<FormData, Long>{
	
	Optional<FormData> findById(long caseId);
	
	List<FormData> findByStatus(String status);
}