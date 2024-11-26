package com.klef.jfsd.springboot.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "frequest_table")
public class FarmerRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "frequest_id")
    private Long requestid;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "fullname", nullable = false, length = 50)
    private String fullname;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "crop_type", nullable = false)
    private String croptype;

    @Column(name = "request_details", nullable = false)
    private String requestdetails;

    @Column(name = "request_date", nullable = false)
    private LocalDateTime requestdate;

    @Column(name = "location", length = 100)
    private String location;

    @Column(name = "status")
    private String status;

	public Long getRequestid() {
		return requestid;
	}

	public void setRequestid(Long requestid) {
		this.requestid = requestid;
	}

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

	public String getCroptype() {
		return croptype;
	}

	public void setCroptype(String croptype) {
		this.croptype = croptype;
	}

	public String getRequestdetails() {
		return requestdetails;
	}

	public void setRequestdetails(String requestdetails) {
		this.requestdetails = requestdetails;
	}

	public LocalDateTime getRequestdate() {
		return requestdate;
	}

	public void setRequestdate(LocalDateTime requestdate) {
		this.requestdate = requestdate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "FarmerRequest [requestid=" + requestid + ", phone=" + phone + ", fullname=" + fullname + ", email="
				+ email + ", croptype=" + croptype + ", requestdetails=" + requestdetails + ", requestdate="
				+ requestdate + ", location=" + location + ", status=" + status + "]";
	}
    
    
   
}
