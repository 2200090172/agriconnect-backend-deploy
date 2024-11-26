package com.klef.jfsd.springboot.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.springboot.model.Farmer;

@Repository
public interface FarmerRepository extends CrudRepository<Farmer, String>
{
	@Query("Select f from Farmer f where f.phone=?1 and f.password=?2")
	Farmer checkfarmerlogin(String fcontact, String pwd);
}
