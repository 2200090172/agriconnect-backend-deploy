package com.klef.jfsd.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.klef.jfsd.springboot.model.Expert;
import com.klef.jfsd.springboot.model.ExpertResponse;
import com.klef.jfsd.springboot.model.FarmerRequest;
import com.klef.jfsd.springboot.service.ExpertService;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class ExpertController 
{
	static int otp;
	@Autowired
	private ExpertService expertService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	@PostMapping("/sendotp/{name}/{toemail}")
	public int sendEmail(@PathVariable String name, @PathVariable String toemail, HttpServletRequest request) throws Exception {
	 
		System.out.println("Sending OTP"+name+" email->>"+toemail);
	 String subject = "Verify your email!";
	 String msg = "Please enter the below OTP";
	 MimeMessage mimeMessage = mailSender.createMimeMessage();
	 MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

	  otp = (int)(Math.random() * 99999); // random number generation
	 helper.setTo(toemail);
	 helper.setSubject(subject);
	 helper.setFrom("aravindch0055@gmail.com");
	 String htmlContent =
	 "<h3>Contact Form Details</h3>" +
	 "<p><strong>Name:</strong> " + name + "</p>" +
	 "<p><strong>Email:</strong> " + toemail + "</p>" +
	 "<p><strong>Subject:</strong> " + subject + "</p>" +
	 "<p><strong>Message:</strong> " + msg + "</p>" +
	 "<p><strong>OTP:</strong> " + otp + "</p>";
	 helper.setText(htmlContent, true);
	 mailSender.send(mimeMessage);

	 return 1;
	 }
	
	@PostMapping("verifyotp/{eotp}")
	public int verifyotp(@PathVariable int eotp)
	{
		if(eotp==otp)
		return 1;
		return 0;
	}
	
	@PostMapping("expertsignuprequest")
	public int expertsignup(@RequestBody Expert expert)
	{
		return expertService.expertsignuprequest(expert);
	}
	
	
	@GetMapping("expertlogin")
	public int expertlogin(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletRequest request)
	{
		Expert expert=expertService.expertlogin(email, password);

		if(expert!=null)
		{
			HttpSession session=request.getSession();
			session.setAttribute("expert", expert);
			System.out.println("Session attributes: " + session.getAttributeNames());
			
			return 1;
		}
		else
		{
			return 0;
		}
	}
	
	
	
	@GetMapping("viewallfarmerrequests")
	public List<FarmerRequest> viewallfarmerrequests()
	{
		return expertService.viewallfarmerrequests();
	}
	
	@GetMapping("getexpertprofile")
    public Expert getexpertprofile(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
//        System.out.println("Session=>"+session);
        if (session != null) {
            Expert expert = (Expert) session.getAttribute("expert");
            System.out.println("Session ID: " + session.getId());
            System.out.println("Expert in session: " + session.getAttribute("expert"));
            if (expert != null) {
                System.out.println("Expert session active: " + expert.getEmail());
                return expert;
            } else {
                System.out.println("Expert session found, but no expert object.");
                throw new RuntimeException("Expert session not found.");
            }
            
            

        } else {
            System.out.println("No active session found.");
            throw new RuntimeException("No active session.");
        }
    }
	

    @GetMapping("checkexpertsession")
    public int checkExpertSession(HttpServletRequest request) {
    	System.out.println("Checking Session");
        HttpSession session = request.getSession(false);
        if (session != null) {
            Expert expert = (Expert) session.getAttribute("expert");
            boolean isActive = expert != null;
            System.out.println("Session check: " + isActive);
            return 1;
        }
        System.out.println("Session is null.");
        return 0;
    }

	 //check here requestId not coming , tomorrow try with sample api that sends only request id=> tha makes help to find problem size
	 @PostMapping("sendexpertresponse")
	    public int sendExpertResponse(@RequestParam("requestId") Long requestId,@RequestParam("expertEmail") String expertEmail,@RequestParam("responseDetails") String responseDetails,
	            @RequestParam("status") String status, HttpServletRequest request) {
		 System.out.println("FRequestID ->"+requestId);
		 System.out.println(" "+expertEmail);
	        try {
	        	System.out.println(requestId+" "+expertEmail+" "+responseDetails+" "+status+" "+request);
	        	return expertService.sendExpertResponse(requestId, expertEmail, responseDetails, status);
	            
	        } catch (RuntimeException e) 
	        {
	        	System.out.println(e);
	        	return 0;
	        }
	    }
	 
	 
	 @GetMapping("getexpertresponses/{expertemail}")
	 public List<ExpertResponse> viewexpertresponses(@PathVariable String expertemail)
	 {
		 System.out.println("expert email =>"+expertemail);
		return expertService.viewexpertresponses(expertemail); 
	 }
}
