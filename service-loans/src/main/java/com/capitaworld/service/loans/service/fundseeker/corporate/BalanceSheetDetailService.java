package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.capitaworld.service.loans.domain.fundseeker.corporate.BalanceSheetDetail;

public interface BalanceSheetDetailService {

	public void saveOrUpdate(BalanceSheetDetail balanceSheetDetail);
	
	public void readBalanceSheetDetails(Long applicationId,Long storageDetailsId,FileInputStream file,XSSFSheet sheet);
	
	public void inActiveBalanceSheetDetail(Long storageDetailsId);
}
