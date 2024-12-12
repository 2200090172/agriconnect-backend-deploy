package com.klef.jfsd.springboot.service;

import com.klef.jfsd.springboot.model.PublicUser;

public interface PublicService {
	 PublicUser userlogin(String uemail, String upwd);
}
