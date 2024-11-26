package com.klef.jfsd.springboot.service;


import java.util.List;
import java.util.Map;

import com.klef.jfsd.springboot.model.Admin;
import com.klef.jfsd.springboot.model.Expert;
import com.klef.jfsd.springboot.model.Farmer;

import ch.qos.logback.classic.spi.STEUtil;

public interface AdminService 
{
	public Admin adminlogin(String uname, String pwd);
	public int addexpert(Expert expert);
	public String addfarmer( Farmer f);
	public List<Farmer> viewallfarmers();
	public List<Expert> viewallexperts();
	public int expertaction(String email, String action);
    public List<Map<String, Object>> getfarmerrequestsovertime(); 
    public List<Map<String, Object>> getExpertRequestSolvedData();


}
