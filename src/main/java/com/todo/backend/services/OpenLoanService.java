package com.todo.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.backend.entities.OpenLoan;
import com.todo.backend.repo.OpenLoanRepo;

@Service
public class OpenLoanService {
	
	@Autowired
	private OpenLoanRepo openLoanRepo;
	
	public List<OpenLoan> getOpenLoans() {
		return openLoanRepo.findAll();
	}
	
	public OpenLoan getTodo(long id) {
		return openLoanRepo.findById(id).get();
	}
	
	public void deleteTodo(long id){
		openLoanRepo.deleteById(id);
	}
	
	public OpenLoan updateTodo(OpenLoan todo,long id) {
		if(id==-1 || id == 0) {
			openLoanRepo.save(todo);
			return todo;
		}
		OpenLoan updatedTodo = getTodo(id);
		updatedTodo.setEmail(todo.getEmail());
		updatedTodo.setAddress(todo.getAddress());
		updatedTodo.setFirstname(todo.getFirstname());
		updatedTodo.setLastname(todo.getLastname());
		updatedTodo.setPartnerType(todo.getPartnerType());
		updatedTodo.setPhone(todo.getPhone());
		updatedTodo.setRemarks(todo.getRemarks());
		updatedTodo.setStatus(todo.getStatus());
		openLoanRepo.save(updatedTodo);
		return updatedTodo;
		
	}
	
	
}
