package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.service.fundseeker.corporate.AssetsDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ExcelExtractionService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LiabilitiesDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.OperatingStatementDetailsService;

@Service
public class ExcelExtractionServiceImpl implements ExcelExtractionService{

	
	@Autowired
	OperatingStatementDetailsService OperatingStatementDetailsService;
	
	@Autowired
	LiabilitiesDetailsService liabilitiesDetailsService;
	
	@Autowired
	AssetsDetailsService assetsDetailsService;
	
	@Override
	public Boolean readCMA(String filePath) {
		// TODO Auto-generated method stub
		
		FileInputStream file;
		XSSFWorkbook workbook;
		XSSFSheet operatingStatementSheet,liabilitiesSheet,assetsSheet;
		
		try{
			 file = new FileInputStream(new File(filePath));
			 workbook = new XSSFWorkbook(file);
			 
	         operatingStatementSheet  = workbook.getSheetAt(0);//pass DPR sheet to function
	         liabilitiesSheet  = workbook.getSheetAt(1);//pass DPR sheet to function
	         assetsSheet  = workbook.getSheetAt(2);//pass DPR sheet to function
	         
	         OperatingStatementDetailsService.readOperatingStatementDetails(file,operatingStatementSheet);
	         liabilitiesDetailsService.readLiabilitiesDetails(file, liabilitiesSheet);
	         assetsDetailsService.readAssetsDetails(file, assetsSheet);
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
		return true;
	}

}
