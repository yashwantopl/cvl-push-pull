package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.DprUserDataDetail;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AssetsDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LiabilitiesDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.OperatingStatementDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.AssetsDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.DprUserDataDetailService;
import com.capitaworld.service.loans.service.fundseeker.corporate.EntityInformationDetailService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ExcelExtractionService;
import com.capitaworld.service.loans.service.fundseeker.corporate.FeasibilityService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LiabilitiesDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ManagementDetailService;
import com.capitaworld.service.loans.service.fundseeker.corporate.MarketPositioningService;
import com.capitaworld.service.loans.service.fundseeker.corporate.MarketScenerioService;
import com.capitaworld.service.loans.service.fundseeker.corporate.OperatingStatementDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ProposalService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ResourcesService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ScotService;
import com.capitaworld.service.loans.service.fundseeker.corporate.TechnologyPositioningDetailService;

@Service
@Transactional
public class ExcelExtractionServiceImpl implements ExcelExtractionService{

	
	@Autowired
	OperatingStatementDetailsService OperatingStatementDetailsService;
	
	@Autowired
	LiabilitiesDetailsService liabilitiesDetailsService;
	
	@Autowired
	AssetsDetailsService assetsDetailsService;
	
	@Autowired
	AssetsDetailsRepository assetsDetailsRepository;
	
	@Autowired
	LiabilitiesDetailsRepository liabilitiesDetailsRepository; 
	
	@Autowired
	OperatingStatementDetailsRepository operatingStatementDetailsRepository;
	
	@Autowired
	EntityInformationDetailService entityInformationDetailService;
	
	@Autowired
	ManagementDetailService managementDetailService;
	
	@Autowired
	DprUserDataDetailService dprUserDataDetailService;
	
	@Autowired
	TechnologyPositioningDetailService technologyPositioningDetailService;
	
	@Autowired
	MarketScenerioService marketScenerioService;
	
	@Autowired
	MarketPositioningService marketPositioningService;
	
	@Autowired
	ResourcesService resourcesService;
	
	@Autowired
	ProposalService proposalService;
	
	@Autowired
	FeasibilityService feasibilityService;
	
	@Autowired
	ScotService scotService;
	
	
	public Boolean readCMA(Long applicationId,Long storageDetailsId,String filePath) {
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
	         
	         OperatingStatementDetailsService.readOperatingStatementDetails(applicationId,storageDetailsId,file,operatingStatementSheet);
	         liabilitiesDetailsService.readLiabilitiesDetails(applicationId,storageDetailsId,file, liabilitiesSheet);
	         assetsDetailsService.readAssetsDetails(applicationId,storageDetailsId,file, assetsSheet);
		}
		catch (Exception e) {
			// TODO: handle exception
			
			assetsDetailsRepository.inActiveAssetsDetails((long) storageDetailsId);
			liabilitiesDetailsRepository.inActiveAssetsDetails((long) storageDetailsId);
			operatingStatementDetailsRepository.inActiveAssetsDetails((long) storageDetailsId);
			
			return false;
		}
		
		return true;
	}


	@Override
	public Boolean readDPR(Long applicationId, Long storageDetailsId, String filePath) {
		
		FileInputStream file;
		XSSFWorkbook workbook;
		XSSFSheet entityInfoSheet,keyManagementSheet,productsSheet,technologySheet,marketScenerioSheet,
						marketPositioningSheet,resourcesSheet, proposalSheet,feasibilitySheet, scotSheet;
		
		try{
			 file = new FileInputStream(new File(filePath));
			 workbook = new XSSFWorkbook(file);
			 
			 entityInfoSheet  = workbook.getSheetAt(0);//pass DPR sheet to function
	         keyManagementSheet  = workbook.getSheetAt(1);//pass DPR sheet to function
	         productsSheet  = workbook.getSheetAt(2);//pass DPR sheet to function
	         technologySheet= workbook.getSheetAt(3);//pass DPR sheet to function
	         marketScenerioSheet= workbook.getSheetAt(4);//pass DPR sheet to function
	         marketPositioningSheet= workbook.getSheetAt(5);//pass DPR sheet to function
	         resourcesSheet= workbook.getSheetAt(6);//pass DPR sheet to function
	         proposalSheet= workbook.getSheetAt(7);//pass DPR sheet to function
	         feasibilitySheet= workbook.getSheetAt(8);//pass DPR sheet to function
	         scotSheet= workbook.getSheetAt(9);//pass DPR sheet to function
	         
	         DprUserDataDetail dprUserDataDetail = new DprUserDataDetail(); 
	         
			 entityInformationDetailService.readEntityInformationDetails(applicationId,storageDetailsId,file,entityInfoSheet);
			 managementDetailService.readManagementDetails(applicationId,storageDetailsId,file,keyManagementSheet);
			 dprUserDataDetailService.readDprUserDataDetails(applicationId,storageDetailsId,file,productsSheet, dprUserDataDetail);
			 technologyPositioningDetailService.readtechnologyPositioningDetail(applicationId,storageDetailsId,file,technologySheet, dprUserDataDetail);
			 marketScenerioService.readMarketScenerioDetails(applicationId,storageDetailsId,file,marketScenerioSheet, dprUserDataDetail);
			 marketPositioningService.readMarketPositioningDetails(applicationId,storageDetailsId,file,marketPositioningSheet, dprUserDataDetail);
			 resourcesService.readResourcesDetails(applicationId,storageDetailsId,file,resourcesSheet, dprUserDataDetail);
			 proposalService.readProposalDetails(applicationId,storageDetailsId,file,proposalSheet, dprUserDataDetail);
			 feasibilityService.readFeasibilityDetails(applicationId,storageDetailsId,file,feasibilitySheet, dprUserDataDetail);
			 scotService.readScotDetails(applicationId,storageDetailsId,file,scotSheet);
			 dprUserDataDetailService.save(storageDetailsId,dprUserDataDetail);
		}
		catch (Exception e) {
			
			entityInformationDetailService.inActiveEntityInformationDetails(storageDetailsId);
			managementDetailService.inActiveManagementDetails(storageDetailsId);
			technologyPositioningDetailService.inActiveTechnologyPositioningDetails(storageDetailsId);
			marketPositioningService.inActiveMarketPositioningDetails(storageDetailsId);
			proposalService.inActiveProposalDetails(storageDetailsId);
			feasibilityService.inActiveFeasibilityDetails(storageDetailsId);
			scotService.inActiveScotDetails(storageDetailsId);
			dprUserDataDetailService.inActiveDprUserDataDetails(storageDetailsId);
			return false;
		}
		
		return true;
	}

}
