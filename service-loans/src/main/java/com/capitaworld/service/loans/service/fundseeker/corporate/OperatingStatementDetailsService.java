package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.json.simple.JSONObject;

import com.capitaworld.service.loans.domain.fundseeker.corporate.OperatingStatementDetails;

public interface OperatingStatementDetailsService {

	public void saveOrUpdate(OperatingStatementDetails operatingStatementDetails);
	
	public void readOperatingStatementDetails(FileInputStream file,XSSFSheet sheet);
}
