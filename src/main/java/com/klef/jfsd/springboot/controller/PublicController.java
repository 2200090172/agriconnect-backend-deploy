package com.klef.jfsd.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klef.jfsd.springboot.model.PublicUser;
import com.klef.jfsd.springboot.service.PublicService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class PublicController {

    @Autowired
    private PublicService publicService;

    @GetMapping("/userlogin")
    public String userlogin(@RequestParam(value = "uemail", required = false) String uemail,
                            @RequestParam(value = "upwd", required = false) String upwd,
                            HttpServletRequest request) {
        if (uemail == null || upwd == null) {
            return "Missing parameters: uemail and upwd are required.";
        }

        PublicUser publicUser = publicService.userlogin(uemail, upwd);
        if (publicUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("publicuser", publicUser); // Store full user object in session
            return "Login Success";
        } else {
            return "Login Fail";
        }
    }

    @GetMapping("/checkusersession")
    public PublicUser checkuserSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // Get existing session if available
        if (session != null && session.getAttribute("publicuser") != null) {
            return (PublicUser) session.getAttribute("publicuser"); // Return full user object from session
        } else {
            return null; // Session is inactive or expired
        }
    }

}
