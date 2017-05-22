package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.capitaworld.service.loans.domain.fundseeker.corporate.DprUserDataDetail;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AssetsDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.BalanceSheetDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LiabilitiesDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.OperatingStatementDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ProfitibilityStatementDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.AssetsDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.BalanceSheetDetailService;
import com.capitaworld.service.loans.service.fundseeker.corporate.DprUserDataDetailService;
import com.capitaworld.service.loans.service.fundseeker.corporate.EntityInformationDetailService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ExcelExtractionService;
import com.capitaworld.service.loans.service.fundseeker.corporate.FeasibilityService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LiabilitiesDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ManagementDetailService;
import com.capitaworld.service.loans.service.fundseeker.corporate.MarketPositioningService;
import com.capitaworld.service.loans.service.fundseeker.corporate.MarketScenerioService;
import com.capitaworld.service.loans.service.fundseeker.corporate.OperatingStatementDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ProfitibilityStatementDetailService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ProposalService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ResourcesService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ScotService;
import com.capitaworld.service.loans.service.fundseeker.corporate.TechnologyPositioningDetailService;

@Service
@Transactional
public class ExcelExtractionServiceImpl implements ExcelExtractionService{

	private final Logger log = LoggerFactory.getLogger(ExcelExtractionService.class);
	
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
	
	@Autowired
	BalanceSheetDetailService balanceSheetDetailService;
	
	@Autowired
	ProfitibilityStatementDetailService profitibilityStatementDetailService;
	
	@Autowired
	BalanceSheetDetailRepository balanceSheetDetailRepository;
	
	@Autowired
	ProfitibilityStatementDetailRepository profitibilityStatementDetailRepository;
	
	@Autowired
	OperatingStatementDetailsService operatingStatementDetailsService; 
	
	
	public Boolean readCMA(Long applicationId,Long storageDetailsId,MultipartFile multipartFile) {
		// TODO Auto-generated method stub
		
		InputStream file;
		XSSFWorkbook workbook;
		XSSFSheet operatingStatementSheet,liabilitiesSheet,assetsSheet;
		
		
		
		try{
			 file = new ByteArrayInputStream(multipartFile.getBytes());
			 workbook = new XSSFWorkbook(file);
			 
	         operatingStatementSheet  = workbook.getSheetAt(0);//pass DPR sheet to function
	         liabilitiesSheet  = workbook.getSheetAt(1);//pass DPR sheet to function
	         assetsSheet  = workbook.getSheetAt(2);//pass DPR sheet to function
	         
	         OperatingStatementDetailsService.readOperatingStatementDetails(applicationId,storageDetailsId,operatingStatementSheet);
	         liabilitiesDetailsService.readLiabilitiesDetails(applicationId,storageDetailsId, liabilitiesSheet);
	         assetsDetailsService.readAssetsDetails(applicationId,storageDetailsId, assetsSheet);
		}
		catch (Exception e) {
			// TODO: handle exception
			
			assetsDetailsRepository.inActiveAssetsDetails(storageDetailsId);
			liabilitiesDetailsRepository.inActiveAssetsDetails(storageDetailsId);
			operatingStatementDetailsRepository.inActiveAssetsDetails(storageDetailsId);
			log.error("Error while reading CMA");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public Boolean readDPR(Long applicationId, Long storageDetailsId, MultipartFile multipartFile) {
		
		InputStream file;
		XSSFWorkbook workbook;
		XSSFSheet entityInfoSheet,keyManagementSheet,productsSheet,technologySheet,marketScenerioSheet,
						marketPositioningSheet,resourcesSheet, proposalSheet,feasibilitySheet, scotSheet;
		
		try{
			
			 file = new ByteArrayInputStream(multipartFile.getBytes());
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
	         
			 entityInformationDetailService.readEntityInformationDetails(applicationId,storageDetailsId,entityInfoSheet);
			 managementDetailService.readManagementDetails(applicationId,storageDetailsId,keyManagementSheet);
			 dprUserDataDetailService.readDprUserDataDetails(applicationId,storageDetailsId,productsSheet, dprUserDataDetail);
			 technologyPositioningDetailService.readtechnologyPositioningDetail(applicationId,storageDetailsId,technologySheet, dprUserDataDetail);
			 marketScenerioService.readMarketScenerioDetails(applicationId,storageDetailsId,marketScenerioSheet, dprUserDataDetail);
			 marketPositioningService.readMarketPositioningDetails(applicationId,storageDetailsId,marketPositioningSheet, dprUserDataDetail);
			 resourcesService.readResourcesDetails(applicationId,storageDetailsId,resourcesSheet, dprUserDataDetail);
			 proposalService.readProposalDetails(applicationId,storageDetailsId,proposalSheet, dprUserDataDetail);
			 feasibilityService.readFeasibilityDetails(applicationId,storageDetailsId,feasibilitySheet, dprUserDataDetail);
			 scotService.readScotDetails(applicationId,storageDetailsId,scotSheet);
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
			log.error("Error while reading DPR");
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	
	@Override
	public Boolean readBS(Long applicationId, Long storageDetailsId, MultipartFile multipartFile) {
		// TODO Auto-generated method stub
		InputStream file;
		XSSFWorkbook workbook;
		XSSFSheet balanceSheet,profitibilityStatementSheet;
		
		try{
			 file = new ByteArrayInputStream(multipartFile.getBytes());
			 workbook = new XSSFWorkbook(file);
			 
			 balanceSheet  = workbook.getSheetAt(0);//pass BS sheet to function
			 profitibilityStatementSheet  = workbook.getSheetAt(1);//pass BS sheet to function
	         
			 balanceSheetDetailService.readBalanceSheetDetails(applicationId, storageDetailsId, balanceSheet);
			 profitibilityStatementDetailService.readProfitibilityStatementDetail(applicationId, storageDetailsId, profitibilityStatementSheet);
		}
		catch (Exception e) {
			// TODO: handle exception
			
			balanceSheetDetailRepository.inActiveBalanceSheetDetail(storageDetailsId);
			profitibilityStatementDetailRepository.inActiveProfitibilityStatementDetail(storageDetailsId);
			log.error("Error while reading BS");
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}

	@Override
	public Boolean inActiveCMA(Long storageDetailsId) {
		// TODO Auto-generated method stub
		try {
			assetsDetailsService.inActiveAssetsDetails(storageDetailsId);
			liabilitiesDetailsService.inActiveAssetsDetails(storageDetailsId);
			operatingStatementDetailsService.inActiveAssetsDetails(storageDetailsId);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error while inactive CMA");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Boolean inActiveDPR(Long storageDetailsId) {
		// TODO Auto-generated method stub
		try {
			entityInformationDetailService.inActiveEntityInformationDetails(storageDetailsId);
			managementDetailService.inActiveManagementDetails(storageDetailsId);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error while inactive DPR");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Boolean inActiveBS(Long storageDetailsId) {
		// TODO Auto-generated method stub
		try {
			balanceSheetDetailService.inActiveBalanceSheetDetail(storageDetailsId);
			profitibilityStatementDetailService.inActiveProfitibilityStatementDetail(storageDetailsId);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error while inactive BS");
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
