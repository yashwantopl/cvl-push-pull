package com.capitaworld.service.loans.utils.dpr;

import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.AvailabilityProposedPlantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CapacityDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.DprUserDataDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.RequirementsAndAvailabilityRawMaterialsDetail;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AvailabilityProposedPlantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CapacityDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.RequirementsAndAvailabilityRawMaterialsDetailRepository;


/**
 * @author Sanket
 *
 */
public class DprNinthSheetExcelReader
{
    public static void run(Long storageDetailsId,XSSFSheet sheet,LoanApplicationMaster loanApplicationMaster,CapacityDetailRepository capacityDetailRepository,AvailabilityProposedPlantDetailRepository availabilityProposedPlantDetailRepository,RequirementsAndAvailabilityRawMaterialsDetailRepository requirementsAndAvailabilityRawMaterialsDetailRepository,DprUserDataDetail dprUserDataDetail) {

	     saveUnitCapacity(storageDetailsId,sheet,"8",loanApplicationMaster,capacityDetailRepository);
         saveUnitCapacity(storageDetailsId,sheet,"9",loanApplicationMaster,capacityDetailRepository);
        saveUnitCapacity(storageDetailsId,sheet,"10",loanApplicationMaster,capacityDetailRepository);
        saveUnitCapacity(storageDetailsId,sheet,"11",loanApplicationMaster,capacityDetailRepository);
        saveUnitCapacity(storageDetailsId,sheet,"12",loanApplicationMaster,capacityDetailRepository);
        saveUnitCapacity(storageDetailsId,sheet,"13",loanApplicationMaster,capacityDetailRepository);
        saveUnitCapacity(storageDetailsId,sheet,"14",loanApplicationMaster,capacityDetailRepository);
        saveUnitCapacity(storageDetailsId,sheet,"15",loanApplicationMaster,capacityDetailRepository);

        //saveAvailabilityFfProposedPlantAndMachineries(storageDetailsId,sheet,"24",loanApplicationMaster,availabilityProposedPlantDetailRepository);
        saveAvailabilityFfProposedPlantAndMachineries(storageDetailsId,sheet,"25",loanApplicationMaster,availabilityProposedPlantDetailRepository);
        saveAvailabilityFfProposedPlantAndMachineries(storageDetailsId,sheet,"26",loanApplicationMaster,availabilityProposedPlantDetailRepository);
        saveAvailabilityFfProposedPlantAndMachineries(storageDetailsId,sheet,"27",loanApplicationMaster,availabilityProposedPlantDetailRepository);
        saveAvailabilityFfProposedPlantAndMachineries(storageDetailsId,sheet,"28",loanApplicationMaster,availabilityProposedPlantDetailRepository);
        saveAvailabilityFfProposedPlantAndMachineries(storageDetailsId,sheet,"29",loanApplicationMaster,availabilityProposedPlantDetailRepository);
        saveAvailabilityFfProposedPlantAndMachineries(storageDetailsId,sheet,"30",loanApplicationMaster,availabilityProposedPlantDetailRepository);
        saveAvailabilityFfProposedPlantAndMachineries(storageDetailsId,sheet,"31",loanApplicationMaster,availabilityProposedPlantDetailRepository);
        saveAvailabilityFfProposedPlantAndMachineries(storageDetailsId,sheet,"32",loanApplicationMaster,availabilityProposedPlantDetailRepository);
        saveAvailabilityFfProposedPlantAndMachineries(storageDetailsId,sheet,"33",loanApplicationMaster,availabilityProposedPlantDetailRepository);


		//saveRequirementsAndAvailabilityOfRawMaterials(storageDetailsId,sheet,"38",loanApplicationMaster,requirementsAndAvailabilityRawMaterialsDetailRepository);
		saveRequirementsAndAvailabilityOfRawMaterials(storageDetailsId,sheet,"39",loanApplicationMaster,requirementsAndAvailabilityRawMaterialsDetailRepository);
		saveRequirementsAndAvailabilityOfRawMaterials(storageDetailsId,sheet,"40",loanApplicationMaster,requirementsAndAvailabilityRawMaterialsDetailRepository);
		saveRequirementsAndAvailabilityOfRawMaterials(storageDetailsId,sheet,"41",loanApplicationMaster,requirementsAndAvailabilityRawMaterialsDetailRepository);
		saveRequirementsAndAvailabilityOfRawMaterials(storageDetailsId,sheet,"42",loanApplicationMaster,requirementsAndAvailabilityRawMaterialsDetailRepository);
		saveRequirementsAndAvailabilityOfRawMaterials(storageDetailsId,sheet,"43",loanApplicationMaster,requirementsAndAvailabilityRawMaterialsDetailRepository);
		saveRequirementsAndAvailabilityOfRawMaterials(storageDetailsId,sheet,"44",loanApplicationMaster,requirementsAndAvailabilityRawMaterialsDetailRepository);
		saveRequirementsAndAvailabilityOfRawMaterials(storageDetailsId,sheet,"45",loanApplicationMaster,requirementsAndAvailabilityRawMaterialsDetailRepository);
		saveRequirementsAndAvailabilityOfRawMaterials(storageDetailsId,sheet,"46",loanApplicationMaster,requirementsAndAvailabilityRawMaterialsDetailRepository);

        //save 595 question
        try {
            //sheet number 3 fill 780 question answer in db
            String question595Answer = getDataFromCell(sheet, "C17");
            if (!(question595Answer.isEmpty())) {//if textbox is empty not insert record
                if (!(question595Answer.equals("Insert Text Here"))) {
                    dprUserDataDetail.setWorking_Days_in_month__number(question595Answer);
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }


        //save 596 question
        try {
            //sheet number 3 fill 780 question answer in db
            String question596Answer = getDataFromCell(sheet, "C18");
            if (!(question596Answer.isEmpty())) {//if textbox is empty not insert record
                if (!(question596Answer.equals("Insert Text Here"))) {
                    dprUserDataDetail.setShiftsInDayNumber(question596Answer);
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void saveRequirementsAndAvailabilityOfRawMaterials(Long storageDetailsId,XSSFSheet sheet, String rowNumber,LoanApplicationMaster loanApplicationMaster,RequirementsAndAvailabilityRawMaterialsDetailRepository requirementsAndAvailabilityRawMaterialsDetailRepository)
    {
        int nullCounter=0;
        String quantityMeasurement  = getDataFromCell(sheet,"C38");
        String name                 = getDataFromCell(sheet,"B"+rowNumber);
        String quantity             = getDataFromCell(sheet,"C"+rowNumber);
        String sources              = getDataFromCell(sheet,"D"+rowNumber);
        String leadTime             = getDataFromCell(sheet,"E"+rowNumber);
        String availability         = getDataFromCell(sheet,"F"+rowNumber);


        if (name.isEmpty())
        {
            ++nullCounter;
        }
        if (quantity.isEmpty())
        {
            ++nullCounter;
        }
        if (sources.isEmpty())
        {
            ++nullCounter;
        }
        if (leadTime.isEmpty())
        {
            ++nullCounter;
        }
        if (availability.isEmpty())
        {
            ++nullCounter;
        }
        if (!(nullCounter==5))
        {
            try {
            	RequirementsAndAvailabilityRawMaterialsDetail requirementsAndAvailabilityOfRawMaterials = new RequirementsAndAvailabilityRawMaterialsDetail();
                requirementsAndAvailabilityOfRawMaterials.setApplicationId(loanApplicationMaster);
                requirementsAndAvailabilityOfRawMaterials.setName(name);
                requirementsAndAvailabilityOfRawMaterials.setQuality(quantity);
                requirementsAndAvailabilityOfRawMaterials.setSources(sources);
                requirementsAndAvailabilityOfRawMaterials.setLeadTime(leadTime);
                requirementsAndAvailabilityOfRawMaterials.setAvailability(availability);
//                UnitOfMeasurement unitOfMeasurement = unitOfMeasurementService.getUnitOfMeasurementFromName(quantityMeasurement);
                requirementsAndAvailabilityOfRawMaterials.setMeasurementUnitQuantity(quantityMeasurement);
                requirementsAndAvailabilityOfRawMaterials.setStorageDetailsId(storageDetailsId);
                requirementsAndAvailabilityOfRawMaterials.setIsActive(true);
                requirementsAndAvailabilityOfRawMaterials.setCreatedDate(new Date());
                requirementsAndAvailabilityOfRawMaterials.setModifiedDate(new Date());
                requirementsAndAvailabilityRawMaterialsDetailRepository.save(requirementsAndAvailabilityOfRawMaterials);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveAvailabilityFfProposedPlantAndMachineries(Long storageDetailsId,XSSFSheet sheet, String rowNumber,LoanApplicationMaster loanApplicationMaster,AvailabilityProposedPlantDetailRepository availabilityProposedPlantDetailRepository)
    {
        int nullCounter=0;
        String DescriptionOfPAndM        = getDataFromCell(sheet,"B"+rowNumber);
        String useOrPurpose              = getDataFromCell(sheet,"C"+rowNumber);
        String importedOrIndigenous      = getDataFromCell(sheet,"D"+rowNumber);
        String suppliers                 = getDataFromCell(sheet,"E"+rowNumber);
        String estimatedValue            = getDataFromCell(sheet,"F"+rowNumber);


        if (DescriptionOfPAndM.isEmpty())
        {
            ++nullCounter;
        }
        if (useOrPurpose.isEmpty())
        {
            ++nullCounter;
        }
        if (importedOrIndigenous.isEmpty())
        {
            ++nullCounter;
        }
        if (suppliers.isEmpty())
        {
            ++nullCounter;
        }
        if (estimatedValue.isEmpty())
        {
            ++nullCounter;
        }
        if (!(nullCounter==5))
        {
            try {
            	AvailabilityProposedPlantDetail availabilityofProposedPlantandMachineries = new AvailabilityProposedPlantDetail();
                availabilityofProposedPlantandMachineries.setApplicationId(loanApplicationMaster);
                availabilityofProposedPlantandMachineries.setDescriptionPM(DescriptionOfPAndM);
                availabilityofProposedPlantandMachineries.setUseOrPurpose(useOrPurpose);
                availabilityofProposedPlantandMachineries.setImportedOrIndigenous(importedOrIndigenous);
                availabilityofProposedPlantandMachineries.setSupplier(suppliers);
                availabilityofProposedPlantandMachineries.setEstimatedValue(estimatedValue);
                availabilityofProposedPlantandMachineries.setStorageDetailsId(storageDetailsId);
                availabilityofProposedPlantandMachineries.setIsActive(true);
                availabilityofProposedPlantandMachineries.setCreatedDate(new Date());
                availabilityofProposedPlantandMachineries.setModifiedDate(new Date());
                availabilityProposedPlantDetailRepository.save(availabilityofProposedPlantandMachineries);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void saveUnitCapacity(Long storageDetailsId,XSSFSheet sheet, String rowNumber,LoanApplicationMaster loanApplicationMaster,CapacityDetailRepository capacityDetailRepository)
    {
        int nullCounter=0;
        String unitOfMeasurementOfCurrentInstalledCapacity = getDataFromCell(sheet,"C7");
        String unitOfMeasurementOfCurrentOperatingLevel    = getDataFromCell(sheet,"E7");
        String product                     = getDataFromCell(sheet,"B"+rowNumber);
        String currentInstalledCapacity    = getDataFromCell(sheet,"C"+rowNumber);
        String currentOperatingLevel       = getDataFromCell(sheet,"D"+rowNumber);
        String futureCapacity              = getDataFromCell(sheet,"E"+rowNumber);

        if (product.isEmpty())
        {
            ++nullCounter;
        }
        if (currentInstalledCapacity.isEmpty())
        {
            ++nullCounter;
        }
        if (currentOperatingLevel.isEmpty())
        {
            ++nullCounter;
        }
        if (futureCapacity.isEmpty())
        {
            ++nullCounter;
        }
        if (!(nullCounter==4))
        {
            try {
                CapacityDetail unitCapacity = new CapacityDetail();
                unitCapacity.setApplicationId(loanApplicationMaster);
                unitCapacity.setProductName(product);
                unitCapacity.setCurrentInstalledCapacity(currentInstalledCapacity);
                unitCapacity.setCurrentOperatingLevel(currentOperatingLevel);
                unitCapacity.setFutureCapacity(futureCapacity);
                unitCapacity.setMeasurementForCurrentInstalledCapacity(unitOfMeasurementOfCurrentInstalledCapacity);
                unitCapacity.setMeasurementForFutureCapacity(unitOfMeasurementOfCurrentOperatingLevel);
                unitCapacity.setStorageDetailsId(storageDetailsId);
                unitCapacity.setIsActive(true);
                unitCapacity.setCreatedDate(new Date());
                unitCapacity.setModifiedDate(new Date());
                capacityDetailRepository.save(unitCapacity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
