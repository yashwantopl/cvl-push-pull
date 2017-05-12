package com.capitaworld.service.loans.utils.bs;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.BalanceSheetDetail;
import com.capitaworld.service.loans.repository.fundseeker.corporate.BalanceSheetDetailRepository;


public class BalanceSheetExcelReader
{
    public static ArrayList<String> balanceSheetMappingList = new ArrayList<String>();
    public static final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public static void run(Long storageDetailsId,XSSFSheet sheet,LoanApplicationMaster loanApplicationMaster,BalanceSheetDetailRepository balanceSheetDetailRepository) {

            balanceSheetMappingList.clear();
            balanceSheetMappingList.add("8");
            balanceSheetMappingList.add("9");
            balanceSheetMappingList.add("10");
            balanceSheetMappingList.add("12");
            balanceSheetMappingList.add("13");
            balanceSheetMappingList.add("14");
            balanceSheetMappingList.add("15");
            balanceSheetMappingList.add("16");
            balanceSheetMappingList.add("17");
            balanceSheetMappingList.add("18");
            balanceSheetMappingList.add("19");
            balanceSheetMappingList.add("20");
            balanceSheetMappingList.add("21");
            balanceSheetMappingList.add("22");
            balanceSheetMappingList.add("23");
            balanceSheetMappingList.add("26");
            balanceSheetMappingList.add("27");
            balanceSheetMappingList.add("28");
            balanceSheetMappingList.add("29");
            balanceSheetMappingList.add("30");
            balanceSheetMappingList.add("31");
            balanceSheetMappingList.add("32");
            balanceSheetMappingList.add("33");
            balanceSheetMappingList.add("34");
            balanceSheetMappingList.add("37");
            balanceSheetMappingList.add("38");
            balanceSheetMappingList.add("39");
            balanceSheetMappingList.add("40");
            balanceSheetMappingList.add("41");
            balanceSheetMappingList.add("42");
            balanceSheetMappingList.add("43");
            balanceSheetMappingList.add("44");
            balanceSheetMappingList.add("47");
            balanceSheetMappingList.add("50");
            balanceSheetMappingList.add("51");
            balanceSheetMappingList.add("52");
            balanceSheetMappingList.add("53");
            balanceSheetMappingList.add("54");
            balanceSheetMappingList.add("55");
            balanceSheetMappingList.add("56");
            balanceSheetMappingList.add("57");
            balanceSheetMappingList.add("58");
            balanceSheetMappingList.add("59");
            balanceSheetMappingList.add("60");
            balanceSheetMappingList.add("61");
            balanceSheetMappingList.add("64");
            balanceSheetMappingList.add("65");
            balanceSheetMappingList.add("66");
            balanceSheetMappingList.add("67");
            balanceSheetMappingList.add("68");
            balanceSheetMappingList.add("69");
            balanceSheetMappingList.add("70");
            balanceSheetMappingList.add("71");
            balanceSheetMappingList.add("72");
            balanceSheetMappingList.add("73");
            balanceSheetMappingList.add("74");
            balanceSheetMappingList.add("75");
            balanceSheetMappingList.add("76");
            balanceSheetMappingList.add("77");
            balanceSheetMappingList.add("78");
            balanceSheetMappingList.add("79");
            balanceSheetMappingList.add("80");
            balanceSheetMappingList.add("81");
            balanceSheetMappingList.add("83");
            balanceSheetMappingList.add("84");
            balanceSheetMappingList.add("87");

            /*
             * this method extract data from excel associate column and row wise
              * e.g. you want to extract B13,B14,... cell data for year 2014
             */
            extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, balanceSheetMappingList,"C","2014",balanceSheetDetailRepository);
            extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, balanceSheetMappingList,"D","2015",balanceSheetDetailRepository);
            extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, balanceSheetMappingList,"E","2016",balanceSheetDetailRepository);
            extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, balanceSheetMappingList,"F","2017",balanceSheetDetailRepository);
            extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, balanceSheetMappingList,"G","2018",balanceSheetDetailRepository);
            extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, balanceSheetMappingList,"H","2019",balanceSheetDetailRepository);
            extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, balanceSheetMappingList,"I","2020",balanceSheetDetailRepository);
            extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, balanceSheetMappingList,"J","2021",balanceSheetDetailRepository);
            extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, balanceSheetMappingList,"K","2022",balanceSheetDetailRepository);
            extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, balanceSheetMappingList,"L","2023",balanceSheetDetailRepository);
            extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, balanceSheetMappingList,"M","2024",balanceSheetDetailRepository);
            extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, balanceSheetMappingList,"N","2025",balanceSheetDetailRepository);

    }

    public static void extractCellFromSheet(Long storageDetailsId,XSSFSheet sheet,LoanApplicationMaster loanApplicationMaster,ArrayList<String> arrayList,String column,String year,BalanceSheetDetailRepository balanceSheetDetailRepository)
    {
        int arrayListCounter = 0;
        int nullCounter=0;
        for (int i = 0; i < balanceSheetMappingList.size(); i++) {
            if ((getNumericDataFromCell(sheet,column + balanceSheetMappingList.get(i)))==0) {
                ++nullCounter;
            }
        }
        if(!(nullCounter==balanceSheetMappingList.size())) {

            BalanceSheetDetail bsBalanceSheet = new BalanceSheetDetail();
            bsBalanceSheet.setApplicationId(loanApplicationMaster);
            bsBalanceSheet.setStorageDetailsId(storageDetailsId);
            
            bsBalanceSheet.setYear(year);

            bsBalanceSheet.setOrdinaryShareCapital(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setPreferenceShareCapital(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setShareApplicationPendingAllotment(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setReservesAndSurplus(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setCapitalReserve(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setCapitalRedemptionReserve(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setSecuritiesPremiumAccount(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setDebentureRedemptionReserve(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setRevaluationReserve(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setContingencyReserve(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setForeignCurrencyTranslationReserve(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setHedgingReserve(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setGeneralReserve(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setSurplusInProfitAndLossAccount(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOthers(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setLongTermBorrowing(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setDebentures(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setTermLoans(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setUnsecuredLoansFromPromoters(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setDeferredPaymentCredits(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setLongTermProvisions(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setTermDeposits(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setDeferredTaxLiability(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOtherNonCurrentLiability(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setShortTermBorrowings(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setTradePayables(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setAdvanceFromCustomers(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setProvisionForTax(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setDividendPayable(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setStatutoryLiabilityDues(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setDepositsAndInstallments(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOthersCurrentLiability(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setTotalCurrentAndNonCurrentLiability(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setFixedAssets(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setGrossFixedAssets(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setDepreciationToDate(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setIntangibleAssets(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setCapitalWorkInProgress(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setCapitalAdvance(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setNonCurrentInvestments(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setInvestmentInSubsidiaries(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setInvestmentInAssociates(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setInvestmentInQuoted(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setLongTermLoansAndAdvance(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOthersNonCurrentAssets(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setCurrentInvestments(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setGovernmentAndOtherTrustee(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setFixedDepositsWithBanks(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOthersCurrentAssets(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setInventory(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setRawMaterial(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setRawMaterialImported(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setRawMaterialIndegenous(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setStockInProcess(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setFinishedGoods(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOtherConsumablesSpares(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOtherConsumablesSparesImported(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOtherConsumablesSparesIndegenous(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setTradeReceivables(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setExports(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOtherThanExports(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setShortTermLoansAndAdvances(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOthersPlsSpecify(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setDeferredTaxAsset(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setMiscExpences(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setGrandTotal(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            
            bsBalanceSheet.setIsActive(true);
            bsBalanceSheet.setCreatedDate(new Date());
            bsBalanceSheet.setModifiedDate(new Date());
//          bsBalanceSheet.setCreatedBy(createdBy);
//          bsBalanceSheet.setModifiedBy(modifiedBy);

            balanceSheetDetailRepository.save(bsBalanceSheet);
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
