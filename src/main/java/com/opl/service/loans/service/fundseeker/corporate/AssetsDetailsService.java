package com.opl.service.loans.service.fundseeker.corporate;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.opl.mudra.api.loans.exception.ExcelException;
import com.opl.service.loans.domain.fundseeker.corporate.AssetsDetails;

public interface AssetsDetailsService {

	public void saveOrUpdate(AssetsDetails assetsDetails);
	
	public void readAssetsDetails(Long applicationId,Long storageDetailsId,XSSFSheet sheet) throws ExcelException;

	public void readAssetsDetails(Long applicationId,Long applicationProposalId,Long storageDetailsId,XSSFSheet sheet)throws ExcelException ;

	public void inActiveAssetsDetails(Long storageDetailsId);
}
