package com.klef.jfsd.springboot.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.springboot.model.Expert;

@Repository
public interface ExpertRepository extends CrudRepository<Expert, String>{

	@Query("select e from Expert e where e.email=?1 and e.password=?2")
	Expert checkexpertlogin(String email, String password);
}
