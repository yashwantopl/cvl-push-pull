package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.fundseeker.corporate.AssetsDetails;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AssetsDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.AssetsDetailsService;
import com.capitaworld.service.loans.utils.cma.AssetsDetailsExcelReader;

@Service
public class AssetsDetailsServiceImpl implements AssetsDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(AssetsDetailsServiceImpl.class);

	@Autowired
	AssetsDetailsRepository assetsDetailsRepository;

	@Autowired
	LoanApplicationRepository loanApplicationRepository;

	@Override
	public void saveOrUpdate(AssetsDetails assetsDetails) {
		assetsDetailsRepository.save(assetsDetails);
	}

	@Override
	public void readAssetsDetails(Long applicationId, Long storageDetailsId, XSSFSheet sheet) throws Exception{
		AssetsDetailsExcelReader.run(storageDetailsId, sheet, loanApplicationRepository.findOne(applicationId),
				assetsDetailsRepository);

	}

	@Override
	public void inActiveAssetsDetails(Long storageDetailsId) {
		logger.info("in asset service");
		assetsDetailsRepository.inActiveAssetsDetails(storageDetailsId);
		logger.info("out from asset service");

	}

}
