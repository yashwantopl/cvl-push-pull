package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.DprUserDataDetail;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.TechnologyPositioningDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.TechnologyPositioningDetailService;
import com.capitaworld.service.loans.utils.dpr.DprFourthSheetExcelReader;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class TechnologyPositioningDetailServiceImpl implements TechnologyPositioningDetailService {
	
	@Autowired
	TechnologyPositioningDetailRepository technologyPositioningDetailRepository; 
	
	@Autowired
	LoanApplicationRepository loanApplicationRepository; 

	@Override
	public void readtechnologyPositioningDetail(Long applicationId, Long storageDetailsId,
			XSSFSheet technologySheet, DprUserDataDetail dprUserDataDetail) {

		DprFourthSheetExcelReader.run(storageDetailsId, technologySheet,
				loanApplicationRepository.findOne(applicationId),technologyPositioningDetailRepository, dprUserDataDetail);

	}

	@Override
	public void inActiveTechnologyPositioningDetails(Long storageDetailsId) {
		technologyPositioningDetailRepository.inActiveTechnologyPositioningDetails(storageDetailsId);
		
		}

}
