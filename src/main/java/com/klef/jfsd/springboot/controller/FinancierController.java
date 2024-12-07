package com.klef.jfsd.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klef.jfsd.springboot.model.FarmerLoanTable;
import com.klef.jfsd.springboot.model.Financier;
import com.klef.jfsd.springboot.model.Loan;
import com.klef.jfsd.springboot.service.FinancierService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@RestController
public class FinancierController {
	
	@Autowired
	private FinancierService financierService;
	
	@GetMapping("/financierlogin")
	public int financierlogin(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletRequest request)
	{
		Financier financier=financierService.financierlogin(email, password);
		if(financier!=null)
		{
			HttpSession session=request.getSession();
			session.setAttribute("financier", financier);
			return 1;
		}
		return 0;
	}
	
	@GetMapping("checkfinanciersession")
	public int checkfinanciersession(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		if(session!=null  && session.getAttribute("financier")!=null)
			return 1;
		return 0;
	}
	
	@PostMapping("addloan")
	public int addloan(@RequestBody Loan loan)
	{
		return financierService.addloan(loan);
	}
	
	
	@GetMapping("/viewallloans")
	public List<Loan> viewallloans()
	{
		return financierService.viewallloans();
	}
	
	
	
	 @GetMapping("/financier/loanrequests")
	    public List<FarmerLoanTable> getAllLoanRequests() {
	        return financierService.getAllLoanRequests();
	    }

	    @PostMapping("/financier/loanrequests/{id}/accept")
	    public int acceptLoanRequest(@PathVariable Long id) {
	        return financierService.updateLoanStatus(id, "Approved");
	    }

	    @PostMapping("/financier/loanrequests/{id}/reject")
	    public int rejectLoanRequest(@PathVariable Long id) {
	        return financierService.updateLoanStatus(id, "Rejected");
	    }
}
