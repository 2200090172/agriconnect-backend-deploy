package com.klef.jfsd.springboot.controller;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klef.jfsd.springboot.model.Admin;
import com.klef.jfsd.springboot.model.Expert;
import com.klef.jfsd.springboot.model.ExpertSignin;
import com.klef.jfsd.springboot.model.Farmer;
import com.klef.jfsd.springboot.model.Financier;
import com.klef.jfsd.springboot.service.AdminService;
import com.klef.jfsd.springboot.service.ExpertService;
import com.klef.jfsd.springboot.service.FarmerRequestService;
import com.klef.jfsd.springboot.service.FinancierService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class AdminController 
{
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ExpertService expertService;
	
	@Autowired
	private FinancierService financierService;
	
	@GetMapping("/")
	public String demo()
	{
		return "Hello Bro";
	}
	
	
	@GetMapping("adminlogin")
	public int adminlogin(@RequestParam("username")String uname, @RequestParam("password") String pwd,  HttpServletRequest request)
	{
		System.out.println(uname+" ->"+pwd);
		Admin admin=adminService.adminlogin(uname, pwd);
		if(admin!=null)
		{
			HttpSession session=request.getSession();
			session.setAttribute("admin", admin);
//			session.setMaxInactiveInterval(5);
			
			return 1;
		}
		else
		{
			return 0;
		}
	}
	
	@GetMapping("checkadminsession")
	public int checkadminsession(HttpServletRequest request)
	{
				System.out.println("Checking Admin Session");
		HttpSession session=request.getSession();
		if(session!=null && session.getAttribute("admin")!=null)
		{
			System.out.println("Admin session true : => "+session);
			return 1;
		}
       System.out.println("Admin session false : => "+session);
		return 0;
	}
	
	@PostMapping("addfarmer")
	public String addfarmer(@RequestBody Farmer farmer)
	{
		return adminService.addfarmer(farmer);
	}
	
	
	@GetMapping("viewallfarmers")
	public List<Farmer> viewallfarmers()
	{
		return adminService.viewallfarmers();
	}
	
	@PostMapping("addexpert")
	public int addexpert(@RequestBody Expert expert)
	{
		System.out.println(expert.getEmail());
		return adminService.addexpert(expert);
	}
	
	@GetMapping("viewallexperts")
	public List<Expert> viewallexperts()
	{
		return adminService.viewallexperts();
	}
	
	@GetMapping("viewallexpertsignin")
	public List<ExpertSignin> viewallexpertsignin()
	{
		return expertService.viewallexpertsignin();
	}
	
	@PostMapping("expertaction")
	public int expertaction(@RequestParam("email") String email, @RequestParam("action") String action)
	{
		return adminService.expertaction(email, action);
	}
	
	
	@GetMapping("/requests-overtime")
    public List<Map<String, Object>> getfarmerrequestsovertime() {
        return adminService.getfarmerrequestsovertime();
    }
	
	@GetMapping("/expert-solved-requests")
    public List<Map<String, Object>> getExpertRequestSolvedData() {
        return adminService.getExpertRequestSolvedData();
    }
	
	@GetMapping("/adminlogout")
	public int adminlogout(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		session.removeAttribute("admin");
		System.out.println("Admin Session Removed!!");
		return 1;
	}
	
	
	
	@PostMapping("/addfinancier")
	public int addfinancier(@RequestBody Financier financier)
	{
		return financierService.addfinancier(financier);
	}
	
	
	@PostMapping("updatepassword")
	public int updatepassword(@RequestParam String email, @RequestParam String password, @RequestParam String role)
	{
		if(role=="expert")
		{
		return expertService.updatepassword(email, password);	
		}
		else if(role=="financier")
		{
			return financierService.updatepassword(email, password);
		}
		return 1;
	}
}
