package com.klef.jfsd.springboot.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.model.Expert;
import com.klef.jfsd.springboot.model.ExpertResponse;
import com.klef.jfsd.springboot.model.ExpertSignin;
import com.klef.jfsd.springboot.model.FarmerRequest;
import com.klef.jfsd.springboot.model.FarmingContent;
import com.klef.jfsd.springboot.repository.ExpertRepository;
import com.klef.jfsd.springboot.repository.ExpertResponseRepository;
import com.klef.jfsd.springboot.repository.ExpertSigninRepository;
import com.klef.jfsd.springboot.repository.FarmerRequestRepository;
import com.klef.jfsd.springboot.repository.FarmingContentRepository;

@Service
public class ExpertServiceImpl implements ExpertService 
{
	@Autowired
	private ExpertRepository expertRepository;
	@Autowired
	private FarmingContentRepository farmingContentRepository;
	
	
	@Autowired
	private ExpertSigninRepository expertSigninRepository;
	
	@Autowired
	private ExpertResponseRepository expertResponseRepository;
	
	@Autowired
	private FarmerRequestRepository farmerRequestRepository;

	@Override
	public int expertsignuprequest(Expert expert) {
		ExpertSignin expertSignin=new ExpertSignin(expert.getPhone(), expert.getFullname(), expert.getEmail(), expert.getExperience(), expert.getFieldofexpertise(),  expert.getQualification(), expert.getLanguagesspoken(), expert.getCertifications(),"pending");
		expertSigninRepository.save(expertSignin);
		return 1;
	}

	@Override
	public List<ExpertSignin> viewallexpertsignin() {
		return (List<ExpertSignin>) expertSigninRepository.findAll();
	}

	@Override
	public Expert expertlogin(String email, String password) {
		return expertRepository.checkexpertlogin(email, password);
		
	}

	@Override
	public List<FarmerRequest> viewallfarmerrequests() {
		List<FarmerRequest> farmers= (List<FarmerRequest>)farmerRequestRepository.findAll();
//		for(var i:farmers)
//		{
//			System.out.println(i.toString());
//		}
//		
//		
		return farmers;
	}

	 public int sendExpertResponse(Long requestId, String expertEmail, String responseDetails, String status) {
	        FarmerRequest farmerRequest = farmerRequestRepository.findById(requestId).get();
		       System.out.println(requestId);

	       System.out.println(farmerRequest.getPhone());
	        farmerRequest.setStatus("Resolved");

	        ExpertResponse expertResponse = new ExpertResponse();
	        
	        expertResponse.setFarmerphone(farmerRequest.getPhone());
	        expertResponse.setExpertEmail(expertEmail);
	        expertResponse.setResponseDetails(responseDetails);
	        expertResponse.setResponseDate(LocalDateTime.now()); 
	        expertResponse.setStatus(status);

	         expertResponseRepository.save(expertResponse);
	         return 1;
	    }

	@Override
	public List<ExpertResponse> viewexpertresponses(String expertemail) {
		return expertResponseRepository.getExpertResponses(expertemail);
	}

	@Override
	public String insertfarmingcontent(FarmingContent farmingContent) {
		farmingContentRepository.save(farmingContent);
		return "Saved Successfully";
	}

	@Override
	public List<FarmingContent> getAllFarmingContent() {
		return (List<FarmingContent>)farmingContentRepository.findAll();
	}

	@Override
	public FarmingContent getFarmingContentById(long id) {
		return farmingContentRepository.findById(id).get();
	}
	
	

	
}
