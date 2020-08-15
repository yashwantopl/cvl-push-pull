package com.opl.service.loans.service.fundseeker.corporate.impl;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opl.mudra.api.loans.exception.ExcelException;
import com.opl.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.opl.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.opl.service.loans.domain.fundseeker.corporate.OperatingStatementDetails;
import com.opl.service.loans.repository.fundseeker.corporate.ApplicationProposalMappingRepository;
import com.opl.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.opl.service.loans.repository.fundseeker.corporate.OperatingStatementDetailsRepository;
import com.opl.service.loans.service.fundseeker.corporate.OperatingStatementDetailsService;
import com.opl.service.loans.utils.cma.OperatingStatementDetailsExcelReader;

@Service
public class OperatingStatementDetailsServiceImpl implements OperatingStatementDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(OperatingStatementDetailsServiceImpl.class);
	
	@Autowired
	OperatingStatementDetailsRepository operatingStatementDetailsRepository;

	@Autowired
	LoanApplicationRepository loanApplicationRepository;

	@Autowired
	ApplicationProposalMappingRepository applicationProposalMappingRepository;

	@Override
	public void saveOrUpdate(OperatingStatementDetails operatingStatementDetails) {
		operatingStatementDetailsRepository.save(operatingStatementDetails);

	}

	@Override
	public void readOperatingStatementDetails(Long applicationId,Long proposalMappingId, Long storageDetailsId,
											  XSSFSheet sheet) throws ExcelException  {
		logger.info("=========== Enter in readOperatingStatementDetails()========= applicationId ==> {} proposalMappingId ==> {} ", applicationId  , proposalMappingId);
		LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(applicationId);
		ApplicationProposalMapping applicationProposalMapping  = applicationProposalMappingRepository.findOne(proposalMappingId);
		try {
			OperatingStatementDetailsExcelReader.run(storageDetailsId, sheet,
					loanApplicationMaster,applicationProposalMapping, operatingStatementDetailsRepository);
		} catch (ExcelException e) {
			logger.error("Error/Exception while saving Operating Statement Details from excel to db MSG -->{} " , e);
			throw e;
		}

	}

	@Override
	public void readOperatingStatementDetails(Long applicationId, Long storageDetailsId,
			XSSFSheet sheet) throws ExcelException {
		logger.info("=========== Enter in readOperatingStatementDetails()========= applicationId ==> {} ", applicationId );
		try {
			OperatingStatementDetailsExcelReader.run(storageDetailsId, sheet,
					loanApplicationRepository.findOne(applicationId), operatingStatementDetailsRepository);
		} catch (ExcelException e) {
			logger.error("Error/Exception while saving Operating Statement Details from excel to db MSG -->{} " , e);
			throw e;
		}

	}

	@Override
	public void inActiveAssetsDetails(Long storageDetailsId) {
		operatingStatementDetailsRepository.inActiveAssetsDetails(storageDetailsId);

	}

}
