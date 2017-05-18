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
public class DprFifthSheetExcelReader
{
    public static void run(Long storageDetailsId, XSSFSheet sheet,DprUserDataDetail dprUserDataDetail) {
        //save question 783
        try {
            String question783Answer = getDataFromCell(sheet, "C4");
            if (!(question783Answer.isEmpty())) {//if textbox is empty not insert record
                if (!(question783Answer.equals("Insert Text Here"))) {
                	dprUserDataDetail.setGlobalScenario(question783Answer);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        //save question 784
        try {
            String question784Answer = getDataFromCell(sheet, "C6");
            if (!(question784Answer.isEmpty())) {//if textbox is empty not insert record
                if (!(question784Answer.equals("Insert Text Here"))) {
                	dprUserDataDetail.setNationalScenario(question784Answer);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        //save question 785
        try {
            String question785Answer = getDataFromCell(sheet, "C8");
            if (!(question785Answer.isEmpty())) {//if textbox is empty not insert record
                if (!(question785Answer.equals("Insert Text Here"))) {
                	dprUserDataDetail.setCompetitiveLandscape(question785Answer);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        //save question 786
        try {
            String question786Answer = getDataFromCell(sheet, "C10");
            if (!(question786Answer.isEmpty())) {//if textbox is empty not insert record
                if (!(question786Answer.equals("Insert Text Here"))) {
                	dprUserDataDetail.setKeyPlayers(question786Answer);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        //save question 787
        try {
            String question787Answer = getDataFromCell(sheet, "C12");
            if (!(question787Answer.isEmpty())) {//if textbox is empty not insert record
                if (!(question787Answer.equals("Insert Text Here"))) {
                	dprUserDataDetail.setMarketTrends(question787Answer);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        //save question 788
        try {
            String question788Answer = getDataFromCell(sheet, "C14");
            if (!(question788Answer.isEmpty())) {//if textbox is empty not insert record
                if (!(question788Answer.equals("Insert Text Here"))) {
                	dprUserDataDetail.setMarketNeeds(question788Answer);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        //save question 789
        try {
            String question789Answer = getDataFromCell(sheet, "C16");
            if (!(question789Answer.isEmpty())) {//if textbox is empty not insert record
                if (!(question789Answer.equals("Insert Text Here"))) {
                	dprUserDataDetail.setMarketGrowth(question789Answer);
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
