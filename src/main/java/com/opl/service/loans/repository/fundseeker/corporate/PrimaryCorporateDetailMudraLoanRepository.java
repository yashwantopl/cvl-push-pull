package com.opl.service.loans.repository.fundseeker.corporate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opl.service.loans.domain.fundseeker.corporate.PrimaryCorporateDetailMudraLoan;

public interface PrimaryCorporateDetailMudraLoanRepository  extends JpaRepository<PrimaryCorporateDetailMudraLoan, Long>{
	
//	PrimaryCorporateDetailMudraLoan findByApplicationIdAndApplicationProposalMappingProposalId(Long applicationId, Long proposalId);
	
	PrimaryCorporateDetailMudraLoan findFirstByApplicationIdAndApplicationProposalMappingProposalIdOrderByIdDesc(Long applicationId, Long proposalId);
	
	PrimaryCorporateDetailMudraLoan findFirstByApplicationIdAndApplicationProposalMappingProposalIdIsNullOrderByIdDesc(Long applicationId);
	
	PrimaryCorporateDetailMudraLoan findByApplicationIdAndIsActive(Long applicationId,Boolean isActive);
	
}
