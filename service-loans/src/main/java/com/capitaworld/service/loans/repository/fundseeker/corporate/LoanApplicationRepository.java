package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;

public interface LoanApplicationRepository extends JpaRepository<LoanApplicationMaster, Long> {

	@Modifying
	@Query("update LoanApplicationMaster lm set lm.isActive = false where lm.id =:id and lm.isActive = true")
	public int inActive(@Param("id") Long id);

	@Query("from LoanApplicationMaster lm where lm.userId =:userId and lm.isActive = true")
	public List<LoanApplicationMaster> getUserLoans(@Param("userId") Long userId);

}
