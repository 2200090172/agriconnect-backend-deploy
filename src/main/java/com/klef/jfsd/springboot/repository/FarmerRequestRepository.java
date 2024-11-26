package com.klef.jfsd.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.springboot.model.FarmerRequest;

@Repository
public interface FarmerRequestRepository extends CrudRepository<FarmerRequest, Long>
{
	@Query("SELECT DATE(requestdate) AS date, COUNT(*) AS totalRequests " +
	           "FROM FarmerRequest " +
	           "GROUP BY DATE(requestdate) " +
	           "ORDER BY date ASC")
	    List<Object[]> getfarmerrequestsovertime();
}
