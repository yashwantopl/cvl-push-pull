package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ApplicationProposalMappingRepository;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.fundseeker.corporate.LiabilitiesDetails;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LiabilitiesDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.LiabilitiesDetailsService;
import com.capitaworld.service.loans.utils.cma.LiabilitiesDetailsExcelReader;

@Service
public class LiabilitiesDetailsServiceImpl implements LiabilitiesDetailsService {

	@Autowired
	LiabilitiesDetailsRepository liabilitiesDetailsRepository;
	

	@Autowired
	LoanApplicationRepository loanApplicationRepository;

	@Autowired
	ApplicationProposalMappingRepository applicationProposalMappingRepository;
	
	@Override
	public void saveOrUpdate(LiabilitiesDetails liabilitiesDetails) {
		// TODO Auto-generated method stub
		liabilitiesDetailsRepository.save(liabilitiesDetails);
	}

	@Override
	public void  readLiabilitiesDetails(Long applicationId,Long storageDetailsId, XSSFSheet sheet) {
		// TODO Auto-generated method stub
		  
		LiabilitiesDetailsExcelReader.run(storageDetailsId, sheet, loanApplicationRepository.findOne(applicationId), liabilitiesDetailsRepository);
	
	}

	@Override
	public void  readLiabilitiesDetails(Long applicationId,Long proposalMappingId,Long storageDetailsId, XSSFSheet sheet) {

		// TODO Auto-generated method stub
		LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(applicationId);
		ApplicationProposalMapping  applicationProposalMapping = applicationProposalMappingRepository.findOne(proposalMappingId);
		LiabilitiesDetailsExcelReader.run(storageDetailsId, sheet, loanApplicationMaster,applicationProposalMapping, liabilitiesDetailsRepository);
	}

	@Override
	public void inActiveAssetsDetails(Long storageDetailsId) {
		// TODO Auto-generated method stub
		liabilitiesDetailsRepository.inActiveAssetsDetails(storageDetailsId);
		
	}

}
