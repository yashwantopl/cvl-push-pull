package com.capitaworld.service.loans.repository.fundseeker.corporate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;

public interface LoanApplicationRepository extends JpaRepository<LoanApplicationMaster, Long>{
	

}
