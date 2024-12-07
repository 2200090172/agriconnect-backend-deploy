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
	    // Step 1: Encrypt the password (first shift by 3 and alternate #, $)
	    String firstStep = applyShiftAndAlternate(fpwd, 3);
	    
	    // Step 2: Encrypt the password again (shift by 5 and alternate #, $)
	    String secondStep = applyShiftAndAlternate(fpwd, 5);
	    
	    // Concatenate both encrypted parts
	    String encryptedPassword = firstStep + secondStep;
	    
	    // Step 3: Use the encrypted password to check for login in the database
	    return farmerRepository.checkfarmerlogin(fcontact, encryptedPassword);
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
	public Farmer getfarmerprofile(String phone) {
		return farmerRepository.findById(phone).get(); //convert optional<farmer> to farmer by get()
	}

	@Override
	public List<ExpertResponse> getexpertresponses(String phone) {
		return expertResponseRepository.getExpertResponsesByPhone(phone);
	}

	

}
