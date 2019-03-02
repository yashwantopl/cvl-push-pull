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
import com.capitaworld.service.loans.domain.fundseeker.corporate.AssetsDetails;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AssetsDetailsRepository;
import com.capitaworld.service.loans.utils.CommonUtils;



public class AssetsDetailsExcelReader
{
    private AssetsDetailsExcelReader() {
        // Do nothing because of X and Y.
    }

	public static final Logger log = LoggerFactory.getLogger(AssetsDetailsExcelReader.class);
    private static final List<String> ASSETS_MAPPING_LIST = new ArrayList<String>();
    public static final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public static void run(Long storageDetailsId, XSSFSheet sheet, LoanApplicationMaster loanApplicationMaster, ApplicationProposalMapping applicationProposalMapping, AssetsDetailsRepository assetsDetailsRepository)throws ExcelException  {
        ASSETS_MAPPING_LIST.clear();

        String[] numbers = new String[]{"9","11","13","15","16",
                "18","20","22","24","26",
                "27","29","31","33","34",
                "35","37","39","41","43",
                "45","46","47","48","49",
                "50","52","54","56","60",
                "62","64","65","67","69",
                "71","72","73","74","76",
                "78","80","82","83","84",
                "85","86","87","88","89",
                "91","93","95","97","99"};

        ASSETS_MAPPING_LIST.addAll(Arrays.asList(numbers));

        log.info("OperatingStatementDetailsExcelReader -----------> {} " , sheet.getRow(4).getCell(1).getNumericCellValue());

        int j = 2;
        if (applicationProposalMapping.getBusinessTypeId() == CommonUtils.BusinessType.EXISTING_BUSINESS.getId()) {
        	
        	cmaAuditedAndEstimatedValidation(sheet);
        	int updateRow = assetsDetailsRepository.inActiveByAppIdAndProposalIdAndFinancialYearlyStatementAndIsActive(loanApplicationMaster.getId() , applicationProposalMapping.getProposalId());
       	 	log.info("---------------- inactive old estimate and project data ------- updated row ==> {}" , updateRow);
       	 	
            extractCellFromSheet(storageDetailsId, sheet, loanApplicationMaster,applicationProposalMapping, ASSETS_MAPPING_LIST, "E", String.valueOf(sheet.getRow(4).getCell(4).getNumericCellValue()), "Estimated", assetsDetailsRepository);
            j = 5;

        }

        if (applicationProposalMapping.getProductId() != 15 && applicationProposalMapping.getProductId() != 1) {

            
            for (int i = 0; i < applicationProposalMapping.getTenure(); i++) {
            	
            	cmaValidationProjection(sheet, j);
                extractCellFromSheet(storageDetailsId, sheet, loanApplicationMaster,applicationProposalMapping, ASSETS_MAPPING_LIST, CellReference.convertNumToColString(sheet.getRow(4).getCell(j).getColumnIndex()), String.valueOf(sheet.getRow(4).getCell(j).getNumericCellValue()), "Projected", assetsDetailsRepository);
                j++;
            }
        }
    }

