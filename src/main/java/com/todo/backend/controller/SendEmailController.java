package com.todo.backend.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.backend.entities.MailRequest;
import com.todo.backend.entities.MailResponse;
import com.todo.backend.services.EmailService;

@RestController
public class SendEmailController {
	
	@Autowired
	private EmailService service;

	@PostMapping("/sendEmail/{caseId}")
	public MailResponse sendEmail(@RequestBody MailRequest request,@PathVariable String caseId) {
		Map<String, Object> model = new HashMap<>();
		model.put("Name", "A2B Digital Service");
		model.put("location", "Tirupathi,Andhrapradesh");
		return service.sendEmail(request, model, caseId);
	}
}
