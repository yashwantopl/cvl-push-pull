package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.exceptions.ExcelException;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.capitaworld.service.loans.domain.fundseeker.corporate.OperatingStatementDetails;

public interface OperatingStatementDetailsService {

	public void saveOrUpdate(OperatingStatementDetails operatingStatementDetails);
	
	public void readOperatingStatementDetails(Long applicationId,Long storageDetailsId,XSSFSheet sheet)  throws ExcelException;
	
	public void inActiveAssetsDetails(Long storageDetailsId);
}
