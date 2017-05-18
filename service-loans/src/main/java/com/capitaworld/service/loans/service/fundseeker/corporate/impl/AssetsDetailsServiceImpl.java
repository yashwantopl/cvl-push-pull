package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.fundseeker.corporate.AssetsDetails;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AssetsDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.AssetsDetailsService;
import com.capitaworld.service.loans.utils.cma.AssetsDetailsExcelReader;

@Service
public class AssetsDetailsServiceImpl implements AssetsDetailsService {

	@Autowired
	AssetsDetailsRepository assetsDetailsRepository;

	@Autowired
	LoanApplicationRepository loanApplicationRepository;

	@Override
	public void saveOrUpdate(AssetsDetails assetsDetails) {
		// TODO Auto-generated method stub
		assetsDetailsRepository.save(assetsDetails);
	}

	@Override
	public void readAssetsDetails(Long applicationId, Long storageDetailsId, FileInputStream file, XSSFSheet sheet) {
		// TODO Auto-generated method stub
		AssetsDetailsExcelReader.run(storageDetailsId, sheet, loanApplicationRepository.findOne(applicationId),
				assetsDetailsRepository);

	}

	@Override
	public void inActiveAssetsDetails(Long storageDetailsId) {
		// TODO Auto-generated method stub
		assetsDetailsRepository.inActiveAssetsDetails(storageDetailsId);

	}

}
