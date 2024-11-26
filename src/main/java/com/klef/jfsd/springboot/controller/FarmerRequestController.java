package com.klef.jfsd.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.klef.jfsd.springboot.model.FarmerRequest;
import com.klef.jfsd.springboot.service.FarmerRequestService;

@RestController
public class FarmerRequestController {
	
	@Autowired
	private FarmerRequestService farmerRequestService;
	
	@PostMapping("/sendrequest")
	public String sendrequest(@RequestBody FarmerRequest farmerRequest)
	{
		return farmerRequestService.sendrequest(farmerRequest);
	}

}
