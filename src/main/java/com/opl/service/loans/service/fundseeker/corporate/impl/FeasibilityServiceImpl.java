package com.opl.service.loans.service.fundseeker.corporate.impl;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opl.service.loans.domain.fundseeker.corporate.DprUserDataDetail;
import com.opl.service.loans.repository.fundseeker.corporate.AvailabilityProposedPlantDetailRepository;
import com.opl.service.loans.repository.fundseeker.corporate.CapacityDetailRepository;
import com.opl.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.opl.service.loans.repository.fundseeker.corporate.RequirementsAndAvailabilityRawMaterialsDetailRepository;
import com.opl.service.loans.service.fundseeker.corporate.FeasibilityService;
import com.opl.service.loans.utils.dpr.DprNinthSheetExcelReader;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class FeasibilityServiceImpl implements FeasibilityService {
	
	@Autowired
	LoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	CapacityDetailRepository capacityDetailRepository;
	
	@Autowired
	AvailabilityProposedPlantDetailRepository availabilityProposedPlantDetailRepository;
	
	@Autowired
	RequirementsAndAvailabilityRawMaterialsDetailRepository requirementsAndAvailabilityRawMaterialsDetailRepository; 

	@Override
	public void readFeasibilityDetails(Long applicationId, Long storageDetailsId,
			XSSFSheet feasibilitySheet, DprUserDataDetail dprUserDataDetail) {

		DprNinthSheetExcelReader.run(storageDetailsId, feasibilitySheet,
				loanApplicationRepository.findOne(applicationId), capacityDetailRepository,availabilityProposedPlantDetailRepository,requirementsAndAvailabilityRawMaterialsDetailRepository,dprUserDataDetail);

		
	}

	@Override
	public void inActiveFeasibilityDetails(Long storageDetailsId) {
		
		capacityDetailRepository.inActiveCapacityDetails(storageDetailsId);
		availabilityProposedPlantDetailRepository.inActiveAvailabilityProposedPlantDetails(storageDetailsId);
		requirementsAndAvailabilityRawMaterialsDetailRepository.inActiveRequirementsAndAvailabilityRawMaterialsDetails(storageDetailsId);
		
	}

}
