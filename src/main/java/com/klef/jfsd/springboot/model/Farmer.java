package com.klef.jfsd.springboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "farmer_table")
public class Farmer {

    @Id
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "fullname", nullable = false, length = 50)
    private String fullname;

    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "location", length = 100)
    private String location;

    @Column(name = "farmsize", length = 20)
    private String farmsize;

    @Column(name = "crops", length = 100)
    private String crops;

    @Column(name = "preferredlanguage", length = 30)
    private String preferredlanguage;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFarmsize() {
        return farmsize;
    }

    public void setFarmsize(String farmsize) {
        this.farmsize = farmsize;
    }

    public String getCrops() {
        return crops;
    }

    public void setCrops(String crops) {
        this.crops = crops;
    }

    public String getPreferredLanguage() {
        return preferredlanguage;
    }

    public void setPreferredLanguage(String preferredlanguage) {
        this.preferredlanguage = preferredlanguage;
    }

	
}
