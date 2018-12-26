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
public class DprFifthSheetExcelReader
{

    private static final Logger logger = LoggerFactory.getLogger(DprFifthSheetExcelReader.class);

    public static void run(Long storageDetailsId, XSSFSheet sheet,DprUserDataDetail dprUserDataDetail) {
        //save question 783
        try {
            String question783Answer = getDataFromCell(sheet, "C4");
            if (!(question783Answer.isEmpty()) && !(question783Answer.equals(CommonUtils.INSERT_TEXT_HERE)) ) {//if textbox is empty not insert record
                	dprUserDataDetail.setGlobalScenario(question783Answer);
            }
        }catch (Exception e) {
            logger.error(CommonUtils.EXCEPTION,e);
        }

        //save question 784
        try {
            String question784Answer = getDataFromCell(sheet, "C6");
            if (!(question784Answer.isEmpty()) && !(question784Answer.equals(CommonUtils.INSERT_TEXT_HERE)) ) {//if textbox is empty not insert record
                	dprUserDataDetail.setNationalScenario(question784Answer);
            }
        }catch (Exception e) {
            logger.error(CommonUtils.EXCEPTION,e);
        }

        //save question 785
        try {
            String question785Answer = getDataFromCell(sheet, "C8");
            if (!(question785Answer.isEmpty()) && !(question785Answer.equals(CommonUtils.INSERT_TEXT_HERE)) ) {//if textbox is empty not insert record
                	dprUserDataDetail.setCompetitiveLandscape(question785Answer);
            }
        }catch (Exception e) {
            logger.error(CommonUtils.EXCEPTION,e);
        }

        //save question 786
        try {
            String question786Answer = getDataFromCell(sheet, "C10");
            if (!(question786Answer.isEmpty()) && !(question786Answer.equals(CommonUtils.INSERT_TEXT_HERE)) ) {//if textbox is empty not insert record
                	dprUserDataDetail.setKeyPlayers(question786Answer);
            }
        }catch (Exception e) {
            logger.error(CommonUtils.EXCEPTION,e);
        }

        //save question 787
        try {
            String question787Answer = getDataFromCell(sheet, "C12");
            if (!(question787Answer.isEmpty()) && !(question787Answer.equals(CommonUtils.INSERT_TEXT_HERE)) ) {//if textbox is empty not insert record
                	dprUserDataDetail.setMarketTrends(question787Answer);
            }
        }catch (Exception e) {
            logger.error(CommonUtils.EXCEPTION,e);
        }

        //save question 788
        try {
            String question788Answer = getDataFromCell(sheet, "C14");
            if (!(question788Answer.isEmpty()) && !(question788Answer.equals(CommonUtils.INSERT_TEXT_HERE)) ) {//if textbox is empty not insert record
                	dprUserDataDetail.setMarketNeeds(question788Answer);
            }
        }catch (Exception e) {
            logger.error(CommonUtils.EXCEPTION,e);
        }

        //save question 789
        try {
            String question789Answer = getDataFromCell(sheet, "C16");
            if (!(question789Answer.isEmpty()) && !(question789Answer.equals(CommonUtils.INSERT_TEXT_HERE)) ) {//if textbox is empty not insert record
                	dprUserDataDetail.setMarketGrowth(question789Answer);
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
