package com.capitaworld.service.loans.service.fundseeker.corporate;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.capitaworld.service.loans.domain.fundseeker.corporate.DprUserDataDetail;
import com.capitaworld.service.matchengine.exception.MatchException;

/**
 * @author Sanket
 *
 */
public interface ProposalService {

	public void readProposalDetails(Long applicationId, Long storageDetailsId, XSSFSheet proposalSheet,
			DprUserDataDetail dprUserDataDetail);

	public void inActiveProposalDetails(Long storageDetailsId);
	
	public void checkPendingProposal() ;

}
