package com.todo.backend.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.backend.entities.FormData;
import com.todo.backend.entities.MailRequest;
import com.todo.backend.entities.MailResponse;
import com.todo.backend.repo.FormDataRepo;
import com.todo.backend.services.EmailService;

@CrossOrigin(origins = {"https://a2bdigital.000webhostapp.com/","https://a2bdigitalservice.000webhostapp.com/"})
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SendEmailController {
	
	@Autowired
	private EmailService service;
	
	@Autowired
	private FormDataRepo formDataRepo;

	@PostMapping("/sendEmail/{caseId}")
	public MailResponse sendEmail(@RequestBody MailRequest request,@PathVariable String caseId) {
		Map<String, Object> model = new HashMap<>();
		model.put("Name", "A2B Digital Service");
		model.put("location", "Tirupathi,Andhrapradesh");
		return service.sendEmail(request, model, caseId);
	}
	
	@PostMapping("/sendEmailToThirdParty/{caseId}")
	public MailResponse sendEmailToThirdParty(@RequestBody MailRequest request,@PathVariable String caseId) {
		
		Optional<FormData> optinalFormData = formDataRepo.findById(Long.valueOf(caseId));
		FormData formData = optinalFormData.get();
		Map<String, Object> model = new HashMap<>();
		model.put("caseId", caseId);
		model.put("firstname", formData.getFirstname() != null? formData.getFirstname(): "");
		model.put("lastname", formData.getLastname() != null ? formData.getLastname() : "");
		model.put("email", formData.getEmail() != null ? formData.getEmail(): "");
		model.put("phone", formData.getPhone() != null ? formData.getPhone(): "");
		model.put("aadhar", formData.getAadharnumber() != null ? formData.getAadharnumber(): "");
		model.put("pan", formData.getPannumber() != null ? formData.getPannumber(): "");
		model.put("dob", formData.getDob() != null ? formData.getDob(): "");
		model.put("pname", formData.getSoname() != null ? formData.getSoname(): "");
		model.put("pphone", formData.getSophone() != null ? formData.getSophone(): "");
		model.put("pemail", formData.getSomail() != null ? formData.getSomail(): "");
		model.put("praddressline1", formData.getPresentaddressline1() != null ? formData.getPresentaddressline1(): "");
		model.put("praddressline2", formData.getPresentaddressline2() != null ? formData.getPresentaddressline2(): "");
		model.put("prstate", formData.getPresentstate() != null ? formData.getPresentstate(): "");
		model.put("prcountry", formData.getPresentcountry() != null ? formData.getPresentcountry(): "");
		model.put("prpincode", formData.getPresentpincode() != null ? formData.getPresentpincode(): "");
		model.put("peaddressline1", formData.getPermanentaddressline1() != null ? formData.getPermanentaddressline1(): "");
		model.put("peaddressline2", formData.getPermanentaddressline2() != null ? formData.getPermanentaddressline2(): "");
		model.put("cname", formData.getCompanyname() != null ? formData.getCompanyname(): "");
		model.put("caddressline1", formData.getCompanyaddressline1() != null ? formData.getCompanyaddressline1(): "");
		model.put("caddressline2", formData.getCompanyaddressline2() != null ? formData.getCompanyaddressline2(): "");
		model.put("cstate", formData.getCompanystate() != null ? formData.getCompanystate(): "");
		model.put("ccountry", formData.getCompanycountry() != null? formData.getCompanycountry(): "");
		model.put("cpincode", formData.getCompanypincode() != null? formData.getCompanypincode(): "");
		model.put("cemail", formData.getCompanyemail() != null? formData.getCompanyemail(): "");
		model.put("monthlyincome", formData.getMonthlyincome() != null? formData.getMonthlyincome():"");
		model.put("tenure", formData.getTenure() != null ? formData.getTenure(): "");
		model.put("activeloans", formData.getActiveLoans() != null? formData.getActiveLoans(): "");
		model.put("totalemiamount", formData.getTotalEMIAmount() != null ? formData.getTotalEMIAmount(): "");
		model.put("nname", formData.getNomineename() != null ? formData.getNomineename(): "");
		model.put("nage", formData.getNomineeage() != null ? formData.getNomineeage(): "");
		model.put("nemail", formData.getNomineeemail() != null ? formData.getNomineeemail(): "");
		return service.sendEmailToThirdParty(request, model, caseId);
	}
}
