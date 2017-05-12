package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.repository.fundseeker.corporate.EmployeesCategoryBreaksDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.KeyManagementDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.ManagementDetailService;
import com.capitaworld.service.loans.utils.dpr.DprSecondSheetExcelReader;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class KeyManagementDetailServiceImpl implements ManagementDetailService{

	@Autowired
	LoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	KeyManagementDetailRepository keyManagementDetailRepository ;
	
	@Autowired
	EmployeesCategoryBreaksDetailRepository employeesCategoryBreaksDetailRepository; 
	
	@Override
	public void readManagementDetails(Long applicationId, Long storageDetailsId, FileInputStream file,
			XSSFSheet keyManagementSheet) {

		DprSecondSheetExcelReader.run(storageDetailsId, keyManagementSheet,
				loanApplicationRepository.findOne(applicationId), keyManagementDetailRepository,employeesCategoryBreaksDetailRepository);

	}

	@Override
	public void inActiveManagementDetails(Long storageDetailsId) {
		keyManagementDetailRepository.inActiveKeyManagementDetails(storageDetailsId);
		employeesCategoryBreaksDetailRepository.inActiveemployeesCategoryBreaksDetails(storageDetailsId);

	}

}
