package com.capitaworld.service.loans.service.common.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.dms.model.DocumentRequest;
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
import com.capitaworld.service.loans.repository.fundseeker.corporate.OperatingStatementDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ProfitibilityStatementDetailRepository;
import com.capitaworld.service.loans.service.common.DownLoadCMAFileService;
import com.capitaworld.service.loans.utils.CommonMultiPartFile;

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

	
	private FormulaEvaluator evaluator;

	@Autowired
	private DMSClient dmsClient;

	private static final Logger logger = LoggerFactory.getLogger(DownloadCMAFileServiceImpl.class);

	@Override
	public DocumentResponse cmaFileGenerator(Long applicationId , Long productDocumentMappingId) {
		logger.info("Enter in cmaFileGenerator()  Forming Excel Data");
		DocumentResponse documentResponse = null;
		Workbook wb = null;
		try {
			String EXCEL_FILE_LOCATION = "cw.mca.cwcmafile.location";
			

			wb = new XSSFWorkbook(OPCPackage.open(environment.getRequiredProperty(EXCEL_FILE_LOCATION)));

			
			Sheet sheet1 = wb.getSheetAt(0);

			Sheet sheet2 = wb.getSheetAt(1);

			Sheet sheet3 = wb.getSheetAt(2);

			evaluator = wb.getCreationHelper().createFormulaEvaluator();

			List<OperatingStatementDetails> operatingStatementDetailsList = operatingStatementDetailsRepository
					.getByApplicationId(applicationId);
			int j = 1;
			for (OperatingStatementDetails operatingStatementDetails : operatingStatementDetailsList) {

				// save into excel
				sheet1.getRow(4).getCell(j).setCellValue(Double.parseDouble(operatingStatementDetails.getYear()));
				System.out.println(sheet1.getRow(4).getCell(j).getNumericCellValue());
				sheet1.getRow(7).getCell(j).setCellValue(operatingStatementDetails.getDomesticSales());
				System.out.println(sheet1.getRow(7).getCell(j).getNumericCellValue());
				sheet1.getRow(8).getCell(j).setCellValue(operatingStatementDetails.getExportSales());
				System.out.println(sheet1.getRow(8).getCell(j).getNumericCellValue());
				sheet1.getRow(9).getCell(j).setCellValue(operatingStatementDetails.getAddOtherRevenueIncome());
				// sheet1.getRow(11).getCell(j).setCellValue(operatingStatementDetails.getTotalCostSales()
				// );

				sheet1.getRow(12).getCell(j).setCellValue(operatingStatementDetails.getLessExciseDuty());
				sheet1.getRow(13).getCell(j).setCellValue(operatingStatementDetails.getDeductOtherItems());
				sheet1.getRow(14).getCell(j).setCellValue(operatingStatementDetails.getNetSales());

				// sheet1.getRow(16).getCell(j).setCellValue(operatingStatementDetails.getF);
				// sheet1.getRow(18).getCell(j).setCellValue(10);

				sheet1.getRow(19).getCell(j).setCellValue(operatingStatementDetails.getRawMaterials());
				sheet1.getRow(20).getCell(j).setCellValue(operatingStatementDetails.getRawMaterialsImported());
				sheet1.getRow(21).getCell(j).setCellValue(operatingStatementDetails.getRawMaterialsIndigenous());

				sheet1.getRow(23).getCell(j).setCellValue(operatingStatementDetails.getOtherSpares());
				sheet1.getRow(24).getCell(j).setCellValue(operatingStatementDetails.getOtherSparesImported());
				sheet1.getRow(25).getCell(j).setCellValue(operatingStatementDetails.getOtherSparesIndigenous());

				sheet1.getRow(27).getCell(j).setCellValue(operatingStatementDetails.getPowerAndFuel());
				sheet1.getRow(29).getCell(j).setCellValue(operatingStatementDetails.getDirectLabour());
				sheet1.getRow(31).getCell(j).setCellValue(operatingStatementDetails.getOtherMfgExpenses());

				sheet1.getRow(33).getCell(j).setCellValue(operatingStatementDetails.getDepreciation());

				// sheet1.getRow(35).getCell(j).setCellValue(operatingStatementDetails.getSubTotalCostSales());

				sheet1.getRow(37).getCell(j).setCellValue(operatingStatementDetails.getAddOperatingStock());
				// sheet1.getRow(39).getCell(j).setCellValue(operatingStatementDetails.get);

				sheet1.getRow(41).getCell(j).setCellValue(operatingStatementDetails.getDeductStockInProcess());

				sheet1.getRow(43).getCell(j).setCellValue(operatingStatementDetails.getProductionCost());

				sheet1.getRow(45).getCell(j).setCellValue(operatingStatementDetails.getAddOperatingStockFg());
				// sheet1.getRow(47).getCell(j).setCellValue(operatingStatementDetails.getOtherMfgExpenses());

				sheet1.getRow(49).getCell(j).setCellValue(operatingStatementDetails.getDeductClStockFg());
				sheet1.getRow(51).getCell(j).setCellValue(operatingStatementDetails.getTotalCostSales());

				sheet1.getRow(53).getCell(j)
						.setCellValue(operatingStatementDetails.getSellingAndDistributionExpenses());
				sheet1.getRow(55).getCell(j).setCellValue(operatingStatementDetails.getSellingGenlAdmnExpenses());
				// sheet1.getRow(57).getCell(j).setCellValue(operatingStatementDetails.get);

				sheet1.getRow(59).getCell(j).setCellValue(operatingStatementDetails.getOpProfitBeforeIntrest());
				sheet1.getRow(61).getCell(j).setCellValue(operatingStatementDetails.getInterest());
				sheet1.getRow(63).getCell(j).setCellValue(operatingStatementDetails.getOpProfitAfterInterest());
				sheet1.getRow(65).getCell(j).setCellValue(operatingStatementDetails.getAddOtherNonOpIncome());
				// sheet1.getRow(66).getCell(j).setCellValue(operatingStatementDetails.getSub);

				sheet1.getRow(67).getCell(j).setCellValue(operatingStatementDetails.getDeductOtherNonOpExp());
				// sheet1.getRow(68).getCell(j).setCellValue(operatingStatementDetails.getSubTotalDeductAndCostOfProduction());

				sheet1.getRow(69).getCell(j).setCellValue(operatingStatementDetails.getNetofNonOpIncomeOrExpenses());
				sheet1.getRow(70).getCell(j).setCellValue(operatingStatementDetails.getExpensesAmortised());
				sheet1.getRow(72).getCell(j).setCellValue(operatingStatementDetails.getProfitBeforeTaxOrLoss());
				sheet1.getRow(74).getCell(j).setCellValue(operatingStatementDetails.getProvisionForTaxes());
				sheet1.getRow(76).getCell(j).setCellValue(operatingStatementDetails.getProvisionForDeferredTax());
				sheet1.getRow(77).getCell(j).setCellValue(operatingStatementDetails.getNetProfitOrLoss());
				sheet1.getRow(79).getCell(j).setCellValue(operatingStatementDetails.getEquityDeividendPaidAmt());
				sheet1.getRow(81).getCell(j).setCellValue(operatingStatementDetails.getDividendRate());
				sheet1.getRow(83).getCell(j).setCellValue(operatingStatementDetails.getRetainedProfit());
				sheet1.getRow(85).getCell(j).setCellValue(operatingStatementDetails.getRetainedProfitOrNetProfit());
				j++;

			}
			// Operating Stmt. ends

			// Liabilities Starts
			List<LiabilitiesDetails> liabilitiesDetailsList = liabilitiesDetailsRepository
					.getByApplicationId(applicationId);
			j = 1;
			for (LiabilitiesDetails liabilitiesDetails : liabilitiesDetailsList) {
				// save in db
				liabilitiesDetailsRepository.save(liabilitiesDetails);

				// save in excel
				sheet2.getRow(4).getCell(j).setCellValue(Double.parseDouble(liabilitiesDetails.getYear()));
				System.out.println(sheet2.getRow(4).getCell(j).getNumericCellValue());
				// sheet1.getRow(8).getCell(j).setCellValue(liabilitiesDetails.getB);
				sheet2.getRow(10).getCell(j).setCellValue(liabilitiesDetails.getFromApplicationBank());
				sheet2.getRow(11).getCell(j).setCellValue(liabilitiesDetails.getFromOtherBanks());
				sheet2.getRow(12).getCell(j).setCellValue(liabilitiesDetails.getWhichBpAndBd());
				sheet2.getRow(14).getCell(j).setCellValue(liabilitiesDetails.getSubTotalA());
				sheet2.getRow(16).getCell(j).setCellValue(liabilitiesDetails.getShortTermBorrowingFromOthers());
				sheet2.getRow(18).getCell(j).setCellValue(liabilitiesDetails.getSundryCreditors());
				sheet2.getRow(20).getCell(j).setCellValue(liabilitiesDetails.getAdvancePaymentsFromCustomers());
				sheet2.getRow(22).getCell(j).setCellValue(liabilitiesDetails.getProvisionalForTaxation());
				sheet2.getRow(24).getCell(j).setCellValue(liabilitiesDetails.getDividendPayable());
				sheet2.getRow(26).getCell(j).setCellValue(liabilitiesDetails.getOtherStatutoryLiability());
				sheet2.getRow(28).getCell(j).setCellValue(liabilitiesDetails.getDepositsOrInstalmentsOfTermLoans());
				sheet2.getRow(31).getCell(j).setCellValue(liabilitiesDetails.getOtherCurrentLiability());
				sheet2.getRow(34).getCell(j).setCellValue(liabilitiesDetails.getSubTotalB());
				sheet2.getRow(36).getCell(j).setCellValue(liabilitiesDetails.getTotalCurrentLiabilities());
				sheet2.getRow(40).getCell(j).setCellValue(liabilitiesDetails.getDebentures());
				sheet2.getRow(42).getCell(j).setCellValue(liabilitiesDetails.getPreferencesShares());
				sheet2.getRow(44).getCell(j).setCellValue(liabilitiesDetails.getTermLoans());
				// sheet1.getRow(45).getCell(j).setCellValue(liabilitiesDetails.getShortTermBorrowingFromOthers());

				sheet2.getRow(46).getCell(j).setCellValue(liabilitiesDetails.getProvisionalForTaxation());
				sheet2.getRow(48).getCell(j).setCellValue(liabilitiesDetails.getDeferredPaymentsCredits());
				sheet2.getRow(50).getCell(j).setCellValue(liabilitiesDetails.getTermDeposits());
				sheet2.getRow(52).getCell(j).setCellValue(liabilitiesDetails.getOtherTermLiabilies());
				sheet2.getRow(54).getCell(j).setCellValue(liabilitiesDetails.getTotalTermLiabilities());
				sheet2.getRow(56).getCell(j).setCellValue(liabilitiesDetails.getOtherNcl());
				sheet2.getRow(57).getCell(j).setCellValue(liabilitiesDetails.getOtherNclUnsecuredLoansFromPromoters());
				sheet2.getRow(58).getCell(j).setCellValue(liabilitiesDetails.getOtherNclUnsecuredLoansFromOther());
				sheet2.getRow(59).getCell(j).setCellValue(liabilitiesDetails.getOtherNclLongTermProvisions());
				sheet2.getRow(60).getCell(j).setCellValue(liabilitiesDetails.getOthers());

				sheet2.getRow(62).getCell(j).setCellValue(liabilitiesDetails.getTotalOutsideLiabilities());
				sheet2.getRow(64).getCell(j).setCellValue(liabilitiesDetails.getNetWorth());
				sheet2.getRow(66).getCell(j).setCellValue(liabilitiesDetails.getOrdinarySharesCapital());
				sheet2.getRow(68).getCell(j).setCellValue(liabilitiesDetails.getShareWarrentsOutstanding());
				sheet2.getRow(70).getCell(j).setCellValue(liabilitiesDetails.getMinorityInterest());
				sheet2.getRow(72).getCell(j).setCellValue(liabilitiesDetails.getGeneralReserve());
				sheet2.getRow(74).getCell(j).setCellValue(liabilitiesDetails.getRevaluationReservse());
				sheet2.getRow(76).getCell(j).setCellValue(liabilitiesDetails.getOtherReservse());
				sheet2.getRow(78).getCell(j).setCellValue(liabilitiesDetails.getSurplusOrDeficit());
				sheet2.getRow(80).getCell(j).setCellValue(liabilitiesDetails.getDeferredTaxLiability());
				sheet2.getRow(82).getCell(j).setCellValue(liabilitiesDetails.getOthers());
				sheet2.getRow(84).getCell(j).setCellValue(liabilitiesDetails.getNetWorth());
				sheet2.getRow(86).getCell(j).setCellValue(liabilitiesDetails.getTotalLiability());
				j++;

			}
			// Liabilities Ends

			// Asset Starts

			List<AssetsDetails> assetsDetailsList = assetsDetailsRepository.getByApplicationId(applicationId);
			j = 1;
			for (AssetsDetails assetsDetails : assetsDetailsList) {
				// save in db
				assetsDetailsRepository.save(assetsDetails);

				// save in excel
				sheet3.getRow(4).getCell(j).setCellValue(Double.parseDouble(assetsDetails.getYear()));
				System.out.println(sheet3.getRow(4).getCell(j).getNumericCellValue());
				// sheet3.getRow(8).getCell(j).setCellValue(assetsDetails.getCashAndBankBalance());

				sheet3.getRow(10).getCell(j).setCellValue(assetsDetails.getInvestments());
				sheet3.getRow(11).getCell(j).setCellValue(assetsDetails.getGovernmentAndOtherTrustee());
				sheet3.getRow(14).getCell(j).setCellValue(assetsDetails.getFixedDepositsWithBanks());
				sheet3.getRow(15).getCell(j).setCellValue(assetsDetails.getReceivableOtherThanDefferred());
				sheet3.getRow(17).getCell(j).setCellValue(assetsDetails.getExportReceivables());
				sheet3.getRow(19).getCell(j).setCellValue(assetsDetails.getInstalmentsDeferred());
				sheet3.getRow(21).getCell(j).setCellValue(assetsDetails.getInventory());
				sheet3.getRow(23).getCell(j).setCellValue(assetsDetails.getRawMaterial());
				sheet3.getRow(25).getCell(j).setCellValue(assetsDetails.getRawMaterialImported());
				sheet3.getRow(26).getCell(j).setCellValue(assetsDetails.getRawMaterialIndegenous());
				sheet3.getRow(28).getCell(j).setCellValue(assetsDetails.getStockInProcess());
				sheet3.getRow(30).getCell(j).setCellValue(assetsDetails.getFinishedGoods());
				sheet3.getRow(32).getCell(j).setCellValue(assetsDetails.getOtherConsumableSpares());
				sheet3.getRow(33).getCell(j).setCellValue(assetsDetails.getOtherConsumableSparesImported());
				sheet3.getRow(34).getCell(j).setCellValue(assetsDetails.getOtherConsumableSparesIndegenous());
				sheet3.getRow(36).getCell(j).setCellValue(assetsDetails.getAdvanceToSupplierRawMaterials());
				sheet3.getRow(38).getCell(j).setCellValue(assetsDetails.getAdvancePaymentTaxes());
				sheet3.getRow(40).getCell(j).setCellValue(assetsDetails.getOtherCurrentAssets());
				sheet3.getRow(42).getCell(j).setCellValue(assetsDetails.getTotalCurrentAssets());
				sheet3.getRow(44).getCell(j).setCellValue(assetsDetails.getGrossBlock());
				sheet3.getRow(45).getCell(j).setCellValue(assetsDetails.getLandBuilding());
				sheet3.getRow(46).getCell(j).setCellValue(assetsDetails.getPlantMachines());
				sheet3.getRow(51).getCell(j).setCellValue(assetsDetails.getDepreciationToDate());
				sheet3.getRow(53).getCell(j).setCellValue(assetsDetails.getImpairmentAsset());
				sheet3.getRow(55).getCell(j).setCellValue(assetsDetails.getNetBlock());
				// shet1.getRow(59).getCell(j).setCellValue(assetsDetails.getNetWorkingCapital());

				sheet3.getRow(61).getCell(j).setCellValue(assetsDetails.getInvestmentsOrBookDebts());
				sheet3.getRow(63).getCell(j).setCellValue(assetsDetails.getInvestmentsInSubsidiary());
				// sheet1.getRow(64).getCell(j).setCellValue(assetsDetails.getInves;

				sheet3.getRow(66).getCell(j).setCellValue(assetsDetails.getAdvanceToSuppliersCapitalGoods());
				// sheet3.getRow(68).getCell(j).setCellValue(assetsDetails.getDeferredReceviables());
				sheet3.getRow(70).getCell(j).setCellValue(assetsDetails.getOthers());
				sheet3.getRow(71).getCell(j).setCellValue(assetsDetails.getOthersPreOperativeExpensesPending());
				sheet3.getRow(72).getCell(j).setCellValue(assetsDetails.getOthersAssetsInTransit());
				sheet3.getRow(73).getCell(j).setCellValue(assetsDetails.getOthersOther());
				sheet3.getRow(75).getCell(j).setCellValue(assetsDetails.getNonConsumableStoreAndSpares());
				sheet3.getRow(77).getCell(j).setCellValue(assetsDetails.getOtherNonCurrentAssets());
				sheet3.getRow(79).getCell(j).setCellValue(assetsDetails.getTotalOtherNonCurrentAssets());
				sheet3.getRow(81).getCell(j).setCellValue(assetsDetails.getIntangibleAssets());
				sheet3.getRow(82).getCell(j).setCellValue(assetsDetails.getPatents());
				sheet3.getRow(83).getCell(j).setCellValue(assetsDetails.getGoodWill());
				sheet3.getRow(84).getCell(j).setCellValue(assetsDetails.getPrelimExpenses());
				sheet3.getRow(85).getCell(j).setCellValue(assetsDetails.getBadOrDoubtfulExpenses());
				// sheet1.getRow(86).getCell(j).setCellValue(assetsDetails.getOthers() );

				sheet3.getRow(88).getCell(j).setCellValue(assetsDetails.getTotalAssets());
				sheet3.getRow(90).getCell(j).setCellValue(assetsDetails.getTangibleNetWorth());
				sheet3.getRow(92).getCell(j).setCellValue(assetsDetails.getNetWorkingCapital());
				sheet3.getRow(94).getCell(j).setCellValue(assetsDetails.getCurrentRatio());
				sheet3.getRow(96).getCell(j).setCellValue(assetsDetails.getTotalOutSideLiability());
				sheet3.getRow(98).getCell(j).setCellValue(assetsDetails.getTotalTermLiability());
				j++;

			}

			// Asset Ends
			
			documentResponse =fileUpload(wb, applicationId, productDocumentMappingId);
			logger.info("Exit with cmaFileGenerator() {} ", documentResponse);

		} catch (DocumentException | IllegalStateException | IOException | InvalidFormatException e) {
			System.err.println("Exception in cmaFileGenerator");
			e.printStackTrace();
		}
		return documentResponse;

	}

	@Override
	public DocumentResponse coCMAFileGenerator(Long applicationId, Long productDocumentMappingId) {
		logger.info("Enter in coCMAFileGenerator() Forming Excel Data");
		DocumentResponse documentResponse = null;
		Workbook wb = null;
		try {
			String EXCEL_FILE_LOCATION = "cw.mca.cocmafile.location";

			wb = new XSSFWorkbook(OPCPackage.open(environment.getRequiredProperty(EXCEL_FILE_LOCATION)));

			
			
			Sheet sheet1 = wb.getSheetAt(0);

			Sheet sheet2 = wb.getSheetAt(1);

			evaluator = wb.getCreationHelper().createFormulaEvaluator();

			List<ProfitibilityStatementDetail> profitibilityStatementDetailsList = profitibilityStatementDetailRepository.getByApplicationId(applicationId);
			int j = 2;
			for (ProfitibilityStatementDetail profitibilityStatementDetails : profitibilityStatementDetailsList) {

				// save in db
				profitibilityStatementDetailRepository.save(profitibilityStatementDetails);

				// save in excel
				sheet1.getRow(3).getCell(j).setCellValue(Double.parseDouble(profitibilityStatementDetails.getYear()));
				System.out.println(sheet1.getRow(3).getCell(j).getNumericCellValue());

				sheet1.getRow(6).getCell(j).setCellValue(profitibilityStatementDetails.getSales());
				sheet1.getRow(7).getCell(j).setCellValue(profitibilityStatementDetails.getSalesExport());
				sheet1.getRow(8).getCell(j).setCellValue(profitibilityStatementDetails.getSalesDomestic());
				sheet1.getRow(9).getCell(j)
						.setCellValue(profitibilityStatementDetails.getLessExciseDutyOrVatOrServiceTax());
				sheet1.getRow(10).getCell(j).setCellValue(profitibilityStatementDetails.getLessAnyOtherItem());
				sheet1.getRow(16).getCell(j).setCellValue(profitibilityStatementDetails.getNetSales());
				sheet1.getRow(17).getCell(j).setCellValue(profitibilityStatementDetails.getOtherOperatingRevenue());
				sheet1.getRow(23).getCell(j).setCellValue(profitibilityStatementDetails.getGrossOperatingRevenue());
				sheet1.getRow(25).getCell(j).setCellValue(profitibilityStatementDetails.getOperatingExpenses());
				sheet1.getRow(26).getCell(j).setCellValue(profitibilityStatementDetails.getCostRawMaterialConsumed());
				sheet1.getRow(27).getCell(j).setCellValue(profitibilityStatementDetails.getRawMaterialImported());
				sheet1.getRow(28).getCell(j).setCellValue(profitibilityStatementDetails.getRawMaterialIndigenous());
				sheet1.getRow(29).getCell(j).setCellValue(profitibilityStatementDetails.getPurchasesStockTnTrade());
				sheet1.getRow(30).getCell(j)
						.setCellValue(profitibilityStatementDetails.getIncreaseOrDecreaseInInventoryFg());
				sheet1.getRow(31).getCell(j).setCellValue(profitibilityStatementDetails.getClosingStockFg());
				sheet1.getRow(32).getCell(j).setCellValue(profitibilityStatementDetails.getOpeningStockOfFg());
				sheet1.getRow(33).getCell(j)
						.setCellValue(profitibilityStatementDetails.getIncreaseOrDecreaseInInventoryWip());
				sheet1.getRow(34).getCell(j).setCellValue(profitibilityStatementDetails.getClosingStockWip());
				sheet1.getRow(35).getCell(j).setCellValue(profitibilityStatementDetails.getOpeningStockWip());
				sheet1.getRow(36).getCell(j).setCellValue(profitibilityStatementDetails.getEmployeeBenefitExpenses());
				sheet1.getRow(37).getCell(j).setCellValue(profitibilityStatementDetails.getFactoryWages());
				sheet1.getRow(38).getCell(j).setCellValue(profitibilityStatementDetails.getPersonnelCost());
				sheet1.getRow(39).getCell(j).setCellValue(profitibilityStatementDetails.getOtherExpenses());
				sheet1.getRow(40).getCell(j).setCellValue(profitibilityStatementDetails.getPowerAndFuel());
				sheet1.getRow(41).getCell(j).setCellValue(profitibilityStatementDetails.getStoreAndSpares());
				sheet1.getRow(42).getCell(j).setCellValue(profitibilityStatementDetails.getStoreAndSparesImported());
				sheet1.getRow(43).getCell(j).setCellValue(profitibilityStatementDetails.getStoreAndSparesIndeigenous());
				sheet1.getRow(44).getCell(j).setCellValue(profitibilityStatementDetails.getGeneralAdminExpenses());
				sheet1.getRow(45).getCell(j)
						.setCellValue(profitibilityStatementDetails.getSellingDistributionExpenses());
				sheet1.getRow(46).getCell(j).setCellValue(profitibilityStatementDetails.getOtherPlsSpecify());
				sheet1.getRow(47).getCell(j).setCellValue(profitibilityStatementDetails.getExpensesCapitalised());
				sheet1.getRow(52).getCell(j)
						.setCellValue(profitibilityStatementDetails.getOperatingProfitBeforeDepreciation());
				sheet1.getRow(54).getCell(j)
						.setCellValue(profitibilityStatementDetails.getDepreciationAndAmortisation());
				sheet1.getRow(55).getCell(j).setCellValue(profitibilityStatementDetails.getDepreciation());
				sheet1.getRow(56).getCell(j).setCellValue(profitibilityStatementDetails.getAmortisation());
				sheet1.getRow(58).getCell(j)
						.setCellValue(profitibilityStatementDetails.getOperatingProfitBeforeInterestAndTax());
				sheet1.getRow(60).getCell(j).setCellValue(profitibilityStatementDetails.getFinanceCost());
				sheet1.getRow(62).getCell(j).setCellValue(profitibilityStatementDetails.getOperatingProfitBeforeTax());
				sheet1.getRow(64).getCell(j).setCellValue(profitibilityStatementDetails.getNonOperatingIncome());
				sheet1.getRow(65).getCell(j).setCellValue(profitibilityStatementDetails.getNonOperatingExpenses());
				sheet1.getRow(66).getCell(j).setCellValue(profitibilityStatementDetails.getExtraordinaryItems());
				sheet1.getRow(72).getCell(j).setCellValue(profitibilityStatementDetails.getProfitBeforeTax());
				sheet1.getRow(74).getCell(j).setCellValue(profitibilityStatementDetails.getProvisionForTax());
				sheet1.getRow(75).getCell(j).setCellValue(profitibilityStatementDetails.getCurrentTax());
				sheet1.getRow(76).getCell(j).setCellValue(profitibilityStatementDetails.getDeferredTax());
				sheet1.getRow(78).getCell(j).setCellValue(profitibilityStatementDetails.getProfitAfterTax());
				sheet1.getRow(80).getCell(j).setCellValue(profitibilityStatementDetails.getDividend());
				sheet1.getRow(81).getCell(j).setCellValue(profitibilityStatementDetails.getAlreadyPaid());
				sheet1.getRow(82).getCell(j).setCellValue(profitibilityStatementDetails.getBsProvision());
				sheet1.getRow(84).getCell(j).setCellValue(profitibilityStatementDetails.getRetainedProfit());

				j++;

			}

		List<BalanceSheetDetail>	balanceSheetDetailsList = balanceSheetDetailRepository.getByApplicationId(applicationId);
			j = 2;
			for (BalanceSheetDetail balanceSheetDetail : balanceSheetDetailsList) {

				// save in db
				balanceSheetDetailRepository.save(balanceSheetDetail);

				// save in excel
				sheet1.getRow(4).getCell(j).setCellValue(Double.parseDouble(balanceSheetDetail.getYear()));
				System.out.println(sheet1.getRow(4).getCell(j).getNumericCellValue());
				// sheet2.getRow(6).getCell(j).setCellValue(balanceSheetDetail.getOrdinaryShareCapital());

				sheet2.getRow(7).getCell(j).setCellValue(balanceSheetDetail.getOrdinaryShareCapital());
				sheet2.getRow(8).getCell(j).setCellValue(balanceSheetDetail.getPreferenceShareCapital());
				sheet2.getRow(9).getCell(j).setCellValue(balanceSheetDetail.getMoneyReceivedAgainstShareWarrants());
				sheet2.getRow(10).getCell(j).setCellValue(balanceSheetDetail.getShareApplicationPendingAllotment());
				sheet2.getRow(11).getCell(j).setCellValue(balanceSheetDetail.getMinorityInterest());
				sheet2.getRow(13).getCell(j).setCellValue(balanceSheetDetail.getReservesAndSurplus());
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
				sheet2.getRow(24).getCell(j).setCellValue(balanceSheetDetail.getOthersPlsSpecify());
				sheet2.getRow(30).getCell(j).setCellValue(balanceSheetDetail.getOtherNonCurrentLiability());
				sheet2.getRow(31).getCell(j).setCellValue(balanceSheetDetail.getLongTermBorrowing());
				sheet2.getRow(32).getCell(j).setCellValue(balanceSheetDetail.getDebentures());
				sheet2.getRow(33).getCell(j).setCellValue(balanceSheetDetail.getTermLoans());
				sheet1.getRow(34).getCell(j).setCellValue(balanceSheetDetail.getTermLoansSecured());
				sheet2.getRow(35).getCell(j).setCellValue(balanceSheetDetail.getTermLoansUnsecured());
				sheet2.getRow(36).getCell(j).setCellValue(balanceSheetDetail.getUnsecuredLoansFromPromoters());
				sheet2.getRow(37).getCell(j).setCellValue(balanceSheetDetail.getDeferredPaymentCredits());
				sheet2.getRow(38).getCell(j).setCellValue(balanceSheetDetail.getLongTermProvisions());
				sheet2.getRow(39).getCell(j).setCellValue(balanceSheetDetail.getTermDeposits());
				sheet2.getRow(40).getCell(j).setCellValue(balanceSheetDetail.getDeferredTaxLiability());
				sheet2.getRow(41).getCell(j).setCellValue(balanceSheetDetail.getOthersPlsSpecify());
				sheet2.getRow(42).getCell(j).setCellValue(balanceSheetDetail.getUnsecuredLoansFromOthers());
				sheet2.getRow(43).getCell(j).setCellValue(balanceSheetDetail.getOtherTermLiability());
				// sheet2.getRow(47).getCell(j).setCellValue(balanceSheetDetail.getCurrent);

				sheet2.getRow(48).getCell(j).setCellValue(balanceSheetDetail.getShortTermBorrowings());
				sheet2.getRow(49).getCell(j).setCellValue(balanceSheetDetail.getCurrentLiabilitiesSecured());
				sheet2.getRow(50).getCell(j).setCellValue(balanceSheetDetail.getCurrentLiabilitiesUnsecured());
				sheet2.getRow(51).getCell(j).setCellValue(balanceSheetDetail.getTradePayables());
				sheet2.getRow(52).getCell(j).setCellValue(balanceSheetDetail.getAdvanceFromCustomers());
				sheet2.getRow(53).getCell(j).setCellValue(balanceSheetDetail.getProvisionForTax());
				sheet2.getRow(54).getCell(j).setCellValue(balanceSheetDetail.getDividendPayable());
				sheet2.getRow(55).getCell(j).setCellValue(balanceSheetDetail.getStatutoryLiabilityDues());
				sheet2.getRow(56).getCell(j).setCellValue(balanceSheetDetail.getDepositsAndInstallments());
				sheet2.getRow(57).getCell(j).setCellValue(balanceSheetDetail.getOthersPlsSpecify());
				// sheet2.getRow(63).getCell(j).setCellValue(balanceSheetDetail.getTo);

				// sheet2.getRow(65).getCell(j).setCellValue(balanceSheetDetail.getOthersNonCurrentAssets());

				sheet2.getRow(66).getCell(j).setCellValue(balanceSheetDetail.getFixedAssets());
				sheet2.getRow(67).getCell(j).setCellValue(balanceSheetDetail.getGrossFixedAssets());
				sheet2.getRow(68).getCell(j).setCellValue(balanceSheetDetail.getDepreciationToDate());
				sheet2.getRow(69).getCell(j).setCellValue(balanceSheetDetail.getImpairmentsOfAssests());
				sheet2.getRow(70).getCell(j).setCellValue(balanceSheetDetail.getIntangibleAssets());
				// sheet2.getRow(77).getCell(j).setCellValue(balanceSheetDetail.get);

				sheet2.getRow(78).getCell(j).setCellValue(balanceSheetDetail.getCapitalAdvance());
				sheet2.getRow(79).getCell(j).setCellValue(balanceSheetDetail.getNonCurrentInvestments());
				sheet2.getRow(80).getCell(j).setCellValue(balanceSheetDetail.getInvestmentInSubsidiaries());
				sheet2.getRow(81).getCell(j).setCellValue(balanceSheetDetail.getInvestmentInAssociates());
				sheet2.getRow(82).getCell(j).setCellValue(balanceSheetDetail.getInvestmentInQuoted());
				sheet2.getRow(83).getCell(j).setCellValue(balanceSheetDetail.getShortTermLoansAndAdvances());
				sheet2.getRow(84).getCell(j).setCellValue(balanceSheetDetail.getOthersPlsSpecify());
				sheet2.getRow(85).getCell(j).setCellValue(balanceSheetDetail.getPreOperativeExpensesPending());
				sheet2.getRow(86).getCell(j).setCellValue(balanceSheetDetail.getAssetsInTransit());
				// sheet2.getRow(91).getCell(j).setCellValue(balanceSheetDetail.getCurrentS);

				sheet2.getRow(92).getCell(j).setCellValue(balanceSheetDetail.getCurrentInvestments());
				sheet2.getRow(93).getCell(j).setCellValue(balanceSheetDetail.getGovernmentAndOtherTrustee());
				sheet2.getRow(94).getCell(j).setCellValue(balanceSheetDetail.getFixedDepositsWithBanks());
				sheet2.getRow(95).getCell(j).setCellValue(balanceSheetDetail.getOthersPlsSpecify());
				sheet2.getRow(100).getCell(j).setCellValue(balanceSheetDetail.getInventory());
				sheet2.getRow(101).getCell(j).setCellValue(balanceSheetDetail.getRawMaterial());
				sheet2.getRow(102).getCell(j).setCellValue(balanceSheetDetail.getRawMaterialImported());
				sheet2.getRow(103).getCell(j).setCellValue(balanceSheetDetail.getRawMaterialIndegenous());
				sheet2.getRow(104).getCell(j).setCellValue(balanceSheetDetail.getStockInProcess());
				sheet2.getRow(105).getCell(j).setCellValue(balanceSheetDetail.getFinishedGoods());
				sheet2.getRow(106).getCell(j).setCellValue(balanceSheetDetail.getOtherConsumablesSpares());
				sheet2.getRow(107).getCell(j).setCellValue(balanceSheetDetail.getOtherConsumablesSparesImported());
				sheet2.getRow(108).getCell(j).setCellValue(balanceSheetDetail.getOtherConsumablesSparesIndegenous());
				sheet2.getRow(109).getCell(j).setCellValue(balanceSheetDetail.getTradeReceivables());
				sheet2.getRow(110).getCell(j).setCellValue(balanceSheetDetail.getExports());
				sheet2.getRow(111).getCell(j).setCellValue(balanceSheetDetail.getOtherThanExports());
				sheet2.getRow(112).getCell(j).setCellValue(balanceSheetDetail.getCashAndCashEquivalents());
				sheet2.getRow(113).getCell(j).setCellValue(balanceSheetDetail.getShortTermLoansAndAdvances());
				sheet2.getRow(118).getCell(j).setCellValue(balanceSheetDetail.getOthersPlsSpecify());
				sheet2.getRow(124).getCell(j).setCellValue(balanceSheetDetail.getDeferredTaxAsset());
				sheet2.getRow(125).getCell(j).setCellValue(balanceSheetDetail.getMiscExpences());
				// sheet2.getRow(127).getCell(j).setCellValue(balanceSheetDetail.get);

				j++;

			}			// Asset Ends
               documentResponse= fileUpload(wb, applicationId, productDocumentMappingId);
			
               logger.info("Exit with cmaFileGenerator() {} ", documentResponse);

		} catch (DocumentException| IllegalStateException | IOException | InvalidFormatException e) {
			System.err.println("Exception in cmaFileGenerator");
			e.printStackTrace();
		}
		return documentResponse;

	}

	public DocumentResponse fileUpload(Workbook wb, Long applicationId, Long productDocumentMappingId)
			throws IOException, DocumentException {
		DocumentRequest documentRequest = new DocumentRequest();
		
		File file = new File("excel_file.xlsx");
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		wb.write(fileOutputStream);
		fileOutputStream.close();
		String contentType = Files.probeContentType(file.toPath());
		documentRequest.setOriginalFileName(file.getName());
		documentRequest.setApplicationId(applicationId);
		documentRequest.setProductDocumentMappingId(productDocumentMappingId);
		documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);

		String json = com.capitaworld.cibil.api.utility.MultipleJSONObjectHelper.getStringfromObject(documentRequest);

		byte[] b = FileUtils.readFileToByteArray(file);
		MultipartFile multipartFile = new CommonMultiPartFile(b, file.getName(), contentType);
		
		logger.info("Exit with fileUpload() {} ");

		return  dmsClient.uploadFile(json, multipartFile);
	}

}
