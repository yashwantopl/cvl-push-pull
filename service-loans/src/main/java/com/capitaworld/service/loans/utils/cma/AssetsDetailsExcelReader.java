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
import com.capitaworld.service.loans.domain.fundseeker.corporate.AssetsDetails;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AssetsDetailsRepository;



public class AssetsDetailsExcelReader
{
	public static final Logger log = LoggerFactory.getLogger(AssetsDetailsExcelReader.class);
    public static List<String> assetsMappingList = new ArrayList<String>();
    public static final DecimalFormat decimalFormat = new DecimalFormat("#.##");
    public static void run(Long storageDetailsId,XSSFSheet sheet,LoanApplicationMaster loanApplicationMaster,AssetsDetailsRepository assetsDetailsRepository) {
        assetsMappingList.clear();
        
        assetsMappingList.add("9");
        assetsMappingList.add("11");
        assetsMappingList.add("13");
        assetsMappingList.add("15");
        assetsMappingList.add("16");
        assetsMappingList.add("18");
        assetsMappingList.add("20");
        assetsMappingList.add("22");
        assetsMappingList.add("24");
        assetsMappingList.add("26");
        assetsMappingList.add("27");
        assetsMappingList.add("29");
        assetsMappingList.add("31");
        assetsMappingList.add("33");
        assetsMappingList.add("34");
        assetsMappingList.add("35");
        assetsMappingList.add("37");
        assetsMappingList.add("39");
        assetsMappingList.add("41");
        assetsMappingList.add("43");
        assetsMappingList.add("45");      
        assetsMappingList.add("46");
        assetsMappingList.add("47");
        assetsMappingList.add("48");
        assetsMappingList.add("49");
        assetsMappingList.add("50");
        assetsMappingList.add("52");
        assetsMappingList.add("54");
        assetsMappingList.add("56");
        assetsMappingList.add("60");
        assetsMappingList.add("62");
        assetsMappingList.add("64");
        assetsMappingList.add("65");
        assetsMappingList.add("67");
        assetsMappingList.add("69");
        assetsMappingList.add("71");
        assetsMappingList.add("72");
        assetsMappingList.add("73");
        assetsMappingList.add("74");
        assetsMappingList.add("76");
        assetsMappingList.add("78");
        assetsMappingList.add("80");
        assetsMappingList.add("82");
        assetsMappingList.add("83");
        assetsMappingList.add("84");
        assetsMappingList.add("85");
        assetsMappingList.add("86");
        assetsMappingList.add("87");
        assetsMappingList.add("89");
        assetsMappingList.add("91");
        assetsMappingList.add("93");
        assetsMappingList.add("95");
        assetsMappingList.add("97");
        assetsMappingList.add("99");
        
            /*
              * this method extract data from excel associate column and row wise
              * e.g. you want to extract B13,B14,... cell data for year 2014
             */
      
       System.out.println("OperatingStatementDetailsExcelReader -----------> "+ sheet.getRow(4).getCell(1).getNumericCellValue());   
//        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"B",String.valueOf(sheet.getRow(4).getCell(1).getNumericCellValue()),"Audited", assetsDetailsRepository);
//        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"C",String.valueOf(sheet.getRow(4).getCell(2).getNumericCellValue()),"Audited", assetsDetailsRepository);
//        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"D",String.valueOf(sheet.getRow(4).getCell(3).getNumericCellValue()),"Audited", assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"E",String.valueOf(sheet.getRow(4).getCell(4).getNumericCellValue()),"Estimated",assetsDetailsRepository);
        if(loanApplicationMaster.getProductId()!=15 && loanApplicationMaster.getProductId()!=1 ){
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"F",String.valueOf(sheet.getRow(4).getCell(5).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"G",String.valueOf(sheet.getRow(4).getCell(6).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"H",String.valueOf(sheet.getRow(4).getCell(7).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"I",String.valueOf(sheet.getRow(4).getCell(8).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"J",String.valueOf(sheet.getRow(4).getCell(9).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"K",String.valueOf(sheet.getRow(4).getCell(10).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"L",String.valueOf(sheet.getRow(4).getCell(11).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"M",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"N",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"O",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"P",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"Q",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"R",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"S",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"T",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"U",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"V",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"W",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"X",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"Y",String.valueOf(sheet.getRow(4).getCell(12).getNumericCellValue()),"Projected",assetsDetailsRepository);
        }
    }

    public static void extractCellFromSheet(Long storageDetailsId,
    										XSSFSheet sheet,
                                            LoanApplicationMaster loanApplicationMaster,
                                            List<String> arrayList,
                                            String column,
                                            String year,
                                            String financialYearlyStatement,
                                            AssetsDetailsRepository assetsDetailsRepository)
    {
        int arrayListCounter = 0;
        int nullCounter=0;
        for (int i = 0; i < assetsMappingList.size(); i++) {
            if ((getNumericDataFromCell(sheet,column + assetsMappingList.get(i)))==0.0) {
                ++nullCounter;
            }
        }
        
        if(!(nullCounter==54)) {
            
        	AssetsDetails cmaAssets = new AssetsDetails();
            System.out.println("calledd===============");
        	cmaAssets.setLoanApplicationMaster(loanApplicationMaster);
        	cmaAssets.setStorageDetailsId(storageDetailsId);
        	
        	cmaAssets.setYear(year);
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
            cmaAssets.setTotalAssets(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setTangibleNetWorth(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setNetWorkingCapital(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setCurrentRatio(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setTotalOutSideLiability(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setTotalTermLiability(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));

            
            cmaAssets.setIsActive(true);
            cmaAssets.setCreatedDate(new Date());
            cmaAssets.setModifiedDate(new Date());
//          cmaAssets.setCreatedBy(createdBy);
//          cmaAssets.setModifiedBy(modifiedBy);
            
            assetsDetailsRepository.save(cmaAssets);
            

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
