package com.capitaworld.service.loans.utils.dpr;


import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.BoardOfDirectorsDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.StrategicAlliancesDetail;
import com.capitaworld.service.loans.repository.fundseeker.corporate.BoardOfDirectorsDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.StrategicAlliancesDetailRepository;


/**
 * @author Sanket
 *
 */
public class DprFirstSheetExcelReader {
    public static void run(Long storageDetailsId,XSSFSheet sheet,LoanApplicationMaster loanApplicationMaster,BoardOfDirectorsDetailRepository boardOfDirectorsDetailRepository,StrategicAlliancesDetailRepository strategicAlliancesDetailRepository){

        //save Boards Of Directors
		saveBoardsOfDirectors(storageDetailsId,sheet, "12",loanApplicationMaster,boardOfDirectorsDetailRepository);
        saveBoardsOfDirectors(storageDetailsId,sheet, "13",loanApplicationMaster,boardOfDirectorsDetailRepository);
        saveBoardsOfDirectors(storageDetailsId,sheet, "14",loanApplicationMaster,boardOfDirectorsDetailRepository);
        saveBoardsOfDirectors(storageDetailsId,sheet, "15",loanApplicationMaster,boardOfDirectorsDetailRepository);
        saveBoardsOfDirectors(storageDetailsId,sheet, "16",loanApplicationMaster,boardOfDirectorsDetailRepository);
        saveBoardsOfDirectors(storageDetailsId,sheet, "17",loanApplicationMaster,boardOfDirectorsDetailRepository);
        saveBoardsOfDirectors(storageDetailsId,sheet, "18",loanApplicationMaster,boardOfDirectorsDetailRepository);

        //save Strategic Alliance
		saveStrategicAlliance(storageDetailsId,sheet,"23",loanApplicationMaster,strategicAlliancesDetailRepository);
        saveStrategicAlliance(storageDetailsId,sheet,"24",loanApplicationMaster,strategicAlliancesDetailRepository);
        saveStrategicAlliance(storageDetailsId,sheet,"25",loanApplicationMaster,strategicAlliancesDetailRepository);
        saveStrategicAlliance(storageDetailsId,sheet,"26",loanApplicationMaster,strategicAlliancesDetailRepository);
        saveStrategicAlliance(storageDetailsId,sheet,"27",loanApplicationMaster,strategicAlliancesDetailRepository);
        saveStrategicAlliance(storageDetailsId,sheet,"28",loanApplicationMaster,strategicAlliancesDetailRepository);
        saveStrategicAlliance(storageDetailsId,sheet,"29",loanApplicationMaster,strategicAlliancesDetailRepository);
        saveStrategicAlliance(storageDetailsId,sheet,"30",loanApplicationMaster,strategicAlliancesDetailRepository);
        saveStrategicAlliance(storageDetailsId,sheet,"31",loanApplicationMaster,strategicAlliancesDetailRepository);
        saveStrategicAlliance(storageDetailsId,sheet,"32",loanApplicationMaster,strategicAlliancesDetailRepository);
        saveStrategicAlliance(storageDetailsId,sheet,"33",loanApplicationMaster,strategicAlliancesDetailRepository);
        saveStrategicAlliance(storageDetailsId,sheet,"34",loanApplicationMaster,strategicAlliancesDetailRepository);
    }

    public static void saveStrategicAlliance(Long storageDetailsId,XSSFSheet sheet, String rowNumber,LoanApplicationMaster loanApplicationMaster,StrategicAlliancesDetailRepository strategicAlliancesDetailRepository)
    {
        int nullCounter=0;
        String keyAlliance          = getDataFromCell(sheet,"B"+rowNumber);
        String name                 = getDataFromCell(sheet,"C"+rowNumber);
        String detailsOfRelationship= getDataFromCell(sheet,"D"+rowNumber);
        if (name.isEmpty())
        {
            ++nullCounter;
        }
        if (detailsOfRelationship.isEmpty())
        {
            ++nullCounter;
        }
        if (!(nullCounter==2))
        {
            try {
                StrategicAlliancesDetail strategicAlliances = new StrategicAlliancesDetail();
                strategicAlliances.setApplicationId(loanApplicationMaster);
                strategicAlliances.setRelationshipDetails(detailsOfRelationship);
                strategicAlliances.setName(name);
                strategicAlliances.setKeyAlliancePartners(keyAlliance);
                strategicAlliances.setStorageDetailsId(storageDetailsId);
//                strategicAlliances.setCreatedBy(createdBy);
                strategicAlliances.setCreatedDate(new Date());
                strategicAlliances.setIsActive(true);
                strategicAlliances.setModifiedDate(new Date());
                strategicAlliancesDetailRepository.save(strategicAlliances);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
    public static void saveBoardsOfDirectors(Long storageDetailsId,XSSFSheet sheet, String rowNumber,LoanApplicationMaster loanApplicationMaster,BoardOfDirectorsDetailRepository boardOfDirectorsDetailRepository)
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
                BoardOfDirectorsDetail boardOfDirectorsDetail = new BoardOfDirectorsDetail();
                boardOfDirectorsDetail.setName(boardOfDirectorName);
                boardOfDirectorsDetail.setDesignation(designation);
                boardOfDirectorsDetail.setQualification(qualification);
                boardOfDirectorsDetail.setExperience(experience);
                boardOfDirectorsDetail.setAnySpecialAchievement(achievements);
                boardOfDirectorsDetail.setFunctionalDuties(functionalDuties);
                boardOfDirectorsDetail.setApplicationId(loanApplicationMaster);
                boardOfDirectorsDetail.setStorageDetailsId(storageDetailsId);
                boardOfDirectorsDetail.setCreatedDate(new Date());
                boardOfDirectorsDetail.setModifiedDate(new Date());
                boardOfDirectorsDetail.setIsActive(true);
                boardOfDirectorsDetailRepository.save(boardOfDirectorsDetail);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // System.out.println(boardOfDirectorName + designation + qualification + experience + achievements + functionalDuties);
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
