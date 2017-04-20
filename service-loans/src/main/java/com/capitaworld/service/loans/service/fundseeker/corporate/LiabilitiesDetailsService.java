package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.json.simple.JSONObject;

import com.capitaworld.service.loans.domain.fundseeker.corporate.LiabilitiesDetails;

public interface LiabilitiesDetailsService {

	public void saveOrUpdate(LiabilitiesDetails liabilitiesDetails);
	
	public void readLiabilitiesDetails(Long applicationId,Long storageDetailsId,FileInputStream file,XSSFSheet sheet);
	
	public void inActiveAssetsDetails(Long storageDetailsId);
}
