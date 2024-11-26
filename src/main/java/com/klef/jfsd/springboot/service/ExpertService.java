package com.klef.jfsd.springboot.service;

import java.util.List;

import com.klef.jfsd.springboot.model.Expert;
import com.klef.jfsd.springboot.model.ExpertResponse;
import com.klef.jfsd.springboot.model.ExpertSignin;
import com.klef.jfsd.springboot.model.FarmerRequest;

public interface ExpertService 
{
	public int expertsignuprequest(Expert expert);
	public List<ExpertSignin> viewallexpertsignin();
	public Expert expertlogin(String email, String password);
	public List<FarmerRequest> viewallfarmerrequests();
   public int sendExpertResponse(Long requestId, String expertEmail, String responseDetails, String status);
   public List<ExpertResponse> viewexpertresponses(String expertemail);
}
