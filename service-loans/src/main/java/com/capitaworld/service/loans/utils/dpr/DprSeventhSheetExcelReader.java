package com.capitaworld.service.loans.utils.dpr;

import com.capitaworld.service.loans.utils.CommonUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.capitaworld.service.loans.domain.fundseeker.corporate.DprUserDataDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Sanket
 *
 */
public class DprSeventhSheetExcelReader
{

    private DprSeventhSheetExcelReader() {
        // Do nothing because of X and Y.
    }

    private static final Logger logger = LoggerFactory.getLogger(DprSeventhSheetExcelReader.class);

    public static void run(Long applicationId, Long storageDetailsId,
			XSSFSheet sheet, DprUserDataDetail dprUserDataDetail) {

        //save 792 question
        try {
            String question792Answer = getDataFromCell(sheet, "C4");
            if (!(question792Answer.isEmpty()) && !(question792Answer.equals(CommonUtils.INSERT_TEXT_HERE)) ) {//if textbox is empty not insert record
                    dprUserDataDetail.setAbsenceCivicRestrictions(question792Answer);
            }
        }catch (Exception e) {
            logger.error(CommonUtils.EXCEPTION,e);
        }

        //save 793 question
        try {
            String question793Answer = getDataFromCell(sheet, "C6");
            if (!(question793Answer.isEmpty()) && !(question793Answer.equals(CommonUtils.INSERT_TEXT_HERE)) ) {//if textbox is empty not insert record
                    dprUserDataDetail.setProximityToSourceRawMaterials(question793Answer);
            }
        }catch (Exception e) {
            logger.error(CommonUtils.EXCEPTION,e);
        }

        //save 794 question
        try {
            String question794Answer = getDataFromCell(sheet, "C8");
            if (!(question794Answer.isEmpty()) && !(question794Answer.equals(CommonUtils.INSERT_TEXT_HERE)) ) {//if textbox is empty not insert record
                    dprUserDataDetail.setMarketForTheProduct(question794Answer);
            }
        }catch (Exception e) {
            logger.error(CommonUtils.EXCEPTION,e);
        }


        //save 795 question
        try {
            String question795Answer = getDataFromCell(sheet, "C11");
            if (!(question795Answer.isEmpty()) && !(question795Answer.equals(CommonUtils.INSERT_TEXT_HERE)) ) {//if textbox is empty not insert record
                    dprUserDataDetail.setPowerAvailability(question795Answer);
            }
        }catch (Exception e) {
            logger.error(CommonUtils.EXCEPTION,e);
        }

        //save 796 question
        try {
            String question796Answer = getDataFromCell(sheet, "C12");
            if (!(question796Answer.isEmpty()) && !(question796Answer.equals(CommonUtils.INSERT_TEXT_HERE)) ) {//if textbox is empty not insert record
                    dprUserDataDetail.setWaterAvailability(question796Answer);
            }
        }catch (Exception e) {
            logger.error(CommonUtils.EXCEPTION,e);
        }

        //save 797 question
        try {
            String question797Answer = getDataFromCell(sheet, "C13");
            if (!(question797Answer.isEmpty()) && !(question797Answer.equals(CommonUtils.INSERT_TEXT_HERE)) ) {//if textbox is empty not insert record
                    dprUserDataDetail.setLabourAvailability(question797Answer);
            }
        }catch (Exception e) {
            logger.error(CommonUtils.EXCEPTION,e);
        }

        //save 798 question
        try {
            String question798Answer = getDataFromCell(sheet, "C14");
            if (!(question798Answer.isEmpty()) && !(question798Answer.equals(CommonUtils.INSERT_TEXT_HERE)) ) {//if textbox is empty not insert record
                    dprUserDataDetail.setTransportAvailability(question798Answer);
            }
        }catch (Exception e) {
            logger.error(CommonUtils.EXCEPTION,e);
        }

        //save 799 question
        try {
            String question799Answer = getDataFromCell(sheet, "C15");
            if (!(question799Answer.isEmpty()) && !(question799Answer.equals(CommonUtils.INSERT_TEXT_HERE)) ) {//if textbox is empty not insert record
                    dprUserDataDetail.setOtherAvailability(question799Answer);
            }
        }catch (Exception e) {
            logger.error(CommonUtils.EXCEPTION,e);
        }

        //save 800 question
        try {
            String question800Answer = getDataFromCell(sheet, "C17");
            if (!(question800Answer.isEmpty()) && !(question800Answer.equals(CommonUtils.INSERT_TEXT_HERE)) ) {//if textbox is empty not insert record
                    dprUserDataDetail.setWhetherClearanceIsObtainedFromPollutionControlAuthority(question800Answer);
            }
        }catch (Exception e) {
            logger.error(CommonUtils.EXCEPTION,e);
        }

        //save 801 question
        try {
            String question801Answer = getDataFromCell(sheet, "C19");
            if (!(question801Answer.isEmpty()) && !(question801Answer.equals(CommonUtils.INSERT_TEXT_HERE)) ) {//if textbox is empty not insert record
                    dprUserDataDetail.setOtherBenefits(question801Answer);
            }
        }catch (Exception e) {
            logger.error(CommonUtils.EXCEPTION,e);
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