    public static void run(Long storageDetailsId,XSSFSheet sheet,LoanApplicationMaster loanApplicationMaster,AssetsDetailsRepository assetsDetailsRepository) throws ExcelException{
        ASSETS_MAPPING_LIST.clear();

        ASSETS_MAPPING_LIST.add("9");
        ASSETS_MAPPING_LIST.add("11");
        ASSETS_MAPPING_LIST.add("13");
        ASSETS_MAPPING_LIST.add("15");
        ASSETS_MAPPING_LIST.add("16");
        ASSETS_MAPPING_LIST.add("18");
        ASSETS_MAPPING_LIST.add("20");
        ASSETS_MAPPING_LIST.add("22");
        ASSETS_MAPPING_LIST.add("24");
        ASSETS_MAPPING_LIST.add("26");
        ASSETS_MAPPING_LIST.add("27");
        ASSETS_MAPPING_LIST.add("29");
        ASSETS_MAPPING_LIST.add("31");
        ASSETS_MAPPING_LIST.add("33");
        ASSETS_MAPPING_LIST.add("34");
        ASSETS_MAPPING_LIST.add("35");
        ASSETS_MAPPING_LIST.add("37");
        ASSETS_MAPPING_LIST.add("39");
        ASSETS_MAPPING_LIST.add("41");
        ASSETS_MAPPING_LIST.add("43");
        ASSETS_MAPPING_LIST.add("45");
        ASSETS_MAPPING_LIST.add("46");
        ASSETS_MAPPING_LIST.add("47");
        ASSETS_MAPPING_LIST.add("48");
        ASSETS_MAPPING_LIST.add("49");
        ASSETS_MAPPING_LIST.add("50");
        ASSETS_MAPPING_LIST.add("52");
        ASSETS_MAPPING_LIST.add("54");
        ASSETS_MAPPING_LIST.add("56");
        ASSETS_MAPPING_LIST.add("60");
        ASSETS_MAPPING_LIST.add("62");
        ASSETS_MAPPING_LIST.add("64");
        ASSETS_MAPPING_LIST.add("65");
        ASSETS_MAPPING_LIST.add("67");
        ASSETS_MAPPING_LIST.add("69");
        ASSETS_MAPPING_LIST.add("71");
        ASSETS_MAPPING_LIST.add("72");
        ASSETS_MAPPING_LIST.add("73");
        ASSETS_MAPPING_LIST.add("74");
        ASSETS_MAPPING_LIST.add("76");
        ASSETS_MAPPING_LIST.add("78");
        ASSETS_MAPPING_LIST.add("80");
        ASSETS_MAPPING_LIST.add("82");
        ASSETS_MAPPING_LIST.add("83");
        ASSETS_MAPPING_LIST.add("84");
        ASSETS_MAPPING_LIST.add("85");
        ASSETS_MAPPING_LIST.add("86");
        ASSETS_MAPPING_LIST.add("87");
        ASSETS_MAPPING_LIST.add("88");
        ASSETS_MAPPING_LIST.add("89");
        ASSETS_MAPPING_LIST.add("91");
        ASSETS_MAPPING_LIST.add("93");
        ASSETS_MAPPING_LIST.add("95");
        ASSETS_MAPPING_LIST.add("97");
        ASSETS_MAPPING_LIST.add("99");
        
            /*
              * this method extract data from excel associate column and row wise
              * e.g. you want to extract B13,B14,... cell data for year 2014
             */
      
        log.info("OperatingStatementDetailsExcelReader -----------> {} " , sheet.getRow(4).getCell(1).getNumericCellValue());   
//        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, ASSETS_MAPPING_LIST,"B",String.valueOf(sheet.getRow(4).getCell(1).getNumericCellValue()),"Audited", assetsDetailsRepository);
//        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, ASSETS_MAPPING_LIST,"C",String.valueOf(sheet.getRow(4).getCell(2).getNumericCellValue()),"Audited", assetsDetailsRepository);
//        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, ASSETS_MAPPING_LIST,"D",String.valueOf(sheet.getRow(4).getCell(3).getNumericCellValue()),"Audited", assetsDetailsRepository);
       
        //j== 2 for NTB 
        int j = 2;
        if(loanApplicationMaster.getBusinessTypeId() == CommonUtils.BusinessType.EXISTING_BUSINESS.getId()) {
    

           	int updateRow = assetsDetailsRepository.inActiveByAppIdAndFinancialYearlyStatementAndIsActive(loanApplicationMaster.getId());
           	log.info("---------------- inactive old estimate and project data ------- updated row ==> {} " , updateRow);

           	extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, ASSETS_MAPPING_LIST,"E",String.valueOf(sheet.getRow(4).getCell(4).getNumericCellValue()),"Estimated",assetsDetailsRepository);
           	j=5;


        }
        
