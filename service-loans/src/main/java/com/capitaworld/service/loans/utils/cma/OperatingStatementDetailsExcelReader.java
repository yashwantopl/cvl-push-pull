package com.capitaworld.service.loans.utils.cma;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.capitaworld.service.loans.exceptions.ExcelException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.OperatingStatementDetails;
import com.capitaworld.service.loans.repository.fundseeker.corporate.OperatingStatementDetailsRepository;
import com.capitaworld.service.loans.utils.CommonUtils;

public class OperatingStatementDetailsExcelReader {

    private OperatingStatementDetailsExcelReader() {
        // Do nothing because of X and Y.
    }

	public static final Logger log = LoggerFactory.getLogger(OperatingStatementDetailsExcelReader.class);
    public static final List<String> OPERATING_STATEMENT_MAPPING_LIST = new ArrayList<String>();
    public static final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public static void run(Long storageDetailsId,XSSFSheet sheet,LoanApplicationMaster loanApplicationMaster,OperatingStatementDetailsRepository operatingStatementDetailsRepository) throws Exception {
           	OPERATING_STATEMENT_MAPPING_LIST.clear();
            OPERATING_STATEMENT_MAPPING_LIST.add("8");
            OPERATING_STATEMENT_MAPPING_LIST.add("9");
            OPERATING_STATEMENT_MAPPING_LIST.add("10");
            OPERATING_STATEMENT_MAPPING_LIST.add("11");
            OPERATING_STATEMENT_MAPPING_LIST.add("13");
            OPERATING_STATEMENT_MAPPING_LIST.add("14");
            OPERATING_STATEMENT_MAPPING_LIST.add("15");
            OPERATING_STATEMENT_MAPPING_LIST.add("17");
            //19
            OPERATING_STATEMENT_MAPPING_LIST.add("20");
            OPERATING_STATEMENT_MAPPING_LIST.add("21");
            OPERATING_STATEMENT_MAPPING_LIST.add("22");
            OPERATING_STATEMENT_MAPPING_LIST.add("24");
            OPERATING_STATEMENT_MAPPING_LIST.add("25");
            OPERATING_STATEMENT_MAPPING_LIST.add("26");
            OPERATING_STATEMENT_MAPPING_LIST.add("28");
            OPERATING_STATEMENT_MAPPING_LIST.add("30");
            OPERATING_STATEMENT_MAPPING_LIST.add("32");
            OPERATING_STATEMENT_MAPPING_LIST.add("34");
            OPERATING_STATEMENT_MAPPING_LIST.add("36");
            OPERATING_STATEMENT_MAPPING_LIST.add("38");
            OPERATING_STATEMENT_MAPPING_LIST.add("40");
            OPERATING_STATEMENT_MAPPING_LIST.add("42");
            OPERATING_STATEMENT_MAPPING_LIST.add("44");
            OPERATING_STATEMENT_MAPPING_LIST.add("46");
            OPERATING_STATEMENT_MAPPING_LIST.add("48");
            OPERATING_STATEMENT_MAPPING_LIST.add("50");
            OPERATING_STATEMENT_MAPPING_LIST.add("52");
            OPERATING_STATEMENT_MAPPING_LIST.add("54");
            OPERATING_STATEMENT_MAPPING_LIST.add("56");
            OPERATING_STATEMENT_MAPPING_LIST.add("58");
            OPERATING_STATEMENT_MAPPING_LIST.add("60");
            OPERATING_STATEMENT_MAPPING_LIST.add("62");
            OPERATING_STATEMENT_MAPPING_LIST.add("64");
            OPERATING_STATEMENT_MAPPING_LIST.add("66");
            OPERATING_STATEMENT_MAPPING_LIST.add("67");
            OPERATING_STATEMENT_MAPPING_LIST.add("68");
            OPERATING_STATEMENT_MAPPING_LIST.add("69");
            OPERATING_STATEMENT_MAPPING_LIST.add("70");
            OPERATING_STATEMENT_MAPPING_LIST.add("71");
            OPERATING_STATEMENT_MAPPING_LIST.add("73");
            OPERATING_STATEMENT_MAPPING_LIST.add("75");
            OPERATING_STATEMENT_MAPPING_LIST.add("76");
            OPERATING_STATEMENT_MAPPING_LIST.add("77");
            OPERATING_STATEMENT_MAPPING_LIST.add("78");
            OPERATING_STATEMENT_MAPPING_LIST.add("80");
            OPERATING_STATEMENT_MAPPING_LIST.add("82");
            OPERATING_STATEMENT_MAPPING_LIST.add("84");
            OPERATING_STATEMENT_MAPPING_LIST.add("86");

            /*
              * this method extract data from excel associate column and row wise
              * e.g. you want to extract B13,B14,... cell data for year 2014
             */
            
             
            log.info("OperatingStatementDetailsExcelReader -----------> "+sheet.getRow(4).getCell(1).getNumericCellValue());       
           

            //extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, OPERATING_STATEMENT_MAPPING_LIST,"B",String.valueOf(sheet.getRow(4).getCell(1).getNumericCellValue()),"Audited", operatingStatementDetailsRepository);
            //extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, OPERATING_STATEMENT_MAPPING_LIST,"C",String.valueOf(sheet.getRow(4).getCell(2).getNumericCellValue()),"Audited",operatingStatementDetailsRepository);
            //extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, OPERATING_STATEMENT_MAPPING_LIST,"D",String.valueOf(sheet.getRow(4).getCell(3).getNumericCellValue()),"Audited",operatingStatementDetailsRepository);
            //j== 2 for NTB 
            int j = 2;
         if(loanApplicationMaster.getBusinessTypeId() == CommonUtils.BusinessType.EXISTING_BUSINESS.getId()) {
        	 
        	 int updateRow = operatingStatementDetailsRepository.inActiveByAppIdAndFinancialYearlyStatementAndIsActive(loanApplicationMaster.getId());
        	 log.info("---------------- inactive old estimate and project data ------- updated row "+ updateRow);
        	 
        	 extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, OPERATING_STATEMENT_MAPPING_LIST,"E",String.valueOf(sheet.getRow(4).getCell(4).getNumericCellValue()),"Estimated",operatingStatementDetailsRepository);
        	 j=5; 
          
        }
        if(loanApplicationMaster.getProductId()!=15 && loanApplicationMaster.getProductId()!=1 ){
        	/*int j = 5;*/

        	for(int i = 0; i < loanApplicationMaster.getTenure(); i++) {
        		extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, OPERATING_STATEMENT_MAPPING_LIST, CellReference.convertNumToColString(sheet.getRow(4).getCell(j).getColumnIndex()),String.valueOf(sheet.getRow(4).getCell(j).getNumericCellValue()),"Projected", operatingStatementDetailsRepository);
        		j++;
        	}

       	
        /*  extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, OPERATING_STATEMENT_MAPPING_LIST,"F",String.valueOf(sheet.getRow(4).getCell(5).getNumericCellValue()),"Projected", operatingStatementDetailsRepository);

        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, OPERATING_STATEMENT_MAPPING_LIST,"G",String.valueOf(sheet.getRow(4).getCell(6).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, OPERATING_STATEMENT_MAPPING_LIST,"H",String.valueOf(sheet.getRow(4).getCell(7).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, OPERATING_STATEMENT_MAPPING_LIST,"I",String.valueOf(sheet.getRow(4).getCell(8).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, OPERATING_STATEMENT_MAPPING_LIST,"J",String.valueOf(sheet.getRow(4).getCell(9).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, OPERATING_STATEMENT_MAPPING_LIST,"K",String.valueOf(sheet.getRow(4).getCell(10).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, OPERATING_STATEMENT_MAPPING_LIST,"L",String.valueOf(sheet.getRow(4).getCell(11).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, OPERATING_STATEMENT_MAPPING_LIST,"M",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, OPERATING_STATEMENT_MAPPING_LIST,"N",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, OPERATING_STATEMENT_MAPPING_LIST,"O",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, OPERATING_STATEMENT_MAPPING_LIST,"P",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, OPERATING_STATEMENT_MAPPING_LIST,"Q",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, OPERATING_STATEMENT_MAPPING_LIST,"R",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, OPERATING_STATEMENT_MAPPING_LIST,"S",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, OPERATING_STATEMENT_MAPPING_LIST,"T",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, OPERATING_STATEMENT_MAPPING_LIST,"U",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, OPERATING_STATEMENT_MAPPING_LIST,"V",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, OPERATING_STATEMENT_MAPPING_LIST,"W",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, OPERATING_STATEMENT_MAPPING_LIST,"X",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, OPERATING_STATEMENT_MAPPING_LIST,"Y",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",operatingStatementDetailsRepository);
        */}
    }

    public static void extractCellFromSheet(Long storageDetailsId,XSSFSheet sheet,LoanApplicationMaster loanApplicationMaster,List<String> arrayList,String column,String year,String financialYearlyStatement,OperatingStatementDetailsRepository operatingStatementDetailsRepository) throws ExcelException
    {
        int arrayListCounter = 0;
        int nullCounter=0;
        for (int i = 0; i < OPERATING_STATEMENT_MAPPING_LIST.size(); i++) {
            if ((getNumericDataFromCell(sheet,column + OPERATING_STATEMENT_MAPPING_LIST.get(i)))==0.0) {
                ++nullCounter;
            }
        }
        log.info("nullCounter---" + nullCounter);
        if(!(nullCounter==46||nullCounter==47)) {
        	
        	Double yearFromSheet  = Double.valueOf(year) ; 
           	OperatingStatementDetails operatingStatementDetails = operatingStatementDetailsRepository.findByLoanApplicationMasterIdAndYearAndFinancialYearlyStatementAndIsActive(loanApplicationMaster.getId(), String.valueOf(yearFromSheet.longValue()) ,  financialYearlyStatement , true );
           	
           	if(operatingStatementDetails != null &&  "Audited".equalsIgnoreCase(operatingStatementDetails.getFinancialYearlyStatement()) && yearFromSheet <= Double.valueOf(operatingStatementDetails.getYear()) ) {
           		
           		throw new ExcelException("Invalid cma details");
         
           	}
           		
           	operatingStatementDetails = new OperatingStatementDetails() ;
           	operatingStatementDetails.setCreatedDate(new Date());
        	operatingStatementDetails.setModifiedDate(new Date());
            operatingStatementDetails.setLoanApplicationMaster(loanApplicationMaster);
            operatingStatementDetails.setStorageDetailsId(storageDetailsId);
            
            operatingStatementDetails.setYear(CommonUtils.getCMAFilterYear(year));
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
            operatingStatementDetails.setOtherIncomeNeedTocCheckOp(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setProvisionForDeferredTax(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setNetProfitOrLoss(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setEquityDeividendPaidAmt(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setDividendRate(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setRetainedProfit(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            operatingStatementDetails.setRetainedProfitOrNetProfit(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));

            operatingStatementDetails.setIsActive(true);
            
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

