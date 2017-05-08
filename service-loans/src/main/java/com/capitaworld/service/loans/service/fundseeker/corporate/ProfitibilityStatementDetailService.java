package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.capitaworld.service.loans.domain.fundseeker.corporate.ProfitibilityStatementDetail;

public interface ProfitibilityStatementDetailService {

	public void saveOrUpdate(ProfitibilityStatementDetail profitibilityStatementDetail);
	
	public void readProfitibilityStatementDetail(Long applicationId,Long storageDetailsId,FileInputStream file,XSSFSheet sheet);
	
	public void inActiveProfitibilityStatementDetail(Long storageDetailsId);
	
}
