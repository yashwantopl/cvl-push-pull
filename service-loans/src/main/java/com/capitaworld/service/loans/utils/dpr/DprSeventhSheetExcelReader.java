package com.capitaworld.service.loans.utils.dpr;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.capitaworld.service.loans.domain.fundseeker.corporate.DprUserDataDetail;


/**
 * @author Sanket
 *
 */
public class DprSeventhSheetExcelReader
{
    public static void run(Long applicationId, Long storageDetailsId,
			XSSFSheet sheet, DprUserDataDetail dprUserDataDetail) {

        //save 792 question
        try {
            String question792Answer = getDataFromCell(sheet, "C4");
            if (!(question792Answer.isEmpty())) {//if textbox is empty not insert record
                if (!(question792Answer.equals("Insert Text Here"))) {
                    dprUserDataDetail.setAbsenceCivicRestrictions(question792Answer);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        //save 793 question
        try {
            String question793Answer = getDataFromCell(sheet, "C6");
            if (!(question793Answer.isEmpty())) {//if textbox is empty not insert record
                if (!(question793Answer.equals("Insert Text Here"))) {
                    dprUserDataDetail.setProximityToSourceRawMaterials(question793Answer);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        //save 794 question
        try {
            String question794Answer = getDataFromCell(sheet, "C8");
            if (!(question794Answer.isEmpty())) {//if textbox is empty not insert record
                if (!(question794Answer.equals("Insert Text Here"))) {
                    dprUserDataDetail.setMarketForTheProduct(question794Answer);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }


        //save 795 question
        try {
            String question795Answer = getDataFromCell(sheet, "C11");
            if (!(question795Answer.isEmpty())) {//if textbox is empty not insert record
                if (!(question795Answer.equals("Insert Text Here"))) {
                    dprUserDataDetail.setPowerAvailability(question795Answer);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        //save 796 question
        try {
            String question796Answer = getDataFromCell(sheet, "C12");
            if (!(question796Answer.isEmpty())) {//if textbox is empty not insert record
                if (!(question796Answer.equals("Insert Text Here"))) {
                    dprUserDataDetail.setWaterAvailability(question796Answer);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        //save 797 question
        try {
            String question797Answer = getDataFromCell(sheet, "C13");
            if (!(question797Answer.isEmpty())) {//if textbox is empty not insert record
                if (!(question797Answer.equals("Insert Text Here"))) {
                    dprUserDataDetail.setLabourAvailability(question797Answer);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        //save 798 question
        try {
            String question798Answer = getDataFromCell(sheet, "C14");
            if (!(question798Answer.isEmpty())) {//if textbox is empty not insert record
                if (!(question798Answer.equals("Insert Text Here"))) {
                    dprUserDataDetail.setTransportAvailability(question798Answer);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        //save 799 question
        try {
            String question799Answer = getDataFromCell(sheet, "C15");
            if (!(question799Answer.isEmpty())) {//if textbox is empty not insert record
                if (!(question799Answer.equals("Insert Text Here"))) {
                    dprUserDataDetail.setOtherAvailability(question799Answer);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        //save 800 question
        try {
            String question800Answer = getDataFromCell(sheet, "C17");
            if (!(question800Answer.isEmpty())) {//if textbox is empty not insert record
                if (!(question800Answer.equals("Insert Text Here"))) {
                    dprUserDataDetail.setWhetherClearanceIsObtainedFromPollutionControlAuthority(question800Answer);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        //save 801 question
        try {
            String question801Answer = getDataFromCell(sheet, "C19");
            if (!(question801Answer.isEmpty())) {//if textbox is empty not insert record
                if (!(question801Answer.equals("Insert Text Here"))) {
                    dprUserDataDetail.setOtherBenefits(question801Answer);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
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
