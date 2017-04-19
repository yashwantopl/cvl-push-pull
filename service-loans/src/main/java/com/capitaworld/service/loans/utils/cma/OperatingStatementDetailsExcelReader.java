package com.capitaworld.service.loans.utils.cma;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.OperatingStatementDetails;
import com.capitaworld.service.loans.repository.fundseeker.corporate.OperatingStatementDetailsRepository;

public class OperatingStatementDetailsExcelReader {
    public static ArrayList<String> operatingStatementMappingList = new ArrayList<String>();
    public static final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public static void run(XSSFSheet sheet,LoanApplicationMaster loanApplicationMaster,OperatingStatementDetailsRepository operatingStatementDetailsRepository) {
            operatingStatementMappingList.clear();
            operatingStatementMappingList.add("9");
            operatingStatementMappingList.add("10");
            operatingStatementMappingList.add("11");
            operatingStatementMappingList.add("12");
            operatingStatementMappingList.add("14");
            operatingStatementMappingList.add("15");
            operatingStatementMappingList.add("16");
            operatingStatementMappingList.add("18");
            operatingStatementMappingList.add("21");
            operatingStatementMappingList.add("22");
            operatingStatementMappingList.add("23");
            operatingStatementMappingList.add("25");
            operatingStatementMappingList.add("26");
            operatingStatementMappingList.add("27");
            operatingStatementMappingList.add("29");
            operatingStatementMappingList.add("31");
            operatingStatementMappingList.add("33");
            operatingStatementMappingList.add("35");
            operatingStatementMappingList.add("37");
            operatingStatementMappingList.add("39");
            operatingStatementMappingList.add("41");
            operatingStatementMappingList.add("43");
            operatingStatementMappingList.add("45");
            operatingStatementMappingList.add("47");
            operatingStatementMappingList.add("49");
            operatingStatementMappingList.add("51");
            operatingStatementMappingList.add("53");
            operatingStatementMappingList.add("55");
            operatingStatementMappingList.add("57");
            operatingStatementMappingList.add("59");
            operatingStatementMappingList.add("61");
            operatingStatementMappingList.add("63");
            operatingStatementMappingList.add("65");
            operatingStatementMappingList.add("66");
            operatingStatementMappingList.add("67");
            operatingStatementMappingList.add("68");
            operatingStatementMappingList.add("69");
            operatingStatementMappingList.add("70");
            operatingStatementMappingList.add("72");
            operatingStatementMappingList.add("74");
            operatingStatementMappingList.add("76");
            operatingStatementMappingList.add("77");
            operatingStatementMappingList.add("79");
            operatingStatementMappingList.add("81");
            operatingStatementMappingList.add("83");
            operatingStatementMappingList.add("85");

            /*
              * this method extract data from excel associate column and row wise
              * e.g. you want to extract B13,B14,... cell data for year 2014
             */
        extractCellFromSheet(sheet,loanApplicationMaster, operatingStatementMappingList,"B","2014",operatingStatementDetailsRepository);
        extractCellFromSheet(sheet,loanApplicationMaster, operatingStatementMappingList,"C","2015",operatingStatementDetailsRepository);
        extractCellFromSheet(sheet,loanApplicationMaster, operatingStatementMappingList,"D","2016",operatingStatementDetailsRepository);
        extractCellFromSheet(sheet,loanApplicationMaster, operatingStatementMappingList,"E","2017",operatingStatementDetailsRepository);
        extractCellFromSheet(sheet,loanApplicationMaster, operatingStatementMappingList,"F","2018",operatingStatementDetailsRepository);
        extractCellFromSheet(sheet,loanApplicationMaster, operatingStatementMappingList,"G","2019",operatingStatementDetailsRepository);
        extractCellFromSheet(sheet,loanApplicationMaster, operatingStatementMappingList,"H","2020",operatingStatementDetailsRepository);
        extractCellFromSheet(sheet,loanApplicationMaster, operatingStatementMappingList,"I","2021",operatingStatementDetailsRepository);
        extractCellFromSheet(sheet,loanApplicationMaster, operatingStatementMappingList,"J","2022",operatingStatementDetailsRepository);
        extractCellFromSheet(sheet,loanApplicationMaster, operatingStatementMappingList,"K","2023",operatingStatementDetailsRepository);
        extractCellFromSheet(sheet,loanApplicationMaster, operatingStatementMappingList,"L","2024",operatingStatementDetailsRepository);
        extractCellFromSheet(sheet,loanApplicationMaster, operatingStatementMappingList,"M","2025",operatingStatementDetailsRepository);
        

    }

    public static void extractCellFromSheet(XSSFSheet sheet,LoanApplicationMaster loanApplicationMaster,ArrayList<String> arrayList,String column,String year,OperatingStatementDetailsRepository operatingStatementDetailsRepository)
    {
        int arrayListCounter = 0;
        int nullCounter=0;
        for (int i = 0; i < operatingStatementMappingList.size(); i++) {
           // System.out.println(getNumericDataFromCell(sheet,column+assetsMappingList.get(i)));
            if ((getNumericDataFromCell(sheet,column + operatingStatementMappingList.get(i)))==0.0) {
                ++nullCounter;
            }
        }
        System.out.println(nullCounter);
        if(!(nullCounter==45||nullCounter==46)) {
            OperatingStatementDetails operatingStatementDetails = new OperatingStatementDetails();
            
            //operatingStatementDetails.setLoanApplicationMaster(loanApplicationMaster);
           
            operatingStatementDetails.setYear(year);

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
