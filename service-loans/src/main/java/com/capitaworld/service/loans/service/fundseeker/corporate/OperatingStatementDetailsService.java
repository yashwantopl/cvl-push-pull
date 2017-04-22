package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.capitaworld.service.loans.domain.fundseeker.corporate.OperatingStatementDetails;

public interface OperatingStatementDetailsService {

	public void saveOrUpdate(OperatingStatementDetails operatingStatementDetails);
	
	public void readOperatingStatementDetails(Long applicationId,Long storageDetailsId,FileInputStream file,XSSFSheet sheet);
	
	public void inActiveAssetsDetails(Long storageDetailsId);
}
