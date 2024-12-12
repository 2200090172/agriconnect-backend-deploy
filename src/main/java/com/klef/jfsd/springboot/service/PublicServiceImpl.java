package com.klef.jfsd.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.model.PublicUser;
import com.klef.jfsd.springboot.repository.PublicUserRepository;

@Service
public class PublicServiceImpl implements PublicService {

    @Autowired
    private PublicUserRepository publicUserRepository;

    @Override
    public PublicUser userlogin(String uemail, String upwd) {
        return publicUserRepository.checkuserlogin(uemail, upwd);
    }
}
