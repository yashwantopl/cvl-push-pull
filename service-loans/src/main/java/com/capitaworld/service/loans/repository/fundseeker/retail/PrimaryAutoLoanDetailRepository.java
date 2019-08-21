package com.capitaworld.service.loans.repository.fundseeker.retail;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryAutoLoanDetail;

public interface PrimaryAutoLoanDetailRepository extends JpaRepository<PrimaryAutoLoanDetail, Long> {

	public PrimaryAutoLoanDetail findById(Long id);
}
