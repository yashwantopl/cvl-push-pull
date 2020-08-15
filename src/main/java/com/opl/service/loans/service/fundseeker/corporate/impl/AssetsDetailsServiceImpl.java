package com.opl.service.loans.service.fundseeker.corporate.impl;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opl.mudra.api.loans.exception.ExcelException;
import com.opl.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.opl.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.opl.service.loans.domain.fundseeker.corporate.AssetsDetails;
import com.opl.service.loans.repository.fundseeker.corporate.ApplicationProposalMappingRepository;
import com.opl.service.loans.repository.fundseeker.corporate.AssetsDetailsRepository;
import com.opl.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.opl.service.loans.service.fundseeker.corporate.AssetsDetailsService;
import com.opl.service.loans.utils.cma.AssetsDetailsExcelReader;

@Service
public class AssetsDetailsServiceImpl implements AssetsDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(AssetsDetailsServiceImpl.class);

	@Autowired
	AssetsDetailsRepository assetsDetailsRepository;

	@Autowired
	LoanApplicationRepository loanApplicationRepository;

	@Autowired
	ApplicationProposalMappingRepository applicationProposalMappingRepository;

	@Override
	public void saveOrUpdate(AssetsDetails assetsDetails) {
		assetsDetailsRepository.save(assetsDetails);
	}

	@Override
	public void readAssetsDetails(Long applicationId, Long storageDetailsId, XSSFSheet sheet) throws ExcelException{
		logger.info("=========== Enter in readAssetsDetails()========= applicationId ==> {} ", applicationId );
		try {
			AssetsDetailsExcelReader.run(storageDetailsId, sheet, loanApplicationRepository.findOne(applicationId),
					assetsDetailsRepository);
		} catch (ExcelException e) {
			logger.error("Error/Exception while saving assets detail from excel to db MSG -->{} " , e);
			throw e;
		}
	}

	@Override
	public void readAssetsDetails(Long applicationId,Long proposalMappingId, Long storageDetailsId, XSSFSheet sheet) throws ExcelException {
		logger.info("=========== Enter in readAssetsDetails() =========== applicationId ==> {} proposalMappingId==> {} ", applicationId , proposalMappingId);
		LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(applicationId);
		ApplicationProposalMapping applicationProposalMapping = applicationProposalMappingRepository.findOne(proposalMappingId);
		try {
			AssetsDetailsExcelReader.run(storageDetailsId, sheet, loanApplicationMaster,applicationProposalMapping,
					assetsDetailsRepository);
		} catch (ExcelException e) {
			logger.error("Error/Exception while saving assets detail from excel to db MSG -->{} " , e);
			throw e;
		}
	}

	@Override
	public void inActiveAssetsDetails(Long storageDetailsId) {
		logger.info("in asset service");
		assetsDetailsRepository.inActiveAssetsDetails(storageDetailsId);
		logger.info("out from asset service");

	}

}
