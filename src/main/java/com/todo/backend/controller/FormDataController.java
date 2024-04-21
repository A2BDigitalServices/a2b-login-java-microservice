package com.todo.backend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.todo.backend.entities.DeletedMsg;
import com.todo.backend.entities.FormData;
import com.todo.backend.entities.MailRequest;
import com.todo.backend.services.FormDataService;

@CrossOrigin(origins = {"https://a2bdigital.000webhostapp.com/","https://a2bdigitalservice.000webhostapp.com/"})
//@CrossOrigin
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class FormDataController {
	
	@Autowired
	private FormDataService formDataService;
	
	@Autowired
	private SendEmailController sendEmailController;
	
	@PostMapping("/form")
	public long createTodo(@RequestBody FormData formData) {
		FormData createdCase = formDataService.createFormData(formData);
		long caseId = createdCase.getId();
		String email = createdCase.getEmail();
		asynchronousCallToEmailSend(email,caseId);
		return caseId;
		
	}
	
	@GetMapping("/forms")
	public List<FormData> getTodos(){
		return formDataService.getAllFormData();
	}
	
	@GetMapping("/forms/{id}")
	public FormData getTodo(@PathVariable long id) {
		return formDataService.getFormData(id);
	}
	
	@DeleteMapping("/forms/delete/{id}")
	public DeletedMsg deleteTodo(@PathVariable long id) {
		formDataService.deleteTodo(id);
		return new DeletedMsg("Todo has been deleted");
				
	}
	
	@PutMapping("/forms/update/{id}")
	public FormData updateTodo(@PathVariable long id,@RequestBody FormData todo) {
		return formDataService.updateTodo(todo, id);
	}
	
	@Async
	public void asynchronousCallToEmailSend(String email,long caseId) {
		MailRequest request = new MailRequest();
		request.setTo(email);
		request.setFrom("a2bdigitalservice@gmail.com");
		sendEmailController.sendEmail(request, String.valueOf(caseId));
	}
}
