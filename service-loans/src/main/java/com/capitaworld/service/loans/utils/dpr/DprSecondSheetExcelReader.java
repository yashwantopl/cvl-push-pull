package com.capitaworld.service.loans.utils.dpr;


import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.EmployeesCategoryBreaksDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.KeyManagementDetail;
import com.capitaworld.service.loans.repository.fundseeker.corporate.EmployeesCategoryBreaksDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.KeyManagementDetailRepository;

/**
 * @author Sanket
 *
 */
public class DprSecondSheetExcelReader
{
    public static void run(Long storageDetailsId,XSSFSheet sheet,LoanApplicationMaster loanApplicationMaster,KeyManagementDetailRepository keyManagementDetailRepository, EmployeesCategoryBreaksDetailRepository employeesCategoryBreaksDetailRepository) {

		saveEmployeeCategoryStatus(storageDetailsId, sheet, "18", loanApplicationMaster, employeesCategoryBreaksDetailRepository);
        saveEmployeeCategoryStatus(storageDetailsId, sheet, "19", loanApplicationMaster, employeesCategoryBreaksDetailRepository);
		saveEmployeeCategoryStatus(storageDetailsId, sheet, "20", loanApplicationMaster, employeesCategoryBreaksDetailRepository);
        saveEmployeeCategoryStatus(storageDetailsId, sheet, "21", loanApplicationMaster, employeesCategoryBreaksDetailRepository);
        saveEmployeeCategoryStatus(storageDetailsId, sheet, "22", loanApplicationMaster, employeesCategoryBreaksDetailRepository);

		saveKeyManagement(storageDetailsId, sheet, "7", loanApplicationMaster, keyManagementDetailRepository);
        saveKeyManagement(storageDetailsId, sheet, "8", loanApplicationMaster, keyManagementDetailRepository);
        saveKeyManagement(storageDetailsId, sheet, "9", loanApplicationMaster, keyManagementDetailRepository);
        saveKeyManagement(storageDetailsId, sheet, "10", loanApplicationMaster, keyManagementDetailRepository);
        saveKeyManagement(storageDetailsId, sheet, "11", loanApplicationMaster, keyManagementDetailRepository);
        saveKeyManagement(storageDetailsId, sheet, "12", loanApplicationMaster, keyManagementDetailRepository);
        saveKeyManagement(storageDetailsId, sheet, "13", loanApplicationMaster, keyManagementDetailRepository);

    }
    
    public static void saveKeyManagement(Long storageDetailsId, XSSFSheet sheet, String rowNumber,LoanApplicationMaster loanApplicationMaster,KeyManagementDetailRepository keyManagementDetailRepository)
    {
        int nullCounter=0;
        String boardOfDirectorName  = getDataFromCell(sheet,"B"+rowNumber);
        String designation          = getDataFromCell(sheet,"C"+rowNumber);
        String qualification        = getDataFromCell(sheet,"D"+rowNumber);
        String experience           = getDataFromCell(sheet,"E"+rowNumber);
        String achievements         = getDataFromCell(sheet,"F"+rowNumber);
        String functionalDuties     = getDataFromCell(sheet,"G"+rowNumber);

        if (boardOfDirectorName.isEmpty())
        {
            ++nullCounter;
        }
        if (designation.isEmpty())
        {
            ++nullCounter;
        }
        if (qualification.isEmpty())
        {
            ++nullCounter;
        }
        if (experience.isEmpty())
        {
            ++nullCounter;
        }
        if (achievements.isEmpty())
        {
            ++nullCounter;
        }
        if (functionalDuties.isEmpty())
        {
            ++nullCounter;
        }
        if (!(nullCounter==6))
        {
            try {
                KeyManagementDetail keyManagement = new KeyManagementDetail();
                keyManagement.setName(boardOfDirectorName);
                keyManagement.setDesignation(designation);
                keyManagement.setQualification(qualification);
                keyManagement.setExperience(experience);
                keyManagement.setAnySpecialAchievement(achievements);
                keyManagement.setFunctionalDuties(functionalDuties);
                keyManagement.setApplicationId(loanApplicationMaster);
                keyManagement.setStorageDetailsId(storageDetailsId);
                keyManagement.setCreatedDate(new Date());
                keyManagement.setModifiedDate(new Date());
                keyManagement.setIsActive(true);
                keyManagementDetailRepository.save(keyManagement);

            } catch (Exception e) {
                e.printStackTrace();
            }
            // System.out.println(boardOfDirectorName + designation + qualification + experience + achievements + functionalDuties);
        }
    }
    public static void saveEmployeeCategoryStatus(Long storageDetailsId, XSSFSheet sheet, String rowNumber,LoanApplicationMaster loanApplicationMaster,EmployeesCategoryBreaksDetailRepository employeesCategoryBreaksDetailRepository)
    {
        int nullCounter=0;
        String category           = getDataFromCell(sheet,"B"+rowNumber);
        String currentNumber      = getDataFromCell(sheet,"C"+rowNumber);
        String proposedNumber     = getDataFromCell(sheet,"D"+rowNumber);
        //System.out.println("current : " + currentNumber + "-- Proposed :" + proposedNumber);

        if (currentNumber.isEmpty())
        {
            ++nullCounter;
        }
        if (proposedNumber.isEmpty())
        {
            ++nullCounter;
        }
        if (!(nullCounter==2)) {
        	EmployeesCategoryBreaksDetail employmentStatusCorporate = new EmployeesCategoryBreaksDetail();
            employmentStatusCorporate.setEmploymentStatusPresent(currentNumber);
            employmentStatusCorporate.setEmploymentStatusFuture(proposedNumber);
            employmentStatusCorporate.setEmployment(category.trim());
            employmentStatusCorporate.setApplicationId(loanApplicationMaster);
            employmentStatusCorporate.setStorageDetailsId(storageDetailsId);
            employmentStatusCorporate.setCreatedDate(new Date());
            employmentStatusCorporate.setModifiedDate(new Date());
            employmentStatusCorporate.setIsActive(true);
            employeesCategoryBreaksDetailRepository.save(employmentStatusCorporate);
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
