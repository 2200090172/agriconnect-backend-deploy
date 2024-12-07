package com.klef.jfsd.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.model.FarmerLoanTable;
import com.klef.jfsd.springboot.model.Financier;
import com.klef.jfsd.springboot.model.Loan;
import com.klef.jfsd.springboot.repository.FarmerLoanTableRepository;
import com.klef.jfsd.springboot.repository.FinancierRepository;
import com.klef.jfsd.springboot.repository.LoanRepository;


@Service
public class FinancierServiceImpl implements FinancierService{

	@Autowired
	private FinancierRepository financierRepository;
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Override
	public int addfinancier(Financier financier) {
		financierRepository.save(financier);
		return 1;
	}

	@Override
	public Financier financierlogin(String email, String password) {
		Financier financier=financierRepository.financierlogin(email, password);
		
		return financier;
	}

	
	
	@Override
	public int addloan(Loan loan) {
		loanRepository.save(loan);
		return 1;
	}

	@Override
	public List<Loan> viewallloans() {
		return (List<Loan>) loanRepository.findAll();
	}

	@Override
	public int updatepassword(String email, String password) {
		financierRepository.updatePasswordByEmail(email, password);
		return 1;
	}
	
	
	@Autowired
    private FarmerLoanTableRepository farmerLoanTableRepository;

    @Override
    public List<FarmerLoanTable> getAllLoanRequests() {
        return (List<FarmerLoanTable>) farmerLoanTableRepository.findAll();
    }

    @Override
    public int updateLoanStatus(Long id, String status) {
        FarmerLoanTable loanRequest = farmerLoanTableRepository.findById(id).orElse(null);
        if (loanRequest != null) {
            loanRequest.setStatus(status);
            farmerLoanTableRepository.save(loanRequest);
            return 1; // Return 1 for success
        }
        return 0; // Return 0 for failure
    }
}
