package com.klef.jfsd.springboot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.model.Admin;
import com.klef.jfsd.springboot.model.Expert;
import com.klef.jfsd.springboot.model.ExpertSignin;
import com.klef.jfsd.springboot.model.Farmer;
import com.klef.jfsd.springboot.repository.AdminRepository;
import com.klef.jfsd.springboot.repository.ExpertRepository;
import com.klef.jfsd.springboot.repository.ExpertResponseRepository;
import com.klef.jfsd.springboot.repository.ExpertSigninRepository;
import com.klef.jfsd.springboot.repository.FarmerRepository;
import com.klef.jfsd.springboot.repository.FarmerRequestRepository;

@Service
public class AdminServiceImpl implements AdminService
{
	@Autowired
	private FarmerRepository farmerRepository;
	
	@Autowired
	FarmerRequestRepository farmerRequestRepository;
	
	@Autowired
	private ExpertRepository expertRepository;
	
	@Autowired
	private ExpertResponseRepository expertResponseRepository;
	
	@Autowired
	private ExpertSigninRepository expertSigninRepository;
	
	
	@Autowired
	private AdminRepository adminRepository;
	
	
	@Override
	public String addfarmer(Farmer farmer) {
	    // Directly apply encryption logic within this method
	    
	    // Step 1: Encrypt the password (first shift by 3 and alternate #, $)
	    String firstStep = applyShiftAndAlternate(farmer.getPassword(), 3);
	    
	    // Step 2: Encrypt the password again (shift by 5 and alternate #, $)
	    String secondStep = applyShiftAndAlternate(farmer.getPassword(), 5);
	    
	    // Concatenate both encrypted parts
	    String encryptedPassword = firstStep + secondStep;

	    // Set the encrypted password back to the farmer object
	    farmer.setPassword(encryptedPassword);
	    
	    // Save the farmer object with the encrypted password
	    farmerRepository.save(farmer);
	    
	    return "Farmer Added Successfully!!";
	}

	// Helper method to apply Caesar cipher shift and add #, $ alternately
	private String applyShiftAndAlternate(String password, int shift) {
	    StringBuilder result = new StringBuilder();
	    for (int i = 0; i < password.length(); i++) {
	        char currentChar = password.charAt(i);
	        char shiftedChar = (char) (currentChar + shift); // Caesar cipher shift
	        result.append(shiftedChar);
	        
	        // Add # or $ alternately between the characters
	        if (i % 2 == 0) {
	            result.append('#');
	        } else {
	            result.append('$');
	        }
	    }
	    return result.toString();
	}


	@Override
	public List<Farmer> viewallfarmers() {
	return 	(List<Farmer>)farmerRepository.findAll();
	}

	@Override
	public Admin adminlogin(String uname, String pwd) {
		return adminRepository.adminlogin(uname, pwd);
	}

	@Override
	public int addexpert(Expert expert) 
	{
		expertRepository.save(expert);
		return 1;
	}

	@Override
	public List<Expert> viewallexperts() {
		return (List<Expert>) expertRepository.findAll();
	}

	@Override
	public int expertaction(String email, String action) {
		expertSigninRepository.updateexpertstatus(action, email);
		if ("accept".equals(action)) {
		    Optional<ExpertSignin> expertOptional = expertSigninRepository.findById(email);
		    
		    if (expertOptional.isPresent()) {
		        ExpertSignin expertSignin = expertOptional.get();
		        Expert expert = new Expert();
		        expert.setPhone(expertSignin.getPhone());
		        expert.setFullname(expertSignin.getFullname());
		        expert.setEmail(expertSignin.getEmail());
		        expert.setExperience(expertSignin.getExperience());
		        expert.setFieldofexpertise(expertSignin.getFieldofexpertise());
		        expert.setQualification(expertSignin.getQualification());
		        expert.setLanguagesspoken(expertSignin.getLanguagesspoken());
		        expert.setCertifications(expertSignin.getCertifications());

		        // Save the new expert in the Expert table
		        expertRepository.save(expert);
		        return 1;
		    } else
		    {
		        System.out.println("Expert with email " + email + " not found.");
		        return 0;
		    }
		}

		
		return 1;
	}
	
	
	//Admin Dashboard below
	public List<Map<String, Object>> getfarmerrequestsovertime() {
        List<Object[]> results = farmerRequestRepository.getfarmerrequestsovertime();
        List<Map<String, Object>> formattedResults = new ArrayList<>();

        for(Object[] result : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("date", result[0].toString());  // Convert date to String
            map.put("totalRequests", result[1]);
            formattedResults.add(map);
        }

        return formattedResults;
    }
	
	 public List<Map<String, Object>> getExpertRequestSolvedData() {
	        List<Object[]> results=expertResponseRepository.countRequestsByExpert();
	        List<Map<String, Object>> formattedResults = new ArrayList<>();
	        
	        for(Object[] result : results) {
	            Map<String, Object> map = new HashMap<>();
	            map.put("expert", result[0].toString());  // Convert date to String
	            map.put("solvedRequests", result[1]);
	            formattedResults.add(map);
	        }

	        return formattedResults;
	    }

}
