package com.capitaworld.service.loans.utils.cma;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.LiabilitiesDetails;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LiabilitiesDetailsRepository;

public class LiabilitiesDetailsExcelReader
{
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
        liabilitiesMappingList.add("47");
        liabilitiesMappingList.add("49");
        liabilitiesMappingList.add("51");
        liabilitiesMappingList.add("53");
        liabilitiesMappingList.add("55");
        liabilitiesMappingList.add("59");
        liabilitiesMappingList.add("61");
        liabilitiesMappingList.add("63");
        liabilitiesMappingList.add("65");
        liabilitiesMappingList.add("67");
        liabilitiesMappingList.add("69");
        liabilitiesMappingList.add("71");
        liabilitiesMappingList.add("73");
        liabilitiesMappingList.add("75");

            /*
              * this method extract data from excel associate column and row wise
              * e.g. you want to extract B13,B14,... cell data for year 2014
             */
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, liabilitiesMappingList,"B","2014",liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, liabilitiesMappingList,"C","2015",liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, liabilitiesMappingList,"D","2016",liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, liabilitiesMappingList,"E","2017",liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, liabilitiesMappingList,"F","2018",liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, liabilitiesMappingList,"G","2019",liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, liabilitiesMappingList,"H","2020",liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, liabilitiesMappingList,"I","2021",liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, liabilitiesMappingList,"J","2022",liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, liabilitiesMappingList,"K","2023",liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, liabilitiesMappingList,"L","2024",liabilitiesDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, liabilitiesMappingList,"M","2025",liabilitiesDetailsRepository);
    }

    public static void extractCellFromSheet(Long storageDetailsId,
    										XSSFSheet sheet,
                                            LoanApplicationMaster loanApplicationMaster,
                                            List<String> arrayList,
                                            String column,
                                            String year,
                                            LiabilitiesDetailsRepository liabilitiesDetailsRepository)
    {
        int arrayListCounter = 0;
        int nullCounter=0;
        for (int i = 0; i < liabilitiesMappingList.size(); i++) {
            if ((getNumericDataFromCell(sheet,column + liabilitiesMappingList.get(i)))==0.0) {
                ++nullCounter;
            }
        }
       
        if(!(nullCounter==31)) {
        	
            LiabilitiesDetails cmaLiabilities = new LiabilitiesDetails();
            
            cmaLiabilities.setFsLoanApplicationMaster(loanApplicationMaster);
            cmaLiabilities.setStorageDetailsId(storageDetailsId);
            
            cmaLiabilities.setYear(year);

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
            cmaLiabilities.setDeferredPaymentsCredits(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setTermDeposits(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setOtherTermLiabilies(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setTotalTermLiabilities(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setTotalOutsideLiabilities(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaLiabilities.setOrdinarySharesCapital(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
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
