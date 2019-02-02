package com.capitaworld.service.loans.utils.dpr;

import com.capitaworld.service.loans.utils.CommonUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.DprUserDataDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Sanket
 *
 */
public class DprThirdSheetExcelReader
{

    private DprThirdSheetExcelReader() {
        // Do nothing because of X and Y.
    }

    private static final Logger logger = LoggerFactory.getLogger(DprThirdSheetExcelReader.class);

	public static void run(Long storageDetailsId,XSSFSheet sheet,LoanApplicationMaster loanApplicationMaster,DprUserDataDetail dprUserDataDetail) {
        try {
            //sheet number 3 fill 780 question answer in db
            String productAndServices = getDataFromCell(sheet, "C8");
            if (!(productAndServices.isEmpty()) && !(productAndServices.equals("Insert Text Here")) ) {//if textbox is empty not insert record
                	dprUserDataDetail.setSpecialFeaturesProductsAndServices(productAndServices);
            }
        }catch (Exception e)
        {
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

