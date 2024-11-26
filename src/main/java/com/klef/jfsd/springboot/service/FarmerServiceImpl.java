package com.klef.jfsd.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.model.ExpertResponse;
import com.klef.jfsd.springboot.model.Farmer;
import com.klef.jfsd.springboot.model.FarmerRequest;
import com.klef.jfsd.springboot.repository.ExpertResponseRepository;
import com.klef.jfsd.springboot.repository.FarmerRepository;

@Service
public class FarmerServiceImpl implements FarmerService {
	
	@Autowired
	private FarmerRepository farmerRepository;
	
	@Autowired
	private ExpertResponseRepository expertResponseRepository;
	
	@Override
	public Farmer farmerlogin(String fcontact, String fpwd) {
		return farmerRepository.checkfarmerlogin(fcontact, fpwd);
		
		
	}

	@Override
	public Farmer getfarmerprofile(String phone) {
		return farmerRepository.findById(phone).get(); //convert optional<farmer> to farmer by get()
	}

	@Override
	public List<ExpertResponse> getexpertresponses(String phone) {
		return expertResponseRepository.getExpertResponsesByPhone(phone);
	}

	

}
