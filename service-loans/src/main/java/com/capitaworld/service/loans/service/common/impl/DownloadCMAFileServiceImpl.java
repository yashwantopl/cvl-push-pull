package com.capitaworld.service.loans.service.common.impl;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.domain.fundseeker.corporate.AssetsDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.BalanceSheetDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.LiabilitiesDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.OperatingStatementDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.ProfitibilityStatementDetail;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AssetsDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.BalanceSheetDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LiabilitiesDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.OperatingStatementDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ProfitibilityStatementDetailRepository;
import com.capitaworld.service.loans.service.common.DownLoadCMAFileService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class DownloadCMAFileServiceImpl implements DownLoadCMAFileService {

	@Autowired
	private Environment environment;
	@Autowired
	private LiabilitiesDetailsRepository liabilitiesDetailsRepository;

	@Autowired
	private AssetsDetailsRepository assetsDetailsRepository;

	@Autowired
	private OperatingStatementDetailsRepository operatingStatementDetailsRepository;

	@Autowired
	private ProfitibilityStatementDetailRepository profitibilityStatementDetailRepository;
	
	@Autowired
	BalanceSheetDetailRepository balanceSheetDetailRepository;
	
	@Autowired
	private LoanApplicationRepository loanApplocationRepo;

	
	private FormulaEvaluator evaluator;

	private static final Logger logger = LoggerFactory.getLogger(DownloadCMAFileServiceImpl.class);
    
	private static final String profitibilitySheet="PS";
	
	private static final String balanceSheet="BS";
	@Override
	public Workbook cmaFileGenerator(Long applicationId , Long productDocumentMappingId) {
		logger.info("Enter in cmaFileGenerator()  Forming Excel Data");
		DocumentResponse documentResponse = null;
		Workbook wb = null;
		try {
	             Double total_Column=24.0;
	 			String EXCEL_FILE_LOCATION ="cw.mca.cwtlwctlcmafile.location";
	 			logger.warn("excel file====>>"+EXCEL_FILE_LOCATION);
	 			
	 			Double tenure = loanApplocationRepo.getTenure(applicationId);
	 			tenure = !CommonUtils.isObjectNullOrEmpty(tenure) ? tenure + 1 : 0.0;
	 			
	 			logger.warn("tenure==>>"+tenure);
	 			if(productDocumentMappingId==(long)DocumentAlias.WC_CMA) {
		      		 EXCEL_FILE_LOCATION =	"cw.mca.cwcmafile.location";
		      		 total_Column=0.0;
				}
	 			
			if(productDocumentMappingId==(long)DocumentAlias.USL_CMA) {
	      		 EXCEL_FILE_LOCATION =	"cw.mca.cwcmafile.usl.location";
	      		 total_Column=0.0;
			}
			wb = new XSSFWorkbook(OPCPackage.open(environment.getRequiredProperty(EXCEL_FILE_LOCATION)));
			
			Sheet sheet1 = wb.getSheetAt(0);

			Sheet sheet2 = wb.getSheetAt(1);

			Sheet sheet3 = wb.getSheetAt(2);

			evaluator = wb.getCreationHelper().createFormulaEvaluator();

			List<OperatingStatementDetails> operatingStatementDetailsList = operatingStatementDetailsRepository
					.getByApplicationId(applicationId);
			int j = 1;
			Double temp=0.0;
			
			for (OperatingStatementDetails operatingStatementDetails : operatingStatementDetailsList) {
				
				// save into excel
				temp=Double.valueOf(operatingStatementDetails.getYear()); 
				sheet1.getRow(4).getCell(j).setCellValue(temp);
				sheet1.getRow(7).getCell(j).setCellValue(operatingStatementDetails.getDomesticSales());
				sheet1.getRow(8).getCell(j).setCellValue(operatingStatementDetails.getExportSales());
				sheet1.getRow(9).getCell(j).setCellValue(operatingStatementDetails.getAddOtherRevenueIncome());
				
			    //sheet1.getRow(10).getCell(j).setCellValue(operatingStatementDetails.getTotalCostSales());
				
				sheet1.getRow(12).getCell(j).setCellValue(operatingStatementDetails.getLessExciseDuty());
				sheet1.getRow(13).getCell(j).setCellValue(operatingStatementDetails.getDeductOtherItems());
				//sheet1.getRow(14).getCell(j).setCellValue(operatingStatementDetails.getNetSales());
				
				sheet1.getRow(16).getCell(j).setCellValue(operatingStatementDetails.getPercentageRiseOrFall());

				//sheet1.getRow(19).getCell(j).setCellValue(operatingStatementDetails.getRawMaterials());
				
				sheet1.getRow(20).getCell(j).setCellValue(operatingStatementDetails.getRawMaterialsImported());
				sheet1.getRow(21).getCell(j).setCellValue(operatingStatementDetails.getRawMaterialsIndigenous());
				
				//sheet1.getRow(23).getCell(j).setCellValue(operatingStatementDetails.getOtherSpares());
				
				sheet1.getRow(24).getCell(j).setCellValue(operatingStatementDetails.getOtherSparesImported());
				sheet1.getRow(25).getCell(j).setCellValue(operatingStatementDetails.getOtherSparesIndigenous());
				sheet1.getRow(27).getCell(j).setCellValue(operatingStatementDetails.getPowerAndFuel());
				sheet1.getRow(29).getCell(j).setCellValue(operatingStatementDetails.getDirectLabour());
				sheet1.getRow(31).getCell(j).setCellValue(operatingStatementDetails.getOtherMfgExpenses());
				sheet1.getRow(33).getCell(j).setCellValue(operatingStatementDetails.getDepreciation());
				
				//sheet1.getRow(35).getCell(j).setCellValue(operatingStatementDetails.getSubTotalCostSales());
				
				sheet1.getRow(37).getCell(j).setCellValue(operatingStatementDetails.getAddOperatingStock());
				
				//sheet1.getRow(39).getCell(j).setCellValue(operatingStatementDetails.getSubTotalOfCostSalesAndOperatingStock());
				
				sheet1.getRow(41).getCell(j).setCellValue(operatingStatementDetails.getDeductStockInProcess());
				
				//sheet1.getRow(43).getCell(j).setCellValue(operatingStatementDetails.getProductionCost());
				
				sheet1.getRow(45).getCell(j).setCellValue(operatingStatementDetails.getAddOperatingStockFg());

				//sheet1.getRow(47).getCell(j).setCellValue(operatingStatementDetails.getSubTotalDeductAndCostOfProduction());
				
				sheet1.getRow(49).getCell(j).setCellValue(operatingStatementDetails.getDeductClStockFg());
				
				//sheet1.getRow(51).getCell(j).setCellValue(operatingStatementDetails.getTotalCostSales());
				
				sheet1.getRow(53).getCell(j).setCellValue(operatingStatementDetails.getSellingAndDistributionExpenses());
				sheet1.getRow(55).getCell(j).setCellValue(operatingStatementDetails.getSellingGenlAdmnExpenses());
				
				//sheet1.getRow(57).getCell(j).setCellValue(operatingStatementDetails.getSubTotalCostSalesAndSelling());
				
				//sheet1.getRow(59).getCell(j).setCellValue(operatingStatementDetails.getOpProfitBeforeIntrest());
				
				sheet1.getRow(61).getCell(j).setCellValue(operatingStatementDetails.getInterest());
				
				//sheet1.getRow(63).getCell(j).setCellValue(operatingStatementDetails.getOpProfitAfterInterest());
				
				sheet1.getRow(65).getCell(j).setCellValue(operatingStatementDetails.getAddOtherNonOpIncome());
				
				//sheet1.getRow(66).getCell(j).setCellValue(operatingStatementDetails.getSubTotalOfIncome());
				
				sheet1.getRow(67).getCell(j).setCellValue(operatingStatementDetails.getDeductOtherNonOpExp());
				
				//sheet1.getRow(68).getCell(j).setCellValue(operatingStatementDetails.getSubTotalExpenses());
				
				//sheet1.getRow(69).getCell(j).setCellValue(operatingStatementDetails.getNetofNonOpIncomeOrExpenses());
				
				sheet1.getRow(70).getCell(j).setCellValue(operatingStatementDetails.getExpensesAmortised());
				
				//sheet1.getRow(72).getCell(j).setCellValue(operatingStatementDetails.getProfitBeforeTaxOrLoss());
				
				sheet1.getRow(74).getCell(j).setCellValue(operatingStatementDetails.getProvisionForTaxes());
				sheet1.getRow(76).getCell(j).setCellValue(operatingStatementDetails.getProvisionForDeferredTax());

				//sheet1.getRow(77).getCell(j).setCellValue(operatingStatementDetails.getNetProfitOrLoss());
				
				sheet1.getRow(79).getCell(j).setCellValue(operatingStatementDetails.getEquityDeividendPaidAmt());
				
				//sheet1.getRow(81).getCell(j).setCellValue(operatingStatementDetails.getDividendRate());
				
				//sheet1.getRow(83).getCell(j).setCellValue(operatingStatementDetails.getRetainedProfit());
				
				//sheet1.getRow(85).getCell(j).setCellValue(operatingStatementDetails.getRetainedProfitOrNetProfit());
				
				sheet1.getRow(10).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(10).getCell(j)));
				sheet1.getRow(14).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(14).getCell(j)));
				sheet1.getRow(19).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(19).getCell(j)));
				sheet1.getRow(23).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(23).getCell(j)));
				sheet1.getRow(35).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(35).getCell(j)));
				sheet1.getRow(39).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(39).getCell(j)));
				sheet1.getRow(43).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(43).getCell(j)));
				sheet1.getRow(47).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(47).getCell(j)));
				sheet1.getRow(51).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(51).getCell(j)));
				sheet1.getRow(57).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(57).getCell(j)));
				sheet1.getRow(59).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(59).getCell(j)));
				sheet1.getRow(63).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(63).getCell(j)));
				sheet1.getRow(66).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(66).getCell(j)));
				sheet1.getRow(68).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(68).getCell(j)));
				sheet1.getRow(69).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(69).getCell(j)));
				sheet1.getRow(72).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(72).getCell(j)));
				sheet1.getRow(77).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(77).getCell(j)));
				sheet1.getRow(81).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(81).getCell(j)));
				sheet1.getRow(83).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(83).getCell(j)));
				sheet1.getRow(85).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(85).getCell(j)));
				j++;
                
			}
			//Hide rows dynamically as per tenure in operating statement.
			int k = 1;
			for(;j<=24;j++) {
				if(k<=tenure) {
	
				}
				else {
					 sheet1.setColumnHidden(j,true);
				}
				k++;
			}
			// Operating Stmt. ends              
			setyear(sheet1, temp, operatingStatementDetailsList.size() , total_Column, true);
			
			
			// Liabilities Starts
			List<LiabilitiesDetails> liabilitiesDetailsList = liabilitiesDetailsRepository
					.getByApplicationId(applicationId);
			j = 1;
			for (LiabilitiesDetails liabilitiesDetails : liabilitiesDetailsList) {
				// save in db
                  temp=Double.parseDouble(liabilitiesDetails.getYear());
				// save in excel
				sheet2.getRow(4).getCell(j).setCellValue(temp);
				System.out.println(sheet2.getRow(4).getCell(j).getNumericCellValue());
			    //sheet1.getRow(8).getCell(j).setCellValue(liabilitiesDetails.get);

				sheet2.getRow(10).getCell(j).setCellValue(liabilitiesDetails.getFromApplicationBank());
				sheet2.getRow(11).getCell(j).setCellValue(liabilitiesDetails.getFromOtherBanks());
				sheet2.getRow(12).getCell(j).setCellValue(liabilitiesDetails.getWhichBpAndBd());
				
				//sheet2.getRow(14).getCell(j).setCellValue(liabilitiesDetails.getSubTotalA());
				
				sheet2.getRow(16).getCell(j).setCellValue(liabilitiesDetails.getShortTermBorrowingFromOthers());
				sheet2.getRow(18).getCell(j).setCellValue(liabilitiesDetails.getSundryCreditors());
				sheet2.getRow(20).getCell(j).setCellValue(liabilitiesDetails.getAdvancePaymentsFromCustomers());
				sheet2.getRow(22).getCell(j).setCellValue(liabilitiesDetails.getProvisionalForTaxation());
				sheet2.getRow(24).getCell(j).setCellValue(liabilitiesDetails.getDividendPayable());
				sheet2.getRow(26).getCell(j).setCellValue(liabilitiesDetails.getOtherStatutoryLiability());
				sheet2.getRow(28).getCell(j).setCellValue(liabilitiesDetails.getDepositsOrInstalmentsOfTermLoans());
				sheet2.getRow(31).getCell(j).setCellValue(liabilitiesDetails.getOtherCurrentLiability());
				
				//sheet2.getRow(34).getCell(j).setCellValue(liabilitiesDetails.getSubTotalB());
				
				//sheet2.getRow(36).getCell(j).setCellValue(liabilitiesDetails.getTotalCurrentLiabilities());

				sheet2.getRow(40).getCell(j).setCellValue(liabilitiesDetails.getDebentures());
				sheet2.getRow(42).getCell(j).setCellValue(liabilitiesDetails.getPreferencesShares());
				
				//sheet2.getRow(44).getCell(j).setCellValue(liabilitiesDetails.getTermLoans());

				sheet2.getRow(45).getCell(j).setCellValue(liabilitiesDetails.getTermLiabilitiesSecured());
				sheet2.getRow(46).getCell(j).setCellValue(liabilitiesDetails.getTermLiabilitiesUnsecured());
				sheet2.getRow(48).getCell(j).setCellValue(liabilitiesDetails.getDeferredPaymentsCredits());
				sheet2.getRow(50).getCell(j).setCellValue(liabilitiesDetails.getTermDeposits());
				sheet2.getRow(52).getCell(j).setCellValue(liabilitiesDetails.getOtherTermLiabilies());
				
				//sheet2.getRow(54).getCell(j).setCellValue(liabilitiesDetails.getTotalTermLiabilities());
				
				//sheet2.getRow(56).getCell(j).setCellValue(liabilitiesDetails.getOtherNcl());

				sheet2.getRow(57).getCell(j).setCellValue(liabilitiesDetails.getOtherNclUnsecuredLoansFromPromoters());
				
				sheet2.getRow(58).getCell(j).setCellValue(liabilitiesDetails.getOtherNclUnsecuredLoansFromOther());
				sheet2.getRow(59).getCell(j).setCellValue(liabilitiesDetails.getOtherNclLongTermProvisions());
				sheet2.getRow(60).getCell(j).setCellValue(liabilitiesDetails.getOtherNclOthers());

				//sheet2.getRow(62).getCell(j).setCellValue(liabilitiesDetails.getTotalOutsideLiabilities());
				
				sheet2.getRow(66).getCell(j).setCellValue(liabilitiesDetails.getOrdinarySharesCapital());
				sheet2.getRow(68).getCell(j).setCellValue(liabilitiesDetails.getShareWarrentsOutstanding());
				sheet2.getRow(70).getCell(j).setCellValue(liabilitiesDetails.getMinorityInterest());
				sheet2.getRow(72).getCell(j).setCellValue(liabilitiesDetails.getGeneralReserve());
				sheet2.getRow(74).getCell(j).setCellValue(liabilitiesDetails.getRevaluationReservse());
				sheet2.getRow(76).getCell(j).setCellValue(liabilitiesDetails.getOtherReservse());
				sheet2.getRow(78).getCell(j).setCellValue(liabilitiesDetails.getSurplusOrDeficit());
				sheet2.getRow(80).getCell(j).setCellValue(liabilitiesDetails.getDeferredTaxLiability());
				sheet2.getRow(82).getCell(j).setCellValue(liabilitiesDetails.getOthers());
				
				//sheet2.getRow(84).getCell(j).setCellValue(liabilitiesDetails.getNetWorth());
				
         	    //sheet2.getRow(86).getCell(j).setCellValue(liabilitiesDetails.getTotalLiability());
				
				sheet2.getRow(14).getCell(j).setCellValue(evaluateCellValue(sheet2.getRow(14).getCell(j)));	
				sheet2.getRow(34).getCell(j).setCellValue(evaluateCellValue(sheet2.getRow(34).getCell(j)));
				sheet2.getRow(36).getCell(j).setCellValue(evaluateCellValue(sheet2.getRow(36).getCell(j)));
				sheet2.getRow(44).getCell(j).setCellValue(evaluateCellValue(sheet2.getRow(44).getCell(j)));
				sheet2.getRow(54).getCell(j).setCellValue(evaluateCellValue(sheet2.getRow(54).getCell(j)));
				sheet2.getRow(56).getCell(j).setCellValue(evaluateCellValue(sheet2.getRow(56).getCell(j)));
				sheet2.getRow(62).getCell(j).setCellValue(evaluateCellValue(sheet2.getRow(62).getCell(j)));
				sheet2.getRow(84).getCell(j).setCellValue(evaluateCellValue(sheet2.getRow(84).getCell(j)));
				sheet2.getRow(86).getCell(j).setCellValue(evaluateCellValue(sheet2.getRow(86).getCell(j)));
				
				j++;

			}
			//Hide rows dynamically as per tenure in liabilities.
			int o = 1;
			for(;j<=24;j++) {
				if(o<=tenure) {
				}
				else {
					 sheet2.setColumnHidden(j,true);
				}
				o++;
			}
			// Liabilities Ends
			setyear(sheet2, temp, liabilitiesDetailsList.size(),total_Column, true);

			
			
			// Asset Starts

			List<AssetsDetails> assetsDetailsList = assetsDetailsRepository.getByApplicationId(applicationId);
			j = 1;
			for (AssetsDetails assetsDetails : assetsDetailsList) {
				      temp=Double.parseDouble(assetsDetails.getYear());
				// save in excel
				sheet3.getRow(4).getCell(j).setCellValue(temp);
				System.out.println(sheet3.getRow(4).getCell(j).getNumericCellValue());
				 sheet3.getRow(8).getCell(j).setCellValue(assetsDetails.getCashAndBankBalance());
				 
				//sheet3.getRow(10).getCell(j).setCellValue(assetsDetails.getInvestments());
				 
				 sheet3.getRow(12).getCell(j).setCellValue(assetsDetails.getGovernmentAndOtherTrustee());
				sheet3.getRow(14).getCell(j).setCellValue(assetsDetails.getFixedDepositsWithBanks());
				sheet3.getRow(15).getCell(j).setCellValue(assetsDetails.getReceivableOtherThanDefferred());
				sheet3.getRow(17).getCell(j).setCellValue(assetsDetails.getExportReceivables());
				sheet3.getRow(19).getCell(j).setCellValue(assetsDetails.getInstalmentsDeferred());
				
				//sheet3.getRow(21).getCell(j).setCellValue(assetsDetails.getInventory());
				
				//sheet3.getRow(23).getCell(j).setCellValue(assetsDetails.getRawMaterial());
				
				sheet3.getRow(25).getCell(j).setCellValue(assetsDetails.getRawMaterialImported());
				sheet3.getRow(26).getCell(j).setCellValue(assetsDetails.getRawMaterialIndegenous());
				
				//sheet3.getRow(28).getCell(j).setCellValue(assetsDetails.getStockInProcess());
				
				
				//sheet3.getRow(30).getCell(j).setCellValue(assetsDetails.getFinishedGoods());
				
				//sheet3.getRow(32).getCell(j).setCellValue(assetsDetails.getOtherConsumableSpares());

				sheet3.getRow(33).getCell(j).setCellValue(assetsDetails.getOtherConsumableSparesImported());
				sheet3.getRow(34).getCell(j).setCellValue(assetsDetails.getOtherConsumableSparesIndegenous());
				sheet3.getRow(36).getCell(j).setCellValue(assetsDetails.getAdvanceToSupplierRawMaterials());
				sheet3.getRow(38).getCell(j).setCellValue(assetsDetails.getAdvancePaymentTaxes());
				sheet3.getRow(40).getCell(j).setCellValue(assetsDetails.getOtherCurrentAssets());
				
				//sheet3.getRow(42).getCell(j).setCellValue(assetsDetails.getTotalCurrentAssets());
				
				//sheet3.getRow(44).getCell(j).setCellValue(assetsDetails.getGrossBlock());
				
				sheet3.getRow(45).getCell(j).setCellValue(assetsDetails.getLandBuilding());
				sheet3.getRow(46).getCell(j).setCellValue(assetsDetails.getPlantMachines());
				
				sheet3.getRow(47).getCell(j).setCellValue(assetsDetails.getGrossBlock1());
				sheet3.getRow(48).getCell(j).setCellValue(assetsDetails.getGrossBlock2());
				sheet3.getRow(49).getCell(j).setCellValue(assetsDetails.getGrossBlock3());
				sheet3.getRow(50).getCell(j).setCellValue(assetsDetails.getGrossBlock4());
				
				sheet3.getRow(51).getCell(j).setCellValue(assetsDetails.getDepreciationToDate());
				sheet3.getRow(53).getCell(j).setCellValue(assetsDetails.getImpairmentAsset());
				
				//sheet3.getRow(55).getCell(j).setCellValue(assetsDetails.getNetBlock());
				
				sheet3.getRow(59).getCell(j).setCellValue(assetsDetails.getOtherNcaOtherCapitalWorkInprogress());

				//sheet3.getRow(61).getCell(j).setCellValue(assetsDetails.getInvestmentsOrBookDebts());
				
				sheet3.getRow(63).getCell(j).setCellValue(assetsDetails.getInvestmentsInSubsidiary());
			    sheet3.getRow(64).getCell(j).setCellValue(assetsDetails.getOthers());
                
				sheet3.getRow(66).getCell(j).setCellValue(assetsDetails.getAdvanceToSuppliersCapitalGoods());
				sheet3.getRow(68).getCell(j).setCellValue(assetsDetails.getDeferredReceviables());
				
				//sheet3.getRow(70).getCell(j).setCellValue(assetsDetails.getDeferredReceviablesOthers()); 
				
				sheet3.getRow(71).getCell(j).setCellValue(assetsDetails.getOthersPreOperativeExpensesPending());
				sheet3.getRow(72).getCell(j).setCellValue(assetsDetails.getOthersAssetsInTransit());
				sheet3.getRow(73).getCell(j).setCellValue(assetsDetails.getOthersOther());
				sheet3.getRow(75).getCell(j).setCellValue(assetsDetails.getNonConsumableStoreAndSpares());
				sheet3.getRow(77).getCell(j).setCellValue(assetsDetails.getOtherNonCurrentAssets());
				
				//sheet3.getRow(79).getCell(j).setCellValue(assetsDetails.getTotalOtherNonCurrentAssets());
				
				//sheet3.getRow(81).getCell(j).setCellValue(assetsDetails.getIntangibleAssets());
				
				sheet3.getRow(82).getCell(j).setCellValue(assetsDetails.getPatents());
				sheet3.getRow(83).getCell(j).setCellValue(assetsDetails.getGoodWill());
				sheet3.getRow(84).getCell(j).setCellValue(assetsDetails.getPrelimExpenses());
				sheet3.getRow(85).getCell(j).setCellValue(assetsDetails.getBadOrDoubtfulExpenses());
				sheet3.getRow(86).getCell(j).setCellValue(assetsDetails.getAnyOther());

				//sheet3.getRow(88).getCell(j).setCellValue(assetsDetails.getTotalAssets());
				
				//sheet3.getRow(90).getCell(j).setCellValue(assetsDetails.getTangibleNetWorth());
				
				//sheet3.getRow(92).getCell(j).setCellValue(assetsDetails.getNetWorkingCapital());
				
				//sheet3.getRow(94).getCell(j).setCellValue(assetsDetails.getCurrentRatio());
				
				//sheet3.getRow(96).getCell(j).setCellValue(assetsDetails.getTotalOutSideLiability());
				
				//sheet3.getRow(98).getCell(j).setCellValue(assetsDetails.getTotalTermLiability());

				sheet3.getRow(10).getCell(j).setCellValue(evaluateCellValue(sheet3.getRow(10).getCell(j)));
				sheet3.getRow(21).getCell(j).setCellValue(evaluateCellValue(sheet3.getRow(21).getCell(j)));
				sheet3.getRow(23).getCell(j).setCellValue(evaluateCellValue(sheet3.getRow(23).getCell(j)));
				sheet3.getRow(28).getCell(j).setCellValue(evaluateCellValue(sheet3.getRow(28).getCell(j)));
				sheet3.getRow(30).getCell(j).setCellValue(evaluateCellValue(sheet3.getRow(30).getCell(j)));
				sheet3.getRow(32).getCell(j).setCellValue(evaluateCellValue(sheet3.getRow(32).getCell(j)));
				sheet3.getRow(42).getCell(j).setCellValue(evaluateCellValue(sheet3.getRow(42).getCell(j)));
				sheet3.getRow(44).getCell(j).setCellValue(evaluateCellValue(sheet3.getRow(44).getCell(j)));
				sheet3.getRow(55).getCell(j).setCellValue(evaluateCellValue(sheet3.getRow(55).getCell(j)));
				sheet3.getRow(61).getCell(j).setCellValue(evaluateCellValue(sheet3.getRow(61).getCell(j)));
				sheet3.getRow(70).getCell(j).setCellValue(evaluateCellValue(sheet3.getRow(70).getCell(j)));
				sheet3.getRow(79).getCell(j).setCellValue(evaluateCellValue(sheet3.getRow(79).getCell(j)));
				sheet3.getRow(81).getCell(j).setCellValue(evaluateCellValue(sheet3.getRow(81).getCell(j)));
				sheet3.getRow(88).getCell(j).setCellValue(evaluateCellValue(sheet3.getRow(88).getCell(j)));
				sheet3.getRow(90).getCell(j).setCellValue(evaluateCellValue(sheet3.getRow(90).getCell(j)));
				sheet3.getRow(92).getCell(j).setCellValue(evaluateCellValue(sheet3.getRow(92).getCell(j)));
				sheet3.getRow(94).getCell(j).setCellValue(evaluateCellValue(sheet3.getRow(94).getCell(j)));
				sheet3.getRow(96).getCell(j).setCellValue(evaluateCellValue(sheet3.getRow(96).getCell(j)));
				sheet3.getRow(98).getCell(j).setCellValue(evaluateCellValue(sheet3.getRow(98).getCell(j)));
				
				j++;

			}
			//Hide rows dynamically as per tenure in asset.
			int p = 1;
			for(;j<=24;j++) {
				if(p<=tenure) {
				}
				else {
					 sheet3.setColumnHidden(j,true);
				}
				p++;
			}
			// Asset Ends
			setyear(sheet3, temp, assetsDetailsList.size(),total_Column, true);
			//documentResponse =fileUpload(wb, applicationId, productDocumentMappingId);
			logger.info("Exit with cmaFileGenerator() {} ", documentResponse);

		} catch ( IllegalStateException | IOException | InvalidFormatException e) {
			System.err.println("Exception in cmaFileGenerator");
			e.printStackTrace();
		}
		return wb;

	}

	@Override
	public Workbook coCMAFileGenerator(Long applicationId, Long productDocumentMappingId) {
		logger.info("================== Enter in  coCMAFileGenerator() Forming Excel Data=====================");
		
		Workbook wb = null;
		try {
			 Double total_Column=12.0;
			String EXCEL_FILE_LOCATION = "cw.mca.cocmafile.location";
			
			if(productDocumentMappingId==(long)DocumentAlias.USL_COMPANY_ACT) {
	      		 EXCEL_FILE_LOCATION =	"cw.mca.cocmafile.usl.location";
	      		 total_Column=0.0;
			}

			wb = new XSSFWorkbook(OPCPackage.open(environment.getRequiredProperty(EXCEL_FILE_LOCATION)));

			Sheet sheet1 = wb.getSheetAt(0);

			Sheet sheet2 = wb.getSheetAt(1);

			evaluator = wb.getCreationHelper().createFormulaEvaluator();

			List<ProfitibilityStatementDetail> profitibilityStatementDetailsList = profitibilityStatementDetailRepository.getByApplicationId(applicationId);
			int j = 2;
          
			Double temp =0.0;
			
			for (ProfitibilityStatementDetail profitibilityStatementDetails : profitibilityStatementDetailsList) {
				// save in excel
				temp=Double.parseDouble(profitibilityStatementDetails.getYear());
				sheet1.getRow(3).getCell(j).setCellValue(temp);
				
				//sheet1.getRow(6).getCell(j).setCellValue(profitibilityStatementDetails.getSales());
				
				sheet1.getRow(7).getCell(j).setCellValue(profitibilityStatementDetails.getSalesExport());
				sheet1.getRow(8).getCell(j).setCellValue(profitibilityStatementDetails.getSalesDomestic());
				sheet1.getRow(9).getCell(j).setCellValue(profitibilityStatementDetails.getLessExciseDutyOrVatOrServiceTax());
				
				//sheet1.getRow(10).getCell(j).setCellValue(profitibilityStatementDetails.getLessAnyOtherItem());
				
				sheet1.getRow(11).getCell(j).setCellValue(profitibilityStatementDetails.getLessItem1());
				sheet1.getRow(12).getCell(j).setCellValue(profitibilityStatementDetails.getLessItem2());
				sheet1.getRow(13).getCell(j).setCellValue(profitibilityStatementDetails.getLessItem3());
				sheet1.getRow(14).getCell(j).setCellValue(profitibilityStatementDetails.getLessItem4());
				sheet1.getRow(15).getCell(j).setCellValue(profitibilityStatementDetails.getLessItem5());
				
				//sheet1.getRow(16).getCell(j).setCellValue(profitibilityStatementDetails.getNetSales());
				
				
				//sheet1.getRow(17).getCell(j).setCellValue(profitibilityStatementDetails.getOtherOperatingRevenue());
				
				sheet1.getRow(18).getCell(j).setCellValue(profitibilityStatementDetails.getOtherOperatingRevenue1());
				sheet1.getRow(19).getCell(j).setCellValue(profitibilityStatementDetails.getOtherOperatingRevenue2());
				sheet1.getRow(20).getCell(j).setCellValue(profitibilityStatementDetails.getOtherOperatingRevenue3());
				sheet1.getRow(21).getCell(j).setCellValue(profitibilityStatementDetails.getOtherOperatingRevenue4());
				sheet1.getRow(22).getCell(j).setCellValue(profitibilityStatementDetails.getOtherOperatingRevenue5());
				
				//sheet1.getRow(23).getCell(j).setCellValue(profitibilityStatementDetails.getGrossOperatingRevenue());
				
				
				//sheet1.getRow(25).getCell(j).setCellValue(profitibilityStatementDetails.getOperatingExpenses());
				
				
				//sheet1.getRow(26).getCell(j).setCellValue(profitibilityStatementDetails.getCostRawMaterialConsumed());
				
				sheet1.getRow(27).getCell(j).setCellValue(profitibilityStatementDetails.getRawMaterialImported());
				sheet1.getRow(28).getCell(j).setCellValue(profitibilityStatementDetails.getRawMaterialIndigenous());
				sheet1.getRow(29).getCell(j).setCellValue(profitibilityStatementDetails.getPurchasesStockTnTrade());
				
				//sheet1.getRow(30).getCell(j).setCellValue(profitibilityStatementDetails.getIncreaseOrDecreaseInInventoryFg());
				
				sheet1.getRow(31).getCell(j).setCellValue(profitibilityStatementDetails.getClosingStockFg());
				sheet1.getRow(32).getCell(j).setCellValue(profitibilityStatementDetails.getOpeningStockOfFg());
				
				//sheet1.getRow(33).getCell(j).setCellValue(profitibilityStatementDetails.getIncreaseOrDecreaseInInventoryWip());
				
				sheet1.getRow(34).getCell(j).setCellValue(profitibilityStatementDetails.getClosingStockWip());
				sheet1.getRow(35).getCell(j).setCellValue(profitibilityStatementDetails.getOpeningStockWip());
				
				//sheet1.getRow(36).getCell(j).setCellValue(profitibilityStatementDetails.getEmployeeBenefitExpenses());
				
				sheet1.getRow(37).getCell(j).setCellValue(profitibilityStatementDetails.getFactoryWages());
				sheet1.getRow(38).getCell(j).setCellValue(profitibilityStatementDetails.getPersonnelCost());
				
				//sheet1.getRow(39).getCell(j).setCellValue(profitibilityStatementDetails.getOtherExpenses());
				
				sheet1.getRow(40).getCell(j).setCellValue(profitibilityStatementDetails.getPowerAndFuel());
				
				//sheet1.getRow(41).getCell(j).setCellValue(profitibilityStatementDetails.getStoreAndSpares());
				
				sheet1.getRow(42).getCell(j).setCellValue(profitibilityStatementDetails.getStoreAndSparesImported());
				sheet1.getRow(43).getCell(j).setCellValue(profitibilityStatementDetails.getStoreAndSparesIndeigenous());
				sheet1.getRow(44).getCell(j).setCellValue(profitibilityStatementDetails.getGeneralAdminExpenses());
				sheet1.getRow(45).getCell(j).setCellValue(profitibilityStatementDetails.getSellingDistributionExpenses());
				
				//sheet1.getRow(46).getCell(j).setCellValue(profitibilityStatementDetails.getOtherPlsSpecify());
				
				sheet1.getRow(47).getCell(j).setCellValue(profitibilityStatementDetails.getExpensesCapitalised());
				sheet1.getRow(48).getCell(j).setCellValue(profitibilityStatementDetails.getExpenseCapitalized1());
				sheet1.getRow(49).getCell(j).setCellValue(profitibilityStatementDetails.getExpenseCapitalized2());
				sheet1.getRow(50).getCell(j).setCellValue(profitibilityStatementDetails.getExpenseCapitalized3());
				sheet1.getRow(51).getCell(j).setCellValue(profitibilityStatementDetails.getExpenseCapitalized4());
				
				//sheet1.getRow(52).getCell(j).setCellValue(profitibilityStatementDetails.getOperatingProfitBeforeDepreciation());
				
				//sheet1.getRow(54).getCell(j).setCellValue(profitibilityStatementDetails.getDepreciationAndAmortisation());
				
				sheet1.getRow(55).getCell(j).setCellValue(profitibilityStatementDetails.getDepreciation());
				sheet1.getRow(56).getCell(j).setCellValue(profitibilityStatementDetails.getAmortisation());
				
				//sheet1.getRow(58).getCell(j).setCellValue(profitibilityStatementDetails.getOperatingProfitBeforeInterestAndTax());
				
				sheet1.getRow(60).getCell(j).setCellValue(profitibilityStatementDetails.getFinanceCost());
				
				//sheet1.getRow(62).getCell(j).setCellValue(profitibilityStatementDetails.getOperatingProfitBeforeTax());
				
				sheet1.getRow(64).getCell(j).setCellValue(profitibilityStatementDetails.getNonOperatingIncome());
				sheet1.getRow(65).getCell(j).setCellValue(profitibilityStatementDetails.getNonOperatingExpenses());
				
				//sheet1.getRow(66).getCell(j).setCellValue(profitibilityStatementDetails.getExtraordinaryItems());
				
				sheet1.getRow(67).getCell(j).setCellValue(profitibilityStatementDetails.getExtraordinaryItems1());
				sheet1.getRow(68).getCell(j).setCellValue(profitibilityStatementDetails.getExtraordinaryItems2());
				sheet1.getRow(69).getCell(j).setCellValue(profitibilityStatementDetails.getExtraordinaryItems3());
				sheet1.getRow(70).getCell(j).setCellValue(profitibilityStatementDetails.getExtraordinaryItems4());
				sheet1.getRow(71).getCell(j).setCellValue(profitibilityStatementDetails.getExtraordinaryItems5());
				
				//sheet1.getRow(72).getCell(j).setCellValue(profitibilityStatementDetails.getProfitBeforeTax());

				//sheet1.getRow(74).getCell(j).setCellValue(profitibilityStatementDetails.getProvisionForTax());
				
				sheet1.getRow(75).getCell(j).setCellValue(profitibilityStatementDetails.getCurrentTax());
				
				sheet1.getRow(76).getCell(j).setCellValue(profitibilityStatementDetails.getDeferredTax());
				
				//sheet1.getRow(78).getCell(j).setCellValue(profitibilityStatementDetails.getProfitAfterTax());
				
				//sheet1.getRow(80).getCell(j).setCellValue(profitibilityStatementDetails.getDividend());
				
				sheet1.getRow(81).getCell(j).setCellValue(profitibilityStatementDetails.getAlreadyPaid());
				sheet1.getRow(82).getCell(j).setCellValue(profitibilityStatementDetails.getBsProvision());
				
				//sheet1.getRow(84).getCell(j).setCellValue(profitibilityStatementDetails.getRetainedProfit());
				
				sheet1.getRow(6).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(6).getCell(j)));
				sheet1.getRow(10).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(10).getCell(j)));
				sheet1.getRow(16).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(16).getCell(j)));
				sheet1.getRow(17).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(17).getCell(j)));
				sheet1.getRow(23).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(23).getCell(j)));
				sheet1.getRow(25).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(25).getCell(j)));
				sheet1.getRow(26).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(26).getCell(j)));
				sheet1.getRow(30).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(30).getCell(j)));
				sheet1.getRow(33).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(33).getCell(j)));
				sheet1.getRow(36).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(36).getCell(j)));
				sheet1.getRow(39).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(39).getCell(j)));
				sheet1.getRow(41).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(41).getCell(j)));
				sheet1.getRow(46).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(46).getCell(j)));
				sheet1.getRow(52).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(52).getCell(j)));
				sheet1.getRow(54).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(54).getCell(j)));
				sheet1.getRow(58).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(58).getCell(j)));
				sheet1.getRow(62).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(62).getCell(j)));
				sheet1.getRow(66).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(66).getCell(j)));
				sheet1.getRow(72).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(72).getCell(j)));
				sheet1.getRow(74).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(74).getCell(j)));
				sheet1.getRow(78).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(78).getCell(j)));
				sheet1.getRow(80).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(80).getCell(j)));
				sheet1.getRow(84).getCell(j).setCellValue(evaluateCellValue(sheet1.getRow(84).getCell(j)));
				
				j++;

			}
			setyear(sheet1, temp, profitibilityStatementDetailsList.size() ,total_Column, false);

		List<BalanceSheetDetail>	balanceSheetDetailsList = balanceSheetDetailRepository.getByApplicationId(applicationId);
			j = 2;
			for (BalanceSheetDetail balanceSheetDetail : balanceSheetDetailsList) {
                
				temp=Double.parseDouble(balanceSheetDetail.getYear());
 				// save in excel
				sheet2.getRow(4).getCell(j).setCellValue(temp);

				sheet2.getRow(7).getCell(j).setCellValue(balanceSheetDetail.getOrdinaryShareCapital());
				sheet2.getRow(8).getCell(j).setCellValue(balanceSheetDetail.getPreferenceShareCapital());
				sheet2.getRow(9).getCell(j).setCellValue(balanceSheetDetail.getMoneyReceivedAgainstShareWarrants());
				sheet2.getRow(10).getCell(j).setCellValue(balanceSheetDetail.getShareApplicationPendingAllotment());
				sheet2.getRow(11).getCell(j).setCellValue(balanceSheetDetail.getMinorityInterest());
				
				//sheet2.getRow(13).getCell(j).setCellValue(balanceSheetDetail.getReservesAndSurplus());
				
				sheet2.getRow(14).getCell(j).setCellValue(balanceSheetDetail.getCapitalReserve());
				sheet2.getRow(15).getCell(j).setCellValue(balanceSheetDetail.getCapitalRedemptionReserve());
				sheet2.getRow(16).getCell(j).setCellValue(balanceSheetDetail.getSecuritiesPremiumAccount());
				sheet2.getRow(17).getCell(j).setCellValue(balanceSheetDetail.getDebentureRedemptionReserve());
				sheet2.getRow(18).getCell(j).setCellValue(balanceSheetDetail.getRevaluationReserve());
				sheet2.getRow(19).getCell(j).setCellValue(balanceSheetDetail.getContingencyReserve());
				sheet2.getRow(20).getCell(j).setCellValue(balanceSheetDetail.getForeignCurrencyTranslationReserve());
				sheet2.getRow(21).getCell(j).setCellValue(balanceSheetDetail.getHedgingReserve());
				sheet2.getRow(22).getCell(j).setCellValue(balanceSheetDetail.getGeneralReserve());
				sheet2.getRow(23).getCell(j).setCellValue(balanceSheetDetail.getSurplusInProfitAndLossAccount());
				
				//sheet2.getRow(24).getCell(j).setCellValue(balanceSheetDetail.getOthers());
				
				sheet2.getRow(25).getCell(j).setCellValue(balanceSheetDetail.getOthers1());
				sheet2.getRow(26).getCell(j).setCellValue(balanceSheetDetail.getOthers2());
				sheet2.getRow(27).getCell(j).setCellValue(balanceSheetDetail.getOthers3());
				sheet2.getRow(28).getCell(j).setCellValue(balanceSheetDetail.getOthers4());
				sheet2.getRow(29).getCell(j).setCellValue(balanceSheetDetail.getOthers5());
				
				//sheet2.getRow(30).getCell(j).setCellValue(balanceSheetDetail.getOtherNonCurrentLiability());
				
				
				//sheet2.getRow(31).getCell(j).setCellValue(balanceSheetDetail.getLongTermBorrowing());
				
				sheet2.getRow(32).getCell(j).setCellValue(balanceSheetDetail.getDebentures());
				
				//sheet2.getRow(33).getCell(j).setCellValue(balanceSheetDetail.getTermLoans());
				
				sheet2.getRow(34).getCell(j).setCellValue(balanceSheetDetail.getTermLoansSecured());
				sheet2.getRow(35).getCell(j).setCellValue(balanceSheetDetail.getTermLoansUnsecured());
				sheet2.getRow(36).getCell(j).setCellValue(balanceSheetDetail.getUnsecuredLoansFromPromoters());
				sheet2.getRow(37).getCell(j).setCellValue(balanceSheetDetail.getDeferredPaymentCredits());
				sheet2.getRow(38).getCell(j).setCellValue(balanceSheetDetail.getLongTermProvisions());
				sheet2.getRow(39).getCell(j).setCellValue(balanceSheetDetail.getTermDeposits());
				sheet2.getRow(40).getCell(j).setCellValue(balanceSheetDetail.getDeferredTaxLiability());
				
				//sheet2.getRow(41).getCell(j).setCellValue(balanceSheetDetail.getOthersPlsSpecify());
				
				sheet2.getRow(42).getCell(j).setCellValue(balanceSheetDetail.getUnsecuredLoansFromOthers());
				sheet2.getRow(43).getCell(j).setCellValue(balanceSheetDetail.getOtherTermLiability());
				sheet2.getRow(44).getCell(j).setCellValue(balanceSheetDetail.getOthersLiabilities1());
				sheet2.getRow(45).getCell(j).setCellValue(balanceSheetDetail.getOthersLiabilities2());
				sheet2.getRow(46).getCell(j).setCellValue(balanceSheetDetail.getOthersLiabilities3() );
				
			    //sheet2.getRow(47).getCell(j).setCellValue(balanceSheetDetail.getOthersCurrentLiability());
			    
				
			    //sheet2.getRow(48).getCell(j).setCellValue(balanceSheetDetail.getShortTermBorrowings());
			    
				sheet2.getRow(49).getCell(j).setCellValue(balanceSheetDetail.getCurrentLiabilitiesSecured());
				sheet2.getRow(50).getCell(j).setCellValue(balanceSheetDetail.getCurrentLiabilitiesUnsecured());
				sheet2.getRow(51).getCell(j).setCellValue(balanceSheetDetail.getTradePayables());
				sheet2.getRow(52).getCell(j).setCellValue(balanceSheetDetail.getAdvanceFromCustomers());
				sheet2.getRow(53).getCell(j).setCellValue(balanceSheetDetail.getProvisionForTax());
				sheet2.getRow(54).getCell(j).setCellValue(balanceSheetDetail.getDividendPayable());
				sheet2.getRow(55).getCell(j).setCellValue(balanceSheetDetail.getStatutoryLiabilityDues());
				sheet2.getRow(56).getCell(j).setCellValue(balanceSheetDetail.getDepositsAndInstallments());
				
				//sheet2.getRow(57).getCell(j).setCellValue(balanceSheetDetail.getOthersTotals());
				
				sheet2.getRow(58).getCell(j).setCellValue(balanceSheetDetail.getOthersTotals1());
				sheet2.getRow(59).getCell(j).setCellValue(balanceSheetDetail.getOthersTotals2());
				sheet2.getRow(60).getCell(j).setCellValue(balanceSheetDetail.getOthersTotals3());
				sheet2.getRow(61).getCell(j).setCellValue(balanceSheetDetail.getOthersTotals4());
				sheet2.getRow(62).getCell(j).setCellValue(balanceSheetDetail.getOthersTotals5());
			    
				//sheet2.getRow(63).getCell(j).setCellValue(balanceSheetDetail.getTotalCurrentAndNonCurrentLiability());
				
				sheet2.getRow(65).getCell(j).setCellValue(balanceSheetDetail.getOthersNonCurrentAssets());

				//sheet2.getRow(66).getCell(j).setCellValue(balanceSheetDetail.getFixedAssets());
				
				sheet2.getRow(67).getCell(j).setCellValue(balanceSheetDetail.getGrossFixedAssets());
				sheet2.getRow(68).getCell(j).setCellValue(balanceSheetDetail.getDepreciationToDate());
				sheet2.getRow(69).getCell(j).setCellValue(balanceSheetDetail.getImpairmentsOfAssests());
				
				//sheet2.getRow(70).getCell(j).setCellValue(balanceSheetDetail.getIntangibleAssets());
							
				sheet2.getRow(71).getCell(j).setCellValue(balanceSheetDetail.getIntangibleAssets1());
				sheet2.getRow(72).getCell(j).setCellValue(balanceSheetDetail.getIntangibleAssets2());
				sheet2.getRow(73).getCell(j).setCellValue(balanceSheetDetail.getIntangibleAssets3());
				sheet2.getRow(74).getCell(j).setCellValue(balanceSheetDetail.getIntangibleAssets4());
				sheet2.getRow(75).getCell(j).setCellValue(balanceSheetDetail.getIntangibleAssets5());
				sheet2.getRow(76).getCell(j).setCellValue(balanceSheetDetail.getIntangibleAssets6());
				sheet2.getRow(77).getCell(j).setCellValue(balanceSheetDetail.getCapitalWorkInProgress());
				sheet2.getRow(78).getCell(j).setCellValue(balanceSheetDetail.getCapitalAdvance());
				
				//sheet2.getRow(79).getCell(j).setCellValue(balanceSheetDetail.getNonCurrentInvestments());
				
				sheet2.getRow(80).getCell(j).setCellValue(balanceSheetDetail.getInvestmentInSubsidiaries());
				sheet2.getRow(81).getCell(j).setCellValue(balanceSheetDetail.getInvestmentInAssociates());
				sheet2.getRow(82).getCell(j).setCellValue(balanceSheetDetail.getInvestmentInQuoted());
				sheet2.getRow(83).getCell(j).setCellValue(balanceSheetDetail.getLongTermLoansAndAdvance());
				
				//sheet2.getRow(84).getCell(j).setCellValue(balanceSheetDetail.getOthersAssetsTransit());
				
				sheet2.getRow(85).getCell(j).setCellValue(balanceSheetDetail.getPreOperativeExpensesPending());
				sheet2.getRow(86).getCell(j).setCellValue(balanceSheetDetail.getAssetsInTransit());
				sheet2.getRow(87).getCell(j).setCellValue(balanceSheetDetail.getAssetsInTransit1());
				sheet2.getRow(88).getCell(j).setCellValue(balanceSheetDetail.getAssetsInTransit2());
				sheet2.getRow(89).getCell(j).setCellValue(balanceSheetDetail.getAssetsInTransit3());
				sheet2.getRow(90).getCell(j).setCellValue(balanceSheetDetail.getAssetsInTransit4());
                  				
				//sheet2.getRow(91).getCell(j).setCellValue(balanceSheetDetail.getOthersCurrentAssets());
								
				//sheet2.getRow(92).getCell(j).setCellValue(balanceSheetDetail.getCurrentInvestments());
				
				sheet2.getRow(93).getCell(j).setCellValue(balanceSheetDetail.getGovernmentAndOtherTrustee());
				sheet2.getRow(94).getCell(j).setCellValue(balanceSheetDetail.getFixedDepositsWithBanks());
				
				//sheet2.getRow(95).getCell(j).setCellValue(balanceSheetDetail.getOtherInvestments());
				
				sheet2.getRow(96).getCell(j).setCellValue(balanceSheetDetail.getOthersInvestment1());
				sheet2.getRow(97).getCell(j).setCellValue(balanceSheetDetail.getOthersInvestment2());
				sheet2.getRow(98).getCell(j).setCellValue(balanceSheetDetail.getOthersInvestment3());
				sheet2.getRow(99).getCell(j).setCellValue(balanceSheetDetail.getOthersInvestment4());
				//sheet2.getRow(100).getCell(j).setCellValue(balanceSheetDetail.getOthersInvestment5());
				
				//sheet2.getRow(100).getCell(j).setCellValue(balanceSheetDetail.getInventory());
								
				//sheet2.getRow(101).getCell(j).setCellValue(balanceSheetDetail.getRawMaterial());
				
				sheet2.getRow(102).getCell(j).setCellValue(balanceSheetDetail.getRawMaterialImported());
				sheet2.getRow(103).getCell(j).setCellValue(balanceSheetDetail.getRawMaterialIndegenous());
				sheet2.getRow(104).getCell(j).setCellValue(balanceSheetDetail.getStockInProcess());
				sheet2.getRow(105).getCell(j).setCellValue(balanceSheetDetail.getFinishedGoods());
				
				//sheet2.getRow(106).getCell(j).setCellValue(balanceSheetDetail.getOtherConsumablesSpares());
				
				sheet2.getRow(107).getCell(j).setCellValue(balanceSheetDetail.getOtherConsumablesSparesImported());
				sheet2.getRow(108).getCell(j).setCellValue(balanceSheetDetail.getOtherConsumablesSparesIndegenous());
				
				//sheet2.getRow(109).getCell(j).setCellValue(balanceSheetDetail.getTradeReceivables());
				
				sheet2.getRow(110).getCell(j).setCellValue(balanceSheetDetail.getExports());
				sheet2.getRow(111).getCell(j).setCellValue(balanceSheetDetail.getOtherThanExports());
				sheet2.getRow(112).getCell(j).setCellValue(balanceSheetDetail.getCashAndCashEquivalents());
				
				//sheet2.getRow(113).getCell(j).setCellValue(balanceSheetDetail.getShortTermLoansAndAdvances());
				
				sheet2.getRow(114).getCell(j).setCellValue(balanceSheetDetail.getShortTerm1());
				sheet2.getRow(115).getCell(j).setCellValue(balanceSheetDetail.getShortTerm2());
				sheet2.getRow(116).getCell(j).setCellValue(balanceSheetDetail.getShortTerm3());
				sheet2.getRow(117).getCell(j).setCellValue(balanceSheetDetail.getShortTerm4());
				
				//sheet2.getRow(118).getCell(j).setCellValue(balanceSheetDetail.getOtherDetails());
				
				sheet2.getRow(119).getCell(j).setCellValue(balanceSheetDetail.getOtherDetails1());
				sheet2.getRow(120).getCell(j).setCellValue(balanceSheetDetail.getOtherDetails2());
				sheet2.getRow(121).getCell(j).setCellValue(balanceSheetDetail.getOtherDetails3());
				sheet2.getRow(122).getCell(j).setCellValue(balanceSheetDetail.getOtherDetails4());
				sheet2.getRow(123).getCell(j).setCellValue(balanceSheetDetail.getOtherDetails5());
				
				sheet2.getRow(124).getCell(j).setCellValue(balanceSheetDetail.getDeferredTaxAsset());
				sheet2.getRow(125).getCell(j).setCellValue(balanceSheetDetail.getMiscExpences());
				
				//sheet2.getRow(127).getCell(j).setCellValue(balanceSheetDetail.getGrandTotal());
								

				sheet2.getRow(13).getCell(j).setCellValue(evaluateCellValue(sheet2.getRow(13).getCell(j)));
				sheet2.getRow(24).getCell(j).setCellValue(evaluateCellValue(sheet2.getRow(24).getCell(j)));
				sheet2.getRow(30).getCell(j).setCellValue(evaluateCellValue(sheet2.getRow(30).getCell(j)));
				sheet2.getRow(31).getCell(j).setCellValue(evaluateCellValue(sheet2.getRow(31).getCell(j)));
				sheet2.getRow(33).getCell(j).setCellValue(evaluateCellValue(sheet2.getRow(33).getCell(j)));
				sheet2.getRow(41).getCell(j).setCellValue(evaluateCellValue(sheet2.getRow(41).getCell(j)));
				sheet2.getRow(47).getCell(j).setCellValue(evaluateCellValue(sheet2.getRow(47).getCell(j)));
				sheet2.getRow(48).getCell(j).setCellValue(evaluateCellValue(sheet2.getRow(48).getCell(j)));
				sheet2.getRow(57).getCell(j).setCellValue(evaluateCellValue(sheet2.getRow(57).getCell(j)));
				sheet2.getRow(63).getCell(j).setCellValue(evaluateCellValue(sheet2.getRow(63).getCell(j)));
				sheet2.getRow(66).getCell(j).setCellValue(evaluateCellValue(sheet2.getRow(66).getCell(j)));
				sheet2.getRow(70).getCell(j).setCellValue(evaluateCellValue(sheet2.getRow(70).getCell(j)));
				sheet2.getRow(79).getCell(j).setCellValue(evaluateCellValue(sheet2.getRow(79).getCell(j)));
				sheet2.getRow(84).getCell(j).setCellValue(evaluateCellValue(sheet2.getRow(84).getCell(j)));
				sheet2.getRow(91).getCell(j).setCellValue(evaluateCellValue(sheet2.getRow(91).getCell(j)));
				sheet2.getRow(92).getCell(j).setCellValue(evaluateCellValue(sheet2.getRow(92).getCell(j)));
				sheet2.getRow(95).getCell(j).setCellValue(evaluateCellValue(sheet2.getRow(95).getCell(j)));
				sheet2.getRow(100).getCell(j).setCellValue(evaluateCellValue(sheet2.getRow(100).getCell(j)));
				sheet2.getRow(101).getCell(j).setCellValue(evaluateCellValue(sheet2.getRow(101).getCell(j)));
				sheet2.getRow(106).getCell(j).setCellValue(evaluateCellValue(sheet2.getRow(106).getCell(j)));
				sheet2.getRow(109).getCell(j).setCellValue(evaluateCellValue(sheet2.getRow(109).getCell(j)));
				sheet2.getRow(113).getCell(j).setCellValue(evaluateCellValue(sheet2.getRow(113).getCell(j)));
				sheet2.getRow(118).getCell(j).setCellValue(evaluateCellValue(sheet2.getRow(118).getCell(j)));
				sheet2.getRow(127).getCell(j).setCellValue(evaluateCellValue(sheet2.getRow(127).getCell(j)));

				j++;

			}			// Asset Ends
            
			setyear(sheet2, temp, balanceSheetDetailsList.size(),total_Column, true);
			logger.info("Exit with cmaFileGenerator() {} ");

		} catch ( IllegalStateException | IOException | InvalidFormatException e) {
			logger.error("=================Exception in coCMAFileGenerator===================>");
			e.printStackTrace();
		}
		  
		return wb;

	}
	
	private void setyear(Sheet sheet , Double temp ,int j, Double total_Column, Boolean flag) {
		Calendar  calendar =Calendar.getInstance();
 		Double tillYear =(double)calendar.get(Calendar.YEAR);
       		
		Double totalYear= temp+total_Column-j+1;
		temp++;
		if(j==0) {
			temp=tillYear-3;
			totalYear=temp+total_Column-1;  
		}
		if(profitibilitySheet.equals(sheet.getSheetName())|| balanceSheet.equals(sheet.getSheetName())) {
			j=1;
		}
		for (int i = j; temp <totalYear;++temp) {
			if(flag) {
//				System.out.println(i+" cell "+sheet.getRow(4).getCell(i).getNumericCellValue());
				sheet.getRow(4).getCell(++i).setCellValue(temp);
			}
			else {
				sheet.getRow(3).getCell(++i).setCellValue(temp);
			}
				
		}
		
	}

	private Double evaluateCellValue(Cell cell){
		CellValue cellValue = evaluator.evaluate(cell);
		return cellValue.getNumberValue();
	}
}