package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.fundseeker.corporate.BalanceSheetDetail;
import com.capitaworld.service.loans.repository.fundseeker.corporate.BalanceSheetDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.BalanceSheetDetailService;
import com.capitaworld.service.loans.utils.bs.BalanceSheetExcelReader;

@Service
public class BalanceSheetDetailServiceImpl implements BalanceSheetDetailService{

	@Autowired
	BalanceSheetDetailRepository balanceSheetDetailRepository;
	
	@Autowired
	LoanApplicationRepository loanApplicationRepository;
	
	@Override
	public void saveOrUpdate(BalanceSheetDetail balanceSheetDetail) {
		// TODO Auto-generated method stub
		balanceSheetDetailRepository.save(balanceSheetDetail);
	}

	@Override
	public void readBalanceSheetDetails(Long applicationId, Long storageDetailsId, FileInputStream file,
			XSSFSheet sheet) {
		// TODO Auto-generated method stub
		BalanceSheetExcelReader.run(storageDetailsId, sheet, loanApplicationRepository.findOne(applicationId),
				balanceSheetDetailRepository);
		
	}

	@Override
	public void inActiveBalanceSheetDetail(Long storageDetailsId) {
		// TODO Auto-generated method stub
		balanceSheetDetailRepository.inActiveBalanceSheetDetail(storageDetailsId);
	}

}
