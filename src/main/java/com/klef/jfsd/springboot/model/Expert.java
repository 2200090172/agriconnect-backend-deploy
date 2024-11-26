package com.klef.jfsd.springboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "expert_table")
public class Expert {

    
    @Column(name = "expert_phone", nullable = false, length = 20)
    private String phone;
    
  
    @Column(name = "expert_fullname", nullable = false, length = 50)
    private String fullname;

    @Id
    @Column(name = "expert_email", nullable = false, length = 50, unique = true)
    private String email;
    
    
    @Column(name = "expert_password", nullable = false, length =30)
    public String password;

    @Column(name = "expert_experience", length = 20)
    private String experience;

    @Column(name = "expert_fieldofexpertise", length = 100)
    private String fieldofexpertise;

    @Column(name = "expert_qualification", length = 50)
    private String qualification;

    @Column(name = "expert_languagesspoken", length = 50)
    private String languagesspoken;

    @Column(name = "expert_certifications", length = 100)
    private String certifications;
    
     //"accepted,pending, rejected"
    // Getters and Setters
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getFieldofexpertise() {
        return fieldofexpertise;
    }

    public void setFieldofexpertise(String fieldofexpertise) {
        this.fieldofexpertise = fieldofexpertise;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getLanguagesspoken() {
        return languagesspoken;
    }

    public void setLanguagesspoken(String languagesspoken) {
        this.languagesspoken = languagesspoken;
    }

    public String getCertifications() {
        return certifications;
    }

    public void setCertifications(String certifications) {
        this.certifications = certifications;
    }
}
