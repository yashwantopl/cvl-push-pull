package com.capitaworld.service.loans.repository.fundseeker.corporate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capitaworld.service.loans.domain.fundseeker.corporate.FinalTermLoanDetail;

public interface FinalTermLoanDetailRepository extends JpaRepository<FinalTermLoanDetail, Long> {
	
}
