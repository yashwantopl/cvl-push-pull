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
import com.capitaworld.service.loans.domain.fundseeker.corporate.LiabilitiesDetails;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LiabilitiesDetailsRepository;

public class LiabilitiesDetailsExcelReader
{
	public static final Logger log = LoggerFactory.getLogger(LiabilitiesDetailsExcelReader.class);
    public static List<String> liabilitiesMappingList = new ArrayList<String>();
    public static final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public static void run(Long storageDetailsId,XSSFSheet sheet,LoanApplicationMaster loanApplicationMaster,LiabilitiesDetailsRepository liabilitiesDetailsRepository) {
        liabilitiesMappingList.clear();
        liabilitiesMappingList.add("11");
        liabilitiesMappingList.add("12");
        liabilitiesMappingList.add("13");
        liabilitiesMappingList.add("15");
        liabilitiesMappingList.add("17");
        liabilitiesMappingList.add("19");
        liabilitiesMappingList.add("21");
        liabilitiesMappingList.add("23");
        liabilitiesMappingList.add("25");
        liabilitiesMappingList.add("27");
        liabilitiesMappingList.add("29");
        liabilitiesMappingList.add("32");
        liabilitiesMappingList.add("35");
        liabilitiesMappingList.add("37");
        liabilitiesMappingList.add("41");
        liabilitiesMappingList.add("43");
        liabilitiesMappingList.add("45");
        liabilitiesMappingList.add("46");
        liabilitiesMappingList.add("47");
        liabilitiesMappingList.add("49");
        liabilitiesMappingList.add("51");
        liabilitiesMappingList.add("53");
        liabilitiesMappingList.add("55");       
        liabilitiesMappingList.add("57");
        liabilitiesMappingList.add("58");
        liabilitiesMappingList.add("59");
        liabilitiesMappingList.add("60");
        liabilitiesMappingList.add("61");
        liabilitiesMappingList.add("63");        
        liabilitiesMappingList.add("67");
        liabilitiesMappingList.add("69");
        liabilitiesMappingList.add("71");
        liabilitiesMappingList.add("73");
        liabilitiesMappingList.add("75");
        liabilitiesMappingList.add("77");
        liabilitiesMappingList.add("79");
        liabilitiesMappingList.add("81");
        liabilitiesMappingList.add("83");
        liabilitiesMappingList.add("85");
        liabilitiesMappingList.add("87");
        

            /*
              * this method extract data from excel associate column and row wise
              * e.g. you want to extract B13,B14,... cell data for year 2014
             */
        System.out.println("OperatingStatementDetailsExcelReader -----------> "+ sheet.getRow(4).getCell(1).getNumericCellValue());       
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, liabilitiesMappingList,"B",String.valueOf(sheet.getRow(4).getCell(1).getNumericCellValue()) ,"Audited", liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, liabilitiesMappingList,"C",String.valueOf(sheet.getRow(4).getCell(2).getNumericCellValue())  ,"Audited", liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, liabilitiesMappingList,"D",String.valueOf(sheet.getRow(4).getCell(3).getNumericCellValue())  ,"Audited", liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, liabilitiesMappingList,"E",String.valueOf(sheet.getRow(4).getCell(4).getNumericCellValue()),"Estimated", liabilitiesDetailsRepository);
        if(loanApplicationMaster.getProductId()!=15){
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, liabilitiesMappingList,"F",String.valueOf(sheet.getRow(4).getCell(5).getNumericCellValue()),"Projected", liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, liabilitiesMappingList,"G",String.valueOf(sheet.getRow(4).getCell(6).getNumericCellValue()),"Projected", liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, liabilitiesMappingList,"H",String.valueOf(sheet.getRow(4).getCell(7).getNumericCellValue()),"Projected", liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, liabilitiesMappingList,"I",String.valueOf(sheet.getRow(4).getCell(8).getNumericCellValue()),"Projected", liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, liabilitiesMappingList,"J",String.valueOf(sheet.getRow(4).getCell(9).getNumericCellValue()),"Projected", liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, liabilitiesMappingList,"K",String.valueOf(sheet.getRow(4).getCell(10).getNumericCellValue()),"Projected", liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, liabilitiesMappingList,"L",String.valueOf(sheet.getRow(4).getCell(11).getNumericCellValue()),"Projected",  liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, liabilitiesMappingList,"M",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected", liabilitiesDetailsRepository);
    }
    }
    public static void extractCellFromSheet(Long storageDetailsId,
    										XSSFSheet sheet,
                                            LoanApplicationMaster loanApplicationMaster,
                                            List<String> arrayList,
                                            String column,
                                            String year,
                                            String financialYearlyStatement,
                                            LiabilitiesDetailsRepository liabilitiesDetailsRepository)
    {
        int arrayListCounter = 0;
        int nullCounter=0;
        for (int i = 0; i < liabilitiesMappingList.size(); i++) {
            if ((getNumericDataFromCell(sheet,column + liabilitiesMappingList.get(i)))==0.0) {
                ++nullCounter;
            }
        }
       
        if(!(nullCounter==40)) {
        	
            LiabilitiesDetails cmaLiabilities = new LiabilitiesDetails();
            
            cmaLiabilities.setFsLoanApplicationMaster(loanApplicationMaster);
            cmaLiabilities.setStorageDetailsId(storageDetailsId);
            
            cmaLiabilities.setYear(year);
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
            cmaLiabilities.setTotalLiability(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            
            cmaLiabilities.setIsActive(true);
            cmaLiabilities.setCreatedDate(new Date());
            cmaLiabilities.setModifiedDate(new Date());
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
