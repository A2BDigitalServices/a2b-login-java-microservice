package com.todo.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.backend.entities.DeletedMsg;
import com.todo.backend.entities.OpenLoan;
import com.todo.backend.entities.Todo;
import com.todo.backend.repo.OpenLoanRepo;
import com.todo.backend.services.OpenLoanService;

@CrossOrigin(origins = {"https://a2bdigital.000webhostapp.com/","https://a2bdigitalservice.000webhostapp.com/"})
//@CrossOrigin(origins = "http://127.0.0.1:8080/")
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class OpenLoanController {
	
	@Autowired
	private OpenLoanRepo openLoanRepo;
	
	@Autowired
	private OpenLoanService openLoanService;
	
	@PostMapping("/open")
	public long submitOpenLoanApplication(@RequestBody OpenLoan openLoan) {
		return openLoanRepo.save(openLoan).getId();
	}
	
	@GetMapping("/loans")
	public List<OpenLoan> getTodos(){
		return openLoanService.getOpenLoans();
	}
	
	@GetMapping("/loans/{id}")
	public OpenLoan getTodo(@PathVariable long id) {
		return openLoanService.getTodo(id);
	}
	
	@DeleteMapping("/loans/delete/{id}")
	public DeletedMsg deleteTodo(@PathVariable long id) {
		openLoanService.deleteTodo(id);
		return new DeletedMsg("Todo has been deleted");
				
	}
	
	@PutMapping("/loans/update/{id}")
	public OpenLoan updateTodo(@PathVariable long id,@RequestBody OpenLoan todo) {
		return openLoanService.updateTodo(todo, id);
	}
}
