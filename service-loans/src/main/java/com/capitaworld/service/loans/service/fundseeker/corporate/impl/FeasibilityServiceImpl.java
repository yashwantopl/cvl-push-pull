package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.DprUserDataDetail;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AvailabilityProposedPlantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CapacityDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.RequirementsAndAvailabilityRawMaterialsDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.FeasibilityService;
import com.capitaworld.service.loans.utils.dpr.DprNinthSheetExcelReader;

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
	public void readFeasibilityDetails(Long applicationId, Long storageDetailsId, FileInputStream file,
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
