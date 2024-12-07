package com.klef.jfsd.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.klef.jfsd.springboot.model.FarmerLoanTable;
import com.klef.jfsd.springboot.model.Financier;
import com.klef.jfsd.springboot.model.Loan;


public interface FinancierService 
{
	public int addfinancier(Financier financier);
	public Financier financierlogin(String email, String password);
	public int addloan(@RequestBody Loan loan);
	public List<Loan> viewallloans();
	public int updatepassword(String email, String password);
	
    List<FarmerLoanTable> getAllLoanRequests();
    int updateLoanStatus(Long id, String status);

}
