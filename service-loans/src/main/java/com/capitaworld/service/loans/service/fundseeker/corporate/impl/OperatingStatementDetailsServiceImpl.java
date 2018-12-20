package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.fundseeker.corporate.OperatingStatementDetails;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.OperatingStatementDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.OperatingStatementDetailsService;
import com.capitaworld.service.loans.utils.cma.OperatingStatementDetailsExcelReader;

@Service
public class OperatingStatementDetailsServiceImpl implements OperatingStatementDetailsService {

	@Autowired
	OperatingStatementDetailsRepository operatingStatementDetailsRepository;

	@Autowired
	LoanApplicationRepository loanApplicationRepository;

	@Override
	public void saveOrUpdate(OperatingStatementDetails operatingStatementDetails) {
		operatingStatementDetailsRepository.save(operatingStatementDetails);

	}

	@Override
	public void readOperatingStatementDetails(Long applicationId, Long storageDetailsId,
			XSSFSheet sheet) throws Exception {

		OperatingStatementDetailsExcelReader.run(storageDetailsId, sheet,
				loanApplicationRepository.findOne(applicationId), operatingStatementDetailsRepository);

	}

	@Override
	public void inActiveAssetsDetails(Long storageDetailsId) {
		operatingStatementDetailsRepository.inActiveAssetsDetails(storageDetailsId);

	}

}
