package com.capitaworld.service.loans.utils.dpr;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.DprUserDataDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.ProjectImplementationScheduleDetail;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ProjectImplementationScheduleDetailRepository;


/**
 * @author Sanket
 *
 */
public class DprEighthSheetExcelReader
{

	public static void run(Long storageDetailsId,XSSFSheet sheet,LoanApplicationMaster loanApplicationMaster,ProjectImplementationScheduleDetailRepository projectImplementationScheduleDetailRepository,DprUserDataDetail dprUserDataDetail) {
        saveProjectImplementationSchedule(storageDetailsId,sheet,"11",loanApplicationMaster,projectImplementationScheduleDetailRepository);
        saveProjectImplementationSchedule(storageDetailsId,sheet,"12",loanApplicationMaster,projectImplementationScheduleDetailRepository);
        saveProjectImplementationSchedule(storageDetailsId,sheet,"13",loanApplicationMaster,projectImplementationScheduleDetailRepository);
        saveProjectImplementationSchedule(storageDetailsId,sheet,"14",loanApplicationMaster,projectImplementationScheduleDetailRepository);
        saveProjectImplementationSchedule(storageDetailsId,sheet,"15",loanApplicationMaster,projectImplementationScheduleDetailRepository);
        saveProjectImplementationSchedule(storageDetailsId,sheet,"16",loanApplicationMaster,projectImplementationScheduleDetailRepository);
        saveProjectImplementationSchedule(storageDetailsId,sheet,"17",loanApplicationMaster,projectImplementationScheduleDetailRepository);
        saveProjectImplementationSchedule(storageDetailsId,sheet,"18",loanApplicationMaster,projectImplementationScheduleDetailRepository);
        saveProjectImplementationSchedule(storageDetailsId,sheet,"19",loanApplicationMaster,projectImplementationScheduleDetailRepository);



        //save 802  question
        try {
            //sheet number 3 fill 780 question answer in db
            String question802Answer = getDataFromCell(sheet, "C25");
            if (!(question802Answer.isEmpty())) {//if textbox is empty not insert record
                if (!(question802Answer.equals("Insert Text Here"))) {
                    dprUserDataDetail.setProjectJjustification(question802Answer);
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void saveProjectImplementationSchedule(Long storageDetailsId,XSSFSheet sheet, String rowNumber,LoanApplicationMaster profile,ProjectImplementationScheduleDetailRepository projectImplementationScheduleDetailRepository)
    {
        int nullCounter=0;
        String activities           = getDataFromCell(sheet,"B"+rowNumber);
        String totalTimeline        = getDataFromCell(sheet,"C"+rowNumber);
        String dateOfCommencement   = getDataFromCell(sheet,"D"+rowNumber);
        String dateOfCompletions    = getDataFromCell(sheet,"E"+rowNumber);

        if (activities.isEmpty())
        {
            ++nullCounter;
        }
        if (totalTimeline.isEmpty())
        {
            ++nullCounter;
        }
        if (dateOfCommencement.isEmpty())
        {
            ++nullCounter;
        }
        if (dateOfCompletions.isEmpty())
        {
            ++nullCounter;
        }
        if (!(nullCounter==4))
        {
            try {
            	ProjectImplementationScheduleDetail projectImplementationSchedule = new ProjectImplementationScheduleDetail();
                projectImplementationSchedule.setApplicationId(profile);
                projectImplementationSchedule.setActivities(activities);
                projectImplementationSchedule.setTimelineTotal(totalTimeline);
                projectImplementationSchedule.setCommencementDate(dateOfCommencement);
                projectImplementationSchedule.setCompletionDate(dateOfCompletions);
                projectImplementationSchedule.setStorageDetailsId(storageDetailsId);
                projectImplementationSchedule.setIsActive(true);
                projectImplementationSchedule.setCreatedDate(new Date());
                projectImplementationSchedule.setModifiedDate(new Date());
                projectImplementationScheduleDetailRepository.save(projectImplementationSchedule);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static String getDataFromCell(XSSFSheet sheet,String cellNumber)
    {
        String returnValue= new String();
        CellReference cellReference = new CellReference(cellNumber);
        Row row = sheet.getRow(cellReference.getRow());
        Cell cell = row.getCell(cellReference.getCol());
        if (cellNumber.contains("D")||cellNumber.contains("E")) {
            String cellValue = ""+cell.getNumericCellValue();
            if(!(cellValue.isEmpty())){
                if (!(cellValue.equals("0.0"))) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date d = cell.getDateCellValue();
                    returnValue = simpleDateFormat.format(d).toString();
                }
            } else {
              returnValue="";
            }
        } else {
            cell.setCellType(Cell.CELL_TYPE_STRING);
            returnValue = cell.getStringCellValue();
        }
        return returnValue;
    }
}
