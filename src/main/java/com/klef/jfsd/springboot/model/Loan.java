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
    private Long id;

    @Column(nullable = false)
    private String loantype;

    @Column(nullable = false)
    private Double interestrate;

    @Column(nullable = false)
    private Double maxamount;

    @Column(nullable = false)
    private Integer repaymentperiod; // In months

    // Relationships
    @ManyToOne
    @JoinColumn(name = "financier_id")
    private Financier financier;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Financier getFinancier() {
		return financier;
	}

	public void setFinancier(Financier financier) {
		this.financier = financier;
	}

    // Getters and Setters
}
