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
import com.capitaworld.service.loans.domain.fundseeker.corporate.LiabilitiesDetails;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LiabilitiesDetailsRepository;
import com.capitaworld.service.loans.utils.CommonUtils;

public class LiabilitiesDetailsExcelReader
{
    private LiabilitiesDetailsExcelReader() {
        // Do nothing because of X and Y.
    }
	public static final Logger log = LoggerFactory.getLogger(LiabilitiesDetailsExcelReader.class);
    public static final List<String> LIABILITIES_MAPPING_LIST = new ArrayList<String>();
    public static final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public static void run(Long storageDetailsId,XSSFSheet sheet,LoanApplicationMaster loanApplicationMaster,LiabilitiesDetailsRepository liabilitiesDetailsRepository) throws Exception {
        LIABILITIES_MAPPING_LIST.clear();
        LIABILITIES_MAPPING_LIST.add("11");
        LIABILITIES_MAPPING_LIST.add("12");
        LIABILITIES_MAPPING_LIST.add("13");
        LIABILITIES_MAPPING_LIST.add("15");
        LIABILITIES_MAPPING_LIST.add("17");
        LIABILITIES_MAPPING_LIST.add("19");
        LIABILITIES_MAPPING_LIST.add("21");
        LIABILITIES_MAPPING_LIST.add("23");
        LIABILITIES_MAPPING_LIST.add("25");
        LIABILITIES_MAPPING_LIST.add("27");
        LIABILITIES_MAPPING_LIST.add("29");
        LIABILITIES_MAPPING_LIST.add("32");
        LIABILITIES_MAPPING_LIST.add("35");
        LIABILITIES_MAPPING_LIST.add("37");
        LIABILITIES_MAPPING_LIST.add("41");
        LIABILITIES_MAPPING_LIST.add("43");
        LIABILITIES_MAPPING_LIST.add("45");
        LIABILITIES_MAPPING_LIST.add("46");
        LIABILITIES_MAPPING_LIST.add("47");
        LIABILITIES_MAPPING_LIST.add("49");
        LIABILITIES_MAPPING_LIST.add("51");
        LIABILITIES_MAPPING_LIST.add("53");
        LIABILITIES_MAPPING_LIST.add("55");
        LIABILITIES_MAPPING_LIST.add("57");
        LIABILITIES_MAPPING_LIST.add("58");
        LIABILITIES_MAPPING_LIST.add("59");
        LIABILITIES_MAPPING_LIST.add("60");
        LIABILITIES_MAPPING_LIST.add("61");
        LIABILITIES_MAPPING_LIST.add("63");
        LIABILITIES_MAPPING_LIST.add("67");
        LIABILITIES_MAPPING_LIST.add("69");
        LIABILITIES_MAPPING_LIST.add("71");
        LIABILITIES_MAPPING_LIST.add("73");
        LIABILITIES_MAPPING_LIST.add("75");
        LIABILITIES_MAPPING_LIST.add("77");
        LIABILITIES_MAPPING_LIST.add("79");
        LIABILITIES_MAPPING_LIST.add("81");
        LIABILITIES_MAPPING_LIST.add("83");
        LIABILITIES_MAPPING_LIST.add("85");
        LIABILITIES_MAPPING_LIST.add("86");
        LIABILITIES_MAPPING_LIST.add("87");
        

            /*
              * this method extract data from excel associate column and row wise
              * e.g. you want to extract B13,B14,... cell data for year 2014
             */
        log.info("OperatingStatementDetailsExcelReader -----------> "+ sheet.getRow(4).getCell(1).getNumericCellValue());       
//        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, LIABILITIES_MAPPING_LIST,"B",String.valueOf(sheet.getRow(4).getCell(1).getNumericCellValue()) ,"Audited", liabilitiesDetailsRepository);
//        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, LIABILITIES_MAPPING_LIST,"C",String.valueOf(sheet.getRow(4).getCell(2).getNumericCellValue())  ,"Audited", liabilitiesDetailsRepository);
//        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, LIABILITIES_MAPPING_LIST,"D",String.valueOf(sheet.getRow(4).getCell(3).getNumericCellValue())  ,"Audited", liabilitiesDetailsRepository);
        
        //j== 2 for NTB 
        int j = 2;
        if(loanApplicationMaster.getBusinessTypeId() == CommonUtils.BusinessType.EXISTING_BUSINESS.getId()) {
        	
       		int updateRow = liabilitiesDetailsRepository.inActiveByAppIdAndFinancialYearlyStatementAndIsActive(loanApplicationMaster.getId());
       		log.info("---------------- inactive old estimate and project data ------- updated row "+ updateRow);
       		
       		extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, LIABILITIES_MAPPING_LIST,"E",String.valueOf(sheet.getRow(4).getCell(4).getNumericCellValue()),"Estimated", liabilitiesDetailsRepository);
           	j=5;
           	
      
        }
        if(loanApplicationMaster.getProductId()!=15 && loanApplicationMaster.getProductId()!=1 ){

        	/*int j = 5;*/

        	for(int i = 0; i < loanApplicationMaster.getTenure(); i++) { 
        		
        		extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, LIABILITIES_MAPPING_LIST,CellReference.convertNumToColString(sheet.getRow(4).getCell(j).getColumnIndex()),String.valueOf(sheet.getRow(4).getCell(j).getNumericCellValue()),"Projected", liabilitiesDetailsRepository);
        		j++;
        	
        	}
        /*extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, LIABILITIES_MAPPING_LIST,"F",String.valueOf(sheet.getRow(4).getCell(5).getNumericCellValue()),"Projected", liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, LIABILITIES_MAPPING_LIST,"G",String.valueOf(sheet.getRow(4).getCell(6).getNumericCellValue()),"Projected", liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, LIABILITIES_MAPPING_LIST,"H",String.valueOf(sheet.getRow(4).getCell(7).getNumericCellValue()),"Projected", liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, LIABILITIES_MAPPING_LIST,"I",String.valueOf(sheet.getRow(4).getCell(8).getNumericCellValue()),"Projected", liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, LIABILITIES_MAPPING_LIST,"J",String.valueOf(sheet.getRow(4).getCell(9).getNumericCellValue()),"Projected", liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, LIABILITIES_MAPPING_LIST,"K",String.valueOf(sheet.getRow(4).getCell(10).getNumericCellValue()),"Projected", liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, LIABILITIES_MAPPING_LIST,"L",String.valueOf(sheet.getRow(4).getCell(11).getNumericCellValue()),"Projected",  liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, LIABILITIES_MAPPING_LIST,"M",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected", liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, LIABILITIES_MAPPING_LIST,"N",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected", liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, LIABILITIES_MAPPING_LIST,"O",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected", liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, LIABILITIES_MAPPING_LIST,"P",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected", liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, LIABILITIES_MAPPING_LIST,"Q",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected", liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, LIABILITIES_MAPPING_LIST,"R",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected", liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, LIABILITIES_MAPPING_LIST,"S",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected", liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, LIABILITIES_MAPPING_LIST,"T",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected", liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, LIABILITIES_MAPPING_LIST,"U",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected", liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, LIABILITIES_MAPPING_LIST,"V",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected", liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, LIABILITIES_MAPPING_LIST,"W",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected", liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, LIABILITIES_MAPPING_LIST,"X",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected", liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, LIABILITIES_MAPPING_LIST,"Y",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected", liabilitiesDetailsRepository);
        */}
    }
    public static void extractCellFromSheet(Long storageDetailsId,XSSFSheet sheet,LoanApplicationMaster loanApplicationMaster,List<String> arrayList,String column,String year,String financialYearlyStatement,LiabilitiesDetailsRepository liabilitiesDetailsRepository) throws ExcelException
    {
        int arrayListCounter = 0;
        int nullCounter=0;
        for (int i = 0; i < LIABILITIES_MAPPING_LIST.size(); i++) {
            if ((getNumericDataFromCell(sheet,column + LIABILITIES_MAPPING_LIST.get(i)))==0.0) {
                ++nullCounter;
            }
        }
       
        if(!(nullCounter==40)) {
        	
        	Double yearFromSheet  = Double.valueOf(year) ; 
        	LiabilitiesDetails  cmaLiabilities   =	liabilitiesDetailsRepository.findByFsLoanApplicationMasterIdAndYearAndFinancialYearlyStatementAndIsActive(loanApplicationMaster.getId(), String.valueOf(yearFromSheet.longValue()) ,  financialYearlyStatement , true );
           	
           	if(cmaLiabilities != null &&  "Audited".equalsIgnoreCase(cmaLiabilities.getFinancialYearlyStatement()) && yearFromSheet <= Double.valueOf(cmaLiabilities.getYear()) ) {
           		
           		throw new ExcelException("Invalid cma details");
         
           	}
        	
        	cmaLiabilities = new LiabilitiesDetails();
        	cmaLiabilities.setModifiedDate(new Date());
        	cmaLiabilities.setCreatedDate(new Date());
            cmaLiabilities.setFsLoanApplicationMaster(loanApplicationMaster);
            cmaLiabilities.setStorageDetailsId(storageDetailsId);
            
            cmaLiabilities.setYear(CommonUtils.getCMAFilterYear(year));
            cmaLiabilities.setFinancialYearlyStatement(financialYearlyStatement);
            cmaLiabilities.setFromApplicationBank(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setFromOtherBanks(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setWhichBpAndBd(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setSubTotalA(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setShortTermBorrowingFromOthers(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setSundryCreditors(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setAdvancePaymentsFromCustomers(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setProvisionalForTaxation(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setDividendPayable(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setOtherStatutoryLiability(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setDepositsOrInstalmentsOfTermLoans(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setOtherCurrentLiability(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setSubTotalB(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setTotalCurrentLiabilities(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setDebentures(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setPreferencesShares(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setTermLoans(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            //46_47
            cmaLiabilities.setTermLiabilitiesSecured(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setTermLiabilitiesUnsecured(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            
            cmaLiabilities.setDeferredPaymentsCredits(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setTermDeposits(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setOtherTermLiabilies(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setTotalTermLiabilities(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            //57 to 61
            
            cmaLiabilities.setOtherNcl(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setOtherNclUnsecuredLoansFromPromoters(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setOtherNclUnsecuredLoansFromOther(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setOtherNclLongTermProvisions(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setOtherNclOthers(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            
            
            
            cmaLiabilities.setTotalOutsideLiabilities(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setOrdinarySharesCapital(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            //69 and 71
            
            cmaLiabilities.setShareWarrentsOutstanding(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setMinorityInterest(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));            
            
            
            cmaLiabilities.setGeneralReserve(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setRevaluationReservse(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setOtherReservse(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setSurplusOrDeficit(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setDeferredTaxLiability(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setOthers(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setNetWorth(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setOtherIncomeNeedTocCheckLia(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setTotalLiability(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            
            cmaLiabilities.setIsActive(true);
//          cmaLiabilities.setCreatedBy(createdBy);
//          cmaLiabilities.setModifiedBy(modifiedBy);

            liabilitiesDetailsRepository.save(cmaLiabilities);
          
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

