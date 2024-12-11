package com.klef.jfsd.springboot.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.klef.jfsd.springboot.model.ExpertResponse;
import com.klef.jfsd.springboot.model.Farmer;
import com.klef.jfsd.springboot.model.FarmerLoanTable;
import com.klef.jfsd.springboot.model.Loan;
import com.klef.jfsd.springboot.model.LoanApplicationRequest;
import com.klef.jfsd.springboot.repository.FarmerLoanTableRepository;
import com.klef.jfsd.springboot.repository.LoanRepository;
import com.klef.jfsd.springboot.service.FarmerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@RestController
public class FarmerController
{
	@Autowired
	private FarmerService farmerService;
	
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private FarmerLoanTableRepository farmerLoanTableRepository;
	
	@GetMapping("farmerlogin")
	public String farmerlogin(@RequestParam("fcontact") String fcontact, @RequestParam("fpwd") String fpwd, HttpServletRequest request)
	{
//		System.out.println(fcontact+" "+fpwd);
		Farmer farmer=farmerService.farmerlogin(fcontact, fpwd);
		if(farmer!=null)
		{
			HttpSession session=request.getSession();
			session.setAttribute("farmer", farmer);

			
			session.setMaxInactiveInterval(300);
			return "Login Success";
		}
		else
		{
			

			return "Login Fail";
		}	
	}
	
	@GetMapping("checkfarmersession")
	public int checkFarmerSession(HttpServletRequest request) {
		System.out.println("Farmer session : "+request.getRequestId());
	    HttpSession session = request.getSession(false); // Get existing session if available, or return null
	    if (session != null) {
		    System.out.println("Farmer session is active : "+session.getId());
	        return 1; // Session is active
	    } else {
		    System.out.println("Farmer session is InACtive : "+session.getId());
	        return 0; // Session is inactive or expired
	    }
	}
	
	@GetMapping("getfarmerprofile")
	public Farmer getfarmerprofile(HttpServletRequest request) {
	    HttpSession session = request.getSession(false); // Get existing session without creating a new one
	    if (session != null) {
	        Farmer farmer = (Farmer) session.getAttribute("farmer");
	        if (farmer != null) {
	            return farmer;
	        } else {
	            throw new RuntimeException("No farmer profile found in session.");
	        }
	    } else {
	        throw new RuntimeException("Session not found. Please log in.");
	    }
	}
	
	@GetMapping("getfarmerresponses")
	public List<ExpertResponse> getfarmResponses(@RequestParam("phone") String phone)
	{
		return farmerService.getexpertresponses(phone);
	}
	
	@GetMapping("/farmerlogout")
	public int farmerlogout(HttpServletRequest request) {
	    HttpSession session = request.getSession();
	    session.removeAttribute("farmer");
	    System.out.println("Farmer Session Removed!!");
	    return 1;
	}

	
	
//	@PostMapping
//	public int applyLoan(
//	        @RequestParam("loanID") Long loanID,
//	        @RequestParam("applicantName") String applicantName,
//	        @RequestParam("farmeremail") String farmerEmail,
//	        @RequestParam("contactNumber") String contactNumber,
//	        @RequestParam("images[]") List<MultipartFile> images) {
//	    try {
//	        // Log query parameters
//	        System.out.println("Loan ID: " + loanID);
//	        System.out.println("Applicant Name: " + applicantName);
//	        System.out.println("Farmer Email: " + farmerEmail);
//	        System.out.println("Contact Number: " + contactNumber);
//
//	        // Process uploaded images
//	        for (MultipartFile file : images) {
//	            System.out.println("Received file: " + file.getOriginalFilename() +
//	                    ", Size: " + file.getSize() + " bytes");
//	        }
//
//	        // Create new FarmerLoanTable instance
//	        FarmerLoanTable farmerLoanTable = new FarmerLoanTable();
//
//	        // Retrieve the loan object using loanID
//	        Loan loan = loanRepository.findById(loanID).get();
//	        String financierEmail = loanRepository.findfinancieremail(loanID);
//
//	        // Set the properties of FarmerLoanTable
//	        farmerLoanTable.setApplicationDate(new Date());
//	        farmerLoanTable.setFarmerphone(contactNumber);
//	        farmerLoanTable.setLoan(loan);
//	        farmerLoanTable.setFinancierEmail(financierEmail);
//
//	        // Set the document fields (images)
//	        if (images.size() > 0) {
//	            farmerLoanTable.setDocument1(images.get(0).getBytes());
//	        }
//	        if (images.size() > 1) {
//	            farmerLoanTable.setDocument2(images.get(1).getBytes());
//	        }
//	        if (images.size() > 2) {
//	            farmerLoanTable.setDocument3(images.get(2).getBytes());
//	        }
//
//	        // Save the FarmerLoanTable instance
//	        farmerLoanTableRepository.save(farmerLoanTable);
//
//	        return 1; // Success
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        // Return failure
//	        return 0;
//	    }
//	}

	
	@PostMapping("/applyloan")
	public int applyLoan(
	    @RequestParam(value = "loanId") long loanId,
	    @RequestParam String farmerphone,
	    @RequestParam(value = "document1", required = false) MultipartFile document1,
	    @RequestParam(value = "document2", required = false) MultipartFile document2,
	    @RequestParam(value = "document3", required = false) MultipartFile document3
	) {
	    try {
	        // Log query parameters
	        System.out.println("Loan ID: " + loanId);
	        System.out.println("Farmer Phone: " + farmerphone);

	        // Process uploaded images
	        if (document1 != null) {
	            System.out.println("Received file: " + document1.getOriginalFilename());
	        }
	        if (document2 != null) {
	            System.out.println("Received file: " + document2.getOriginalFilename());
	        }
	        if (document3 != null) {
	            System.out.println("Received file: " + document3.getOriginalFilename());
	        }

	        // Create new FarmerLoanTable instance
	        FarmerLoanTable farmerLoanTable = new FarmerLoanTable();

	        // Retrieve the loan object using loanId
	        Loan loan = loanRepository.findById(loanId).orElse(null);
	        if (loan == null) {
	            // Handle the case where loan is not found
	            System.out.println("Loan not found for ID: " + loanId);
	            return 0; // Failure
	        }
	        String financierEmail = loanRepository.findfinancieremail(loanId);

	        // Set the properties of FarmerLoanTable
	        farmerLoanTable.setApplicationDate(new Date());
	        farmerLoanTable.setFarmerphone(farmerphone);
	        farmerLoanTable.setLoan(loan);
	        farmerLoanTable.setFinancierEmail(financierEmail);

	        // Set the document fields (files as byte arrays)
	        if (document1 != null) {
	            farmerLoanTable.setDocument1(document1.getBytes());
	        }
	        if (document2 != null) {
	            farmerLoanTable.setDocument2(document2.getBytes());
	        }
	        if (document3 != null) {
	            farmerLoanTable.setDocument3(document3.getBytes());
	        }

	        // Save the FarmerLoanTable instance
//	        System.out.println(farmerLoanTable);
	        // Uncomment this when you are ready to save to the database
	         farmerLoanTableRepository.save(farmerLoanTable);

	        return 1; // Success
	    } catch (Exception e) {
	        e.printStackTrace();
	        return 0; // Failure
	    }
	}
}

	

