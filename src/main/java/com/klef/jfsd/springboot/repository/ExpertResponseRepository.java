package com.klef.jfsd.springboot.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.springboot.model.ExpertResponse;

@Repository
public interface ExpertResponseRepository  extends CrudRepository<ExpertResponse, Long>
{
	@Query("select e from ExpertResponse e where e.farmerphone = ?1")
	public List<ExpertResponse> getExpertResponsesByPhone(String phone);
	
	@Query("SELECT e.expertEmail AS email, COUNT(e) AS totalCount "
		       + "FROM ExpertResponse e "
		       + "GROUP BY e.expertEmail")
		List<Object[]> countRequestsByExpert();
		
		
	@Query("select e from ExpertResponse e where e.expertEmail=?1")
	public List<ExpertResponse> getExpertResponses(String expertemail);
}
