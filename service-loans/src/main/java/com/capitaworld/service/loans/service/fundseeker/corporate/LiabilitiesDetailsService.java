package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.exceptions.ExcelException;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.capitaworld.service.loans.domain.fundseeker.corporate.LiabilitiesDetails;

public interface LiabilitiesDetailsService {

	public void saveOrUpdate(LiabilitiesDetails liabilitiesDetails);
	
	public void readLiabilitiesDetails(Long applicationId,Long storageDetailsId,XSSFSheet sheet) throws ExcelException;
	
	public void inActiveAssetsDetails(Long storageDetailsId);
}
