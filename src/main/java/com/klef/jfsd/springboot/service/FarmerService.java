package com.klef.jfsd.springboot.service;

import java.util.List;

import com.klef.jfsd.springboot.model.ExpertResponse;
import com.klef.jfsd.springboot.model.Farmer;

public interface FarmerService {
	public Farmer farmerlogin(String fcontact, String fpwd);
	public Farmer getfarmerprofile(String phone);
	public List<ExpertResponse> getexpertresponses(String phone);
}
