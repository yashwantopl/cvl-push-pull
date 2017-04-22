package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.capitaworld.service.loans.domain.fundseeker.corporate.AssetsDetails;

public interface AssetsDetailsService {

	public void saveOrUpdate(AssetsDetails assetsDetails);
	
	public void readAssetsDetails(Long applicationId,Long storageDetailsId,FileInputStream file,XSSFSheet sheet);
	
	public void inActiveAssetsDetails(Long storageDetailsId);
}
