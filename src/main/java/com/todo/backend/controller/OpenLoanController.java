package com.todo.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.backend.entities.OpenLoan;
import com.todo.backend.repo.OpenLoanRepo;

@CrossOrigin(origins = "https://a2bdigitalservice.000webhostapp.com/")
//@CrossOrigin(origins = "http://127.0.0.1:8080/")
@RestController
public class OpenLoanController {
	
	@Autowired
	private OpenLoanRepo openLoanRepo;
	
	@PostMapping("/open")
	public long submitOpenLoanApplication(@RequestBody OpenLoan openLoan) {
		return openLoanRepo.save(openLoan).getId();
	}
}
