package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.json.simple.JSONObject;

import com.capitaworld.service.loans.domain.fundseeker.corporate.AssetsDetails;

public interface AssetsDetailsService {

	public void saveOrUpdate(AssetsDetails assetsDetails);
	
	public JSONObject readAssetsDetails(FileInputStream file,XSSFSheet sheet);
}
