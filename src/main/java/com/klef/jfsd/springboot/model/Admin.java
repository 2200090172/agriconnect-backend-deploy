package com.klef.jfsd.springboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin_table")
public class Admin 
{
	@Id
	@Column(name = "admin_username",nullable = false,length = 30)
	private String username;
	
	@Column(name = "admin_password", nullable = false, length = 30)
	private String password;
}
