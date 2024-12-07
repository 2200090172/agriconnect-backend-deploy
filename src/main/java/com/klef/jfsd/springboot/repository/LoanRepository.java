package com.klef.jfsd.springboot.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.springboot.model.Loan;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> 
{
	
	@Query("select l.addedby from Loan l where l.loanid=?1")
	String findfinancieremail(long loanid);
}
