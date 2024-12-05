package com.klef.jfsd.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klef.jfsd.springboot.model.ExpertResponse;
import com.klef.jfsd.springboot.model.Farmer;
import com.klef.jfsd.springboot.service.FarmerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@RestController
public class FarmerController
{
	@Autowired
	private FarmerService farmerService;
	
	@GetMapping("farmerlogin")
	public String farmerlogin(@RequestParam("fcontact") String fcontact, @RequestParam("fpwd") String fpwd, HttpServletRequest request)
	{
//		System.out.println(fcontact+" "+fpwd);
		Farmer farmer=farmerService.farmerlogin(fcontact, fpwd);
		if(farmer!=null)
		{
			HttpSession session=request.getSession();
			session.setAttribute("farmer", farmer);
			
//			session.setMaxInactiveInterval(5);
			return "Login Success";
		}
		else
		{
			return "Login Fail";
		}	
	}
	
	@GetMapping("checkfarmersession")
	public int checkFarmerSession(HttpServletRequest request) {
	    HttpSession session = request.getSession(false); // Get existing session if available, or return null
	    if (session != null && session.getAttribute("farmer") != null) {
	        return 1; // Session is active
	    } else {
	        return 0; // Session is inactive or expired
	    }
	}
	
	@GetMapping("getfarmerprofile")
	public Farmer getfarmerprofile(HttpServletRequest request) {
	    HttpSession session = request.getSession(false); // Get existing session without creating a new one
	    if (session != null) {
	        Farmer farmer = (Farmer) session.getAttribute("farmer");
	        if (farmer != null) {
	            return farmer;
	        } else {
	            throw new RuntimeException("No farmer profile found in session.");
	        }
	    } else {
	        throw new RuntimeException("Session not found. Please log in.");
	    }
	}
	
	@GetMapping("getfarmerresponses")
	public List<ExpertResponse> getfarmResponses(@RequestParam("phone") String phone)
	{
		return farmerService.getexpertresponses(phone);
	}
	
	@GetMapping("/farmerlogout")
	public int farmerlogout(HttpServletRequest request) {
	    HttpSession session = request.getSession();
	    session.removeAttribute("farmer");
	    System.out.println("Farmer Session Removed!!");
	    return 1;
	}

	
}
