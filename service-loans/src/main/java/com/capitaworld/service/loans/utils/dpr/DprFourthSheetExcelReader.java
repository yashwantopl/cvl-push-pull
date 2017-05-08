package com.capitaworld.service.loans.utils.dpr;

import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.DprUserDataDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.TechnologyPositioningDetail;
import com.capitaworld.service.loans.repository.fundseeker.corporate.TechnologyPositioningDetailRepository;


/**
 * @author Sanket
 *
 */
public class DprFourthSheetExcelReader
{
    public static void run(Long storageDetailsId,XSSFSheet sheet,LoanApplicationMaster loanApplicationMaster,TechnologyPositioningDetailRepository technologyPositioningDetailRepository,DprUserDataDetail dprUserDataDetail) {

        saveTechnologyPositioning(storageDetailsId,sheet,"12",loanApplicationMaster,technologyPositioningDetailRepository);
        saveTechnologyPositioning(storageDetailsId,sheet,"13",loanApplicationMaster,technologyPositioningDetailRepository);
        saveTechnologyPositioning(storageDetailsId,sheet,"14",loanApplicationMaster,technologyPositioningDetailRepository);
        saveTechnologyPositioning(storageDetailsId,sheet,"15",loanApplicationMaster,technologyPositioningDetailRepository);
        saveTechnologyPositioning(storageDetailsId,sheet,"16",loanApplicationMaster,technologyPositioningDetailRepository);
        saveTechnologyPositioning(storageDetailsId,sheet,"17",loanApplicationMaster,technologyPositioningDetailRepository);
        saveTechnologyPositioning(storageDetailsId,sheet,"18",loanApplicationMaster,technologyPositioningDetailRepository);
        saveTechnologyPositioning(storageDetailsId,sheet,"19",loanApplicationMaster,technologyPositioningDetailRepository);
        saveTechnologyPositioning(storageDetailsId,sheet,"20",loanApplicationMaster,technologyPositioningDetailRepository);


        //save question 781
        try {
            String question781Answer = getDataFromCell(sheet, "C4");
            if (!(question781Answer.isEmpty())) {//if textbox is empty not insert record
                if (!(question781Answer.equalsIgnoreCase("Insert Text Here"))) {
                	dprUserDataDetail.setManufacturingProcess(question781Answer);
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        //save question 782
        try {
            String question782Answer = getDataFromCell(sheet, "C6");
            if (!(question782Answer.isEmpty())) {//if textbox is empty not insert record
                if (!(question782Answer.equalsIgnoreCase("Insert Text Here"))) {
                	dprUserDataDetail.setTechnicalKnowHow(question782Answer);
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public static void saveTechnologyPositioning(Long storageDetailsId,XSSFSheet sheet, String rowNumber,LoanApplicationMaster loanApplicationMaster,TechnologyPositioningDetailRepository technologyPositioningDetailRepository)
    {
        int nullCounter=0;
        String type           = getDataFromCell(sheet,"B"+rowNumber);
        String details        = getDataFromCell(sheet,"C"+rowNumber);

        if (type.isEmpty())
        {
            ++nullCounter;
        }
        if (details.isEmpty())
        {
            ++nullCounter;
        }
        if (!(nullCounter==2))
        {
            try {
            	TechnologyPositioningDetail technologyPositioning = new TechnologyPositioningDetail();
                technologyPositioning.setApplicationId(loanApplicationMaster);
                technologyPositioning.setType(type);
                technologyPositioning.setDetails(details);
                technologyPositioning.setStorageDetailsId(storageDetailsId);
                technologyPositioning.setModifiedDate(new Date());
                technologyPositioning.setCreatedDate(new Date());
                technologyPositioning.setIsActive(true);
                technologyPositioningDetailRepository.save(technologyPositioning);
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
