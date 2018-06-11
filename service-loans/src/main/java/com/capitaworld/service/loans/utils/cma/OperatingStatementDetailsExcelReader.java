package com.capitaworld.service.loans.utils.cma;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.OperatingStatementDetails;
import com.capitaworld.service.loans.repository.fundseeker.corporate.OperatingStatementDetailsRepository;

public class OperatingStatementDetailsExcelReader {
	public static final Logger log = LoggerFactory.getLogger(OperatingStatementDetailsExcelReader.class);
    public static List<String> operatingStatementMappingList = new ArrayList<String>();
    public static final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public static void run(Long storageDetailsId,XSSFSheet sheet,LoanApplicationMaster loanApplicationMaster,OperatingStatementDetailsRepository operatingStatementDetailsRepository) {
            operatingStatementMappingList.clear();
            operatingStatementMappingList.add("8");
            operatingStatementMappingList.add("9");
            operatingStatementMappingList.add("10");
            operatingStatementMappingList.add("11");
            operatingStatementMappingList.add("13");
            operatingStatementMappingList.add("14");
            operatingStatementMappingList.add("15");
            operatingStatementMappingList.add("17");
            //19
            operatingStatementMappingList.add("20");
            operatingStatementMappingList.add("21");
            operatingStatementMappingList.add("22");
            operatingStatementMappingList.add("24");
            operatingStatementMappingList.add("25");
            operatingStatementMappingList.add("26");
            operatingStatementMappingList.add("28");
            operatingStatementMappingList.add("30");
            operatingStatementMappingList.add("32");
            operatingStatementMappingList.add("34");
            operatingStatementMappingList.add("36");
            operatingStatementMappingList.add("38");
            operatingStatementMappingList.add("40");
            operatingStatementMappingList.add("42");
            operatingStatementMappingList.add("44");
            operatingStatementMappingList.add("46");
            operatingStatementMappingList.add("48");
            operatingStatementMappingList.add("50");
            operatingStatementMappingList.add("52");
            operatingStatementMappingList.add("54");
            operatingStatementMappingList.add("56");
            operatingStatementMappingList.add("58");
            operatingStatementMappingList.add("60");
            operatingStatementMappingList.add("62");
            operatingStatementMappingList.add("64");
            operatingStatementMappingList.add("66");
            operatingStatementMappingList.add("67");
            operatingStatementMappingList.add("68");
            operatingStatementMappingList.add("69");
            operatingStatementMappingList.add("70");
            operatingStatementMappingList.add("71");
            operatingStatementMappingList.add("73");
            operatingStatementMappingList.add("75");
            operatingStatementMappingList.add("77");
            operatingStatementMappingList.add("78");
            operatingStatementMappingList.add("80");
            operatingStatementMappingList.add("82");
            operatingStatementMappingList.add("84");
            operatingStatementMappingList.add("86");

            /*
              * this method extract data from excel associate column and row wise
              * e.g. you want to extract B13,B14,... cell data for year 2014
             */
            
             
        System.out.println("OperatingStatementDetailsExcelReader -----------> "+sheet.getRow(4).getCell(1).getNumericCellValue());       
//        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, operatingStatementMappingList,"B",String.valueOf(sheet.getRow(4).getCell(1).getNumericCellValue()),"Audited", operatingStatementDetailsRepository);
//        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, operatingStatementMappingList,"C",String.valueOf(sheet.getRow(4).getCell(2).getNumericCellValue()),"Audited",operatingStatementDetailsRepository);
//        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, operatingStatementMappingList,"D",String.valueOf(sheet.getRow(4).getCell(3).getNumericCellValue()),"Audited",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, operatingStatementMappingList,"E",String.valueOf(sheet.getRow(4).getCell(4).getNumericCellValue()),"Estimated",operatingStatementDetailsRepository);
        if(loanApplicationMaster.getProductId()!=15 && loanApplicationMaster.getProductId()!=1 ){
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, operatingStatementMappingList,"F",String.valueOf(sheet.getRow(4).getCell(5).getNumericCellValue()),"Projected", operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, operatingStatementMappingList,"G",String.valueOf(sheet.getRow(4).getCell(6).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, operatingStatementMappingList,"H",String.valueOf(sheet.getRow(4).getCell(7).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, operatingStatementMappingList,"I",String.valueOf(sheet.getRow(4).getCell(8).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, operatingStatementMappingList,"J",String.valueOf(sheet.getRow(4).getCell(9).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, operatingStatementMappingList,"K",String.valueOf(sheet.getRow(4).getCell(10).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, operatingStatementMappingList,"L",String.valueOf(sheet.getRow(4).getCell(11).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, operatingStatementMappingList,"M",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, operatingStatementMappingList,"N",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, operatingStatementMappingList,"O",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, operatingStatementMappingList,"P",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, operatingStatementMappingList,"Q",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, operatingStatementMappingList,"R",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, operatingStatementMappingList,"S",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, operatingStatementMappingList,"T",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, operatingStatementMappingList,"U",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, operatingStatementMappingList,"V",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, operatingStatementMappingList,"W",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, operatingStatementMappingList,"X",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, operatingStatementMappingList,"Y",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        }
    }

    public static void extractCellFromSheet(Long storageDetailsId,XSSFSheet sheet,LoanApplicationMaster loanApplicationMaster,List<String> arrayList,String column,String year,String financialYearlyStatement,OperatingStatementDetailsRepository operatingStatementDetailsRepository)
    {
        int arrayListCounter = 0;
        int nullCounter=0;
        for (int i = 0; i < operatingStatementMappingList.size(); i++) {
            if ((getNumericDataFromCell(sheet,column + operatingStatementMappingList.get(i)))==0.0) {
                ++nullCounter;
            }
        }
        System.out.println(nullCounter);
        if(!(nullCounter==46||nullCounter==47)) {
            OperatingStatementDetails operatingStatementDetails = new OperatingStatementDetails();
            
            operatingStatementDetails.setLoanApplicationMaster(loanApplicationMaster);
            operatingStatementDetails.setStorageDetailsId(storageDetailsId);
     
            operatingStatementDetails.setYear(year);
            operatingStatementDetails.setFinancialYearlyStatement(financialYearlyStatement);
            operatingStatementDetails.setDomesticSales(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setExportSales(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setAddOtherRevenueIncome(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setTotalGrossSales(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setLessExciseDuty(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setDeductOtherItems(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setNetSales(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setPercentageRiseOrFall(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setRawMaterials(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setRawMaterialsImported(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setRawMaterialsIndigenous(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setOtherSpares(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setOtherSparesImported(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setOtherSparesIndigenous(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setPowerAndFuel(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setDirectLabour(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setOtherMfgExpenses(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setDepreciation(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setSubTotalCostSales(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setAddOperatingStock(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setSubTotalOfCostSalesAndOperatingStock(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setDeductStockInProcess(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setProductionCost(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setAddOperatingStockFg(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setSubTotalDeductAndCostOfProduction(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setDeductClStockFg(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setTotalCostSales(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setSellingAndDistributionExpenses(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setSellingGenlAdmnExpenses(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setSubTotalCostSalesAndSelling(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setOpProfitBeforeIntrest(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setInterest(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setOpProfitAfterInterest(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setAddOtherNonOpIncome(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            
            operatingStatementDetails.setSubTotalOfIncome(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setDeductOtherNonOpExp(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setSubTotalExpenses(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setNetofNonOpIncomeOrExpenses(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setExpensesAmortised(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setProfitBeforeTaxOrLoss(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setProvisionForTaxes(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setProvisionForDeferredTax(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setNetProfitOrLoss(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setEquityDeividendPaidAmt(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setDividendRate(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setRetainedProfit(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setRetainedProfitOrNetProfit(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));

            operatingStatementDetails.setIsActive(true);
            operatingStatementDetails.setCreatedDate(new Date());
            operatingStatementDetails.setModifiedDate(new Date());
//          operatingStatementDetails.setCreatedBy(createdBy);
//          operatingStatementDetails.setModifiedBy(modifiedBy);
            
          	operatingStatementDetailsRepository.save(operatingStatementDetails);
        }
    }
    public static double getNumericDataFromCell(XSSFSheet sheet,String cellNumber)
    {
    	log.info("getNumericDataFromCell:"+cellNumber );
        CellReference cellReference = new CellReference(cellNumber);
        Row row = sheet.getRow(cellReference.getRow());
        Cell cell = row.getCell(cellReference.getCol());
        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
        return Double.parseDouble(decimalFormat.format(cell.getNumericCellValue()));

    }
    public static String getDataFromCell(XSSFSheet sheet,String cellNumber)
    {
        CellReference cellReference = new CellReference(cellNumber);
        Row row = sheet.getRow(cellReference.getRow());
        Cell cell = row.getCell(cellReference.getCol());
        cell.setCellType(Cell.CELL_TYPE_STRING);
        return cell.getStringCellValue();

    }
}
