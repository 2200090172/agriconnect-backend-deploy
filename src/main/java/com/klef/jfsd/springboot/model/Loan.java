package com.klef.jfsd.springboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanid;

    @Column(nullable = false)
    private String loantype;

    @Column(nullable = false)
    private Double interestrate;

    @Column(nullable = false)
    private Double maxamount;

    @Column(nullable = false)
    private Integer repaymentperiod; // In months
    
    private String addedby;
    private String documentsrequired;

   

	public Long getId() {
		return loanid;
	}

	public void setId(Long id) {
		this.loanid = id;
	}

	public String getLoantype() {
		return loantype;
	}

	public void setLoantype(String loantype) {
		this.loantype = loantype;
	}

	public Double getInterestrate() {
		return interestrate;
	}

	public void setInterestrate(Double interestrate) {
		this.interestrate = interestrate;
	}

	public Double getMaxamount() {
		return maxamount;
	}

	public void setMaxamount(Double maxamount) {
		this.maxamount = maxamount;
	}

	public Integer getRepaymentperiod() {
		return repaymentperiod;
	}

	public void setRepaymentperiod(Integer repaymentperiod) {
		this.repaymentperiod = repaymentperiod;
	}

	

	

	public Long getLoanid() {
		return loanid;
	}

	public void setLoanid(Long loanid) {
		this.loanid = loanid;
	}

	public String getDocumentsrequired() {
		return documentsrequired;
	}

	public void setDocumentsrequired(String documentsrequired) {
		this.documentsrequired = documentsrequired;
	}

    // Getters and Setters
}
