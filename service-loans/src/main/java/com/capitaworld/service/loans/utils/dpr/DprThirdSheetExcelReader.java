package com.capitaworld.service.loans.utils.dpr;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.DprUserDataDetail;


/**
 * @author Sanket
 *
 */
public class DprThirdSheetExcelReader
{
	public static void run(Long storageDetailsId,XSSFSheet sheet,LoanApplicationMaster loanApplicationMaster,DprUserDataDetail dprUserDataDetail) {
        try {
            //sheet number 3 fill 780 question answer in db
            String productAndServices = getDataFromCell(sheet, "C8");
            if (!(productAndServices.isEmpty())) {//if textbox is empty not insert record
                if (!(productAndServices.equals("Insert Text Here"))) {
                	dprUserDataDetail.setSpecialFeaturesProductsAndServices(productAndServices);
                }
            }
        }catch (Exception e)
        {
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

