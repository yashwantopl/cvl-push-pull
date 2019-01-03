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
import com.capitaworld.service.loans.domain.fundseeker.corporate.BalanceSheetDetail;
import com.capitaworld.service.loans.repository.fundseeker.corporate.BalanceSheetDetailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BalanceSheetExcelReader
{

    private static final Logger logger = LoggerFactory.getLogger(BalanceSheetExcelReader.class);

    public static final ArrayList<String> BALANCE_SHEET_MAPPING_LIST = new ArrayList<String>();
    public static final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public static void run(Long storageDetailsId,XSSFSheet sheet,LoanApplicationMaster loanApplicationMaster,BalanceSheetDetailRepository balanceSheetDetailRepository) {

            BALANCE_SHEET_MAPPING_LIST.clear();
            BALANCE_SHEET_MAPPING_LIST.add("8");
            BALANCE_SHEET_MAPPING_LIST.add("9");
            BALANCE_SHEET_MAPPING_LIST.add("10");
            BALANCE_SHEET_MAPPING_LIST.add("11");
            BALANCE_SHEET_MAPPING_LIST.add("12");
            BALANCE_SHEET_MAPPING_LIST.add("14");
            BALANCE_SHEET_MAPPING_LIST.add("15");
            BALANCE_SHEET_MAPPING_LIST.add("16");
            BALANCE_SHEET_MAPPING_LIST.add("17");
            BALANCE_SHEET_MAPPING_LIST.add("18");
            BALANCE_SHEET_MAPPING_LIST.add("19");
            BALANCE_SHEET_MAPPING_LIST.add("20");
            BALANCE_SHEET_MAPPING_LIST.add("21");
            BALANCE_SHEET_MAPPING_LIST.add("22");
            BALANCE_SHEET_MAPPING_LIST.add("23");
            BALANCE_SHEET_MAPPING_LIST.add("24");
            BALANCE_SHEET_MAPPING_LIST.add("25");
            BALANCE_SHEET_MAPPING_LIST.add("26");
            BALANCE_SHEET_MAPPING_LIST.add("27");
            BALANCE_SHEET_MAPPING_LIST.add("28");
            BALANCE_SHEET_MAPPING_LIST.add("29");
            BALANCE_SHEET_MAPPING_LIST.add("30");
            BALANCE_SHEET_MAPPING_LIST.add("31");
            BALANCE_SHEET_MAPPING_LIST.add("32");
            BALANCE_SHEET_MAPPING_LIST.add("33");
            BALANCE_SHEET_MAPPING_LIST.add("34");
            BALANCE_SHEET_MAPPING_LIST.add("35");
            BALANCE_SHEET_MAPPING_LIST.add("36");
            BALANCE_SHEET_MAPPING_LIST.add("37");
            BALANCE_SHEET_MAPPING_LIST.add("38");
            BALANCE_SHEET_MAPPING_LIST.add("39");
            BALANCE_SHEET_MAPPING_LIST.add("40");
            BALANCE_SHEET_MAPPING_LIST.add("41");
            BALANCE_SHEET_MAPPING_LIST.add("42");
            BALANCE_SHEET_MAPPING_LIST.add("43");
            BALANCE_SHEET_MAPPING_LIST.add("44");
            BALANCE_SHEET_MAPPING_LIST.add("45");
            BALANCE_SHEET_MAPPING_LIST.add("46");
            BALANCE_SHEET_MAPPING_LIST.add("47");
            BALANCE_SHEET_MAPPING_LIST.add("48");
            BALANCE_SHEET_MAPPING_LIST.add("49");
            BALANCE_SHEET_MAPPING_LIST.add("50");
            BALANCE_SHEET_MAPPING_LIST.add("51");
            BALANCE_SHEET_MAPPING_LIST.add("52");
            BALANCE_SHEET_MAPPING_LIST.add("53");
            BALANCE_SHEET_MAPPING_LIST.add("54");
            BALANCE_SHEET_MAPPING_LIST.add("55");
            BALANCE_SHEET_MAPPING_LIST.add("56");
            BALANCE_SHEET_MAPPING_LIST.add("57");
            BALANCE_SHEET_MAPPING_LIST.add("58");
            BALANCE_SHEET_MAPPING_LIST.add("59");
            BALANCE_SHEET_MAPPING_LIST.add("60");
            BALANCE_SHEET_MAPPING_LIST.add("61");
            BALANCE_SHEET_MAPPING_LIST.add("62");
            BALANCE_SHEET_MAPPING_LIST.add("63");
            BALANCE_SHEET_MAPPING_LIST.add("64");
            BALANCE_SHEET_MAPPING_LIST.add("66");
            BALANCE_SHEET_MAPPING_LIST.add("67");
            BALANCE_SHEET_MAPPING_LIST.add("68");
            BALANCE_SHEET_MAPPING_LIST.add("69");
            BALANCE_SHEET_MAPPING_LIST.add("70");
            BALANCE_SHEET_MAPPING_LIST.add("71");
            BALANCE_SHEET_MAPPING_LIST.add("72");
            BALANCE_SHEET_MAPPING_LIST.add("73");
            BALANCE_SHEET_MAPPING_LIST.add("74");
            BALANCE_SHEET_MAPPING_LIST.add("75");
            BALANCE_SHEET_MAPPING_LIST.add("76");
            BALANCE_SHEET_MAPPING_LIST.add("77");
            BALANCE_SHEET_MAPPING_LIST.add("78");
            BALANCE_SHEET_MAPPING_LIST.add("79");
            BALANCE_SHEET_MAPPING_LIST.add("80");
            BALANCE_SHEET_MAPPING_LIST.add("81");
            BALANCE_SHEET_MAPPING_LIST.add("82");
            BALANCE_SHEET_MAPPING_LIST.add("83");
            BALANCE_SHEET_MAPPING_LIST.add("84");
            BALANCE_SHEET_MAPPING_LIST.add("85");
            BALANCE_SHEET_MAPPING_LIST.add("86");
            BALANCE_SHEET_MAPPING_LIST.add("87");
            BALANCE_SHEET_MAPPING_LIST.add("88");
            BALANCE_SHEET_MAPPING_LIST.add("89");
            BALANCE_SHEET_MAPPING_LIST.add("90");
            BALANCE_SHEET_MAPPING_LIST.add("91");
            BALANCE_SHEET_MAPPING_LIST.add("92");
            BALANCE_SHEET_MAPPING_LIST.add("93");
            BALANCE_SHEET_MAPPING_LIST.add("94");
            BALANCE_SHEET_MAPPING_LIST.add("95");
            BALANCE_SHEET_MAPPING_LIST.add("96");
            BALANCE_SHEET_MAPPING_LIST.add("97");
            BALANCE_SHEET_MAPPING_LIST.add("98");
            BALANCE_SHEET_MAPPING_LIST.add("99");
            BALANCE_SHEET_MAPPING_LIST.add("100");
            BALANCE_SHEET_MAPPING_LIST.add("101");
            BALANCE_SHEET_MAPPING_LIST.add("102");
            BALANCE_SHEET_MAPPING_LIST.add("103");
            BALANCE_SHEET_MAPPING_LIST.add("104");
            BALANCE_SHEET_MAPPING_LIST.add("105");
            BALANCE_SHEET_MAPPING_LIST.add("106");
            BALANCE_SHEET_MAPPING_LIST.add("107");
            BALANCE_SHEET_MAPPING_LIST.add("108");
            BALANCE_SHEET_MAPPING_LIST.add("109");
            BALANCE_SHEET_MAPPING_LIST.add("110");
            BALANCE_SHEET_MAPPING_LIST.add("111");
            BALANCE_SHEET_MAPPING_LIST.add("112");
            BALANCE_SHEET_MAPPING_LIST.add("113");
            BALANCE_SHEET_MAPPING_LIST.add("114");
            BALANCE_SHEET_MAPPING_LIST.add("115");
            BALANCE_SHEET_MAPPING_LIST.add("116");
            BALANCE_SHEET_MAPPING_LIST.add("117");
            BALANCE_SHEET_MAPPING_LIST.add("118");
            BALANCE_SHEET_MAPPING_LIST.add("119");
            BALANCE_SHEET_MAPPING_LIST.add("120");
            BALANCE_SHEET_MAPPING_LIST.add("121");
            BALANCE_SHEET_MAPPING_LIST.add("122");
            BALANCE_SHEET_MAPPING_LIST.add("123");
            BALANCE_SHEET_MAPPING_LIST.add("124");
            BALANCE_SHEET_MAPPING_LIST.add("125");
            BALANCE_SHEET_MAPPING_LIST.add("126");
            BALANCE_SHEET_MAPPING_LIST.add("128");
            

            /*
             * this method extract data from excel associate column and row wise
              * e.g. you want to extract B13,B14,... cell data for year 2014
             */
            
            logger.info("BalanceSheetExcelReader -----------> "+sheet.getRow(4).getCell(2).getNumericCellValue());
//            extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, BALANCE_SHEET_MAPPING_LIST,"C",String.valueOf(sheet.getRow(4).getCell(2).getNumericCellValue()),"Audited", balanceSheetDetailRepository);
//            extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, BALANCE_SHEET_MAPPING_LIST,"D",String.valueOf(sheet.getRow(4).getCell(3).getNumericCellValue()),"Audited",balanceSheetDetailRepository);
//            extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, BALANCE_SHEET_MAPPING_LIST,"E",String.valueOf(sheet.getRow(4).getCell(4).getNumericCellValue()),"Audited",balanceSheetDetailRepository);
            extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, BALANCE_SHEET_MAPPING_LIST,"F",String.valueOf(sheet.getRow(4).getCell(5).getNumericCellValue()),"Estimated",balanceSheetDetailRepository);
            if(loanApplicationMaster.getProductId()!=15){
            extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, BALANCE_SHEET_MAPPING_LIST,"G",String.valueOf(sheet.getRow(4).getCell(6).getNumericCellValue()), CommonUtils.PROJECTED, balanceSheetDetailRepository);
            extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, BALANCE_SHEET_MAPPING_LIST,"H",String.valueOf(sheet.getRow(4).getCell(7).getNumericCellValue()),CommonUtils.PROJECTED,balanceSheetDetailRepository);
            extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, BALANCE_SHEET_MAPPING_LIST,"I",String.valueOf(sheet.getRow(4).getCell(8).getNumericCellValue()),CommonUtils.PROJECTED,balanceSheetDetailRepository);
            extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, BALANCE_SHEET_MAPPING_LIST,"J",String.valueOf(sheet.getRow(4).getCell(9).getNumericCellValue()),CommonUtils.PROJECTED,balanceSheetDetailRepository);
            extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, BALANCE_SHEET_MAPPING_LIST,"K",String.valueOf(sheet.getRow(4).getCell(10).getNumericCellValue()),CommonUtils.PROJECTED,balanceSheetDetailRepository);
            extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, BALANCE_SHEET_MAPPING_LIST,"L",String.valueOf(sheet.getRow(4).getCell(11).getNumericCellValue()),CommonUtils.PROJECTED,balanceSheetDetailRepository);
            extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, BALANCE_SHEET_MAPPING_LIST,"M",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),CommonUtils.PROJECTED,balanceSheetDetailRepository);
            extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, BALANCE_SHEET_MAPPING_LIST,"N",String.valueOf(sheet.getRow(4).getCell(13).getNumericCellValue()),CommonUtils.PROJECTED,balanceSheetDetailRepository);

            }
            
           
            
            
    }

    public static void extractCellFromSheet(Long storageDetailsId,XSSFSheet sheet,LoanApplicationMaster loanApplicationMaster,ArrayList<String> arrayList,String column,String year, String financialYearlyStatement,BalanceSheetDetailRepository balanceSheetDetailRepository)
    {
        int arrayListCounter = 0;
        int nullCounter=0;
        for (int i = 0; i < BALANCE_SHEET_MAPPING_LIST.size(); i++) {
            if ((getNumericDataFromCell(sheet,column + BALANCE_SHEET_MAPPING_LIST.get(i)))==0) {
                ++nullCounter;
            }
        }
        if(!(nullCounter==BALANCE_SHEET_MAPPING_LIST.size())) {

            BalanceSheetDetail bsBalanceSheet = new BalanceSheetDetail();
            bsBalanceSheet.setApplicationId(loanApplicationMaster);
            bsBalanceSheet.setStorageDetailsId(storageDetailsId);
            
            bsBalanceSheet.setYear(year);
            bsBalanceSheet.setFinancialYearlyStatement(financialYearlyStatement);
            bsBalanceSheet.setOrdinaryShareCapital(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            
            bsBalanceSheet.setPreferenceShareCapital(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setMoneyReceivedAgainstShareWarrants(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setShareApplicationPendingAllotment(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setMinorityInterest(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));   
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
            bsBalanceSheet.setOthers1(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOthers2(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOthers3(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOthers4(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOthers5(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
           
            bsBalanceSheet.setOtherNonCurrentLiability(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            
            bsBalanceSheet.setLongTermBorrowing(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setDebentures(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setTermLoans(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setTermLoansSecured(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setTermLoansUnsecured(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setUnsecuredLoansFromPromoters(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setDeferredPaymentCredits(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setLongTermProvisions(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setTermDeposits(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setDeferredTaxLiability(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOthersPlsSpecify(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setUnsecuredLoansFromOthers(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
           
            bsBalanceSheet.setOtherTermLiability(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            
            bsBalanceSheet.setOthersLiabilities1(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOthersLiabilities2(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOthersLiabilities3(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOthersCurrentLiability(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setShortTermBorrowings(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));            
            bsBalanceSheet.setCurrentLiabilitiesSecured(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setCurrentLiabilitiesUnsecured(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));            
            bsBalanceSheet.setTradePayables(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setAdvanceFromCustomers(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setProvisionForTax(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setDividendPayable(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setStatutoryLiabilityDues(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setDepositsAndInstallments(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOthersTotals(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOthersTotals1(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOthersTotals2(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOthersTotals3(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOthersTotals4(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOthersTotals5(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setTotalCurrentAndNonCurrentLiability(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOthersNonCurrentAssets(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setFixedAssets(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setGrossFixedAssets(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setDepreciationToDate(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setImpairmentsOfAssests(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setIntangibleAssets(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setIntangibleAssets1(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setIntangibleAssets2(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setIntangibleAssets3(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setIntangibleAssets4(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setIntangibleAssets5(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setIntangibleAssets6(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));            
            bsBalanceSheet.setCapitalWorkInProgress(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setCapitalAdvance(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setNonCurrentInvestments(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setInvestmentInSubsidiaries(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setInvestmentInAssociates(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setInvestmentInQuoted(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setLongTermLoansAndAdvance(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOthersAssetsTransit(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setPreOperativeExpensesPending(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setAssetsInTransit(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setAssetsInTransit1(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setAssetsInTransit2(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setAssetsInTransit3(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setAssetsInTransit4(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOthersCurrentAssets(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setCurrentInvestments(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setGovernmentAndOtherTrustee(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setFixedDepositsWithBanks(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOtherInvestments(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOthersInvestment1(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOthersInvestment2(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOthersInvestment3(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOthersInvestment4(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
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
            bsBalanceSheet.setCashAndCashEquivalents(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setShortTermLoansAndAdvances(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setShortTerm1(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setShortTerm2(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setShortTerm3(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setShortTerm4(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOtherDetails(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOtherDetails1(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOtherDetails2(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOtherDetails3(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOtherDetails4(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            bsBalanceSheet.setOtherDetails5(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
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




