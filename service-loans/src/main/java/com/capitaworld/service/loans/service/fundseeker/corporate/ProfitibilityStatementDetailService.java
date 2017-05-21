package com.capitaworld.service.loans.service.fundseeker.corporate;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.capitaworld.service.loans.domain.fundseeker.corporate.ProfitibilityStatementDetail;

public interface ProfitibilityStatementDetailService {

	public void saveOrUpdate(ProfitibilityStatementDetail profitibilityStatementDetail);
	
	public void readProfitibilityStatementDetail(Long applicationId,Long storageDetailsId,XSSFSheet sheet);
	
	public void inActiveProfitibilityStatementDetail(Long storageDetailsId);
	
}
