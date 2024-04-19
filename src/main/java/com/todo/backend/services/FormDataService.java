package com.todo.backend.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.backend.entities.FormData;
import com.todo.backend.repo.FormDataRepo;

@Service
public class FormDataService {
	
	@Autowired
	private FormDataRepo formDataRepo;
	
	public long createFormData(FormData formData) {
		return formDataRepo.save(formData).getId();
		
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
		updatedTodo.setEmail(formData.getEmail());
		updatedTodo.setFirstname(formData.getFirstname());
		updatedTodo.setLastname(formData.getLastname());
		updatedTodo.setMonthlyincome(formData.getMonthlyincome());
		updatedTodo.setNomineeage(formData.getNomineeage());
		updatedTodo.setNomineeemail(formData.getNomineeemail());
		updatedTodo.setNomineename(formData.getNomineename());
		updatedTodo.setNomineephone(formData.getNomineephone());
		updatedTodo.setPannumber(formData.getPannumber());
		updatedTodo.setPermanentaddressline1(formData.getPermanentaddressline1());
		updatedTodo.setPermanentaddressline2(formData.getPermanentaddressline2());
		updatedTodo.setPermanentcountry(formData.getPermanentcountry());
		updatedTodo.setPermanentpincode(formData.getPermanentpincode());
		updatedTodo.setPermanentstate(formData.getPermanentstate());
		updatedTodo.setPresentaddressline1(formData.getPresentaddressline1());
		updatedTodo.setPresentaddressline2(formData.getPresentaddressline2());
		updatedTodo.setPresentcountry(formData.getPresentcountry());
		updatedTodo.setPresentpincode(formData.getPresentpincode());
		updatedTodo.setPresentstate(formData.getPresentstate());
		updatedTodo.setReference1name(formData.getReference1name());
		updatedTodo.setReference1phone(formData.getReference1phone());
		updatedTodo.setReference2name(formData.getReference2name());
		updatedTodo.setReference2phone(formData.getReference2phone());
		updatedTodo.setRequiredamount(formData.getRequiredamount());
		updatedTodo.setSomail(formData.getSomail());
		updatedTodo.setSoname(formData.getSoname());
		updatedTodo.setSophone(formData.getSophone());
		updatedTodo.setTenure(formData.getTenure());
		updatedTodo.setTotalEMIAmount(formData.getTotalEMIAmount());
		formDataRepo.save(updatedTodo);
		return updatedTodo;
		
	}
}
