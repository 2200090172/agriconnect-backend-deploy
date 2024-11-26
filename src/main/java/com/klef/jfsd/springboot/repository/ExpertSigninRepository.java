package com.klef.jfsd.springboot.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.klef.jfsd.springboot.model.ExpertSignin;

@Repository
public interface ExpertSigninRepository extends CrudRepository<ExpertSignin, String> 
{
	@Transactional
	@Modifying
	@Query("update ExpertSignin e set e.status=?1 where e.email=?2")
	int updateexpertstatus(String status, String email);
	
}
