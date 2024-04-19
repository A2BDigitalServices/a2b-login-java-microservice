package com.todo.backend.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="form_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String firstname;
	private String lastname;
	private String email;
	private String aadharnumber;
	private String dob;
	private String pannumber;
	private String soname;
	private String sophone;
	private String somail;
	private String presentaddressline1;
	private String presentaddressline2;
	private String presentstate;
	private String presentcountry;
	private String presentpincode;
	private String permanentaddressline1;
	private String permanentaddressline2;
	private String permanentstate;
	private String permanentcountry;
	private String permanentpincode;
	private String companyname;
	private String companyaddressline1;
	private String companyaddressline2;
	private String companystate;
	private String companycountry;
	private String companypincode;
	private String companyemail;
	private String monthlyincome;
	private String requiredamount;
	private String tenure;
	private String activeLoans;
	private String totalEMIAmount;
	private String reference1name;
	private String reference1phone;
	private String reference2name;
	private String reference2phone;
	private String nomineename;
	private String nomineeage;
	private String nomineephone;
	private String nomineeemail;
}