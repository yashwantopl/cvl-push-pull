package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.repository.fundseeker.corporate.BoardOfDirectorsDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.StrategicAlliancesDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.EntityInformationDetailService;
import com.capitaworld.service.loans.utils.dpr.DprFirstSheetExcelReader;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class BoardOfDirectorsDetailServiceImpl implements EntityInformationDetailService{
	
	@Autowired
	private BoardOfDirectorsDetailRepository boardOfDirectorsDetailRepository;
	
	@Autowired
	private StrategicAlliancesDetailRepository strategicAlliancesDetailRepository; 
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository; 

	@Override
	public void readEntityInformationDetails(Long applicationId, Long storageDetailsId,
			XSSFSheet managementSheet) {

		DprFirstSheetExcelReader.run(storageDetailsId, managementSheet,
				loanApplicationRepository.findOne(applicationId), boardOfDirectorsDetailRepository,strategicAlliancesDetailRepository);

	}

	@Override
	public void inActiveEntityInformationDetails(Long storageDetailsId) {
		boardOfDirectorsDetailRepository.inActiveBoardOfDirectorsDetails(storageDetailsId);
		strategicAlliancesDetailRepository.inActiveStrategicAlliancesDetails(storageDetailsId);

	}

}
