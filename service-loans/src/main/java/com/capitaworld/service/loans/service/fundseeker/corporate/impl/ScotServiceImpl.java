package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ScotAnalysisDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.ScotService;
import com.capitaworld.service.loans.utils.dpr.DprTenthSheetExcelReader;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class ScotServiceImpl implements ScotService {
	
	@Autowired
	LoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	ScotAnalysisDetailRepository scotAnalysisDetailRepository;

	@Override
	public void readScotDetails(Long applicationId, Long storageDetailsId, FileInputStream file, XSSFSheet scotSheet) {
		DprTenthSheetExcelReader.run(storageDetailsId, scotSheet,
				loanApplicationRepository.findOne(applicationId), scotAnalysisDetailRepository);
		
	}

	@Override
	public void inActiveScotDetails(Long storageDetailsId) {
		scotAnalysisDetailRepository.inActiveScotDetails(storageDetailsId);
		
	}

}
