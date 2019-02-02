package com.capitaworld.service.loans.repository.fundseeker;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capitaworld.service.loans.domain.fundseeker.IneligibleProposalTransferHistory;

/**
 * 
 * @author jaimin.darji
 *
 */
public interface IneligibleProposalTransferHistoryRepository extends JpaRepository<IneligibleProposalTransferHistory,Long> {

}
