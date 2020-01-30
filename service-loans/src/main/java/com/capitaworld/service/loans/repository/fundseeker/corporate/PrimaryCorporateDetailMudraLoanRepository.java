package com.capitaworld.service.loans.repository.fundseeker.corporate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryCorporateDetailMudraLoan;

public interface PrimaryCorporateDetailMudraLoanRepository  extends JpaRepository<PrimaryCorporateDetailMudraLoan, Long>{
	
	PrimaryCorporateDetailMudraLoan findByApplicationIdAndApplicationProposalMappingProposalId(Long applicationId, Long proposalId);
	
	PrimaryCorporateDetailMudraLoan findByApplicationIdAndApplicationProposalMappingProposalIdIsNull(Long applicationId);
	
	PrimaryCorporateDetailMudraLoan findByApplicationIdAndIsActive(Long applicationId,Boolean isActive);
	
}
