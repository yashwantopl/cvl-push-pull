package com.capitaworld.service.loans.utils.bs;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import com.capitaworld.service.loans.utils.CommonUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.ProfitibilityStatementDetail;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ProfitibilityStatementDetailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProfitabilityStatementExcelReader {

	private static final Logger logger = LoggerFactory.getLogger(ProfitabilityStatementExcelReader.class);

	public static final ArrayList<String> PROFITABILITY_STATEMENT_MAPPING_LIST = new ArrayList<String>();
	public static final DecimalFormat decimalFormat = new DecimalFormat("#.##");

	public static void run(Long storageDetailsId, XSSFSheet sheet, LoanApplicationMaster loanApplicationMaster,
			ProfitibilityStatementDetailRepository profitibilityStatementDetailRepository) {
		PROFITABILITY_STATEMENT_MAPPING_LIST.clear();
		
		
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("7");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("8");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("9");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("10");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("11");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("12");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("13");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("14");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("15");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("16");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("17");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("18");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("19");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("20");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("21");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("22");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("23");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("24");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("26");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("27");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("28");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("29");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("30");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("31");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("32");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("33");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("34");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("35");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("36");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("37");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("38");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("39");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("40");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("41");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("42");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("43");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("44");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("45");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("46");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("47");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("48");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("49");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("50");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("51");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("52");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("53");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("55");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("56");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("57");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("59");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("61");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("63");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("65");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("66");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("67");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("68");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("69");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("70");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("71");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("72");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("73");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("75");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("76");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("77");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("79");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("81");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("82");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("83");
		PROFITABILITY_STATEMENT_MAPPING_LIST.add("85");
		
		/*
		 * this method extract data from excel associate column and row wise
		 * e.g. you want to extract B13,B14,... cell data for year 2014
		 */
		
		logger.info("ProfitabilityStatementExcelReader -----------> "+sheet.getRow(3).getCell(2).getNumericCellValue());
//        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, PROFITABILITY_STATEMENT_MAPPING_LIST,"C",String.valueOf(sheet.getRow(3).getCell(2).getNumericCellValue()),"Audited", profitibilityStatementDetailRepository);
//        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, PROFITABILITY_STATEMENT_MAPPING_LIST,"D",String.valueOf(sheet.getRow(3).getCell(3).getNumericCellValue()),"Audited",profitibilityStatementDetailRepository);
//        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, PROFITABILITY_STATEMENT_MAPPING_LIST,"E",String.valueOf(sheet.getRow(3).getCell(4).getNumericCellValue()),"Audited",profitibilityStatementDetailRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, PROFITABILITY_STATEMENT_MAPPING_LIST,"F",String.valueOf(sheet.getRow(3).getCell(5).getNumericCellValue()),"Estimated",profitibilityStatementDetailRepository);
        if(loanApplicationMaster.getProductId()!=15){
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, PROFITABILITY_STATEMENT_MAPPING_LIST,"G",String.valueOf(sheet.getRow(3).getCell(6).getNumericCellValue()), CommonUtils.PROJECTED, profitibilityStatementDetailRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, PROFITABILITY_STATEMENT_MAPPING_LIST,"H",String.valueOf(sheet.getRow(3).getCell(7).getNumericCellValue()),CommonUtils.PROJECTED,profitibilityStatementDetailRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, PROFITABILITY_STATEMENT_MAPPING_LIST,"I",String.valueOf(sheet.getRow(3).getCell(8).getNumericCellValue()),CommonUtils.PROJECTED,profitibilityStatementDetailRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, PROFITABILITY_STATEMENT_MAPPING_LIST,"J",String.valueOf(sheet.getRow(3).getCell(9).getNumericCellValue()),CommonUtils.PROJECTED,profitibilityStatementDetailRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, PROFITABILITY_STATEMENT_MAPPING_LIST,"K",String.valueOf(sheet.getRow(3).getCell(10).getNumericCellValue()),CommonUtils.PROJECTED,profitibilityStatementDetailRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, PROFITABILITY_STATEMENT_MAPPING_LIST,"L",String.valueOf(sheet.getRow(3).getCell(11).getNumericCellValue()),CommonUtils.PROJECTED,profitibilityStatementDetailRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, PROFITABILITY_STATEMENT_MAPPING_LIST,"M",String.valueOf(sheet.getRow(3).getCell(12).getNumericCellValue()),CommonUtils.PROJECTED,profitibilityStatementDetailRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, PROFITABILITY_STATEMENT_MAPPING_LIST,"N",String.valueOf(sheet.getRow(3).getCell(13).getNumericCellValue()),CommonUtils.PROJECTED,profitibilityStatementDetailRepository);
		}
		
		
	}

	public static void extractCellFromSheet(Long storageDetailsId, XSSFSheet sheet,
			LoanApplicationMaster loanApplicationMaster, ArrayList<String> arrayList, String column, String year,String financialYearlyStatement,
			ProfitibilityStatementDetailRepository profitibilityStatementDetailRepository) {
		int nullCounter = 0;
		int arrayListCounter = 0;
		// check user enter the data or not in column

		for (int i = 0; i < PROFITABILITY_STATEMENT_MAPPING_LIST.size(); i++) {
			if ((getNumericDataFromCell(sheet, column + PROFITABILITY_STATEMENT_MAPPING_LIST.get(i))) == 0) {
				++nullCounter;
			}
		}
		if (!(nullCounter == PROFITABILITY_STATEMENT_MAPPING_LIST.size())) {

			ProfitibilityStatementDetail bsProfitabilityStatement = new ProfitibilityStatementDetail();
			bsProfitabilityStatement.setApplicationId(loanApplicationMaster);
			bsProfitabilityStatement.setStorageDetailsId(storageDetailsId);

			bsProfitabilityStatement.setYear(year);
            bsProfitabilityStatement.setFinancialYearlyStatement(financialYearlyStatement);
			
			bsProfitabilityStatement.setSales(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setSalesExport(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setSalesDomestic(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setLessExciseDutyOrVatOrServiceTax(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));	
			bsProfitabilityStatement.setLessAnyOtherItem(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setLessItem1(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setLessItem2(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setLessItem3(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setLessItem4(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setLessItem5(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setNetSales(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setOtherOperatingRevenue(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setOtherOperatingRevenue1(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setOtherOperatingRevenue2(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setOtherOperatingRevenue3(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setOtherOperatingRevenue4(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setOtherOperatingRevenue5(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setGrossOperatingRevenue(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setOperatingExpenses(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setCostRawMaterialConsumed(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setRawMaterialImported(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setRawMaterialIndigenous(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setPurchasesStockTnTrade(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setIncreaseOrDecreaseInInventoryFg(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setClosingStockFg(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setOpeningStockOfFg(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setIncreaseOrDecreaseInInventoryWip(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setClosingStockWip(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setOpeningStockWip(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setEmployeeBenefitExpenses(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setFactoryWages(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setPersonnelCost(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setOtherExpenses(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setPowerAndFuel(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setStoreAndSpares(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setStoreAndSparesImported(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setStoreAndSparesIndeigenous(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setGeneralAdminExpenses(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setSellingDistributionExpenses(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
//			bsProfitabilityStatement.setAdminAndSellingExpenses(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setOtherPlsSpecify(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setExpensesCapitalised(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setExpenseCapitalized1(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setExpenseCapitalized2(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setExpenseCapitalized3(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setExpenseCapitalized4(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setOperatingProfitBeforeDepreciation(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setDepreciationAndAmortisation(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setDepreciation(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setAmortisation(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setOperatingProfitBeforeInterestAndTax(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setFinanceCost(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setOperatingProfitBeforeTax(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setNonOperatingIncome(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setNonOperatingExpenses(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setExtraordinaryItems(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setExtraordinaryItems1(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setExtraordinaryItems2(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setExtraordinaryItems3(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setExtraordinaryItems4(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setExtraordinaryItems5(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));	
			bsProfitabilityStatement.setProfitBeforeTax(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setProvisionForTax(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setCurrentTax(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setDeferredTax(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setProfitAfterTax(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setDividend(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setAlreadyPaid(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setBsProvision(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
			bsProfitabilityStatement.setRetainedProfit(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));

			bsProfitabilityStatement.setIsActive(true);
			bsProfitabilityStatement.setCreatedDate(new Date());
			bsProfitabilityStatement.setModifiedDate(new Date());
			// bsProfitabilityStatement.setCreatedBy(createdBy);
			// bsProfitabilityStatement.setModifiedBy(modifiedBy);

			profitibilityStatementDetailRepository.save(bsProfitabilityStatement);
		}
	}

	public static double getNumericDataFromCell(XSSFSheet sheet, String cellNumber) {
		CellReference cellReference = new CellReference(cellNumber);
		Row row = sheet.getRow(cellReference.getRow());
		Cell cell = row.getCell(cellReference.getCol());
		cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		return Double.parseDouble(decimalFormat.format(cell.getNumericCellValue()));

	}

	public static String getDataFromCell(XSSFSheet sheet, String cellNumber) {
		CellReference cellReference = new CellReference(cellNumber);
		Row row = sheet.getRow(cellReference.getRow());
		Cell cell = row.getCell(cellReference.getCol());
		cell.setCellType(Cell.CELL_TYPE_STRING);
		return cell.getStringCellValue();
	}

}
