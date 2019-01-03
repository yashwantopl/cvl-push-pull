package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.DprUserDataDetail;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ProjectImplementationScheduleDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.ProposalService;
import com.capitaworld.service.loans.utils.dpr.DprEighthSheetExcelReader;
import com.capitaworld.service.matchengine.ProposalDetailsClient;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class ProposalServiceImpl implements ProposalService {

	private static final Logger logger = LoggerFactory.getLogger(ProposalServiceImpl.class);
	
	@Autowired
	LoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	private ProposalDetailsClient proposalDetailsClient;
	
	@Autowired
	ProjectImplementationScheduleDetailRepository projectImplementationScheduleDetailRepository; 

	@Override
	public void readProposalDetails(Long applicationId, Long storageDetailsId,
			XSSFSheet proposalSheet, DprUserDataDetail dprUserDataDetail) {
		DprEighthSheetExcelReader.run(storageDetailsId, proposalSheet,
				loanApplicationRepository.findOne(applicationId), projectImplementationScheduleDetailRepository,dprUserDataDetail);

	}

	@Override
	public void inActiveProposalDetails(Long storageDetailsId) {
		projectImplementationScheduleDetailRepository.inActiveProjectImplementationScheduleDetails(storageDetailsId);
		
	}

	@Override
	public void checkPendingProposal() {
		try
		{
			proposalDetailsClient.checkPendingProposal();	
		}
		catch (Exception e) {
			logger.error("Exception in checkPendingProposal() ",e);
		}
		
		
	}
	
	

}
