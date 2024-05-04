package com.todo.backend.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.todo.backend.entities.FormData;
import com.todo.backend.repo.FormDataRepo;

@Service
public class FormDataService {
	
	@Autowired
	private FormDataRepo formDataRepo;
	
	public FormData createFormData(FormData formData, String a2bemployeeemail) {
		formData.setId(generateUniqueFiveDigitNumber());
		formData.setStatus("Applied");
		formData.setDate(LocalDate.now());	
		formData.setWhoapplied(a2bemployeeemail);
		return formDataRepo.save(formData);
		
	}
	
	public List<FormData> getAllFormData() {
		return formDataRepo.findAll();
	}
	
	public FormData getFormData(long id) {
		return formDataRepo.findById(id).get();
	}
	
	public void deleteTodo(long id){
		formDataRepo.deleteById(id);
	}
	
	public FormData updateTodo(FormData formData,long id) {
		if(id==-1 || id == 0) {
			formDataRepo.save(formData);
			return formData;
		}
		FormData updatedTodo = getFormData(id);
		updatedTodo.setAadharnumber(formData.getAadharnumber());
		updatedTodo.setActiveLoans(formData.getActiveLoans());
		updatedTodo.setCompanyaddressline1(formData.getCompanyaddressline1());
		updatedTodo.setCompanyaddressline2(formData.getCompanyaddressline2());
		updatedTodo.setCompanycountry(formData.getCompanycountry());
		updatedTodo.setCompanyemail(formData.getCompanyemail());
		updatedTodo.setCompanyname(formData.getCompanyname());
		updatedTodo.setCompanypincode(formData.getCompanypincode());
		updatedTodo.setCompanystate(formData.getCompanystate());
		updatedTodo.setDob(formData.getDob());
		updatedTodo.setPhone(formData.getPhone());
		updatedTodo.setEmail(formData.getEmail());
		updatedTodo.setFirstname(formData.getFirstname());
		updatedTodo.setLastname(formData.getLastname());
		updatedTodo.setMonthlymode(formData.getMonthlymode());
		updatedTodo.setMonthlyincome(formData.getMonthlyincome());
		updatedTodo.setNomineedob(formData.getNomineedob());
		updatedTodo.setNomineeemail(formData.getNomineeemail());
		updatedTodo.setNomineename(formData.getNomineename());
		updatedTodo.setNomineephone(formData.getNomineephone());
		updatedTodo.setPannumber(formData.getPannumber());
		updatedTodo.setPermanentaddressline1(formData.getPermanentaddressline1());
		updatedTodo.setPermanentaddressline2(formData.getPermanentaddressline2());
		updatedTodo.setPermanentlandmark(formData.getPermanentlandmark());
		updatedTodo.setPermanentlandmark(formData.getPermanentlandmark());
		updatedTodo.setPermanentcountry(formData.getPermanentcountry());
		updatedTodo.setPermanentpincode(formData.getPermanentpincode());
		updatedTodo.setPermanentpostaloffice(formData.getPermanentpostaloffice());
		updatedTodo.setPermanentstate(formData.getPermanentstate());
		updatedTodo.setPresentaddressline1(formData.getPresentaddressline1());
		updatedTodo.setPresentaddressline2(formData.getPresentaddressline2());
		updatedTodo.setPresentlandmark(formData.getPresentlandmark());
		updatedTodo.setPresentcountry(formData.getPresentcountry());
		updatedTodo.setPresentpincode(formData.getPresentpincode());
		updatedTodo.setPresentpostaloffice(formData.getPresentpostaloffice());
		updatedTodo.setPresentstate(formData.getPresentstate());
		updatedTodo.setReference1name(formData.getReference1name());
		updatedTodo.setReference1phone(formData.getReference1phone());
		updatedTodo.setReferencerelationship1(formData.getReferencerelationship1());
		updatedTodo.setReference2name(formData.getReference2name());
		updatedTodo.setReference2phone(formData.getReference2phone());
		updatedTodo.setRequiredamount(formData.getRequiredamount());
		updatedTodo.setFname(formData.getFname());
		updatedTodo.setMname(formData.getMname());
		updatedTodo.setSname(formData.getSname());
		updatedTodo.setTenure(formData.getTenure());
		updatedTodo.setTotalEMIAmount(formData.getTotalEMIAmount());
		updatedTodo.setStatus(formData.getStatus());	
		updatedTodo.setLoantype(formData.getLoantype());
		updatedTodo.setRelationship(formData.getRelationship());
		updatedTodo.setWhoapplied(formData.getWhoapplied());
		formDataRepo.save(updatedTodo);
		return updatedTodo;
		
	}
	
	private long generateUniqueFiveDigitNumber() {
	    // Generate a random 5-digit number between 10000 and 99999
	    Random random = new Random();
	    long uniqueNumber = random.nextInt(90000) + 10000;

	    // Ensure the generated number is unique in the database
	    while (formDataRepo.existsById(uniqueNumber)) {
	        uniqueNumber = random.nextInt(90000) + 10000;
	    }

	    return uniqueNumber;
	}
	
	public long countOfCases(String status){
		List<FormData> list = formDataRepo.findByStatus(status);
		return list.size();
	}
	
	public List<FormData> getAllFormDataWithPagination(String offSet, String pageSize) {
		Pageable pageable = PageRequest.of(Integer.valueOf(offSet)-1, Integer.valueOf(pageSize));
		Page<FormData> response= formDataRepo.findAll(pageable);
		List<FormData> contentList = response.getContent();
		return contentList;
	}
}