        if(loanApplicationMaster.getProductId()!=15 && loanApplicationMaster.getProductId()!=1 ){

        	/*int j = 5;*/
        	for(int i = 0; i < loanApplicationMaster.getTenure(); i++) { 
        		extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, ASSETS_MAPPING_LIST,CellReference.convertNumToColString(sheet.getRow(4).getCell(j).getColumnIndex()),String.valueOf(sheet.getRow(4).getCell(j).getNumericCellValue()),"Projected",assetsDetailsRepository);
        		j++;
        	}


        /*extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, ASSETS_MAPPING_LIST,"G",String.valueOf(sheet.getRow(4).getCell(6).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, ASSETS_MAPPING_LIST,"H",String.valueOf(sheet.getRow(4).getCell(7).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, ASSETS_MAPPING_LIST,"I",String.valueOf(sheet.getRow(4).getCell(8).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, ASSETS_MAPPING_LIST,"J",String.valueOf(sheet.getRow(4).getCell(9).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, ASSETS_MAPPING_LIST,"K",String.valueOf(sheet.getRow(4).getCell(10).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, ASSETS_MAPPING_LIST,"L",String.valueOf(sheet.getRow(4).getCell(11).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, ASSETS_MAPPING_LIST,"M",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, ASSETS_MAPPING_LIST,"N",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, ASSETS_MAPPING_LIST,"O",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, ASSETS_MAPPING_LIST,"P",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, ASSETS_MAPPING_LIST,"Q",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, ASSETS_MAPPING_LIST,"R",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, ASSETS_MAPPING_LIST,"S",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, ASSETS_MAPPING_LIST,"T",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, ASSETS_MAPPING_LIST,"U",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, ASSETS_MAPPING_LIST,"V",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, ASSETS_MAPPING_LIST,"W",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, ASSETS_MAPPING_LIST,"X",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, ASSETS_MAPPING_LIST,"Y",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",assetsDetailsRepository);
       */ }

    }

    public static void extractCellFromSheet(Long storageDetailsId,
                                            XSSFSheet sheet,
                                            LoanApplicationMaster loanApplicationMaster,
                                            ApplicationProposalMapping applicationProposalMapping,
                                            List<String> arrayList,
                                            String column,
                                            String year,
                                            String financialYearlyStatement,
                                            AssetsDetailsRepository assetsDetailsRepository) throws ExcelException
    {
        int arrayListCounter = 0;
        int nullCounter=0;
        for (int i = 0; i < ASSETS_MAPPING_LIST.size(); i++) {
            if ((getNumericDataFromCell(sheet,column + ASSETS_MAPPING_LIST.get(i)))==0.0) {
                ++nullCounter;
            }
        }
        
        if(nullCounter!=54) {

        	/*Double yearFromSheet  = Double.valueOf(year) ;
        	AssetsDetails cmaAssets = assetsDetailsRepository.findByLoanApplicationMasterIdAndYearAndFinancialYearlyStatementAndIsActive(loanApplicationMaster.getId(), String.valueOf(yearFromSheet.longValue()) ,  financialYearlyStatement , true );

        	if(cmaAssets != null &&  "Audited".equalsIgnoreCase(cmaAssets.getFinancialYearlyStatement()) && yearFromSheet <= Double.valueOf(cmaAssets.getYear()) ) {

           		throw new ExcelException("Invalid cma details");

           	}*/
        	
        	cmaValidationFromDB(assetsDetailsRepository, loanApplicationMaster.getId(), applicationProposalMapping.getProposalId(), year);
        	
        	AssetsDetails cmaAssets = new AssetsDetails();
    		cmaAssets.setCreatedDate(new Date());
    		cmaAssets.setModifiedDate(new Date());
        	log.info("calledd===============");
        	cmaAssets.setLoanApplicationMaster(loanApplicationMaster);
        	cmaAssets.setStorageDetailsId(storageDetailsId);

            cmaAssets.setApplicationProposalMapping(applicationProposalMapping);

            cmaAssets.setYear(CommonUtils.getCMAFilterYear(year));
            cmaAssets.setFinancialYearlyStatement(financialYearlyStatement);
            cmaAssets.setCashAndBankBalance(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setInvestments(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setGovernmentAndOtherTrustee(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setFixedDepositsWithBanks(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setReceivableOtherThanDefferred(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setExportReceivables(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setInstalmentsDeferred(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setInventory(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setRawMaterial(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setRawMaterialImported(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setRawMaterialIndegenous(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setStockInProcess(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setFinishedGoods(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setOtherConsumableSpares(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setOtherConsumableSparesImported(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setOtherConsumableSparesIndegenous(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setAdvanceToSupplierRawMaterials(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setAdvancePaymentTaxes(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setOtherCurrentAssets(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setTotalCurrentAssets((getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++))));
            cmaAssets.setGrossBlock(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setLandBuilding(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setPlantMachines(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setGrossBlock1(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setGrossBlock2(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setGrossBlock3(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setDepreciationToDate(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setImpairmentAsset(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setNetBlock(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setOtherNcaOtherCapitalWorkInprogress(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setInvestmentsOrBookDebts(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setInvestmentsInSubsidiary(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setOthers(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setAdvanceToSuppliersCapitalGoods(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setDeferredReceviables(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setDeferredReceviablesOthers(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setOthersPreOperativeExpensesPending(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setOthersAssetsInTransit(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setOthersOther(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setNonConsumableStoreAndSpares(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setOtherNonCurrentAssets(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setTotalOtherNonCurrentAssets(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setIntangibleAssets(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setPatents(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setGoodWill(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setPrelimExpenses(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setBadOrDoubtfulExpenses(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setAnyOther(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setOtherIncomeNeedTocCheckAsset(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setTotalAssets(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setTangibleNetWorth(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setNetWorkingCapital(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setCurrentRatio(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setTotalOutSideLiability(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setTotalTermLiability(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));


            cmaAssets.setIsActive(true);
            cmaAssets.setCreatedDate(new Date());
            cmaAssets.setModifiedDate(new Date());
            assetsDetailsRepository.save(cmaAssets);
        }
    }

    public static void extractCellFromSheet(Long storageDetailsId,
    										XSSFSheet sheet,
                                            LoanApplicationMaster loanApplicationMaster,
                                            List<String> arrayList,
                                            String column,
                                            String year,
                                            String financialYearlyStatement,
                                            AssetsDetailsRepository assetsDetailsRepository) throws ExcelException
    {
        int arrayListCounter = 0;
        int nullCounter=0;
        for (int i = 0; i < ASSETS_MAPPING_LIST.size(); i++) {
            if ((getNumericDataFromCell(sheet,column + ASSETS_MAPPING_LIST.get(i)))==0.0) {
                ++nullCounter;
            }
        }

        if(!(nullCounter==54)) {

        	Double yearFromSheet  = Double.valueOf(year) ;
        	AssetsDetails cmaAssets = assetsDetailsRepository.findByLoanApplicationMasterIdAndYearAndFinancialYearlyStatementAndIsActive(loanApplicationMaster.getId(), String.valueOf(yearFromSheet.longValue()) ,  financialYearlyStatement , true );

        	if(cmaAssets != null &&  "Audited".equalsIgnoreCase(cmaAssets.getFinancialYearlyStatement()) && yearFromSheet <= Double.valueOf(cmaAssets.getYear()) ) {

           		throw new  ExcelException("Invalid cma details");

           	}

        	cmaAssets = new AssetsDetails();
    		cmaAssets.setCreatedDate(new Date());
    		cmaAssets.setModifiedDate(new Date());
        	log.info("calledd===============");
        	cmaAssets.setLoanApplicationMaster(loanApplicationMaster);
        	cmaAssets.setStorageDetailsId(storageDetailsId);

        	cmaAssets.setYear(CommonUtils.getCMAFilterYear(year));
        	cmaAssets.setFinancialYearlyStatement(financialYearlyStatement);
            cmaAssets.setCashAndBankBalance(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setInvestments(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setGovernmentAndOtherTrustee(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setFixedDepositsWithBanks(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setReceivableOtherThanDefferred(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setExportReceivables(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setInstalmentsDeferred(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setInventory(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setRawMaterial(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setRawMaterialImported(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setRawMaterialIndegenous(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setStockInProcess(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setFinishedGoods(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setOtherConsumableSpares(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setOtherConsumableSparesImported(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setOtherConsumableSparesIndegenous(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setAdvanceToSupplierRawMaterials(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setAdvancePaymentTaxes(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setOtherCurrentAssets(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setTotalCurrentAssets((getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++))));
            cmaAssets.setGrossBlock(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setLandBuilding(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setPlantMachines(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setGrossBlock1(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setGrossBlock2(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setGrossBlock3(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setDepreciationToDate(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setImpairmentAsset(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setNetBlock(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setOtherNcaOtherCapitalWorkInprogress(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setInvestmentsOrBookDebts(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setInvestmentsInSubsidiary(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setOthers(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setAdvanceToSuppliersCapitalGoods(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setDeferredReceviables(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setDeferredReceviablesOthers(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setOthersPreOperativeExpensesPending(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setOthersAssetsInTransit(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setOthersOther(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setNonConsumableStoreAndSpares(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setOtherNonCurrentAssets(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setTotalOtherNonCurrentAssets(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setIntangibleAssets(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setPatents(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setGoodWill(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setPrelimExpenses(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setBadOrDoubtfulExpenses(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setAnyOther(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setOtherIncomeNeedTocCheckAsset(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setTotalAssets(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setTangibleNetWorth(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setNetWorkingCapital(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setCurrentRatio(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setTotalOutSideLiability(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setTotalTermLiability(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));


            cmaAssets.setIsActive(true);

//          cmaAssets.setCreatedBy(createdBy);
//          cmaAssets.setModifiedBy(modifiedBy);
            
            assetsDetailsRepository.save(cmaAssets);
            

        }
    }
    public static double getNumericDataFromCell(XSSFSheet sheet,String cellNumber)
    {
    	log.info("getNumericDataFromCell:==>{} " ,cellNumber );
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
    
    public static void cmaValidationFromDB(AssetsDetailsRepository assetsDetailsRepository,Long applicationId , Long proposalId ,  String year) throws ExcelException {
    	log.info("============= Enter into cmaValidationFromDB() ============ applicationId ==> {} year ==> {}" , applicationId  , year);
    	
    	
    	int rowUpdated = 0;
    	List<AssetsDetails> assetsDetailsList  = assetsDetailsRepository.findByLoanApplicationMasterIdAndYearAndIsActive(applicationId , year,  true);
    	
    	if(assetsDetailsList.stream().anyMatch(opsd -> "Audited".equalsIgnoreCase(opsd.getFinancialYearlyStatement()))) {
    		throw new ExcelException("Invalid cma file");
    	}else {
    		if(proposalId == null  ) {
    			rowUpdated = assetsDetailsRepository.inActiveByAppIdAndFinancialYearlyStatementAndIsActive(applicationId);
    		}else {
    			rowUpdated = assetsDetailsRepository.inActiveByAppIdAndProposalIdAndFinancialYearlyStatementAndIsActive(applicationId, proposalId);
    		}
    		
    		log.info("----------------- inactive the old Estimated and Projected FinancialYearlyStatement ------------ rowUpdated ==> {}" ,rowUpdated);
    	}

    	log.info("============= Exit from cmaValidationFromDB() ============ ");
    } 
}

