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
import com.capitaworld.service.loans.domain.fundseeker.corporate.AssetsDetails;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AssetsDetailsRepository;


public class AssetsDetailsExcelReader
{
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
        assetsMappingList.add("47");
        assetsMappingList.add("49");
        assetsMappingList.add("53");
        assetsMappingList.add("55");
        assetsMappingList.add("56");
        assetsMappingList.add("58");
        assetsMappingList.add("60");
        assetsMappingList.add("62");
        assetsMappingList.add("64");
        assetsMappingList.add("66");
        assetsMappingList.add("68");
        assetsMappingList.add("70");
        assetsMappingList.add("71");
        assetsMappingList.add("72");
        assetsMappingList.add("73");
        assetsMappingList.add("74");
        assetsMappingList.add("75");
        assetsMappingList.add("77");
        assetsMappingList.add("79");
        assetsMappingList.add("81");
        assetsMappingList.add("83");
        assetsMappingList.add("85");
        assetsMappingList.add("86");

            /*
              * this method extract data from excel associate column and row wise
              * e.g. you want to extract B13,B14,... cell data for year 2014
             */
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"B","2014",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"C","2015",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"D","2016",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"E","2017",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"F","2018",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"G","2019",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"H","2020",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"I","2021",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"J","2022",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"K","2023",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"L","2024",assetsDetailsRepository);
        extractCellFromSheet(storageDetailsId,sheet,loanApplicationMaster, assetsMappingList,"M","2025",assetsDetailsRepository);

    }

    public static void extractCellFromSheet(Long storageDetailsId,
    										XSSFSheet sheet,
                                            LoanApplicationMaster loanApplicationMaster,
                                            List<String> arrayList,
                                            String column,
                                            String year,
                                            AssetsDetailsRepository assetsDetailsRepository)
    {
        int arrayListCounter = 0;
        int nullCounter=0;
        for (int i = 0; i < assetsMappingList.size(); i++) {
            if ((getNumericDataFromCell(sheet,column + assetsMappingList.get(i)))==0.0) {
                ++nullCounter;
            }
        }
        
        if(!(nullCounter==44)) {
            
        	AssetsDetails cmaAssets = new AssetsDetails();
            
        	cmaAssets.setLoanApplicationMaster(loanApplicationMaster);
        	cmaAssets.setStorageDetailsId(storageDetailsId);
        	
        	cmaAssets.setYear(year);
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
            cmaAssets.setDepreciationToDate(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setNetBlock(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setInvestmentsOrBookDebts(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setInvestmentsInSubsidiary(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setOthers(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setAdvanceToSupplierRawMaterials(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setDeferredReceviables(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setDeferredReceviablesOthers(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setNonConsumableStoreAndSpares(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setOtherNonCurrentAssets(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setTotalOtherNonCurrentAssets(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
            cmaAssets.setTotalIntangibleAssets(getNumericDataFromCell(sheet, column + arrayList.get(arrayListCounter++)));
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
