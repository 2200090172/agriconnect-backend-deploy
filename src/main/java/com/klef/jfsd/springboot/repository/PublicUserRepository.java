package com.klef.jfsd.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.klef.jfsd.springboot.model.PublicUser;

public interface PublicUserRepository extends JpaRepository<PublicUser, String> {
    @Query("SELECT p FROM PublicUser p WHERE p.email = ?1 AND p.password = ?2")
    PublicUser checkuserlogin(String uemail, String upwd);
}
