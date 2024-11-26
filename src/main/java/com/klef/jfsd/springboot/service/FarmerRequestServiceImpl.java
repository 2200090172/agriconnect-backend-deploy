package com.klef.jfsd.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.model.FarmerRequest;
import com.klef.jfsd.springboot.repository.FarmerRequestRepository;

@Service
public class FarmerRequestServiceImpl implements FarmerRequestService{
	
	@Autowired
	private FarmerRequestRepository farmerRequestRepository;
	
	@Override
	public String sendrequest(FarmerRequest frequest) {
		frequest.setStatus("Pending");
		farmerRequestRepository.save(frequest);
		return "Request Sent into table";
	}

}
