package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.fundseeker.corporate.ProfitibilityStatementDetail;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ProfitibilityStatementDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.ProfitibilityStatementDetailService;
import com.capitaworld.service.loans.utils.bs.ProfitabilityStatementExcelReader;

@Service
public class ProfitibilityStatementDetailServiceImpl implements ProfitibilityStatementDetailService{

	@Autowired
	ProfitibilityStatementDetailRepository profitibilityStatementDetailRepository;
	
	@Autowired
	LoanApplicationRepository loanApplicationRepository;
	
	@Override
	public void saveOrUpdate(ProfitibilityStatementDetail profitibilityStatementDetail) {
		// TODO Auto-generated method stub
		profitibilityStatementDetailRepository.save(profitibilityStatementDetail);
		
	}

	@Override
	public void readProfitibilityStatementDetail(Long applicationId, Long storageDetailsId,
			XSSFSheet sheet) {
		// TODO Auto-generated method stub
		ProfitabilityStatementExcelReader.run(storageDetailsId, sheet, loanApplicationRepository.findOne(applicationId), profitibilityStatementDetailRepository);
	}

	@Override
	public void inActiveProfitibilityStatementDetail(Long storageDetailsId) {
		// TODO Auto-generated method stub
		profitibilityStatementDetailRepository.inActiveProfitibilityStatementDetail(storageDetailsId);
		
	}

}
