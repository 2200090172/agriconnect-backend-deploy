package com.klef.jfsd.springboot.model;
import org.springframework.web.multipart.MultipartFile;

public class LoanApplicationRequest {

    private Long loanID;
    private String applicantName;
    private String farmerEmail;
    private String contactNumber;
    private MultipartFile document1;
    private MultipartFile document2;
    private MultipartFile document3;

    // Getters and Setters

    public Long getLoanID() {
        return loanID;
    }

    public void setLoanID(Long loanID) {
        this.loanID = loanID;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getFarmerEmail() {
        return farmerEmail;
    }

    public void setFarmerEmail(String farmerEmail) {
        this.farmerEmail = farmerEmail;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public MultipartFile getDocument1() {
        return document1;
    }

    public void setDocument1(MultipartFile document1) {
        this.document1 = document1;
    }

    public MultipartFile getDocument2() {
        return document2;
    }

    public void setDocument2(MultipartFile document2) {
        this.document2 = document2;
    }

    public MultipartFile getDocument3() {
        return document3;
    }

    public void setDocument3(MultipartFile document3) {
        this.document3 = document3;
    }
}
