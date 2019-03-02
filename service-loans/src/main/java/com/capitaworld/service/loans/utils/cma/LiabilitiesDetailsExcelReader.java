package com.capitaworld.service.loans.utils.cma;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.capitaworld.service.loans.exceptions.ExcelException;
import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
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
    private static final List<String> LIABILITIES_MAPPING_LIST = new ArrayList<String>();
    public static final DecimalFormat decimalFormat = new DecimalFormat("#.##");


    public static void run(Long storageDetailsId, XSSFSheet sheet, LoanApplicationMaster loanApplicationMaster, ApplicationProposalMapping applicationProposalMapping, LiabilitiesDetailsRepository liabilitiesDetailsRepository) throws ExcelException {

        String[] numbers = new String[]{"11","12","13","15","17","19",
                "21","23","25","27","29","32",
                "35","37","41","43","45","46",
                "47","49","51","53","55","57",
                "58","59","60","61","63","67",
                "69","71","73","75","77","79",
                "81","83","85","86","87"};

        LIABILITIES_MAPPING_LIST.clear();
        LIABILITIES_MAPPING_LIST.addAll(Arrays.asList(numbers));

        log.info("OperatingStatementDetailsExcelReader -----------> {} " , sheet.getRow(4).getCell(1).getNumericCellValue());
        int j = 2;
        if (applicationProposalMapping.getBusinessTypeId() == CommonUtils.BusinessType.EXISTING_BUSINESS.getId()) {
        	cmaAuditedAndEstimatedValidation(sheet);
        	
        	int updateRow = liabilitiesDetailsRepository.inActiveByAppIdAndProposalIdAndFinancialYearlyStatementAndIsActive(loanApplicationMaster.getId() , applicationProposalMapping.getProposalId());
       	 	log.info("---------------- inactive old estimate and project data ------- updated row ==> {}" , updateRow);
       	 	
            extractCellFromSheet(storageDetailsId, sheet, loanApplicationMaster,applicationProposalMapping, LIABILITIES_MAPPING_LIST, "E", String.valueOf(sheet.getRow(4).getCell(4).getNumericCellValue()), "Estimated", liabilitiesDetailsRepository);
            j = 5;

        }
        if (applicationProposalMapping.getProductId() != 15 && applicationProposalMapping.getProductId() != 1) {

        	
            for (int i = 0; i < applicationProposalMapping.getTenure(); i++) {
            	cmaValidationProjection(sheet, j);
                extractCellFromSheet(storageDetailsId, sheet,loanApplicationMaster, applicationProposalMapping, LIABILITIES_MAPPING_LIST, CellReference.convertNumToColString(sheet.getRow(4).getCell(j).getColumnIndex()), String.valueOf(sheet.getRow(4).getCell(j).getNumericCellValue()), "Projected", liabilitiesDetailsRepository);
                j++;
            }
        }
    }

    public static void run(Long storageDetailsId,XSSFSheet sheet,LoanApplicationMaster loanApplicationMaster,LiabilitiesDetailsRepository liabilitiesDetailsRepository)  throws Exception {
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
        log.info("OperatingStatementDetailsExcelReader -----------> {} ", sheet.getRow(4).getCell(1).getNumericCellValue());       
//        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, LIABILITIES_MAPPING_LIST,"B",String.valueOf(sheet.getRow(4).getCell(1).getNumericCellValue()) ,"Audited", liabilitiesDetailsRepository);
//        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, LIABILITIES_MAPPING_LIST,"C",String.valueOf(sheet.getRow(4).getCell(2).getNumericCellValue())  ,"Audited", liabilitiesDetailsRepository);
//        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, LIABILITIES_MAPPING_LIST,"D",String.valueOf(sheet.getRow(4).getCell(3).getNumericCellValue())  ,"Audited", liabilitiesDetailsRepository);
        
        //j== 2 for NTB 
        int j = 2;
        if(loanApplicationMaster.getBusinessTypeId() == CommonUtils.BusinessType.EXISTING_BUSINESS.getId()) {

       		int updateRow = liabilitiesDetailsRepository.inActiveByAppIdAndFinancialYearlyStatementAndIsActive(loanApplicationMaster.getId());
       		log.info("---------------- inactive old estimate and project data ------- updated row ==> {} " ,  updateRow);

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

    public static void extractCellFromSheet(Long storageDetailsId,
                                            XSSFSheet sheet,
                                            LoanApplicationMaster loanApplicationMaster,
                                            ApplicationProposalMapping applicationProposalMapping,
                                            List<String> arrayList,
                                            String column,
                                            String year,
                                            String financialYearlyStatement,
                                            LiabilitiesDetailsRepository liabilitiesDetailsRepository) throws ExcelException
    {
        int arrayListCounter = 0;
        int nullCounter=0;
        for (int i = 0; i < LIABILITIES_MAPPING_LIST.size(); i++) {
            if ((getNumericDataFromCell(sheet,column + LIABILITIES_MAPPING_LIST.get(i)))==0.0) {
                ++nullCounter;
            }
        }
       
        if(nullCounter != 40) {
        	
        	/*Double yearFromSheet  = Double.valueOf(year) ;
        	LiabilitiesDetails  cmaLiabilities   =	liabilitiesDetailsRepository.findByFsLoanApplicationMasterIdAndYearAndFinancialYearlyStatementAndIsActive(loanApplicationMaster.getId(), String.valueOf(yearFromSheet.longValue()) ,  financialYearlyStatement , true );

           	if(cmaLiabilities != null &&  "Audited".equalsIgnoreCase(cmaLiabilities.getFinancialYearlyStatement()) && yearFromSheet <= Double.valueOf(cmaLiabilities.getYear()) ) {

           		throw new ExcelException("Invalid cma details");

           	}*/
        	
        	cmaValidationFromDB(liabilitiesDetailsRepository, loanApplicationMaster.getId(), applicationProposalMapping.getProposalId(), year );
        	
        	LiabilitiesDetails cmaLiabilities = new LiabilitiesDetails();
        	cmaLiabilities.setModifiedDate(new Date());
        	cmaLiabilities.setCreatedDate(new Date());
            cmaLiabilities.setFsLoanApplicationMaster(loanApplicationMaster);
            cmaLiabilities.setApplicationProposalMapping(applicationProposalMapping);
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
    public static void extractCellFromSheet(Long storageDetailsId,
    										XSSFSheet sheet,
                                            LoanApplicationMaster loanApplicationMaster,
                                            List<String> arrayList,
                                            String column,
                                            String year,
                                            String financialYearlyStatement,
                                            LiabilitiesDetailsRepository liabilitiesDetailsRepository) throws Exception
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

           		throw new  Exception("Invalid cma details");

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
    	log.info("getNumericDataFromCell:==> {}",cellNumber );
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
    
    public static void cmaAuditedAndEstimatedValidation(XSSFSheet sheet ) throws ExcelException {
    	int i = 0 ;
    	for(i = 1 ; i <=3 ; i++) {
	    	if(!"Audited".equalsIgnoreCase(sheet.getRow(5).getCell(i).getStringCellValue()) &&  
	    			sheet.getRow(4).getCell(i).getNumericCellValue() <= sheet.getRow(4).getCell(i+1).getNumericCellValue()) {
	    		throw new ExcelException("Please enter valid years in cma file ");
	   	 	}
    	}
    	if(!"Estimated".equalsIgnoreCase(sheet.getRow(5).getCell(4).getStringCellValue()) 
    			&&  (sheet.getRow(4).getCell(4).getNumericCellValue() <= sheet.getRow(4).getCell(3).getNumericCellValue())
    			&&  (sheet.getRow(4).getCell(5).getNumericCellValue() <= sheet.getRow(4).getCell(4).getNumericCellValue())) {
    		throw new ExcelException("Please enter valid years in cma file");
    	}
    	
    }
    public static void cmaValidationProjection(XSSFSheet sheet , int cellNumber ) throws ExcelException {
    	log.info("------------XSSFSheet info compare with Projected----------- sheet ==> {} cellNumber ==> {} " , sheet.getRow(5).getCell(cellNumber).getStringCellValue() , cellNumber );
    	if( ! "Projected".equalsIgnoreCase(sheet.getRow(5).getCell(cellNumber).getStringCellValue()) 
    			&& sheet.getRow(4).getCell(cellNumber).getNumericCellValue() >=  sheet.getRow(4).getCell(cellNumber-1).getNumericCellValue()) {
    		throw new ExcelException("Please enter valid years in cma file");
    	}
    	
    }
    
    public static void cmaValidationFromDB(LiabilitiesDetailsRepository liabilitiesDetailsRepository,Long applicationId  , Long proposalId, String year) throws ExcelException {
    	log.info("============= Enter into cmaValidationFromDB() ============ applicationId ==> {} year ==> {}" , applicationId  , year);
    	
    	
    	int rowUpdated = 0;
    	List<LiabilitiesDetails> liabilitiesDetailsList  = liabilitiesDetailsRepository.findByFsLoanApplicationMasterIdAndYearAndIsActive(applicationId , year,  true);
    	
    	if(liabilitiesDetailsList.stream().anyMatch(opsd -> "Audited".equalsIgnoreCase(opsd.getFinancialYearlyStatement()))) {
    		throw new ExcelException("Invalid cma file");
    	}else {
    		if(proposalId == null  ) {
    			rowUpdated = liabilitiesDetailsRepository.inActiveByAppIdAndFinancialYearlyStatementAndIsActive(applicationId);
    		}else {
    			rowUpdated = liabilitiesDetailsRepository.inActiveByAppIdAndProposalIdAndFinancialYearlyStatementAndIsActive(applicationId , proposalId);
    			
    		}
    		log.info("----------------- inactive the old Estimated and Projected FinancialYearlyStatement ------------ rowUpdated ==> {}" ,rowUpdated);
    	}
    	
    	log.info("============= Exit from cmaValidationFromDB() ============ ");
    } 
}

