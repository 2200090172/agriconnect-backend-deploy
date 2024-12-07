package com.klef.jfsd.springboot.model;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Date;

@Entity
public class FarmerLoanTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "loan_id", nullable = false)
    private Loan loan;

    @Column(nullable = false)
    private String farmerphone; // Reference to Farmer

    private String financierEmail; // Reference to Financier

    
    @Column(nullable = false)
    private Date applicationDate;

    @Column(nullable = false)
    private String status = "Pending"; // Pending, Approved, Rejected

    // Document fields
    @Lob
    private byte[] document1;

    @Lob
    private byte[] document2;

    @Lob
    private byte[] document3;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    
    public String getFinancierEmail() {
        return financierEmail;
    }

    public void setFinancierEmail(String financierEmail) {
        this.financierEmail = financierEmail;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public byte[] getDocument1() {
        return document1;
    }

    public void setDocument1(byte[] document1) {
        this.document1 = document1;
    }

    public byte[] getDocument2() {
        return document2;
    }

    public void setDocument2(byte[] document2) {
        this.document2 = document2;
    }

    public byte[] getDocument3() {
        return document3;
    }

    public void setDocument3(byte[] document3) {
        this.document3 = document3;
    }

	public String getFarmerphone() {
		return farmerphone;
	}

	public void setFarmerphone(String farmerphone) {
		this.farmerphone = farmerphone;
	}

	@Override
	public String toString() {
		return "FarmerLoanTable [id=" + id + ", loan=" + loan + ", farmerphone=" + farmerphone + ", financierEmail="
				+ financierEmail + ", applicationDate=" + applicationDate + ", status=" + status + ", document1="
				+ Arrays.toString(document1) + ", document2=" + Arrays.toString(document2) + ", document3="
				+ Arrays.toString(document3) + "]";
	}
}
