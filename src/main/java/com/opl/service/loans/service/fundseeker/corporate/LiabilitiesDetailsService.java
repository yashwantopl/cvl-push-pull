package com.opl.service.loans.service.fundseeker.corporate;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.opl.mudra.api.loans.exception.ExcelException;
import com.opl.service.loans.domain.fundseeker.corporate.LiabilitiesDetails;

public interface LiabilitiesDetailsService {

	public void saveOrUpdate(LiabilitiesDetails liabilitiesDetails);
	
	public void readLiabilitiesDetails(Long applicationId,Long storageDetailsId,XSSFSheet sheet) throws ExcelException;

	public void readLiabilitiesDetails(Long applicationId,Long proposalMappingId,Long storageDetailsId,XSSFSheet sheet)throws ExcelException;

	public void inActiveAssetsDetails(Long storageDetailsId);
}
