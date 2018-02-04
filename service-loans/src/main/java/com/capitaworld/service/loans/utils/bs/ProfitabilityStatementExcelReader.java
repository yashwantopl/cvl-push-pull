package com.capitaworld.service.loans.utils.bs;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.ProfitibilityStatementDetail;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ProfitibilityStatementDetailRepository;

public class ProfitabilityStatementExcelReader {
	public static ArrayList<String> profitabilityStatementMappingList = new ArrayList<String>();
	public static final DecimalFormat decimalFormat = new DecimalFormat("#.##");

	public static void run(Long storageDetailsId, XSSFSheet sheet, LoanApplicationMaster loanApplicationMaster,
			ProfitibilityStatementDetailRepository profitibilityStatementDetailRepository) {
		profitabilityStatementMappingList.clear();
		
		
		profitabilityStatementMappingList.add("7");
		profitabilityStatementMappingList.add("8");
		profitabilityStatementMappingList.add("9");
		profitabilityStatementMappingList.add("10");
		profitabilityStatementMappingList.add("11");
		profitabilityStatementMappingList.add("12");
		profitabilityStatementMappingList.add("13");
		profitabilityStatementMappingList.add("14");
		profitabilityStatementMappingList.add("15");
		profitabilityStatementMappingList.add("16");
		profitabilityStatementMappingList.add("17");
		profitabilityStatementMappingList.add("18");
		profitabilityStatementMappingList.add("19");
		profitabilityStatementMappingList.add("20");
		profitabilityStatementMappingList.add("21");
		profitabilityStatementMappingList.add("22");
		profitabilityStatementMappingList.add("23");
		profitabilityStatementMappingList.add("24");
		profitabilityStatementMappingList.add("26");
		profitabilityStatementMappingList.add("27");
		profitabilityStatementMappingList.add("28");
		profitabilityStatementMappingList.add("29");
		profitabilityStatementMappingList.add("30");
		profitabilityStatementMappingList.add("31");
		profitabilityStatementMappingList.add("32");
		profitabilityStatementMappingList.add("33");
		profitabilityStatementMappingList.add("34");
		profitabilityStatementMappingList.add("35");
		profitabilityStatementMappingList.add("36");
		profitabilityStatementMappingList.add("37");
		profitabilityStatementMappingList.add("38");
		profitabilityStatementMappingList.add("39");
		profitabilityStatementMappingList.add("40");
		profitabilityStatementMappingList.add("41");
		profitabilityStatementMappingList.add("42");
		profitabilityStatementMappingList.add("43");
		profitabilityStatementMappingList.add("44");
		profitabilityStatementMappingList.add("45");
		profitabilityStatementMappingList.add("46");
		profitabilityStatementMappingList.add("47");
		profitabilityStatementMappingList.add("48");
		profitabilityStatementMappingList.add("49");
		profitabilityStatementMappingList.add("50");
		profitabilityStatementMappingList.add("51");
		profitabilityStatementMappingList.add("52");
		profitabilityStatementMappingList.add("53");
		profitabilityStatementMappingList.add("55");
		profitabilityStatementMappingList.add("56");
		profitabilityStatementMappingList.add("57");
		profitabilityStatementMappingList.add("59");
		profitabilityStatementMappingList.add("61");
		profitabilityStatementMappingList.add("63");
		profitabilityStatementMappingList.add("65");
		profitabilityStatementMappingList.add("66");
		profitabilityStatementMappingList.add("67");
		profitabilityStatementMappingList.add("68");
		profitabilityStatementMappingList.add("69");
		profitabilityStatementMappingList.add("70");
		profitabilityStatementMappingList.add("71");
		profitabilityStatementMappingList.add("72");
		profitabilityStatementMappingList.add("73");
		profitabilityStatementMappingList.add("75");
		profitabilityStatementMappingList.add("76");
		profitabilityStatementMappingList.add("77");
		profitabilityStatementMappingList.add("79");
		profitabilityStatementMappingList.add("81");
		profitabilityStatementMappingList.add("82");
		profitabilityStatementMappingList.add("83");
		profitabilityStatementMappingList.add("85");
		
		/*
		 * this method extract data from excel associate column and row wise
		 * e.g. you want to extract B13,B14,... cell data for year 2014
		 */
		extractCellFromSheet(storageDetailsId, sheet, loanApplicationMaster, profitabilityStatementMappingList, "C",
				"2015", profitibilityStatementDetailRepository);
		extractCellFromSheet(storageDetailsId, sheet, loanApplicationMaster, profitabilityStatementMappingList, "D",
				"2016", profitibilityStatementDetailRepository);
		extractCellFromSheet(storageDetailsId, sheet, loanApplicationMaster, profitabilityStatementMappingList, "E",
				"2017", profitibilityStatementDetailRepository);
		extractCellFromSheet(storageDetailsId, sheet, loanApplicationMaster, profitabilityStatementMappingList, "F",
				"2018", profitibilityStatementDetailRepository);
	
		if(loanApplicationMaster.getProductId()!=15){
		extractCellFromSheet(storageDetailsId, sheet, loanApplicationMaster, profitabilityStatementMappingList, "G",
				"2019", profitibilityStatementDetailRepository);
		extractCellFromSheet(storageDetailsId, sheet, loanApplicationMaster, profitabilityStatementMappingList, "H",
				"2020", profitibilityStatementDetailRepository);
		extractCellFromSheet(storageDetailsId, sheet, loanApplicationMaster, profitabilityStatementMappingList, "I",
				"2021", profitibilityStatementDetailRepository);
		extractCellFromSheet(storageDetailsId, sheet, loanApplicationMaster, profitabilityStatementMappingList, "J",
				"2022", profitibilityStatementDetailRepository);
		extractCellFromSheet(storageDetailsId, sheet, loanApplicationMaster, profitabilityStatementMappingList, "K",
				"2023", profitibilityStatementDetailRepository);
		extractCellFromSheet(storageDetailsId, sheet, loanApplicationMaster, profitabilityStatementMappingList, "L",
				"2024", profitibilityStatementDetailRepository);
		extractCellFromSheet(storageDetailsId, sheet, loanApplicationMaster, profitabilityStatementMappingList, "M",
				"2025", profitibilityStatementDetailRepository);
		extractCellFromSheet(storageDetailsId, sheet, loanApplicationMaster, profitabilityStatementMappingList, "N",
				"2026", profitibilityStatementDetailRepository);
		}
	}

	public static void extractCellFromSheet(Long storageDetailsId, XSSFSheet sheet,
			LoanApplicationMaster loanApplicationMaster, ArrayList arrayList, String column, String year,
			ProfitibilityStatementDetailRepository profitibilityStatementDetailRepository) {
		int nullCounter = 0;
		int arrayListCounter = 0;
		// check user enter the data or not in column

		for (int i = 0; i < profitabilityStatementMappingList.size(); i++) {
			if ((getNumericDataFromCell(sheet, column + profitabilityStatementMappingList.get(i))) == 0) {
				++nullCounter;
			}
		}
		if (!(nullCounter == profitabilityStatementMappingList.size())) {

			ProfitibilityStatementDetail bsProfitabilityStatement = new ProfitibilityStatementDetail();
			bsProfitabilityStatement.setApplicationId(loanApplicationMaster);
			bsProfitabilityStatement.setStorageDetailsId(storageDetailsId);

			bsProfitabilityStatement.setYear(year);

		
			
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
