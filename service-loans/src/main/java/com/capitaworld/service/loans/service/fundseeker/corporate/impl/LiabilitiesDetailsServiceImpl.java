package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import com.capitaworld.service.loans.exceptions.ExcelException;
import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ApplicationProposalMappingRepository;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.fundseeker.corporate.LiabilitiesDetails;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LiabilitiesDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.LiabilitiesDetailsService;
import com.capitaworld.service.loans.utils.cma.LiabilitiesDetailsExcelReader;

@Service
public class LiabilitiesDetailsServiceImpl implements LiabilitiesDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(LiabilitiesDetailsServiceImpl.class);
	
	@Autowired
	LiabilitiesDetailsRepository liabilitiesDetailsRepository;

	@Autowired
	LoanApplicationRepository loanApplicationRepository;

	@Autowired
	ApplicationProposalMappingRepository applicationProposalMappingRepository;

	@Override
	public void saveOrUpdate(LiabilitiesDetails liabilitiesDetails) {

		liabilitiesDetailsRepository.save(liabilitiesDetails);
	}

	@Override
	public void  readLiabilitiesDetails(Long applicationId,Long storageDetailsId, XSSFSheet sheet) throws ExcelException {
		logger.info("=========== Enter in readLiabilitiesDetails()========= applicationId ==> {} ", applicationId  );
		try {
			LiabilitiesDetailsExcelReader.run(storageDetailsId, sheet, loanApplicationRepository.findOne(applicationId), liabilitiesDetailsRepository);
		} catch (ExcelException e) {
			logger.error("Error/Exception while saving Liabilities Details from excel to db MSG -->{} " , e);
			throw e;
		}

	}

	@Override
	public void  readLiabilitiesDetails(Long applicationId,Long proposalMappingId,Long storageDetailsId, XSSFSheet sheet) throws ExcelException {
		logger.info("=========== Enter in readOperatingStatementDetails()========= applicationId ==> {} proposalMappingId ==> {} ", applicationId  , proposalMappingId);
		// TODO Auto-generated method stub
		LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(applicationId);
		ApplicationProposalMapping  applicationProposalMapping = applicationProposalMappingRepository.findOne(proposalMappingId);
		try {
			LiabilitiesDetailsExcelReader.run(storageDetailsId, sheet, loanApplicationMaster,applicationProposalMapping, liabilitiesDetailsRepository);
		} catch (ExcelException e) {
			logger.error("Error/Exception while saving Operating Statement Details from excel to db MSG -->{} " , e);
			throw e;
		}
	}

	@Override
	public void inActiveAssetsDetails(Long storageDetailsId) {

		liabilitiesDetailsRepository.inActiveAssetsDetails(storageDetailsId);
		
	}

}
