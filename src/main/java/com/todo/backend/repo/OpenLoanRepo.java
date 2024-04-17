package com.todo.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.todo.backend.entities.OpenLoan;

@Repository
public interface OpenLoanRepo extends JpaRepository<OpenLoan, Long>{

}
