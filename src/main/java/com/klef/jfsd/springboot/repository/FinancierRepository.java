package com.klef.jfsd.springboot.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.klef.jfsd.springboot.model.Financier;

@Repository
public interface FinancierRepository  extends CrudRepository<Financier, String>
{
	@Query("select f from Financier f where f.email=?1 and f.password=?2")
	Financier financierlogin(String email, String password);
	
	@Transactional
	@Modifying
	@Query("UPDATE Financier f SET f.password = :password WHERE f.email = :email")
	int updatePasswordByEmail(@Param("email") String email, @Param("password") String password);

	
	
}
